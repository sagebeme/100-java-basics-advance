public class RoastTimer {

    public static final int FIRST_CRACK_C = 196;

    public static int secondsToFirstCrack(int startTempC, double degreesPerMinute) {
        // TODO: 0 if already there, -1 if it never will be, otherwise seconds rounded up
        return 0;
    }

    public static String clock(int totalSeconds) {
        // TODO: "7:40"
        return "";
    }

    public static void main(String[] args) {
        int seconds = secondsToFirstCrack(150, 6);
        System.out.println("Listen for first crack at " + clock(seconds));
    }
}
