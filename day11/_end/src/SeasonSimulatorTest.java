import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeasonSimulatorTest {

    @Test
    void rainfallStaysBetweenSixHundredAndTwentyTwoHundred() {
        Random rng = new Random(42);
        for (int i = 0; i < 1000; i++) {
            double mm = SeasonSimulator.randomRainfall(rng);
            assertTrue(mm >= 600 && mm < 2200, "rainfall " + mm + " out of range");
        }
    }

    @Test
    void droughtAndFloodBothHurtTheHarvest() {
        assertEquals(1.0, SeasonSimulator.kgPerTree(700), 0.0001);
        assertEquals(2.5, SeasonSimulator.kgPerTree(800), 0.0001);
        assertEquals(2.5, SeasonSimulator.kgPerTree(1800), 0.0001);
        assertEquals(1.8, SeasonSimulator.kgPerTree(1900), 0.0001);
    }

    @Test
    void harvestIsTreesTimesKgPerTree() {
        assertEquals(750, SeasonSimulator.harvestKg(300, 1250), 0.0001);
    }

    @Test
    void wetYearsDryMoreSlowly() {
        assertEquals(14, SeasonSimulator.dryingDays(1250));
        assertEquals(21, SeasonSimulator.dryingDays(2000));
    }

    @Test
    void splitsGreenBeansIntoGradesInOrder() {
        Map<String, Double> grades = SeasonSimulator.gradeSplit(125);
        assertEquals(List.of("AA", "AB", "PB", "C"), List.copyOf(grades.keySet()));
        assertEquals(37.5, grades.get("AA"), 0.0001);
        assertEquals(50.0, grades.get("AB"), 0.0001);
        assertEquals(12.5, grades.get("PB"), 0.0001);
        assertEquals(25.0, grades.get("C"), 0.0001);
    }

    @Test
    void paysTheRateMinusTheLevy() {
        assertEquals(60562.5, SeasonSimulator.payout(750), 0.0001);
    }

    @Test
    void reportsAGoodSeason() {
        assertEquals("Season report for Wanjiru\n"
                        + "Rainfall: 1250 mm (a good year)\n"
                        + "Harvest: 750.0 kg of cherry from 300 trees\n"
                        + "Drying: 14 days on the beds\n"
                        + "Green beans: 125.0 kg (AA 37.5, AB 50.0, PB 12.5, C 25.0)\n"
                        + "Payout: KES 60,562.50",
                SeasonSimulator.seasonReport("Wanjiru", 300, 1250));
    }

    @Test
    void namesADryYear() {
        assertTrue(SeasonSimulator.seasonReport("Otieno", 100, 700).contains("(a dry year)"));
        assertTrue(SeasonSimulator.seasonReport("Otieno", 100, 2000).contains("(a very wet year)"));
    }
}
