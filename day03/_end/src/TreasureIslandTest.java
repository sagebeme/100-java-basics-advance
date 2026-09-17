import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreasureIslandTest {

    @Test
    void yellowDoorWins() {
        assertEquals("You found the treasure! You Win!", TreasureIsland.play("left", "wait", "yellow"));
    }

    @Test
    void redDoorIsFire() {
        assertEquals("It's a room full of fire. Game Over.", TreasureIsland.play("left", "wait", "red"));
    }

    @Test
    void blueDoorIsBeasts() {
        assertEquals("You enter a room of beasts. Game Over.", TreasureIsland.play("left", "wait", "blue"));
    }

    @Test
    void unknownDoorIsGameOver() {
        assertEquals("You chose a door that doesn't exist. Game Over.", TreasureIsland.play("left", "wait", "green"));
    }

    @Test
    void swimmingGetsAttackedByATrout() {
        assertEquals("You get attacked by an angry trout. Game Over.", TreasureIsland.play("left", "swim", ""));
    }

    @Test
    void goingRightFallsInAHole() {
        assertEquals("You fell into a hole. Game Over.", TreasureIsland.play("right", "", ""));
    }
}
