import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuChoiceTest {

    @Test
    void acceptsAChoiceOnTheMenu() {
        assertEquals(2, MenuChoice.parseChoice("2", 4));
        assertEquals(4, MenuChoice.parseChoice(" 4 ", 4));
    }

    @Test
    void refusesNumbersOffTheMenu() {
        assertEquals(-1, MenuChoice.parseChoice("5", 4));
        assertEquals(-1, MenuChoice.parseChoice("0", 4));
    }

    @Test
    void refusesWordsAndBlanksWithoutCrashing() {
        assertEquals(-1, MenuChoice.parseChoice("two", 4));
        assertEquals(-1, MenuChoice.parseChoice("", 4));
        assertEquals(-1, MenuChoice.parseChoice("-1", 4));
        assertEquals(-1, MenuChoice.parseChoice("2.5", 4));
    }
}
