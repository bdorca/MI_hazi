package sudoku;

import java.util.Arrays;
import java.util.Random;

public class Tabla {
	public int[] puzzle;
	public int[] kitakart;
	public int[] nyolcvanegy;
	public int szamlalo = 0;
	public int szam = 0;
	public int szam_2 = 0;
	public char[] chpuzzle;
	public static char[] karakterkeszlet;
	public char[] konvert;

	public int kitakartNum=0;
	
	public Tabla() {
		puzzle = new int[81];
		nyolcvanegy = new int[81];
		konvert=new char[9];
		Arrays.fill(konvert, '0');
	}

	public void save81() {												///Elmenti a legeneralt puzzle tomb allasat egy masik tombbe
		for (int i = 0; i < 81; i++) {
			nyolcvanegy[i] = puzzle[i];
		}
	}

	public void load81() {  											///Lemasolja az egyik tombot, hogy ujra az eredeti allas keruljon a puzzle tombbe
		for (int i = 0; i < 81; i++) {
			puzzle[i] = nyolcvanegy[i];
		}
	}

	public boolean makePuzzle(int[] puzzle, int i) {					///Letrehoz egy szabalyos tablat szamokkal	
		
		szam++;
		
		if (szam > 1000) { 												///ha tobb mint 1000-szer hivtuk a rekurziot, akkor kilepunk, es ujrahivjuk, hogy gyorsitsuk a generalast
			return false; 
		}
		
		for (int x = 1; x < 10; x++) {									///belepunk egy ciklusba mely majd a feltoltesben segedkezik
			
		if (puzzle[i] != 0) {											///ha a kapott indexu elem nem nulla, akkor tovabblepunk a kovetkezo elemre 
				if (makePuzzle(puzzle, i + 1))
					return true;

			} else {
				puzzle[i] = x;											///ha nulla az i. helyen levo elem, akkor ertekul adjuk neki az aktualis elemet a ciklusnak
				if (checkConstraints(puzzle)) {							///megnezzuk, hogy az adott helyre illesztett elem szabalyos-e ott							
					if (i > 79)											///ha igen, es az index 79-nel nagyobb, akkor keszen vagyunk
					{
						charpuzzle();									///szabadon legeneralhatjuk a betuket tartalmazo tombot
					return true;										///es visszaterunk IGAZ ertekkel
					} else if (makePuzzle(puzzle, i + 1))
						return true;									///ha meg nem ertuk el a 80. indexet, akkor tovabblepunk a kovetkezo elemre

				}
				puzzle[i] = 0;											///ha pedig az ellenorzes soran kiderult, hogy a beirt ertek nem jo, kitoroljuk es probaljuk a kovetkezot
			}
		}
		return false;
	}

	public boolean solvePuzzle(int[] puzzle, int i) {					///Megprobal megoldani egy szabalyos tablat kitakart elemekkel
		szam_2++;
		
		if(szam_2 > 100){												///Ha szaz iteracional tartunk mar, akkor ujrafuttatjuk az egeszet az eredeti ertekekkel
			return false;
		}
		boolean check = true;

		for (int j = 0; j < 81; j++) { 									///megnezzuk, hogy vegeztunk-e
			if (puzzle[j] == 0)
				check = false;
		}

		if (check) {													///ha igen, akkor kiirjuk es visszaterunk es atalakitjuk a tombot
			charpuzzle();
			return true;
		}

		if (puzzle[i] != 0) {											///ha az adott elem nem nulla
			if (i < 80) {
				if (solvePuzzle(puzzle, i + 1))
					return true;										/// megvizsgaljuk, hogy van-e meg hely a tombben, ha igen akkor meghivjuk a kovetkezo elemre, mert nincs dolgunk vele
			} else {
				if (solvePuzzle(puzzle, 0))
					return true;										/// ha a vegere ertunk, akkor meghivjuk elolrol a megoldo fuggvenyt
			}
		} else { 														
			checkSquare(puzzle,i);										///behelyettesitjuk az aktualis helyre az egyik lehetseges elemet
			if (i < 80) {
				if (solvePuzzle(puzzle, i + 1))
					return true;										/// megvizsgaljuk, hogy van-e meg hely a tombben, ha igen akkor meghivjuk a kovetkezo elemre, mert nincs dolgunk vele
			} else {
				if (solvePuzzle(puzzle, 0))			
					return true;										/// ha a vegere ertunk, akkor meghivjuk elolrol a megoldo fuggvenyt
			}
		}
		return false;
	}

