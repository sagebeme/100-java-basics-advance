public class LorryCheck {

    public static double toTonnes(int kg) {
        return kg / 1000;
    }

    public static boolean isOverloaded(double grossKg, double limitKg) {
        return grossKg >= limitKg;
    }

    public static void main(String[] args) {
        System.out.println(toTonnes(3920) + " tonnes");
        System.out.println("Overloaded: " + isOverloaded(10000, 10000));
    }
}
