package logic.controller;

import logic.book.Book;
import logic.book.Category;
import logic.parser.Parser;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

import static java.lang.Thread.sleep;

/**
 * Class to launch the constant size thread pool of parsers.
 */
@Slf4j
final class ParserLauncherThread implements Runnable {
    private final Class<? extends Parser> parserClass;
    private final URIGenerator generator;
    private final BlockingQueue<Book> rootQueue;
    private final Category category;

    private static final int MAX_POOL_SIZE = 5;
    private boolean executing = true;

    ParserLauncherThread(final Class<? extends Parser> parserClass, final URIGenerator generator,
                         final BlockingQueue<Book> queue, Category category) {
        this.parserClass = parserClass;
        this.generator = generator;
        rootQueue = queue;
        this.category = category;
    }

    private synchronized boolean isExecuting() {
        return executing;
    }

    synchronized void resetExecutingFlag() {
        executing = false;
    }


    @Override
    public void run() {

        ExecutorService executor = Executors.newFixedThreadPool(MAX_POOL_SIZE);
        LinkedBlockingQueue<ParserWrapperThread> queue = new LinkedBlockingQueue<>();

        while (isExecuting()) {

            try {
                int count = ((ThreadPoolExecutor) executor).getActiveCount();
                int toRun = MAX_POOL_SIZE - count;

                while (toRun > 0) {
                    queue.put(new ParserWrapperThread(parserClass, generator.generateNextFullURI(), this, rootQueue, category));
                    --toRun;
                }

                if (!queue.isEmpty()) {
                    executor.execute(queue.take());
                }

                sleep(100);

            } catch (InterruptedException e) {
                log.debug("Interrupted exception found", e);
                log.error("Interrupted exception found", e);
            }
        }

        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            log.debug("Interrupted exception found", e);
            log.error("Interrupted exception found", e);
        }
    }
}