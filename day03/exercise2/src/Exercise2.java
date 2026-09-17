import java.util.Scanner;

public class Exercise2 {

    public static boolean canVote(int age) {
        return age >= 18;
    }

    public static boolean canDrive(int age, boolean hasLicense) {
        return age >= 16 && hasLicense;
    }

    public static boolean getsDiscount(boolean isStudent, boolean isSenior) {
        return isStudent || isSenior;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your age: ");
        int age = scanner.nextInt();

        System.out.print("Do you have a license? (true/false): ");
        boolean hasLicense = scanner.nextBoolean();

        System.out.print("Are you a student? (true/false): ");
        boolean isStudent = scanner.nextBoolean();

        System.out.print("Are you a senior? (true/false): ");
        boolean isSenior = scanner.nextBoolean();

        System.out.println("Can vote: " + canVote(age));
        System.out.println("Can drive: " + canDrive(age, hasLicense));
        System.out.println("Gets discount: " + getsDiscount(isStudent, isSenior));

        scanner.close();
    }
}
