import java.util.Map;

public class PhoneBook {

    public static String phoneFor(Map<String, String> book, String farmer) {
        return book.get(farmer).trim();
    }

    public static void main(String[] args) {
        Map<String, String> book = Map.of(
                "Wanjiru", "0712 345 678 ",
                "Otieno", " 0722 111 222");
        System.out.println(phoneFor(book, "Wanjiru"));
        System.out.println(phoneFor(book, "Achieng"));
    }
}
