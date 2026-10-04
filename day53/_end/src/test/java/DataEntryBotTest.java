import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DataEntryBotTest extends BrowserTest {

    @Test
    void entersEveryRowAndChecksTheResult() {
        driver.get(site.url("/form.html").toString());
        List<Rental> rows = List.of(new Rental("Ruaka", 1, 28000), new Rental("Kasarani", 0, 18000), new Rental("Kileleshwa", 4, 180000));
        assertEquals(rows, DataEntryBot.enterAll(new SurveyForm(driver), rows));
    }

    @Test
    void aRowTheFormRefusesStopsTheBot() {
        driver.get(site.url("/form.html").toString());
        assertThrows(IllegalArgumentException.class, () -> new SurveyForm(driver).submit(new Rental("Ruaka", 1, 50)));
    }
}
