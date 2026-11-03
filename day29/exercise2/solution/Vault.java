import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Vault {

    private final Path file;
    private final List<Login> logins = new ArrayList<>();
    private int skippedLines;

    private Vault(Path file) {
        this.file = file;
    }

    // Reads the file, one "site|username|password" per line. No file yet is an empty vault.
    public static Vault open(Path file) throws IOException {
        Vault vault = new Vault(file);
        if (!Files.exists(file)) {
            return vault;
        }
        for (String line : Files.readAllLines(file, StandardCharsets.UTF_8)) {
            if (line.isBlank()) {
                continue;
            }
            String[] parts = line.split("\\|", -1);
            if (parts.length != 3 || parts[0].isBlank()) {
                vault.skippedLines++;
                continue;
            }
            vault.logins.add(new Login(parts[0], parts[1], parts[2]));
        }
        return vault;
    }

    public void save() throws IOException {
        List<String> lines = new ArrayList<>();
        for (Login login : logins) {
            lines.add(login.getSite() + "|" + login.getUsername() + "|" + login.getPassword());
        }
        Files.write(file, lines, StandardCharsets.UTF_8);
    }

    // Adds a login, or replaces the one for the same site (ignoring case) where it was.
    public void put(Login login) {
        for (String part : new String[] {login.getSite(), login.getUsername(), login.getPassword()}) {
            if (part.contains("|") || part.contains("\n")) {
                throw new IllegalArgumentException("A login can't contain | or a new line");
            }
        }
        if (login.getSite().isBlank()) {
            throw new IllegalArgumentException("A login needs a site");
        }
        for (int i = 0; i < logins.size(); i++) {
            if (logins.get(i).getSite().equalsIgnoreCase(login.getSite())) {
                logins.set(i, login);
                return;
            }
        }
        logins.add(login);
    }

    public Optional<Login> get(String site) {
        for (Login login : logins) {
            if (login.getSite().equalsIgnoreCase(site)) {
                return Optional.of(login);
            }
        }
        return Optional.empty();
    }

    // Logins whose site or username contains the term, ignoring case, in vault order.
    public List<Login> search(String term) {
        String wanted = term.toLowerCase();
        List<Login> found = new ArrayList<>();
        for (Login login : logins) {
            if (login.getSite().toLowerCase().contains(wanted)
                    || login.getUsername().toLowerCase().contains(wanted)) {
                found.add(login);
            }
        }
        return found;
    }

    // True if there was a login for that site to remove.
    public boolean remove(String site) {
        return logins.removeIf(login -> login.getSite().equalsIgnoreCase(site));
    }

    public int size() {
        return logins.size();
    }

    // Lines open() couldn't read, so the app can warn instead of losing them quietly.
    public int skippedLines() {
        return skippedLines;
    }

    public static void main(String[] args) throws IOException {
        Path file = Path.of("vault-demo.txt");
        Vault vault = open(file);
        vault.put(new Login("kplc.co.ke", "cafe-eldoret", "Ug7?mT3x-qK2"));
        vault.put(new Login("ecitizen.go.ke", "owner", "p4N!wq8Zr=dE"));
        vault.save();
        System.out.println(open(file).search("go"));
    }
}
