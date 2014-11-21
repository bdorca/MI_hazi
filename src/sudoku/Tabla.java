package sudoku;

import java.util.ArrayList;
import java.util.Random;

public class Tabla {
	ArrayList<Sor> sorok;
	ArrayList<Oszlop> oszlopok;
	ArrayList<Negyzet> negyzetek;

	ArrayList<Mezo> mezok;
    public int[] puzzle;

    public int szam = 0;

	public Tabla() {
		sorok = new ArrayList<Sor>(9);
		oszlopok = new ArrayList<Oszlop>(9);
		negyzetek = new ArrayList<Negyzet>(9);
		mezok = new ArrayList<Mezo>(81);
	    puzzle = new int[81];
		for (int i = 0; i < 9; i++) {
			oszlopok.add(new Oszlop(i));
			sorok.add(new Sor(i));
			negyzetek.add(new Negyzet(i));
		}
		
		  for(int i=0;i<9;i++){
		  	for(int j=0;j<9;j++){ 
			  //sorok.get(i).mezok.add(new Mezo(0,0,0));
			  oszlopok.get(i).mezok.add(new Mezo(0,0,0));
			  //negyzetek.get(i).mezok.add(new Mezo(0,0,0));
		  	} 
		  	
		  	for(int j = 0; j < 3; j++){
		  		for(int k = 0; k < 3; k++){
			  		negyzetek.get(i).mezoim[j][k]=0;
		  		}
		  	}
		  }
		 
	}
	
	
	public boolean rekurz(){
		
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					negyzetek.get(i).mezoim[j][k] = 0;
				}
			}
		}
			
		int[] kilenc = new int[9];
			for(int negyzetszam = 0; negyzetszam < 3; negyzetszam++){
				
				for (int i = 0; i < 9; i++) {
					kilenc[i] = i + 1;
				}
				
					switch(negyzetszam){
					case 0: 
						for(int i = 0; i<3;i++){
							negyzetek.get(0).mezoim[i][i]=i+1;
							kilenc[i] = -1;
						}
						
						for(int j = 0; j<3; j++){
							for(int k = 0; k<3; k++){
								if(j!=k){
									Random rand = new Random();
									int value = rand.nextInt(9);
									int szum = 0;

									while (kilenc[value] == -1) {
										if(szum > 100) System.out.println("GEBASZ VAN_1!!!");
										szum++;
										value = rand.nextInt(9);
									}
									
									negyzetek.get(0).mezoim[j][k]=kilenc[value];
									kilenc[value]=-1;
								}
							}
						}
						break;
						
					case 1:
						for(int i = 0; i<3;i++){
							negyzetek.get(4).mezoim[i][i]=i+4;
							kilenc[i+3] = -1;
						}
						
						for(int j = 0; j<3; j++){
							for(int k = 0; k<3; k++){
								if(j!=k){
									Random rand = new Random();
									int value = rand.nextInt(9);
									int szum = 0;

									while (kilenc[value] == -1) {
										if(szum > 100) System.out.println("GEBASZ VAN_2!!!");
										szum++;
										value = rand.nextInt(9);
									}
									
									negyzetek.get(4).mezoim[j][k]=kilenc[value];
									kilenc[value]=-1;
								}
							}
						}
						break;
					case 2:
						for(int i = 0; i<3;i++){
							negyzetek.get(8).mezoim[i][i]=i+7;
							kilenc[i+6] = -1;
						}
						for(int j = 0; j<3; j++){
							for(int k = 0; k<3; k++){
								if(j!=k){
									Random rand = new Random();
									int value = rand.nextInt(9);
									int szum = 0;

									while (kilenc[value] == -1) {
										if(szum > 100) System.out.println("GEBASZ VAN_3!!!");
										szum++;
										value = rand.nextInt(9);
									}
									
									negyzetek.get(8).mezoim[j][k]=kilenc[value];
									kilenc[value]=-1;
								}
							}
						}
						break;
						
					}
			}//ÁTLÓFELTÖLTÉS VÉGE ------------------------------------------------------------
			

			for (int z = 0; z < 9; z++) {
				kilenc[z] = z + 1;
			}			
			
			for (int i = 0; i < 3; i++) {			
				for (int j = 0; j < 3; j++) {	
					
					for (int z = 0; z < 9; z++) {
						kilenc[z] = z + 1;
					}
					
					for(int k = 0; k<3; k++){
						kilenc[(negyzetek.get(0).mezoim[i][k])-1] = -1;
						kilenc[(negyzetek.get(8).mezoim[k][j])-1] = -1;
					}
					
					for(int x = 0; x < 3; x++){
						for(int y = 0; y < 3; y++){
							if(negyzetek.get(2).mezoim[x][y] != 0){
								kilenc[negyzetek.get(2).mezoim[x][y]-1] = -1;
							}
						}
					}
					
					int kilencke = 0;
					int negyzet = 0;
					int loop = 0;
					for(int x = 0; x < 3; x++){
						for(int y = 0; y < 3; y++){
							if(kilenc[loop] != -1 ) kilencke++;
							loop++;
							if(negyzetek.get(2).mezoim[x][y] == 0) negyzet++;
						}
					}
					if((negyzet > kilencke) && negyzet < 5) {
						return false;
					}
					
					Random rand = new Random();
					int value = rand.nextInt(9);
					int szum = 0;

					while (kilenc[value] == -1) {
						if (szum > 100)
							System.out.println("GEBASZ VAN_4!!!");
						szum++;
						value = rand.nextInt(9);
					}
					
					negyzetek.get(2).mezoim[i][j] = kilenc[value];
				}
				/*if(GEBASZ) {
					i = -1;
					for(int x = 0; x < 3; x++){
						for(int y = 0; y < 3; y++){
							negyzetek.get(2).mezoim[x][y] = 0;
						}
					}
					
				}*/
			}
			//JOBBFELSŐ FELTÖLTÉSÉNEK VÉGE -------------------------------------
			
			/*for (int z = 0; z < 9; z++) {
				kilenc[z] = z + 1;
			}			
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {	
					
					for (int z = 0; z < 9; z++) {
						kilenc[z] = z + 1;
					}
					
					for(int k = 0; k<3; k++){
						kilenc[(negyzetek.get(0).mezoim[k][i])-1] = -1;
						kilenc[(negyzetek.get(8).mezoim[j][k])-1] = -1;
					}
					
					for(int x = 0; x < 3; x++){
						for(int y = 0; y < 3; y++){
							if(negyzetek.get(6).mezoim[x][y] != 0){
								kilenc[negyzetek.get(6).mezoim[x][y]-1] = -1;
							}
						}
					}
					
					Random rand = new Random();
					int value = rand.nextInt(9);
					int szum = 0;

					while (kilenc[value] == -1) {
						if (szum > 100)
							System.out.println("GEBASZ VAN_4!!!");
						szum++;
						value = rand.nextInt(9);
					}
					
					negyzetek.get(6).mezoim[i][j] = kilenc[value];
				}
			}*/
			return true;
	}
	
	public void NegyzetesFeltoltes(){
		int e = 0;
		boolean d = false;
		/*while(!d){
			if(e == 2) d = rekurz();
			d=rekurz();
			e++;
		}*/
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
	
	public void makePuzzle(int[] puzzle, int i)
    {
		szam++;
		System.out.println(szam);
        for (int x = 1; x< 10 ; x++)
        {
            puzzle[i]=x;
            if(checkConstraints(puzzle))
            {
                if (i>79)//terminal condition
                {
                    System.out.println(this);//print out the completed puzzle
                        puzzle[i]=0;
                        return;
                }
                else
                    makePuzzle(puzzle,i+1);//find a number for the next square                          
            }
            puzzle[i]=0;//this try didn't work, delete the evidence 
        }       
        
    }
    public boolean checkConstraints(int[] puzzle)
    {
        int test;
        
      for(int sor = 0; sor < 9; sor++){
    	  test = puzzle[sor*10];
    	  for(int oszlop = 0; oszlop < 9; oszlop++){
        	  if(test != 0 && oszlop != sor && test == puzzle[oszlop*10])
        		  return false;
    	  }
      }
        
     //test that rows have unique values    
      for (int column=0; column<9; column++)
        {
            for (int row=0; row<9; row++)
            {
                test=puzzle[row+column*9];
                for (int j=0;j<9;j++)
                {
                    if( test!=0 &&  row!=j && test==puzzle[j+column*9])
                        return false;
                }
            }
        }
        //test that columns have unique values
        for  (int column=0; column<9; column++) 
        {
             for(int row=0; row<9; row++)
            {
                test=puzzle[column+row*9];
                for (int j=0;j<9;j++)
                {
                    if(test!=0 && row!=j && test==puzzle[column+j*9])
                        return false;
                }
            }
        }
        //implement region test here
        /*
        int[][] regions = new int[9][9];
        int[] regionIndex ={0,3,6,27,30,33,54,57,60};
        for (int region=0; region<9;region++) //for each region
        {

            int j =0;
            for (int k=regionIndex[region];k<regionIndex[region]+27; k=(k%3==2?k+7:k+1))
                {
                    regions[region][j]=puzzle[k];
                    j++;
                }
        }
        for (int i=0;i<9;i++)//region counter
        {
            for (int j=0;j<9;j++)
            {
                for (int k=0;k<9;k++)
                {
                    if (regions[i][j]!=0&&j!=k&&regions[i][j]==regions[i][k])
                    return false;
                }

            }
        }*/
        
        for (int i=0;i<9;i++)//region counter
        {
            for (int j=0;j<9;j++)
            {
				int ertek = i * 9 + j;
				test = puzzle[i * 9 + j];

				switch (ertek) {
				case 0: case 1: case 2:
				case 9: case 10: case 11:
				case 18: case 19: case 20:
					
					for (int k = 0; k < 3; k++) {
						for (int l = 0; l < 3; l++) {
							if (test != 0 && ((k * 9 + l) != (i * 9 + j)) && (test == puzzle[k * 9 + l]))
								return false;
						}
					}
					break;
				
				case 3: case 4: case 5:
				case 12: case 13: case 14:
				case 21: case 22: case 23:
					
					for (int k = 0; k < 3; k++) {
						for (int z = 3; z < 6; z++) {
							if (test != 0 && ((k * 9 + z) != (i * 9 + j)) && (test == puzzle[k * 9 + z])){
								System.out.println("FALSE");
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
							if (test != 0 && ((k * 9 + z) != (i * 9 + j)) && (test == puzzle[k * 9 + z])){
								System.out.println("FALSE_2");
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
							if (test != 0 && ((k * 9 + z) != (i * 9 + j)) && (test == puzzle[k * 9 + z])){
								System.out.println("FALSE_3");
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
							if (test != 0 && ((k * 9 + z) != (i * 9 + j)) && (test == puzzle[k * 9 + z])){
								System.out.println("FALSE_4");
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
							if (test != 0 && ((k * 9 + z) != (i * 9 + j)) && (test == puzzle[k * 9 + z])){
								System.out.println("FALSE_5");
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
							if (test != 0 && ((k * 9 + z) != (i * 9 + j)) && (test == puzzle[k * 9 + z])){
								System.out.println("FALSE_6");
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
							if (test != 0 && ((k * 9 + z) != (i * 9 + j)) && (test == puzzle[k * 9 + z])){
								System.out.println("FALSE_6");
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
							if (test != 0 && ((k * 9 + z) != (i * 9 + j)) && (test == puzzle[k * 9 + z])){
								System.out.println("FALSE_6");
								return false;
								}
						}
					}
					break;
					
					
					default: continue;
				}

            }
        }
        
    return true;

    }
    public String toString()
    {
        String string= "";
        for (int i=0; i <9;i++)
        {
            for  (int j = 0; j<9;j++)
            {
                string = string+puzzle[i*9+j]+" ";
                if((j+1)%3 == 0) string += "|";
            }
            string =string +"\n";
            if((i+1)%3 == 0) string += "--------------------\n";
        }
        return string;
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
				System.out.println("-------------------------------------------------------------");
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
	
	void NegyzetPrint(){
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 3; j++){
				for(int k = 0; k < 3; k++){
					System.out.print(negyzetek.get(j).mezoim[i][k]);
					System.out.print(" ");
				}
				System.out.print("|");
			}
			System.out.print("\n");
		}
		System.out.println("---------------------");
		for(int i = 0; i < 3; i++){
			for(int j = 3; j < 6; j++){
				for(int k = 0; k < 3; k++){
					System.out.print(negyzetek.get(j).mezoim[i][k]);
					System.out.print(" ");
				}
				System.out.print("|");
			}
			System.out.print("\n");
		}
		System.out.println("---------------------");
		for(int i = 0; i < 3; i++){
			for(int j = 6; j < 9; j++){
				for(int k = 0; k < 3; k++){
					System.out.print(negyzetek.get(j).mezoim[i][k]);
					System.out.print(" ");
				}
				System.out.print("|");
			}
			System.out.print("\n");
		}
	}
	
}
