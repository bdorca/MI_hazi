package sudoku;

/**
 * Fo osztaly
 */
public class Main {

	/**
	 * Letrehoz egy Controllert es egy SFrame-et
	 */
	
	public static void main(String[] args) {
		Controller c=new Controller();

		SFrame frame=new SFrame(c);
		frame.setVisible(true);
	}
}

