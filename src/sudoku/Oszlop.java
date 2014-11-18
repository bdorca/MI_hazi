package sudoku;

import java.util.ArrayList;

public class Oszlop {
	ArrayList<Mezo> mezok;
	public int sorszam;
	
	public Oszlop(int s){
		sorszam=s;
		mezok=new ArrayList<Mezo>();
	}
}
