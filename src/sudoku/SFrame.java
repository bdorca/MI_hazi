package sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.xeiam.xchart.Chart;
import com.xeiam.xchart.ChartBuilder;
import com.xeiam.xchart.Series;
import com.xeiam.xchart.SeriesColor;
import com.xeiam.xchart.SeriesLineStyle;
import com.xeiam.xchart.SeriesMarker;
import com.xeiam.xchart.StyleManager.ChartType;
import com.xeiam.xchart.StyleManager.LegendPosition;
import com.xeiam.xchart.StyleManager.TextAlignment;
import com.xeiam.xchart.XChartPanel;

public class SFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 650261138298404165L;
	

	private JPanel contentPane;
	private JTextFieldLimit f[][] = new JTextFieldLimit[9][9];
	private JPanel p[][] = new JPanel[3][3];
	private Controller controller;
	private boolean helpmode=false;
	private JLabel lblTime;
	private Timer timer;
	private int time;
	private boolean ended=true;
	private int nehezseg=1;
	private int rosszTipp=0;
	private int tipp=0;
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

		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				f[x][y] = new JTextFieldLimit(1);
				f[x][y].setEditable(false);
				f[x][y].setHorizontalAlignment(JTextField.CENTER);
				f[x][y].getDocument().addDocumentListener(tl);

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
		gbl_buttonPanel.rowHeights = new int[] { 23, 23, 23, 0, 0, 0 };
		gbl_buttonPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_buttonPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
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

		JLabel lblLevel = new JLabel("level (1..5)");
		panel.add(lblLevel);

		final JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 5, 1));
		spinner.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				nehezseg=(Integer) spinner.getValue();
				
			}
		});
		panel.add(spinner);
		
		JToggleButton tglbtnHelpMe = new JToggleButton("Help ME");
		GridBagConstraints gbc_tglbtnHelpMe = new GridBagConstraints();
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
		if(controller.getT().kitakartNum<=0){ 
			solved();
			return;
		}
		int value = 0;
		Random rand = new Random();
		value = rand.nextInt(81);
		while(controller.getT().kitakart[value] > 0 && controller.getT().kitakart[value]==controller.getT().nyolcvanegy[value]){
			value = rand.nextInt(81);
		}
		int ertek=controller.getT().nyolcvanegy[value];

		time+=60;		
		
		f[value/9][value%9].setText(String.valueOf(controller.getT().getChar(ertek-1)));
		f[value/9][value%9].setEditable(false);
		f[value/9][value%9].setBackground(new Color(66,213,229));
		
		controller.getT().kitakart[value]=controller.getT().nyolcvanegy[value];
	}

	public void solved(){
		ended=true;
		timer.stop();
		
		File f=new File("scores.txt");
		try {
			
		    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(f, true)));
		    String now=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
		    System.out.println(now+" "+new Date());
		    out.println(controller.getNehezseg()+";"+now+";"+time+";"+tipp+";"+rosszTipp);
		    out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		
		JPanel xcpanel=drawChart(f);
		JPanel p=new JPanel();
		p.setLayout(new BorderLayout());
		JLabel label=new JLabel(String.format("Ön nyert! ideje: %02d:%02d", time/60,time%60));
		label.setFont(new Font("Courier", Font.BOLD,20));

		p.add(label,BorderLayout.NORTH);
		p.add(xcpanel,BorderLayout.CENTER);
		JOptionPane.showMessageDialog(this,p,"WIN" , JOptionPane.PLAIN_MESSAGE);
		
	}
	
	public JPanel drawChart(File f){
		FileReader fr;
		try {
			fr = new FileReader(f);
			BufferedReader br=new BufferedReader(fr);
			
			ArrayList<Date> dateList=new ArrayList<Date>();
			ArrayList<Integer> timeList=new ArrayList<Integer>();
			ArrayList<Integer> tippList=new ArrayList<Integer>(); 
			ArrayList<Integer> rosszTippList=new ArrayList<Integer>();
			ArrayList<Date> cheatList=new ArrayList<Date>();

			
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String line;
			String[] adat;
			int neh=controller.getNehezseg();
			
        	while (true) {
    			line = br.readLine();
    			if (line == null){
    				break;
    			}
    			adat=line.split(";");
    			if(Integer.parseInt(adat[0])==neh){
    				dateList.add(sdf.parse(adat[1]));
    				timeList.add(Integer.parseInt(adat[2]));
    				if(timeList.get(timeList.size()-1)==0){
    					cheatList.add(dateList.get(dateList.size()-1));
    				}
    				tippList.add(Integer.parseInt(adat[3]));
    				rosszTippList.add(Integer.parseInt(adat[4]));
    			}
			}
        	br.close();
        	
        	while(dateList.size()>5){
        		if(dateList.get(0)==cheatList.get(0)){
        			cheatList.remove(0);
        		}
        		dateList.remove(0);
        		timeList.remove(0);
        		tippList.remove(0);
        		rosszTippList.remove(0);
        	}

        	JPanel panel=new JPanel();
        	panel.setLayout(new BorderLayout());
        	
        	Chart chart1 = new ChartBuilder().width(600).height(250).title(neh+". szint legutóbbi játékainak eredménye").build();
        	chart1.getStyleManager().setLegendVisible(false);
        	Series series=chart1.addSeries("megfejtés ideje", dateList, timeList);
        	
        	series.setLineColor(SeriesColor.BLUE);
        	series.setMarkerColor(Color.BLUE);
        	series.setMarker(SeriesMarker.CIRCLE);
        	series.setLineStyle(SeriesLineStyle.DASH_DASH);

        	chart1.setXAxisTitle("dátum");
        	chart1.getStyleManager().setDatePattern("MM-dd HH:mm");
        	chart1.getStyleManager().setYAxisLabelAlignment(TextAlignment.Right);
        	chart1.setYAxisTitle("megfejtés ideje [sec]");
        	chart1.getStyleManager().setAxisTickLabelsFont(new Font("Courier", Font.PLAIN, 10));
        	 
        	chart1.getStyleManager().setPlotPadding(5);
        	if(cheatList.size()>0){
	        	ArrayList<Integer> nullList=new ArrayList<Integer>();
	        	for(int i=0;i<cheatList.size();i++){
	        		nullList.add(0);
	        	}
	        	series=chart1.addSeries("csalás", cheatList, nullList);
	        	series.setMarkerColor(Color.RED);
	        	series.setMarker(SeriesMarker.CIRCLE);
	        	series.setLineStyle(SeriesLineStyle.NONE);
        	}
        	
        	XChartPanel xcpanel1=new XChartPanel(chart1);
        	
        	panel.add(xcpanel1,BorderLayout.NORTH);
        	
        	Chart chart2 = new ChartBuilder().chartType(ChartType.Area).width(600).height(250).title(neh+". szint próbálkozások száma").xAxisTitle("dátum").yAxisTitle("db").build();
        	
        	series=chart2.addSeries("összes próbálkozás", dateList, tippList);
        	series.setFillColor(new Color(0,0,255,128));
        	series.setLineColor(new Color(0,0,255,128));
        	series.setMarkerColor(new Color(0,0,255,128));
        	
        	series=chart2.addSeries("rossz próbálkozás", dateList, rosszTippList);
        	series.setFillColor(new Color(255,0,0,128));
        	series.setLineColor(new Color(255,0,0,128));
        	series.setMarkerColor(new Color(255,0,0,128));

        	chart2.getStyleManager().setLegendPosition(LegendPosition.InsideNW);
        	chart2.getStyleManager().setAxisTitlesVisible(true);
        	chart2.getStyleManager().setDatePattern("MM-dd HH:mm");
        	chart2.getStyleManager().setAxisTickLabelsFont(new Font("Courier", Font.PLAIN, 10));
        	
        	XChartPanel xcpanel2=new XChartPanel(chart2);
        	
        	panel.add(xcpanel2, BorderLayout.SOUTH);
        	
        	return panel;
        	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
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
				tipp++;
				int prev=controller.getT().kitakart[x*9+y];
				String betu=f[x][y].getText().toUpperCase();
				int vart =controller.getT().nyolcvanegy[9*x+y];
				if(prev==vart){
					if(betu.charAt(0)!=controller.getT().getChar(prev-1)){
						controller.getT().kitakartNum++;
					}else{
						tipp--;
						return;
					}
				}
				
				controller.getT().kitakart[x*9+y]=controller.getT().getINT(betu.charAt(0));
				if(controller.getT().kitakart[x*9+y]==-1 || controller.getT().kitakart[x*9+y]!=controller.getT().nyolcvanegy[x*9+y]){					
					rosszTipp++;
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
			int x=0, y=0;
			for(int i=0;i<9;i++){
				for(int j=0;j<9;j++){
					if(f[i][j].getDocument()==arg0.getDocument()){
						x=i;
						y=j;
					}
				}
			}
			if(f[x][y].isEditable()){
				System.out.println(controller.getT().kitakartNum);
				System.out.println(x+" "+y+" "+controller.getT().kitakart[x*9+y]);
				int value=x*9+y;

				if(controller.getT().kitakart[value]==controller.getT().nyolcvanegy[value]){
					controller.getT().kitakartNum++;
				}
				controller.getT().kitakart[value]=0;
				if(helpmode){
					f[x][y].setBackground(Color.WHITE);
				}
			}
		}

	}
	public class BtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int z = 0;
			String str = e.getActionCommand();
			if(str.equals("New Game")) z = 0;
			if(str.equals("Hint")) z = 1;
			if(str.equals("Solve")) z = 2;

			switch (z) {
			case 0:
				System.out.println("NEWGAME");
				controller.resetTabla();
				controller.setNehezseg(nehezseg);
				controller.initCharset();
				controller.makeTabla();
				System.out.println(controller.solveTabla());
				setBoard();
				time=0;
				ended=false;
				tipp=0;
				rosszTipp=0;
				timer.start();
				break;
			case 1:
				if(!ended){
					hintBoard();
				}
				break;
			case 2:
				if(!ended){
					time=0;
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
			if(helpmode){
				time++;
			}
			String t=String.format("%02d:%02d", time/60, time%60 );
			lblTime.setText(t);
		}
		
	}
}
