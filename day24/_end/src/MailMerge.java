import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class MailMerge {
    public static void main(String[] args) {
        try {
            // Read template
            File templateFile = new File("template.txt");
            Scanner templateScanner = new Scanner(templateFile);
            StringBuilder template = new StringBuilder();
            while (templateScanner.hasNextLine()) {
                template.append(templateScanner.nextLine()).append("\n");
            }
            templateScanner.close();
            
            // Read names
            File namesFile = new File("names.txt");
            Scanner namesScanner = new Scanner(namesFile);
            
            int count = 1;
            while (namesScanner.hasNextLine()) {
                String name = namesScanner.nextLine();
                
                // Replace placeholder
                String output = template.toString().replace("[name]", name);
                
                // Write to file
                FileWriter writer = new FileWriter("output_" + count + ".txt");
                writer.write(output);
                writer.close();
                
                count++;
            }
            namesScanner.close();
            
            System.out.println("Mail merge complete! Created " + (count - 1) + " files.");
            
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}

