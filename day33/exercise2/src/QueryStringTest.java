import org.junit.jupiter.api.Test;
import java.util.LinkedHashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueryStringTest {

    @Test
    void encodesSpacesAmpersandsAndSlashes() {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("q", "nyama choma & chips");
        params.put("timezone", "Africa/Nairobi");
        assertEquals("https://example.com/search?q=nyama+choma+%26+chips&timezone=Africa%2FNairobi",
                QueryString.withQuery("https://example.com/search", params));
    }

    @Test
    void keepsTheMapsOrder() {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("b", "2");
        params.put("a", "1");
        assertEquals("https://x.test/?b=2&a=1", QueryString.withQuery("https://x.test/", params));
    }

    @Test
    void noParametersMeansNoQuestionMark() {
        assertEquals("https://x.test/", QueryString.withQuery("https://x.test/", Map.of()));
    }
}
