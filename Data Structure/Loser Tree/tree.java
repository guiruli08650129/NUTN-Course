import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import java.awt.TextField;
import java.awt.Button;
import java.util.Random;
import java.lang.Math.*;
import java.awt.event.TextListener;
import java.awt.event.TextEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Label;
import java.awt.Font;

public class tree implements ActionListener, TextListener {
	Random r = new Random();
	private JFrame frame;
	private TextField[] A = new TextField[4];
	private TextField[] B = new TextField[4];
	private TextField textField;
	private TextField textField_1;
	private TextField textField_2;
	private TextField textField_3;
	private TextField textField_4;
	private TextField textField_5;
	private TextField textField_6;
	static int i = 0;
	static int j = 0;
	protected Button button;
	protected Button button_1;
	protected int[] x = { 0, 0, 0, 0};
	protected int[] y = { 0, 0, 0, 0};
	private Button btnNewButton;
	private TextField textField_7;
	private Label label_1;
	private Label label_2;
	private Label label_3;
	private Label label_4;
	private Label label_5;
	private Label label_6;
	private Label label_7;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tree window = new tree();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public tree() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Loser Tree");
		frame.setBounds(100, 100, 500, 400);
		frame.getContentPane().setLayout(null);
		
		
		A[0] = new TextField();		
		A[0].setEditable(false);
		A[0].setBounds(10, 271, 40, 30);
		frame.getContentPane().add(A[0]);
		
		B[0] = new TextField();
		B[0].setEditable(false);
		B[0].setBounds(56, 271, 40, 30);
		frame.getContentPane().add(B[0]);
		
		A[1] = new TextField();
		A[1].setEditable(false);
		A[1].setBounds(102, 271, 40, 30);
		frame.getContentPane().add(A[1]);
		
		B[1] = new TextField();
		B[1].setEditable(false);
		B[1].setBounds(148, 271, 40, 30);
		frame.getContentPane().add(B[1]);
		
		A[2] = new TextField();
		A[2].setEditable(false);
		A[2].setBounds(194, 271, 40, 30);
		frame.getContentPane().add(A[2]);
		
		B[2] = new TextField();
		B[2].setEditable(false);
		B[2].setBounds(240, 271, 40, 30);
		frame.getContentPane().add(B[2]);
		
		A[3] = new TextField();
		A[3].setEditable(false);
		A[3].setBounds(286, 271, 40, 30);
		frame.getContentPane().add(A[3]);
		
		B[3] = new TextField();
		B[3].setEditable(false);
		B[3].setBounds(332, 271, 40, 30);
		frame.getContentPane().add(B[3]);
		
		textField = new TextField();
		textField.setEditable(false);
		textField.setBounds(171, 66, 40, 30);
		textField.addTextListener(this);
		frame.getContentPane().add(textField);
		
		textField_1 = new TextField();
		textField_1.setEditable(false);
		textField_1.setBounds(171, 113, 40, 30);
		frame.getContentPane().add(textField_1);
		
		textField_2 = new TextField();
		textField_2.setEditable(false);
		textField_2.setBounds(80, 155, 40, 30);
		frame.getContentPane().add(textField_2);
		
		textField_3 = new TextField();
		textField_3.setEditable(false);
		textField_3.setBounds(259, 155, 40, 30);
		frame.getContentPane().add(textField_3);
		
		textField_4 = new TextField();
		textField_4.setEditable(false);
		textField_4.setBounds(30, 215, 40, 30);
		frame.getContentPane().add(textField_4);
		
		textField_5 = new TextField();
		textField_5.setEditable(false);
		textField_5.setBounds(130, 215, 40, 30);
		frame.getContentPane().add(textField_5);
		
		textField_6 = new TextField();
		textField_6.setEditable(false);
		textField_6.setBounds(210, 215, 40, 30);
		frame.getContentPane().add(textField_6);		

		button = new Button("A");
        button.setActionCommand("A");
        button.addActionListener(this);
		button.setBounds(10, 10, 76, 23);
		frame.getContentPane().add(button);		
		
		button_1 = new Button("B");
        button_1.setActionCommand("B");
        button_1.addActionListener(this);
		button_1.setBounds(112, 10, 76, 23);		
		frame.getContentPane().add(button_1);
		
