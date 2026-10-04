import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

// The three-step application form. Each step must be valid before the next one appears.
public class ApplyPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ApplyPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String apply(Applicant me, String letter) {
        type("name", me.name());
        type("email", me.email());
        type("phone", me.phone());
        next("next1", "step2");

        new Select(driver.findElement(By.id("years"))).selectByValue(String.valueOf(bucket(me.years())));
        for (WebElement box : driver.findElements(By.cssSelector("input[name=skill]"))) {
            if (me.skills().contains(box.getDomAttribute("value")) != box.isSelected()) box.click();
        }
        next("next2", "step3");

        // A file upload is typed into: send the file's full path to the <input type="file">.
        driver.findElement(By.id("cv")).sendKeys(me.cv().toAbsolutePath().toString());
        type("letter", letter);
        driver.findElement(By.id("send")).click();
        failIfErrors();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reference"))).getText();
    }

    // The form's choices: 0, 1, 3 or 6 years.
    static int bucket(int years) {
        return years >= 6 ? 6 : years >= 3 ? 3 : years >= 1 ? 1 : 0;
    }

    private void next(String button, String nextStep) {
        driver.findElement(By.id(button)).click();
        failIfErrors();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(nextStep)));
    }

    private void failIfErrors() {
        List<String> errors = driver.findElements(By.cssSelector("#errors .error")).stream().map(WebElement::getText).toList();
        if (!errors.isEmpty()) throw new IllegalArgumentException("The form said: " + String.join(" ", errors));
    }

    private void type(String id, String text) {
        WebElement field = driver.findElement(By.id(id));
        field.clear();
        field.sendKeys(text);
    }
}
