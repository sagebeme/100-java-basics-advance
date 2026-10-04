public class Units {

    public static double mbps(long bytes, long millis) {
        return bytes * 8.0 / 1_000_000 / (millis / 1000.0);
    }

    public static double secondsToDownload(double megabytes, double mbps) {
        return megabytes * 8 / mbps;
    }

    public static String describe(double mbps) {
        if (mbps < 5) return "slow";
        if (mbps < 25) return "fine for HD video";
        return "fast";
    }
}