	public boolean checkSquare(int[] puzzle, int i) {					///Feltolti az aktualis mezot egy lehetseges ertekkel
		int sor = i / 9;												///A "sor" nevu valtozo az aktualis mezo 9-cel valo egesz osztasanak erteke
		int oszlop = i % 9;												///Az "oszlop" nevu valtozo az aktualis mezo 9-es maradeka
		int[] kilenc = new int[9];										///Egy tomb feltoltve az 1,2...9 ertekekkel
		for (int j = 0; j < 9; j++) {									///A tomb feltoltese
			kilenc[j] = j + 1;
		}

		for (int j = 0; j < 9; j++) {									///Kivesszuk az 1,2...9 ertekekkel feltoltott tombbol azokat az elemeket, amik szerepelnek az aktualis mezo soraban
			if (puzzle[sor * 9 + j] != 0) {
				kilenc[puzzle[sor * 9 + j] - 1] = -1;
			}
		}
		for (int j = 0; j < 9; j++) {									///Kivesszuk az 1,2...9 ertekekkel feltoltott tombbol azokat az elemeket, amik szerepelnek az aktualis mezo oszlopaban
			if (puzzle[j * 9 + oszlop] != 0) {
				kilenc[puzzle[j * 9 + oszlop] - 1] = -1;
			}
		}

		switch (i) {													///Ez a switch-case szerkezet pedig az aktualis mezohoz tartozo 3x3-as teruletet vizsgalja a tablaban. Szinten kiveszi a "kilenc" tombbol azokat az elemeket, amik szerepelnek a 3x3-as resztablaban. Ezt a reszt lehetne szebben is csinalni, azonban azt hiszem, hogy igy sokkal konnyebben atlathato
		case 0: case 1: case 2:
		case 9: case 10: case 11:
		case 18: case 19: case 20:

			for (int k = 0; k < 3; k++) {
				for (int z = 0; z < 3; z++) {
					if (puzzle[k * 9 + z] != 0)
						kilenc[puzzle[k * 9 + z] - 1] = -1;
				}
			}
			break;

		case 3: case 4: case 5:
		case 12: case 13: case 14:
		case 21: case 22: case 23:

			for (int k = 0; k < 3; k++) {
				for (int z = 3; z < 6; z++) {
					if (puzzle[k * 9 + z] != 0)
						kilenc[puzzle[k * 9 + z] - 1] = -1;
				}
			}
			break;

		case 6: case 7: case 8:
		case 15: case 16: case 17:
		case 24: case 25: case 26:

			for (int k = 0; k < 3; k++) {
				for (int z = 6; z < 9; z++) {
					if (puzzle[k * 9 + z] != 0)
						kilenc[puzzle[k * 9 + z] - 1] = -1;
				}
			}
			break;

		case 27: case 28: case 29:
		case 36: case 37: case 38:
		case 45: case 46: case 47:

			for (int k = 3; k < 6; k++) {
				for (int z = 0; z < 3; z++) {
					if (puzzle[k * 9 + z] != 0)
						kilenc[puzzle[k * 9 + z] - 1] = -1;
				}
			}
			break;
		case 30: case 31: case 32:
		case 39: case 40: case 41:
		case 48: case 49: case 50:

			for (int k = 3; k < 6; k++) {
				for (int z = 3; z < 6; z++) {
					if (puzzle[k * 9 + z] != 0)
						kilenc[puzzle[k * 9 + z] - 1] = -1;
				}
			}
			break;
		case 33: case 34: case 35:
		case 42: case 43: case 44:
		case 51: case 52: case 53:

			for (int k = 3; k < 6; k++) {
				for (int z = 6; z < 9; z++) {
					if (puzzle[k * 9 + z] != 0)
						kilenc[puzzle[k * 9 + z] - 1] = -1;
				}
			}
			break;
		case 54: case 55: case 56:
		case 63: case 64: case 65:
		case 72: case 73: case 74:

			for (int k = 6; k < 9; k++) {
				for (int z = 0; z < 3; z++) {
					if (puzzle[k * 9 + z] != 0)
						kilenc[puzzle[k * 9 + z] - 1] = -1;
				}
			}
			break;
		case 57: case 58: case 59:
		case 66: case 67: case 68:
		case 75: case 76: case 77:

			for (int k = 6; k < 9; k++) {
				for (int z = 3; z < 6; z++) {
					if (puzzle[k * 9 + z] != 0)
						kilenc[puzzle[k * 9 + z] - 1] = -1;
				}
			}
			break;
		case 60: case 61: case 62:
		case 69: case 70: case 71:
		case 78: case 79: case 80:

			for (int k = 6; k < 9; k++) {
				for (int z = 6; z < 9; z++) {
					if (puzzle[k * 9 + z] != 0)
						kilenc[puzzle[k * 9 + z] - 1] = -1;
				}
			}
			break;
		}

		int szam = 0;													///Ez a resz pedig megvizsgalja, hogy maradt-e olyan eleme a kilences tombnek amit meg be lehet irni
		for (int j = 0; j < 9; j++) {
			if (kilenc[j] != -1)
				szam++;
		}
		if (szam == 0)													///Ha nem maradt a vizsgalt tombben ertek, akkor HAMIS ertekkel terunk vissza
			return false;
		
		Random rand = new Random();										///Valasztunk egy random erteket 1..9 intervallumon
		int value = rand.nextInt(9);

		while (kilenc[value] == -1) {									///Ha ott -1 van, akkor ujat valasztunk
		value = rand.nextInt(9);
		}
		puzzle[i] = kilenc[value];										///A vizsgalt helyre beirjuk a leheteseges ertekek kozul veletlenszeruen valasztott erteket

		return true;													///Es IGAZ ertekkel visszaterunk
	}

