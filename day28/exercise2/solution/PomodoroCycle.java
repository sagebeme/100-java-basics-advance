public class PomodoroCycle {

    public enum Phase { WORK, SHORT_BREAK, LONG_BREAK }

    private final int workMinutes;
    private final int shortBreakMinutes;
    private final int longBreakMinutes;
    private final int sessionsPerSet;

    private Phase phase = Phase.WORK;
    private int completedSessions;

    public PomodoroCycle(int workMinutes, int shortBreakMinutes, int longBreakMinutes, int sessionsPerSet) {
        if (workMinutes <= 0 || shortBreakMinutes <= 0 || longBreakMinutes <= 0 || sessionsPerSet <= 0) {
            throw new IllegalArgumentException("Every length and the set size must be above 0");
        }
        this.workMinutes = workMinutes;
        this.shortBreakMinutes = shortBreakMinutes;
        this.longBreakMinutes = longBreakMinutes;
        this.sessionsPerSet = sessionsPerSet;
    }

    // The usual: 25 minutes' work, 5 off, and 15 off after every 4th session.
    public static PomodoroCycle classic() {
        return new PomodoroCycle(25, 5, 15, 4);
    }

    public Phase phase() {
        return phase;
    }

    public int minutes() {
        switch (phase) {
            case WORK:
                return workMinutes;
            case SHORT_BREAK:
                return shortBreakMinutes;
            default:
                return longBreakMinutes;
        }
    }

    // The current phase is over: move to the next one.
    public void advance() {
        if (phase == Phase.WORK) {
            completedSessions++;
            phase = completedSessions % sessionsPerSet == 0 ? Phase.LONG_BREAK : Phase.SHORT_BREAK;
        } else {
            phase = Phase.WORK;
        }
    }

    public int completedSessions() {
        return completedSessions;
    }

    // "Work 2 of 4", "Short break", "Long break".
    public String label() {
        switch (phase) {
            case WORK:
                return "Work " + (completedSessions % sessionsPerSet + 1) + " of " + sessionsPerSet;
            case SHORT_BREAK:
                return "Short break";
            default:
                return "Long break";
        }
    }

    public int workMinutesDone() {
        return completedSessions * workMinutes;
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
