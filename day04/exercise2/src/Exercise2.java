import java.util.ArrayList;
import java.util.Scanner;

public class Exercise2 {

    public static void removeByIndex(ArrayList<String> names, int index) {
        names.remove(index);
    }

    public static boolean search(ArrayList<String> names, String name) {
        return names.contains(name);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> names = new ArrayList<>();

        System.out.println("Enter names one per line, then type 'done':");
        while (true) {
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("done")) break;
            names.add(name);
        }

        System.out.println("Names: " + names);
        scanner.close();
    }
}
