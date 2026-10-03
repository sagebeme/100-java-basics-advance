import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimetableStyleTest {

    private String css;

    @BeforeEach
    void readCss() throws IOException {
        try (InputStream in = TimetableStyleTest.class.getResourceAsStream("/timetable.css")) {
            css = new String(in.readAllBytes(), StandardCharsets.UTF_8).replaceAll("(?s)/\\*.*?\\*/", "");
        }
    }

    private String rule(String selectorRegex) {
        Matcher m = Pattern.compile("(?m)^\\s*" + selectorRegex + "\\s*\\{([^}]*)\\}").matcher(css);
        return m.find() ? m.group(1) : null;
    }

    @Test
    void theWeekIsSevenEqualColumns() {
        String week = rule("\\.week");
        assertNotNull(week, "add a .week rule");
        assertTrue(week.matches("(?s).*display:\\s*grid.*"));
        assertTrue(week.matches("(?s).*grid-template-columns:\\s*repeat\\(7,\\s*1fr\\).*"));
        assertTrue(week.contains("gap"));
    }

    @Test
    void aLongSessionSpansTwoDays() {
        String longOne = rule("\\.session\\.long");
        assertNotNull(longOne, "add a .session.long rule");
        assertTrue(longOne.matches("(?s).*grid-column:\\s*span 2.*"));
    }

    @Test
    void phonesGetOneColumn() {
        Matcher m = Pattern.compile("(?s)@media\\s*\\(max-width:[^)]*\\)\\s*\\{(.*)\\}").matcher(css);
        assertTrue(m.find(), "add a @media (max-width: ...) block");
        assertTrue(m.group(1).matches("(?s).*\\.week\\s*\\{[^}]*grid-template-columns:\\s*1fr.*"));
    }
}