	public boolean checkConstraints(int[] puzzle) {						///Leellenorzi, hogy a tabla a szabalyoknak megfelelo-e
		int test;

		for (int sor = 0; sor < 9; sor++) {								///A mi jatekunk sajatossaga, hogy a raadasul a foatloban is egyedi elemeknek kell szerepelniuk, ha ez nem teljesul, akkor mar lephetunk is vissza.
			test = puzzle[sor * 10];
			for (int oszlop = 0; oszlop < 9; oszlop++) {
				if (test != 0 && oszlop != sor && test == puzzle[oszlop * 10])
					return false;
			}
		}

		for (int column = 0; column < 9; column++) {					///A kovetkezo ket ciklus leellenorzi, hogy az oszlopokban es a sorokban egyedi ertekek kerultek-e
			for (int row = 0; row < 9; row++) {
				test = puzzle[row + column * 9];
				for (int j = 0; j < 9; j++) {
					if (test != 0 && row != j && test == puzzle[j + column * 9])
						return false;
				}
			}
		}

		for (int column = 0; column < 9; column++) {
			for (int row = 0; row < 9; row++) {
				test = puzzle[column + row * 9];
				for (int j = 0; j < 9; j++) {
					if (test != 0 && row != j && test == puzzle[column + j * 9])
						return false;
				}
			}
		}
	
		for (int i = 0; i < 9; i++){									///Az alabbi igen bonyolultnak tuno switch-case szerkezet csupan megvizsgalja a tabla egyes 3x3-as komponenseire, hogy azok is szabalyosak-e. Lehetne rovidebben is, azonban igy szepen attekintheto
			
			for (int j = 0; j < 9; j++) {
				int ertek = i * 9 + j;
				test = puzzle[i * 9 + j];

				switch (ertek) {
				case 0: case 1: case 2:
				case 9: case 10: case 11:
				case 18: case 19: case 20:

					for (int k = 0; k < 3; k++) {
						for (int l = 0; l < 3; l++) {
							if (test != 0 && ((k * 9 + l) != (i * 9 + j))
									&& (test == puzzle[k * 9 + l]))
								return false;
						}
					}
					break;

                case 3: case 4: case 5:
                case 12: case 13: case 14:
                case 21: case 22: case 23:
 
					for (int k = 0; k < 3; k++) {
						for (int z = 3; z < 6; z++) {
							if (test != 0 && ((k * 9 + z) != (i * 9 + j))
									&& (test == puzzle[k * 9 + z])) {
								// System.out.println("FALSE");
								return false;
							}
						}
					}
					break;

                    
             case 6: case 7: case 8:
             case 15: case 16: case 17:
             case 24: case 25: case 26:

					for (int k = 0; k < 3; k++) {
						for (int z = 6; z < 9; z++) {
							if (test != 0 && ((k * 9 + z) != (i * 9 + j))
									&& (test == puzzle[k * 9 + z])) {
								// System.out.println("FALSE_2");
								return false;
							}
						}
					}
					break;

             case 27: case 28: case 29:
             case 36: case 37: case 38:
             case 45: case 46: case 47:

					for (int k = 3; k < 6; k++) {
						for (int z = 0; z < 3; z++) {
							if (test != 0 && ((k * 9 + z) != (i * 9 + j))
									&& (test == puzzle[k * 9 + z])) {
								// System.out.println("FALSE_3");
								return false;
							}
						}
					}
					break;

             case 30: case 31: case 32:
             case 39: case 40: case 41:
             case 48: case 49: case 50:

					for (int k = 3; k < 6; k++) {
						for (int z = 3; z < 6; z++) {
							if (test != 0 && ((k * 9 + z) != (i * 9 + j))
									&& (test == puzzle[k * 9 + z])) {
								// System.out.println("FALSE_4");
								return false;
							}
						}
					}
					break;

             case 33: case 34: case 35:
             case 42: case 43: case 44:
             case 51: case 52: case 53:

					for (int k = 3; k < 6; k++) {
						for (int z = 6; z < 9; z++) {
							if (test != 0 && ((k * 9 + z) != (i * 9 + j))
									&& (test == puzzle[k * 9 + z])) {
								// System.out.println("FALSE_5");
								return false;
							}
						}
					}
					break;
				case 54: case 55: case 56:
				case 63: case 64: case 65:
				case 72: case 73: case 74:

					for (int k = 6; k < 9; k++) {
						for (int z = 0; z < 3; z++) {
							if (test != 0 && ((k * 9 + z) != (i * 9 + j))
									&& (test == puzzle[k * 9 + z])) {
								// System.out.println("FALSE_6");
								return false;
							}
						}
					}
					break;
				case 57: case 58: case 59:
				case 66: case 67: case 68:
				case 75: case 76: case 77:

					for (int k = 6; k < 9; k++) {
						for (int z = 3; z < 6; z++) {
							if (test != 0 && ((k * 9 + z) != (i * 9 + j))
									&& (test == puzzle[k * 9 + z])) {
								// System.out.println("FALSE_6");
								return false;
							}
						}
					}
					break;
				case 60: case 61: case 62:
				case 69: case 70: case 71:
				case 78: case 79: case 80:

					for (int k = 6; k < 9; k++) {
						for (int z = 6; z < 9; z++) {
							if (test != 0 && ((k * 9 + z) != (i * 9 + j))
									&& (test == puzzle[k * 9 + z])) {
								// System.out.println("FALSE_6");
								return false;
							}
						}
					}
					break;

				default:
					continue;
				}

			}
		}

		return true;
	}

