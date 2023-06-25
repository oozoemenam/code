package code;

import java.util.Arrays;
import java.util.Comparator;

import code.util.Melon;

public class CollectionsAndDataStructures {
  
  public static void main(String[] args) {
    
    Melon[] melons = new Melon[] {
      new Melon("type", 3),
      new Melon("type", 1),
      new Melon("type", 5)
    };
    
    System.out.println(Arrays.toString(compareSort(melons)));
  }

  public static int[] easySort(int[] in) {
    Arrays.sort(in);
    return in;
  }

  public static Melon[] compareSort(Melon[] melons) {
    Arrays.sort(melons, new Comparator<Melon>() {
      @Override
      public int compare(Melon melon1, Melon melon2) {
        return Integer.compare(melon1.getWeight(), melon2.getWeight());
      }
    });
    return melons;
  }
}
