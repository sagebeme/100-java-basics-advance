import org.junit.jupiter.api.Test;
import java.lang.reflect.Modifier;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeighingScaleTest {

    @Test
    void anHonestCalibrationIsApplied() {
        WeighingScale scale = new WeighingScale();
        assertTrue(scale.calibrate(-0.2));
        assertEquals(23.5, scale.weigh(23.7), 0.0001);
    }

    @Test
    void aBigCalibrationIsRefusedAndChangesNothing() {
        WeighingScale scale = new WeighingScale();
        scale.calibrate(-0.2);
        assertFalse(scale.calibrate(5));
        assertFalse(scale.calibrate(-0.6));
        assertEquals(-0.2, scale.getOffset(), 0.0001);
    }

    @Test
    void theEdgesOfTheRangeAreAllowed() {
        WeighingScale scale = new WeighingScale();
        assertTrue(scale.calibrate(0.5));
        assertTrue(scale.calibrate(-0.5));
    }

    @Test
    void anEmptyScaleNeverShowsANegativeWeight() {
        WeighingScale scale = new WeighingScale();
        scale.calibrate(-0.2);
        assertEquals(0, scale.weigh(0), 0.0001);
    }

    @Test
    void theOffsetIsPrivate() throws Exception {
        assertTrue(Modifier.isPrivate(WeighingScale.class.getDeclaredField("offset").getModifiers()));
    }
}
