import java.util.Objects;

public class Card {
  private String color;
  private int value;

  public static final int MIN_VALUE = 0;
  public static final String[] COLORS = { "blue", "green", "red", "yellow" };
  public static final int MAX_VALUE = 9;

  public Card(String color, int value) {
    setColor(color);
    setValue(value);
  }

  public static boolean isValidColor(String color) {
    for (String validColor : COLORS) {
      if (validColor.equals(color)) {
        return true;
      }
    }
    return false;
  }

  public void setColor(String color) {
    if (isValidColor(color)) {
      this.color = color;
    } else {
      throw new IllegalArgumentException("Invalid color: " + color);
    }
  }

  public void setValue(int value) {
    if (value >= MIN_VALUE && value <= MAX_VALUE) {
      this.value = value;
    } else {
      throw new IllegalArgumentException("Invalid value: " + value);
    }
  }

  public String getColor() {
    return this.color;
  }

  public int getValue() {
    return this.value;
  }

  @Override
  public String toString() {
    return this.color + " " + this.value;
  }

  public boolean matches(Card other) {
    if (other == null) {
      return false;
    }
    return this.color.equals(other.color) || this.value == other.value;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Card other = (Card) obj;
    return this.value == other.value && this.color.equals(other.color);
  }

  @Override
  public int hashCode() {
    return Objects.hash(color, value);
  }
}
