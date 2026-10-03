public class MemberCodes {

    public static String cleanName(String name) {
        String lower = name.trim().toLowerCase();
        int space = lower.indexOf(' ');
        String first = lower.substring(0, space);
        String last = lower.substring(space + 1).trim();
        return capitalise(first) + " " + capitalise(last);
    }

    private static String capitalise(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    public static String initials(String name) {
        int space = name.indexOf(' ');
        char first = name.charAt(0);
        char last = name.charAt(space + 1);
        return (String.valueOf(first) + last).toUpperCase();
    }

    public static String memberCode(String name, int memberNumber) {
        return String.format("%s-%04d", initials(cleanName(name)), memberNumber);
    }

    public static void main(String[] args) {
        System.out.println(cleanName("  wanjiru KAMAU "));
        System.out.println(memberCode("wanjiru kamau", 42));
    }
}
