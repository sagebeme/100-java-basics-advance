public class EmailText {

    public static boolean isValidAddress(String address) {
        // TODO
        return false;
    }

    public static String toMessageText(String to, String subject, String body) {
        // TODO: throw IllegalArgumentException for a bad address
        return "";
    }

    public static void main(String[] args) {
        System.out.println(toMessageText("wanjiku@example.com", "Happy birthday!", "Have a wonderful day."));
    }
}
