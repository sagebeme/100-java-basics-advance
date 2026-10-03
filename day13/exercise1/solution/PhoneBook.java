import java.util.Map;

public class PhoneBook {

    public static String phoneFor(Map<String, String> book, String farmer) {
        for (String name : book.keySet()) {
            if (name.equalsIgnoreCase(farmer.trim())) {
                return book.get(name).trim();
            }
        }
        return "no number on file"; // get() would have returned null here
    }

    public static void main(String[] args) {
        Map<String, String> book = Map.of(
                "Wanjiru", "0712 345 678 ",
                "Otieno", " 0722 111 222");
        System.out.println(phoneFor(book, "Wanjiru"));
        System.out.println(phoneFor(book, "Achieng"));
    }
}
