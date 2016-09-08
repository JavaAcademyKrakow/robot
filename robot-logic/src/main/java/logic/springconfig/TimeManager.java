package logic.springconfig;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.*;

/**
 * TimeManager class which is used to check if the next run of the crawler is possible. The crawler should not
 * be launched more often than a one hour (since the last run).
 * If the new launch is possible, then lastRun field is reset to the time point of the new launch.
 */
public class TimeManager {

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

    boolean canRun() {
        LocalDateTime date = now();

        if (lastRun == null || date.isAfter(expectedNextRun)) {
            update(date);
            return true;
        }

        return false;
    }

    LocalDateTime getLastRun() {
        return lastRun;
    }
}
