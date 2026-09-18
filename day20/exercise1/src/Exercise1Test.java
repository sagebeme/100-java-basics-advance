import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class Exercise1Test {

    @Test
    void movesForwardWhenThereIsRoom() {
        assertArrayEquals(new int[]{15, 5}, Exercise1.moveAndBounce(10, 5, 0, 20));
    }

    @Test
    void bouncesOffTheMaximumEdge() {
        // at 18 with velocity 5 would go to 23, past max 20, so it bounces back to 13
        assertArrayEquals(new int[]{13, -5}, Exercise1.moveAndBounce(18, 5, 0, 20));
    }

    @Test
    void bouncesOffTheMinimumEdge() {
        // at 2 with velocity -5 would go to -3, past min 0, so it bounces back to 7
        assertArrayEquals(new int[]{7, 5}, Exercise1.moveAndBounce(2, -5, 0, 20));
    }
}
