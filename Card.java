package BlackJack;

public class Card {

    private Suit suit;
    private Number number;

    public Card(Suit suit, Number number) {
        this.suit = suit;
        this.number = number;
    }

    public String toString() {
        return this.suit.toString() + " - " + this.number.toString();
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public void setNumber(Number number) {
        this.number = number;
    }

    public Suit getSuit() {
        return suit;
    }

    public Number getNumber() {
        return number;
    }
}
