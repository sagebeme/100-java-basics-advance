public class Kuku {

    private final int startX;
    private final int startY;
    private final int step;
    private final int width;
    private final int height;
    private int x;
    private int y;
    private int level = 1;

    public Kuku(int startX, int startY, int step, int width, int height) {
        if (step <= 0) {
            throw new IllegalArgumentException("A step must be more than 0, not " + step);
        }
        this.startX = startX;
        this.startY = startY;
        this.step = step;
        this.width = width;
        this.height = height;
        this.x = startX;
        this.y = startY;
    }

    public void moveUp() {
        y = Math.max(0, y - step);
    }

    public void moveDown() {
        y = Math.min(height, y + step);
    }

    public void moveLeft() {
        x = Math.max(0, x - step);
    }

    public void moveRight() {
        x = Math.min(width, x + step);
    }

    public boolean reachedTheOtherSide() {
        return y == 0;
    }

    // Made it across? Then the next level starts back at the start. Returns whether it did.
    public boolean checkCrossing() {
        if (!reachedTheOtherSide()) {
            return false;
        }
        level++;
        backToStart();
        return true;
    }

    public void backToStart() {
        x = startX;
        y = startY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLevel() {
        return level;
    }
}
