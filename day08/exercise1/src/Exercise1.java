public class Exercise1 {

    public static String describePerson(String name, int age, double heightInMeters) {
        return name + " is " + age + " years old and " + heightInMeters + "m tall.";
    }

    /**
     * Divides a by b, returning 0 instead of throwing when b is zero (an edge case).
     */
    public static double safeDivide(double a, double b) {
        if (b == 0) {
            return 0;
        }
        return a / b;
    }

    public static void main(String[] args) {
        System.out.println(describePerson("Amina", 30, 1.68));
        System.out.println("10 / 2 = " + safeDivide(10, 2));
        System.out.println("10 / 0 = " + safeDivide(10, 0));
    }
}
