import java.util.List;

// Every way to get across town. Each kind of ride works out its own fare.
public abstract class Ride {

    private final String name;
    private final int seats;

    protected Ride(String name, int seats) {
        this.name = name;
        this.seats = seats;
    }

    public String getName() {
        return name;
    }

    public int getSeats() {
        return seats;
    }

    public boolean fits(int passengers) {
        return passengers <= seats;
    }

    // What the whole group pays, in shillings, for a trip of km kilometres.
    public abstract int fare(int km, int passengers);

    // Works for every kind of ride: Java calls the fare method of the real object.
    public String quote(int km, int passengers) {
        return name + ": KES " + fare(km, passengers);
    }

    // The ride that fits the group and costs least. On a tie, the one earlier in the list. None fits: null.
    public static Ride cheapest(List<Ride> rides, int km, int passengers) {
        // TODO
        return null;
    }
}
