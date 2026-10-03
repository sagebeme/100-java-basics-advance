import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PickingTeamTest {

    private final String[] team = {"Wanjiru", "Brian", "Mama Njeri", "Otieno"};

    @Test
    void firstIsIndexZero() {
        assertEquals("Wanjiru", PickingTeam.firstToArrive(team));
    }

    @Test
    void lastIsLengthMinusOne() {
        assertEquals("Otieno", PickingTeam.lastToArrive(team));
        assertEquals("Solo", PickingTeam.lastToArrive(new String[]{"Solo"}));
    }

    @Test
    void sizeIsTheArrayLength() {
        assertEquals(4, PickingTeam.teamSize(team));
    }

    @Test
    void findsSomeoneOnTheTeam() {
        assertTrue(PickingTeam.isOnTeam(team, "Mama Njeri"));
        assertFalse(PickingTeam.isOnTeam(team, "Kip"));
    }
}
