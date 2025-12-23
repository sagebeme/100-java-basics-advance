public class DebuggingChallenge {
    public static void main(String[] args) {
        // Fixed: Added semicolons, proper String declaration
        
        int number = 5;  // Fixed: Added semicolon
        String message = "Hello World";  // Fixed: Added semicolon and proper String type
        
        for (int i = 0; i < number; i++) {
            System.out.println(message);  // Fixed: Added semicolon
        }
        
        if (number > 3) {
            System.out.println("Number is greater than 3");  // Fixed: Added semicolon
        } else {
            System.out.println("Number is less than or equal to 3");  // Fixed: Added semicolon
        }
    }
}

