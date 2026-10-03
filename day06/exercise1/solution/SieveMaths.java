public class SieveMaths {

    static final double MM_PER_INCH = 25.4;

    public static double screenToMm(int screen) {
        return screen * MM_PER_INCH / 64;
    }

    public static int screenFromMm(double mm) {
        return (int) (mm * 64 / MM_PER_INCH + 1e-9); // the tiny nudge stops 14.9999 becoming 14
    }

    public static void main(String[] args) {
        System.out.println("Screen 17 is " + screenToMm(17) + " mm");
        System.out.println("6.8 mm is screen " + screenFromMm(6.8));
    }
}
