package logic.controller;


import domain.CategoryName;
import logic.parser.Parser;
import lombok.extern.slf4j.Slf4j;
import repositories.ParsedBook;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Thread.sleep;

/**
 * Class to launch the constant size thread pool of parsers.
 */
@Slf4j
final class ParserLauncherThread implements Runnable {
    private static final int MAX_POOL_SIZE = 5;
    private final Class<? extends Parser> parserClass;
    private final URIGenerator generator;
    private final BlockingQueue<ParsedBook> rootQueue;
    private final CategoryName category;
    private static final String MESSAGE = "Interrupted exception found";

    final AtomicBoolean executing = new AtomicBoolean(true);

    ParserLauncherThread(final Class<? extends Parser> parserClass, final URIGenerator generator,
                         final BlockingQueue<ParsedBook> queue, CategoryName category) {
        this.parserClass = parserClass;
        this.generator = generator;
        rootQueue = queue;
        this.category = category;
    }


    @Override
    public void run() {

        ExecutorService executor = Executors.newFixedThreadPool(MAX_POOL_SIZE);
        LinkedBlockingQueue<ParserWrapperThread> queue = new LinkedBlockingQueue<>();

        while (executing.get()) {

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
                log.debug(MESSAGE, e);
                Thread.currentThread().interrupt();
            }
        }

        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            log.debug(MESSAGE, e);
            Thread.currentThread().interrupt();
        }
    }
}