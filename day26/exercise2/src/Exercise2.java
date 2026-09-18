import java.util.List;
import java.util.stream.Collectors;

public class Exercise2 {

    public static List<Integer> evenNumbers(List<Integer> numbers) {
        return numbers.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
    }

    public static List<Integer> doubled(List<Integer> numbers) {
        return numbers.stream().map(n -> n * 2).collect(Collectors.toList());
    }

    public static int sum(List<Integer> numbers) {
        return numbers.stream().reduce(0, Integer::sum);
    }

    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        System.out.println("Even: " + evenNumbers(numbers));
        System.out.println("Doubled: " + doubled(numbers));
        System.out.println("Sum: " + sum(numbers));
    }
}
