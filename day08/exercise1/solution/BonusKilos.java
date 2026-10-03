public class BonusKilos {

    public static double withBonus(double kg, double bonus) {
        return kg + bonus;
    }

    public static void addBonusToAll(double[] kgs, double bonus) {
        for (int i = 0; i < kgs.length; i++) {
            kgs[i] = kgs[i] + bonus;
        }
    }

    public static void main(String[] args) {
        double mine = 50;
        withBonus(mine, 10);       // the result is thrown away, and mine is untouched
        System.out.println(mine);  // 50.0

        double[] team = {50, 20};
        addBonusToAll(team, 10);     // changes the array team points at
        System.out.println(team[0]); // 60.0
    }
}
