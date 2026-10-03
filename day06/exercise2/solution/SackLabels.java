public class SackLabels {

    public static final double FULL_SACK_KG = 60;

    public static String sackLabel(String grade) {
        return sackLabel(grade, FULL_SACK_KG);
    }

    public static String sackLabel(String grade, double kg) {
        return String.format("%s | %.1f kg", grade, kg);
    }

    public static String sackLabel(String grade, double kg, String lot) {
        return "Lot " + lot + " | " + sackLabel(grade, kg);
    }

    public static void main(String[] args) {
        System.out.println(sackLabel("AA"));
        System.out.println(sackLabel("AB", 32.5));
        System.out.println(sackLabel("PB", 18, "0412"));
    }
}
