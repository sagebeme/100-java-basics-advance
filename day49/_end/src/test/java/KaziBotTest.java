import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KaziBotTest extends BrowserTest {

    private Applicant me(Path cv, String phone) {
        return new Applicant("Wanjiru Kamau", "wanjiru@example.com", phone, 1, Set.of("java", "git"), List.of("Nairobi"), cv, "");
    }

    @Test
    void readsEveryJob() {
        driver.get(site.url("/").toString());
        List<Job> jobs = new JobsPage(driver).jobs();
        assertEquals(5, jobs.size());
        assertEquals(List.of("java", "selenium", "junit"), jobs.get(4).skills());
        assertTrue(jobs.get(4).remote());
    }

    @Test
    void completesAllThreeStepsWithAnUpload() throws Exception {
        Path cv = Files.createTempFile("cv", ".txt");
        Files.writeString(cv, "My CV");
        driver.get(site.url("/apply.html?job=K-201").toString());
        String ref = new ApplyPage(driver).apply(me(cv, "0712 345 678"), "x".repeat(60));
        assertEquals("K-201-" + cv.getFileName().toString().length(), ref);
        Files.delete(cv);
    }

    @Test
    void stopsWithTheFormsMessageWhenAStepIsInvalid() throws Exception {
        driver.get(site.url("/apply.html?job=K-201").toString());
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> new ApplyPage(driver).apply(me(Path.of("cv.txt"), "12345"), "x".repeat(60)));
        assertTrue(e.getMessage().contains("Enter a Kenyan mobile number."));
    }
}
