import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FollowersPage {

    private final WebDriver driver;

    public FollowersPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<Follower> followers() {
        List<Follower> list = new ArrayList<>();
        for (WebElement li : driver.findElements(By.cssSelector("#followers li"))) {
            list.add(new Follower(li.getDomAttribute("data-user"),
                    Integer.parseInt(li.getDomAttribute("data-posts")),
                    Integer.parseInt(li.getDomAttribute("data-followers")),
                    "true".equals(li.findElement(By.tagName("button")).getDomAttribute("aria-pressed"))));
        }
        return list;
    }

    // True if the follow went through; false if the site said "slow down".
    public boolean followBack(String user) {
        WebElement button = driver.findElement(By.cssSelector("li[data-user='" + user + "'] button"));
        button.click();
        return "true".equals(button.getDomAttribute("aria-pressed"));
    }

    public boolean isThrottled() {
        return driver.findElement(By.id("throttle")).isDisplayed();
    }
}
