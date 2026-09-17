public class Exercise2 {

    public static char toUpperChar(char c) {
        return Character.toUpperCase(c);
    }

    public static char toLowerChar(char c) {
        return Character.toLowerCase(c);
    }

    /**
     * Shifts a letter by the given number of places in the alphabet, wrapping around, and
     * preserving case. Non-letters are returned unchanged.
     */
    public static char shift(char c, int shiftBy) {
        if (!Character.isLetter(c)) {
            return c;
        }
        char base = Character.isUpperCase(c) ? 'A' : 'a';
        int wrapped = Math.floorMod((c - base) + shiftBy, 26);
        return (char) (base + wrapped);
    }

    public static boolean isLetterChar(char c) {
        return Character.isLetter(c);
    }

    /**
     * @return the 1-based position of a letter in the alphabet (A/a is 1), or -1 for a non-letter.
     */
    public static int alphabetPosition(char c) {
        if (!Character.isLetter(c)) {
            return -1;
        }
        char base = Character.isUpperCase(c) ? 'A' : 'a';
        return c - base + 1;
    }

    public static void main(String[] args) {
        System.out.println(toUpperChar('a'));
        System.out.println(shift('y', 3));
        System.out.println(isLetterChar('7'));
        System.out.println(alphabetPosition('c'));
    }
}
