package logic;

import logic.controller.MainController;
import logic.springconfig.TimeManager;
import lombok.extern.slf4j.Slf4j;

/**
 * Entry point...
 */
@Slf4j
public class Main {
    private static final MainController MAIN_CONTROLLER = new MainController();
    public static final TimeManager TIME_MANAGER = new TimeManager();

    private Main() {}

    /**
     * Launcher of the main controller. It starts crawling the web pages and storing results in the database.
     */
    public static void launch() {
        try {
            MAIN_CONTROLLER.launch();
        } catch (InterruptedException e) {
            log.debug("Interrupted exception found", e);
            Thread.currentThread().interrupt();
        }
    }
}