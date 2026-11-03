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

    /**
     * The next card after currentIndex that isn't known yet, going round to the start, and
     * ending with the current card itself. -1 if every card is known.
     */
    public static int nextToStudy(int currentIndex, boolean[] known) {
        for (int step = 1; step <= known.length; step++) {
            int i = (currentIndex + step) % known.length;
            if (!known[i]) {
                return i;
            }
        }
        return -1;
    }
}
