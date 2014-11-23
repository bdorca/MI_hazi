package sudoku;

import java.awt.BorderLayout;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.SpinnerNumberModel;

public class SFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextFieldLimit f[][] = new JTextFieldLimit[9][9];
	private JPanel p[][] = new JPanel[3][3];
	private Controller controller;

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

		// char[] asd=controller.getT().charpuzzle(controller.getT().kitakart);
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				f[x][y] = new JTextFieldLimit(1);
				f[x][y].setHorizontalAlignment(JTextField.CENTER);

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
		gbl_buttonPanel.rowHeights = new int[] { 23, 23, 23, 0, 0 };
		gbl_buttonPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_buttonPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
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
		gbc_btnHint.anchor = GridBagConstraints.WEST;
		gbc_btnHint.insets = new Insets(0, 0, 5, 0);
		gbc_btnHint.gridx = 0;
		gbc_btnHint.gridy = 1;
		buttonPanel.add(btnHint, gbc_btnHint);

		JButton btnSolve = new JButton("Solve");
		GridBagConstraints gbc_btnSolve = new GridBagConstraints();
		gbc_btnSolve.insets = new Insets(0, 0, 5, 0);
		gbc_btnSolve.anchor = GridBagConstraints.WEST;
		gbc_btnSolve.gridx = 0;
		gbc_btnSolve.gridy = 2;
		buttonPanel.add(btnSolve, gbc_btnSolve);

		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
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
				controller.setNehezseg((int) spinner.getValue());

			}

		});
		panel.add(spinner);

	}

	public void setBoard() {
		char[] asd = controller.getT().charpuzzle(controller.getT().kitakart);
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				f[x][y].setHorizontalAlignment(JTextField.CENTER);
				// if(controller.getT().chpuzzle[x*9+y]!='%'){
				f[x][y].setText(null);
				f[x][y].setEditable(true);
				if (asd[x * 9 + y] != '%') {
					f[x][y].setText(String.valueOf(controller.getT().chpuzzle[x
							* 9 + y]));
					f[x][y].setEditable(false);

				}
			}
		}
	}

	public class TextListener implements DocumentListener {

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

	public class BtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String str = e.getActionCommand();
			switch (str) {
			case "New Game":
				System.out.println("NEWGAME");
				controller.resetTabla();
				controller.initCharset();
				controller.makeTabla();
				controller.kitakarTabla();
				System.out.println(controller.solveTabla());
				setBoard();
				break;
			case "Hint":
				break;
			case "Solve":
				break;
			}
		}

	}
}
