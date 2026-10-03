public class Phone {

    // 0712345678, 712345678, 254712345678 and "+254 712 345 678" all become +254712345678.
    public static String normalise(String raw) {
        String digits = raw.replaceAll("[\\s-]", "");
        if (digits.startsWith("+254")) digits = digits.substring(4);
        else if (digits.startsWith("254")) digits = digits.substring(3);
        else if (digits.startsWith("0")) digits = digits.substring(1);

        if (!digits.matches("[17]\\d{8}")) {
            throw new IllegalArgumentException("Not a Kenyan mobile number: " + raw);
        }
        return "+254" + digits;
    }
}
