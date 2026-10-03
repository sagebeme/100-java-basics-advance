public class CherryTally {

    private int ripe;

    public static int countRed(String[] cherries) {
        int count = 0; // declared and set once, before the loop
        for (String cherry : cherries) {
            if (cherry.equals("red")) {
                count++;
            }
        }
        return count;
    }

    public void add(int ripe) {
        this.ripe = this.ripe + ripe; // this.ripe is the field; plain ripe is the parameter
    }

    public int getRipe() {
        return ripe;
    }

    public static void main(String[] args) {
        System.out.println(countRed(new String[]{"red", "green", "red", "red"}));

        CherryTally tally = new CherryTally();
        tally.add(5);
        tally.add(3);
        System.out.println(tally.getRipe());
    }
}
