public class Exercise2 {

    /**
     * @return true if (row, col) should be the dark square of a checkerboard.
     */
    public static boolean isDarkSquare(int row, int col) {
        return (row + col) % 2 == 0;
    }

    public static void main(String[] args) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                System.out.print(isDarkSquare(row, col) ? "#" : ".");
            }
            System.out.println();
        }
    }
}
