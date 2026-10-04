public class Simulator {

    static final int[] BASE = {15, 100, 600, 3000};
    static final int[] RATE = {1, 5, 24, 100};

    static long cost(int i, int owned) {
        return Math.round(BASE[i] * Math.pow(1.15, owned));
    }

    // Total chapatis earned over `seconds` seconds.
    public static long simulate(boolean smart, int seconds, int clicksPerSecond) {
        // TODO
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("naive: " + simulate(false, 600, 5));
        System.out.println("smart: " + simulate(true, 600, 5));
    }
}
