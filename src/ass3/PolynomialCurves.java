package ass3;

public class PolynomialCurves {
	public static void main(String[] args) {
		double[] cubic = { 1.0 / 25, 0, 0.1, -1.5 };
		drawCurve(cubic, 1, 'o');
	}

	/**
	 * Determines whither or not the given point should be considered to be part of
	 * the curve.
	 * 
	 * @param coord coordinates of a point on the plane
	 * @param coef  the coefficients of the poly function
	 * @param thick the thickness of the curve
	 * @return true if the inequality holds; false otherwise
	 */
	public static boolean onCurve(int[] coord, double[] coef, double thick) {
		// compute the value of poly
		double poly = 0;
		int coefLength = coef.length;
		for (int i = 0; i < coefLength; i++) {
			poly += coef[i] * Math.pow(coord[0], (coefLength - 1 - i));
		}
		// check if it is in the range
		if ((coord[1] - thick < poly) && (poly < coord[1] + thick)) {
			return true;
		}
		return false;
	}

	/**
	 * Check for coefficients array and thickness coef array must have at least one
	 * element. thickness must be a positive number
	 * 
	 * @param coef  coefficient array of the poly
	 * @param thick thickness of the curve
	 */
	public static void verifyInput(double[] coef, double thick) {
		if (coef.length <= 0) {
			throw new IllegalArgumentException("The coefficients array must have at least one element.");
		}
		if (thick <= 0) {
			throw new IllegalArgumentException("The thickness should be a positive number.");
		}
	}

	public static void drawCurve(double[] coef, double thick, char symbol) {
		verifyInput(coef, thick);
		int y = 21;
		int x = 21;
		// check for the y-intercept see if we need to extend the y axis
		double constant = coef[coef.length - 1];
		if (Math.abs(constant) > 5) {
			y = 11 + (int) (Math.abs(constant)) + 5;
		}
		// find the position of origin
		int origin = 11;
		if (y > 21 && constant > 0) {
			origin = y - 10;
		}
		// draw the graph
		for (int i = 1; i <= y; i++) {
			for (int j = 1; j <= x; j++) {
				// the position of '^'
				if (i == 1 && j == 11) {
					System.out.print("^");
					continue;
				}
				// curve has priority, check it first
				int[] coord = { j - 11, origin - i };
				if (onCurve(coord, coef, thick)) {
					System.out.print(symbol);
				}
				// the position of origin '+'
				else if (i == origin && j == 11) {
					System.out.print("+");
				}
				// the position of the end of positive x-axis '>'
				else if (i == origin && j == 21) {
					System.out.print(">");
				}
				// the x-axis '-'
				else if (i == origin && j != 21 && j != 11) {
					System.out.print("-");
				}
				// the y-axis '|'
				else if (j == 11 && i != 1 && i != origin) {
					System.out.print("|");
				}
				// blank in other positions
				else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
	}
}
