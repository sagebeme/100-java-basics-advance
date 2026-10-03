public class CherryTally {

    private int ripe;

    // Should count the "red" cherries. It doesn't.
    public static int countRed(String[] cherries) {
        int count = 0;
        for (String cherry : cherries) {
            count = 0;
            if (cherry.equals("red")) {
                count++;
            }
        }
        return count;
    }

    // Should add to this tally's running total. It doesn't.
    public void add(int ripe) {
        ripe = ripe + ripe;
    }

    public int getRipe() {
        return ripe;
    }

    public static void main(String[] args) {
        System.out.println(countRed(new String[]{"red", "green", "red", "red"})); // should be 3

        CherryTally tally = new CherryTally();
        tally.add(5);
        tally.add(3);
        System.out.println(tally.getRipe()); // should be 8
    }
}
