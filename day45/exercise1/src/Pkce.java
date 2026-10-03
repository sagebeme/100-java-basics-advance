import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Pkce {

    // TODO: 64 random bytes from a SecureRandom, base64url-encoded without padding
    public static String newVerifier() {
        return "";
    }

    // TODO: base64url (no padding) of the SHA-256 of the verifier's ASCII bytes
    public static String challenge(String verifier) {
        return "";
    }
}
