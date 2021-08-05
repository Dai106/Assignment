package ass4;

import java.util.ArrayList;

public class GameOfLife {

	public static void main(String[] args) {
		int[][] tub = { { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 0 }, { 0, 1, 0, 1, 0 }, { 0, 0, 1, 0, 0 }, { 0, 0, 0, 0, 0 } };
		int[][] toad = { { 0, 0, 0, 0, 0, 0 }, { 0, 0, 1, 1, 1, 0 }, { 0, 1, 1, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0 } };
		int[][] pentadec = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1, 0, 0, 0, 0 },
				{ 0, 0, 0, 1, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0, 1, 0, 0, 0 },
				{ 0, 0, 0, 0, 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 1, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
		simulateNGenerations(pentadec, 9);
	}

	/**
	 * A valid universe is rectangular. i.e. all sub-arrays have the same size and
	 * all the elements are either 0 or 1.
	 * 
	 * @param universe the possible universe needs to be checked.
	 * @return true if the input universe if valid; flase otherwise
	 */
	public static boolean isValidUniverse(int[][] universe) {
		// check for empty set
		if (universe.length == 0 || universe[0].length == 0) {
			return false;
		}
		// check for the length of all subarrays
		for (int i = 0; i < universe.length; i++) {
			if (universe[0].length != universe[1].length) {
				return false;
			}
			// check for all the elements
			for (int j = 0; j < universe[0].length; j++) {
				if (universe[i][j] != 0 && universe[i][j] != 1) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * display the universe with board and * for alive cells and blank for dead
	 * cells
	 * 
	 * @param universe target universe
	 * @pre assume the input universe is valid
	 */
	public static void displayUniverse(int[][] universe) {
		int length = universe.length + 2;
		int width = universe[0].length + 2;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				// check for corner '+'
				if ((i == 0 && j == 0) || (i == 0 && j == width - 1) || (i == length - 1 && j == 0)
						|| (i == length - 1 && j == width - 1)) {
					System.out.print("+");
				}
				// for the border
				else if (i == 0 || i == length - 1) {
					System.out.print("-");
				} else if (j == 0 || j == width - 1) {
					System.out.print("|");
				}
				// for alive cells
				else if (universe[i - 1][j - 1] == 1) {
					System.out.print("*");
				}
				// for dead cells
				else {
					System.out.print(" ");
				}
			}
			// change the line
			System.out.println();
		}
	}

	/**
	 * Get the state of the target cell in the next generation
	 * 
	 * @pre assume the given universe is valid and the poosition is valid
	 * @param universe
	 * @param x        position of the cell, x represents the sub-array
	 * @param y        position of the cell, y represents the element in a sub-array
	 * @return 1 for alive state; 0 for dead state
	 */
	public static int getNextGenCell(int[][] universe, int x, int y) {
		// number of alive neighbor
		int alive = 0;
		ArrayList<Integer> neighbor = getNeighbor(universe, x, y);
		for (int i : neighbor) {
			if (i == 1) {
				alive++;
			}
		}
		// if the given cell is alive now
		if (universe[x][y] == 1) {

			// dies with fewer than two live neighbors
			if (alive < 2) {
				return 0;
			}
			// lives with 2 or 3 live neighbors
			else if (alive == 2 || alive == 3) {
				return 1;
			}
			// dies with more than three live neighbors
			else {
				return 0;
			}
		} else {
			// lives with exactly 3 live neighbors
			if (alive == 3) {
				return 1;
			}
			return 0;
		}
	}

	/**
	 * Helper method to get the neighbors of the target cell
	 * 
	 * @param universe
	 * @param x
	 * @param y
	 * @return an array list of integer, contains the value of each neighbor
	 */
	private static ArrayList<Integer> getNeighbor(int[][] universe, int x, int y) {
		ArrayList<Integer> neighbor = new ArrayList<>();
		int width = universe[0].length;
		// vertically
		if (x - 1 >= 0) {
			neighbor.add(universe[x - 1][y]);
		}
		if (x + 1 < universe.length) {
			neighbor.add(universe[x + 1][y]);
		}

		// horizontally
		if (y - 1 >= 0) {
			neighbor.add(universe[x][y - 1]);
		}
		if (y + 1 < width) {
			neighbor.add(universe[x][y + 1]);
		}

		// diagonal
		if ((x - 1 >= 0) && (y - 1 >= 0)) {
			neighbor.add(universe[x - 1][y - 1]);
		}
		if ((x - 1 >= 0) && (y + 1 < width)) {
			neighbor.add(universe[x - 1][y + 1]);
		}
		if ((x + 1 < universe.length) && (y - 1 >= 0)) {
			neighbor.add(universe[x + 1][y - 1]);
		}
		if ((x + 1 < universe.length) && (y + 1 < width)) {
			neighbor.add(universe[x + 1][y + 1]);
		}

		return neighbor;
	}

	/**
	 * get the universe in its next generation
	 * 
	 * @param universe the original universe
	 * @return the universe in its next generation
	 */
	public static int[][] getNectGenUniverse(int[][] universe) {
		int[][] renew = new int[universe.length][universe[0].length];
		for (int i = 0; i < universe.length; i++) {
			for (int j = 0; j < universe[0].length; j++) {
				renew[i][j] = getNextGenCell(universe, i, j);
			}
		}
		return renew;
	}

	public static void simulateNGenerations(int[][] universe, int n) {
		// check the validation of the universe
		if (!isValidUniverse(universe)) {
			throw new IllegalArgumentException(
					"The input universe is invalid. A valid universe should be a rectangle and all elements are either 1 or 0.");
		}
		// check the validation of the number n
		if (n < 0) {
			throw new IllegalArgumentException("The input integer is invalid, it should be a integer >= 0.");
		}
		// display the original seed
		System.out.println("Original seed");
		displayUniverse(universe);
		for (int i = 1; i <= n; i++) {
			System.out.printf("Generation %d\n", i);
			universe = getNectGenUniverse(universe);
			displayUniverse(universe);
		}
	}
}
