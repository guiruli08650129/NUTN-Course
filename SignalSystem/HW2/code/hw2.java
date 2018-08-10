import java.awt.*;

import de.erichseifert.gral.data.DataTable;

import javax.swing.*;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.LinkedList;
import java.util.Scanner;

import javax.swing.border.TitledBorder;

import de.congrace.exp4j.*;
import de.erichseifert.gral.data.DataTable;

import java.awt.Window.Type;
import javax.swing.border.LineBorder;

public class hw2 {

	private final static Double PI = 3.1415926;
	public static JFrame frmSignalSystem;
	public static JPanel InputPane = new JPanel();
	public static JPanel controlPane = new JPanel();
	private final JTextField getTs = new JTextField();
	private final JLabel lblForm = new JLabel("X(t) = sin( w * n * Ts ) ");
	private final JLabel lblLeft = new JLabel("(");
	private final JLabel lblRight = new JLabel(")");
	public static JButton create = new JButton("create");
	private final JPanel formPane = new JPanel();
	private final JPanel centerPane = new JPanel();
	private final JPanel HsPane = new JPanel();
	private final JSpinner getm = new JSpinner();
	private final JComboBox sincos = new JComboBox();
	private final JSpinner getn = new JSpinner();
	private final JLabel lblm = new JLabel("m = ");
	private final JLabel lbln = new JLabel("n = ");
	private final JPanel panel = new JPanel();
	private final JPanel arPane = new JPanel();
	private final JPanel frPane = new JPanel();
	private final static JTextField textField = new JTextField();
	private final JPanel btPane = new JPanel();
	private final static  JButton btnN = new JButton("n");
	private final static  JButton btnBack = new JButton("back");
	private final static  JButton btnLog = new JButton("log");
	private final static  JButton btnSqrt = new JButton("sqrt");
	private final static  JButton btnSin = new JButton("sin");
	private final static  JButton btnCos = new JButton("cos");
	private final static  JButton btnLeftBracket = new JButton("(");
	private final static  JButton btnRightBracket = new JButton(")");
	private final static  JButton btnUp = new JButton("^");
	private final static JButton btnClear = new JButton("clear");
	private final static  JButton btn1 = new JButton("1");
	private final static  JButton btn2 = new JButton("2");
	private final static  JButton btn3 = new JButton("3");
	private final static  JButton btn4 = new JButton("4");
	private final static  JButton btn5 = new JButton("5");
	private final static  JButton btn6 = new JButton("6");
	private final static  JButton btn7 = new JButton("7");
	private final static  JButton btn8 = new JButton("8");
	private final static  JButton btn9 = new JButton("9");
	private final static  JButton btn0 = new JButton("0");
	private final static   JButton btnPlus = new JButton("+");
	private final static   JButton btnMinus = new JButton("-");
	private final static   JButton btnMul = new JButton("*");
	private final static   JButton btnDivide = new JButton("/");
	private final JButton btnOutput = new JButton("Output");
	private final JButton btnFr = new JButton("Frequence Response");
	public static double[] x;
	public static int sizeP = 0;
	public static int sizeC = 0;
	public static JTextField[] getp;
	public static JTextField[] getc;
	public static int num = 0;
	private final JRadioButton selectFR = new JRadioButton("Frequency Response");
	private final JRadioButton selectArb = new JRadioButton("Arbitary Input");
	private final JPanel panel_1 = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hw2 window = new hw2();
					window.frmSignalSystem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	 public static boolean expression( String input) throws Exception {

		 	double ts = 0.0;
			JSpinner getNum = new JSpinner();
			
			JTextField getts = new JTextField();
			getts.setText("0.1");
			getts.setEditable(true);
			boolean ok = false;
			try{
			if(input.contains("sin") || input.contains("cos"))
			{
				getNum.setModel(new SpinnerNumberModel(new Integer(2), new Integer(1), null, new Integer(1)));
				Object[] message = { "\u8acb\u8f38\u5165\u9031\u671f\u500b\u6578：", getNum, "\u8acb\u8f38\u5165\u53d6\u6a23\u9031\u671f", getts};
				
				
				
				int option = JOptionPane.showConfirmDialog(null, message, "Output", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) 
				{
						 
					ts = Double.parseDouble(getts.getText());
					num = (int) (((2*PI)/ts)*(int) getNum.getValue());
					x = new double[num];
		
					for(int i = 0 ; i < num ; i++)
					{
					    Calculable calc = new ExpressionBuilder(input)//input = 2*sin(n+3)
					      .withVariable("n", i*ts)// if Ts = 0.1, num是要取的點的個數
					      .build();
					    x[i] = calc.calculate();
					}	
					ok = true;
				}
				
				
			}
			else
			{
				getNum.setModel(new SpinnerNumberModel(new Integer(100), new Integer(1), null, new Integer(1)));
				Object[] message = { "\u8acb\u8f38\u5165\u9ede\u7684\u500b\u6578：", getNum};
				
				int option = JOptionPane.showConfirmDialog(null, message, "Output", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) 
				{
					num = (int) getNum.getValue();
					x = new double[num];
		
					for(int i = 0 ; i < num ; i++)
					{
					    Calculable calc = new ExpressionBuilder(input)//input = 2*sin(n+3)
					      .withVariable("n", i)// num是要取的點的個數
					      .build();
					    x[i] = calc.calculate();
					}
					ok = true;
				}
				
			
			}
			}catch(Exception e)
			{
				JOptionPane.showMessageDialog(null,  "Input format error!!", "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			return ok;
			
	    }
	
	public hw2() {
		initialize();
	}

	
	private void initialize() {

		frmSignalSystem = new JFrame();
		frmSignalSystem.setTitle("Signal System");
		frmSignalSystem.setType(Type.POPUP);
		frmSignalSystem.getContentPane().setBackground(SystemColor.scrollbar);
		frmSignalSystem.setBounds(100, 100, 800, 460);
		frmSignalSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSignalSystem.getContentPane().setLayout(new BoxLayout(frmSignalSystem.getContentPane(), BoxLayout.X_AXIS));
		InputPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Input", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		
		InputPane.setPreferredSize(new Dimension(380, 200));
		InputPane.setLayout(new BoxLayout(InputPane, BoxLayout.Y_AXIS));
		frPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		InputPane.add(frPane);
		frPane.setLayout(new BoxLayout(frPane, BoxLayout.Y_AXIS));
		frPane.add(formPane);
		formPane.setLayout(new BoxLayout(formPane, BoxLayout.Y_AXIS));
		formPane.setPreferredSize(new Dimension(500, 40));
		btnFr.setSelected(true);
		buttonGroup.add(selectFR);
		formPane.add(selectFR);
		lblForm.setForeground(Color.BLACK);
		formPane.add(lblForm);
		lblForm.setFont(new Font("新細明體", Font.BOLD, 14));
		frPane.add(centerPane);
		centerPane.setLayout(new FlowLayout());
		
		sincos.setModel(new DefaultComboBoxModel(new String[] {"sin", "cos"}));
		sincos.setMaximumRowCount(2);		
		centerPane.add(sincos);
		
		centerPane.add(lblLeft);
		
		centerPane.add(new JLabel("w * n *"));
		getTs.setText("0.01");
		getTs.setColumns(5);
		
		centerPane.add(getTs);
		centerPane.add(lblRight);

		
		frmSignalSystem.getContentPane().add(InputPane);
		arPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		InputPane.add(arPane);
		arPane.setLayout(new BoxLayout(arPane, BoxLayout.Y_AXIS));
		
		
		
		arPane.add(panel_1);
		buttonGroup.add(selectArb);
		selectArb.setSelected(true);
		sincos.setEnabled(false);
		getTs.setEditable(false);
		btnOutput.setEnabled(true);
		btnFr.setEnabled(false);
		panel_1.add(selectArb);
		selectArb.setHorizontalAlignment(SwingConstants.CENTER);
		
		selectFR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(e.getSource() == selectFR)
				{
					sincos.setEnabled(true);
					getTs.setEditable(true);
					btnOutput.setEnabled(false);
					btnFr.setEnabled(true);
				}
			}
		});

