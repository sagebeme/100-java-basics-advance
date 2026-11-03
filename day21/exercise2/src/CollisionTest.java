import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollisionTest {

    private final List<String> body = List.of("2,1", "2,2", "1,2", "1,1");

    @Test
    void anEmptySquareIsClear() {
        assertEquals("CLEAR", Collision.check("3,1", body, "7,7", 10, 10, false));
    }

    @Test
    void findsTheFood() {
        assertEquals("FOOD", Collision.check("3,1", body, "3,1", 10, 10, false));
    }

    @Test
    void everyEdgeIsAWall() {
        assertEquals("WALL", Collision.check("10,4", body, "7,7", 10, 10, false));
        assertEquals("WALL", Collision.check("-1,4", body, "7,7", 10, 10, false));
        assertEquals("WALL", Collision.check("4,10", body, "7,7", 10, 10, false));
        assertEquals("WALL", Collision.check("4,-1", body, "7,7", 10, 10, false));
        assertEquals("CLEAR", Collision.check("9,9", body, "7,7", 10, 10, false));
    }

    @Test
    void runningIntoItselfIsSelf() {
        assertEquals("SELF", Collision.check("2,2", body, "7,7", 10, 10, false));
        assertEquals("SELF", Collision.check("1,2", body, "7,7", 10, 10, false));
    }

    @Test
    void theTailMovesAwayOnANormalMove() {
        assertEquals("CLEAR", Collision.check("1,1", body, "7,7", 10, 10, false));
    }

    @Test
    void theTailStaysWhenGrowing() {
        assertEquals("SELF", Collision.check("1,1", body, "7,7", 10, 10, true));
    }

    @Test
    void theWallWinsOverFood() {
        assertEquals("WALL", Collision.check("10,0", body, "10,0", 10, 10, false));
    }

    @Test
    void theSnakeWinsOverFood() {
        assertEquals("SELF", Collision.check("2,2", body, "2,2", 10, 10, false));
    }
}
