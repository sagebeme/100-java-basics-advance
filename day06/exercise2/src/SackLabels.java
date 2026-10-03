public class SackLabels {

    public static final double FULL_SACK_KG = 60;

    public static String sackLabel(String grade) {
        // TODO: a full sack
        return "";
    }

    public static String sackLabel(String grade, double kg) {
        // TODO: "AB | 32.5 kg"
        return "";
    }

    public static String sackLabel(String grade, double kg, String lot) {
        // TODO: "Lot 0412 | PB | 18.0 kg"
        return "";
    }

    public static void main(String[] args) {
        System.out.println(sackLabel("AA"));
        System.out.println(sackLabel("AB", 32.5));
        System.out.println(sackLabel("PB", 18, "0412"));
    }
}
