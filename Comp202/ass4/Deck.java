package ass4;

import java.util.Random;

public class Deck {
	private Card[] cards;
	private int numCardLeft;
	private static Random random = new Random(123);

	public Deck() {
		this.cards = new Card[52];
		String[] suit = { "Spades", "Hearts", "Diamonds", "Clubs" };
		int index = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 13; j >= 1; j--) {
				this.cards[index] = new Card(j, suit[i]);
				index++;
			}
		}
		this.numCardLeft = 52;
	}

	public int getNumOfCards() {
		return this.numCardLeft;
	}

	public Card[] getCards() {
		Card[] copy = new Card[this.cards.length];
		for (int i = 0; i < this.cards.length; i++) {
			copy[i] = this.cards[i];
		}
		return copy;
	}

	/**
	 * Display the Card from top to bottom. Note that the order displayed is
	 * opposite with the order in the array.
	 */
	public void showCards() {
		for (int i = this.numCardLeft - 1; i >= 0; i--) {
			System.out.print(this.cards[i]);
			if (i != 0) {
				System.out.print(", ");
			} else {
				System.out.println(".");
			}
		}
	}

	/**
	 * Shuffles the cards left in the deck, by using the random generator. Use the
	 * random generator to generate 2 indices, and shift the chards in these 2
	 * positions
	 */
	public void shuffle() {
		for (int i = 0; i < 1000; i++) {
			int a = random.nextInt(this.numCardLeft);
			int b = random.nextInt(this.numCardLeft);
			Card current = this.cards[a];
			this.cards[a] = this.cards[b];
			this.cards[b] = current;
		}
	}

	/**
	 * Remove the top card
	 * 
	 * @return the removed card; null if there's no more card left
	 */
	public Card deal() {
		// check for number of cards left
		if (this.numCardLeft == 0) {
			return null;
		}
		this.numCardLeft--;
		return this.cards[this.numCardLeft];
	}

	/**
	 * Return the card at the position given by the player The returned card should
	 * be removed (cannot appear anymore).
	 * 
	 * @param n the position given from the player (that's in the player's point of
	 *          view)
	 * @return null if there's no enough card left; otherwise, the card at that
	 *         position.
	 */
	public Card pickACard(int n) {
		// check to see if there's no enough cards left
		if (n > this.numCardLeft - 1) {
			return null;
		}
		int position = this.numCardLeft - n - 1;
		Card target = this.cards[position];
		// move this card
		for (int i = position; i < this.numCardLeft; i++) {
			this.cards[i] = this.cards[i + 1];
		}
		this.cards[this.numCardLeft - 1] = target;
		this.numCardLeft--;
		return target;
	}

	/**
	 * Resets the deck back to its original form
	 */
	public void restockDeck() {
		this.numCardLeft = 52;
		this.cards = new Deck().getCards();
	}
}
