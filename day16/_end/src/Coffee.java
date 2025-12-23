public class Coffee {
    private String name;
    private int water;
    private int milk;
    private int coffee;
    private double cost;
    
    public Coffee(String name, int water, int milk, int coffee, double cost) {
        this.name = name;
        this.water = water;
        this.milk = milk;
        this.coffee = coffee;
        this.cost = cost;
    }
    
    public String getName() {
        return name;
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
    
    public double getCost() {
        return cost;
    }
}

