public class Matatu extends Ride {

    public Matatu() {
        super("Matatu", 14);
    }

    // KES 50 a person for up to 10 km, then KES 5 a person for every km after that.
    @Override
    public int fare(int km, int passengers) {
        int perPerson = 50 + 5 * Math.max(0, km - 10);
        return perPerson * passengers;
    }
}
