import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Exercise2Test {

    @Test
    void positionsInsideTheBoardDoNotHitTheWall() {
        assertFalse(Exercise2.hitsWall(5, 5, 10, 10));
    }

    @Test
    void negativePositionsHitTheWall() {
        assertTrue(Exercise2.hitsWall(-1, 5, 10, 10));
    }

    @Test
    void matchingCoordinatesCollide() {
        assertTrue(Exercise2.hitsObject(3, 3, 3, 3));
    }

    @Test
    void aHeadOverlappingItsOwnBodyHitsSelf() {
        List<int[]> body = List.of(new int[]{1, 1}, new int[]{2, 2});
        assertTrue(Exercise2.hitsSelf(new int[]{2, 2}, body));
    }

    @Test
    void aHeadClearOfItsBodyDoesNotHitSelf() {
        List<int[]> body = List.of(new int[]{1, 1}, new int[]{2, 2});
        assertFalse(Exercise2.hitsSelf(new int[]{5, 5}, body));
    }
}
