import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Posts written ahead of time, one per line: "2026-10-04T08:00 | Text of the post"
public class PostQueue {

    public static final int MAX_LENGTH = 280;

    public static List<ScheduledPost> parse(List<String> lines) {
        List<ScheduledPost> posts = new ArrayList<>();
        for (String line : lines) {
            if (line.isBlank() || line.startsWith("#")) continue;
            String[] parts = line.split("\\|", 2);
            if (parts.length < 2) throw new IllegalArgumentException("Expected \"time | text\": " + line);
            String text = parts[1].trim();
            if (text.isEmpty() || text.length() > MAX_LENGTH) {
                throw new IllegalArgumentException("A post must be 1 to " + MAX_LENGTH + " characters: " + line);
            }
            posts.add(new ScheduledPost(LocalDateTime.parse(parts[0].trim()), text));
        }
        return posts;
    }

    // Posts whose time has come and that haven't been posted yet, oldest first.
    public static List<ScheduledPost> due(List<ScheduledPost> queue, LocalDateTime now, List<String> alreadyPosted) {
        // TODO: at or before now, not already posted, oldest first
        return List.of();
    }
}
