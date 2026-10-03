public class Caffeine {

    public static final int DAILY_LIMIT_MG = 400;

    public static int caffeineMg(String drink) {
        // TODO: a switch on the cleaned-up drink name
        return 0;
    }

    public static boolean overDailyLimit(String... drinks) {
        // TODO: add them up and return whether the total is over the limit
        return false;
    }

    public static void main(String[] args) {
        System.out.println(caffeineMg("Espresso"));
        System.out.println(overDailyLimit("filter", "filter", "chai"));
    }
}
