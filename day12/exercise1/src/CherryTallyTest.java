import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CherryTallyTest {

    @Test
    void countsEveryRedCherry() {
        assertEquals(3, CherryTally.countRed(new String[]{"red", "green", "red", "red"}));
        assertEquals(0, CherryTally.countRed(new String[]{"green"}));
    }

    @Test
    void theCountDoesntDependOnTheLastCherry() {
        assertEquals(2, CherryTally.countRed(new String[]{"red", "red", "green"}));
    }

    @Test
    void addingUpdatesTheFieldNotTheParameter() {
        CherryTally tally = new CherryTally();
        tally.add(5);
        tally.add(3);
        assertEquals(8, tally.getRipe());
    }
}
