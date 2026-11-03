public class CountdownTimer {

    private final int startSeconds;
    private int secondsLeft;
    private boolean running;

    public CountdownTimer(int startSeconds) {
        if (startSeconds <= 0) {
            throw new IllegalArgumentException("A countdown must start above 0, not " + startSeconds);
        }
        this.startSeconds = startSeconds;
        this.secondsLeft = startSeconds;
    }

    public void start() {
        if (secondsLeft > 0) {
            running = true;
        }
    }

    public void pause() {
        running = false;
    }

    public void reset() {
        running = false;
        secondsLeft = startSeconds;
    }

    // One second passes. Returns true only on the tick that reaches 0, so the alarm rings once.
    public boolean tick() {
        if (!running) {
            return false;
        }
        secondsLeft--;
        if (secondsLeft == 0) {
            running = false;
            return true;
        }
        return false;
    }

    public boolean isRunning() {
        return running;
    }

    public boolean isComplete() {
        return secondsLeft == 0;
    }

    public int getSecondsLeft() {
        return secondsLeft;
    }

    // 1500 → "25:00", 65 → "01:05", 4500 → "75:00": minutes keep counting past 59.
    public static String format(int seconds) {
        return String.format("%02d:%02d", seconds / 60, seconds % 60);
    }

    public static void main(String[] args) {
        CountdownTimer timer = new CountdownTimer(3);
        timer.start();
        for (int i = 0; i < 5; i++) {
            boolean rang = timer.tick();
            System.out.println(format(timer.getSecondsLeft()) + (rang ? "  ring!" : ""));
        }
    }
}
