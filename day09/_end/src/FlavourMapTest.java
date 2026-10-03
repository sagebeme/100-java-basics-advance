import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FlavourMapTest {

    private final Map<String, List<String>> regions = FlavourMap.kenyanRegions();

    @Test
    void describesARegion() {
        assertEquals("Nyeri: blackcurrant, grapefruit, brown sugar", FlavourMap.describe(regions, "Nyeri"));
    }

    @Test
    void anUnknownRegionIsNamedNotCrashed() {
        assertEquals("Unknown region: Mombasa", FlavourMap.describe(regions, "Mombasa"));
    }

    @Test
    void listsEveryNoteOnceInOrder() {
        Set<String> notes = FlavourMap.allNotes(regions);
        assertEquals(15, notes.size()); // 18 notes, but honey, black tea and caramel each appear twice
        assertEquals("black tea", notes.iterator().next());
    }

    @Test
    void findsRegionsByNoteIgnoringCapitals() {
        assertEquals(List.of("Kirinyaga", "Murang'a"), FlavourMap.regionsWithNote(regions, "Honey"));
        assertEquals(List.of(), FlavourMap.regionsWithNote(regions, "mango"));
    }

    @Test
    void findsSharedNotes() {
        assertEquals(Set.of("black tea"), FlavourMap.sharedNotes(regions, "Kiambu", "Embu"));
        assertTrue(FlavourMap.sharedNotes(regions, "Nyeri", "Kisii").isEmpty());
    }

    @Test
    void anUnknownRegionSharesNothing() {
        assertTrue(FlavourMap.sharedNotes(regions, "Nyeri", "Mombasa").isEmpty());
    }

    @Test
    void sharedNotesDoNotChangeTheMap() {
        FlavourMap.sharedNotes(regions, "Kirinyaga", "Murang'a");
        assertEquals(List.of("blackberry", "citrus", "honey"), regions.get("Kirinyaga"));
    }
}
