import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class SafariClub {

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("""
                    Commands:
                      join "<name>" <email> <City:CODE:maxPrice>...   e.g. join "Wanjiku Kamau" wanjiku@example.com Mombasa:MBA:7000
                      leave <email>
                      list
                      send [yyyy-mm-dd]""");
            return;
        }
        MemberStore store = new MemberStore(Path.of("members.json"));
        try {
            switch (args[0]) {
                case "join" -> {
                    if (args.length < 4) throw new IllegalArgumentException("Usage: join \"<name>\" <email> <City:CODE:maxPrice>...");
                    List<WishItem> wishes = new ArrayList<>();
                    for (int i = 3; i < args.length; i++) wishes.add(Member.parseWish(args[i]));
                    Member member = new Member(args[1], args[2], wishes);
                    System.out.println(store.join(member)
                            ? "Karibu, " + member.name() + "! You'll hear from us before every long weekend."
                            : member.email() + " is already a member.");
                }
                case "leave" -> System.out.println(args.length > 1 && store.leave(args[1])
                        ? "Removed. Kwaheri, and safe travels!" : "No member with that email.");
                case "list" -> {
                    for (Member m : store.all()) {
                        List<String> wishes = m.wishes().stream().map(w -> w.city() + " ≤ KES " + w.maxPriceKes()).toList();
                        System.out.println(m.name() + " <" + m.email() + ">: " + String.join(", ", wishes));
                    }
                    System.out.println(store.all().size() + " members");
                }
                case "send" -> send(store, args.length > 1 ? LocalDate.parse(args[1]) : LocalDate.now());
                default -> System.out.println("Unknown command \"" + args[0] + "\".");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    static void send(MemberStore store, LocalDate today) throws Exception {
        LongWeekend weekend = Holidays.next(Holidays.read(Path.of("holidays.csv")), today);
        if (weekend == null) {
            System.out.println("No long weekend coming up in holidays.csv.");
            return;
        }
        Set<String> codes = new HashSet<>();
        for (Member m : store.all()) for (WishItem w : m.wishes()) codes.add(w.code());

        String apiUrl = System.getenv("FARE_API_URL");
        try (PracticeFareServer practice = apiUrl == null ? new PracticeFareServer(Path.of("fares.json")) : null) {
            FareClient client = new FareClient(apiUrl != null ? apiUrl : practice.baseUri().toString());
            Map<String, List<Fare>> fares = new HashMap<>();
            for (String code : codes) { // search each destination once, however many members want it
                fares.put(code, client.search("NBO", code, weekend.start(), weekend.end()));
            }

            Mailer mailer = new OutboxMailer(Path.of("outbox"));
            int sent = 0;
            for (Member m : store.all()) {
                Optional<Email> email = Digest.emailFor(m, weekend, fares);
                if (email.isPresent()) {
                    mailer.send(email.get());
                    sent++;
                    System.out.println("  to " + m.email() + ": " + email.get().subject());
                }
            }
            int skipped = store.all().size() - sent;
            System.out.println("Searched " + codes.size() + " destinations for " + weekend + ". Sent " + sent
                    + (sent == 1 ? " email" : " emails") + " (in outbox/), skipped " + skipped
                    + (skipped == 1 ? " member" : " members") + " with no deals.");
        }
    }
}
