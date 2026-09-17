public class Exercise1 {

    public static long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static int[] multiplicationTable(int number, int upTo) {
        int[] table = new int[upTo];
        for (int i = 1; i <= upTo; i++) {
            table[i - 1] = number * i;
        }
        return table;
    }

    public static void main(String[] args) {
        System.out.println("Numbers 1 to 100:");
        for (int i = 1; i <= 100; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println("Even numbers 2 to 50:");
        for (int i = 2; i <= 50; i += 2) {
            System.out.print(i + " ");
        }
        System.out.println();

        int number = 7;
        System.out.println("Multiplication table for " + number + ":");
        for (int value : multiplicationTable(number, 10)) {
            System.out.print(value + " ");
        }
        System.out.println();

        System.out.println("5! = " + factorial(5));
    }
}
