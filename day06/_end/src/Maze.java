public class Maze {
    private static int x = 0;
    private static int y = 0;
    private static String direction = "EAST";
    
    public static void move() {
        switch (direction) {
            case "EAST": x++; break;
            case "WEST": x--; break;
            case "NORTH": y++; break;
            case "SOUTH": y--; break;
        }
        System.out.println("Moved to position (" + x + ", " + y + ")");
    }
    
    public static void turnLeft() {
        switch (direction) {
            case "EAST": direction = "NORTH"; break;
            case "NORTH": direction = "WEST"; break;
            case "WEST": direction = "SOUTH"; break;
            case "SOUTH": direction = "EAST"; break;
        }
        System.out.println("Turned left, now facing " + direction);
    }
    
    public static void turnRight() {
        switch (direction) {
            case "EAST": direction = "SOUTH"; break;
            case "SOUTH": direction = "WEST"; break;
            case "WEST": direction = "NORTH"; break;
            case "NORTH": direction = "EAST"; break;
        }
        System.out.println("Turned right, now facing " + direction);
    }
    
    public static boolean isPathClear() {
        // Simplified: assume path is clear if not at boundaries
        return x >= 0 && x < 10 && y >= 0 && y < 10;
    }
    
    public static boolean isAtGoal() {
        return x == 9 && y == 9; // Goal at (9, 9)
    }
    
    public static void main(String[] args) {
        System.out.println("Starting maze navigation...");
        System.out.println("Goal: Reach position (9, 9)");
        
        while (!isAtGoal()) {
            if (isPathClear()) {
                move();
            } else {
                turnRight();
            }
            
            // Simple navigation logic
            if (x < 9 && isPathClear()) {
                // Continue moving
            } else if (y < 9) {
                turnLeft();
                move();
                turnLeft();
            }
        }
        
        System.out.println("Reached the goal!");
    }
}

