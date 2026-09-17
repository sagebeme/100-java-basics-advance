import java.util.LinkedHashMap;
import java.util.Map;

public class Exercise1 {

    public static Map<String, Integer> celebrityFollowers() {
        Map<String, Integer> data = new LinkedHashMap<>();
        data.put("Instagram", 500);
        data.put("Cristiano Ronaldo", 400);
        data.put("Ariana Grande", 300);
        return data;
    }

    public static Map<String, Integer> countryPopulations() {
        Map<String, Integer> data = new LinkedHashMap<>();
        data.put("Kenya", 55);
        data.put("Nigeria", 220);
        data.put("Tanzania", 65);
        return data;
    }

    public static void main(String[] args) {
        System.out.println("Followers (millions): " + celebrityFollowers());
        System.out.println("Population (millions): " + countryPopulations());
    }
}
