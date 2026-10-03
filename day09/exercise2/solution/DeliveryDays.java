import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeliveryDays {

    public static Set<String> bothDays(List<String> monday, List<String> tuesday) {
        Set<String> both = new HashSet<>(monday);
        both.retainAll(tuesday);
        return both;
    }

    public static Set<String> onlyTuesday(List<String> monday, List<String> tuesday) {
        Set<String> newFaces = new HashSet<>(tuesday);
        newFaces.removeAll(monday);
        return newFaces;
    }

    public static void main(String[] args) {
        List<String> monday = List.of("Wanjiru", "Otieno", "Wanjiru", "Achieng");
        List<String> tuesday = List.of("Otieno", "Kip", "Kip", "Wanjiru");
        System.out.println("Both days: " + bothDays(monday, tuesday));
        System.out.println("New on Tuesday: " + onlyTuesday(monday, tuesday));
    }
}
