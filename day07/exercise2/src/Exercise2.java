import java.util.List;

public class Exercise2 {

    public static boolean isValidGuess(String guess, List<Character> guessedLetters) {
        if (guess == null || guess.length() != 1) {
            return false;
        }
        char letter = Character.toLowerCase(guess.charAt(0));
        if (!Character.isLetter(letter)) {
            return false;
        }
        return !guessedLetters.contains(letter);
    }

    public static void main(String[] args) {
        System.out.println(isValidGuess("a", List.of('b', 'c')));
        System.out.println(isValidGuess("a", List.of('a')));
        System.out.println(isValidGuess("5", List.of()));
    }
}
