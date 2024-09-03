import java.util.Scanner;

public class CardMatch {
  public static final int NUM_PLAYERS = 2;
  public static final int NUM_INIT_CARDS = 5;
  public static final int MAX_CARDS = 10;
  public static final String[] COLORS = { "blue", "green", "red", "yellow" };

  private Scanner console;
  private Deck deck;
  private Player[] players;
  private Card topDiscard;

  public CardMatch(Scanner console, int randomSeed, String playerName) {
    this.console = console; // Scanner instance for reading input
    this.deck = new Deck(randomSeed);
    this.deck.shuffle();
    this.players = new Player[NUM_PLAYERS];
    this.players[0] = new Player(playerName);
    this.players[1] = new ComputerPlayer("the computer");
    this.dealHands();
    this.topDiscard = this.deck.drawCard();
  }

  public void dealHands() {
    for (int i = 0; i < NUM_INIT_CARDS; i++) {
      for (int j = 0; j < NUM_PLAYERS; j++) {
        this.players[j].addCard(this.deck.drawCard());
      }
    }
  }

  public void play() {
    this.printGameState();
    while (true) {
      for (int i = 0; i < this.players.length; i++) {
        executeOnePlay(this.players[i]);
      }
      this.printGameState();
      if (this.gameOver()) {
        return;
      }
    }
  }

  public void printGameState() {
    System.out.println();
    for (int i = 0; i < this.players.length; i++) {
      this.players[i].displayHand();
    }
    System.out.println("discard pile: ");
    System.out.println("  " + this.topDiscard);
  }

  public void executeOnePlay(Player player) {
    while (true) {
      int cardNum = player.getPlay(this.console, this.topDiscard);

      if (cardNum == -1) {
        System.out.println(player + " draws a card.");
        player.addCard(this.deck.drawCard());
        return;
      } else {
        if (cardNum < 0 || cardNum >= player.getNumCards()) {
          System.out.println("Invalid card number.");
          continue;
        }

        Card cardToPlay = player.getCard(cardNum);

        if (cardToPlay.matches(this.topDiscard)) {
          System.out.println(player + " discards " + cardToPlay + ".");
          player.removeCard(cardNum);
          this.topDiscard = cardToPlay;
          return;
        } else {
          System.out.println("Invalid play -- " + cardToPlay + " doesn't match top of discard pile");
        }
      }
    }
  }

  public boolean gameOver() {
    for (int i = 0; i < this.players.length; i++) {
      if (this.players[i].getNumCards() == 0 || this.players[i].getNumCards() == MAX_CARDS) {
        return true;
      }
    }
    return false;
  }

  public void reportResults() {
    int totalValue = 0;
    int winnerIndex = 0;
    int winnerValue = this.players[0].getHandValue();
    totalValue += winnerValue;
    boolean isTie = false;

    for (int i = 1; i < this.players.length; i++) {
      int playerValue = this.players[i].getHandValue();
      totalValue += playerValue;

      if (playerValue < winnerValue) {
        winnerValue = playerValue;
        winnerIndex = i;
        isTie = false;
      } else if (playerValue == winnerValue) {
        isTie = true;
      }
    }

    if (isTie) {
      System.out.println("The game is a tie; no one earns any points.");
    } else {
      System.out.print("The winner is " + this.players[winnerIndex]);
      System.out.print(", who earns " + (totalValue - winnerValue));
      System.out.println(" points.");
    }
  }

  // Encapsulated method for getting user input
  public static String getUserInput(Scanner console, String prompt) {
    System.out.print(prompt);
    return console.nextLine();
  }

  public static void main(String[] args) {
    Scanner console = new Scanner(System.in);

    int seed = -1;
    if (args.length > 0) {
      seed = Integer.parseInt(args[0]);
    }

    // Use the getUserInput method for reading input
    String name = getUserInput(console, "Welcome to the game of Card Match! What's your first name? ");

    CardMatch game = new CardMatch(console, seed, name);
    game.play();
    game.reportResults();

    console.close();
  }
}
