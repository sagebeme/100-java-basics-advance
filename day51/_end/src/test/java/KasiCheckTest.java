import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class KasiCheckTest extends BrowserTest {

    @Test
    void waitsForEachTestAndReadsTheFinalNumbers() {
        // 7.8 / 4 = 1.95, which JavaScript's toFixed(1) shows as "1.9": binary fractions strike again
        List<SpeedResult> runs = KasiCheck.measure(driver, site.url("/?profile=slow").toString());
        assertEquals(List.of(new SpeedResult(6.2, 1.6, 18), new SpeedResult(7.8, 1.9, 21), new SpeedResult(5.9, 1.5, 24)), runs);
    }

    @Test
    void opensATicket() {
        driver.get(site.url("/help.html").toString());
        String details = "x".repeat(50);
        assertEquals("KC-517-50", new HelpDeskPage(driver).openTicket("204517", "speed", details));
    }

    @Test
    void aBadAccountNumberIsRefused() {
        driver.get(site.url("/help.html").toString());
        assertThrows(IllegalArgumentException.class, () -> new HelpDeskPage(driver).openTicket("12", "speed", "x".repeat(50)));
    }
}
