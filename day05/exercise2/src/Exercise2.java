import java.util.Scanner;

public class Exercise2 {

    public static int sumUntilZero(int[] inputs) {
        int sum = 0;
        for (int value : inputs) {
            if (value == 0) break;
            sum += value;
        }
        return sum;
    }

    public static int firstDivisibleBySevenAndEleven(int from) {
        int n = from;
        while (n % 7 != 0 || n % 11 != 0) {
            n++;
        }
        return n;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Counting down:");
        for (int i = 10; i >= 1; i--) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println("Enter numbers to sum, 0 to stop:");
        int sum = 0;
        while (true) {
            int value = scanner.nextInt();
            if (value == 0) break;
            sum += value;
        }
        System.out.println("Sum: " + sum);

        System.out.println("First number >= 1 divisible by both 7 and 11: " + firstDivisibleBySevenAndEleven(1));

        scanner.close();
    }
}
