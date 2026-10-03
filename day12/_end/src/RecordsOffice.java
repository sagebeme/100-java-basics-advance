public class RecordsOffice {
    public static void main(String[] args) {
        System.out.println("=== Records Office ===");

        FarmerRecord wanjiru = new FarmerRecord("Wanjiru", "WK-0042");
        FarmerRecord otieno = new FarmerRecord("Otieno", "OA-0107");
        System.out.println("Opened " + FarmerRecord.recordsOpened() + " records.");

        wanjiru.addDelivery(23.5);
        wanjiru.addDelivery(10);
        otieno.addDelivery(31);
        System.out.println(wanjiru.summary());
        System.out.println(otieno.summary());

        boolean added = otieno.addDelivery(-200);
        System.out.println("Tried to add -200 kg to " + otieno.getName() + ": " + (added ? "added." : "refused."));
        System.out.println(otieno.summary());

        // Uncomment the next line, compile, and read the error. Then put the comment back.
        // otieno.deliveredKg = 1000;
    }
}
