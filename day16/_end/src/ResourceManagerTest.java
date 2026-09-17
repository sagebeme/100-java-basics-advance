import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResourceManagerTest {

    @Test
    void reportsEnoughResourcesWhenStockIsSufficient() {
        ResourceManager resources = new ResourceManager(300, 200, 100);
        assertTrue(resources.checkResources(50, 0, 18));
    }

    @Test
    void reportsNotEnoughWaterWhenStockIsLow() {
        ResourceManager resources = new ResourceManager(10, 200, 100);
        assertFalse(resources.checkResources(50, 0, 18));
    }

    @Test
    void useResourcesReducesEachStockByTheAmountUsed() {
        ResourceManager resources = new ResourceManager(300, 200, 100);
        resources.useResources(50, 0, 18);
        assertEquals(250, resources.getWater());
        assertEquals(200, resources.getMilk());
        assertEquals(82, resources.getCoffee());
    }

    @Test
    void addResourcesTopsUpEachStock() {
        ResourceManager resources = new ResourceManager(0, 0, 0);
        resources.addResources(300, 200, 100);
        assertEquals(300, resources.getWater());
        assertEquals(200, resources.getMilk());
        assertEquals(100, resources.getCoffee());
    }
}
