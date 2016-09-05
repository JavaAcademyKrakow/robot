package logic.springconfig;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.*;

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
