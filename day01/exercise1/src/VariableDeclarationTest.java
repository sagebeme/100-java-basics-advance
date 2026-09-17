import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VariableDeclarationTest {

    @Test
    void printsAllFourVariables() {
        ByteArrayOutputStream captured = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(captured));
        try {
            VariableDeclaration.main(new String[]{});
        } finally {
            System.setOut(original);
        }
        String output = captured.toString();
        assertTrue(output.contains("Age:"));
        assertTrue(output.contains("Height:"));
        assertTrue(output.contains("Name:"));
        assertTrue(output.contains("Likes Java:"));
    }
}
