public class MenuChoice {

    public static int parseChoice(String input, int options) {
        // TODO: -1 for empty text or anything that isn't all digits
        // TODO: -1 for a number outside 1..options, otherwise the number
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(parseChoice(" 3 ", 4));
        System.out.println(parseChoice("three", 4));
    }
}
