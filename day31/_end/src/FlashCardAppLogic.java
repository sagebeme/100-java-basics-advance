public class FlashCardAppLogic {

    public static String formatCard(String question, String answer, boolean known) {
        return question + "|" + answer + "|" + known;
    }

    /**
     * Reverses formatCard. Returns null if the line has fewer than 2 parts.
     */
    public static String[] parseCard(String line) {
        String[] parts = line.split("\\|");
        return parts.length >= 2 ? parts : null;
    }

    public static int nextIndex(int currentIndex, int totalCards) {
        return (currentIndex + 1) % totalCards;
    }
}
