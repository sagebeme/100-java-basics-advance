import java.time.LocalDate;

public record Member(String name, String email, LocalDate birthday, int contributionDay) {

    // "Wanjiku,wanjiku@example.com,1991-10-02,5"
    public static Member fromCsvLine(String line) {
        String[] parts = line.split(",");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Expected name,email,birthday,contributionDay but got: " + line);
        }
        return new Member(parts[0].trim(), parts[1].trim(), LocalDate.parse(parts[2].trim()),
                Integer.parseInt(parts[3].trim()));
    }
}
