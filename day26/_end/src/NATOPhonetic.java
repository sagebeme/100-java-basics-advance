import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class NATOPhonetic {
    private static final Map<Character, String> natoAlphabet = new HashMap<>();
    
    static {
        natoAlphabet.put('A', "Alpha");
        natoAlphabet.put('B', "Bravo");
        natoAlphabet.put('C', "Charlie");
        natoAlphabet.put('D', "Delta");
        natoAlphabet.put('E', "Echo");
        natoAlphabet.put('F', "Foxtrot");
        natoAlphabet.put('G', "Golf");
        natoAlphabet.put('H', "Hotel");
        natoAlphabet.put('I', "India");
        natoAlphabet.put('J', "Juliet");
        natoAlphabet.put('K', "Kilo");
        natoAlphabet.put('L', "Lima");
        natoAlphabet.put('M', "Mike");
        natoAlphabet.put('N', "November");
        natoAlphabet.put('O', "Oscar");
        natoAlphabet.put('P', "Papa");
        natoAlphabet.put('Q', "Quebec");
        natoAlphabet.put('R', "Romeo");
        natoAlphabet.put('S', "Sierra");
        natoAlphabet.put('T', "Tango");
        natoAlphabet.put('U', "Uniform");
        natoAlphabet.put('V', "Victor");
        natoAlphabet.put('W', "Whiskey");
        natoAlphabet.put('X', "X-ray");
        natoAlphabet.put('Y', "Yankee");
        natoAlphabet.put('Z', "Zulu");
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a word: ");
        String word = scanner.nextLine().toUpperCase();
        
        // Convert using streams and lambdas
        String result = word.chars()
            .mapToObj(c -> (char) c)
            .filter(Character::isLetter)
            .map(c -> c + " for " + natoAlphabet.get(c))
            .collect(Collectors.joining("\n"));
        
        System.out.println(result);
        
        scanner.close();
    }
}

