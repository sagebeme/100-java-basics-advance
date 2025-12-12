import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    public static void main(String[] args) {
        try {
            File file = new File("sample.txt");
            Scanner scanner = new Scanner(file);
            
            int lineCount = 0;
            int wordCount = 0;
            String searchWord = "Java";
            int wordOccurrences = 0;
            
            // TODO: Read file line by line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                lineCount++;
                
                // TODO: Count words in line
                // String[] words = line.split("\\s+");
                // wordCount += words.length;
                
                // TODO: Search for specific word
                // if (line.toLowerCase().contains(searchWord.toLowerCase())) {
                //     wordOccurrences++;
                // }
            }
            
            // TODO: Print statistics
            // System.out.println("Total lines: " + lineCount);
            // System.out.println("Total words: " + wordCount);
            // System.out.println("Occurrences of '" + searchWord + "': " + wordOccurrences);
            
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
}