	public void charpuzzle() {											///Lemasolja a lemasolni valo puzzle tablat olyanra, hogy az char elemekbol alljon, igy a jatekfeluletre kiirhato legyen
		chpuzzle = new char[81];
		if(konvert[0]=='0'){
			konvert = new char[9];
			for (int i = 0; i < 9; i++) {
				int index=puzzle[i * 10] - 1;
				if(index!=-1){
					konvert[index] = karakterkeszlet[i];
				}
			}
		}
		for (int i = 0; i < 81; i++) {
			int index=puzzle[i] - 1;
			if(index!=-1){
				chpuzzle[i] = konvert[index];
			}else{
				chpuzzle[i] = '%';
			}
		}
	}
	
	public char[] charpuzzle(int[] tomb) {								///Ugyanazt csinalja, mint a charpuzzle(), csak kapott tomb alapjan visszateresi ertekkel
		char[] chtomb = new char[81];
		if(konvert[0]=='0'){
			konvert = new char[9];
			for (int i = 0; i < 9; i++) {
				int index=puzzle[i * 10] - 1;
				if(index!=-1){
					konvert[index] = karakterkeszlet[i];
				}
			}
		}
		for (int i = 0; i < 81; i++) {
			int index=tomb[i] - 1;
			if(index!=-1){
				chtomb[i] = konvert[index];
			}else{
				chtomb[i] = '%';
			}
		}
		return chtomb;
	}

