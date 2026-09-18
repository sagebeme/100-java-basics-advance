import java.io.IOException;

public class FileWriter {

    public static String formatDataFileContent(int fileNumber) {
        return String.format("This is data file %d%n", fileNumber);
    }

    public static void main(String[] args) {
        try {
            java.io.FileWriter writer = new java.io.FileWriter("output.txt");
            writer.write("Hello, World!\n");
            writer.write("This is line 2.\n");
            writer.close();
            System.out.println("File written successfully!");
        } catch (IOException e) {
            System.out.println("Error writing file!");
        }

        try {
            java.io.FileWriter appendWriter = new java.io.FileWriter("output.txt", true);
            appendWriter.write("This is appended text.\n");
            appendWriter.close();
            System.out.println("Text appended successfully!");
        } catch (IOException e) {
            System.out.println("Error appending to file!");
        }

        for (int i = 1; i <= 3; i++) {
            try {
                java.io.FileWriter fileWriter = new java.io.FileWriter("data" + i + ".txt");
                fileWriter.write(formatDataFileContent(i));
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error creating file " + i);
            }
        }
    }
}
