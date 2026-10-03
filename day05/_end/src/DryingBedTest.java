import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DryingBedTest {

    @Test
    void fiftyTwoToElevenAtThreeADayTakesFourteenDays() {
        assertEquals(14, DryingBed.daysToDry(52, 11, 3));
    }

    @Test
    void landingExactlyOnTheTargetCountsAsDry() {
        assertEquals(2, DryingBed.daysToDry(17, 11, 3));
    }

    @Test
    void alreadyDryBeansTakeNoDays() {
        assertEquals(0, DryingBed.daysToDry(10, 11, 3));
    }

    @Test
    void noLossMeansTheyNeverDry() {
        assertEquals(-1, DryingBed.daysToDry(52, 11, 0));
    }

    @Test
    void rainyDaysDontCountAndCloudyDaysCountHalf() {
        // 20 -> S 17 -> R 17 -> C 15.5 -> S 12.5 -> S 9.5
        assertEquals(5, DryingBed.daysToDryWithWeather(20, 11, "SRCSS"));
    }

    @Test
    void aShortForecastIsNotEnough() {
        assertEquals(-1, DryingBed.daysToDryWithWeather(52, 11, "SSS"));
    }

    @Test
    void oneHashForEveryFullFivePoints() {
        assertEquals("##########", DryingBed.moistureBar(52));
        assertEquals("##", DryingBed.moistureBar(11));
        assertEquals("", DryingBed.moistureBar(4.9));
    }
}
