import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringManipulationTest {

    @Test
    void findsTheFirstWordOfASentence() {
        assertEquals("Hello", StringManipulation.firstWord("Hello there"));
    }

    @Test
    void returnsTheWholeStringWhenThereIsOnlyOneWord() {
        assertEquals("Hello", StringManipulation.firstWord("Hello"));
    }
}
