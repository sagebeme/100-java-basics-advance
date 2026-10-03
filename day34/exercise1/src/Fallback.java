import java.util.List;
import java.util.function.Supplier;

public class Fallback {

    public static String firstThatWorks(List<Supplier<String>> sources, String lastResort) {
        // TODO: try each source; skip ones that throw or give null/blank
        return lastResort;
    }

    public static void main(String[] args) {
        System.out.println(firstThatWorks(List.of(
                () -> { throw new RuntimeException("no Wi-Fi"); },
                () -> "from the cache"), "built-in questions"));
    }
}
