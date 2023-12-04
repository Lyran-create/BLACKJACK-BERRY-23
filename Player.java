public class Player extends Hand {
    private String name;
    private int bankroll;
    private int betAmount;

    public Player(String name, int initialBankroll) {
        this.name = name;
        this.bankroll = initialBankroll;
        this.betAmount = 0;
    }

    public void placeBet(int bet) {
        if (bet > 0 && bet <= bankroll) {
            betAmount = bet;
            bankroll -= betAmount;
        } else {
            System.out.println("Invalid bet amount");
        }
    }

    public void winBet() {
        bankroll += 2 * betAmount;
        betAmount = 0;
    }

    public void tieBet() {
        bankroll += betAmount;
        betAmount = 0;
    }

    public void resetHand() {
        cards.clear();
    }
}