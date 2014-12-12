package sudoku;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

/**
 * Controller osztaly, a jatektabla kezelesere
 */
public class Controller {
	private Tabla t;
	private int nehezseg = 1;

	/**
	 * Konstruktor, letrehozza a tablat
	 */
	public Controller() {
		t = new Tabla();
	}

	/**
	 * A szolistabol random valaszt egy sort es ennek a karaktereibol allitja
	 * elo a tabla karakterkeszletet
	 */
	public void initCharset() {
		FileInputStream fr;
		try {
			fr = new FileInputStream("C:\\Users\\Legoo\\Desktop\\félév#5\\MI\\sudoku\\MI_hazi\\9char.txt");
//			fr = new FileInputStream("9char.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fr,
					"UTF-8"));
			Random rand = new Random();
			int sorszam = rand.nextInt(1758);
			for (int i = 0; i < sorszam; i++) {
				br.readLine();
			}
			String szo = br.readLine();
			System.out.println(szo);
			Tabla.karakterkeszlet = szo.toUpperCase().toCharArray();
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Tablat letrehozo fuggveny. Feltolti a tabla elso sorat, majd feltolti a
	 * tobbit is, amig nem kap szabalyos tablat, addig ismetli ezeket a
	 * lepeseket. A kesz tabla elemeit elmenti a Tabla nyolcvanegy tombjebe.
	 */
	public void makeTabla() {
		t.ToltPuzzle(t.puzzle);
		while (!(t.makePuzzle(t.puzzle, 0))) {
			t.szam = 0;
			t.ToltPuzzle(t.puzzle);
		}
		t.save81();
	}

	/**
	 * Nehezsegnek megfelelo szamu mezot kitakar. Valaszt egy random sor-oszlop
	 * parost, ha az erteke mar 0, akkor addig uj paros valaszt, kinullazza az
	 * erteket es a kitakart mezok szamat eggyel noveli.
	 */
	public void kitakarTabla() {
		t.kitakartNum = 0;
		for (int i = 0; i < (nehezseg * 10 + 1); i++) { // Az, hogy hány számot
														// távolítsunk el (ha
														// mindig mást generál a
														// két random)
			Random rand = new Random();
			int value = rand.nextInt(9);
			int value_2 = rand.nextInt(9);
			while (t.puzzle[value * 9 + value_2] == 0) {
				value = rand.nextInt(9);
				value_2 = rand.nextInt(9);
			}
			t.puzzle[value * 9 + value_2] = 0;
			t.kitakartNum++;
		}
		t.kitakart = t.puzzle.clone();
		t.charpuzzle();
		System.out.println("\n" + t.toString() + "\n");
	}

	/**
	 * Megoldo fuggveny. Betolti a generalt tablat es kitakartatja az elemeket.
	 * Megoldja ezt, ha nem jar sikerrel, akkor uj kitakarast alkalmaz.
	 * 
	 * @return
	 */
	public boolean solveTabla() {
		boolean megoldva = false;
		flag:
		while (!megoldva) {
			t.load81();
			kitakarTabla();
			System.out.println("A megoldas:");
			
			for (int i = 0; i < 20; i++) {
				t.puzzle = t.kitakart.clone();
				
				for(int j = 0; j < 100; j++){
					if (t.solvePuzzle(t.puzzle, 0)) {
						j = 100;
					} else {
						t.szam_2 = 0;
						t.puzzle = t.kitakart.clone();
					}
				}

				if (!(Arrays.equals(t.nyolcvanegy, t.puzzle))) {
					for(int j = 0; j < 10; j++){
						System.out.println("GEBAAAAAAAAAAAAAAAAAAAAASZ");
					}
					continue flag;
				}
				
			}
			megoldva = true;
		}
		return megoldva;
	}

	/**
	 * Beallitja a nehezseget
	 * @param nehezseg a beallitando nehezseg
	 */
	public void setNehezseg(int nehezseg) {
		this.nehezseg = nehezseg;
	}
	/**
	 * Visszaadja a nehezseget
	 * @return a nehezseg
	 */
	public int getNehezseg() {
		return nehezseg;
	}
	/**
	 * 
	 * @return a tabla
	 */
	public Tabla getT() {
		return t;
	}
	/**
	 * Visszaallitja a tablat az alapallapotaba
	 */
	public void resetTabla() {
		t.reset();
	}
	
	/**
	 * A parameterben kapott int tombot visszaadja emesztheto formatumban 
	 * @param t stringge alakitando tomb
	 * @return a tomb emesztheto formatumban
	 */
	public String printINT(int[] t) {
		String str = new String();
		for (int i = 0; i < t.length; i++) {
			str += t[i] + " ";
			if (i % 8 == 0) {
				str += "\n";
			}
		}
		return str;
	}
}
