public record WishItem(String city, String code, int maxPriceKes) {

    // "Mombasa,MBA,7000"
    public static WishItem fromCsv(String line) {
        String[] p = line.split(",");
        return new WishItem(p[0].trim(), p[1].trim().toUpperCase(), Integer.parseInt(p[2].trim()));
    }
}
