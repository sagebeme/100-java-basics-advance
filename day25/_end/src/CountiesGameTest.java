import java.io.File;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CountiesGameTest {

    private final List<String> counties = List.of("Kisumu", "Murang'a", "Tana River");

    @Test
    void aCountyInTheListIsCorrect() {
        assertEquals(CountiesGame.GuessResult.CORRECT, CountiesGame.evaluateGuess("Kisumu", counties, List.of()));
    }

    @Test
    void capitalsAndSpacesDoNotMatter() {
        assertEquals(CountiesGame.GuessResult.CORRECT, CountiesGame.evaluateGuess("  tana river ", counties, List.of()));
        assertEquals("Tana River", CountiesGame.spelling("TANA RIVER", counties));
    }

    @Test
    void aCountyAlreadyNamedIsFlagged() {
        assertEquals(CountiesGame.GuessResult.ALREADY_GUESSED,
                CountiesGame.evaluateGuess("kisumu", counties, List.of("Kisumu")));
    }

    @Test
    void aNameNotInTheListIsInvalid() {
        assertEquals(CountiesGame.GuessResult.INVALID, CountiesGame.evaluateGuess("Kampala", counties, List.of()));
        assertNull(CountiesGame.spelling("Kampala", counties));
    }

    @Test
    void theCsvHasAll47Counties() throws Exception {
        File csv = new File("counties.csv");
        if (!csv.exists()) {
            csv = new File("day25/_end/src/counties.csv");
        }
        List<String> all = CountiesGame.readCounties(csv);
        assertEquals(47, all.size());
        assertEquals("Mombasa", all.get(0));
        assertEquals("Nairobi", all.get(46));
    }
}
