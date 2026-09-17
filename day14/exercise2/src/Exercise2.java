public class Exercise2 {

    public static String determineWinner(String nameA, int valueA, String nameB, int valueB) {
        return valueA >= valueB ? nameA : nameB;
    }

    public static boolean isValidGuess(String guess) {
        return guess != null && (guess.equalsIgnoreCase("A") || guess.equalsIgnoreCase("B"));
    }

    public static String formatComparison(String nameA, String nameB) {
        return "Compare A: " + nameA + " vs B: " + nameB;
    }

    public static void main(String[] args) {
        System.out.println(determineWinner("Instagram", 500, "TikTok", 450));
        System.out.println(isValidGuess("A"));
        System.out.println(isValidGuess("C"));
        System.out.println(formatComparison("Instagram", "TikTok"));
    }
}
