import java.util.Scanner;

public class Player {
  private String name;
  private Card[] hand;
  private int numCards;

  public Player(String name) {
    this.name = name;
    this.hand = new Card[CardMatch.MAX_CARDS];
    this.numCards = 0;
  }

  public String getName() {
    return name;
  }

  public int getNumCards() {
    return numCards;
  }

  @Override
  public String toString() {
    return name;
  }

  public void addCard(Card card) {
    if (card == null || numCards == hand.length) {
      throw new IllegalArgumentException();
    }
    hand[numCards] = card;
    numCards++;
  }

  public Card getCard(int index) {
    if (index < 0 || index >= numCards || hand[index] == null) {
      throw new IllegalArgumentException();
    }
    return hand[index];
  }

  public int getHandValue() {
    int result = 0;
    for (int i = 0; i < numCards; i++) {
      result += hand[i].getValue();
    }
    return result;
  }

  public void displayHand() {
    System.out.println(name + "'s hand:");
    for (int i = 0; i < numCards; i++) {
      System.out.println("  " + i + ": " + hand[i].getColor() + " " + hand[i].getValue());
    }
  }

  public Card removeCard(int index) {
    if (index < 0 || index >= numCards) {
      throw new IndexOutOfBoundsException();
    }
    Card removedCard = hand[index];
    hand[index] = hand[numCards - 1];
    hand[numCards - 1] = null;
    numCards--;
    return removedCard;
  }

  public int getPlay(Scanner input, Card discard) {
    System.out.println(name + ": Enter a number!");
    int play = input.nextInt();
    if (play == -1) {
      return -1;
    } else if (play < 0 || play >= numCards) {
      throw new IllegalArgumentException();
    }
    return play;
  }
}
