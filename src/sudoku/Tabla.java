package sudoku;

import java.util.Random;

public class Tabla {
    public int[] puzzle;
    public int szam = 0;
    public char[] chpuzzle;
    public static char[] karakterkeszlet;
    
	public Tabla() {
	    puzzle = new int[81];
	}
	
	
	public boolean makePuzzle(int[] puzzle, int i)
    {
		szam++;
		System.out.println(szam);
		/*if(szam>30000){ 
			System.out.println("shit.");
			return false;
			} */
        for (int x = 1; x< 10 ; x++)
        {
			if (puzzle[i] != 0) {
				if (makePuzzle(puzzle, i + 1))
					return true;
			} else {
				puzzle[i] = x;
				if (checkConstraints(puzzle)) {
					if (i > 79)// terminal condition
					{
						charpuzzle();
						System.out.println(this);// print out the completed
													// puzzle
						puzzle[i] = 0;
						return true;
					} else if (makePuzzle(puzzle, i + 1))
						return true;// find a number for the next square
				}
				puzzle[i] = 0;// this try didn't work, delete the evidence
			}
        }
        return false;
        
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
    
    public void charpuzzle(){
    	chpuzzle=new char[81];
    	char[] konvert=new char[9];
    	for(int i=0;i<9;i++){
    		konvert[puzzle[i*10]-1]=karakterkeszlet[i];
    	}
    	for(int i=0;i<81;i++){
    		chpuzzle[i]=konvert[puzzle[i]-1];
    	}
    }
    
    @Override
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
    
	public void ToltPuzzle(int[] puzzle){
		int[] kilenc = new int[9];
		
		for(int i = 0; i < 9; i++){
			kilenc[i] = i+1;
		}
		
		for(int sorszam = 0; sorszam < 9; sorszam++){
			
	    		Random rand = new Random();
				int value = rand.nextInt(9);
				
				while (kilenc[value] == -1) {
					value = rand.nextInt(9);
	    	}
				puzzle[sorszam] = kilenc[value];
				kilenc[value] = -1;
		}
    }
    
}
