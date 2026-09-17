public class Exercise2 {

    public static double add(double a, double b) { return a + b; }
    public static double subtract(double a, double b) { return a - b; }
    public static double multiply(double a, double b) { return a * b; }
    public static double divide(double a, double b) { return a / b; }
    public static double modulo(double a, double b) { return a % b; }
    public static double power(double base, double exponent) { return Math.pow(base, exponent); }
    public static double squareRoot(double value) { return Math.sqrt(value); }

    public static void main(String[] args) {
        System.out.println("add: " + add(4, 2));
        System.out.println("subtract: " + subtract(4, 2));
        System.out.println("multiply: " + multiply(4, 2));
        System.out.println("divide: " + divide(4, 2));
        System.out.println("modulo: " + modulo(5, 2));
        System.out.println("power: " + power(2, 10));
        System.out.println("squareRoot: " + squareRoot(16));
    }
}
