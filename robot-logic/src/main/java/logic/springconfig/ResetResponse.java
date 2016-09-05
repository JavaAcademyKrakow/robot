package logic.springconfig;

class ResetResponse {

    private final int hour;
    private final int minutes;
    private final int seconds;

    final boolean canReset;

    ResetResponse(int hour, int minutes, int seconds, boolean canReset) {
        this.hour = hour;
        this.minutes = minutes;
        this.seconds = seconds;
        this.canReset = canReset;
    }

    public boolean getCanReset() {
        return canReset;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getHour() {
        return hour;
    }

}