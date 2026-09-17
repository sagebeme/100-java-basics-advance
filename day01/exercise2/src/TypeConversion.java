public class TypeConversion {

    public static double intToDouble(int number) {
        return number;
    }

    public static int doubleToInt(double decimal) {
        return (int) decimal;
    }

    public static int stringToInt(String numberString) {
        return Integer.parseInt(numberString);
    }

    public static void main(String[] args) {
        // Convert int to double
        int number = 10;
        double asDouble = intToDouble(number);

        // Convert double to int (casting)
        double decimal = 15.7;
        int asInt = doubleToInt(decimal);

        // Convert String to int
        String numberString = "25";
        int parsed = stringToInt(numberString);

        // Print results
        System.out.println("int to double: " + number + " -> " + asDouble);
        System.out.println("double to int: " + decimal + " -> " + asInt);
        System.out.println("String to int: \"" + numberString + "\" -> " + parsed);
    }
}
