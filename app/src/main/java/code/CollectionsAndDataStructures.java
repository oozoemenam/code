package code;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.IntStream;

import code.util.Melon;

public class CollectionsAndDataStructures {
  
  public static void main(String[] args) {
    
    int[] arr = new int[] {9, 1, 6, 3, 4, 3, 5, 2, 7};
    Melon[] melons = new Melon[] {
      new Melon("Crenshaw", 2000),
      new Melon("Gac", 1200), 
      new Melon("Bitter", 2200)
    };
    System.out.println(indexOfElement(arr, 4));
  }

  public static int indexOf(int[] arr, int toFind) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == toFind) {
        return i;
      }
    }
    return -1;
  }

  public static int indexOfElement(int[] arr, int toFind) {
    return IntStream.range(0, arr.length)
      .filter(i -> toFind == arr[i])
      .findFirst()
      .orElse(-1);
  }

  public static <T> boolean containsElementObjectComparator(T[] arr, T toContain, Comparator<? super T> c) {
    for (T elem : arr) {
      if (c.compare(elem, toContain) == 0) {
        return true;
      }
    }
    return false;
  }

  public static <T> boolean containsElementObject(T[] arr, T toContain) {
    for (T elem : arr) {
      if (elem.equals(toContain)) {
        return true;
      }
    }
    return false;
  }

  public static boolean containsElement(int[] arr, int toContain) {
    for (int elem : arr) {
      if (elem == toContain) {
        return true;
      }
    }
    return false;
  }

  public static boolean containsElement2(int[] arr, int toContain) {
    return Arrays.stream(arr)
      .anyMatch(e -> e == toContain);
  }

  public static int[] shuffleArray(int[] arr) {
    int index;
    Random random = new Random();
    for (int i = arr.length - 1; i > 0; i--) {
      index = random.nextInt(i + 1);
      swap(arr, index, i);
    }
    return arr;
  }

  public static int[] heapSort(int[] arr) {
    int n = arr.length;
    buildHeap(arr, n);
    while (n > 1) {
      swap(arr, 0, n - 1);
      n--;
      heapify(arr, n, 0);
    }
    return arr;
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

  // O(n^2)
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

  private static void buildHeap(int[] arr, int n) {
    for (int i = arr.length / 2; i >= 0; i--) {
      heapify(arr, n, i);
    }
  }

  private static void heapify(int[] arr, int n, int i) {
    int left = i * 2 + 1;
    int right = i * 2 + 2;
    int greater;
    if (left < n && arr[left] > arr[i]) {
      greater = left;
    } else {
      greater = i;
    }
    if (right < n && arr[right] > arr[greater]) {
      greater = right;
    }
    if (greater != i) {
      swap(arr, i, greater);
      heapify(arr, n, greater);
    }
  }

  private static void swap(int[] arr, int x, int y) {
    int temp = arr[x];
    arr[x] = arr[y];
    arr[y] = temp;
  }
}
