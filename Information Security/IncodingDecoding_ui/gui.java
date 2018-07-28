import java.awt.EventQueue;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class gui {

	private JFrame frame;
	private static JButton incode = new JButton("Incode");
	private static JButton decode = new JButton("Decode");
	private static JTextArea outputarea = new JTextArea();
	private static JTextArea inputarea = new JTextArea();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui window = new gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public gui() {
		initialize();
	}

	private void initialize() {
		
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("lock.png"));
		frame.setTitle("Information Security Homework#1");
		frame.setBounds(100, 100, 650, 475);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(frame,
						"\u78ba\u5b9a\u8981\u7d50\u675f\u7a0b\u5f0f\u55ce?",
						"\u78ba\u8a8d\u8a0a\u606f", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE);
				if (result == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		
		JPanel informationP = new JPanel();
		informationP.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Description", TitledBorder.CENTER, TitledBorder.TOP, null, Color.GRAY));
		informationP.setPreferredSize(new Dimension(630, 85));
		frame.getContentPane().add(informationP);
		
		JLabel label1 = new JLabel("\u9019\u662f\u4e00\u500b\u7c21\u55ae\u7684\u52a0\u89e3\u5bc6\u7cfb\u7d71"+
									"\uff0c\u8acb\u5728\u5de6\u4e0b\u65b9\u8f38\u5165\u6587\u7ae0\uff0c");
		JLabel label2 = new JLabel("\u4e26\u9078\u64c7\u52a0\u5bc6\u6587\u7ae0\u6216\u8005\u89e3\u5bc6\u6587\u7ae0"+
									"\uff0c\u53f3\u4e0b\u65b9\u5c07\u8f38\u51fa\u52a0\u89e3\u5bc6\u7d50\u679c\u3002");
		label1.setFont(new Font("標楷體", Font.PLAIN, 22));
		label1.setPreferredSize(new Dimension(620, 25));
		informationP.add(label1);
		label2.setFont(new Font("標楷體", Font.PLAIN, 22));
		label2.setPreferredSize(new Dimension(620, 25));
		informationP.add(label2);
		
		JPanel inputP = new JPanel();
		inputP.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Input", TitledBorder.CENTER, TitledBorder.TOP, null, Color.GRAY));
		inputP.setPreferredSize(new Dimension(310, 285));
		frame.getContentPane().add(inputP);		
				
		inputarea.setLineWrap(false);
		
		JScrollPane inscp = new JScrollPane(inputarea);
		inscp.setPreferredSize(new Dimension(290, 255));
		inscp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		inscp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		inputP.add(inscp);	
		
		JPanel outputP = new JPanel();
		outputP.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Output", TitledBorder.CENTER, TitledBorder.TOP, null, Color.GRAY));
		outputP.setPreferredSize(new Dimension(310, 285));
		frame.getContentPane().add(outputP);
		
		outputarea.setEditable(false);
		outputarea.setLineWrap(true);		
		
		JScrollPane outscp = new JScrollPane(outputarea);
		outscp.setPreferredSize(new Dimension(290, 255));
		outscp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		outscp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		outputP.add(outscp);	
		
		JPanel btnpanel = new JPanel();
		btnpanel.setPreferredSize(new Dimension(620, 45));
		frame.getContentPane().add(btnpanel);
		btnpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		actionPerformed action = new actionPerformed();	
		
		incode.addActionListener(action);
		incode.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		incode.setPreferredSize(new Dimension(100, 35));
		btnpanel.add(incode);

		decode.addActionListener(action);
		decode.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		decode.setPreferredSize(new Dimension(100, 35));
		btnpanel.add(decode);
		
	}
	
	static class actionPerformed implements ActionListener{
		public void actionPerformed(ActionEvent e){
            String cmd = e.getActionCommand();
            if(e.getSource() == incode)
            {
            	String s = inputarea.getText();
            	s = s.replaceAll("\n", " ");
            	
            	if(s.isEmpty())
            	{
            		JOptionPane.showMessageDialog(null,  "Please input text!!", "ERROR", JOptionPane.ERROR_MESSAGE);    				
            	}
            	else
            	{
		        	try {
						incode(s);
					} catch (IOException e1) {
						e1.printStackTrace();
					}            		
            	}
            	

            }
            else if(e.getSource() == decode)
            {
            	String s = inputarea.getText();
            	s = s.replaceAll("\n", " ");
            	
            	if(s.isEmpty())
            	{
            		JOptionPane.showMessageDialog(null,  "Please input text!!", "ERROR", JOptionPane.ERROR_MESSAGE);    				
            	}
            	else
            	{
		        	try {
						decode(s);
					} catch (IOException e1) {
						e1.printStackTrace();
					}            		
            	}
            }
            else
            	;
		}
	}
	
	public static void incode(String s) throws IOException
	{

		while(s.length()%25!=0)
		{
			s += " ";//pending space
		}
		
		LinkedList<matrixClass> l = new LinkedList<matrixClass>();
		for(int i=0; i < s.length()/25;i++)
		{
		   matrixClass mc = new matrixClass();
		   String subs = s.substring(i*25, i*25+25);
		   mc.ascAdd1(subs);//ascii + 1
		   l.add(mc);
		}
		
		for(int i=0; i<l.size();i++)
		{
			l.get(i).trans();//transpose
			//l.get(i).print();
		}
		
		String str = "";
		int[][] get = new int[5][5];
		char[] output = new char[25];
		
		for(int j = l.size()-1 ; j >= 0 ; j--)//block reverse output
		{
			get = l.get(j).getm();
			
			for(int i = 0 ; i < 5 ; i++)
			{
				for(int k = 0 ; k < 5 ; k++)
				{
					output[i*5+k]=(char) ((get[i][k]+253)%95+32);
				}
			}
			str += String.copyValueOf(output);
		}
		
		outputarea.setText(str);
		//System.out.println(str);
	}
	
	public static void decode(String s) throws IOException
	{
		
		LinkedList<matrixClass> l = new LinkedList<matrixClass>();
		for(int i=0; i<s.length()/25;i++)
		{
			matrixClass mc = new matrixClass();
		   String subs = s.substring(i*25, i*25+25);
		   mc.set(subs);//input block
		   l.add(mc);
		}
		
		for(int i=0; i<l.size();i++)
		{
			l.get(i).trans();//transpose
			//l.get(i).print();
		}

		String str = "";
		int[][] get = new int[5][5];
		char[] output = new char[25];
		
		for(int j = l.size()-1 ; j >= 0  ; j--)
		{			
			get = l.get(j).getm();
			for(int i = 0 ; i < 5 ; i++)
			{
				for(int k = 0 ; k < 5 ; k++)
				{
					output[i*5+k]=(char) ((get[i][k]+252)%95+32);//ascii-1
				}
			}
			str += String.copyValueOf(output);
		}
		
		//System.out.println(str);
		outputarea.setText(str);
		
	}
	
	
}
