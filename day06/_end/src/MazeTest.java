import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MazeTest {

    @Test
    void startsAtTheOriginFacingEast() {
        Maze maze = new Maze();
        assertEquals(0, maze.getX());
        assertEquals(0, maze.getY());
        assertEquals("EAST", maze.getDirection());
    }

    @Test
    void movingEastIncreasesX() {
        Maze maze = new Maze();
        maze.move();
        assertEquals(1, maze.getX());
        assertEquals(0, maze.getY());
    }

    @Test
    void turningLeftFromEastFacesNorth() {
        Maze maze = new Maze();
        maze.turnLeft();
        assertEquals("NORTH", maze.getDirection());
    }

    @Test
    void turningRightFromEastFacesSouth() {
        Maze maze = new Maze();
        maze.turnRight();
        assertEquals("SOUTH", maze.getDirection());
    }

    @Test
    void fourLeftTurnsReturnToTheOriginalDirection() {
        Maze maze = new Maze();
        maze.turnLeft();
        maze.turnLeft();
        maze.turnLeft();
        maze.turnLeft();
        assertEquals("EAST", maze.getDirection());
    }

    @Test
    void isAtGoalOnlyAtPositionNineNine() {
        Maze maze = new Maze();
        assertFalse(maze.isAtGoal());
        for (int i = 0; i < 9; i++) {
            maze.move(); // walks east from (0,0) to (9,0)
        }
        assertFalse(maze.isAtGoal());
    }

    @Test
    void pathIsClearWithinTheTenByTenGrid() {
        Maze maze = new Maze();
        assertTrue(maze.isPathClear());
    }
}
