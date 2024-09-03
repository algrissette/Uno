import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
  private ArrayList<Card> cards;
  private Random random;

  public Deck(int randomSeed) {
    this.cards = new ArrayList<>();
    this.random = new Random(randomSeed);
    this.initializeDeck();
  }

  private void initializeDeck() {
    for (int value = Card.MIN_VALUE; value <= Card.MAX_VALUE; value++) {
      for (String color : Card.COLORS) {
        this.cards.add(new Card(color, value));
      }
    }
  }

  public void shuffle() {
    Collections.shuffle(this.cards, this.random);
  }

  public Card drawCard() {
    if (this.cards.isEmpty()) {
      throw new IllegalStateException("The deck is empty.");
    }
    return this.cards.remove(this.cards.size() - 1);
  }

  public int size() {
    return this.cards.size();
  }
}
