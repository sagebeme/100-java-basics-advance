import java.util.List;

public class Masking {

    public static String mask(String secret) {
        if (secret == null || secret.isEmpty()) return "(not set)";
        if (secret.length() <= 8) return "••••••••";
        return "••••••••••••" + secret.substring(secret.length() - 4);
    }

    public static String redact(String line, List<String> secrets) {
        String result = line;
        for (String secret : secrets) {
            if (secret != null && !secret.isEmpty()) {
                result = result.replace(secret, mask(secret));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(mask("atsk_9f2c1e7a55d04b8e"));
        System.out.println(redact("POST /sms key=atsk_9f2c1e7a55d04b8e", List.of("atsk_9f2c1e7a55d04b8e")));
    }
}
