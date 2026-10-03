import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RouteStyleTest {

    private String css;

    @BeforeEach
    void readCss() throws IOException {
        try (InputStream in = RouteStyleTest.class.getResourceAsStream("/route.css")) {
            css = new String(in.readAllBytes(), StandardCharsets.UTF_8).replaceAll("(?s)/\\*.*?\\*/", "");
        }
    }

    private String rule(String selectorRegex) {
        Matcher m = Pattern.compile("(?m)^\\s*" + selectorRegex + "\\s*\\{([^}]*)\\}").matcher(css);
        return m.find() ? m.group(1) : null;
    }

    private static boolean has(String rule, String regex) {
        return Pattern.compile(regex).matcher(rule).find();
    }

    @Test
    void theHeaderIsAFlexRowPushedApart() {
        String header = rule("\\.card header");
        assertNotNull(header, "add a .card header rule");
        assertTrue(has(header, "display:\\s*flex"));
        assertTrue(has(header, "justify-content:\\s*space-between"));
        assertTrue(has(header, "align-items:\\s*center"));
    }

    @Test
    void theStopsWrapWithAGap() {
        String stops = rule("\\.stops");
        assertNotNull(stops, "add a .stops rule");
        assertTrue(has(stops, "display:\\s*flex"));
        assertTrue(has(stops, "flex-wrap:\\s*wrap"));
        assertTrue(has(stops, "gap:"));
    }

    @Test
    void theFooterIsAFlexRowToo() {
        String footer = rule("\\.card footer");
        assertNotNull(footer, "add a .card footer rule");
        assertTrue(has(footer, "display:\\s*flex"));
        assertTrue(has(footer, "justify-content:\\s*space-between"));
    }
}
