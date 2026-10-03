import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// Turns the fares for one long weekend into a personal email for each member who has a deal.
public class Digest {

    // fares: every fare found for the weekend, keyed by destination code
    public static List<Deal> dealsFor(Member member, Map<String, List<Fare>> fares) {
        List<Deal> deals = new ArrayList<>();
        for (WishItem wish : member.wishes()) {
            Optional<Fare> best = fares.getOrDefault(wish.code(), List.of()).stream()
                    .filter(f -> f.priceKes() <= wish.maxPriceKes())
                    .min(Comparator.comparingInt(Fare::priceKes));
            best.ifPresent(f -> deals.add(new Deal(wish, f)));
        }
        deals.sort(Comparator.comparingInt(Deal::saving).reversed());
        return deals;
    }

    // Empty when there's nothing to tell them: no deals, no email.
    public static Optional<Email> emailFor(Member member, LongWeekend weekend, Map<String, List<Fare>> fares) {
        List<Deal> deals = dealsFor(member, fares);
        if (deals.isEmpty()) return Optional.empty();

        String firstName = member.name().split(" ")[0];
        String subject = deals.size() + (deals.size() == 1 ? " long weekend deal" : " long weekend deals") + " for you, " + firstName;
        StringBuilder body = new StringBuilder("Habari " + firstName + ",\n\nFlights from Nairobi for " + weekend + ":\n\n");
        for (Deal d : deals) {
            body.append(String.format("• %s: KES %,d with %s, %s (KES %,d under your limit)\n",
                    d.wish().city(), d.fare().priceKes(), d.fare().airline(), d.fare().route(), d.saving()));
        }
        body.append("\nSafari njema!\nThe Safari Club\n\nTo stop these emails, reply \"leave\".");
        return Optional.of(new Email(member.email(), subject, body.toString()));
    }
}
