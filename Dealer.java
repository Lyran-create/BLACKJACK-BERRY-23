public class Dealer extends Hand {
    public boolean shouldHit() {
        return calculateHandValue() < 17;
    }

    public void resetHand() {
        cards.clear();
    }
}