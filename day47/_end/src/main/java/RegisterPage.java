import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

    private final WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    // Fills in the form and submits it. Returns the confirmation code, or throws with the page's own
    // error messages if the form refused the details.
    public String register(Attendee who) {
        type("name", who.name());
        type("email", who.email());
        type("tickets", String.valueOf(who.tickets()));
        WebElement terms = driver.findElement(By.id("terms"));
        if (!terms.isSelected()) terms.click();
        driver.findElement(By.id("submit")).click();

        // Either errors appear straight away, or the confirmation appears a moment later.
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(d ->
                !d.findElements(By.cssSelector("#errors .error")).isEmpty() || !d.findElements(By.id("code")).isEmpty());

        List<String> errors = driver.findElements(By.cssSelector("#errors .error")).stream().map(WebElement::getText).toList();
        if (!errors.isEmpty()) {
            throw new IllegalArgumentException("The form said: " + String.join(" ", errors));
        }
        return driver.findElement(By.id("code")).getText();
    }

    private void type(String id, String text) {
        WebElement field = driver.findElement(By.id(id));
        field.clear();
        field.sendKeys(text);
    }
}
