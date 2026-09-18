public class Exercise1 {
    public static void main(String[] args) {
        CountdownTimer timer = new CountdownTimer(5 * 60);
        timer.start();
        timer.tick();
        System.out.println(CountdownTimer.format(timer.getSecondsLeft()));
    }
}
