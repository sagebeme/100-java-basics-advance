public class PickingTeam {

    public static String firstToArrive(String[] team) {
        return team[0];
    }

    public static String lastToArrive(String[] team) {
        return team[team.length - 1];
    }

    public static int teamSize(String[] team) {
        return team.length;
    }

    public static boolean isOnTeam(String[] team, String name) {
        for (int i = 0; i < team.length; i++) {
            if (team[i].equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String[] team = {"Wanjiru", "Brian", "Mama Njeri", "Otieno"};
        System.out.println(firstToArrive(team) + " arrived first, " + lastToArrive(team) + " last.");
    }
}
