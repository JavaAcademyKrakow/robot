package logic.controller;


import domain.CategoryName;
import logic.parser.Parser;
import lombok.extern.slf4j.Slf4j;
import repositories.ParsedBook;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * The root of the tree structure of thread tree. Propagates the mappings from the single parser class
 * to the children threads.
 */
@Slf4j
final class ParserClassThread implements Runnable {
    private final Class<? extends Parser> parserClass;
    private final Map<CategoryName, List<URIGenerator>> categoryMappings;
    private final BlockingQueue<ParsedBook> rootQueue;

    ParserClassThread(final Class<? extends Parser> clazz, final Map<CategoryName, List<URIGenerator>> mappings, BlockingQueue<ParsedBook> queue) {
        parserClass = clazz;
        categoryMappings = Collections.unmodifiableMap(mappings);
        rootQueue = queue;
    }

    @Override
    public void run() {
        ExecutorService executor = Executors.newFixedThreadPool(categoryMappings.size());
        categoryMappings.keySet().forEach(e -> executor.submit(new SingleCategoryThread(parserClass, categoryMappings.get(e), rootQueue, e)));
        executor.shutdown();

        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            log.debug("Interrupted exception found", e);
            Thread.currentThread().interrupt();
        }
    }
}