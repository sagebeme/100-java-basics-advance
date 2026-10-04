import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.OptionalInt;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CleanerTest {

    @Test
    void readsRentsWrittenManyWays() {
        assertEquals(OptionalInt.of(65000), Cleaner.rent("KES 65,000 / month"));
        assertEquals(OptionalInt.of(85000), Cleaner.rent("KES 85,000/mo"));
        assertEquals(OptionalInt.empty(), Cleaner.rent("Call for price"));
    }

    @Test
    void readsBedroomsWrittenManyWays() {
        assertEquals(OptionalInt.of(2), Cleaner.bedrooms("2BR apartment"));
        assertEquals(OptionalInt.of(1), Cleaner.bedrooms("1 Bedroom with balcony"));
        assertEquals(OptionalInt.of(0), Cleaner.bedrooms("BEDSITTER"));
        assertEquals(OptionalInt.empty(), Cleaner.bedrooms("Office space"));
    }

    @Test
    void dropsDuplicatesAndGaps() {
        List<String[]> raw = List.of(
                new String[]{"Kilimani ", "2BR apartment", "KES 65,000"},
                new String[]{"Kilimani", "2BR apartment", "KES 65,000"},
                new String[]{"Westlands", "Office space", "KES 90,000"},
                new String[]{"Ruaka", "1 Bedroom", "Call us"},
                new String[]{"Kasarani", "Studio", "KES 18,000"});
        assertEquals(List.of(new Rental("Kilimani", 2, 65000), new Rental("Kasarani", 0, 18000)), Cleaner.clean(raw));
    }
}
