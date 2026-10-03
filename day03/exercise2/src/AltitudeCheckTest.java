import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AltitudeCheckTest {

    @Test
    void belowFourteenHundredIsTooLow() {
        assertEquals("too low", AltitudeCheck.altitudeRating(1399));
    }

    @Test
    void fourteenHundredStartsGood() {
        assertEquals("good", AltitudeCheck.altitudeRating(1400));
        assertEquals("good", AltitudeCheck.altitudeRating(1699));
    }

    @Test
    void seventeenHundredToTwentyOneHundredIsExcellent() {
        assertEquals("excellent", AltitudeCheck.altitudeRating(1700));
        assertEquals("excellent", AltitudeCheck.altitudeRating(2100));
    }

    @Test
    void aboveTwentyOneHundredIsTooHigh() {
        assertEquals("too high", AltitudeCheck.altitudeRating(2101));
    }
}
