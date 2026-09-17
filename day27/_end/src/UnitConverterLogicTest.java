import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitConverterLogicTest {

    @Test
    void convertsMilesToKilometers() {
        assertEquals(16.09, UnitConverterLogic.milesToKilometers(10), 0.001);
    }

    @Test
    void zeroMilesIsZeroKilometers() {
        assertEquals(0.0, UnitConverterLogic.milesToKilometers(0), 0.001);
    }
}
