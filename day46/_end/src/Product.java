// wasKes is the crossed-out "before" price a shop shows during a sale, or 0 when there isn't one.
public record Product(String sku, String name, int priceKes, int wasKes, boolean inStock) {

    public boolean claimsADiscount() {
        return wasKes > priceKes;
    }
}
