import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {

    @Test
    void movesUpBySpeed() {
        Player player = new Player(300, 750, 20, 600, 800);
        player.moveUp();
        assertEquals(730, player.getY());
    }

    @Test
    void cannotMoveAboveTheTopEdge() {
        Player player = new Player(300, 10, 20, 600, 800);
        player.moveUp();
        assertEquals(0, player.getY());
    }

    @Test
    void cannotMoveBeyondTheRightEdge() {
        Player player = new Player(590, 750, 20, 600, 800);
        player.moveRight();
        assertEquals(600, player.getX());
    }

    @Test
    void movesLeftBySpeed() {
        Player player = new Player(300, 750, 20, 600, 800);
        player.moveLeft();
        assertEquals(280, player.getX());
    }
}
