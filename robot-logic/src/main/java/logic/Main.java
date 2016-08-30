package logic;

import logic.controller.MainController;
import lombok.extern.slf4j.Slf4j;

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
            log.error("Interrupted exception found", e);
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
}
