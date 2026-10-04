public class Simulator {

    static final int[] BASE = {15, 100, 600, 3000};
    static final int[] RATE = {1, 5, 24, 100};

    static long cost(int i, int owned) {
        return Math.round(BASE[i] * Math.pow(1.15, owned));
    }

    public static long simulate(boolean smart, int seconds, int clicksPerSecond) {
        int[] owned = new int[4];
        long wallet = 0, total = 0;
        for (int s = 0; s < seconds; s++) {
            long income = clicksPerSecond;
            for (int i = 0; i < 4; i++) income += (long) RATE[i] * owned[i];
            wallet += income;
            total += income;
            while (true) {
                int pick = -1;
                for (int i = 0; i < 4; i++) {
                    if (cost(i, owned[i]) > wallet) continue;
                    if (pick == -1) { pick = i; continue; }
                    double mine = smart ? (double) cost(i, owned[i]) / RATE[i] : cost(i, owned[i]);
                    double best = smart ? (double) cost(pick, owned[pick]) / RATE[pick] : cost(pick, owned[pick]);
                    if (mine < best) pick = i;
                }
                if (pick == -1) break;
                wallet -= cost(pick, owned[pick]);
                owned[pick]++;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("naive: " + simulate(false, 600, 5));
        System.out.println("smart: " + simulate(true, 600, 5));
    }
}
