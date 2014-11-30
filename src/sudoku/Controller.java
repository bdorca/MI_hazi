package sudoku;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Controller {
	private Tabla t;
	private int nehezseg=1;
	
	
	public Controller(){
		t=new Tabla();
	}
	
	public void initCharset(){
		FileInputStream fr;
		try {
//			fr = new FileReader("C:\\Users\\Legoo\\Desktop\\f�l�v#5\\MI\\sudoku\\MI_hazi\\src\\9char.txt");
			fr= new FileInputStream("9char.txt");
			BufferedReader br = new BufferedReader( new InputStreamReader(fr,"UTF-8"));
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
	
	public void makeTabla(){
		t.ToltPuzzle(t.puzzle);
		while (!(t.makePuzzle(t.puzzle, 0))) {
			for (int i = 0; i < 10; i++) {
				System.out
						.println("HEUREEEEEEEEEEEEEEEEEEEEEKAAAAAAAAAAAAAAAAAAAAAAAA!!!!!!!!!!!!!44");
			}
			t.szam = 0;
			t.ToltPuzzle(t.puzzle);
		}
		t.save81();
	}
	
	public void kitakarTabla(){
		t.kitakartNum=0;
		for (int i = 0; i < (nehezseg * 10 + 1); i++) { // Az, hogy hány számot távolítsunk el (ha mindig mást generál a két random)
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
		t.kitakart =t.puzzle.clone();
		t.charpuzzle();
		System.out.println("\n" + t.toString() + "\n");
	}
	
	public boolean solveTabla(){
		boolean megoldva=false;
		while(!megoldva){
			t.load81();
			kitakarTabla();
			System.out.println("A megoldas:");
			t.solvePuzzle(t.puzzle, 0);	
			megoldva=true;
			
			for(int i=0;i<80;i++){
				if(t.puzzle[i]!=t.nyolcvanegy[i]){
					megoldva=false;
				}
			}
		}
		return megoldva;
	}
	
	public void setNehezseg(int nehezseg) {
		this.nehezseg = nehezseg;
	}
	
	public int getNehezseg() {
		return nehezseg;
	}
	
	public Tabla getT() {
		return t;
	}

	public void resetTabla() {
		t.reset();
	}
	
	public String printINT(int[] t){
		String str=new String();
		for(int i=0;i<t.length;i++){
			str+=t[i]+" ";
			if(i%8==0){
				str+="\n";
			}
		}
		return str;
	}
}
