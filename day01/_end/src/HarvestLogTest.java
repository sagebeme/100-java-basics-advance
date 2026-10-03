import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HarvestLogTest {

    @Test
    void writesTheFarmerTheWeightAndYesForARipeDelivery() {
        assertEquals("Wanjiru delivered 23.5 kg of cherry. All ripe: yes",
                HarvestLog.logLine("Wanjiru", 23.5, true));
    }

    @Test
    void writesNoWhenSomeCherriesWereNotRipe() {
        assertEquals("Otieno delivered 12.0 kg of cherry. All ripe: no",
                HarvestLog.logLine("Otieno", 12, false));
    }
}
