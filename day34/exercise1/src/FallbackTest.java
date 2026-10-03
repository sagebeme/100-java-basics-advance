import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.function.Supplier;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FallbackTest {

    @Test
    void skipsSourcesThatThrowOrAreBlank() {
        List<Supplier<String>> sources = List.of(
                () -> { throw new RuntimeException("no Wi-Fi"); },
                () -> "  ",
                () -> null,
                () -> "from the cache");
        assertEquals("from the cache", Fallback.firstThatWorks(sources, "built-in"));
    }

    @Test
    void usesTheFirstGoodAnswerAndStopsThere() {
        int[] calls = {0};
        List<Supplier<String>> sources = List.of(() -> "live", () -> { calls[0]++; return "never needed"; });
        assertEquals("live", Fallback.firstThatWorks(sources, "built-in"));
        assertEquals(0, calls[0]);
    }

    @Test
    void fallsBackToTheLastResort() {
        assertEquals("built-in", Fallback.firstThatWorks(List.of(() -> { throw new IllegalStateException(); }), "built-in"));
        assertEquals("built-in", Fallback.firstThatWorks(List.of(), "built-in"));
    }
}
