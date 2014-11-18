package sudoku;

import java.util.ArrayList;
import java.util.Random;

public class Tabla {
	ArrayList<Sor> sorok;
	ArrayList<Oszlop> oszlopok;
	ArrayList<Negyzet> negyzetek;

	public Tabla() {
		sorok = new ArrayList<Sor>(9);
		oszlopok = new ArrayList<Oszlop>(9);
		for (int i = 0; i < 9; i++) {
			oszlopok.add(new Oszlop(i));
			sorok.add(new Sor(i));
		}
		/*
		 * for(int i=0;i<9;i++){
		 * 
		 * for(int j=0;j<9;j++){ sorok.get(i).mezok.add(new Mezo((i+1)*(j+1)));
		 * oszlopok.get(j).mezok.add(new Mezo((i+1)*(j+1))); } }
		 */
	}

	public void feltolt() {
		int[] kilenc = new int[9];
		int x = 0;
		int y = 0;
		
		for (int j = 0; j < 9; j++) {
			
			for (int i = 0; i < 9; i++) {
				kilenc[i] = i + 1;
			}
			
			for (int i = 0; i < 9; i++) {
				Random rand = new Random();
				int value = rand.nextInt(9);

				while (kilenc[value] == -1) {
					value = rand.nextInt(9);
				}
				oszlopok.get(j).mezok.add(new Mezo(kilenc[value],y+i,x+j));
				//sorok.get(j).mezok.add(new Mezo(kilenc[value],x+i,y+j));
				kilenc[value] = -1;
			}
		}
	}

	public void print() {
		for (int i = 0; i < 9; i++) {
			if (i % 3 == 0) {
				System.out.println("-------------------------------------------------------------");
			}
			for (int j = 0; j < 9; j++) {
				if (j % 3 == 0) {
					System.out.print("|");
				}
				System.out.print(oszlopok.get(j).mezok.get(i).ertek + " ");
				//System.out.print(sorok.get(i).mezok.get(j).ertek + " ");

				//System.out.print("(" + oszlopok.get(j).mezok.get(i).x + ";" + oszlopok.get(j).mezok.get(i).y + ")" + " ");
				//System.out.print("(" + sorok.get(i).mezok.get(j).x + ";" + sorok.get(i).mezok.get(j).y + ")" + " ");

			}
			System.out.print("|\n");
		}
	}
}
