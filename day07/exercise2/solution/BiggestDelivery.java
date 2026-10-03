import java.util.ArrayList;
import java.util.List;

public class BiggestDelivery {

    public static String biggestDelivery(ArrayList<String> farmers, ArrayList<Double> kgs) {
        if (kgs.isEmpty()) {
            return "No deliveries yet";
        }
        int biggest = 0;
        for (int i = 1; i < kgs.size(); i++) {
            if (kgs.get(i) > kgs.get(biggest)) {
                biggest = i;
            }
        }
        return farmers.get(biggest) + " with " + kgs.get(biggest) + " kg";
    }

    public static void main(String[] args) {
        ArrayList<String> farmers = new ArrayList<>(List.of("Wanjiru", "Otieno", "Achieng"));
        ArrayList<Double> kgs = new ArrayList<>(List.of(23.5, 31.0, 12.0));
        System.out.println(biggestDelivery(farmers, kgs));
    }
}
