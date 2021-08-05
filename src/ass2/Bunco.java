package ass2;

import java.util.Scanner;

public class Bunco {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Player one name: ");
		String player1 = sc.nextLine();
		System.out.println("Player two name: ");
		String player2 = sc.nextLine();
		sc.close();
		playBunco(player1, player2);
	}

	/**
	 * simulate a dice roll, produce an int between [1,6]
	 * 
	 * @return the dice number (int)
	 */
	public static int diceRoll() {
		int number = 1 + (int) (Math.random() * 6);
		return number;
	}

	/**
	 * This method computes the score of a play for a round
	 * 
	 * @param roll1:    the number for dice 1
	 * @param roll2:    the number for dice 2
	 * @param roll3:    the number for dice 3
	 * @param roundNum: the number of this round
	 * @return the score that the player get for this round.
	 */
	public static int getScore(int roll1, int roll2, int roll3, int roundNum) {
		int score = 0;
		// if it's a bunco
		if (roll1 == roll2 && roll1 == roll3 && roll1 == roundNum) {
			score = 21;
		} else if (roll1 == roll2 && roll1 == roll3) {
			score = 5;
		} else {
			if (roll1 == roundNum) {
				score += 1;
			}
			if (roll2 == roundNum) {
				score += 1;
			}
			if (roll3 == roundNum) {
				score += 1;
			}
		}

		return score;
	}

	/**
	 * This method simulates one player playing one round of Bunco, and return his
	 * score.
	 * 
	 * @param name:     the name of the player
	 * @param roundNum: the round number
	 * @return -1 if the input round number is invalid. otherwise, return the score
	 *         that the player get in this round.
	 */
	public static int playOneRound(String name, int roundNum) {
		// check if the roundNum is valid
		if (roundNum < 1 || roundNum > 6) {
			System.out.println(
					"The input is invalid, please input a String for player name and an int between 1 and 6 (both included) for the round number.");
			return -1;
		}
		// roll the three dice.
		int roll1 = diceRoll();
		int roll2 = diceRoll();
		int roll3 = diceRoll();
		// get the score
		int score = getScore(roll1, roll2, roll3, roundNum);
		System.out.printf("%s rolled %d %d %d and scored %d points\n", name, roll1, roll2, roll3, score);
		return score;
	}

	public static void playBunco(String player1, String player2) {
		int score1 = 0;
		int score2 = 0;
		for (int i = 1; i <= 6; i++) {
			System.out.printf("ROUND #:%d\n\n", i);
			score1 += playOneRound(player1, i);
			score2 += playOneRound(player2, i);
			System.out.println();
			System.out.printf("%s's score after round %d is %d\n", player1, i, score1);
			System.out.printf("%s's score after round %d is %d\n", player2, i, score2);
			System.out.println("------------------------------------------");
		}
		System.out.println();
		if (score1 > score2) {
			System.out.println(player1 + " wins!");
		} else if (score1 < score2) {
			System.out.println(player2 + " wins!");
		} else {
			System.out.println("Tie!");
		}
	}

}
