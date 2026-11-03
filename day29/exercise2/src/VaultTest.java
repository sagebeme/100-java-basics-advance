import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VaultTest {

    @TempDir
    Path dir;

    private final Login kplc = new Login("kplc.co.ke", "cafe-eldoret", "Ug7?mT3x");
    private final Login ecitizen = new Login("ecitizen.go.ke", "owner", "p4N!wq8Z");
    private final Login kra = new Login("itax.kra.go.ke", "A012345678Z", "Kp=3rT9w");

    @Test
    void aMissingFileIsAnEmptyVault() throws IOException {
        Vault vault = Vault.open(dir.resolve("vault.txt"));
        assertEquals(0, vault.size());
    }

    @Test
    void whatIsSavedComesBackInOrder() throws IOException {
        Path file = dir.resolve("vault.txt");
        Vault vault = Vault.open(file);
        vault.put(kplc);
        vault.put(ecitizen);
        vault.save();

        assertEquals(List.of("kplc.co.ke|cafe-eldoret|Ug7?mT3x", "ecitizen.go.ke|owner|p4N!wq8Z"),
                Files.readAllLines(file));
        Vault again = Vault.open(file);
        assertEquals(List.of(kplc, ecitizen), again.search(""));
    }

    @Test
    void puttingTheSameSiteReplacesItInPlace() throws IOException {
        Vault vault = Vault.open(dir.resolve("vault.txt"));
        vault.put(kplc);
        vault.put(ecitizen);
        Login newPassword = new Login("KPLC.co.ke", "cafe-eldoret", "N3w!pass");
        vault.put(newPassword);
        assertEquals(2, vault.size());
        assertEquals(List.of(newPassword, ecitizen), vault.search(""));
    }

    @Test
    void getsBySiteIgnoringCase() throws IOException {
        Vault vault = Vault.open(dir.resolve("vault.txt"));
        vault.put(kplc);
        assertEquals(Optional.of(kplc), vault.get("KPLC.CO.KE"));
        assertEquals(Optional.empty(), vault.get("safaricom.co.ke"));
    }

    @Test
    void searchesSitesAndUsernames() throws IOException {
        Vault vault = Vault.open(dir.resolve("vault.txt"));
        vault.put(kplc);
        vault.put(ecitizen);
        vault.put(kra);
        assertEquals(List.of(ecitizen, kra), vault.search("GO.KE"));
        assertEquals(List.of(kplc), vault.search("eldoret"));
        assertEquals(List.of(), vault.search("Ug7"), "passwords are never searched");
    }

    @Test
    void removesBySite() throws IOException {
        Vault vault = Vault.open(dir.resolve("vault.txt"));
        vault.put(kplc);
        vault.put(ecitizen);
        assertTrue(vault.remove("Kplc.co.ke"));
        assertFalse(vault.remove("kplc.co.ke"));
        assertEquals(List.of(ecitizen), vault.search(""));
    }

    @Test
    void refusesLoginsThatWouldBreakTheFile() throws IOException {
        Vault vault = Vault.open(dir.resolve("vault.txt"));
        assertThrows(IllegalArgumentException.class, () -> vault.put(new Login("a.com", "me", "pa|ss")));
        assertThrows(IllegalArgumentException.class, () -> vault.put(new Login("a.com", "me\nyou", "x")));
        assertThrows(IllegalArgumentException.class, () -> vault.put(new Login(" ", "me", "x")));
        assertEquals(0, vault.size());
    }

    @Test
    void skipsAndCountsLinesItCantRead() throws IOException {
        Path file = dir.resolve("vault.txt");
        Files.write(file, List.of(
                "kplc.co.ke|cafe-eldoret|Ug7?mT3x",
                "",
                "half a line",
                "a|b|c|d",
                "|nobody|x",
                "wifi.local|admin|"));
        Vault vault = Vault.open(file);
        assertEquals(2, vault.size());
        assertEquals(3, vault.skippedLines());
        assertEquals(Optional.of(new Login("wifi.local", "admin", "")), vault.get("wifi.local"));
    }
}
