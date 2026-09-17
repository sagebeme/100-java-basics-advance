import java.util.Scanner;

public class Exercise1 {

    public static String classifySign(int number) {
        if (number > 0) return "positive";
        if (number < 0) return "negative";
        return "zero";
    }

    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a number: ");
        int number = scanner.nextInt();

        System.out.println("The number is " + classifySign(number));
        System.out.println("The number is " + (isEven(number) ? "even" : "odd"));

        scanner.close();
    }
}
