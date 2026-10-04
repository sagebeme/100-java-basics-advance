import java.nio.file.Path;
import java.util.List;
import java.util.Set;

public record Applicant(String name, String email, String phone, int years, Set<String> skills,
                        List<String> places, Path cv, String letterTemplate) {}
