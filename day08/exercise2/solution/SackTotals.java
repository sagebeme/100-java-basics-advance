public class SackTotals {

    public static double totalKg(double... sacks) {
        double total = 0;
        for (double kg : sacks) {
            total += kg;
        }
        return total;
    }

    public static double averageKg(double... sacks) {
        if (sacks.length == 0) {
            return 0;
        }
        return totalKg(sacks) / sacks.length;
    }

    public static void main(String[] args) {
        System.out.println(totalKg(12.5, 8, 20));
        System.out.println(averageKg(10, 20, 30));
    }
}
