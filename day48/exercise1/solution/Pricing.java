public class Pricing {

    public static long costAfter(int base, int owned) {
        return Math.round(base * Math.pow(1.15, owned));
    }

    public static long totalCost(int base, int owned, int more) {
        long total = 0;
        for (int i = 0; i < more; i++) total += costAfter(base, owned + i);
        return total;
    }
}