		selectArb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if(e.getSource() == selectArb)
				{
					sincos.setEnabled(false);
					getTs.setEditable(false);
					btnOutput.setEnabled(true);
					btnFr.setEnabled(false);
				}
			}
		});
		
		
		
		actionPerformed action = new actionPerformed();
		
		textField.setEditable(false);
		textField.setFont(new Font("新細明體", Font.BOLD, 16));
		
		arPane.add(textField);
		
		arPane.add(btPane);
		btPane.setLayout(new GridLayout(0, 4, 0, 0));
		
		btPane.add(btnClear);
		btnClear.addActionListener(action);
		btPane.add(btnBack);
		btnBack.addActionListener(action);
		btPane.add(btnLeftBracket);
		btnLeftBracket.addActionListener(action);
		btPane.add(btnRightBracket);
		btnRightBracket.addActionListener(action);
		btPane.add(btnSin);
		btnSin.addActionListener(action);
		btPane.add(btnCos);
		btnCos.addActionListener(action);
		btPane.add(btnLog);
		btnLog.addActionListener(action);
		btPane.add(btnSqrt);
		btnSqrt.addActionListener(action);
		btPane.add(btnUp);
		btnUp.addActionListener(action);
		btPane.add(btnN);
		btnN.addActionListener(action);
		btPane.add(btn1);
		btn1.addActionListener(action);
		btPane.add(btn2);
		btn2.addActionListener(action);
		btPane.add(btn3);
		btn3.addActionListener(action);
		btPane.add(btn4);
		btn4.addActionListener(action);
		btPane.add(btn5);
		btn5.addActionListener(action);
		btPane.add(btn6);
		btn6.addActionListener(action);
		btPane.add(btn7);
		btn7.addActionListener(action);
		btPane.add(btn8);
		btn8.addActionListener(action);
		btPane.add(btn9);
		btn9.addActionListener(action);
		btPane.add(btn0);
		btn0.addActionListener(action);
		btPane.add(btnPlus);
		btnPlus.addActionListener(action);
		btPane.add(btnMinus);
		btnMinus.addActionListener(action);
		btPane.add(btnMul);
		btnMul.addActionListener(action);
		btPane.add(btnDivide);
		btnDivide.addActionListener(action);

		
		HsPane.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "System", TitledBorder.CENTER, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		HsPane.setPreferredSize(new Dimension(340, 300));
		HsPane.setLayout(new BoxLayout(HsPane, BoxLayout.Y_AXIS));
		frmSignalSystem.getContentPane().add(HsPane);
		panel.setBackground(SystemColor.activeCaption);
		
		HsPane.add(panel);
		JLabel equation = new JLabel(new ImageIcon("123.png"));
		panel.add(equation);
		panel.setPreferredSize(new Dimension(430, 65));
		equation.setHorizontalAlignment(SwingConstants.LEFT);
		
		equation.setPreferredSize(new Dimension(330, 60));
		panel.add(lblm);
		
		
		getm.setPreferredSize(new Dimension(50, 20));
		getm.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		panel.add(getm);
		
		panel.add(lbln);
		panel.add(getn);
		getn.setPreferredSize(new Dimension(50, 20));
		getn.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		panel.add(create);
		
		JButton btnReset = new JButton("reset");
		btnReset.setEnabled(false);
		panel.add(btnReset);
	

		
		controlPane.setPreferredSize(new Dimension(360, 300));
		controlPane.setLayout(null);

		
		JScrollPane jp = new JScrollPane(controlPane);
		jp.setPreferredSize(new Dimension(320, 250));
		jp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JPanel vab = new JPanel();
		vab.add(jp);
		HsPane.add(vab);
		vab.add(btnOutput);		
		btnFr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				String str = (String)sincos.getSelectedItem();
				DataTable magFR = new DataTable(Double.class, Double.class);
				DataTable phaseFR = new DataTable(Double.class, Double.class);
				num = 100;
				
				double ts = Double.parseDouble(getTs.getText());
				//int num = (int) (((2*PI)/ts)*1);//2為週期個數
				x = new double[num];
				
				for(int q = -19 ; q < 19 ; q++)
				{
					for(int i = 0 ; i < num ; i++)
					{
						if(str.equals("sin"))
							x[i] = Math.sin(Math.pow(10.0, q)*i*ts);//if w = pow(10.0, q)
						else
							x[i] = Math.cos(Math.pow(10.0, q)*i*ts);
					}	
					
					String[] sc = new String[sizeC];
					for (int i = 0; i < sizeC; i++) {
						sc[i] = getc[i].getText().trim();
					}
	
					String[] sp = new String[sizeP];
					for (int i = 0; i < sizeP; i++) {
						sp[i] = getp[i].getText().trim();
					}					
					
					double[] c = new double[sc.length];
					for (int i = 0; i < sc.length; i++)
						c[i] = Double.parseDouble(sc[i]);
	
					double[] p = new double[sp.length];
					for (int j = 0; j < sp.length; j++)
						p[j] = Double.parseDouble(sp[j]);
	
					double[] newx = new double[num];
					double[] newy = new double[num];
					double[] total = new double[num];
					double[] temp = new double[num];
					for (int i = 0; i < num; i++) {			
						newx[0] = 1;
						newx[i] = 0;
						newy[i] = 0;
						total[0] = c[0];
						total[i] = 0;	
						temp[i] = 0;
					}
					
					//uni-sample
					for (int n = 0; n < num; n += 1) {
	
						double tempX = 0.0;
						double tempY = 0.0;
	
						for (int m = 0; m < sp.length; m++) {
							if (n - m < 0)
								continue;
							else {
								tempX += p[m] * newx[n - m];
							}
						}
	
						for (int k = 0; k < sc.length; k++) {
							if (n - k - 1 < 0)
								continue;
							else {
								tempY += c[k] * newy[n - k - 1];
							}
						}
						total[n] = tempX + tempY;
					}

					int delay = 0;
					double max = 0.0;
					int cont = 0;
					/*convolution*/
					for(int n = 0 ; n < num ; n++)
					{		
						double segma = 0;
						for(int k = 0 ; k < num ; k++)
						{
							if((n-k) >= 0)
								temp[k] = x[k] * total[n - k];
							else
							{
								temp[k] = x[k] * total[num + n - k];
							}
						}
						for(int j = 0 ; j < num ; j++)
						{
							segma += temp[j];
						}
						if(segma>max)
							max = segma;	
						if(segma <= 0)
							delay += 1;
					}	

					double phi = delay*Math.pow(10.0, q)*ts;
					
					magFR.add((double)q, max);
					phaseFR.add((double)q, phi);

					
				}
				show d = new show(phaseFR, new String("pha"));
				d.setVisible(true);
				

				show s = new show(magFR, new String("mag"));				
				s.setVisible(true);

			}
		});
		vab.add(btnFr);		
		btnOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String userInput = textField.getText();
				if(userInput.isEmpty())
				{
					//System.out.println(userInput);
					JOptionPane.showMessageDialog(null, "Please input function!!", "ERROR", JOptionPane.ERROR_MESSAGE);
				
			}
			else
			{
				try {
					
					if(expression(userInput))
					{
						String[] sc = new String[sizeC];
						for (int i = 0; i < sizeC; i++) {
							sc[i] = getc[i].getText().trim();
						}
	
						String[] sp = new String[sizeP];
						for (int i = 0; i < sizeP; i++) {
							sp[i] = getp[i].getText().trim();
						}
						
						show s = new show(sc, sp, x, num);
						s.setVisible(true);
					}
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}}
			}
		});
		

		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlPane.removeAll();
				controlPane.repaint();
				create.setEnabled(true);
				btnReset.setEnabled(false);
				
			}
		});

		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {


				
				sizeP = (int)getm.getValue();
				
				//if((9-sizeP) < 0)
					//controlPane.setBounds(10, 10, 570, 400 + 30*(sizeP-9));
					
				
				JLabel[] p = new JLabel[sizeP];
				getp = new JTextField[sizeP];
				for (int i = 0; i < sizeP; i++) {
					p[i] = new JLabel("p" + Integer.toString(i) + " =");
					p[i].setBounds(30, 10 + 25 * i, 40, 20);
					p[i].setFont(new Font("新細明體", Font.BOLD, 14));
					controlPane.add(p[i]);

					getp[i] = new JTextField();
					getp[i].setBounds(75, 10 + 25 * i, 50, 20);
					getp[i].setText("1.0");
					getp[i].setColumns(5);
					controlPane.add(getp[i]);
				}

				sizeC = (int)getn.getValue();
				JLabel[] c = new JLabel[sizeC];
				getc = new JTextField[sizeC];
				for (int i = 0; i < sizeC; i++) {
					c[i] = new JLabel("c" + Integer.toString(i) + " =");
					c[i].setBounds(130, 10 + 25 * i, 40, 20);
					c[i].setFont(new Font("新細明體", Font.BOLD, 14));
					controlPane.add(c[i]);

					getc[i] = new JTextField();
					getc[i].setBounds(180, 10 + 25 * i, 50, 20);
					getc[i].setText("0.0");
					getc[i].setColumns(5);
					controlPane.add(getc[i]);
				}
				
				controlPane.repaint();
				create.setEnabled(false);
				btnReset.setEnabled(true);
				
			}
		});
		
		
		
		
	}
	static class actionPerformed implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
			String s = textField.getText();
			
			if(e.getSource() == btnClear)
			{
				s = new String();
				textField.setText(null);
			}
			else if(e.getSource() == btnUp)
			{
				s = s + "^";
				textField.setText(s);
			}
			else if(e.getSource() == btnLeftBracket)
			{
				s = s + "(";
				textField.setText(s);
			}
			else if(e.getSource() == btnRightBracket)
			{
				s = s + ")";
				textField.setText(s);				
			}
			else if(e.getSource() == btnN)
			{
				s = s + "n";
				textField.setText(s);
			}
			else if(e.getSource() == btnBack)
			{
				if(s.length()!= 0)
					s = s.substring(0, s.length()-1);
				textField.setText(s);
			}
			else if(e.getSource() == btnLog)
			{
				s = s + "log(";
				textField.setText(s);
			}
			else if(e.getSource() == btnSqrt)
			{
				s = s + "sqrt(";
				textField.setText(s);
			}
			else if(e.getSource() == btnSin)
			{
				s = s+ "sin(";
				textField.setText(s);
			}
			else if(e.getSource() == btnCos)
			{
				s = s+"cos(";
				textField.setText(s);
			}
			else if(e.getSource() == btn1)
			{
				s = s+"1";
				textField.setText(s);
			}
			else if(e.getSource() == btn2)
			{
				s = s+"2";
				textField.setText(s);
			}
			else if(e.getSource() == btn3)
			{
				s = s+"3";
				textField.setText(s);
			}
			else if(e.getSource() == btn4)
			{
				s = s+"4";
				textField.setText(s);
			}
			else if(e.getSource() == btn5)
			{
				s = s+"5";
				textField.setText(s);
			}
			else if(e.getSource() == btn6)
			{
				s = s+"6";
				textField.setText(s);
			}
			else if(e.getSource() == btn7)
			{
				s = s+"7";
				textField.setText(s);
			}
			else if(e.getSource() == btn8)
			{
				s = s+"8";
				textField.setText(s);
			}
			else if(e.getSource() == btn9)
			{
				s = s+"9";
				textField.setText(s);
			}
			else if(e.getSource() == btn0)
			{
				s = s+"0";
				textField.setText(s);
			}
			else if(e.getSource() == btnPlus)
			{
				s = s+"+";
				textField.setText(s);
			}
			else if(e.getSource() == btnMinus)
			{
				s = s+"-";
				textField.setText(s);
			}
			else if(e.getSource() == btnMul)
			{
				s = s+"*";
				textField.setText(s);
			}
			else if(e.getSource() == btnDivide)
			{
				s = s+"/";
				textField.setText(s);
			}
			else
				;
		
				
			
		}
	}
}
