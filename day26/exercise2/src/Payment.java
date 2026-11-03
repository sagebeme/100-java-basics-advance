// One payment into Mama Oliech's M-Pesa till. Already done.
public class Payment {

    private final String code;
    private final String phone;
    private final int shillings;
    private final int hour;

    public Payment(String code, String phone, int shillings, int hour) {
        this.code = code;
        this.phone = phone;
        this.shillings = shillings;
        this.hour = hour;
    }

    public String getCode() {
        return code;
    }

    public String getPhone() {
        return phone;
    }

    public int getShillings() {
        return shillings;
    }

    // 0 to 23.
    public int getHour() {
        return hour;
    }
}
