public class Plate {

    private final String tidy;

    public Plate(String text) {
        String squashed = text.replace(" ", "").toUpperCase();
        if (!squashed.matches("[A-Z]{3}\\d{3}[A-Z]")) {
            throw new IllegalArgumentException("Not a Kenyan number plate: " + text);
        }
        this.tidy = squashed.substring(0, 3) + " " + squashed.substring(3);
    }

    @Override
    public String toString() {
        return tidy;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Plate)) {
            return false;
        }
        Plate that = (Plate) other;
        return tidy.equals(that.tidy);
    }

    @Override
    public int hashCode() {
        return tidy.hashCode();
    }

    public static void main(String[] args) {
        Plate a = new Plate("KDA 123A");
        Plate b = new Plate(" kda123a ");
        System.out.println(a + " and " + b + " are the same car: " + a.equals(b));
    }
}
