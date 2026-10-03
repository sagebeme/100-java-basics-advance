import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BonusKilosTest {

    @Test
    void returnsTheNewValueAndLeavesTheCallersVariableAlone() {
        double mine = 50;
        assertEquals(60, BonusKilos.withBonus(mine, 10), 0.0001);
        assertEquals(50, mine, 0.0001);
    }

    @Test
    void changesEveryElementOfTheCallersArray() {
        double[] team = {50, 20, 0};
        BonusKilos.addBonusToAll(team, 10);
        assertArrayEquals(new double[]{60, 30, 10}, team, 0.0001);
    }
}
