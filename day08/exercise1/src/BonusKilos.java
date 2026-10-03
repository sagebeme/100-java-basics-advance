public class BonusKilos {

    public static double withBonus(double kg, double bonus) {
        // TODO: return the new value
        return 0;
    }

    public static void addBonusToAll(double[] kgs, double bonus) {
        // TODO: change every element of kgs
    }

    public static void main(String[] args) {
        double mine = 50;
        withBonus(mine, 10);
        System.out.println(mine);

        double[] team = {50, 20};
        addBonusToAll(team, 10);
        System.out.println(team[0]);
    }
}
