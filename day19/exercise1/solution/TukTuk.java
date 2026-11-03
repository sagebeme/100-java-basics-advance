public class TukTuk extends Ride {

    public TukTuk() {
        super("Tuk-tuk", 3);
    }

    // KES 100 to start, plus KES 20 a km, however many of the 3 seats are used.
    @Override
    public int fare(int km, int passengers) {
        return 100 + 20 * km;
    }
}
