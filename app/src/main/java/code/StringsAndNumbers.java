package code;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringsAndNumbers {
    private static final String WHITESPACE = " ";

    private static final Pattern PATTERN = Pattern.compile(" +");

    public static void main(String[] args) {
        System.out.println(containsOnlyDigits("007"));
    }

    public static Map<Character, Integer> countDuplicateCharacters(String string) {
        Map<Character, Integer> result = new HashMap<>();
        for (char ch : string.toCharArray()) {
            result.compute(ch, (k, v) -> (v == null) ? 1 : ++v);
        }
        return result;
    }

    public static Map<Character, Long> countDuplicateCharacters2(String string) {
        Map<Character, Long> result = string.chars()
            .mapToObj(c -> (char) c)
            .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
        return result;
    }

    public static char firstNonRepeatedCharacter(String string) {
        Map<Character, Integer> chars = new LinkedHashMap<>();
        for (char ch : string.toCharArray()) {
            chars.compute(ch, (k, v) -> (v == null) ? 1 : ++v);
        }
        for (Map.Entry<Character, Integer> entry : chars.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return Character.MIN_VALUE;
    }

    public static String firstNonRepeatedCharacter2(String string) {
        Map<Integer, Long> codePoints = string.codePoints()
            .mapToObj(cp -> cp)
            .collect(Collectors.groupingBy(
                Function.identity(),
                LinkedHashMap::new, 
                Collectors.counting()));
        int codePoint = codePoints.entrySet().stream()
            .filter(e -> e.getValue() == 1L)
            .findFirst()
            .map(Map.Entry::getKey)
            .orElse(Integer.valueOf(Character.MIN_VALUE));
        return String.valueOf(Character.toChars(codePoint));
    }

    public static String reverseCharacters(String string) {
        String[] words = string.split(WHITESPACE);
        StringBuilder reverseString = new StringBuilder();
        for (String word : words) {
            StringBuilder reverseWord = new StringBuilder();
            for (int i = word.length() - 1; i >= 0; i--) {
                reverseWord.append(word.charAt(i));
            }
            reverseString.append(reverseWord).append(WHITESPACE);
        }
        return reverseString.toString();
    }

    public static String reverseCharacters2(String string) {
        return PATTERN.splitAsStream(string)
            .map(w -> new StringBuilder(w).reverse())
            .collect(Collectors.joining(" "));
    }

    public static boolean containsOnlyDigits(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (!Character.isDigit(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean containsOnlyDigits2(String string) {
        return !string.chars().anyMatch(n -> !Character.isDigit(n));
    }
}
