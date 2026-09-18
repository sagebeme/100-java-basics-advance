public class CountdownTimer {
    private int secondsLeft;
    private boolean running = false;

    public CountdownTimer(int startSeconds) {
        this.secondsLeft = startSeconds;
    }

    public void start() {
        running = true;
    }

    public void pause() {
        running = false;
    }

    /**
     * Advances the timer by one second, if running. Has no effect once it reaches zero.
     */
    public void tick() {
        if (running && secondsLeft > 0) {
            secondsLeft--;
        }
    }

    public boolean isComplete() {
        return secondsLeft == 0;
    }

    public int getSecondsLeft() {
        return secondsLeft;
    }

    public static String format(int seconds) {
        return String.format("%02d:%02d", seconds / 60, seconds % 60);
    }
}
