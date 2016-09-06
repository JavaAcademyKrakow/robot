package logic.controller;


import domain.CategoryName;
import logic.parser.Parser;
import lombok.extern.slf4j.Slf4j;
import repositories.ParsedBook;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

import static java.lang.Thread.sleep;

/**
 * This class is a wrapper for the threads of parser (because ParserThread is Callable). It allows to
 * avoid blocking executor at the level of ParserLauncherThread.
 */
@Slf4j
final class ParserWrapperThread implements Runnable {

    private final Class<? extends Parser> parserClass;
    private final BlockingQueue<ParsedBook> rootQueue;
    private final CategoryName category;
    private String linkToParse;

    private final ParserLauncherThread parentThread;


    private static final String MESSAGE = "Interrupted exception found";


    ParserWrapperThread(Class<? extends Parser> parserClass, String linkToParse, ParserLauncherThread parentThread,
                        BlockingQueue<ParsedBook> queue, CategoryName category) {
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
            log.debug(MESSAGE, e);
            Thread.currentThread().interrupt();
        }
        parentThread.executing.set(false);
    }

    private void handleResults(Optional<List<ParsedBook>> listOptional) {
        if (!listOptional.isPresent()) {
            stopExecutingParent();
            return;
        }

        List<ParsedBook> list = listOptional.get();

        if (!list.isEmpty()) {
            list.forEach(e -> {
                try {
                    rootQueue.put(e);
                } catch (InterruptedException e1) {
                    log.debug(MESSAGE, e);
                    Thread.currentThread().interrupt();
                }
            });

        }
    }

    @Override
    public void run() {
        Callable<Optional<List<ParsedBook>>> callable = new ParserThread(parserClass, category, linkToParse);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Optional<List<ParsedBook>>> futureListOfBooks = executor.submit(callable);

        try {
            handleResults(futureListOfBooks.get());
        } catch (InterruptedException | ExecutionException e) {
            log.debug("Exception found", e);
            Thread.currentThread().interrupt();
        }

        executor.shutdown();
    }
}