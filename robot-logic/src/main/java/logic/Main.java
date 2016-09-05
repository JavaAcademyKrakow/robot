package logic;

import logic.controller.MainController;
import logic.springconfig.SpringConfig;
import logic.springconfig.TimeManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * Entry point...
 */
@Slf4j
public class Main {
    private static final MainController MAIN_CONTROLLER = new MainController();
    public static TimeManager TIME_MANAGER = new TimeManager();

    public static void launch() {
        try {
            MAIN_CONTROLLER.launch();
        } catch (InterruptedException e) {
            log.debug("Interrupted exception found", e);
            Thread.currentThread().interrupt();
        }
    }


    /**
     * Entry point of the application.
     *
     * @param args - unused
     */
    @SuppressWarnings({"unused", "resource"})
    public static void main(String[] args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
    }
}