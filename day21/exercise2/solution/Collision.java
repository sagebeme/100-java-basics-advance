import java.util.List;

public class Collision {

    // "WALL", "SELF", "FOOD" or "CLEAR" for the square the head is about to enter.
    public static String check(String next, List<String> body, String food, int cols, int rows, boolean growing) {
        String[] parts = next.split(",");
        int col = Integer.parseInt(parts[0]);
        int row = Integer.parseInt(parts[1]);
        if (col < 0 || col >= cols || row < 0 || row >= rows) {
            return "WALL";
        }
        // On a normal move the tail leaves its square as the head arrives, so it doesn't count.
        List<String> inTheWay = growing ? body : body.subList(0, body.size() - 1);
        if (inTheWay.contains(next)) {
            return "SELF";
        }
        if (next.equals(food)) {
            return "FOOD";
        }
        return "CLEAR";
    }

    public static void main(String[] args) {
        List<String> body = List.of("2,1", "2,2", "1,2", "1,1");
        System.out.println(check("1,1", body, "7,7", 10, 10, false));
        System.out.println(check("1,1", body, "7,7", 10, 10, true));
    }
}
