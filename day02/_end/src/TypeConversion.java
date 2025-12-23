public class TypeConversion {
    public static void main(String[] args) {
        // Convert String "123" to int
        String str = "123";
        int num = Integer.parseInt(str);
        
        // Convert int 456 to String
        int number = 456;
        String numStr = String.valueOf(number);
        
        // Convert double 3.14 to int (with casting)
        double decimal = 3.14;
        int integer = (int) decimal;
        
        // Convert int 100 to double
        int value = 100;
        double doubleValue = value;
        
        // Print all results
        System.out.println("String \"123\" to int: " + num);
        System.out.println("int 456 to String: \"" + numStr + "\"");
        System.out.println("double 3.14 to int: " + integer);
        System.out.println("int 100 to double: " + doubleValue);
    }
}

