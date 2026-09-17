import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TipCalculatorTest {

    @Test
    void splitsBillPlusTipEvenly() {
        // $150 bill, 12% tip, 2 people -> (150 + 18) / 2 = 84.0
        assertEquals(84.0, TipCalculator.totalPerPerson(150.0, 12, 2), 0.001);
    }

    @Test
    void handlesASingleDiner() {
        assertEquals(110.0, TipCalculator.totalPerPerson(100.0, 10, 1), 0.001);
    }
}
