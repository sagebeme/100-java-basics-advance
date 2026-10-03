import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FarmerTotals {

    public static Map<String, Double> totalsByFarmer(List<String> farmers, List<Double> kgs) {
        Map<String, Double> totals = new LinkedHashMap<>();
        // TODO: add each delivery to that farmer's running total
        return totals;
    }

    public static void main(String[] args) {
        System.out.println(totalsByFarmer(List.of("Wanjiru", "Otieno", "Wanjiru"), List.of(23.5, 31.0, 10.0)));
    }
}
