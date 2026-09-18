import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Exercise1Test {

    @Test
    void removeLastReturnsAndRemovesTheLastItem() {
        List<String> items = new ArrayList<>(List.of("a", "b", "c"));
        assertEquals("c", Exercise1.removeLast(items));
        assertEquals(List.of("a", "b"), items);
    }

    @Test
    void firstNReturnsASliceFromTheStart() {
        List<String> items = List.of("a", "b", "c", "d");
        assertEquals(List.of("a", "b"), Exercise1.firstN(items, 2));
    }

    @Test
    void firstNIsNotConfusedByAskingForMoreThanThereIs() {
        List<String> items = List.of("a", "b");
        assertEquals(List.of("a", "b"), Exercise1.firstN(items, 5));
    }
}
