import java.util.ArrayList;
import java.util.List;

public class BiggestDelivery {

    public static String biggestDelivery(ArrayList<String> farmers, ArrayList<Double> kgs) {
        // TODO: "No deliveries yet" for empty lists
        // TODO: remember the index of the biggest delivery so far
        return "";
    }

    public static void main(String[] args) {
        ArrayList<String> farmers = new ArrayList<>(List.of("Wanjiru", "Otieno", "Achieng"));
        ArrayList<Double> kgs = new ArrayList<>(List.of(23.5, 31.0, 12.0));
        System.out.println(biggestDelivery(farmers, kgs));
    }
}
