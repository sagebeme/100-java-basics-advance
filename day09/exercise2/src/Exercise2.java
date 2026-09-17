import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Exercise2 {

    public static Set<Integer> union(Set<Integer> a, Set<Integer> b) {
        Set<Integer> result = new HashSet<>(a);
        result.addAll(b);
        return result;
    }

    public static Set<Integer> intersection(Set<Integer> a, Set<Integer> b) {
        Set<Integer> result = new HashSet<>(a);
        result.retainAll(b);
        return result;
    }

    public static Set<Integer> removeDuplicates(List<Integer> numbers) {
        return new HashSet<>(numbers);
    }

    public static void main(String[] args) {
        Set<Integer> a = Set.of(1, 2, 3);
        Set<Integer> b = Set.of(2, 3, 4);
        System.out.println("Union: " + union(a, b));
        System.out.println("Intersection: " + intersection(a, b));
        System.out.println("No duplicates: " + removeDuplicates(List.of(1, 1, 2, 2, 3)));
    }
}
