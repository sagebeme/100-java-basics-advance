public class WeighingTickets {

    public static String ticketNumbers(int first, int last) {
        String tickets = "";
        for (int n = first; n <= last; n++) {
            if (n > first) {
                tickets = tickets + " ";
            }
            tickets = tickets + String.format("T%03d", n);
        }
        return tickets;
    }

    public static double totalKg(double[] deliveries) {
        double total = 0;
        for (double kg : deliveries) {
            total += kg;
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(ticketNumbers(7, 10));
        System.out.println(totalKg(new double[]{12.5, 8.0, 20.25}));
    }
}
