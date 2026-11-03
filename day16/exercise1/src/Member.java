public class Member {

    // TODO: three private fields: name, phone and savings (whole shillings)

    public Member(String name, String phone) {
        // TODO: refuse a bad phone number, then store the name and phone. Savings start at 0.
    }

    public String getName() {
        // TODO
        return "";
    }

    public String getPhone() {
        // TODO
        return "";
    }

    public int getSavings() {
        // TODO
        return -1;
    }

    public void setPhone(String phone) {
        // TODO: only change it to a good number
    }

    public void contribute(int shillings) {
        // TODO: refuse 0 or less, otherwise add to the savings
    }

    public String card() {
        // TODO: "Wanjiru · 0712345678 · KES 1500 saved"
        return "";
    }

    public static void main(String[] args) {
        // TODO: Wanjiru, 0712345678, contributes 500 then 1000. Print her card.
    }
}
