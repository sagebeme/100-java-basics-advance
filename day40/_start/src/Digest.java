import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Digest {

    // fares: every fare found for the weekend, keyed by destination code.
    // One Deal per wish that has a fare at or under its limit (the cheapest), biggest saving first.
    public static List<Deal> dealsFor(Member member, Map<String, List<Fare>> fares) {
        // TODO
        return new ArrayList<>();
    }

    // Empty when there are no deals. Otherwise, to the member's email:
    //   subject "2 long weekend deals for you, Wanjiku" ("1 long weekend deal ..." for one)
    //   body    "Habari Wanjiku,\n\nFlights from Nairobi for <weekend>:\n\n" then one line per deal:
    //           "• Mombasa: KES 6,200 with Ndege Link, direct (KES 800 under your limit)\n"
    //           and a friendly sign-off with how to leave the club.
    public static Optional<Email> emailFor(Member member, LongWeekend weekend, Map<String, List<Fare>> fares) {
        // TODO
        return Optional.empty();
    }
}
