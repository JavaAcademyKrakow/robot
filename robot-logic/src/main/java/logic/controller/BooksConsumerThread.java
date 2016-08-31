package logic.controller;


import dbconfiguration.SpringDBConfiguration;
import lombok.extern.slf4j.Slf4j;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repositories.BookInput;
import repositories.ParsedBook;


import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

@Slf4j
final class BooksConsumerThread implements Runnable {

    private final BlockingQueue<ParsedBook> rootQueue;
    private ApplicationContext context = new AnnotationConfigApplicationContext(SpringDBConfiguration.class);
    private BookInput databaseInput = context.getBean(BookInput.class);
    private boolean run = true;

    BooksConsumerThread(BlockingQueue<ParsedBook> queue) {
        rootQueue = queue;
    }

    private synchronized boolean running() {
        return run;
    }

    synchronized void stopRunning() {
        run = false;
    }


    @Override
    public void run() {

        Queue<ParsedBook> drained = new LinkedList<>();

        while (running()) {

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