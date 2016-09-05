package logic.springconfig;

import logic.Main;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import static logic.Main.*;

@Slf4j
class ScheduledBean {

    /**
     * This method tries to launch the controller every four hours.
     */
    @Scheduled(cron = "0 0 */4 * * ?")
    public void launch() {

        if (TIME_MANAGER.canRun()) {

            log.info("Launching controller...");
            new Thread(Main::launch).start();
            log.info("Controller launched...");

        } else {
            log.info("New launch refused (too often)...");
        }
    }
}