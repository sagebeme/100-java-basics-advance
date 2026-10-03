import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FarmerPayoutTest {

    @Test
    void grossIsKilosTimesTheRate() {
        assertEquals(44200, FarmerPayout.grossPay(520, 85), 0.0001);
    }

    @Test
    void netTakesOffTheLevyAndTheAdvance() {
        assertEquals(36990, FarmerPayout.netPay(520, 85, 5, 5000), 0.0001);
    }

    @Test
    void aBigAdvanceLeavesNothingButNeverLessThanNothing() {
        assertEquals(0, FarmerPayout.netPay(10, 85, 5, 5000), 0.0001);
    }

    @Test
    void fillsTheCallersArray() {
        double[] payouts = new double[3];
        FarmerPayout.fillPayouts(new double[]{100, 200, 0}, 50, 10, payouts);
        assertArrayEquals(new double[]{4500, 9000, 0}, payouts, 0.0001);
    }

    @Test
    void printsAFiveLinePayslip() {
        assertEquals("Payslip for Wanjiru\n"
                        + "Cherry: 520.0 kg at KES 85.00 = KES 44,200.00\n"
                        + "Levy (5.0%): KES 2,210.00\n"
                        + "Advance repaid: KES 5,000.00\n"
                        + "To pay: KES 36,990.00",
                FarmerPayout.payslip("Wanjiru", 520, 85, 5, 5000));
    }
}
