import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Exercise2Test {

    @Test
    void theTopLeftSquareIsDark() {
        assertTrue(Exercise2.isDarkSquare(0, 0));
    }

    @Test
    void adjacentSquaresAlternate() {
        assertFalse(Exercise2.isDarkSquare(0, 1));
        assertFalse(Exercise2.isDarkSquare(1, 0));
    }

    @Test
    void diagonalSquaresMatch() {
        assertTrue(Exercise2.isDarkSquare(2, 2));
    }
}
