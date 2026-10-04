import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;

public class KasiCheck {

    static final double PLAN_MBPS = 20;
    static final int RUNS = 3;

    public static List<SpeedResult> measure(WebDriver driver, String url) {
        driver.get(url);
        SpeedTestPage page = new SpeedTestPage(driver);
        List<SpeedResult> runs = new ArrayList<>();
        for (int i = 0; i < RUNS; i++) runs.add(page.runOnce());
        return runs;
    }

    public static void main(String[] args) throws Exception {
        String profile = args.length > 0 ? args[0] : "slow"; // slow, typical or fast
        try (PracticeSite site = new PracticeSite()) {
            WebDriver driver = Browsers.headlessChrome();
            try {
                List<SpeedResult> runs = measure(driver, site.url("/?profile=" + profile).toString());
                double median = Verdict.median(runs.stream().map(SpeedResult::downMbps).toList());
                Path log = Path.of("speeds.csv");
                if (!Files.exists(log)) Files.writeString(log, "time,downMbps,upMbps,pingMs\n");
                for (SpeedResult r : runs) {
                    Files.writeString(log, LocalDateTime.now().withNano(0) + "," + r.downMbps() + "," + r.upMbps() + "," + r.pingMs() + "\n",
                            StandardOpenOption.APPEND);
                }
                System.out.printf("Median download: %.1f Mbps on a %.0f Mbps plan.%n", median, PLAN_MBPS);
                if (Verdict.shouldComplain(runs, PLAN_MBPS, 0.7)) {
                    String text = Verdict.complaint(runs, PLAN_MBPS);
                    driver.get(site.url("/help.html").toString());
                    String ticket = new HelpDeskPage(driver).openTicket(System.getenv().getOrDefault("KASI_ACCOUNT", "204517"), "speed", text);
                    System.out.println("Under 70% of the plan. Opened ticket " + ticket + ":\n" + text);
                } else {
                    System.out.println("That's at least 70% of the plan. No complaint needed.");
                }
            } finally {
                driver.quit();
            }
        }
    }
}
