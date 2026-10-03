public class WeighingScale {

    // TODO: a private double offset

    public boolean calibrate(double newOffset) {
        // TODO: only -0.5 to 0.5 is allowed
        return false;
    }

    public double weigh(double rawKg) {
        // TODO: raw + offset, never below 0
        return 0;
    }

    public double getOffset() {
        // TODO
        return 0;
    }

    public static void main(String[] args) {
        WeighingScale scale = new WeighingScale();
        System.out.println(scale.calibrate(-0.2));
        System.out.println(scale.weigh(23.7));
        System.out.println(scale.calibrate(5));
    }
}
