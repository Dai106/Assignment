package ass4;

import java.util.Scanner;

public class Blackjack {
	public static void main(String[] args) {
		System.out.println("Welcome! Now start!");
		Scanner sc = new Scanner(System.in);
		Deck deck = new Deck();
		deck.shuffle();
		Card playerCard = deck.deal();
		Card dealerCard = deck.deal();
		System.out.println("Your first card is: " + playerCard.toString());
		int playerScore = playerCard.getValue();
		int dealerScore = dealerCard.getValue();
		// player turn
		while (true) {
			if (playerScore > 21) {
				System.out.println("You lose!");
				System.out.println("END!");
				return;
			}
			System.out.println("Do you want a card? (y/n)");
			String s = sc.nextLine();
			if (s.equals("y")) {
				Card next = deck.deal();
				System.out.println(next);
				playerScore += next.getValue();
			} else {
				break;
			}
		}
		// dealer turn
		System.out.println("AI's turn!");
		System.out.println("It's first card: " + dealerCard.toString());
		System.out.println("It gets the following cards: ");
		while (true) {
			Card next = deck.deal();
			System.out.println(next);
			dealerScore += next.getValue();
			if (dealerScore > 21) {
				System.out.println("AI exceed 21 points, you win!");
				break;
			} else if (dealerScore > playerScore) {
				System.out.println("AI gets higher score than you, you lose!");
				break;
			} else if (dealerScore == playerScore) {
				System.out.print("tie!");
				break;
			}
		}

	}
}
