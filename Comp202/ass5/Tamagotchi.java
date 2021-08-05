package ass5;

import java.util.ArrayList;
import java.util.Random;

public class Tamagotchi {
	// static attributes
	private static final double MAX_ENERGY = 10;
	private static Random r = new Random();

	// non static attributes
	private String name;
	private int level;
	private double energy;
	private int xp;
	private int meals;
	private ArrayList<Toy> toys;

	// constructor
	public Tamagotchi(String name, int level, double energy, int xp, int meals, ArrayList<Toy> toys) {
		this.name = name;
		this.level = level;
		this.energy = energy;
		this.xp = xp;
		this.meals = meals;
		this.toys = new ArrayList<Toy>();
		this.toys.addAll(toys);
	}

	public Tamagotchi(String name) {
		this.name = name;
		this.level = 1;
		this.energy = MAX_ENERGY;
		this.xp = 0;
		this.meals = 0;
		// list of toys contian a Toy generated at random
		this.toys = new ArrayList<Toy>();
		this.toys.add(new Toy());
	}

	// get methods
	public String getName() {
		return this.name;
	}

	public int getLevel() {
		return this.level;
	}

	public double getEnergy() {
		return this.energy;
	}

	public int getXp() {
		return this.xp;
	}

	public int getNumOfMeals() {
		return this.meals;
	}

	public ArrayList<Toy> getToys() {
		ArrayList<Toy> copy = new ArrayList<Toy>();
		copy.addAll(this.toys);
		return copy;
	}

	/**
	 * A private method to check whether the tamagotchi has enough experience to
	 * level up. If yes, level up, reset meals consumed to 0 and create a brand new
	 * toy. Then print a message. Otherwise, no nothing.
	 */
	private void levelUp() {
		if (this.xp >= 50 * (this.level) / 2) {
			this.level++;
			this.meals = 0;
			this.toys.add(new Toy());
			System.out.println("*** YAY, time to level up!! ***");
			System.out.println(this.name + " is now level " + this.level);
			System.out.println("Your new toy is " + this.toys.get(this.toys.size() - 1).toString());
		}
	}

	/**
	 * @pre assume the input mode is either 1 or 2
	 * @param mode
	 */
	public void play(int mode) {
		// case for not able to play
		if (this.toys.size() == 0 || this.energy < 2) {
			throw new IllegalArgumentException("The tamagotchi now is not able to play.");
		}

		// select a toy
		Toy targetToy;

		if (mode == 1) {
			targetToy = Toy.findBestToy(this.toys);
		} else {
			targetToy = this.toys.get(r.nextInt(this.toys.size()));
		}

		this.xp += targetToy.getXp();
		this.energy -= r.nextDouble() * 0.5 + 20.0;
		System.out.printf("%s played with %s and earned %d xp.\n%s has now %d xp, and %.2f energy.\n", this.name,
				targetToy.toString(), this.xp, this.name, this.xp, this.energy);
		// check for level up
		levelUp();
	}

	/**
	 * feed may increase the energy, experience, meal consumed and level.
	 */
	public void feed() {
		// case for not able to eat
		if (this.energy < 1 || this.meals >= 2 * this.level) {
			throw new IllegalStateException("The tamagotchi cannot eat now.");
		}
		this.energy += r.nextDouble() * 0.5;
		this.xp += r.nextInt(3) + 1;
		this.meals++;
		System.out.println("Nom nom nom");
		System.out.printf("%s has now %d xp, and %.2f energy.\n", this.name, this.xp, this.energy);
		// check for level up
		levelUp();
	}

	/**
	 * reset the energy to max energy
	 */
	public void sleep() {
		System.out.println("Now sleep ... z~ z~~ z~~ z~~~");
		this.energy = MAX_ENERGY;
	}

	@Override
	public String toString() {
		return String.format("Name:\t %s\nLevel:\t %d\nEnergy:\t %.2f\nXp:\t %d\nMeals:\t %d\n" + "Toys:\t %s",
				this.name, this.level, this.energy, this.xp, this.meals, this.toys);
	}
}
