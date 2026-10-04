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
        // TODO: fill #name, #email and #tickets, tick #terms, click #submit
        // TODO: wait until either "#errors .error" or #code appears
        // TODO: throw IllegalArgumentException("The form said: " + the errors) if there are errors
        return "";
    }

    private void type(String id, String text) {
        WebElement field = driver.findElement(By.id(id));
        field.clear();
        field.sendKeys(text);
    }
}
