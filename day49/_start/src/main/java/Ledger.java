import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

// Remembers every application, so the bot never applies to the same job twice.
public class Ledger {

    private final Path file;

    public Ledger(Path file) {
        this.file = file;
    }

    public Set<String> appliedJobIds() throws IOException {
        Set<String> ids = new HashSet<>();
        if (!Files.exists(file)) return ids;
        for (String line : Files.readAllLines(file)) {
            if (!line.isBlank() && !line.startsWith("date,")) ids.add(line.split(",")[1]);
        }
        return ids;
    }

    public void record(LocalDate day, String jobId, String reference) throws IOException {
        if (!Files.exists(file)) Files.writeString(file, "date,job,reference\n");
        Files.writeString(file, day + "," + jobId + "," + reference + "\n", StandardOpenOption.APPEND);
    }
}
