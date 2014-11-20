package sudoku;

import java.util.ArrayList;
import java.util.Random;

public class Tabla {
	ArrayList<Sor> sorok;
	ArrayList<Oszlop> oszlopok;
	ArrayList<Negyzet> negyzetek;

	ArrayList<Mezo> mezok;

	public Tabla() {
		sorok = new ArrayList<Sor>(9);
		oszlopok = new ArrayList<Oszlop>(9);
		mezok = new ArrayList<Mezo>(81);
		for (int i = 0; i < 9; i++) {
			oszlopok.add(new Oszlop(i));
			sorok.add(new Sor(i));
		}
		
		  for(int i=0;i<9;i++){
		  	for(int j=0;j<9;j++){ 
			  //sorok.get(i).mezok.add(new Mezo(0,0,0));
			  oszlopok.get(i).mezok.add(new Mezo(0,0,0)); } 
		  }
		 
	}

	public void feltolt() {
		int[] kilenc = new int[9];
		int x = 0;
		int y = 0;

		for (int oszlopszam = 0; oszlopszam < 9; oszlopszam++) {

			for (int i = 0; i < 9; i++) {
				kilenc[i] = i + 1;
			}

			for (int sorszam = 0; sorszam < 9; sorszam++) {
				Random rand = new Random();
				int value = rand.nextInt(9);
				int szum = 0;

				while (kilenc[value] == -1) {
					if(szum > 1000) System.out.println("GEBASZ VAN!!!");
					szum++;
					value = rand.nextInt(9);
				}
				
				if (sorszam == oszlopszam) {
					
					/*for(int k=0;k<i;k++){ //EZ VOLNA AZ AMI VIZSGÁLJA, HOGY PL A 7;7 MEZŐ KITÖLTÉSE ELŐTT AZ ADOTT OSZLOPBA
						if(oszlopok.get(oszlopszam).mezok.get(k).ertek == sorszam+1){ //BEKERÜLT-E MÁR A HETES VALAHOVA
							oszlopok.get(oszlopszam).mezok.get(k).ertek = kilenc[value];
						}
					}*/
					oszlopok.get(oszlopszam).mezok.get(sorszam).ertek = sorszam+1;
					//oszlopok.get(oszlopszam).mezok.get(sorszam).x = y + sorszam;      //NEM FONTOS, FELTÖLTI AZ X;Y ÉRTÉKEKET
					//oszlopok.get(oszlopszam).mezok.get(sorszam).y = x + oszlopszam;
					kilenc[sorszam] = -1;
					//kilenc[value] = -1;
					
					} else {
					/*for(int k = 0; k < oszlopszam; k++){					//KIVESZEM A TÖMBBŐL AZOKAT AZ ÉRTÉKEKET, AMIK
						int z = oszlopok.get(k).mezok.get(sorszam).ertek;	//KORÁBBI OSZLOPOKBAN AZ ADOTT SORBAN MÁR SZEREPELTEK
						kilenc[z-1] = -1;
					}*/
					
					oszlopok.get(oszlopszam).mezok.get(sorszam).ertek = kilenc[value];
					// oszlopok.get(oszlopszam).mezok.add(new Mezo(kilenc[value], y + sorszam, x + oszlopszam));
					// sorok.get(oszlopszam).mezok.add(new Mezo(kilenc[value],x+sorszam,y+oszlopszam));
					//kilenc[value] = -1;

					/*for(int k = 0; k < oszlopszam; k++){						//EZ VISSZAÍRJA A KORÁBBI SZÁMOKAT A KILENC TÖMBBE
						int z = oszlopok.get(k).mezok.get(sorszam).ertek;		//CSAK NEM MŰKÖDIK
						kilenc[z-1] = oszlopok.get(k).mezok.get(sorszam).ertek;
					}*/
					kilenc[value] = -1;

					}
				
					/*for (int ujra = 0; ujra < 9; ujra++) {	//ALTERNATÍVA A -1BE ÁLLÍTÁSRA, HA MINDENHOL KIVESSZÜK, AKKOR 
						kilenc[ujra] = ujra + 1;				//MŰKÖDNIE KÉNE, HISZEN EZ ÚJRA FELTÖLTI MINDEN ITERÁCIÓ
					}											//VÉGÉN A TÖMBÖT, ÉS A UTÁNA KIVESZI BELŐLE AZOKAT, AMIK
					for(int l = 0; l < sorszam; l++){			//AMIK AZ ADOTT OSZLOPBAN MÁR SZEREPELNEK KORÁBBAN
						int u = (oszlopok.get(oszlopok).mezok.get(l).ertek);
						kilenc[u-1] = -1;
					}*/
				}
			}
		}
	

	/*public void MezoFeltolt() {
		int[] kilenc = new int[9];

		for (int i = 0; i < 81; i++) {
			mezok.add(new Mezo(0, 0, 0));
		}

		for (int j = 0; j < 9; j++) {

			Random rand = new Random();
			int value = rand.nextInt(9);

			while (kilenc[value] == -1) {
				value = rand.nextInt(9);
			}
			
			for (int i = 0; i < 9; i++) {

				Mezo mezo = new Mezo(value + 1, i, 0);
				mezok.set(i, mezo);
				kilenc[value] = -1;
			}
				
			for (int i = 0; i < 81; i++) {
				System.out.print(mezok.get(i).ertek + " ");
			}
		}
	}*/

	public void print() {
		for (int i = 0; i < 9; i++) {
			if (i % 3 == 0) {
				System.out
						.println("-------------------------------------------------------------");
			}
			for (int j = 0; j < 9; j++) {
				if (j % 3 == 0) {
					System.out.print("|");
				}
				System.out.print(oszlopok.get(j).mezok.get(i).ertek + " ");
				// System.out.print(sorok.get(i).mezok.get(j).ertek + " ");

				// System.out.print("(" + oszlopok.get(j).mezok.get(i).x + ";" +
				// oszlopok.get(j).mezok.get(i).y + ")" + " ");
				// System.out.print("(" + sorok.get(i).mezok.get(j).x + ";" +
				// sorok.get(i).mezok.get(j).y + ")" + " ");

			}
			System.out.print("|\n");
		}
	}
}
