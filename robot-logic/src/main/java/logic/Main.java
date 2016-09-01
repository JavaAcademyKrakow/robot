package logic;

import logic.controller.MainController;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * Entry point...
 */
@Slf4j
public class Main {
    private final MainController mainController = new MainController();

    private void launch() {
        try {
            mainController.launch();
        } catch (InterruptedException e) {
            log.debug("Interrupted exception found", e);
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Entry point of the application.
     * @param args - unused
     */
    public static void main(String[] args) {
        Main entryPoint = new Main();
        entryPoint.launch();
    }


    // Helper class to check if the time to launch the crawler (on user's demand) is not to frequent.
    private static class TimeManager {

        private static final long MAX_DIFF_HOURS = 1;

        private LocalDateTime lastRun;
        private LocalDateTime expectedNextRun;

        private void updateExpectedNextRun() {
            expectedNextRun = lastRun.plusHours(MAX_DIFF_HOURS);
        }

        private void update(LocalDateTime date) {
            lastRun = date;
            updateExpectedNextRun();
        }

        private boolean canRun() {
            LocalDateTime date = LocalDateTime.now();

            if (lastRun == null || date.isAfter(expectedNextRun)) {
                update(date);
                return true;
            }

            return false;
        }
    }
}