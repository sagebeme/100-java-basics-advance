public class CupSize {

    public static int totalMl(int... parts) {
        int total = 0;
        for (int ml : parts) {
            total += ml;
        }
        return total;
    }

    public static String cupSize(int ml) {
        if (ml <= 0) return "empty";
        if (ml <= 240) return "small";
        if (ml <= 360) return "medium";
        return "large";
    }

    public static void main(String[] args) {
        int latte = totalMl(30, 300, 20);
        System.out.println("A " + latte + " ml latte is a " + cupSize(latte) + ".");
    }
}
