package ass1;

import java.util.Scanner;

public class ISBN {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int input = 0;
		
		while(true) {
			System.out.print("Please input a four digit number: ");
			if(sc.hasNextInt()) {
				input = sc.nextInt();
				if(input > 999 && input < 10000) {
					break;
				}
			}
			// input "exit" to stop
			String s = sc.nextLine();
			if(s.equals("exit")) {
				sc.close();
				return;
			}
		}
		
		//compute the first 4 digits value
		int sum = 0;
		for(int i = 1; i <= 4; i++) {
			sum += (i+1)*(input % 10);
			input = input / 10;
		}
		// the remainder to be a multiple of 11
		int remainder = sum % 11;
		// the last digit
		int lastDigit = 11 - remainder;
		if(lastDigit < 10) {
			System.out.println("The last digit must be " + lastDigit + ".");
		}else if(lastDigit == 10){
			System.out.println("The last digit must be X.");
		}else if(lastDigit == 11) {
			System.out.println("The last digit must be 0.");
		}
		sc.close();
	}
}
