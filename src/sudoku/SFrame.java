package sudoku;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.Random;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JToggleButton;
import javax.swing.Timer;

public class SFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextFieldLimit f[][] = new JTextFieldLimit[9][9];
	private JPanel p[][] = new JPanel[3][3];
	private Controller controller;
	private boolean helpmode=false;
	private JLabel lblTime;
	private Timer timer;
	private int time;
	private boolean ended=false;
	/**
	 * Create the frame.
	 */
	public SFrame(Controller c) {
		super("Sudoku");
		controller = c;
		// controller.initCharset();
		// controller.makeTabla();
		// controller.kitakarTabla(5);
		// System.out.println(controller.solveTabla());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel gamePanel = new JPanel();
		contentPane.add(gamePanel, BorderLayout.CENTER);
		gamePanel.setLayout(new GridLayout(3, 3, 5, 5));
		TextListener tl=new TextListener();
		// char[] asd=controller.getT().charpuzzle(controller.getT().kitakart);
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				f[x][y] = new JTextFieldLimit(1);
				f[x][y].setHorizontalAlignment(JTextField.CENTER);
				f[x][y].getDocument().addDocumentListener(tl);
				// if(asd[x*9+y]!='%'){
				// f[x][y].setText(String.valueOf(controller.getT().chpuzzle[x*9+y]));
				// f[x][y].setEditable(false);
				//
				// }

			}
		}

		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				p[x][y] = new JPanel(new GridLayout(3, 3));
				gamePanel.add(p[x][y]);
			}
		}

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				for (int x = 0; x < 3; x++) {
					for (int y = 0; y < 3; y++) {
						p[i][j].add(f[x + i * 3][y + j * 3]);
					}
				}
			}
		}

		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.EAST);

		BtnListener bl = new BtnListener();
		GridBagLayout gbl_buttonPanel = new GridBagLayout();
		gbl_buttonPanel.columnWidths = new int[] { 83, 0 };
		gbl_buttonPanel.rowHeights = new int[] { 23, 23, 23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_buttonPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_buttonPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		buttonPanel.setLayout(gbl_buttonPanel);

		JButton btnNewGame = new JButton("New Game");
		btnNewGame.addActionListener(bl);
		GridBagConstraints gbc_btnNewGame = new GridBagConstraints();
		gbc_btnNewGame.anchor = GridBagConstraints.WEST;
		gbc_btnNewGame.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewGame.gridx = 0;
		gbc_btnNewGame.gridy = 0;
		buttonPanel.add(btnNewGame, gbc_btnNewGame);

		JButton btnHint = new JButton("Hint");
		GridBagConstraints gbc_btnHint = new GridBagConstraints();
		btnHint.addActionListener(bl);
		gbc_btnHint.anchor = GridBagConstraints.WEST;
		gbc_btnHint.insets = new Insets(0, 0, 5, 0);
		gbc_btnHint.gridx = 0;
		gbc_btnHint.gridy = 1;
		buttonPanel.add(btnHint, gbc_btnHint);

		JButton btnSolve = new JButton("Solve");
		
	    
		btnSolve.addActionListener(bl);
		GridBagConstraints gbc_btnSolve = new GridBagConstraints();
		gbc_btnSolve.insets = new Insets(0, 0, 5, 0);
		gbc_btnSolve.anchor = GridBagConstraints.WEST;
		gbc_btnSolve.gridx = 0;
		gbc_btnSolve.gridy = 2;
		buttonPanel.add(btnSolve, gbc_btnSolve);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 3;
		buttonPanel.add(panel, gbc_panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblLevel = new JLabel("level (1..6)");
		panel.add(lblLevel);

		final JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 6, 1));
		spinner.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				controller.setNehezseg((Integer) spinner.getValue());
			}
		});
		panel.add(spinner);
		
		JToggleButton tglbtnHelpMe = new JToggleButton("Help ME");
		GridBagConstraints gbc_tglbtnHelpMe = new GridBagConstraints();
		gbc_tglbtnHelpMe.insets = new Insets(0, 0, 5, 0);
		tglbtnHelpMe.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				AbstractButton abstractButton = (AbstractButton) e.getSource();
		        helpmode = abstractButton.getModel().isSelected();
				System.out.println(helpmode);
			}
			
		});
		gbc_tglbtnHelpMe.gridx = 0;
		gbc_tglbtnHelpMe.gridy = 4;
		buttonPanel.add(tglbtnHelpMe, gbc_tglbtnHelpMe);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		lblTime = new JLabel("Time");
		panel_1.add(lblTime, BorderLayout.CENTER);
		
		timer = new Timer(1000, new TimeListener());
		timer.setInitialDelay(0);

	}

	public void setBoard() {
		char[] asd = controller.getT().charpuzzle(controller.getT().kitakart);
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				f[x][y].setHorizontalAlignment(JTextField.CENTER);
				f[x][y].setFont(new Font("Courier", Font.BOLD,20));
				// if(controller.getT().chpuzzle[x*9+y]!='%'){
				f[x][y].setText(null);
				f[x][y].setEditable(true);
				f[x][y].setBackground(Color.WHITE);
				if (asd[x * 9 + y] != '%') {
					f[x][y].setEditable(false);
					f[x][y].setText(String.valueOf(asd[x
							* 9 + y]));
					f[x][y].setBackground(new Color(220,220,220));

				}
			}
		}
	}
	
	public void solveBoard() {
		char[] asd = controller.getT().charpuzzle(controller.getT().nyolcvanegy);
		controller.getT().kitakart = controller.getT().nyolcvanegy.clone();
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				if (asd[x * 9 + y] != '%') {
					f[x][y].setEditable(false);
					f[x][y].setText(String.valueOf(asd[x * 9 + y]));

				}
			}
		}
		controller.getT().kitakartNum=0;
	}
	
	public void hintBoard(){
//		int szam = 0;
//		
//		for(int i = 0; i < 81; i++){
//			if(controller.getT().kitakart[i] <= 0){
//				szam++;
//			}
//		}
//		if(szam == 0) return;
		if(controller.getT().kitakartNum<=0){ 
			solved();
			return;
		}
		int value = 0;
		Random rand = new Random();
		value = rand.nextInt(81);
		while(controller.getT().kitakart[value] > 0){
			value = rand.nextInt(81);
		}
		int ertek=controller.getT().nyolcvanegy[value];
		//controller.getT().kitakartNum--;
		// if(controller.getT().chpuzzle[x*9+y]!='%'){
		time+=60;		
		
		f[value/9][value%9].setText(String.valueOf(controller.getT().getChar(ertek-1)));
		f[value/9][value%9].setEditable(false);
		f[value/9][value%9].setBackground(new Color(66,213,229));
		
		controller.getT().kitakart[value]=controller.getT().nyolcvanegy[value];
	}

	public void solved(){
		ended=true;
		timer.stop();
		JOptionPane.showMessageDialog(this, String.format("Ön nyert! ideje: %02d:%02d", time/60,time%60));
	}
	
	public class TextListener implements DocumentListener {

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			int x=0;
			int y=0;
			
			for(int i=0;i<9;i++){
				for(int j=0;j<9;j++){
					if(f[i][j].getDocument()==arg0.getDocument()){
						x=i;
						y=j;
					}
				}
			}
			if(f[x][y].isEditable()){
				int prev=controller.getT().kitakart[x*9+y];
				String betu=f[x][y].getText().toUpperCase();
				int vart =controller.getT().nyolcvanegy[9*x+y];
				if(prev==vart){
					if(betu.charAt(0)!=controller.getT().getChar(prev-1)){
						controller.getT().kitakartNum++;
					}else{
						return;
					}
				}
				char vartC=controller.getT().getChar(vart-1);
				controller.getT().kitakart[x*9+y]=controller.getT().getINT(betu.charAt(0));
				if(controller.getT().kitakart[x*9+y]==-1 || controller.getT().kitakart[x*9+y]!=controller.getT().nyolcvanegy[x*9+y]){					
					if(helpmode){
						f[x][y].setBackground(Color.RED);
					}
				}else{
					controller.getT().kitakartNum--;
					if(helpmode){
						f[x][y].setBackground(Color.GREEN);
					}
				}
				System.out.println(controller.getT().kitakartNum);
				System.out.println(x+" "+y+" "+betu+" "+controller.getT().kitakart[x*9+y]);
				//System.out.println(controller.printINT(controller.getT().kitakart));
			}
			if(controller.getT().kitakartNum==0){
				solved();
			}
		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			
		}

	}
	public class BtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int z = 0;
			String str = e.getActionCommand();
			if(str == "New Game") z = 0;
			if(str == "Hint") z = 1;
			if(str == "Solve") z = 2;

			switch (z) {
			case 0:
				System.out.println("NEWGAME");
				controller.resetTabla();
				controller.initCharset();
				controller.makeTabla();
				controller.kitakarTabla();
				System.out.println(controller.solveTabla());
				setBoard();
				time=0;
				ended=false;
				timer.start();
				break;
			case 1:
				if(!ended){
					hintBoard();
				}
				break;
			case 2:
				if(!ended){
					time=3598;
					solveBoard();
					solved();
				}
				break;
			}
		}

	}
	
	private class TimeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			time++;
			String t=String.format("%02d:%02d", time/60, time%60 );
			lblTime.setText(t);
		}
		
	}
}
