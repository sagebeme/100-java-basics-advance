import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CaesarCipherTest {

    @Test
    void shiftsLettersForward() {
        assertEquals("khoor", CaesarCipher.encode("hello", 3));
    }

    @Test
    void wrapsAroundTheAlphabet() {
        assertEquals("ab", CaesarCipher.encode("yz", 2));
    }

    @Test
    void preservesCaseAndNonLetters() {
        assertEquals("Khoor, Zruog!", CaesarCipher.encode("Hello, World!", 3));
    }

    @Test
    void decodeReversesEncode() {
        String original = "Attack at dawn";
        String encoded = CaesarCipher.encode(original, 7);
        assertEquals(original, CaesarCipher.decode(encoded, 7));
    }
}
