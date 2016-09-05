package logic.springconfig;

import logic.Main;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static logic.Main.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@Slf4j
@RestController
class ResetRestController {

    /**
     * REST controller to manage refresh request from the user. It is invoked when user visits the '/reset' URL.
     * @return RestResponse object which contains an hour, minute, second of the last run and the boolean
     * which marks if the launch() method of the controller was invoked again. The response is in JSON format.
     */
    @RequestMapping(value = "/reset", method = GET)
    public ResetResponse reset() {
        boolean canRun = TIME_MANAGER.canRun();

        if (canRun) {
            log.info("Launching controller...");

            new Thread(Main::launch).start();

            log.info("Controller launched...");
        } else {
            log.info("New launch refused (too often)...");
        }

        LocalDateTime lastDate = TIME_MANAGER.getLastRun();
        return new ResetResponse(lastDate.getHour(), lastDate.getMinute(), lastDate.getSecond(), canRun);
    }
}