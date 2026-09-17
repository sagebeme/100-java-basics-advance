public class TypeConversion {

    public static int parseStringToInt(String str) {
        return Integer.parseInt(str);
    }

    public static String intToString(int number) {
        return String.valueOf(number);
    }

    public static int doubleToInt(double decimal) {
        return (int) decimal;
    }

    public static double intToDouble(int value) {
        return value;
    }

    public static void main(String[] args) {
        String str = "123";
        int num = parseStringToInt(str);

        int number = 456;
        String numStr = intToString(number);

        double decimal = 3.14;
        int integer = doubleToInt(decimal);

        int value = 100;
        double doubleValue = intToDouble(value);

        System.out.println("String \"123\" to int: " + num);
        System.out.println("int 456 to String: " + numStr);
        System.out.println("double 3.14 to int: " + integer);
        System.out.println("int 100 to double: " + doubleValue);
    }
}
