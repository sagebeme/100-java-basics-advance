import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {

    public static int countWords(String line) {
        String trimmed = line.trim();
        if (trimmed.isEmpty()) return 0;
        return trimmed.split("\s+").length;
    }

    public static boolean containsWord(String line, String word) {
        return line.toLowerCase().contains(word.toLowerCase());
    }

    public static void main(String[] args) {
        try {
            File file = new File("sample.txt");
            Scanner scanner = new Scanner(file);

            int lineCount = 0;
            int wordCount = 0;
            String searchWord = "Java";
            int wordOccurrences = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineCount++;
                wordCount += countWords(line);
                if (containsWord(line, searchWord)) {
                    wordOccurrences++;
                }
            }

            System.out.println("Total lines: " + lineCount);
            System.out.println("Total words: " + wordCount);
            System.out.println("Occurrences of '" + searchWord + "': " + wordOccurrences);

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
}
