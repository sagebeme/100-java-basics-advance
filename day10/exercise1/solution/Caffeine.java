public class Caffeine {

    public static final int DAILY_LIMIT_MG = 400;

    public static int caffeineMg(String drink) {
        return switch (drink.trim().toLowerCase()) {
            case "espresso" -> 63;
            case "filter" -> 95;
            case "instant" -> 62;
            case "chai" -> 40;
            case "decaf" -> 3;
            default -> 0;
        };
    }

    public static boolean overDailyLimit(String... drinks) {
        int total = 0;
        for (String drink : drinks) {
            total += caffeineMg(drink);
        }
        return total > DAILY_LIMIT_MG;
    }

    public static void main(String[] args) {
        System.out.println(caffeineMg("Espresso"));
        System.out.println(overDailyLimit("filter", "filter", "chai"));
    }
}
