import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MilkReport {

    // Litres per farmer, farmers in alphabetical order.
    public static Map<String, Integer> totalByFarmer(List<Delivery> deliveries) {
        Map<String, Integer> totals = new TreeMap<>();
        for (Delivery d : deliveries) {
            totals.put(d.getFarmer(), totals.getOrDefault(d.getFarmer(), 0) + d.getLitres());
        }
        return totals;
    }

    // The n farmers who brought the most, as "Njoroge 43", most first. A tie goes alphabetically.
    public static List<String> topFarmers(List<Delivery> deliveries, int n) {
        List<Map.Entry<String, Integer>> totals = new ArrayList<>(totalByFarmer(deliveries).entrySet());
        totals.sort((a, b) -> {
            int byLitres = Integer.compare(b.getValue(), a.getValue());
            return byLitres != 0 ? byLitres : a.getKey().compareTo(b.getKey());
        });
        List<String> top = new ArrayList<>();
        for (int i = 0; i < Math.min(n, totals.size()); i++) {
            top.add(totals.get(i).getKey() + " " + totals.get(i).getValue());
        }
        return top;
    }

    // Litres per delivery. No deliveries: 0.
    public static double averageLitres(List<Delivery> deliveries) {
        if (deliveries.isEmpty()) {
            return 0;
        }
        int total = 0;
        for (Delivery d : deliveries) {
            total += d.getLitres();
        }
        return (double) total / deliveries.size();
    }

    // What percent of all the litres were this grade, rounded. No milk at all: 0.
    public static int gradeShare(List<Delivery> deliveries, String grade) {
        int all = 0;
        int ofGrade = 0;
        for (Delivery d : deliveries) {
            all += d.getLitres();
            if (d.getGrade().equals(grade)) {
                ofGrade += d.getLitres();
            }
        }
        return all == 0 ? 0 : (int) Math.round(100.0 * ofGrade / all);
    }

    // Grade C milk is turned away. Which deliveries, in the order they came in?
    public static List<Delivery> rejected(List<Delivery> deliveries) {
        List<Delivery> turnedAway = new ArrayList<>();
        for (Delivery d : deliveries) {
            if (d.getGrade().equals("C")) {
                turnedAway.add(d);
            }
        }
        return turnedAway;
    }
}
