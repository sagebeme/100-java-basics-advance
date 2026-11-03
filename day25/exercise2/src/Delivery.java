// One farmer's milk at the co-op on one day. Already done.
public class Delivery {

    private final String farmer;
    private final String date;
    private final int litres;
    private final String grade;

    public Delivery(String farmer, String date, int litres, String grade) {
        this.farmer = farmer;
        this.date = date;
        this.litres = litres;
        this.grade = grade;
    }

    public String getFarmer() {
        return farmer;
    }

    public String getDate() {
        return date;
    }

    public int getLitres() {
        return litres;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return farmer + " " + date + " " + litres + " l grade " + grade;
    }
}
