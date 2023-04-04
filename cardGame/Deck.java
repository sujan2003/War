/*
Name: Sujan	
Date: 03/17/2023
Description: 
	<>
	It is a blueprint for creating a card deck. 
	It has a couple of functionality such as shuffling, drawing a card from the deck, 
	checking if the deck is empty, and swapping two cards
	<>
*/

package cardGame;

public class Deck {
	private Card[] deck;
	private int top;
	
	public Deck()
	{
		//number of cards in the deck
		deck = new Card[52];

		//position of the deck
		int pos = 0;

		//all the card types 
		char[] letters = {'C', 'D', 'S', 'H'};

		//gets each card type from the array
		for (char letter : letters){
			//running the loop 13 
			for (int c = 2; c < 15; c++){
				//adding cards to the deck array
				deck[pos] = new Card(c, letter); 
				//incrementing the position for the deck
				pos++;
			}
		}
		//assigning top to 51
		top = 51;
	}
	
	// shuffle the deck
	public void shuffle()
	{
		//looping the number of cards in the deck 
		for (int i = 0; i < deck.length; i++) {
			//getting a random number between 0 and 51
			int randNum = (int) (Math.random() * deck.length);

			//swapping the cards using the swap method 
			swap(i, randNum);
		}
	}
	
	// drawing a card
	public Card draw()
	{
		//if there is no card on the top
		if (top < 0) {
			System.out.println("\nNo more cards");
			return null;
		}

		//storing the card on the top
		Card toDraw = deck[top];
	
		//decrementing the top after drawing 
		top--;

		//priting the card 
		System.out.println("The card is: " + toDraw.getSuit() + ", " + toDraw.getRank()+ "\n");

		//returning thecard on the top
		return toDraw;
	}
	
	// check if the deck is empty
	public boolean isEmpty()
	{
		if (deck.length == 0)
			return true;

		return false;
	}
	
	// swap two cards
	private void swap(int i, int j)
	{
		Card temporary = deck[i];
		deck[i] = deck[j];
		deck[j] = temporary;
	}
	
}