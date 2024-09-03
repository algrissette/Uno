import java.util.Scanner;

public class ComputerPlayer extends Player {
  public ComputerPlayer(String name) {
    super(name);
  }

  @Override
  public void displayHand() {
    System.out.println(getName() + "'s hand:");
    if (getNumCards() == 1) {
      System.out.println(getNumCards() + " card");
    } else {
      System.out.println("  " + getNumCards() + " cards");
    }
  }

  @Override
  public int getPlay(Scanner input, Card discard) {
    int bestCardIndex = -1;
    int highestValue = -1;
    for (int i = 0; i < getNumCards(); i++) {
      if (getCard(i).matches(discard) && getCard(i).getValue() > highestValue) {
        highestValue = getCard(i).getValue();
        bestCardIndex = i;
      }
    }
    return bestCardIndex;
  }
}
