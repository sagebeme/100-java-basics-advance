import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SurveyForm {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public SurveyForm(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public int responseCount() {
        String count = driver.findElement(By.id("responses")).getDomAttribute("data-count");
        return count == null ? 0 : Integer.parseInt(count);
    }

    public void submit(Rental r) {
        // TODO: type the area, choose bedrooms (4 means "4 or more"), type the rent, click #submit;
        //       throw with the #error text if there is one; wait until responseCount() goes up by one
    }

    // What the form recorded, to check against what we meant to send.
    public List<Rental> responses() {
        List<Rental> rows = new ArrayList<>();
        for (WebElement tr : driver.findElements(By.cssSelector("#responses tr"))) {
            List<WebElement> td = tr.findElements(By.tagName("td"));
            rows.add(new Rental(td.get(0).getText(), Integer.parseInt(td.get(1).getText()), Integer.parseInt(td.get(2).getText())));
        }
        return rows;
    }
}
