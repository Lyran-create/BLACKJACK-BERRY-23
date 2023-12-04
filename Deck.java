import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<>();
        initializeDeck();
        shuffleDeck();
    }

    private void initializeDeck() {
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};

        for (String suit : suits) {
            for (String rank : ranks) {
                int value = getValueFromRank(rank);
                Card card = new Card(rank, suit, value);
                cards.add(card);
            }
        }
    }

    private void shuffleDeck() {
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        if (!cards.isEmpty()) {
            return cards.remove(0);
        }
        return null;
    }

    private int getValueFromRank(String rank) {
        switch (rank) {
            case "2": case "3": case "4": case "5": case "6": case "7": case "8": case "9":
                return Integer.parseInt(rank);
            case "10": case "J": case "Q": case "K":
                return 10;
            case "A":
                return 11; // Assume A is 11, logic for 1 can be added later
            default:
                return 0;
        }
    }
}