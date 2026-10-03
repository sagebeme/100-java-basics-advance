import java.util.ArrayList;

public class StationQueue {

    public static void join(ArrayList<String> queue, String name) {
        if (!queue.contains(name)) {
            queue.add(name);
        }
    }

    public static String serveNext(ArrayList<String> queue) {
        if (queue.isEmpty()) {
            return "nobody";
        }
        return queue.remove(0);
    }

    public static int positionOf(ArrayList<String> queue, String name) {
        int index = queue.indexOf(name);
        return index == -1 ? -1 : index + 1;
    }

    public static void main(String[] args) {
        ArrayList<String> queue = new ArrayList<>();
        join(queue, "Wanjiru");
        join(queue, "Otieno");
        System.out.println("Otieno is number " + positionOf(queue, "Otieno"));
        System.out.println("Serving " + serveNext(queue));
    }
}
