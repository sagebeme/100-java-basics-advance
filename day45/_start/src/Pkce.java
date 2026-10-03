import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

// PKCE ("pixy"), from RFC 7636: proves that the program finishing a login is the same one that started it.
// The program keeps a random secret (the verifier) and sends only its hash (the challenge) at the start.
public class Pkce {

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final Base64.Encoder URL_SAFE = Base64.getUrlEncoder().withoutPadding();

    // 64 random bytes, base64url-encoded: 86 characters, which is inside the 43–128 the standard allows.
    public static String newVerifier() {
        byte[] bytes = new byte[64];
        RANDOM.nextBytes(bytes);
        return URL_SAFE.encodeToString(bytes);
    }

    public static String challenge(String verifier) {
        try {
            byte[] hash = MessageDigest.getInstance("SHA-256").digest(verifier.getBytes(StandardCharsets.US_ASCII));
            return URL_SAFE.encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Every Java has SHA-256", e);
        }
    }
}
