import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LorryCheckTest {

    @Test
    void tonnesKeepTheirDecimals() {
        assertEquals(3.92, LorryCheck.toTonnes(3920), 0.0001);
        assertEquals(0.5, LorryCheck.toTonnes(500), 0.0001);
    }

    @Test
    void exactlyAtTheLimitIsAllowed() {
        assertFalse(LorryCheck.isOverloaded(10000, 10000));
    }

    @Test
    void overTheLimitIsOverloaded() {
        assertTrue(LorryCheck.isOverloaded(10000.5, 10000));
        assertFalse(LorryCheck.isOverloaded(9999, 10000));
    }
}
