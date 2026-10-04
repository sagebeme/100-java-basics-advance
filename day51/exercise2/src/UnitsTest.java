import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitsTest {

    @Test
    void bytesAndTimeToMegabits() {
        assertEquals(20.0, Units.mbps(2_500_000, 1000), 1e-9);
        assertEquals(8.0, Units.mbps(500_000, 500), 1e-9);
    }

    @Test
    void aHundredMegabytesAtTwentyMegabits() {
        assertEquals(40.0, Units.secondsToDownload(100, 20), 1e-9);
    }

    @Test
    void describesASpeed() {
        assertEquals("slow", Units.describe(4.9));
        assertEquals("fine for HD video", Units.describe(5));
        assertEquals("fast", Units.describe(25));
    }
}
