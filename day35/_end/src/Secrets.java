public class Secrets {

    // "atsk_9f2c1e7a55d04b8e" → "••••••••••••4b8e" (a fixed-length mask, so the length isn't revealed either)
    public static String mask(String secret) {
        if (secret == null || secret.isEmpty()) return "(not set)";
        if (secret.length() <= 8) return "••••••••";
        return "••••••••••••" + secret.substring(secret.length() - 4);
    }
}
