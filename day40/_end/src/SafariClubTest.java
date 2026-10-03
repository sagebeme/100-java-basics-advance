import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SafariClubTest {

    private static final LongWeekend CHRISTMAS = new LongWeekend(LocalDate.of(2026, 12, 25), LocalDate.of(2026, 12, 27),
            List.of("Christmas Day", "Boxing Day"));

    private static Fare fare(String to, int price, String airline) {
        return new Fare(to, to, CHRISTMAS.start(), CHRISTMAS.end(), price, airline, 0);
    }

    private static final Map<String, List<Fare>> FARES = Map.of(
            "MBA", List.of(fare("MBA", 7900, "Twiga Air"), fare("MBA", 6200, "Ndege Link")),
            "ZNZ", List.of(fare("ZNZ", 21500, "Ndege Link")),
            "KIS", List.of(fare("KIS", 4800, "Ndege Link")));

    private static Member wanjiku() {
        return new Member("Wanjiku Kamau", "Wanjiku@Example.com", List.of(
                new WishItem("Mombasa", "MBA", 7000), new WishItem("Zanzibar", "ZNZ", 22000), new WishItem("Kisumu", "KIS", 4500)));
    }

    @Test
    void membersAreValidatedAndTidied() {
        assertEquals("wanjiku@example.com", wanjiku().email());
        assertThrows(IllegalArgumentException.class, () -> new Member("X", "not-an-email", List.of(new WishItem("a", "MBA", 1))));
        assertThrows(IllegalArgumentException.class, () -> new Member("X", "x@y.com", List.of()));
        assertThrows(IllegalArgumentException.class, () -> Member.parseWish("Mombasa-7000"));
        assertEquals(new WishItem("Mombasa", "MBA", 7000), Member.parseWish("Mombasa:mba:7000"));
    }

    @Test
    void eachMemberGetsOnlyTheirOwnDealsBiggestSavingFirst() {
        List<Deal> deals = Digest.dealsFor(wanjiku(), FARES);
        assertEquals(List.of("MBA", "ZNZ"), deals.stream().map(d -> d.fare().to()).toList()); // Kisumu is over the limit
        assertEquals(6200, deals.get(0).fare().priceKes());
    }

    @Test
    void writesAFriendlyPersonalEmail() {
        Email email = Digest.emailFor(wanjiku(), CHRISTMAS, FARES).orElseThrow();
        assertEquals("wanjiku@example.com", email.to());
        assertEquals("2 long weekend deals for you, Wanjiku", email.subject());
        assertTrue(email.body().startsWith("Habari Wanjiku,"));
        assertTrue(email.body().contains("• Mombasa: KES 6,200 with Ndege Link, direct (KES 800 under your limit)"));
    }

    @Test
    void noDealsMeansNoEmail() {
        Member picky = new Member("Otieno", "otieno@example.com", List.of(new WishItem("Kisumu", "KIS", 3000)));
        assertEquals(Optional.empty(), Digest.emailFor(picky, CHRISTMAS, FARES));
    }

    @Test
    void theStoreKeepsMembersBetweenRunsAndEachEmailOnce() throws IOException {
        Path file = Files.createTempFile("members", ".json");
        Files.delete(file);
        MemberStore store = new MemberStore(file);
        assertTrue(store.join(wanjiku()));
        assertFalse(store.join(new Member("Someone Else", "WANJIKU@example.com", List.of(new WishItem("Lamu", "LAU", 15000)))));

        MemberStore reopened = new MemberStore(file);
        assertEquals(1, reopened.all().size());
        assertEquals(3, reopened.all().get(0).wishes().size());
        assertTrue(reopened.leave("wanjiku@example.com"));
        assertTrue(new MemberStore(file).all().isEmpty());
        Files.deleteIfExists(file);
    }
}
