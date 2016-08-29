package logic.controller;


import dbconfiguration.SpringDBConfiguration;
import lombok.extern.slf4j.Slf4j;

import DAO.BookDAO;
import domain.Book;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

@Slf4j
final class BooksConsumerThread implements Runnable {

    ApplicationContext context = new AnnotationConfigApplicationContext(SpringDBConfiguration.class);
    BookDAO bookDAO = context.getBean(BookDAO.class);
    //    static long cnt = 0;
    private final BlockingQueue<Book> rootQueue;
    private boolean run = true;

    BooksConsumerThread(BlockingQueue<Book> queue) {
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

        Queue<Book> drained = new LinkedList<>();

        while (running()) {

            int n = rootQueue.size();

            if (n > 0) {
                rootQueue.drainTo(drained);
                drained.stream().forEach(e -> bookDAO.save(e));
                log.info(drained.toString());
                drained.clear();
            }
        }
    }
}