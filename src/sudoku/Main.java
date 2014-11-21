package sudoku;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//van-e ebben main?
//yay
import java.util.Random;



//SSSSSSSSSSSSSSS UUUUUUUU     UUUUUUUUDDDDDDDDDDDDD             OOOOOOOOO     KKKKKKKKK    KKKKKKKUUUUUUUU     UUUUUUUU
//SS:::::::::::::::SU::::::U     U::::::UD::::::::::::DDD        OO:::::::::OO   K:::::::K    K:::::KU::::::U     U::::::U
//S:::::SSSSSS::::::SU::::::U     U::::::UD:::::::::::::::DD    OO:::::::::::::OO K:::::::K    K:::::KU::::::U     U::::::U
//S:::::S     SSSSSSS U:::::U     U:::::UUDDD:::::DDDDD:::::D  O:::::::OOO:::::::OK:::::::K   K::::::KUU:::::U     U:::::UU
//S:::::S             U:::::U     U:::::U   D:::::D    D:::::D O::::::O   O::::::OKK::::::K  K:::::KKK U:::::U     U:::::U 
//S:::::S             U:::::D     D:::::U   D:::::D     D:::::DO:::::O     O:::::O  K:::::K K:::::K    U:::::D     D:::::U 
//S::::SSSS          U:::::D     D:::::U   D:::::D     D:::::DO:::::O     O:::::O  K::::::K:::::K     U:::::D     D:::::U 
//SS::::::SSSSS     U:::::D     D:::::U   D:::::D     D:::::DO:::::O     O:::::O  K:::::::::::K      U:::::D     D:::::U 
// SSS::::::::SS   U:::::D     D:::::U   D:::::D     D:::::DO:::::O     O:::::O  K:::::::::::K      U:::::D     D:::::U 
//    SSSSSS::::S  U:::::D     D:::::U   D:::::D     D:::::DO:::::O     O:::::O  K::::::K:::::K     U:::::D     D:::::U 
//         S:::::S U:::::D     D:::::U   D:::::D     D:::::DO:::::O     O:::::O  K:::::K K:::::K    U:::::D     D:::::U 
//         S:::::S U::::::U   U::::::U   D:::::D    D:::::D O::::::O   O::::::OKK::::::K  K:::::KKK U::::::U   U::::::U 
//SSSSSSS     S:::::S U:::::::UUU:::::::U DDD:::::DDDDD:::::D  O:::::::OOO:::::::OK:::::::K   K::::::K U:::::::UUU::::::U 
//S::::::SSSSSS:::::S  UU:::::::::::::UU  D:::::::::::::::DD    OO:::::::::::::OO K:::::::K    K:::::K  UU:::::::::::::UU  
//S:::::::::::::::SS     UU:::::::::UU    D::::::::::::DDD        OO:::::::::OO   K:::::::K    K:::::K    UU:::::::::UU    
//SSSSSSSSSSSSSSS         UUUUUUUUU      DDDDDDDDDDDDD             OOOOOOOOO     KKKKKKKKK    KKKKKKK      UUUUUUUUU      
                                                                                                                      
                                                                                                                      
                                                                                                                      
                                                                                                                      




                                                                                                                      
                                                                                                                      
public class Main {

	public static void main(String[] args){
		FileReader fr;
		try {
			
			fr = new FileReader("9char.txt");
			BufferedReader br = new BufferedReader(fr);
			Random rand= new Random();
			int sorszam=rand.nextInt(1758);
			for(int i=0;i<sorszam;i++){
				br.readLine();
			}
			String szo=br.readLine();
			System.out.println(szo);
			Mezo.karakterkeszlet=szo.toCharArray();
			
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Tabla t=new Tabla();
		//t.feltolt();
		//t.NegyzetesFeltoltes();
		//t.print();
		//t.NegyzetPrint();
		t.ToltPuzzle(t.puzzle);
		t.makePuzzle(t.puzzle, 9);
		//System.out.print(t.negyzetek.get(0).mezoim[2][2]);
	}//Main f�ggv�ny v�ge
}//Main v�ge
