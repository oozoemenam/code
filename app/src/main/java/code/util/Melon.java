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
    return this.type;
  }

  public int getWeight() {
    return this.weight;
  }

  public boolean equals(final Object o) {
    if (o == this) return true;
    if (!(o instanceof Melon)) return false;
    final Melon other = (Melon) o;
    if (!other.canEqual((Object) this)) return false;
    final Object this$type = this.getType();
    final Object other$type = other.getType();
    if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
    if (this.getWeight() != other.getWeight()) return false;
    return true;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $type = this.getType();
    result = result * PRIME + ($type == null ? 43 : $type.hashCode());
    result = result * PRIME + this.getWeight();
    return result;
  }

  public String toString() {
    return "Melon(type=" + this.getType() + ", weight=" + this.getWeight() + ")";
  }

  @Override
  public int compare(Melon o1, Melon o2) {
    return o1.getType().compareTo(o2.getType());
  }

  protected boolean canEqual(final Object other) {
    return other instanceof Melon;
  }
}
