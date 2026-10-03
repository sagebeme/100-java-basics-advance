public class StationHours {

    public static boolean isOpen(String day, int hour) {
        // TODO: Sunday is closed, Saturday is 7 to 12, weekdays are 7 to 17
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isOpen("Monday", 9));
        System.out.println(isOpen("Sunday", 9));
    }
}
