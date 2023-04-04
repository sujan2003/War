package cardGame;
/*
Name:  Sujan Niroula
Date:  March 20, 2023
Description:  
    <>
    The war game is run by this class. 
    There are two players: the user and the computer. 
    When the user presses enter, a card is given to the user. 
    When all of the cards in the deck have been taken, the game is over. 
    The winner is the one with the most cards.
    <>
Sources Cited:  Geeksforgeeks for HashSet revision 
*/

import java.io.IOException;
import java.util.HashSet;

public class War {

	//Deck object initialized
	Deck d = new Deck();

    //Using HashSet to create a set of cards for each player
	HashSet<Card> user = new HashSet<Card>();
	HashSet<Card> computer = new HashSet<Card>();

	
	//set of cards still in play
	HashSet<Card> cardsInP = new HashSet<>();
	
	//total number of cards drawn out of deck
	int cardsDrawn = 0;

	public static void main(String[] args) {
		War war = new War();
		war.startGame();
        
	} 

    //prompt user entry
	public static void promptEnterKey() {
		try {
			System.in.read(new byte[2]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	//game starts here
	public void startGame() {

		//shuffle
		d.shuffle();

		//number of drawn cards should not be greater than 52
		while (cardsDrawn <= 52) {

			Card userCard = userDrawsCard();
			Card computerCard = computerDrawsCard();
			
			//add each card in play to cardsInP set
			cardsInP.add(userCard);
			cardsInP.add(computerCard);
			

            //Checking to see which card is bigger
			String winner = checkBiggerCard(userCard, computerCard);

			//Deciding who takes the card depending on who has bigger card
			if (winner.equals("User")) {
				userTakesCard();

			} 
			else if (winner.equals("Computer")) {
				computerTakesCard();

			} else {
                //starting the WAR since the cards are equal
				staleMateCheck();
			}

		}

	}

	//method fot when WAR occurs
	private void staleMateCheck() {
		
		//each player draws 2 cards

		Card userCard1 = userDrawsCard();
		Card userCard2 = userDrawsCard();

		cardsInP.add(userCard1);
		cardsInP.add(userCard2);
		
		Card computerCard1 = computerDrawsCard();
		Card computerCard2 = computerDrawsCard();

		cardsInP.add(computerCard1);
		cardsInP.add(computerCard2);

        //Checking to see which card is bigger
		String winner = checkBiggerCard(userCard1, computerCard1);
		
		//Deciding who takes the card depending on who has bigger card
		if (winner.equals("User")) {
			userTakesCard();
			return;

		} else if (winner.equals("Computer")) {
			computerTakesCard();
			return;
		}

        //if the first two cards are equal then comparing second two
        winner = checkBiggerCard(userCard2, computerCard2);
        
        //Deciding who takes the card depending on who has bigger card
        if (winner.equals("User")) {
            userTakesCard();
            return;

        } else if (winner.equals("Computer")) {
            computerTakesCard();
            return;

        } else {
            //restarting the War process if both cards are still equal
            staleMateCheck();
        }

	}

	//user draws a card
	private Card userDrawsCard() {
		System.out.println("Press enter to draw card: ");
		promptEnterKey();
		System.out.println("User Drawing...");

		Card c = d.draw();
		
		//game ends when no card is drawn
		if (c == null) {
			endGame();
			return null;
		}

        //incrementing the cardsDrawn after drawing a card
		cardsDrawn++;
		return c;

	}

	//computer draws a card
	private Card computerDrawsCard() {
		System.out.println("Computer Drawing...");
		Card c = d.draw();
		
		//game ends when no card is drawn
		if (c == null) {
			endGame();
			return null;
		}
        //incrementing the cardsDrawn after drawing a card
		cardsDrawn++;
		return c;
	}

	//user wins and takes all cards in pot
	private void userTakesCard() {
		for (Card c: cardsInP) {
			user.add(c);
		}
		cardsInP.removeAll(cardsInP);
		System.out.println("Cards with User: " + user.size() + "\n");

	}

	//computer wins and takes all cards in pot
	private void computerTakesCard() {
		for (Card c: cardsInP) {
			computer.add(c);
		}
		cardsInP.removeAll(cardsInP);
		System.out.println("Cards with Computer: " + computer.size() + "\n");
	}

	//end of the game
	private void endGame() {
		if (user.size() > computer.size()) {
			System.out.println("User is Winner. Cards: " + user.size() );
		} 
        else if (user.size() < computer.size()) {
			System.out.println("Computer is Winner. Cards: " + computer.size());
		}
        else {
            System.out.println("Game is Draw");
        }
		System.exit(0);

	}



	//condition to check whose card is bigger
	private String checkBiggerCard(Card userCard, Card computerCard) {
		if (userCard.getRank() > computerCard.getRank()) {
			System.out.println("Winner of this round: User ");
			return "User";
		} 
        else if (userCard.getRank() < computerCard.getRank()) {
			System.out.println("Winner of this round: Computer" );
			return "Computer";
		}
		System.out.println("WAR...\n" );
		return "None";
	}

}