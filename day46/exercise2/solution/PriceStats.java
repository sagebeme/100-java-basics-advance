import java.util.List;

public class PriceStats {

    public static double percentBelowHigh(int today, List<Integer> history) {
        int high = history.stream().mapToInt(Integer::intValue).max().orElse(today);
        if (today >= high) return 0.0;
        return Math.round((high - today) * 1000.0 / high) / 10.0;
    }

    public static boolean isLowestYet(int today, List<Integer> history) {
        return history.stream().allMatch(p -> today < p);
    }
}
