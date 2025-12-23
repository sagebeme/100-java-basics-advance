import java.util.Scanner;

public class CaesarCipher {
    public static String encode(String message, int shift) {
        StringBuilder encoded = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                char shifted = (char) ((c - base + shift) % 26 + base);
                encoded.append(shifted);
            } else {
                encoded.append(c);
            }
        }
        return encoded.toString();
    }
    
    public static String decode(String message, int shift) {
        return encode(message, 26 - shift); // Decode is encode with reverse shift
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Caesar Cipher");
        System.out.println("Type 'encode' to encrypt, type 'decode' to decrypt:");
        String direction = scanner.nextLine().toLowerCase();
        
        System.out.println("Type your message:");
        String message = scanner.nextLine();
        
        System.out.println("Type the shift number:");
        int shift = scanner.nextInt();
        
        if (direction.equals("encode")) {
            System.out.println("Encoded message: " + encode(message, shift));
        } else if (direction.equals("decode")) {
            System.out.println("Decoded message: " + decode(message, shift));
        } else {
            System.out.println("Invalid direction!");
        }
        
        scanner.close();
    }
}

