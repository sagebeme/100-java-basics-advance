public class Plate {

    // TODO: a private field for the tidy form, like "KDA 123A"

    public Plate(String text) {
        // TODO: tidy it, refuse anything that isn't 3 letters, 3 digits, 1 letter, then store it
    }

    @Override
    public String toString() {
        // TODO
        return super.toString();
    }

    @Override
    public boolean equals(Object other) {
        // TODO: only another Plate with the same tidy form
        return super.equals(other);
    }

    @Override
    public int hashCode() {
        // TODO: equal plates, equal hash codes
        return super.hashCode();
    }

    public static void main(String[] args) {
        Plate a = new Plate("KDA 123A");
        Plate b = new Plate(" kda123a ");
        System.out.println(a + " and " + b + " are the same car: " + a.equals(b));
    }
}
