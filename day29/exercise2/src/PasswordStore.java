import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PasswordStore {

    public static void save(String path, List<String> entries) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            for (String entry : entries) {
                writer.println(entry);
            }
        }
    }

    public static List<String> load(String path) throws IOException {
        List<String> entries = new ArrayList<>();
        File file = new File(path);
        if (!file.exists()) return entries;
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isBlank()) entries.add(line);
            }
        }
        return entries;
    }

    public static List<String> search(List<String> entries, String term) {
        List<String> matches = new ArrayList<>();
        for (String entry : entries) {
            if (entry.toLowerCase().contains(term.toLowerCase())) {
                matches.add(entry);
            }
        }
        return matches;
    }

    public static List<String> delete(List<String> entries, String website) {
        List<String> remaining = new ArrayList<>();
        for (String entry : entries) {
            if (!entry.startsWith(website + "|")) {
                remaining.add(entry);
            }
        }
        return remaining;
    }

    public static void main(String[] args) throws IOException {
        List<String> entries = List.of("github.com|sage|hunter2", "gmail.com|sage|letmein");
        save("passwords-demo.txt", entries);
        System.out.println(load("passwords-demo.txt"));
        System.out.println(search(entries, "github"));
        System.out.println(delete(entries, "github.com"));
    }
}
