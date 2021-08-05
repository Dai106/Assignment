package ass5;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Playground {
	public static void main(String[] args) {
		play();
	}

	public static void play() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome!\n");
		System.out.println("Please enter the name of the Tamagotchi you'd like to play with: ");

		String name = sc.nextLine();
		System.out.println();

		String fileName = name.toLowerCase() + ".txt";

		Tamagotchi player;
		try {
			player = FileIO.loadTamagotchi(fileName);
			System.out.println("Your Tamagotchi has been loaded");
		} catch (IOException e) {
			player = new Tamagotchi(name);
			System.out.println("Your Tamagotchi has been created");
		}

		System.out.println();
		drawTamagotchi();
		System.out.println();

		System.out.println(player);

		// loop until sleep
		System.out.println("Enter a command (play, feed, or sleep): ");
		String command = sc.nextLine();
		System.out.println();

		while (!command.equalsIgnoreCase("sleep")) {
			try {
				// if command is play
				if (command.equalsIgnoreCase("play")) {
					System.out.println("Choose one of the following play mode: ");
					System.out.println("1. " + player.getName() + " plays with the best toy");
					System.out.println("2. " + player.getName() + " plays with a random toy");

					int mode = sc.nextInt();
					// clean the buffer
					sc.nextLine();
					System.out.println();
					player.play(mode);
				}
				// feed
				else if (command.equalsIgnoreCase("feed")) {
					player.feed();
				}
				// others
				else {
					System.out.println("The input command is invalid");
				}
			} catch (IllegalStateException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
			}
			System.out.println();
			System.out.println("Please enter the next command: ");
			command = sc.nextLine();
			System.out.println();
		}
		player.sleep();

		String fileToys = name.toLowerCase() + "Toys.txt";
		FileIO.saveTamagotchi(player, fileName, fileToys);
	}

	public static void drawTamagotchi() {
		System.out.println("      oooooo      oooooo");
		System.out.println("     o      o    o      o");
		System.out.println("     o      o    o      o");
		System.out.println("     o      o    o      o");
		System.out.println("     o ooooo oooo ooooo o");
		System.out.println("    o                    o");
		System.out.println("   o     ^          ^     o");
		System.out.println("  o     / \\        / \\     o");
		System.out.println("  o                        o");
		System.out.println("  o                        o");
		System.out.println("   o       ________       o");
		System.out.println("    oo                  oo");
		System.out.println("      ooo            ooo");
		System.out.println("         oo oooooo oo");
	}
}
