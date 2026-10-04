import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TemplateTest {

    @Test
    void fillsEveryPlaceholder() {
        assertEquals("Dear Mfuko, I'd like the Java Developer role.",
                Template.fill("Dear {{company}}, I'd like the {{title}} role.", Map.of("company", "Mfuko", "title", "Java Developer")));
    }

    @Test
    void namesEveryMissingValueOnce() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> Template.fill("Hi {{name}} from {{town}}, {{name}}!", Map.of()));
        assertEquals("Missing values for: name, town", e.getMessage());
    }

    @Test
    void valuesWithDollarSignsAreSafe() {
        assertEquals("Salary: $500", Template.fill("Salary: {{pay}}", Map.of("pay", "$500")));
    }
}
