import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import org.openqa.selenium.WebDriver;

public class DataEntryBot {

    public static List<Rental> enterAll(SurveyForm form, List<Rental> rows) {
        for (Rental r : rows) form.submit(r);
        List<Rental> recorded = form.responses();
        if (!recorded.equals(rows)) {
            throw new IllegalStateException("The form recorded something different from what was sent: " + recorded);
        }
        return recorded;
    }

    public static void main(String[] args) throws Exception {
        try (PracticeSite site = new PracticeSite()) {
            String html = HttpClient.newHttpClient().send(HttpRequest.newBuilder(site.url("/listings.html")).build(),
                    HttpResponse.BodyHandlers.ofString()).body();
            List<String[]> raw = ListingsScraper.raw(html);
            List<Rental> rows = Cleaner.clean(raw);
            System.out.println("Scraped " + raw.size() + " listings; " + rows.size() + " clean rows after removing duplicates and gaps.");

            StringBuilder csv = new StringBuilder("area,bedrooms,rentKes\n");
            rows.forEach(r -> csv.append(r.area()).append(',').append(r.bedrooms()).append(',').append(r.rentKes()).append('\n'));
            Files.writeString(Path.of("rentals.csv"), csv);

            WebDriver driver = args.length > 0 && args[0].equals("--watch") ? Browsers.visibleChrome() : Browsers.headlessChrome();
            try {
                driver.get(site.url("/form.html").toString());
                List<Rental> recorded = enterAll(new SurveyForm(driver), rows);
                System.out.println("Entered and checked " + recorded.size() + " responses. Saved rentals.csv too.");
            } finally {
                driver.quit();
            }
        }
    }
}
