import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RideTest {

    private final Ride matatu = new Matatu();
    private final Ride boda = new Boda();
    private final Ride tukTuk = new TukTuk();

    @Test
    void eachRideHasItsNameAndSeats() {
        assertEquals("Matatu", matatu.getName());
        assertEquals(14, matatu.getSeats());
        assertEquals("Boda", boda.getName());
        assertEquals(1, boda.getSeats());
        assertEquals("Tuk-tuk", tukTuk.getName());
        assertEquals(3, tukTuk.getSeats());
    }

    @Test
    void aMatatuChargesEachPassenger() {
        assertEquals(50, matatu.fare(4, 1));
        assertEquals(50, matatu.fare(10, 1));
        assertEquals(55, matatu.fare(11, 1));
        assertEquals(300, matatu.fare(12, 5));
    }

    @Test
    void aBodaChargesForTheTrip() {
        assertEquals(80, boda.fare(1, 1));
        assertEquals(170, boda.fare(4, 1));
    }

    @Test
    void aTukTukChargesForTheTripHoweverManyRide() {
        assertEquals(140, tukTuk.fare(2, 1));
        assertEquals(140, tukTuk.fare(2, 3));
    }

    @Test
    void onlySoManyFit() {
        assertTrue(boda.fits(1));
        assertFalse(boda.fits(2));
        assertTrue(tukTuk.fits(3));
        assertFalse(tukTuk.fits(4));
    }

    @Test
    void aQuoteUsesTheRealRidesFare() {
        assertEquals("Boda: KES 170", boda.quote(4, 1));
        assertEquals("Tuk-tuk: KES 140", tukTuk.quote(2, 3));
    }

    @Test
    void picksTheCheapestRideThatFits() {
        List<Ride> rides = List.of(boda, tukTuk, matatu);
        assertSame(matatu, Ride.cheapest(rides, 4, 1));
        assertSame(tukTuk, Ride.cheapest(rides, 2, 3));
        assertSame(boda, Ride.cheapest(List.of(boda, tukTuk), 1, 1));
    }

    @Test
    void aTieGoesToTheFirstInTheList() {
        Ride otherTukTuk = new TukTuk();
        assertSame(tukTuk, Ride.cheapest(List.of(tukTuk, otherTukTuk), 2, 2));
    }

    @Test
    void nothingFitsATooBigGroup() {
        assertNull(Ride.cheapest(List.of(boda, tukTuk), 5, 4));
    }
}
