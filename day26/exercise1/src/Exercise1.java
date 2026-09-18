import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Function;

public class Exercise1 {

    public static List<String> sortByLength(List<String> words) {
        List<String> sorted = new ArrayList<>(words);
        sorted.sort(Comparator.comparingInt(String::length));
        return sorted;
    }

    public static List<String> filterStartingWith(List<String> words, String letter) {
        Predicate<String> startsWithLetter = word -> word.toLowerCase().startsWith(letter.toLowerCase());
        List<String> result = new ArrayList<>();
        for (String word : words) {
            if (startsWithLetter.test(word)) result.add(word);
        }
        return result;
    }

    public static List<Integer> transformToLengths(List<String> words) {
        Function<String, Integer> toLength = String::length;
        List<Integer> lengths = new ArrayList<>();
        for (String word : words) {
            lengths.add(toLength.apply(word));
        }
        return lengths;
    }

    public static void main(String[] args) {
        List<String> words = List.of("banana", "kiwi", "apple", "berry");
        System.out.println(sortByLength(words));
        System.out.println(filterStartingWith(words, "b"));
        System.out.println(transformToLengths(words));
    }
}
