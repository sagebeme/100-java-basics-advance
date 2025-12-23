public class ResourceManager {
    private int water;
    private int milk;
    private int coffee;
    
    public ResourceManager(int water, int milk, int coffee) {
        this.water = water;
        this.milk = milk;
        this.coffee = coffee;
    }
    
    public boolean checkResources(int waterNeeded, int milkNeeded, int coffeeNeeded) {
        if (water < waterNeeded) {
            System.out.println("Sorry, there is not enough water.");
            return false;
        }
        if (milk < milkNeeded) {
            System.out.println("Sorry, there is not enough milk.");
            return false;
        }
        if (coffee < coffeeNeeded) {
            System.out.println("Sorry, there is not enough coffee.");
            return false;
        }
        return true;
    }
    
    public void useResources(int waterUsed, int milkUsed, int coffeeUsed) {
        water -= waterUsed;
        milk -= milkUsed;
        coffee -= coffeeUsed;
    }
    
    public void addResources(int waterAdded, int milkAdded, int coffeeAdded) {
        water += waterAdded;
        milk += milkAdded;
        coffee += coffeeAdded;
    }
    
    public void displayReport() {
        System.out.println("Water: " + water + "ml");
        System.out.println("Milk: " + milk + "ml");
        System.out.println("Coffee: " + coffee + "g");
    }
    
    public int getWater() {
        return water;
    }
    
    public int getMilk() {
        return milk;
    }
    
    public int getCoffee() {
        return coffee;
    }
}

