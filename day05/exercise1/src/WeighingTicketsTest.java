import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeighingTicketsTest {

    @Test
    void numbersEveryTicketFromFirstToLast() {
        assertEquals("T007 T008 T009 T010", WeighingTickets.ticketNumbers(7, 10));
    }

    @Test
    void aSingleTicketHasNoSpaces() {
        assertEquals("T120", WeighingTickets.ticketNumbers(120, 120));
    }

    @Test
    void addsUpTheDeliveries() {
        assertEquals(40.75, WeighingTickets.totalKg(new double[]{12.5, 8.0, 20.25}), 0.0001);
    }

    @Test
    void noDeliveriesWeighNothing() {
        assertEquals(0, WeighingTickets.totalKg(new double[]{}), 0.0001);
    }
}
