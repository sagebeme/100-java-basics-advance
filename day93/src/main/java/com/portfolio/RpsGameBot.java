package com.portfolio;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Automates the bundled rps-game.html using a real, headless Chrome browser via Selenium. The
 * game is a local, self-contained page (no external site) so this automation isn't dependent on
 * any third party's markup staying stable.
 */
public class RpsGameBot implements AutoCloseable {

    private final WebDriver driver;
    private final ActivityLog activityLog;
    private final List<RoundResult> history = new ArrayList<>();

    public RpsGameBot(ActivityLog activityLog) throws IOException {
        this.activityLog = activityLog;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new", "--disable-gpu", "--no-sandbox");
        this.driver = new ChromeDriver(options);
        driver.get(extractGameHtml().toUri().toString());
        activityLog.record("Opened game page");
    }

    private Path extractGameHtml() throws IOException {
        Path tempFile = Files.createTempFile("rps-game", ".html");
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("rps-game.html")) {
            if (in == null) {
                throw new IOException("rps-game.html not found on classpath");
            }
            Files.copy(in, tempFile, StandardCopyOption.REPLACE_EXISTING);
        }
        tempFile.toFile().deleteOnExit();
        return tempFile;
    }

    public RoundResult playRound(Strategy strategy) {
        Move move = strategy.nextMove(history);
        driver.findElement(By.id(move.toButtonId())).click();

        WebElement resultElement = driver.findElement(By.id("result"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(d -> List.of("win", "loss", "tie").contains(resultElement.getText()));

        String computerMove = driver.findElement(By.id("computer-move")).getText();
        String outcome = resultElement.getText();

        RoundResult round = new RoundResult(history.size() + 1, move, computerMove, outcome);
        history.add(round);
        activityLog.record("Round " + round.roundNumber() + ": played " + move + ", computer played "
                + computerMove + ", outcome: " + outcome);
        return round;
    }

    public PerformanceSummary playGames(Strategy strategy, int rounds) {
        for (int i = 0; i < rounds; i++) {
            playRound(strategy);
        }
        PerformanceSummary summary = PerformanceSummary.from(history);
        activityLog.record("Finished " + rounds + " rounds: " + summary);
        return summary;
    }

    public List<RoundResult> getHistory() {
        return List.copyOf(history);
    }

    @Override
    public void close() {
        driver.quit();
    }
}
