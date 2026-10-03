public class EmailText {

    public static boolean isValidAddress(String address) {
        if (address == null || address.contains(" ")) return false;
        int at = address.indexOf('@');
        if (at <= 0 || at != address.lastIndexOf('@')) return false; // exactly one @, not at the start
        String domain = address.substring(at + 1);
        int dot = domain.indexOf('.');
        return dot > 0 && dot < domain.length() - 1;
    }

    public static String toMessageText(String to, String subject, String body) {
        if (!isValidAddress(to)) {
            throw new IllegalArgumentException("Not a valid email address: " + to);
        }
        return "To: " + to + "\nSubject: " + subject + "\n\n" + body;
    }

    public static void main(String[] args) {
        System.out.println(toMessageText("wanjiku@example.com", "Happy birthday!", "Have a wonderful day."));
    }
}
