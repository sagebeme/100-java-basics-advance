import java.util.Scanner;

public class StringManipulation {

    public static String firstWord(String sentence) {
        if (sentence.contains(" ")) {
            return sentence.substring(0, sentence.indexOf(" "));
        }
        return sentence;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a sentence: ");
        String sentence = scanner.nextLine();

        String upper = sentence.toUpperCase();
        String lower = sentence.toLowerCase();
        int length = sentence.length();
        String firstWord = firstWord(sentence);

        System.out.println("Uppercase: " + upper);
        System.out.println("Lowercase: " + lower);
        System.out.println("Character count: " + length);
        System.out.println("First word: " + firstWord);

        scanner.close();
    }
}
