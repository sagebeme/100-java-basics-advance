import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MilkReport {

    // Litres per farmer, farmers in alphabetical order.
    public static Map<String, Integer> totalByFarmer(List<Delivery> deliveries) {
        // TODO: a TreeMap keeps its keys in order
        return new TreeMap<>();
    }

    // The n farmers who brought the most, as "Njoroge 43", most first. A tie goes alphabetically.
    public static List<String> topFarmers(List<Delivery> deliveries, int n) {
        // TODO
        return new ArrayList<>();
    }

    // Litres per delivery. No deliveries: 0.
    public static double averageLitres(List<Delivery> deliveries) {
        // TODO
        return -1;
    }

    // What percent of all the litres were this grade, rounded. No milk at all: 0.
    public static int gradeShare(List<Delivery> deliveries, String grade) {
        // TODO
        return -1;
    }

    // Grade C milk is turned away. Which deliveries, in the order they came in?
    public static List<Delivery> rejected(List<Delivery> deliveries) {
        // TODO
        return new ArrayList<>();
    }
}
