import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

import javax.swing.*;

public class GUI {

	public static JButton search = new JButton();
	public static JButton reset = new JButton();
	public static JSpinner numSpinner = new JSpinner();
	public static JTextField getkey = new JTextField();
	public static JComboBox getCat = new JComboBox();
	public static JComboBox pageComboBox = new JComboBox();
	public static JList list = new JList();
	public static DefaultListModel model = new DefaultListModel();
	public static ArrayList<String> detail = new ArrayList<String>();
	
	public static void main(String[] args) {
	
		try {
			
			File f = new File("response.txt");
			FileWriter fw = new FileWriter(f);
			fw.write("");
			fw.close();		
			
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		GUI frame = new GUI();
		detail.clear();
		

	}
	
	public GUI()
	{
		
		JFrame frame = new JFrame("比價系統");
		frame.setBounds(100, 100, 535, 385);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		Panel controlPanel = new Panel();
		controlPanel.setBounds(10, 10, 500, 80);
		frame.getContentPane().add(controlPanel);
		controlPanel.setLayout(null);
		
		JLabel keyword = new JLabel("關鍵字：");
		keyword.setBounds(10, 10, 60, 25);
		controlPanel.add(keyword);
		
		getkey = new JTextField();
		getkey.setBounds(75, 10, 410, 25);
		controlPanel.add(getkey);
		
		numSpinner = new JSpinner();
		numSpinner.setBounds(80, 45, 60, 25);
		controlPanel.add(numSpinner);
		numSpinner.setModel(new SpinnerNumberModel(new Integer(50), new Integer(1), null, new Integer(1)));
		
		JLabel num = new JLabel("搜尋筆數：");
		num.setBounds(15, 45, 70, 25);
		controlPanel.add(num);
		
		
		JLabel category = new JLabel("搜尋類別：");
		category.setBounds(155, 45, 70, 25);
		controlPanel.add(category);
		
		getCat = new JComboBox();
		getCat.setModel(new DefaultComboBoxModel(new String[] {"圖書", "全館 ", "影音", "雜誌", "百貨"}));
		getCat.setMaximumRowCount(6);
		getCat.setBounds(220, 45, 60, 25);
		controlPanel.add(getCat);
				
		JPanel p1 = new JPanel();
		p1.setBackground(Color.CYAN);
		p1.setBounds(10, 100, 500, 230);
		
		actionPerformed action = new actionPerformed();
		
		JLabel page = new JLabel("目前頁數：");
		p1.add(page);
		
		pageComboBox = new JComboBox();	
		pageComboBox.setDefaultLocale(null);
		pageComboBox.setMaximumRowCount(10);
		pageComboBox.addActionListener(action);
		p1.add(pageComboBox);		
		
		
		list = new JList(model);
		list.setVisibleRowCount(12);
		list.setFont(new Font("標楷體",Font.BOLD,16));
		
		JScrollPane jp = new JScrollPane(list);
		jp.setPreferredSize(new Dimension(490, 190));
		jp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		p1.add(jp);
		frame.getContentPane().add(p1);
		
		reset = new JButton("RESSET");
		reset.setEnabled(false);
		reset.setBounds(390, 45, 90, 25);
		reset.addActionListener(action);
		controlPanel.add(reset);
		
		
		search = new JButton("SEARCH");
		search.setBounds(290, 45, 90, 25);
		controlPanel.add(search);
		search.addActionListener(action);
		
		
		frame.setVisible(true);	
		
	}
	
	public static String category(int c)
	{
		if(c == 0)
			return "BKA";
		else if(c == 4)
			return "5";
		else
			return Integer.toString(c);
	}
	
	static class actionPerformed implements ActionListener{
		public void actionPerformed(ActionEvent e){
            String cmd = e.getActionCommand();
            
            if(e.getSource() == search)
            {
				String key = getkey.getText();
				if(key.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "請輸入關鍵字", "Input Error", JOptionPane.WARNING_MESSAGE);
					key = getkey.getText();
				}
				else
				{
					
					String cat = category(getCat.getSelectedIndex());
					int num = (Integer)numSpinner.getValue();	
					
					
					try {	
						
						url Myurl = new url(key, cat, num);
						
						if(num < 20)
							Myurl.parser(key, cat, 1);	
						else
						{
							for(int i = 1 ; i <= (num/20 + 1) ; i++)
							{
								Myurl.parser(key, cat, i);
							}
							
						}

						if(num > 12)
						{
							for(int i = 1 ; i <= (num/12+1) ; i++)
							{
								pageComboBox.addItem(Integer.toString(i)); 	
							}							
						}
						else
							pageComboBox.addItem("1"); 

						
						detail.clear();
						Myurl.analysis();	
						show(num);
						
					} catch (IOException ee) {ee.printStackTrace();}
					
					search.setEnabled(false);
					reset.setEnabled(true);
					
				}			
			
            }
            else if(e.getSource() == reset)
            {
            	getkey.setText("");
            	numSpinner.setModel(new SpinnerNumberModel(new Integer(50), new Integer(1), null, new Integer(1)));
            	getCat.setSelectedItem("圖書");
            	
            	DefaultListModel listModel = (DefaultListModel) list.getModel();
                listModel.removeAllElements();                
                DefaultComboBoxModel d = (DefaultComboBoxModel)pageComboBox.getModel();
                d.removeAllElements();
                pageComboBox.setSelectedIndex(-1);
            	
        		try {
        			
        			File f = new File("response.txt");
        			FileWriter fw = new FileWriter(f);
        			fw.write("");
        			fw.close();	
        			
        			File ff = new File("data.txt");
        			FileWriter fw2 = new FileWriter(ff);
        			fw2.write("");
        			fw2.close();
        			
        		} catch (IOException ee) {			
        			ee.printStackTrace();
        		}
            	
				search.setEnabled(true);
				reset.setEnabled(false);
            	
            }
            else if(e.getSource() == pageComboBox)
            {
            	
            	String qq = new String();
            	try{
            		
            		qq = Objects.toString(pageComboBox.getSelectedItem().toString());
            	
            	}catch(Exception g){ qq="0"; }
            	
            	if(!qq.equals(null))
            	{
		           	int num = (Integer)numSpinner.getValue();
	            	int s = Integer.parseInt(qq);
	            	int more = num % 12;
	            	if(s != 1)
	            	{
		            	if((more == 0) && (s != 1))
		            	{
		            		model.clear();
			    		    for (int i = 0; i < 12; i++)
			    		    	model.add(i, detail.get(i+(s-1)*12));            		
		            	}
		            	else
		            	{
		            		
		            		if(s <= (int)num/12)
		            		{
		            			try{
		            				
		            				model.clear();
		            				for (int i = 0; i < 12; i++)
		            					model.add(i, detail.get(i+(s-1)*12));
		            				
		    	    		    }catch(Exception w){ s = Integer.parseInt(qq); }
		            		}
		            		else
		            		{
		            			model.clear();
		    	    		    for (int i = 0; i < more; i++)
		    	    		    	model.add(i, detail.get(i+(s-1)*12));   
		            		}
		            	}
		            }
	            	else if(qq.equals("1"))
	            	{
	            		try{
	            		model.clear();
	        		    for (int i = 0; i < 12; i++)
	        		    	model.add(i, detail.get(i));	
	            		}catch(Exception w)
	            		{
	            			qq = "1";
	            		}
	            	}
		        }
		            
		     }            		
        }
 
	}
	
	@SuppressWarnings("unchecked")
	public static void show(int num) throws IOException
	{
		BufferedReader br = new BufferedReader (new InputStreamReader(new FileInputStream("data.txt"), "utf-8"));
		String seperate = "";
		
		seperate = br.readLine();
		while(seperate != null)
		{
			
			if(seperate.equals("."))
			{
				String temp = br.readLine();
				String item = "";
				boolean control = true;
				while(control)
				{
					String s = br.readLine();
					if(s.contains("元"))
					{
						
						item += (s + "   \t");
						break;
						
					}
					else
						item += (s + "   \t");
				}
				detail.add(item);
				
			}
			
			seperate = br.readLine();
		}
		
		if( num <= 12)
		{
		    for (int i = 0; i < num; i++)
		    	model.add(i, detail.get(i));			
		}
		else
		{
		    for (int i = 0; i < 12; i++)
		    	model.add(i, detail.get(i));	
		}	
		
		br.close();
		
	}
}
