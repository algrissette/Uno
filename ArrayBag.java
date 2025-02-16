
public class ArrayBag {
  /**
   * The array used to store the items in the bag.
   */
  private Object[] items;

  /**
   * The number of items in the bag.
   */
  private int numItems;

  public static final int DEFAULT_MAX_SIZE = 50;

  /**
   * Constructor with no parameters - creates a new, empty ArrayBag with
   * the default maximum size.
   */
  public ArrayBag() {
    this.items = new Object[DEFAULT_MAX_SIZE];
    this.numItems = 0;
  }

  /**
   * A constructor that creates a new, empty ArrayBag with the specified
   * maximum size.
   */
  public ArrayBag(int maxSize) {
    if (maxSize <= 0) {
      throw new IllegalArgumentException("maxSize must be > 0");
    }
    this.items = new Object[maxSize];
    this.numItems = 0;
  }

  /**
   * numItems - accessor method that returns the number of items
   * in this ArrayBag.
   */
  public int numItems() {
    return this.numItems;
  }

  /**
   * add - adds the specified item to this ArrayBag. Returns true if there
   * is room to add it, and false otherwise.
   * Throws an IllegalArgumentException if the item is null.
   */
  public boolean add(Object item) {
    if (item == null) {
      throw new IllegalArgumentException("item must be non-null");
    } else if (this.numItems == this.items.length) {
      return false; // no more room!
    } else {
      this.items[this.numItems] = item;
      this.numItems++;
      return true;
    }
  }

  /**
   * remove - removes one occurrence of the specified item (if any)
   * from this ArrayBag. Returns true on success and false if the
   * specified item (i.e., an object equal to item) is not in this ArrayBag.
   */
  public boolean remove(Object item) {
    for (int i = 0; i < this.numItems; i++) {
      if (this.items[i].equals(item)) {
        // Shift the remaining items left by one.
        for (int j = i; j < this.numItems - 1; j++) {
          this.items[j] = this.items[j + 1];
        }
        this.items[this.numItems - 1] = null;

        this.numItems--;
        return true;
      }
    }

    return false; // item not found
  }

  /**
   * contains - returns true if the specified item is in the Bag, and
   * false otherwise.
   */
  public boolean contains(Object item) {
    for (int i = 0; i < this.numItems; i++) {
      if (this.items[i].equals(item)) {
        return true;
      }
    }

    return false;
  }

  /**
   * grab - returns a reference to a randomly chosen item in this ArrayBag.
   */
  public Object grab() {
    if (this.numItems == 0) {
      throw new IllegalStateException("the bag is empty");
    }

    int whichOne = (int) (Math.random() * this.numItems);
    return this.items[whichOne];
  }

  /**
   * toArray - return an array containing the current contents of the bag
   */
  public Object[] toArray() {
    Object[] copy = new Object[this.numItems];

    for (int i = 0; i < this.numItems; i++) {
      copy[i] = this.items[i];
    }

    return copy;
  }

  /**
   * toString - converts this ArrayBag into a string that can be printed.
   * Overrides the version of this method inherited from the Object class.
   */
  public String toString() {
    String str = "{";

    for (int i = 0; i < this.numItems; i++) {
      str = str + this.items[i];
      if (i != this.numItems - 1) {
        str += ", ";
      }
    }

    str = str + "}";
    return str;
  }

  /* Test the ArrayBag implementation. */
  public static void main(String[] args) {
    ArrayBag b1 = new ArrayBag(6);
    String[] words1 = { "hello", "you", "how", "are", "you", "today?" };
    for (String w : words1) {
      b1.add(w);
    }
    System.out.println(b1);

    ArrayBag b2 = new ArrayBag(6);
    String[] words2 = { "not", "bad", "how", "are", "you", "doing" };
    for (String w : words2) {
      b2.add(w);
    }
    System.out.println(b2);

    System.out.println(b1.removeItems(b2));
    System.out.println(b1);
    System.out.println(b2);
  }

  public int roomLeft() {
    // shows how many null spots are left in array
    return items.length - numItems;
  }

  public boolean isFull() {
    // true if the bag has a full amoiunt of values
    if (items.length == numItems) {
      return true;
    } else {
      return false;
    }
  }

  public void increaseCapacity(int increment) {
    // increases how much the bag can hold
    Object[] newitems = new Object[items.length + increment];
    for (int i = 0; i < items.length; i++) {
      newitems[i] = items[i];
    }
    this.items = newitems;
  }

  public boolean removeItems(ArrayBag other) {
    // Removes items
    boolean result = false;
    for (int i = 0; i < other.numItems; i++) {
      if (contains(other.items[i])) {
        remove(items[i]);
        result = true;
      }

    }

    return result;

  }

  public ArrayBag intersectionWith(ArrayBag other) {
    // sees intersection bags and if they have the same values
    ArrayBag newarray = new ArrayBag();

    if (items.length == 0 || other.items.length == 0) {
      return newarray;

    } else {
      for (int i = 0; i < numItems; i++) {
        for (int j = 0; j < other.items.length; j++) {
          if (items[i] == other.items[j]) {
            if (newarray.contains(items[i]) == false) {
              if (newarray.add(items[i]) == false) {
                newarray.increaseCapacity(1);
                newarray.add(items[i]);
              }

            }
          }
        }

      }

    }
    return newarray;

  }
}