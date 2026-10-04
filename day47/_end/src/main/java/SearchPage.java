import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

// A "page object": everything the bot knows about the search page lives here, so when the page
// changes, this is the only class that needs fixing.
public class SearchPage {

    private final WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<EventCard> search(String query, String city) {
        driver.findElement(By.id("q")).sendKeys(query);
        new Select(driver.findElement(By.id("city"))).selectByValue(city);
        driver.findElement(By.id("search")).click();

        // Results arrive after a delay: wait for them instead of guessing with Thread.sleep.
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeToBe(By.id("results"), "data-ready", "true"));

        List<EventCard> cards = new ArrayList<>();
        for (WebElement li : driver.findElements(By.cssSelector("li.event"))) {
            String venue = li.findElement(By.cssSelector(".venue")).getText();
            cards.add(new EventCard(
                    Integer.parseInt(li.getDomAttribute("data-id")),
                    li.findElement(By.cssSelector(".title")).getText(),
                    venue.substring(venue.indexOf(", ") + 2, venue.indexOf(" ·")),
                    LocalDate.parse(li.findElement(By.cssSelector("time")).getDomAttribute("datetime")),
                    Integer.parseInt(li.findElement(By.cssSelector(".price")).getDomAttribute("data-kes"))));
        }
        return cards;
    }

    public RegisterPage openRegistration(int eventId) {
        driver.findElement(By.cssSelector("li.event[data-id='" + eventId + "'] a.register")).click();
        return new RegisterPage(driver);
    }
}
