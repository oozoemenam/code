package code;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.ArrayList;
import java.util.Arrays;

public class ObjectsAndImmutability {
  
  public static void main(String[] args) {
    System.out.println(sumIntegers(Arrays.asList(1, 2, null, 4, null, 16, 7, null)));
  }

  public static List<Integer> evenIntegers(List<Integer> integers) {
    if (integers == null) {
      return Collections.emptyList();
    }
    List<Integer> evens = new ArrayList<>();
    for (Integer i : integers) {
      if (i != null && i % 2 == 0) {
        evens.add(i);
      }
    }
    return evens;
  }

  public static int sumIntegers(List<Integer> integers) {
    if (Objects.isNull(integers)) {
      throw new IllegalArgumentException("List cannot be null");
    }
    return integers.stream()
      .filter(Objects::nonNull)
      .mapToInt(Integer::intValue)
      .sum();
  }
}
