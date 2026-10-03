public class Order {

    static final String[] NUMBER_WORDS = {"One", "Two", "Three", "Four", "Five"};

    public static String orderSentence(String size, String drink, boolean takeaway) {
        String where = takeaway ? "to take away" : "to have here";
        return "One " + size.toLowerCase() + " " + drink.toLowerCase() + " " + where + ", please.";
    }

    public static String orderFor(int people, String drink) {
        String number = people <= NUMBER_WORDS.length ? NUMBER_WORDS[people - 1] : String.valueOf(people);
        String drinks = drink.toLowerCase() + (people > 1 ? "s" : "");
        return number + " " + drinks + ", please.";
    }

    public static void main(String[] args) {
        System.out.println(orderSentence("medium", "Latte", true));
        System.out.println(orderFor(3, "Flat White"));
    }
}
