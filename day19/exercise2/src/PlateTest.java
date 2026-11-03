import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlateTest {

    @Test
    void tidiesWhateverTheGuardTypes() {
        assertEquals("KDA 123A", new Plate("KDA 123A").toString());
        assertEquals("KDA 123A", new Plate("kda123a").toString());
        assertEquals("KDA 123A", new Plate("  KDA  123A ").toString());
        assertEquals("KCB 007Z", new Plate("k c b 0 0 7 z").toString());
    }

    @Test
    void refusesSomethingThatIsNotAPlate() {
        assertThrows(IllegalArgumentException.class, () -> new Plate("KD 123A"));
        assertThrows(IllegalArgumentException.class, () -> new Plate("KDA 1234"));
        assertThrows(IllegalArgumentException.class, () -> new Plate("KDA 12AA"));
        assertThrows(IllegalArgumentException.class, () -> new Plate(""));
    }

    @Test
    void theSameCarTypedTwoWaysIsEqual() {
        assertEquals(new Plate("KDA 123A"), new Plate("kda123a"));
    }

    @Test
    void differentCarsAreNotEqual() {
        assertNotEquals(new Plate("KDA 123A"), new Plate("KDA 123B"));
    }

    @Test
    void aPlateIsNeverEqualToAStringOrNull() {
        Plate plate = new Plate("KDA 123A");
        assertFalse(plate.equals("KDA 123A"));
        assertFalse(plate.equals(null));
    }

    @Test
    void equalPlatesHaveEqualHashCodes() {
        assertEquals(new Plate("KDA 123A").hashCode(), new Plate(" kda 123a").hashCode());
    }

    @Test
    void theGateListKeepsEachCarOnce() {
        Set<Plate> residents = new HashSet<>();
        for (String typed : List.of("KDA 123A", "kda123a", " KDA  123A ", "KCB 007Z")) {
            residents.add(new Plate(typed));
        }
        assertEquals(2, residents.size());
    }
}
