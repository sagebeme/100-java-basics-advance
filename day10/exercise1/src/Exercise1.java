public class Exercise1 {

    public static int sum(int a, int b) {
        return a + b;
    }

    public static int product(int a, int b, int c) {
        return a * b * c;
    }

    public static int largest(int a, int b) {
        return Math.max(a, b);
    }

    public static String formatted(String label, double value) {
        return label + ": " + String.format("%.2f", value);
    }

    public static boolean isPrime(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static boolean isEven(int n) {
        return n % 2 == 0;
    }

    public static void main(String[] args) {
        System.out.println(sum(2, 3));
        System.out.println(product(2, 3, 4));
        System.out.println(largest(2, 3));
        System.out.println(formatted("Total", 9.5));
        System.out.println(isPrime(7));
        System.out.println(isEven(7));
    }
}
