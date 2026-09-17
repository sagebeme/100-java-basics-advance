import java.util.HashMap;
import java.util.Map;

public class Exercise1 {

    public static Map<String, Integer> countOccurrences(String[] words) {
        Map<String, Integer> counts = new HashMap<>();
        for (String word : words) {
            counts.merge(word, 1, Integer::sum);
        }
        return counts;
    }

    public static void main(String[] args) {
        String[] words = {"chai", "mandazi", "chai", "chai", "mandazi"};
        System.out.println(countOccurrences(words));
    }
}
