import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FarmProfileTest {

    @Test
    void printsEveryFactWithItsLabel() {
        ByteArrayOutputStream captured = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(captured));
        try {
            FarmProfile.main(new String[]{});
        } finally {
            System.setOut(original);
        }
        String output = captured.toString();
        for (String label : new String[]{"Farmer:", "Trees:", "Altitude:", "Farm size:", "Organic:"}) {
            assertTrue(output.contains(label), "missing the line starting with " + label);
        }
    }
}
