package code;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import code.util.Pair;
import code.util.Sort;

public class StringsAndNumbers {
    private static final String WHITESPACE = " ";

    private static final Pattern PATTERN = Pattern.compile(" +");

    private static final Set<Character> allVowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));

    private static final int EXTENDED_ASCII_CODES = 256;

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[] { "abc", "abcd", "ab", "abcde" }));
    }

    public static String longestCommonPrefix(String[] strings) {
        if (strings.length == 1) {
            return strings[0];
        }
        int firstLen = strings[0].length();
        for (int prefixLen = 0; prefixLen < firstLen; prefixLen++) {
            char ch = strings[0].charAt(prefixLen);
            for (int i = 1; i < strings.length; i++) {
                if (prefixLen >= strings[i].length()
                        || strings[i].charAt(prefixLen) != ch) {
                    return strings[i].substring(0, prefixLen);
                }
            }
        }
        return strings[0];
    }

    public static boolean isAnagram(String str1, String str2) {
        int[] charCount = new int[EXTENDED_ASCII_CODES];
        char[] charStr1 = str1.replaceAll("\\s", "").toLowerCase().toCharArray();
        char[] charStr2 = str2.replaceAll("\\s", "").toLowerCase().toCharArray();
        if (charStr1.length != charStr2.length) {
            return false;
        }
        for (int i = 0; i < charStr1.length; i++) {
            charCount[charStr1[i]]++;
            charCount[charStr2[i]]--;
        }
        for (int i = 0; i < charCount.length; i++) {
            if (charCount[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public static int countStringInString(String string, String toFind) {
        int position = 0;
        int count = 0;
        while ((position = string.indexOf(toFind, position)) != -1) {
            position += toFind.length();
            count++;
        }
        return count;
    }

    public static void sortArrayByLength(String[] strings, Sort direction) {
        if (direction.equals(Sort.ASC)) {
            Arrays.sort(strings, (String s1, String s2) -> Integer.compare(s1.length(), s2.length()));
        } else {
            Arrays.sort(strings, (String s1, String s2) -> (-1) * Integer.compare(s1.length(), s2.length()));
        }
    }

    public static void sortArrayByLength3(String[] strings, Sort direction) {
        if (direction.equals(Sort.ASC)) {
            Arrays.sort(strings, Comparator.comparingInt(String::length));
        } else {
            Arrays.sort(strings, Comparator.comparingInt(String::length).reversed());
        }
    }

    public static String[] sortArrayByLength2(String[] strings, Sort direction) {
        if (direction.equals(Sort.ASC)) {
            return Arrays.stream(strings)
                    .sorted(Comparator.comparingInt(String::length))
                    .toArray(String[]::new);
        } else {
            return Arrays.stream(strings)
                    .sorted(Comparator.comparingInt(String::length).reversed())
                    .toArray(String[]::new);
        }
    }

    public static Pair<Character, Integer> maxOccurenceCharacter(String str) {
        Map<Character, Integer> counter = new HashMap<>();
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char currentChar = charArray[i];
            if (!Character.isWhitespace(currentChar)) {
                Integer noChar = counter.get(currentChar);
                if (noChar == null) {
                    counter.put(currentChar, 1);
                } else {
                    counter.put(currentChar, ++noChar);
                }
            }
        }
        int maxOccurences = Collections.max(counter.values());
        char maxCharacter = Character.MIN_VALUE;
        for (Entry<Character, Integer> entry : counter.entrySet()) {
            if (entry.getValue() == maxOccurences) {
                maxCharacter = entry.getKey();
            }
        }
        return Pair.of(maxCharacter, maxOccurences);
    }

    public static String removeCharacter(String str, char ch) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] charArray = str.toCharArray();
        for (char c : charArray) {
            if (c != ch) {
                stringBuilder.append(c);
            }
        }
        return stringBuilder.toString();
    }

    public static String removeCharacter2(String str, char ch) {
        return str.chars()
                .filter(c -> c != ch)
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());
    }

    public static String removeDuplicates(String str) {
        char[] charArray = str.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for (char ch : charArray) {
            if (stringBuilder.indexOf(String.valueOf(ch)) == -1) {
                stringBuilder.append(ch);
            }
        }
        return stringBuilder.toString();
    }

    public static String removeDuplicates2(String str) {
        return Arrays.asList(str.split(""))
                .stream()
                .distinct()
                .collect(Collectors.joining());
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
                        str.substring(i + 1, n) + str.substring(0, i));
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
