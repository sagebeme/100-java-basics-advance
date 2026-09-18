import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Exercise2 {

    public static List<Integer> filterAbove(List<Integer> numbers, int threshold) {
        List<Integer> result = new ArrayList<>();
        for (int n : numbers) {
            if (n > threshold) result.add(n);
        }
        return result;
    }

    public static List<Integer> sortAscending(List<Integer> numbers) {
        List<Integer> sorted = new ArrayList<>(numbers);
        Collections.sort(sorted);
        return sorted;
    }

    public static double average(List<Integer> numbers) {
        int sum = 0;
        for (int n : numbers) sum += n;
        return (double) sum / numbers.size();
    }

    public static void main(String[] args) {
        List<Integer> data = List.of(5, 2, 9, 1, 7);
        System.out.println("Above 4: " + filterAbove(data, 4));
        System.out.println("Sorted: " + sortAscending(data));
        System.out.println("Average: " + average(data));
    }
}
