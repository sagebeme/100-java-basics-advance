import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Exercise2Test {

    @Test
    void unionCombinesBothSets() {
        assertEquals(Set.of(1, 2, 3, 4), Exercise2.union(Set.of(1, 2, 3), Set.of(2, 3, 4)));
    }

    @Test
    void intersectionKeepsOnlySharedElements() {
        assertEquals(Set.of(2, 3), Exercise2.intersection(Set.of(1, 2, 3), Set.of(2, 3, 4)));
    }

    @Test
    void removingDuplicatesLeavesEachValueOnce() {
        assertEquals(Set.of(1, 2, 3), Exercise2.removeDuplicates(List.of(1, 1, 2, 2, 3)));
    }
}
