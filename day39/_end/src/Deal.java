public record Deal(WishItem wish, Fare fare) {

    public int saving() {
        return wish.maxPriceKes() - fare.priceKes();
    }
}
