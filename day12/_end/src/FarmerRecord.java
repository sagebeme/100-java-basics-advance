public class FarmerRecord {

    private static int recordsOpened = 0;

    private final String name;
    private final String memberCode;
    private double deliveredKg;

    public FarmerRecord(String name, String memberCode) {
        this.name = name;
        this.memberCode = memberCode;
        recordsOpened++;
    }

    public boolean addDelivery(double kg) {
        if (kg <= 0) {
            return false;
        }
        deliveredKg += kg;
        return true;
    }

    public String getName() {
        return name;
    }

    public double getDeliveredKg() {
        return deliveredKg;
    }

    public String summary() {
        return memberCode + " " + name + ": " + deliveredKg + " kg";
    }

    public static int recordsOpened() {
        return recordsOpened;
    }
}
