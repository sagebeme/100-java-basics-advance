import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Shuffle {

    public static <T> List<T> shuffled(List<T> items, Random random) {
        // TODO: copy, then Fisher–Yates from the end down to index 1
        return new ArrayList<>(items);
    }

    public static void main(String[] args) {
        System.out.println(shuffled(List.of("Nyeri", "Embu", "Meru", "Kisii", "Kiambu"), new Random()));
    }
}
