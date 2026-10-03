import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class CheapestFares {

    public record SimpleFare(String city, int priceKes, String airline) {}

    public static Map<String, SimpleFare> cheapestPerCity(List<SimpleFare> fares) {
        return fares.stream().collect(Collectors.groupingBy(
                SimpleFare::city,
                TreeMap::new,
                Collectors.collectingAndThen(
                        Collectors.minBy(Comparator.comparingInt(SimpleFare::priceKes)),
                        Optional::get)));
    }

    public static void main(String[] args) {
        System.out.println(cheapestPerCity(List.of(
                new SimpleFare("Mombasa", 7900, "Twiga Air"),
                new SimpleFare("Kisumu", 4800, "Ndege Link"),
                new SimpleFare("Mombasa", 6200, "Ndege Link"))));
    }
}
