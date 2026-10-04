import java.util.List;

public class Stats {

    public static double median(List<Double> values) {
        if (values.isEmpty()) throw new IllegalArgumentException("No values");
        List<Double> s = values.stream().sorted().toList();
        int n = s.size();
        return n % 2 == 1 ? s.get(n / 2) : (s.get(n / 2 - 1) + s.get(n / 2)) / 2;
    }

    public static double trimmedMean(List<Double> values) {
        if (values.isEmpty()) throw new IllegalArgumentException("No values");
        List<Double> s = values.stream().sorted().toList();
        List<Double> kept = s.size() >= 3 ? s.subList(1, s.size() - 1) : s;
        return kept.stream().mapToDouble(Double::doubleValue).average().orElseThrow();
    }
}
