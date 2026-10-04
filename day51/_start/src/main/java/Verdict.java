import java.util.List;

// Compares measured speeds with the plan you pay for.
public class Verdict {

    // The middle value, or the average of the two middle values.
    public static double median(List<Double> values) {
        // TODO: sort a copy; the middle one, or the average of the two middle ones; throw for an empty list
        return 0;
    }

    // Complain when the median download is under `share` (for example 0.7 = 70%) of the plan.
    public static boolean shouldComplain(List<SpeedResult> runs, double planMbps, double share) {
        // TODO
        return false;
    }

    public static String complaint(List<SpeedResult> runs, double planMbps) {
        double median = median(runs.stream().map(SpeedResult::downMbps).toList());
        StringBuilder text = new StringBuilder(String.format(
                "My plan is %.0f Mbps, but I measured a median download speed of %.1f Mbps (%.0f%% of the plan) over %d tests:%n",
                planMbps, median, median * 100 / planMbps, runs.size()));
        for (int i = 0; i < runs.size(); i++) {
            SpeedResult r = runs.get(i);
            text.append(String.format("  test %d: %.1f Mbps down, %.1f Mbps up, %d ms ping%n", i + 1, r.downMbps(), r.upMbps(), r.pingMs()));
        }
        return text.append("Please check my line.").toString();
    }
}
