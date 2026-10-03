import java.time.OffsetDateTime;

// published is null when the page doesn't say when the story came out.
public record Story(String id, String section, String headline, String url, OffsetDateTime published, String summary) {}
