import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FeedPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public FeedPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static FeedPage logIn(WebDriver driver, String user, String password) {
        driver.findElement(By.id("username")).sendKeys(user);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("sign-in")).click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(d ->
                d.getCurrentUrl().endsWith("/feed.html") || !d.findElement(By.id("login-error")).getText().isBlank());
        if (!driver.getCurrentUrl().endsWith("/feed.html")) {
            throw new IllegalStateException(driver.findElement(By.id("login-error")).getText());
        }
        FeedPage feed = new FeedPage(driver);
        feed.waitForCount(1);
        return feed;
    }

    private void waitForCount(int atLeast) {
        wait.until(d -> {
            String count = d.findElement(By.id("feed")).getDomAttribute("data-count");
            return count != null && Integer.parseInt(count) >= atLeast;
        });
    }

    // Presses "Load more" until every post is on the page (or `maxPages` loads, as a safety limit).
    public void loadAll(int maxPages) {
        for (int i = 0; i < maxPages; i++) {
            WebElement more = driver.findElement(By.id("more"));
            wait.until(ExpectedConditions.or(ExpectedConditions.elementToBeClickable(more),
                    ExpectedConditions.textToBePresentInElement(more, "No more posts")));
            if (!more.isEnabled()) return;
            int before = posts().size();
            more.click();
            waitForCount(before + 1);
        }
    }

    public List<Post> posts() {
        List<Post> posts = new ArrayList<>();
        for (WebElement li : driver.findElements(By.cssSelector("#feed li.post"))) {
            posts.add(new Post(li.getDomAttribute("data-id"), li.findElement(By.className("author")).getText().replace("@", ""),
                    li.findElement(By.className("text")).getText(),
                    "true".equals(li.findElement(By.className("like")).getDomAttribute("aria-pressed"))));
        }
        return posts;
    }

    public void like(String postId) {
        driver.findElement(By.cssSelector("li.post[data-id='" + postId + "'] button.like")).click();
    }

    public void publish(String text) {
        int before = posts().size();
        WebElement box = driver.findElement(By.id("compose"));
        box.clear();
        box.sendKeys(text);
        driver.findElement(By.id("post")).click();
        waitForCount(before + 1);
    }
}
