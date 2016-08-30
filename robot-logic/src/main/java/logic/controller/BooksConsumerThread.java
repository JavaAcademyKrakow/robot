package logic.controller;

import logic.book.Book;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

@Slf4j
final class BooksConsumerThread implements Runnable {

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
                log.info(drained.toString());
                drained.clear();
            }
        }
    }
}