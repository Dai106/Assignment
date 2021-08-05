package ass1;

import java.util.Scanner;

public class InputAnalyzer {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double[] input = new double[3];
		int index = 0;
		System.out.println("Input 3 numbers: ");
		while(index < 3) {
			if(sc.hasNextDouble()) {
				input[index] = sc.nextDouble();
				index ++;
			}
		}
		sc.close();
		//check for properties
		boolean nonNegative = true;
		boolean even = false;
		boolean decreasing = true;
		for(int i = 0; i < 3; i++) {
			if(input[i] < 0.0) {
				nonNegative = false;
			}
			if(input[i] % 2 == 0) {
				even = true;
			}
			if(i > 0 && input[i] >= input[i-1]) {
				decreasing = false;
			}
		}
		
		// print out the result
		System.out.printf("The numbers %.1f, %.1f, and %.1f are all non-negative: %b\n", 
				input[0], input[1], input[2], nonNegative);
		System.out.printf("At least one between %.1f, %.1f, and %.1f is even: %b\n", 
				input[0], input[1], input[2], even);
		System.out.printf("The numbers %.1f, %.1f, and %.1f are in a strictly decreasing order: %b\n", 
				input[0], input[1], input[2], decreasing);
		System.out.printf("The numbers %.1f, %.1f, and %.1f are either all non-negative or in a strictly decreasing order: %b\n", 
				input[0], input[1], input[2], (nonNegative || decreasing));
		System.out.printf("The numbers %.1f, %.1f, and %.1f are all non-negative numbers and non of them is even: %b\n", 
				input[0], input[1], input[2], (nonNegative && !even));
	}
}
