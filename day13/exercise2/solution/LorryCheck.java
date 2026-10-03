public class LorryCheck {

    public static double toTonnes(int kg) {
        return kg / 1000.0; // was kg / 1000: two ints, so the decimals were dropped
    }

    public static boolean isOverloaded(double grossKg, double limitKg) {
        return grossKg > limitKg; // was >=: exactly at the limit is allowed
    }

    public static void main(String[] args) {
        System.out.println(toTonnes(3920) + " tonnes");
        System.out.println("Overloaded: " + isOverloaded(10000, 10000));
    }
}
