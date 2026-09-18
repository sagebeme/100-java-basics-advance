public class Exercise1 {

    /**
     * Moves a position by velocity, reversing velocity when it would go past [min, max].
     * @return {newPosition, newVelocity}
     */
    public static int[] moveAndBounce(int position, int velocity, int min, int max) {
        int next = position + velocity;
        if (next < min || next > max) {
            velocity = -velocity;
            next = position + velocity;
        }
        return new int[]{next, velocity};
    }

    public static void main(String[] args) {
        int position = 0;
        int velocity = 5;
        for (int i = 0; i < 5; i++) {
            int[] result = moveAndBounce(position, velocity, 0, 20);
            position = result[0];
            velocity = result[1];
            System.out.println("Position: " + position);
        }
    }
}
