package logic.controller;


import dbconfiguration.SpringDBConfiguration;
import lombok.extern.slf4j.Slf4j;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repositories.BookSaver;
import repositories.ParsedBook;


import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
final class BooksConsumerThread implements Runnable {

    private final BlockingQueue<ParsedBook> rootQueue;
    private ApplicationContext context = new AnnotationConfigApplicationContext(SpringDBConfiguration.class);
    private BookSaver databaseInput = context.getBean(BookSaver.class);

    final AtomicBoolean running = new AtomicBoolean(true);

    BooksConsumerThread(BlockingQueue<ParsedBook> queue) {
        rootQueue = queue;
    }

    @Override
    public void run() {

        Queue<ParsedBook> drained = new LinkedList<>();

        while (running.get()) {

            int n = rootQueue.size();

            if (n > 0) {
                rootQueue.drainTo(drained);
                drained.stream().forEach(databaseInput::save);
                log.info(drained.toString());
                drained.clear();
            }
        }
    }
}
