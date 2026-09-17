public class Exercise2 {

    /**
     * Divides a by b. Instead of throwing on b == 0, returns a message explaining what went wrong.
     */
    public static String safeDivide(int a, int b) {
        try {
            int result = a / b;
            return "Result: " + result;
        } catch (ArithmeticException e) {
            return "Error: cannot divide by zero";
        }
    }

    /**
     * Parses a string as an int. Returns a message explaining what went wrong instead of throwing.
     */
    public static String safeParse(String text) {
        try {
            int value = Integer.parseInt(text);
            return "Parsed: " + value;
        } catch (NumberFormatException e) {
            return "Error: \"" + text + "\" is not a number";
        } finally {
            System.out.println("Finished trying to parse \"" + text + "\"");
        }
    }

    public static void main(String[] args) {
        System.out.println(safeDivide(10, 2));
        System.out.println(safeDivide(10, 0));
        System.out.println(safeParse("42"));
        System.out.println(safeParse("forty-two"));
    }
}
