package BlackJack;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffle();
        System.out.println("Welcome to Black Jack");
        Deck playerDeck = new Deck();
        Deck dealerDeck = new Deck();

        double playerMoney = 100.00;


        Scanner userInput = new Scanner(System.in);

        //Game Loop
        while (playerMoney > 0) {
            boolean endRound = false;
            System.out.println("You have $" + playerMoney + ", how much would you like to bet?");
            double playerBet = userInput.nextDouble();
            if (playerBet > playerMoney) {
                System.out.println("You cannot bet more than you have.");
                break;
            }

            //start Dealing.
            //Player gets two cards
            playerDeck.draw(playingDeck);
            playerDeck.draw(playingDeck);

            //Dealer gets two cards as well.
            dealerDeck.draw(playingDeck);
            dealerDeck.draw(playingDeck);

            while (true) {
                System.out.println("Your hand: " + playerDeck.toString());
                System.out.println("Your value of your cards : ");
                System.out.println(playerDeck.cardsSumValue());

                System.out.println("Dealer hand: ");
                System.out.println(dealerDeck.getCard(0).toString() + " and Hidden");


                System.out.println("Would you like to (1)hit or (2)stand.");
                int response = userInput.nextInt();
                if (response == 1) {
                    playerDeck.draw(playingDeck);
                    System.out.println("You draw a : " + playerDeck.getCard(playerDeck.deckSize() - 1).toString());

                    //Bust
                    if (playerDeck.cardsSumValue() > 21) {
                        System.out.println("Bust. You lost the game.");
                        playerMoney -= playerBet;
                        endRound = true;
                        break;
                    }
                }
                if (response == 2) {
                    break;
                }
            }

            //Reveal dealers cards.
            System.out.println("Dealers Cards are : " + dealerDeck.toString());

            //Dealer Wins.
            if (dealerDeck.cardsSumValue() > playerDeck.cardsSumValue() && !endRound) {
                System.out.println("Dealer beats you");
                playerMoney -= playerBet;
                endRound = true;
            }

            //Dealer draws at 16, stands at 17
            while (dealerDeck.cardsSumValue() < 17 && !endRound) {
                dealerDeck.draw(playingDeck);
                System.out.println("dealer draws a card : " + dealerDeck.getCard(dealerDeck.deckSize() - 1).toString());
            }
            System.out.println("Dealers BlackJack.Card value : " + dealerDeck.cardsSumValue());

            //Dealer Bust.
            if (dealerDeck.cardsSumValue() > 21 && !endRound) {
                System.out.println("Dealer bust. You won");
                playerMoney += playerBet;
                endRound = true;
            }

            //Dealer and player have the same value, push to the next round
            if (dealerDeck.cardsSumValue() == playerDeck.cardsSumValue() && !endRound) {
                System.out.println("Draw");
                endRound = true;
            }

            //Player cards value bigger than dealers cards value

            if (playerDeck.cardsSumValue() > dealerDeck.cardsSumValue() && !endRound) {
                System.out.println("You won the game");
                playerMoney += playerBet;
                endRound = true;
            } else if (!endRound) {
                System.out.println("You lost the game, dealer has bigger value than yours.");
                playerMoney -= playerBet;
                endRound = true;
            }


            playerDeck.returnAllCardstoDeck(playingDeck);
            dealerDeck.returnAllCardstoDeck(playingDeck);

            System.out.println("the game has ended. All cards have been returned. ");

        }

        System.out.println("Game over.");
    }
}
