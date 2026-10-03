public class WeighingScale {

    private double offset;

    public boolean calibrate(double newOffset) {
        if (newOffset < -0.5 || newOffset > 0.5) {
            return false;
        }
        offset = newOffset;
        return true;
    }

    public double weigh(double rawKg) {
        return Math.max(0, rawKg + offset);
    }

    public double getOffset() {
        return offset;
    }

    public static void main(String[] args) {
        WeighingScale scale = new WeighingScale();
        System.out.println(scale.calibrate(-0.2));
        System.out.println(scale.weigh(23.7));
        System.out.println(scale.calibrate(5));
    }
}
