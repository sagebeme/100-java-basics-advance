import java.util.ArrayList;

public class StationQueue {

    public static void join(ArrayList<String> queue, String name) {
        // TODO: add to the back, but only if they aren't already in the queue
    }

    public static String serveNext(ArrayList<String> queue) {
        // TODO: remove and return the front person, or "nobody"
        return "";
    }

    public static int positionOf(ArrayList<String> queue, String name) {
        // TODO: 1 for the front of the queue, -1 if they're not in it
        return 0;
    }

    public static void main(String[] args) {
        ArrayList<String> queue = new ArrayList<>();
        join(queue, "Wanjiru");
        join(queue, "Otieno");
        System.out.println("Otieno is number " + positionOf(queue, "Otieno"));
        System.out.println("Serving " + serveNext(queue));
    }
}
