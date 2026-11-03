public class Boda extends Ride {

    public Boda() {
        super("Boda", 1);
    }

    // KES 50 to start, plus KES 30 a km.
    @Override
    public int fare(int km, int passengers) {
        return 50 + 30 * km;
    }
}
