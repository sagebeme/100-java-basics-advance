import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SieveMathsTest {

    @Test
    void screenSeventeenIsAboutSixPointSevenFiveMillimetres() {
        assertEquals(6.746875, SieveMaths.screenToMm(17), 0.0001);
    }

    @Test
    void roundsDownToTheScreenThatFits() {
        assertEquals(17, SieveMaths.screenFromMm(6.8));
        assertEquals(16, SieveMaths.screenFromMm(6.7));
    }

    @Test
    void convertingThereAndBackGivesTheSameScreen() {
        for (int screen = 10; screen <= 20; screen++) {
            assertEquals(screen, SieveMaths.screenFromMm(SieveMaths.screenToMm(screen)));
        }
    }
}
