public record Upgrade(String id, int cost, int ratePerSecond, int owned) {

    // Seconds of this upgrade's own output to pay back its price: lower is a better deal.
    public double paybackSeconds() {
        return (double) cost / ratePerSecond;
    }
}
