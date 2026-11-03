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
        // TODO: no file → return the empty vault
        // TODO: skip blank lines; count (and skip) lines that aren't three parts with a site
        return vault;
    }

    public void save() throws IOException {
        // TODO: one line per login, in vault order
    }

    // Adds a login, or replaces the one for the same site (ignoring case) where it was.
    public void put(Login login) {
        // TODO: refuse a blank site, and | or a new line in any part, with an IllegalArgumentException
    }

    public Optional<Login> get(String site) {
        // TODO
        return Optional.empty();
    }

    // Logins whose site or username contains the term, ignoring case, in vault order.
    public List<Login> search(String term) {
        // TODO
        return new ArrayList<>();
    }

    // True if there was a login for that site to remove.
    public boolean remove(String site) {
        // TODO
        return false;
    }

    public int size() {
        // TODO
        return 0;
    }

    // Lines open() couldn't read, so the app can warn instead of losing them quietly.
    public int skippedLines() {
        // TODO
        return 0;
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
