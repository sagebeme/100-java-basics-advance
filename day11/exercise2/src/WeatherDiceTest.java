import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeatherDiceTest {

    @Test
    void staysBetweenMinAndMax() {
        Random rng = new Random(7);
        for (int i = 0; i < 1000; i++) {
            double value = WeatherDice.between(rng, 600, 2200);
            assertTrue(value >= 600 && value < 2200, value + " out of range");
        }
    }

    @Test
    void zeroPercentNeverHappensAndOneHundredAlwaysDoes() {
        Random rng = new Random(7);
        for (int i = 0; i < 1000; i++) {
            assertFalse(WeatherDice.chance(rng, 0));
            assertTrue(WeatherDice.chance(rng, 100));
        }
    }

    @Test
    void twentyFivePercentHappensAboutAQuarterOfTheTime() {
        Random rng = new Random(7);
        int hits = 0;
        for (int i = 0; i < 10000; i++) {
            if (WeatherDice.chance(rng, 25)) {
                hits++;
            }
        }
        assertTrue(hits > 2300 && hits < 2700, "expected about 2500, got " + hits);
    }
}
