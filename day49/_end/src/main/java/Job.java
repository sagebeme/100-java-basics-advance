import java.util.List;

public record Job(String id, String title, String company, String location, boolean remote, List<String> skills) {}
