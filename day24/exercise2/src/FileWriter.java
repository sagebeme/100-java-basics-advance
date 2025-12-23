import java.io.FileWriter;
import java.io.IOException;

public class FileWriter {
    public static void main(String[] args) {
        // TODO: Write to a new file
        try {
            FileWriter writer = new FileWriter("output.txt");
            writer.write("Hello, World!\n");
            writer.write("This is line 2.\n");
            writer.close();
            System.out.println("File written successfully!");
        } catch (IOException e) {
            System.out.println("Error writing file!");
        }
        
        // TODO: Append to existing file
        try {
            FileWriter appendWriter = new FileWriter("output.txt", true);
            appendWriter.write("This is appended text.\n");
            appendWriter.close();
            System.out.println("Text appended successfully!");
        } catch (IOException e) {
            System.out.println("Error appending to file!");
        }
        
        // TODO: Create multiple files with formatted output
        // for (int i = 1; i <= 3; i++) {
        //     try {
        //         FileWriter fileWriter = new FileWriter("data" + i + ".txt");
        //         fileWriter.write(String.format("This is data file %d\n", i));
        //         fileWriter.close();
        //     } catch (IOException e) {
        //         System.out.println("Error creating file " + i);
        //     }
        // }
    }
}






