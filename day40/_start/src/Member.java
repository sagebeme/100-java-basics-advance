import java.util.List;

public record Member(String name, String email, List<WishItem> wishes) {

    public Member {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("A member needs a name");
        if (!isValidEmail(email)) throw new IllegalArgumentException("Not a valid email address: " + email);
        if (wishes.isEmpty()) throw new IllegalArgumentException("Add at least one place to the wishlist");
        name = name.trim();
        email = email.trim().toLowerCase();
        wishes = List.copyOf(wishes);
    }

    static boolean isValidEmail(String address) {
        return address != null && address.trim().matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+");
    }

    // "Mombasa:MBA:7000"
    public static WishItem parseWish(String text) {
        String[] p = text.split(":");
        if (p.length != 3) throw new IllegalArgumentException("Write wishes as City:CODE:maxPrice, e.g. Mombasa:MBA:7000");
        return new WishItem(p[0].trim(), p[1].trim().toUpperCase(), Integer.parseInt(p[2].trim()));
    }
}
