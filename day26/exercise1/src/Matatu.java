// One matatu in the Sacco's fleet. Already done.
public class Matatu {

    private final String plate;
    private final String route;
    private final int seats;
    private final double rating;

    public Matatu(String plate, String route, int seats, double rating) {
        this.plate = plate;
        this.route = route;
        this.seats = seats;
        this.rating = rating;
    }

    public String getPlate() {
        return plate;
    }

    public String getRoute() {
        return route;
    }

    public int getSeats() {
        return seats;
    }

    public double getRating() {
        return rating;
    }
}
