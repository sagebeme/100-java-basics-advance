import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class FlavourMap {

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

    public static String describe(Map<String, List<String>> regions, String region) {
        if (!regions.containsKey(region)) {
            return "Unknown region: " + region;
        }
        return region + ": " + String.join(", ", regions.get(region));
    }

    public static Set<String> allNotes(Map<String, List<String>> regions) {
        Set<String> notes = new TreeSet<>();
        for (List<String> regionNotes : regions.values()) {
            notes.addAll(regionNotes);
        }
        return notes;
    }

    public static List<String> regionsWithNote(Map<String, List<String>> regions, String note) {
        List<String> found = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : regions.entrySet()) {
            for (String n : entry.getValue()) {
                if (n.equalsIgnoreCase(note.trim())) {
                    found.add(entry.getKey());
                }
            }
        }
        return found;
    }

    public static Set<String> sharedNotes(Map<String, List<String>> regions, String a, String b) {
        Set<String> shared = new TreeSet<>();
        if (regions.containsKey(a) && regions.containsKey(b)) {
            shared.addAll(regions.get(a));
            shared.retainAll(regions.get(b));
        }
        return shared;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, List<String>> regions = kenyanRegions();
        System.out.println("=== Flavour Map ===");

        for (String region : regions.keySet()) {
            System.out.println(describe(regions, region));
        }

        System.out.println("Which flavour do you like?");
        List<String> matches = regionsWithNote(regions, scanner.nextLine());
        if (matches.isEmpty()) {
            System.out.println("No region on the map tastes of that yet.");
        } else {
            System.out.println("Try coffee from: " + String.join(", ", matches));
        }

        scanner.close();
    }
}
