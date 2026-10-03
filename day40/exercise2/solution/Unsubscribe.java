import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class Unsubscribe {

    public static String token(String email, String secret) {
        try {
            String text = secret + ":" + email.trim().toLowerCase();
            byte[] hash = MessageDigest.getInstance("SHA-256").digest(text.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash).substring(0, 16);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Every Java has SHA-256", e);
        }
    }

    public static String link(String base, String email, String secret) {
        return base + "?email=" + URLEncoder.encode(email.trim().toLowerCase(), StandardCharsets.UTF_8)
                + "&token=" + token(email, secret);
    }

    public static boolean isValid(String email, String token, String secret) {
        if (token == null) return false;
        byte[] expected = token(email, secret).getBytes(StandardCharsets.UTF_8);
        return MessageDigest.isEqual(expected, token.getBytes(StandardCharsets.UTF_8));
    }

    public static void main(String[] args) {
        System.out.println(link("https://safari.example/leave", "wanjiku@example.com", "a-long-random-secret"));
    }
}
