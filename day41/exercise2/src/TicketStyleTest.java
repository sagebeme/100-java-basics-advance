import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TicketStyleTest {

    private String css;

    @BeforeEach
    void readCss() throws IOException {
        try (InputStream in = TicketStyleTest.class.getResourceAsStream("/ticket.css")) {
            css = new String(in.readAllBytes(), StandardCharsets.UTF_8).replaceAll("/\\*.*?\\*/", ""); // ignore comments
        }
    }

    // The declarations inside the first rule whose selector matches, e.g. rule("\\.ticket")
    private String rule(String selectorRegex) {
        Matcher m = Pattern.compile("(?m)^\\s*" + selectorRegex + "\\s*\\{([^}]*)\\}").matcher(css);
        return m.find() ? m.group(1) : null;
    }

    @Test
    void namesAtLeastThreeColoursInRoot() {
        String root = rule(":root");
        assertNotNull(root, "add a :root rule");
        assertTrue(Pattern.compile("--[\\w-]+\\s*:").matcher(root).results().count() >= 3, "define at least three --variables");
        assertTrue(css.contains("var(--"), "use your variables with var(--name)");
    }

    @Test
    void theTicketHasRoundedCornersAndAShadow() {
        String ticket = rule("\\.ticket");
        assertNotNull(ticket, "add a .ticket rule");
        assertTrue(ticket.contains("border-radius"));
        assertTrue(ticket.contains("box-shadow"));
    }

    @Test
    void choosesAFontWithAFallback() {
        assertTrue(Pattern.compile("font-family\\s*:[^;]*,").matcher(css).find(), "list a fallback font after a comma");
    }

    @Test
    void theStampIsTurned() {
        String stamp = rule("\\.stamp");
        assertNotNull(stamp, "add a .stamp rule");
        assertTrue(stamp.contains("rotate("));
    }

    @Test
    void somethingRespondsToHover() {
        assertTrue(css.contains(":hover"));
    }
}