	public char getChar(int c){											///Visszaadja a kapott szamhoz tartozo karaktert
		return konvert[c];
	}
	
	public int getINT(char c){											///Visszaadja a kapott karakterhez tartozo szamot, ha nincs, akkor -1-et
		for(int i=0;i<9;i++){
			if(konvert[i]==c){
				return i+1;
			}
		}
		return -1;
	}
	
	@Override
	public String toString() {											///A 81 elemu tomboket emesztheto formaban adja vissza (formazott String)
		String string = "";
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				string = string + chpuzzle[i * 9 + j] + " ";
				if ((j + 1) % 3 == 0)
					string += "|";
			}
			string = string + "\n";
			if ((i + 1) % 3 == 0)
				string += "--------------------\n";
		}
		return string;
	}

	public boolean checkBlack(int[] puzzle, int i, int kapott) {		///Megnezi, hogy a kapott mezobe a kapott ertek helyesen beirhato-e
		
		if(kapott < 1 || kapott > 9) return false;
		
		int sor = i / 9;												///A "sor" nevu valtozo az aktualis mezo 9-cel valo egesz osztasanak erteke
		int oszlop = i % 9;												///Az "oszlop" nevu valtozo az aktualis mezo 9-es maradeka
		int[] kilenc = new int[9];										///Egy tomb feltoltve az 1,2...9 ertekekkel
		for (int j = 0; j < 9; j++) {									///A tomb feltoltese
			kilenc[j] = j + 1;
		}

		for (int j = 0; j < 9; j++) {									///Kivesszuk az 1,2...9 ertekekkel feltoltott tombbol azokat az elemeket, amik szerepelnek az aktualis mezo soraban
			if (puzzle[sor * 9 + j] != 0) {
				kilenc[puzzle[sor * 9 + j] - 1] = -1;
			}
		}
		for (int j = 0; j < 9; j++) {									///Kivesszuk az 1,2...9 ertekekkel feltoltott tombbol azokat az elemeket, amik szerepelnek az aktualis mezo oszlopaban
			if (puzzle[j * 9 + oszlop] != 0) {
				kilenc[puzzle[j * 9 + oszlop] - 1] = -1;
			}
		}

		switch (i) {													///Ez a switch-case szerkezet pedig az aktualis mezohoz tartozo 3x3-as teruletet vizsgalja a tablaban. Szinten kiveszi a "kilenc" tombbol azokat az elemeket, amik szerepelnek a 3x3-as resztablaban. Ezt a reszt lehetne szebben is csinalni, azonban azt hiszem, hogy igy sokkal konnyebben atlathato
		case 0: case 1: case 2:
		case 9: case 10: case 11:
		case 18: case 19: case 20:

			for (int k = 0; k < 3; k++) {
				for (int z = 0; z < 3; z++) {
					if (puzzle[k * 9 + z] != 0)
						kilenc[puzzle[k * 9 + z] - 1] = -1;
				}
			}
			break;

		case 3: case 4: case 5:
		case 12: case 13: case 14:
		case 21: case 22: case 23:

			for (int k = 0; k < 3; k++) {
				for (int z = 3; z < 6; z++) {
					if (puzzle[k * 9 + z] != 0)
						kilenc[puzzle[k * 9 + z] - 1] = -1;
				}
			}
			break;

		case 6: case 7: case 8:
		case 15: case 16: case 17:
		case 24: case 25: case 26:

			for (int k = 0; k < 3; k++) {
				for (int z = 6; z < 9; z++) {
					if (puzzle[k * 9 + z] != 0)
						kilenc[puzzle[k * 9 + z] - 1] = -1;
				}
			}
			break;

		case 27: case 28: case 29:
		case 36: case 37: case 38:
		case 45: case 46: case 47:

			for (int k = 3; k < 6; k++) {
				for (int z = 0; z < 3; z++) {
					if (puzzle[k * 9 + z] != 0)
						kilenc[puzzle[k * 9 + z] - 1] = -1;
				}
			}
			break;
		case 30: case 31: case 32:
		case 39: case 40: case 41:
		case 48: case 49: case 50:

			for (int k = 3; k < 6; k++) {
				for (int z = 3; z < 6; z++) {
					if (puzzle[k * 9 + z] != 0)
						kilenc[puzzle[k * 9 + z] - 1] = -1;
				}
			}
			break;
		case 33: case 34: case 35:
		case 42: case 43: case 44:
		case 51: case 52: case 53:

			for (int k = 3; k < 6; k++) {
				for (int z = 6; z < 9; z++) {
					if (puzzle[k * 9 + z] != 0)
						kilenc[puzzle[k * 9 + z] - 1] = -1;
				}
			}
			break;
		case 54: case 55: case 56:
		case 63: case 64: case 65:
		case 72: case 73: case 74:

			for (int k = 6; k < 9; k++) {
				for (int z = 0; z < 3; z++) {
					if (puzzle[k * 9 + z] != 0)
						kilenc[puzzle[k * 9 + z] - 1] = -1;
				}
			}
			break;
		case 57: case 58: case 59:
		case 66: case 67: case 68:
		case 75: case 76: case 77:

			for (int k = 6; k < 9; k++) {
				for (int z = 3; z < 6; z++) {
					if (puzzle[k * 9 + z] != 0)
						kilenc[puzzle[k * 9 + z] - 1] = -1;
				}
			}
			break;
		case 60: case 61: case 62:
		case 69: case 70: case 71:
		case 78: case 79: case 80:

			for (int k = 6; k < 9; k++) {
				for (int z = 6; z < 9; z++) {
					if (puzzle[k * 9 + z] != 0)
						kilenc[puzzle[k * 9 + z] - 1] = -1;
				}
			}
			break;
		}

		for (int j = 0; j < 9; j++) {									///Ez a resz pedig megvizsgalja, hogy maradt-e olyan eleme a kilences tombnek ami egyenlo a kapott ertekkel
			if(kilenc[j] == kapott)
				return true;
		}
		return false;													///Ha pedig a kapott (user altal beirt) ertek nincs benne a lehetseges ertekek tombjeben, akkor false lesz a valasz
	}
	
	public void ToltPuzzle(int[] puzzle) {								///A makePuzzle() szamara veletlenszeruen kitolti egy int tomb elso 9 elemet 1..9 egyedi elemekkel
		int[] kilenc = new int[9];

		for (int i = 0; i < 9; i++) {
			kilenc[i] = i + 1;
		}

		for (int sorszam = 0; sorszam < 9; sorszam++) {

			Random rand = new Random();
			int value = rand.nextInt(9);

			while (kilenc[value] == -1) {
				value = rand.nextInt(9);
			}
			puzzle[sorszam] = kilenc[value];
			kilenc[value] = -1;
		}
		for (int sorszam = 9; sorszam < 81; sorszam++) {
			puzzle[sorszam] = 0;
		}
	}

	public void reset() {												///Visszaallitja az osszes valtozot alapallapotba
		puzzle=new int[81];
		kitakart=new int[81];
		nyolcvanegy=new int[81];
		szamlalo = 0;
		szam = 0;
		szam_2 = 0;
		chpuzzle=new char[81];
		karakterkeszlet=new char[9];
		konvert=new char[9];
		Arrays.fill(konvert, '0');

	}

}
