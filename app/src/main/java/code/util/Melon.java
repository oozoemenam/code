package code.util;

import java.util.Comparator;

public class Melon implements Comparator<Melon> {
  private final String type;
  private final int weight;

  public Melon(String type, int weight) {
    this.type = type;
    this.weight = weight;
  }

  public String getType() {
    return type;
  }

  public int getWeight() {
    return weight;
  }

  @Override
  public String toString() {
    return "{ type: " + type + ", weight: " + weight + "}";
  }

  @Override
  public int compare(Melon o1, Melon o2) {
    return o1.getType().compareTo(o2.getType());
  }
}
