import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Shuffle {

    public static <T> List<T> shuffled(List<T> items, Random random) {
        List<T> copy = new ArrayList<>(items);
        for (int i = copy.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Collections.swap(copy, i, j);
        }
        return copy;
    }

    public static void main(String[] args) {
        System.out.println(shuffled(List.of("Nyeri", "Embu", "Meru", "Kisii", "Kiambu"), new Random()));
    }
}
