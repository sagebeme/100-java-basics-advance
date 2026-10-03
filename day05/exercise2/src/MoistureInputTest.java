import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoistureInputTest {

    private String printedWhile(Runnable action) {
        ByteArrayOutputStream captured = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(captured));
        try {
            action.run();
        } finally {
            System.setOut(original);
        }
        return captured.toString();
    }

    @Test
    void acceptsAGoodAnswerStraightAway() {
        assertEquals(52.0, MoistureInput.readMoisture(new Scanner("52\n")), 0.0001);
    }

    @Test
    void skipsWordsAndNumbersOutOfRange() {
        double[] result = new double[1];
        String printed = printedWhile(() -> result[0] = MoistureInput.readMoisture(new Scanner("fifty\n150\n-3\n11.5\n")));
        assertEquals(11.5, result[0], 0.0001);
        assertEquals(3, printed.split("Please type a number from 0 to 100.", -1).length - 1);
    }

    @Test
    void zeroAndOneHundredAreAllowed() {
        assertEquals(0.0, MoistureInput.readMoisture(new Scanner("0\n")), 0.0001);
        assertEquals(100.0, MoistureInput.readMoisture(new Scanner("100\n")), 0.0001);
    }
}
