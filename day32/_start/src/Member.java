import java.time.LocalDate;

public record Member(String name, String email, LocalDate birthday, int contributionDay) {

    // "Wanjiku,wanjiku@example.com,1991-10-02,5"
    public static Member fromCsvLine(String line) {
        // TODO: split on commas, trim each part, parse the date and the day.
        // TODO: throw an IllegalArgumentException if there aren't exactly 4 parts.
        return null;
    }
}
