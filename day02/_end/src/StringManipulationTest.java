import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringManipulationTest {

    @Test
    void returnsTextBeforeTheFirstSpace() {
        assertEquals("Hello", StringManipulation.firstWord("Hello world"));
    }

    @Test
    void returnsTheWholeStringWhenThereIsNoSpace() {
        assertEquals("Hello", StringManipulation.firstWord("Hello"));
    }
}
