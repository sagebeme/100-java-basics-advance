public class PomodoroCycle {

    public enum Phase { WORK, SHORT_BREAK, LONG_BREAK }

    private Phase phase = Phase.WORK;
    private int completedWorkSessions = 0;
    private final int sessionsBeforeLongBreak;

    public PomodoroCycle(int sessionsBeforeLongBreak) {
        this.sessionsBeforeLongBreak = sessionsBeforeLongBreak;
    }

    public Phase getPhase() {
        return phase;
    }

    public int getCompletedWorkSessions() {
        return completedWorkSessions;
    }

    /**
     * Call when the current phase's timer finishes, to move on to the next one.
     */
    public void advance() {
        if (phase == Phase.WORK) {
            completedWorkSessions++;
            phase = (completedWorkSessions % sessionsBeforeLongBreak == 0) ? Phase.LONG_BREAK : Phase.SHORT_BREAK;
        } else {
            phase = Phase.WORK;
        }
    }

    public static void main(String[] args) {
        PomodoroCycle cycle = new PomodoroCycle(4);
        for (int i = 0; i < 8; i++) {
            System.out.println(cycle.getPhase());
            cycle.advance();
        }
    }
}
