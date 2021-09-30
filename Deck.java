package BlackJack;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private ArrayList<Card> cards;

    public Deck() {
        this.cards = new ArrayList<Card>();
    }

    public void createFullDeck() {
        for (Suit cardSuit : Suit.values()) {
            for (Number number : Number.values()) {
                this.cards.add(new Card(cardSuit, number));
            }
        }
    }

    public void shuffle() {
        ArrayList<Card> tempDeck = new ArrayList<>();
        Random random = new Random();
        int randomCardIndex = 0;
        int cardDeckSize = this.cards.size();
        for (int i = 0; i < cardDeckSize; i++) {
            //Generate random index formula "rand.nextInt((max - min) + 1) + min;
            randomCardIndex = random.nextInt((this.cards.size() - 1) + 1);
            tempDeck.add(this.cards.get(randomCardIndex));
            this.cards.remove(randomCardIndex);
        }
        this.cards = tempDeck;
    }


    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Card aCard : this.cards) {
            output.append("\n ").append(aCard);
        }
        return output.toString();
    }

    public void removeCard(int i) {
        this.cards.remove(i);
    }

    public Card getCard(int i) {
        return this.cards.get(i);
    }

    public void addCard(Card addCard) {
        this.cards.add(addCard);
    }

    public void draw(Deck comingFrom) {
        this.cards.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }

    public int cardsSumValue() {
        int totalValue = 0;
        int aces = 0;

        for (Card aCard : this.cards) {
            switch (aCard.getNumber()) {
                case Two -> totalValue += 2;
                case Three -> totalValue += 3;
                case Four -> totalValue += 4;
                case Five -> totalValue += 5;
                case Six -> totalValue += 6;
                case Seven -> totalValue += 7;
                case Eight -> totalValue += 8;
                case Nine -> totalValue += 9;
                case Ten, Queen, King, Jack -> totalValue += 10;
                case Ace -> aces += 1;
            }
            for (int i = 0; i < aces; i++) {
                if (totalValue > 10) {
                    totalValue += 1;
                } else {
                    totalValue += 11;
                }
            }
        }

        return totalValue;
    }

    public int deckSize() {
        return this.cards.size();
    }

    public void returnAllCardstoDeck(Deck playedDeck) {
        int deckSize = this.cards.size();

        for (int i = 0; i < deckSize; i++) {
            playedDeck.addCard(this.cards.get(i));
        }
        for (int i = 0; i < deckSize; i++) {
            this.removeCard(0);
        }
    }

}

