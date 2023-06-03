package code;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringsAndNumbers {
    private static final String WHITESPACE = " ";

    private static final Pattern PATTERN = Pattern.compile(" +");

    private static final Set<Character> allVowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

    public static void main(String[] args) {
        System.out.println(isPalindrome("madam"));
    }

    public static boolean isPalindrome(String str) {
        int n = str.length();
        for (int i = 0; i < n / 2; i++) {
            if (str.charAt(i) != str.charAt(n - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindrome2(String str) {
        return IntStream.range(0, str.length() / 2)
            .noneMatch(p -> str.charAt(p) != str.charAt(str.length() - p - 1));
    }

    public static void permuteAndPrint(String prefix, String str) {
        int n = str.length();
        if (n == 0) {
            System.out.print(prefix + " ");
        } else {
            for (int i = 0; i < n; i++) {
                permuteAndPrint(
                    prefix + str.charAt(i),
                    str.substring(i + 1, n) + str.substring(0, i)
                );
            }
        }
    }

    public static void permuteAndPrintStream(String prefix, String str) {
        int n = str.length();
        if (n == 0) {
            System.out.print(prefix + " ");
        } else {
            IntStream.range(0, n)
                .parallel()
                .forEach(i -> permuteAndPrintStream(
                    prefix + str.charAt(i), 
                    str.substring(i + 1, n) + str.substring(0, i)));
        }
    }
    public static String joinByDelimiter(char delimiter, String... args) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        for (i = 0; i < args.length - 1; i++) {
            result.append(args[i]).append(delimiter);
        }
        result.append(args[i]);
        return result.toString();
    }

    public static String joinByDelimiter2(char delimiter, String... args) {
        return Arrays.stream(args, 0, args.length)
            .collect(Collectors.joining(String.valueOf(delimiter)));
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

    public static Pair<Integer, Integer> countVowelsAndConsonants(String string) {
        string = string.toLowerCase();
        int vowels = 0;
        int consonants = 0;
        for (int i = 0; i < string.length(); i++) {
            char ch = string.charAt(i);
            if (allVowels.contains(ch)) {
                vowels++;
            } else if ((ch >= 'a' && ch <= 'z')) {
                consonants++;
            }
        }
        return Pair.of(vowels, consonants);
    }

    public static Pair<Long, Long> countVowelsAndConsonants2(String string) {
        string = string.toLowerCase();
        long vowels = string.chars()
            .filter(c -> allVowels.contains((char) c))
            .count();
        long consonants = string.chars()
            .filter(c -> !allVowels.contains((char) c))
            .filter(ch -> (ch >= 'a' && ch <= 'z'))
            .count();
        return Pair.of(vowels, consonants);
    }

    public static int countOccurrencesOfCharacter(String str, char ch) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }

    public static long countOccurrencesOfCharacter2(String str, char ch) {
        return str.chars()
            .filter(c -> c == ch)
            .count();
    }
}
