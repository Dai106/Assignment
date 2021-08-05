package ass5;

import java.util.ArrayList;
import java.util.Random;

public class Toy {
	// static attributes
	private static String[] names = { "Bob", "Penny", "Fisher", "Snoopy", "Garfield", "Mary", "Chuchu", "Trooper",
			"Lovebug", "Bella" };
	private static String[] colors = { "red", "blue", "green", "yellow", "orange", "purple" };
	private static String[] types = { "car", "doll", "stuffed cat", "train", "ball", "kite", "teddy bear", "trike" };

	private static Random r = new Random();

	// non static attributes
	private String name;
	private String color;
	private String type;
	private int experience;

	// constructor
	// initialize based on the user input
	public Toy(String name, String color, String type, int experience) {
		this.name = name;
		this.color = color;
		this.type = type;
		this.experience = experience;
	}

	// initialize randomly
	public Toy() {
		this.name = getRandomName();
		this.color = getRandomColor();
		this.type = getRandomType();
		this.experience = r.nextInt(15) + 10;
	}

	// private static methods provided
	private static String getRandomName() {
		int i = r.nextInt(names.length);
		return names[i];
	}

	private static String getRandomColor() {
		int i = r.nextInt(colors.length);
		return colors[i];
	}

	private static String getRandomType() {
		int i = r.nextInt(types.length);
		return types[i];
	}

	// get methods
	public String getName() {
		return this.name;
	}

	public String getType() {
		return this.type;
	}

	public String getColor() {
		return this.color;
	}

	public int getXp() {
		return this.experience;
	}

	@Override
	public String toString() {
		return this.name + " the " + this.color + " " + this.type;
	}

	/**
	 * Use the info given by the user to create a toy
	 * 
	 * @param info
	 * @return
	 */
	public static Toy createToy(String info) {
		String[] spInfo = info.split("\t");
		// check for the number of info
		if (spInfo.length != 4) {
			throw new IllegalArgumentException(
					"The provided info is not valid. Please give a String contains the name, the color, the type and the experience to be awarded, separated by a tab character.");
		}
		int xp = 0;
		try {
			xp = Integer.parseInt(info);
		} catch (NumberFormatException ex) {
			System.out.println("The experience shouble be an integer.");
		}
		return new Toy(spInfo[0], spInfo[1], spInfo[2], xp);
	}

	/**
	 * Find the toy with the highest experience. If there are more than one toy has
	 * the same highest experience, return the first one.
	 * 
	 * @param toys
	 * @return null if the toy array is empty; the toy with the highest experience
	 */
	public static Toy findBestToy(ArrayList<Toy> toys) {
		// check the array length
		if (toys.size() == 0) {
			return null;
		}
		int max = 0;
		Toy bestToy = toys.get(0);
		for (Toy t : toys) {
			if (t.getXp() > max) {
				max = t.getXp();
				bestToy = t;
			}
		}
		return bestToy;
	}

}
