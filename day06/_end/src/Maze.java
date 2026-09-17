public class Maze {
    private int x = 0;
    private int y = 0;
    private String direction = "EAST";

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getDirection() {
        return direction;
    }

    public void move() {
        switch (direction) {
            case "EAST": x++; break;
            case "WEST": x--; break;
            case "NORTH": y++; break;
            case "SOUTH": y--; break;
        }
        System.out.println("Moved to position (" + x + ", " + y + ")");
    }

    public void turnLeft() {
        switch (direction) {
            case "EAST": direction = "NORTH"; break;
            case "NORTH": direction = "WEST"; break;
            case "WEST": direction = "SOUTH"; break;
            case "SOUTH": direction = "EAST"; break;
        }
        System.out.println("Turned left, now facing " + direction);
    }

    public void turnRight() {
        switch (direction) {
            case "EAST": direction = "SOUTH"; break;
            case "SOUTH": direction = "WEST"; break;
            case "WEST": direction = "NORTH"; break;
            case "NORTH": direction = "EAST"; break;
        }
        System.out.println("Turned right, now facing " + direction);
    }

    public boolean isPathClear() {
        // Simplified: assume path is clear if not at boundaries
        return x >= 0 && x < 10 && y >= 0 && y < 10;
    }

    public boolean isAtGoal() {
        return x == 9 && y == 9; // Goal at (9, 9)
    }

    public static void main(String[] args) {
        System.out.println("Starting maze navigation...");
        System.out.println("Goal: Reach position (9, 9)");

        Maze maze = new Maze();

        while (!maze.isAtGoal()) {
            if (maze.isPathClear()) {
                maze.move();
            } else {
                maze.turnRight();
            }

            // Simple navigation logic
            if (maze.getX() < 9 && maze.isPathClear()) {
                // Continue moving
            } else if (maze.getY() < 9) {
                maze.turnLeft();
                maze.move();
                maze.turnLeft();
            }
        }

        System.out.println("Reached the goal!");
    }
}
