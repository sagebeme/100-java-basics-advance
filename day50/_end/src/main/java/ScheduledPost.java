import java.time.LocalDateTime;

public record ScheduledPost(LocalDateTime at, String text) {}
