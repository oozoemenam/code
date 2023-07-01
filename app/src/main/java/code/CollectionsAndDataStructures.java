package code;

import java.util.Arrays;
import java.util.Comparator;

import code.util.Melon;

public class CollectionsAndDataStructures {
  
  public static void main(String[] args) {
    
    int[] arr = new int[] {9, 1, 6, 3, 4, 3, 5, 2, 7};
    
    System.out.println(Arrays.toString(countingSort(arr)));
  }

  // O(n + k)
  public static int[] countingSort(int[] arr) {
    int min = arr[0];
    int max = arr[0];
    for (int i = 1; i < arr.length; i++) {
      if (arr[i] < min) {
        min = arr[i];
      } else if (arr[i] > max) {
        max = arr[i];
      }
    }
    int[] counts = new int[max - min + 1];
    for (int i = 0; i < arr.length; i++) {
      counts[arr[i] - min]++;
    }
    int sortedIndex = 0;
    for (int i = 0; i < counts.length; i++) {
      while (counts[i] > 0) {
        arr[sortedIndex++] = i + min;
        counts[i]--;
      }
    }
    return arr;
  }


  public static <T> T[] insertionSortWithComparator(T arr[], Comparator<? super T> c) {
    int n = arr.length;
    for (int i = 1; i < n; ++i) {
      T key = arr[i];
      int j = i - 1;
      while (j >= 0 && c.compare(arr[j], key) > 0) {
        arr[j + 1] = arr[j];
        j = j - 1;
      }
      arr[j + 1] = key;
    }
    return arr;
  }

  public static int[] insertionSort(int arr[]) {
    int n = arr.length;
    for (int i = 1; i < n; ++i) {
      int key = arr[i];
      int j = i - 1;
      while (j >= 0 && arr[j] > key) {
        arr[j + 1] = arr[j];
        j = j - 1;
      }
      arr[j + 1] = key;
    }
    return arr;
  }

  public static <T> T[] bubbleSortWithComparator(T arr[], Comparator<? super T> c) {
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (c.compare(arr[j], arr[j + 1]) > 0) {
          T temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
        }
      }
    }
    return arr;
  }

  // O(n^2)
  public static int[] bubbleSort(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (arr[j] > arr[j + 1]) {
          int temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
        }
      }
    }
    return arr;
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
