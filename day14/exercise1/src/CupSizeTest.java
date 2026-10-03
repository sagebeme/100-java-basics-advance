import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CupSizeTest {

    @Test
    void addsUpTheParts() {
        assertEquals(240, CupSize.totalMl(30, 200, 10));
        assertEquals(0, CupSize.totalMl());
    }

    @Test
    void namesEachSizeAtItsEdges() {
        assertEquals("empty", CupSize.cupSize(0));
        assertEquals("small", CupSize.cupSize(1));
        assertEquals("small", CupSize.cupSize(240));
        assertEquals("medium", CupSize.cupSize(241));
        assertEquals("medium", CupSize.cupSize(360));
        assertEquals("large", CupSize.cupSize(361));
    }
}
