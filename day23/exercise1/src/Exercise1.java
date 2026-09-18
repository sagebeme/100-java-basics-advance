public class Exercise1 {
    public static void main(String[] args) {
        Player player = new Player(300, 750, 20, 600, 800);
        player.moveUp();
        System.out.println("Player at: (" + player.getX() + ", " + player.getY() + ")");
    }
}
