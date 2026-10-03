public class MemberCodes {

    // "  wanjiru KAMAU " -> "Wanjiru Kamau"
    public static String cleanName(String name) {
        // TODO: trim, lower-case, split at the space, capitalise each part's first letter
        return "";
    }

    // "Wanjiru Kamau" -> "WK"
    public static String initials(String name) {
        // TODO: the first letter of each part, upper-cased
        return "";
    }

    // ("wanjiru kamau", 42) -> "WK-0042"
    public static String memberCode(String name, int memberNumber) {
        // TODO: clean the name, take its initials, add the number padded to 4 digits
        return "";
    }

    public static void main(String[] args) {
        System.out.println(cleanName("  wanjiru KAMAU "));
        System.out.println(memberCode("wanjiru kamau", 42));
    }
}
