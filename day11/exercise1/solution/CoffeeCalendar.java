public class CoffeeCalendar {

    public static String seasonFor(int month) {
        if (month < 1 || month > 12) return "not a month";
        if (month >= 10) return "main crop";
        if (month >= 4 && month <= 6) return "fly crop";
        return "growing";
    }

    public static int harvestMonths() {
        int count = 0;
        for (int month = 1; month <= 12; month++) {
            if (!seasonFor(month).equals("growing")) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("November: " + seasonFor(11));
        System.out.println("Harvest months a year: " + harvestMonths());
    }
}
