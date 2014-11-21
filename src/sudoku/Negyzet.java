package sudoku;

import java.util.ArrayList;

public class Negyzet {
	ArrayList<Mezo> mezok;
	public int[][] mezoim;
	public int x;
	public int y;
	public int sorszam;
	
	public Negyzet(int s){
		sorszam=s;
		mezok=new ArrayList<Mezo>();
		mezoim = new int[3][3];
	}
}

	