public class MenuChoice {

    public static int parseChoice(String input, int options) {
        String text = input.trim();
        if (text.isEmpty()) {
            return -1;
        }
        for (char c : text.toCharArray()) {
            if (!Character.isDigit(c)) {
                return -1;
            }
        }
        int choice = Integer.parseInt(text);
        if (choice < 1 || choice > options) {
            return -1;
        }
        return choice;
    }

    public static void main(String[] args) {
        System.out.println(parseChoice(" 3 ", 4));
        System.out.println(parseChoice("three", 4));
    }
}
