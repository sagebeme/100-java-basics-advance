import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.openqa.selenium.WebDriver;

public class TamashaBot {

    public static void main(String[] args) throws Exception {
        boolean watch = args.length > 0 && args[0].equals("--watch");
        try (PracticeSite site = new PracticeSite()) {
            WebDriver driver = watch ? Browsers.visibleChrome() : Browsers.headlessChrome();
            try {
                driver.get(site.url("/").toString());
                SearchPage search = new SearchPage(driver);
                List<EventCard> events = search.search("jazz", "Nairobi");
                System.out.println("Found " + events.size() + " jazz events in Nairobi:");
                events.forEach(e -> System.out.printf("  %-22s %s  KES %,d%n", e.title(), e.date(), e.priceKes()));

                Optional<EventCard> pick = EventPicker.pick(events, 1500, LocalDate.of(2026, 12, 1));
                if (pick.isEmpty()) {
                    System.out.println("Nothing within budget.");
                    return;
                }
                System.out.println("Registering for " + pick.get().title() + "…");
                String code = search.openRegistration(pick.get().id()).register(new Attendee("Wanjiru Kamau", "wanjiru@example.com", 2));
                System.out.println("Done! Confirmation code: " + code);
                if (watch) Thread.sleep(3000);
            } finally {
                driver.quit();
            }
        }
    }
}
