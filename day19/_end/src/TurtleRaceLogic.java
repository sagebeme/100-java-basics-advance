public class TurtleRaceLogic {

    public static final int FINISH_LINE = 750;

    public static double advance(double x, int step) {
        return x + step;
    }

    public static boolean hasFinished(double x) {
        return x >= FINISH_LINE;
    }
}
