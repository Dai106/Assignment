package ass5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileIO {
	public static ArrayList<Toy> loadToys(String fileName) throws IOException {
		ArrayList<Toy> toys = new ArrayList<>();
		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			while (line != null) {
				toys.add(Toy.createToy(line));
				line = br.readLine();
			}
			br.close();
			fr.close();
		} catch (IllegalArgumentException e) {
			// return an empty array list
			toys.clear();
			System.out.println("Format of the toy's file is incorrect");
		}
		return toys;
	}

	public static boolean saveToys(ArrayList<Toy> toys, String fileName) {
		try {
			FileWriter fw = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(fw);
			for (Toy t : toys) {
				// write into the file
				bw.write(t.getName() + "\t");
				bw.write(t.getColor() + "\t");
				bw.write(t.getType() + "\t");
				bw.write("" + t.getXp());
				bw.newLine();
			}
			bw.close();
			fw.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public static Tamagotchi loadTamagotchi(String fileName) throws IOException {
		Tamagotchi t = null;
		FileReader fr = new FileReader(fileName);
		BufferedReader br = new BufferedReader(fr);
		String name = br.readLine();
		int level = Integer.parseInt(br.readLine());
		double energy = Double.parseDouble(br.readLine());
		int xp = Integer.parseInt(br.readLine());
		int meal = Integer.parseInt(br.readLine());
		String toyFileName = br.readLine();
		ArrayList<Toy> toys = loadToys(toyFileName);
		t = new Tamagotchi(name, level, energy, xp, meal, toys);
		br.close();
		fr.close();
		return t;
	}

	public static boolean saveTamagotchi(Tamagotchi t, String fileName, String fileToys) {
		try {
			FileWriter fw = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(t.getName() + "\n");
			bw.write("" + t.getLevel() + "\n");
			bw.write("" + t.getEnergy() + "\n");
			bw.write("" + t.getXp() + "\n");
			bw.write("" + t.getNumOfMeals() + "\n");
			bw.write(fileToys);
			boolean b = saveToys(t.getToys(), fileToys);
			bw.close();
			fw.close();
			return b;
		} catch (IOException e) {
			return false;
		}
	}
}
