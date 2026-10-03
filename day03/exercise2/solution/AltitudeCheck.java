public class AltitudeCheck {

    public static String altitudeRating(int metres) {
        if (metres < 1400) {
            return "too low";
        } else if (metres < 1700) {
            return "good";
        } else if (metres <= 2100) {
            return "excellent";
        } else {
            return "too high";
        }
    }

    public static void main(String[] args) {
        System.out.println(altitudeRating(1750));
    }
}
