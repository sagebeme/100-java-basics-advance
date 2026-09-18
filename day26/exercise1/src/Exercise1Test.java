import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Exercise1Test {

    private final List<String> words = List.of("banana", "kiwi", "apple", "berry");

    @Test
    void sortsFromShortestToLongest() {
        assertEquals(List.of("kiwi", "apple", "berry", "banana"), Exercise1.sortByLength(words));
    }

    @Test
    void filtersWordsByTheirFirstLetter() {
        assertEquals(List.of("banana", "berry"), Exercise1.filterStartingWith(words, "b"));
    }

    @Test
    void transformsWordsIntoTheirLengths() {
        assertEquals(List.of(6, 4, 5, 5), Exercise1.transformToLengths(words));
    }
}
