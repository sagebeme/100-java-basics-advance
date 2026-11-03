import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KukuTest {

    // A road 300 wide and 200 deep. The kuku starts in the middle of the bottom edge and steps 20 at a time.
    private Kuku kuku() {
        return new Kuku(150, 200, 20, 300, 200);
    }

    @Test
    void startsAtTheStartOnLevelOne() {
        Kuku kuku = kuku();
        assertEquals(150, kuku.getX());
        assertEquals(200, kuku.getY());
        assertEquals(1, kuku.getLevel());
    }

    @Test
    void stepsInEveryDirection() {
        Kuku kuku = kuku();
        kuku.moveUp();
        assertEquals(180, kuku.getY());
        kuku.moveLeft();
        assertEquals(130, kuku.getX());
        kuku.moveRight();
        kuku.moveRight();
        assertEquals(170, kuku.getX());
        kuku.moveDown();
        assertEquals(200, kuku.getY());
    }

    @Test
    void cannotLeaveTheRoad() {
        Kuku kuku = kuku();
        kuku.moveDown();
        assertEquals(200, kuku.getY());
        for (int i = 0; i < 20; i++) {
            kuku.moveLeft();
        }
        assertEquals(0, kuku.getX());
        for (int i = 0; i < 20; i++) {
            kuku.moveRight();
        }
        assertEquals(300, kuku.getX());
    }

    @Test
    void reachesTheOtherSideAtTheTop() {
        Kuku kuku = kuku();
        for (int i = 0; i < 9; i++) {
            kuku.moveUp();
        }
        assertFalse(kuku.reachedTheOtherSide());
        kuku.moveUp();
        assertTrue(kuku.reachedTheOtherSide());
        kuku.moveUp();
        assertEquals(0, kuku.getY());
    }

    @Test
    void crossingStartsTheNextLevelBackAtTheStart() {
        Kuku kuku = kuku();
        kuku.moveLeft();
        assertFalse(kuku.checkCrossing());
        for (int i = 0; i < 10; i++) {
            kuku.moveUp();
        }
        assertTrue(kuku.checkCrossing());
        assertEquals(2, kuku.getLevel());
        assertEquals(150, kuku.getX());
        assertEquals(200, kuku.getY());
    }

    @Test
    void goingBackToStartKeepsTheLevel() {
        Kuku kuku = kuku();
        for (int i = 0; i < 10; i++) {
            kuku.moveUp();
        }
        kuku.checkCrossing();
        kuku.moveUp();
        kuku.backToStart();
        assertEquals(200, kuku.getY());
        assertEquals(2, kuku.getLevel());
    }

    @Test
    void refusesAStepOfNothing() {
        assertThrows(IllegalArgumentException.class, () -> new Kuku(150, 200, 0, 300, 200));
    }
}
