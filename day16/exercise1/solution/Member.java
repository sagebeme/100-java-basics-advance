public class Member {

    private final String name;
    private String phone;
    private int savings;

    public Member(String name, String phone) {
        if (!isValidPhone(phone)) {
            throw new IllegalArgumentException("Not a phone number: " + phone);
        }
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getSavings() {
        return savings;
    }

    public void setPhone(String phone) {
        if (!isValidPhone(phone)) {
            throw new IllegalArgumentException("Not a phone number: " + phone);
        }
        this.phone = phone;
    }

    public void contribute(int shillings) {
        if (shillings <= 0) {
            throw new IllegalArgumentException("A contribution must be more than 0, not " + shillings);
        }
        savings += shillings;
    }

    public String card() {
        return name + " · " + phone + " · KES " + savings + " saved";
    }

    // 10 digits, starting 07 or 01.
    private static boolean isValidPhone(String phone) {
        return phone != null && phone.matches("0[17]\\d{8}");
    }

    public static void main(String[] args) {
        Member wanjiru = new Member("Wanjiru", "0712345678");
        wanjiru.contribute(500);
        wanjiru.contribute(1000);
        System.out.println(wanjiru.card());
    }
}
