import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SackLabelsTest {

    @Test
    void justAGradeMeansAFullSack() {
        assertEquals("AA | 60.0 kg", SackLabels.sackLabel("AA"));
    }

    @Test
    void aPartSackShowsItsWeight() {
        assertEquals("AB | 32.5 kg", SackLabels.sackLabel("AB", 32.5));
    }

    @Test
    void aLotNumberGoesInFront() {
        assertEquals("Lot 0412 | PB | 18.0 kg", SackLabels.sackLabel("PB", 18, "0412"));
    }
}
