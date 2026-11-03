public class Kuku {

    // TODO: private fields: where it starts, how far one step goes, the road's size,
    // where it is now, and the level (starting at 1)

    public Kuku(int startX, int startY, int step, int width, int height) {
        // TODO: refuse a step of 0 or less
    }

    public void moveUp() {
        // TODO: never above 0
    }

    public void moveDown() {
        // TODO: never below the bottom
    }

    public void moveLeft() {
        // TODO
    }

    public void moveRight() {
        // TODO
    }

    public boolean reachedTheOtherSide() {
        // TODO: the far side is y == 0
        return false;
    }

    // Made it across? Then the next level starts back at the start. Returns whether it did.
    public boolean checkCrossing() {
        // TODO
        return false;
    }

    public void backToStart() {
        // TODO
    }

    public int getX() {
        // TODO
        return -1;
    }

    public int getY() {
        // TODO
        return -1;
    }

    public int getLevel() {
        // TODO
        return 0;
    }
}
