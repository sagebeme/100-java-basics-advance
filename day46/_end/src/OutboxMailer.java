import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

// "Sends" each email by writing it to a numbered text file, so you can read exactly what would go out.
public class OutboxMailer implements Mailer {

    private final Path folder;
    private int sent = 0;

    public OutboxMailer(Path folder) {
        this.folder = folder;
    }

    @Override
    public void send(Email email) {
        try {
            Files.createDirectories(folder);
            sent++;
            String text = "To: " + email.to() + "\nSubject: " + email.subject() + "\n\n" + email.body() + "\n";
            Files.writeString(folder.resolve(String.format("%03d.txt", sent)), text);
        } catch (IOException e) {
            throw new UncheckedIOException("Couldn't write to the outbox", e);
        }
    }
}
