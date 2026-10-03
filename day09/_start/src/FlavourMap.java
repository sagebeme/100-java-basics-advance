import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class FlavourMap {

    // Typical tasting notes. LinkedHashMap keeps the regions in this order.
    public static Map<String, List<String>> kenyanRegions() {
        Map<String, List<String>> regions = new LinkedHashMap<>();
        regions.put("Nyeri", List.of("blackcurrant", "grapefruit", "brown sugar"));
        regions.put("Kirinyaga", List.of("blackberry", "citrus", "honey"));
        regions.put("Kiambu", List.of("lemon", "black tea", "caramel"));
        regions.put("Murang'a", List.of("red currant", "plum", "honey"));
        regions.put("Embu", List.of("orange", "molasses", "black tea"));
        regions.put("Kisii", List.of("milk chocolate", "red apple", "caramel"));
        return regions;
    }

    // "Nyeri: blackcurrant, grapefruit, brown sugar", or "Unknown region: Mombasa"
    public static String describe(Map<String, List<String>> regions, String region) {
        // TODO
        return "";
    }

    // Every note once, sorted
    public static Set<String> allNotes(Map<String, List<String>> regions) {
        // TODO: a TreeSet
        return new TreeSet<>();
    }

    // Regions whose notes include the note, ignoring capitals, in the map's order
    public static List<String> regionsWithNote(Map<String, List<String>> regions, String note) {
        // TODO
        return List.of();
    }

    // Notes both regions have, sorted. Empty if either region is unknown.
    public static Set<String> sharedNotes(Map<String, List<String>> regions, String a, String b) {
        // TODO: copy one region's notes into a TreeSet, then retainAll
        return new TreeSet<>();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, List<String>> regions = kenyanRegions();
        System.out.println("=== Flavour Map ===");

        // TODO: describe every region
        // TODO: ask "Which flavour do you like?" and print "Try coffee from: ..." (or "No region on the map tastes of that yet.")

        scanner.close();
    }
}
