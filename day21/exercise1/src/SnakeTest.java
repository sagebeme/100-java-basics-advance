import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SnakeTest {

    @Test
    void startsLyingFlatWithTheHeadOnTheRight() {
        Snake snake = new Snake(5, 3, 3);
        assertEquals(List.of("5,3", "4,3", "3,3"), snake.body());
        assertEquals("5,3", snake.head());
    }

    @Test
    void movingAddsAHeadAndDropsTheTail() {
        Snake snake = new Snake(5, 3, 3);
        snake.move(1, 0);
        assertEquals(List.of("6,3", "5,3", "4,3"), snake.body());
        snake.move(0, -1);
        assertEquals(List.of("6,2", "6,3", "5,3"), snake.body());
    }

    @Test
    void growingKeepsTheTailForOneMove() {
        Snake snake = new Snake(5, 3, 3);
        snake.grow();
        snake.move(0, 1);
        assertEquals(List.of("5,4", "5,3", "4,3", "3,3"), snake.body());
        snake.move(0, 1);
        assertEquals(List.of("5,5", "5,4", "5,3", "4,3"), snake.body());
    }

    @Test
    void theTailIsTheLastFewSquaresInOrder() {
        Snake snake = new Snake(9, 0, 5);
        assertEquals(List.of("6,0", "5,0"), snake.tail(2));
        assertEquals(snake.body(), snake.tail(10));
    }

    @Test
    void aBiteKeepsTheSquaresUpToIt() {
        Snake snake = new Snake(9, 0, 5);
        snake.biteAt(1);
        assertEquals(List.of("9,0", "8,0"), snake.body());
        snake.move(1, 0);
        assertEquals(List.of("10,0", "9,0"), snake.body());
    }

    @Test
    void changingACopyDoesNotChangeTheSnake() {
        Snake snake = new Snake(5, 3, 3);
        snake.body().clear();
        snake.tail(2).clear();
        assertEquals(3, snake.body().size());
    }

    @Test
    void aSnakeOfOneSquareStillMoves() {
        Snake snake = new Snake(0, 0, 1);
        snake.move(1, 0);
        assertEquals(List.of("1,0"), snake.body());
    }

    @Test
    void refusesASnakeWithNoSquares() {
        assertThrows(IllegalArgumentException.class, () -> new Snake(5, 3, 0));
    }
}
