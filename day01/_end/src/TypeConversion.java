public class TypeConversion {
    public static void main(String[] args) {
        // Convert int to double (widening conversion - automatic)
        int number = 10;
        double numberAsDouble = number; // Automatic conversion
        System.out.println("Int to double: " + number + " -> " + numberAsDouble);
        
        // Convert double to int (narrowing conversion - requires casting)
        double decimal = 15.7;
        int decimalAsInt = (int) decimal; // Explicit cast - truncates decimal part
        System.out.println("Double to int: " + decimal + " -> " + decimalAsInt);
        
        // Convert String to int (parsing)
        String numberString = "25";
        int parsedNumber = Integer.parseInt(numberString);
        System.out.println("String to int: \"" + numberString + "\" -> " + parsedNumber);
        
        // Additional examples
        System.out.println("\n--- Additional Conversions ---");
        float floatValue = 3.14f;
        int floatToInt = (int) floatValue;
        System.out.println("Float to int: " + floatValue + " -> " + floatToInt);
        
        int intValue = 42;
        String intToString = String.valueOf(intValue);
        System.out.println("Int to String: " + intValue + " -> \"" + intToString + "\"");
    }
}

