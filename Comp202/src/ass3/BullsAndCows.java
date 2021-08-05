package ass3;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BullsAndCows {
	public static void main(String[] args) {
		playBullsAndCows(123);
	}

	/**
	 * Check if the specified integer is an element of the given array
	 * 
	 * @param arr
	 * @param target
	 * @return true if arr contains target; false otherwise
	 */
	public static boolean contains(int[] arr, int target) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == target) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Generate an array containing four digits that are all different from one
	 * another
	 * 
	 * @param seed: the seed for Random generator
	 * @return: the 4 digits int array
	 */
	public static int[] generateSecretDigits(int seed) {
		Random random = new Random(seed);
		int[] result = new int[4];
		int index = 0;
		while (index < 4) {
			int current = random.nextInt(10);
			for (int i = 0; i < index; i++) {
				if (current == result[i])
					continue;
			}
			result[index] = current;
			index++;
		}
		return result;
	}

	/**
	 * Given an integer, translate it into an int array with at least 4 digits
	 * 
	 * @param target: the target int
	 * @return return the digit in int[] form. If length < 4, fill with leading 1s.
	 */
	public static int[] extractDigits(int target) {
		ArrayList<Integer> extract = new ArrayList<>();
		while (target > 0) {
			extract.add(0, target % 10);
			target = target / 10;
		}
		while (extract.size() < 4) {
			extract.add(0, 0);
		}
		int[] result = new int[extract.size()];
		for (int i = 0; i < extract.size(); i++) {
			result[i] = extract.get(i);
		}
		return result;
	}

	/**
	 * Get the number of bulls that the player get in this round. If the matching
	 * digits are in the correct position, called bulls.
	 * 
	 * @param secret: secret number
	 * @param guess:  number guessed by the player
	 * @return number of bulls
	 */
	public static int getNumOfBulls(int[] secret, int[] guess) {
		// check for length
		if (secret.length != guess.length) {
			throw new IllegalArgumentException("The guessing number has different digits with the secret number.");
		}
		int bulls = 0;
		for (int i = 0; i < secret.length; i++) {
			if (secret[i] == guess[i]) {
				bulls++;
			}
		}
		return bulls;
	}

	/**
	 * Get the number of cows that the player get in this round. matching digits in
	 * different position are in the correct position, called bulls.
	 * 
	 * @param secret: secret number
	 * @param guess:  number guessed by the player
	 * @return number of cows
	 */
	public static int getNumOfCows(int[] secret, int[] guess) {
		// check for length
		if (secret.length != guess.length) {
			throw new IllegalArgumentException("The guessing number has different digits with the secret number.");
		}
		int cows = 0;
		for (int i = 0; i < guess.length; i++) {
			for (int j = 0; j < secret.length; j++) {
				if ((guess[i] == secret[j]) && (i != j)) {
					cows++;
				}
			}
		}
		return cows;
	}

	public static void playBullsAndCows(int seed) {
		System.out.println("Hey, you! Want to play a game?");
		System.out.println("Do you think you can crack the code?");
		System.out.println();
		// generate a secret number
		int[] secret = generateSecretDigits(seed);

		Scanner sc = new Scanner(System.in);
		int round = 0;
		while (true) {
			if (round >= 5) {
				System.out.println("Might be time for you to give up. Do you want to quit? (y/n)");
				// buffer clean
				sc.nextLine();
				String s = sc.nextLine();
				System.out.println();
				if (s.equals("y")) {
					System.out.printf("You gave up after %d tries.\n", round);
					sc.close();
					return;
				}
			}
			round++;
			System.out.printf("Guess #%d. Enter a four digit number: ", round);
			int input = sc.nextInt();
			// check for input
			if (input < 0 || input > 9999) {
				System.out.println("You must enter a positive integer with at most four digits. Try again!");
				System.out.println();
				continue;
			}
			// display the bulls and cows
			int[] guess = extractDigits(input);
			int cows = getNumOfCows(secret, guess);
			int bulls = getNumOfBulls(secret, guess);
			System.out.printf("Bulls: %d, Cows: %d\n\n", bulls, cows);

			// check if crack the code
			if (bulls == 4) {
				System.out.printf("Congratulations! You cracked the code in only %d times.", round);
				sc.close();
				return;
			}

		}

	}
}
