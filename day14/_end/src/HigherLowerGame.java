import java.util.Scanner;
import java.util.Random;
import java.util.HashMap;

public class HigherLowerGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        HashMap<String, Integer> data = new HashMap<>();
        data.put("Instagram", 500);
        data.put("Cristiano Ronaldo", 400);
        data.put("Ariana Grande", 300);
        data.put("Dwayne Johnson", 200);
        data.put("Selena Gomez", 250);
        
        int score = 0;
        boolean gameOver = false;
        String previousPerson = "";
        int previousValue = 0;
        
        while (!gameOver) {
            String[] people = data.keySet().toArray(new String[0]);
            String personA = people[random.nextInt(people.length)];
            
            if (previousPerson.isEmpty()) {
                previousPerson = personA;
                previousValue = data.get(personA);
            }
            
            String personB = people[random.nextInt(people.length)];
            while (personB.equals(personA)) {
                personB = people[random.nextInt(people.length)];
            }
            
            System.out.println("Compare A: " + personA + " with " + data.get(personA) + " followers");
            System.out.println("Against B: " + personB + " with ? followers");
            System.out.print("Who has more followers? Type 'A' or 'B': ");
            
            String guess = scanner.nextLine().toUpperCase();
            int valueA = data.get(personA);
            int valueB = data.get(personB);
            
            boolean correct = false;
            if (guess.equals("A") && valueA > valueB) {
                correct = true;
            } else if (guess.equals("B") && valueB > valueA) {
                correct = true;
            }
            
            if (correct) {
                score++;
                System.out.println("You're right! Current score: " + score);
                previousPerson = personB;
                previousValue = valueB;
            } else {
                System.out.println("Sorry, that's wrong. Final score: " + score);
                gameOver = true;
            }
        }
        
        scanner.close();
    }
}

