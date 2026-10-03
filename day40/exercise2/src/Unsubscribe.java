import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class Unsubscribe {

    public static String token(String email, String secret) {
        // TODO: first 16 hex characters of SHA-256(secret + ":" + lower-cased, trimmed email)
        return "";
    }

    public static String link(String base, String email, String secret) {
        // TODO
        return base;
    }

    public static boolean isValid(String email, String token, String secret) {
        // TODO: compare with MessageDigest.isEqual
        return false;
    }

    public static void main(String[] args) {
        System.out.println(link("https://safari.example/leave", "wanjiku@example.com", "a-long-random-secret"));
    }
}
