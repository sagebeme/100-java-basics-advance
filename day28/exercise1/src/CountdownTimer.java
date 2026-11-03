public class CountdownTimer {

    // TODO: private fields: where it started, how many seconds are left, and whether it's running

    public CountdownTimer(int startSeconds) {
        // TODO: refuse 0 or less. A new timer is stopped.
    }

    public void start() {
        // TODO: a finished timer can't start again until it's reset
    }

    public void pause() {
        // TODO
    }

    public void reset() {
        // TODO: back to the start, stopped
    }

    // One second passes. Returns true only on the tick that reaches 0, so the alarm rings once.
    public boolean tick() {
        // TODO: nothing happens while stopped
        return false;
    }

    public boolean isRunning() {
        // TODO
        return false;
    }

    public boolean isComplete() {
        // TODO
        return false;
    }

    public int getSecondsLeft() {
        // TODO
        return -1;
    }

    // 1500 → "25:00", 65 → "01:05", 4500 → "75:00": minutes keep counting past 59.
    public static String format(int seconds) {
        // TODO
        return "";
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
