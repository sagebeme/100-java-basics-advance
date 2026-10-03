import java.util.List;

public class Masking {

    public static String mask(String secret) {
        // TODO
        return secret;
    }

    public static String redact(String line, List<String> secrets) {
        // TODO
        return line;
    }

    public static void main(String[] args) {
        System.out.println(mask("atsk_9f2c1e7a55d04b8e"));
        System.out.println(redact("POST /sms key=atsk_9f2c1e7a55d04b8e", List.of("atsk_9f2c1e7a55d04b8e")));
    }
}
