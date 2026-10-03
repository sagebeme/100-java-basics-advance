public class Phone {

    // 0712345678, 712345678, 254712345678 and "+254 712 345 678" all become +254712345678.
    public static String normalise(String raw) {
        // TODO: drop spaces and dashes, strip +254 / 254 / 0, check 9 digits starting with 7 or 1
        // TODO: throw IllegalArgumentException for anything else
        return raw;
    }
}
