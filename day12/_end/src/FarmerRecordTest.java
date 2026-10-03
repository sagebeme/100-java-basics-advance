import org.junit.jupiter.api.Test;
import java.lang.reflect.Modifier;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FarmerRecordTest {

    @Test
    void addsUpDeliveries() {
        FarmerRecord record = new FarmerRecord("Wanjiru", "WK-0042");
        assertTrue(record.addDelivery(23.5));
        assertTrue(record.addDelivery(10));
        assertEquals(33.5, record.getDeliveredKg(), 0.0001);
    }

    @Test
    void refusesZeroAndNegativeDeliveries() {
        FarmerRecord record = new FarmerRecord("Otieno", "OA-0107");
        record.addDelivery(31);
        assertFalse(record.addDelivery(-200));
        assertFalse(record.addDelivery(0));
        assertEquals(31, record.getDeliveredKg(), 0.0001);
    }

    @Test
    void summarisesTheRecord() {
        FarmerRecord record = new FarmerRecord("Wanjiru", "WK-0042");
        record.addDelivery(33.5);
        assertEquals("WK-0042 Wanjiru: 33.5 kg", record.summary());
        assertEquals("Wanjiru", record.getName());
    }

    @Test
    void countsEveryRecordOpened() {
        int before = FarmerRecord.recordsOpened();
        new FarmerRecord("A", "A-1");
        new FarmerRecord("B", "B-1");
        assertEquals(before + 2, FarmerRecord.recordsOpened());
    }

    @Test
    void theFieldsArePrivateAndTheNameIsFinal() throws Exception {
        int kg = FarmerRecord.class.getDeclaredField("deliveredKg").getModifiers();
        int name = FarmerRecord.class.getDeclaredField("name").getModifiers();
        assertTrue(Modifier.isPrivate(kg), "deliveredKg should be private");
        assertTrue(Modifier.isPrivate(name) && Modifier.isFinal(name), "name should be private and final");
    }
}
