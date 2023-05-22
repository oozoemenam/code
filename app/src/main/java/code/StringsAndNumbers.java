package code;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StringsAndNumbers {
    public static void main(String[] args) {
        System.out.println(countDuplicateCharacters("Duplicate"));
    }

    public static Map<Character, Integer> countDuplicateCharacters(String string) {
        Map<Character, Integer> result = new HashMap<>();
        for (char ch : string.toCharArray()) {
            result.compute(ch, (k, v) -> (v == null) ? 1 : ++v);
        }
        return result;
    }

    public static Map<Character, Long> countDuplicateChars(String string) {
        Map<Character, Long> result = string.chars()
            .mapToObj(c -> (char) c)
            .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        return result;
    }
}
