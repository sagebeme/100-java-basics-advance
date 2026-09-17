import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BandNameGeneratorTest {

    @Test
    void combinesCityAndPetWithASpace() {
        assertEquals("Nairobi Simba", BandNameGenerator.generateBandName("Nairobi", "Simba"));
    }

    @Test
    void worksWithMultiWordNames() {
        assertEquals("New York Mr Whiskers", BandNameGenerator.generateBandName("New York", "Mr Whiskers"));
    }
}
