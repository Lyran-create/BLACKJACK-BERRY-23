import java.util.ArrayList;

public class Hand {
    protected ArrayList<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int calculateHandValue() {
        int value = 0;
        int numAces = 0;

        for (Card card : cards) {
            value += card.getValue();

            if (card.getRank().equals("A")) {
                numAces++;
            }
        }

        // Handle Aces
        while (numAces > 0 && value > 21) {
            value -= 10;
            numAces--;
        }

        return value;
    }

    public boolean hasBlackjack() {
        return cards.size() == 2 && calculateHandValue() == 21;
    }

    public boolean isBusted() {
        return calculateHandValue() > 21;
    }

    public void displayHand() {
        System.out.println("Hand: " + cards);
    }
}