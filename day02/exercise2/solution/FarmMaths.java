public class FarmMaths {

    public static double averageKgPerTree(int totalKg, int trees) {
        return (double) totalKg / trees;
    }

    public static int sacksNeeded(int kg, int sackSize) {
        return (int) Math.ceil((double) kg / sackSize);
    }

    public static int percentRipe(int ripe, int total) {
        return ripe * 100 / total; // multiply first, or 45 / 60 is already 0
    }

    public static void main(String[] args) {
        System.out.println(averageKgPerTree(25, 10));
        System.out.println(sacksNeeded(120, 50));
        System.out.println(percentRipe(45, 60));
    }
}
