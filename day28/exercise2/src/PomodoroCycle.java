public class PomodoroCycle {

    public enum Phase { WORK, SHORT_BREAK, LONG_BREAK }

    // TODO: private fields for the three lengths, the set size, the current phase and the sessions done

    public PomodoroCycle(int workMinutes, int shortBreakMinutes, int longBreakMinutes, int sessionsPerSet) {
        // TODO: refuse anything 0 or less. Every cycle starts on WORK with nothing done.
    }

    // The usual: 25 minutes' work, 5 off, and 15 off after every 4th session.
    public static PomodoroCycle classic() {
        // TODO
        return null;
    }

    public Phase phase() {
        // TODO
        return null;
    }

    public int minutes() {
        // TODO: how long the current phase lasts
        return 0;
    }

    // The current phase is over: move to the next one.
    public void advance() {
        // TODO: after work, count the session and pick the break; after any break, back to work
    }

    public int completedSessions() {
        // TODO
        return 0;
    }

    // "Work 2 of 4", "Short break", "Long break".
    public String label() {
        // TODO
        return "";
    }

    public int workMinutesDone() {
        // TODO
        return 0;
    }

    public static void main(String[] args) {
        PomodoroCycle cycle = classic();
        for (int i = 0; i < 9; i++) {
            System.out.println(cycle.label() + " (" + cycle.minutes() + " min)");
            cycle.advance();
        }
        System.out.println(cycle.workMinutesDone() + " minutes of revision done");
    }
}
