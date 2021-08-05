package ass4;

public class Card {
	private int value;
	private String suit;

	public Card(int value, String suit) {
		if (value < 0 || value > 13) {
			throw new IllegalArgumentException("The card value shoule be between 1 and 13 (both included).");
		}
		if (!suit.equalsIgnoreCase("Spades") && !suit.equalsIgnoreCase("Hearts") && !suit.equalsIgnoreCase("Clubs")
				&& !suit.equalsIgnoreCase("Diamonds")) {
			throw new IllegalArgumentException(
					"The type of card should be one of \"spades\", \"hearts\", \"diamonds\" or \"clubs\", ignore the case");
		}
		this.value = value;
		this.suit = suit;
	}

	public int getValue() {
		return this.value;
	}

	public String getSuit() {
		return this.suit;
	}

	// @Override
	public String toString() {
		return this.value + " of " + this.suit;
	}
}
