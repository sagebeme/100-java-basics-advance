public class StationHours {

    public static boolean isOpen(String day, int hour) {
        if (day.equalsIgnoreCase("Sunday")) {
            return false;
        }
        if (day.equalsIgnoreCase("Saturday")) {
            return hour >= 7 && hour < 12;
        }
        return hour >= 7 && hour < 17;
    }

    public static void main(String[] args) {
        System.out.println(isOpen("Monday", 9));
        System.out.println(isOpen("Sunday", 9));
    }
}