		btnNewButton = new Button("BUILD");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
		BBB(x, y);				
			}
		});
		btnNewButton.setBounds(210, 10, 87, 23);
		
		textField_7 = new TextField();
		textField_7.setEditable(false);
		textField_7.setBounds(310, 215, 40, 30);
		frame.getContentPane().add(textField_7);
		
		Label label = new Label("A");
		label.setFont(new Font("Dialog", Font.BOLD, 18));
		label.setBounds(25, 310, 23, 23);
		frame.getContentPane().add(label);
		
		label_1 = new Label("B");
		label_1.setFont(new Font("Dialog", Font.BOLD, 18));
		label_1.setBounds(70, 310, 23, 23);
		frame.getContentPane().add(label_1);
		
		label_2 = new Label("A");
		label_2.setFont(new Font("Dialog", Font.BOLD, 18));
		label_2.setBounds(115, 310, 23, 23);
		frame.getContentPane().add(label_2);
		
		label_3 = new Label("B");
		label_3.setFont(new Font("Dialog", Font.BOLD, 18));
		label_3.setBounds(160, 310, 23, 23);
		frame.getContentPane().add(label_3);
		
		label_4 = new Label("A");
		label_4.setFont(new Font("Dialog", Font.BOLD, 18));
		label_4.setBounds(205, 310, 23, 23);
		frame.getContentPane().add(label_4);
		
		label_5 = new Label("B");
		label_5.setFont(new Font("Dialog", Font.BOLD, 18));
		label_5.setBounds(250, 310, 23, 23);
		frame.getContentPane().add(label_5);
		
		label_6 = new Label("A");
		label_6.setFont(new Font("Dialog", Font.BOLD, 18));
		label_6.setBounds(295, 310, 23, 23);
		frame.getContentPane().add(label_6);
		
		label_7 = new Label("B");
		label_7.setFont(new Font("Dialog", Font.BOLD, 18));
		label_7.setBounds(340, 310, 23, 23);
		frame.getContentPane().add(label_7);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if((cmd == "A") && (i < 4) )
		{
			x[i] = java.lang.Math.abs(r.nextInt()%100 + 1);
			A[i].setText(Integer.toString(x[i]));
			i++;
			if( i == 4 )
				button.setEnabled(false);
		}
		if((cmd == "B") && (j < 4) )
		{
			y[j] = java.lang.Math.abs(r.nextInt()%100 + 1);
			B[j].setText(Integer.toString(y[j]));
			j++;
			if( j == 4 )
				button_1.setEnabled(false);
		}			
		if( i == 4 && j == 4 )
		{
			frame.getContentPane().add(btnNewButton);
			i++;
			j++;
		}
		if((cmd == "A") && (i > 4) )
		{					
			for( int q = 0 ; q < 4 ; q++)
				if( x[q] == 0 )
				{
					x[q] = java.lang.Math.abs(r.nextInt()%100 + 1);
					A[q].setText(Integer.toString(x[q]));
					button.setEnabled(false);
				}

		}
		if((cmd == "B") && (j > 4) )
		{
			for( int q = 0 ; q < 4 ; q++)
				if( y[q] == 0 )
				{
					y[q] = java.lang.Math.abs(r.nextInt()%100 + 1);
					B[q].setText(Integer.toString(y[q]));
					button.setEnabled(false);
				}
		}
	}
	
	/**
	 * Build the loser tree.
	 */
	public void BBB( int x[], int y[] ) {

		int[] c = new int[4];
		int[] d = new int[2];
		
		textField_4.setText(Integer.toString(plusB(x[0], y[0])));
		textField_5.setText(Integer.toString(plusB(x[1], y[1])));		
		textField_6.setText(Integer.toString(plusB(x[2], y[2])));		
		textField_7.setText(Integer.toString(plusB(x[3], y[3])));
		
		for( int i = 0 ; i < 4 ; i++)
			c[i] = build(x[i], y[i]);		
		textField_2.setText(Integer.toString(plusB(c[0], c[1])));
		textField_3.setText(Integer.toString(plusB(c[2], c[3])));

		d[0] = build(c[0], c[1]);	
		d[1] = build(c[2], c[3]);				
		textField_1.setText(Integer.toString(plusB(d[0], d[1])));
		
		int e = build(d[0], d[1]);
		textField.setText(Integer.toString( d[0] > d[1] ? d[0] : d[1] ));		
		
		System.out.print("\n");
		System.out.println( "Maximum Value: " + e);
	}
	
	public int build( int a, int b )
	{

			if( a > b )
			{
				return a;
			}
			else
			{
				return b;
			}
		
	}
	
	public int plusB( int a, int b )
	{

			if( a > b )
			{
				return b;
			}
			else
			{
				return a;
			}
		
	}

	@Override
	public void textValueChanged(TextEvent arg0) {
		int target = Integer.parseInt(textField.getText());
		for( int q = 0 ; q < 4 ; q++)
			if( x[q] == target )
			{
				x[q] = 0;
				button.setEnabled(true);
			}
		for( int w = 0 ; w < 4 ; w++)
			if( y[w] == target )
			{
				y[w] = 0;
				button_1.setEnabled(true);
			}
	}
}
