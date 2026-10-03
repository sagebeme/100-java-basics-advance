import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeighbridgeTest {

    private final double[] gross = {9120.5, 8450.0, 9900.0};
    private final double[] tare = {5200.0, 5150.0, 5210.0};

    @Test
    void netIsGrossMinusTare() {
        assertEquals(3920.5, Weighbridge.netKg(9120.5, 5200), 0.0001);
    }

    @Test
    void totalsEveryLorryWithoutRunningOffTheEnd() {
        assertEquals(11910.5, Weighbridge.totalNet(gross, tare), 0.0001);
    }

    @Test
    void matchesFactoryNamesByTextNotByObject() {
        String[] factories = {"Othaya", "Karindundu", "Othaya"};
        double[] net = {3920.5, 3300.0, 4690.0};
        // new String(...) makes a different object with the same text, like a name typed at the keyboard
        assertEquals(8610.5, Weighbridge.totalFor(factories, net, new String("Othaya")), 0.0001);
    }

    @Test
    void averageKeepsTheDecimals() {
        assertEquals(3970.1666, Weighbridge.averageNet(new double[]{3920.5, 3300.0, 4690.0}), 0.001);
        assertEquals(0.5, Weighbridge.averageNet(new double[]{0.5}), 0.0001);
    }
}
