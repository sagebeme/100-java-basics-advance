import java.time.LocalDate;

public record Fare(String to, String city, LocalDate depart, LocalDate back, int priceKes, String airline, int stops) {

    public String route() {
        return stops == 0 ? "direct" : stops + (stops == 1 ? " stop" : " stops");
    }
}
