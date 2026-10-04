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
        // TODO: type the query into #q, choose the city in #city with a Select, click #search
        // TODO: wait (WebDriverWait) until #results has data-ready="true"
        List<EventCard> cards = new ArrayList<>();
        // TODO: one EventCard per li.event: data-id, .title, the city from .venue ("Venue, City · date"),
        //       time[datetime] and .price[data-kes]
        return cards;
    }

    public RegisterPage openRegistration(int eventId) {
        driver.findElement(By.cssSelector("li.event[data-id='" + eventId + "'] a.register")).click();
        return new RegisterPage(driver);
    }
}
