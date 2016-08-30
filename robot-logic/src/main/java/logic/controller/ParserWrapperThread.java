package logic.controller;

import logic.book.Book;
import logic.book.Category;
import logic.parser.Parser;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.*;

import static java.lang.Thread.sleep;

/**
 * This class is a wrapper for the threads of logic.parser (because ParserThread is Callable). It allows to
 * avoid blocking executor at the level of ParserLauncherThread.
 */
@Slf4j
final class ParserWrapperThread implements Runnable {

    private final Class<? extends Parser> parserClass;
    private final BlockingQueue<Book> rootQueue;
    private final Category category;
    private String linkToParse;

    private final ParserLauncherThread parentThread;


    ParserWrapperThread(Class<? extends Parser> parserClass, String linkToParse, ParserLauncherThread parentThread,
                        BlockingQueue<Book> queue, Category category) {
        this.parserClass = parserClass;
        this.linkToParse = linkToParse;
        this.parentThread = parentThread;
        rootQueue = queue;
        this.category = category;
    }

    private void stopExecutingParent() {
        try {
            sleep(500);
        } catch (InterruptedException e) {
            log.debug("Interrupted exception found", e);
            log.error("Interrupted exception found", e);
        }
        parentThread.resetExecutingFlag();
    }

    private void handleResults(List<Book> list) {
        if (list == null) {
            stopExecutingParent();
            return;
        }

        if (!list.isEmpty()) {
            list.forEach(e -> {
                try {
                    rootQueue.put(e);
                } catch (InterruptedException e1) {
                    log.debug("Interrupted exception found", e);
                    log.error("Interrupted exception found", e);
                }
            });

        }
    }

    @Override
    public void run() {
        Callable<List<Book>> callable = new ParserThread(parserClass, category, linkToParse);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<List<Book>> futureListOfBooks = executor.submit(callable);

        try {
            handleResults(futureListOfBooks.get());
        } catch (InterruptedException | ExecutionException e) {
            log.debug("Exception found", e);
            log.error("Exception found", e);
        }

        executor.shutdown();
    }
}