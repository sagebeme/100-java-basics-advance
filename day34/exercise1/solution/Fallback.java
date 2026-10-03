import java.util.List;
import java.util.function.Supplier;

public class Fallback {

    public static String firstThatWorks(List<Supplier<String>> sources, String lastResort) {
        for (Supplier<String> source : sources) {
            try {
                String answer = source.get();
                if (answer != null && !answer.isBlank()) {
                    return answer;
                }
            } catch (RuntimeException e) {
                // this source failed: try the next one
            }
        }
        return lastResort;
    }

    public static void main(String[] args) {
        System.out.println(firstThatWorks(List.of(
                () -> { throw new RuntimeException("no Wi-Fi"); },
                () -> "from the cache"), "built-in questions"));
    }
}
