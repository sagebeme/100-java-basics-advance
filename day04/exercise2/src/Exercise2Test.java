import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Exercise2Test {

    @Test
    void removesANameByItsPosition() {
        ArrayList<String> names = new ArrayList<>(Arrays.asList("Amina", "Kip", "Zawadi"));
        Exercise2.removeByIndex(names, 1);
        assertEquals(Arrays.asList("Amina", "Zawadi"), names);
    }

    @Test
    void findsANameThatIsInTheList() {
        ArrayList<String> names = new ArrayList<>(Arrays.asList("Amina", "Kip"));
        assertTrue(Exercise2.search(names, "Kip"));
    }

    @Test
    void doesNotFindANameThatIsMissing() {
        ArrayList<String> names = new ArrayList<>(Arrays.asList("Amina", "Kip"));
        assertFalse(Exercise2.search(names, "Zawadi"));
    }
}
