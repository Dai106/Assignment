
package ass1;

import java.util.Scanner;

public class PizzaCalculator {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// ask for input
		System.out.println("Please choose your mode: 1 for Quantity mode, 2 for Price mode: ");
		String input = sc.nextLine();
		if (input.equals("1")) {
			System.out.println("You selected \"Quantity mode\".");
			System.out.println("The diameter of the large pizza (inches) : ");
			int large = sc.nextInt();

			System.out.println("The diameter of the large pizza (inches) : ");
			int small = sc.nextInt();

			double result = (Math.PI * Math.pow(large, 2)) / (Math.PI * Math.pow(small, 2));
			System.out.printf("You should order %d small pizzas.\n", (int) Math.ceil(result));
		} else if (input.equals("2")) {
			System.out.println("You selected \"Price mode\".");

			System.out.println("The diameter of the lare pizza (inches): ");
			int largeSize = sc.nextInt();

			System.out.println("The price of one large pizza is (dollars): ");
			double largePrice = sc.nextDouble();

			System.out.println("The diameter of the small pizza (inches): ");
			int smallSize = sc.nextInt();

			System.out.println("The number of small pizza you want to buy: ");
			int numBuy = sc.nextInt();

			double perPrice = largePrice / (Math.PI * Math.pow(largeSize, 2));
			double fairPrice = perPrice * numBuy * (Math.PI * Math.pow(smallSize, 2));

			System.out.printf("The fair price to pay for %d small pizzas is %.2f dollars.\n", numBuy, fairPrice);

		} else {
			System.out.println("This mode is not supported.");
		}
	}
}
