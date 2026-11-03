import java.util.List;

public class Collision {

    // "WALL", "SELF", "FOOD" or "CLEAR" for the square the head is about to enter.
    public static String check(String next, List<String> body, String food, int cols, int rows, boolean growing) {
        // TODO: wall first, then self (mind the tail), then food
        return "";
    }

    public static void main(String[] args) {
        List<String> body = List.of("2,1", "2,2", "1,2", "1,1");
        System.out.println(check("1,1", body, "7,7", 10, 10, false));
        System.out.println(check("1,1", body, "7,7", 10, 10, true));
    }
}
