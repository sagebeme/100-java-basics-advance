import java.util.ArrayList;
import java.util.List;

public class Exercise1 {

    public static String removeLast(List<String> items) {
        return items.remove(items.size() - 1);
    }

    public static List<String> firstN(List<String> items, int n) {
        return new ArrayList<>(items.subList(0, Math.min(n, items.size())));
    }

    public static void main(String[] args) {
        List<String> items = new ArrayList<>(List.of("a", "b", "c", "d"));
        System.out.println("Removed: " + removeLast(items));
        System.out.println("Remaining: " + items);
        System.out.println("First 2: " + firstN(items, 2));
    }
}
