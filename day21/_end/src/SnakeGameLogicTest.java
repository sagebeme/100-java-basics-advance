import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SnakeGameLogicTest {

    @Test
    void movingRightAdvancesTheHeadByOneCell() {
        assertArrayEquals(new int[]{20, 0}, SnakeGameLogic.nextHead(new int[]{0, 0}, 1, 0));
    }

    @Test
    void positionsInsideTheBoardDoNotHitTheWall() {
        assertFalse(SnakeGameLogic.hitsWall(100, 100));
    }

    @Test
    void goingNegativeHitsTheWall() {
        assertTrue(SnakeGameLogic.hitsWall(-20, 100));
    }

    @Test
    void goingPastTheEdgeHitsTheWall() {
        assertTrue(SnakeGameLogic.hitsWall(SnakeGameLogic.WIDTH, 100));
    }

    @Test
    void headOverlappingABodySegmentHitsSelf() {
        ArrayList<int[]> snake = new ArrayList<>(Arrays.asList(new int[]{40, 40}, new int[]{60, 40}));
        assertTrue(SnakeGameLogic.hitsSelf(60, 40, snake));
    }

    @Test
    void headNotOverlappingAnySegmentDoesNotHitSelf() {
        ArrayList<int[]> snake = new ArrayList<>(Arrays.asList(new int[]{40, 40}, new int[]{60, 40}));
        assertFalse(SnakeGameLogic.hitsSelf(80, 40, snake));
    }

    @Test
    void landingOnFoodCountsAsEating() {
        assertTrue(SnakeGameLogic.eatsFood(100, 100, new int[]{100, 100}));
    }

    @Test
    void missingFoodDoesNotCountAsEating() {
        assertFalse(SnakeGameLogic.eatsFood(100, 100, new int[]{120, 100}));
    }
}
