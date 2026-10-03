public class ScaleReadings {

    public static double parseReading(String reading) {
        return Double.parseDouble(reading.trim());
    }

    public static int wholeKilos(double kg) {
        return (int) kg; // drops the decimals: 23.9 becomes 23
    }

    public static double kgFromSacks(int sacks, int kgPerSack) {
        return sacks * kgPerSack; // the int result widens to a double on its own
    }

    public static String asText(int kg) {
        return String.valueOf(kg);
    }

    public static void main(String[] args) {
        System.out.println(parseReading(" 23.5 "));
        System.out.println(wholeKilos(23.9));
        System.out.println(kgFromSacks(3, 50));
        System.out.println(asText(42));
    }
}
