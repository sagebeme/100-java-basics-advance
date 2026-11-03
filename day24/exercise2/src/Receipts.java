import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Receipts {

    // Writes folder/receipt-0007.txt and returns its path. Never overwrites a receipt that's already there.
    public static Path write(Path folder, int number, String customer, Map<String, Integer> items) throws IOException {
        // TODO: make the folder if it's missing; build the lines; write them with CREATE_NEW
        return folder;
    }

    // Adds "0007 300" to the end of the day book, making the file if it's the day's first receipt.
    public static void addToDayBook(Path dayBook, int number, int total) throws IOException {
        // TODO: CREATE and APPEND
    }

    // The name on the left, cut to 15 characters; the amount on the right; 20 characters in all.
    static String line(String name, int shillings) {
        // TODO: String.format("%-15s%5d", ...)
        return "";
    }

    public static void main(String[] args) throws IOException {
        Map<String, Integer> items = new LinkedHashMap<>();
        items.put("Sugar 2kg", 240);
        items.put("Bread", 60);
        Path receipt = write(Path.of("receipts"), 7, "Otieno", items);
        addToDayBook(Path.of("receipts", "daybook.txt"), 7, 300);
        System.out.println(Files.readString(receipt));
    }
}
