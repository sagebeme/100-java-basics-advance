public class RoastTimer {

    public static final int FIRST_CRACK_C = 196;

    public static int secondsToFirstCrack(int startTempC, double degreesPerMinute) {
        if (startTempC >= FIRST_CRACK_C) {
            return 0;
        }
        if (degreesPerMinute <= 0) {
            return -1;
        }
        double minutes = (FIRST_CRACK_C - startTempC) / degreesPerMinute;
        return (int) Math.ceil(minutes * 60);
    }

    public static String clock(int totalSeconds) {
        return String.format("%d:%02d", totalSeconds / 60, totalSeconds % 60);
    }

    public static void main(String[] args) {
        int seconds = secondsToFirstCrack(150, 6);
        System.out.println("Listen for first crack at " + clock(seconds));
    }
}
