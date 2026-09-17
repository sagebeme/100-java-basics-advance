import java.util.List;

public class Exercise1 {

    public static String displayWord(String word, List<Character> guessedLetters) {
        StringBuilder display = new StringBuilder();
        for (char letter : word.toCharArray()) {
            if (display.length() > 0) display.append(" ");
            display.append(guessedLetters.contains(letter) ? letter : '_');
        }
        return display.toString();
    }

    public static void main(String[] args) {
        System.out.println(displayWord("JAVA", List.of('A', 'V')));
    }
}
