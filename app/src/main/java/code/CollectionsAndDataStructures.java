package code;

import java.util.Arrays;

public class CollectionsAndDataStructures {
  
  public static void main(String[] args) {
    System.out.println(Arrays.toString(easySort(new int[]{1, 3, 2, 5, 2})));
  }

  public static int[] easySort(int[] in) {
    Arrays.sort(in);
    return in;
  }
}
