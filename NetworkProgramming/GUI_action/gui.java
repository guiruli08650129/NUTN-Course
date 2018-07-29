import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.*;
import java.io.*;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.ScrollPaneConstants;



public class gui {

	public static JFrame frame;
	public static JTextField getName;
	public static JTextField getID;
	public static JButton add = new JButton("ADD");		
	public static JButton modify = new JButton("MODIFY");
	public static JButton search = new JButton("SEARCH");
	public static JButton delete = new JButton("DELETE");
	public static JButton clear = new JButton("CLEAR");	
	public static JButton reinput = new JButton("REINPUT");		
	public static String choosefood = null;
	public static String choosesport = null;
	public static JRadioButton pasta = new JRadioButton("pasta");	
	public static JRadioButton potato = new JRadioButton("potato");	
	public static JRadioButton friedchicken = new JRadioButton("fried chicken");	
	public static JRadioButton pizza = new JRadioButton("pizza");
	public static JRadioButton table_tennis = new JRadioButton("table_tennis");	
	public static JRadioButton basketball = new JRadioButton("basketball");
	public static JRadioButton baseball = new JRadioButton("baseball");	
	public static JRadioButton football = new JRadioButton("football");
	public static ButtonGroup bg1 = new ButtonGroup();
	public static ButtonGroup bg2 = new ButtonGroup();
	private final JPanel panel_1 = new JPanel();
	public static JTextArea t1 = new JTextArea();




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


	public gui() throws IOException {
		initialize();
	}


	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("icon.jpg"));
		frame.setTitle("GUI Action");
		frame.setBounds(100, 100, 540, 400);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		frame.addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent e) {
	          int result=JOptionPane.showConfirmDialog(frame,"Do you want to quit?", "Quit", 
	        		  JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
	          
	          if (result==JOptionPane.YES_OPTION) 
	          {
				  try 
				  {
					  
					  BufferedWriter fileOut = new BufferedWriter(new FileWriter("data.dat"));
					  t1.write(fileOut);
						
				  } 
				  catch (IOException e1)
				  {
					  e1.printStackTrace();
				  }
	        	  
	        	  System.exit(0);
	          }
	        }   
	    });
		
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 540, 400);
		frame.getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {102, 102, 102, 102, 102};
		gbl_panel.rowHeights = new int[] {30, 60, 60};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		JLabel Name = new JLabel("Name\uFF1A");
		Name.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_Name = new GridBagConstraints();
		gbc_Name.fill = GridBagConstraints.BOTH;
		gbc_Name.insets = new Insets(0, 0, 5, 5);
		gbc_Name.gridx = 0;
		gbc_Name.gridy = 0;
		panel.add(Name, gbc_Name);		
		getName = new JTextField();
		getName.setColumns(10);
		GridBagConstraints gbc_getName = new GridBagConstraints();
		gbc_getName.fill = GridBagConstraints.BOTH;
		gbc_getName.insets = new Insets(0, 0, 5, 5);
		gbc_getName.gridx = 1;
		gbc_getName.gridy = 0;
		panel.add(getName, gbc_getName);
		JLabel StudentID = new JLabel("StudentID\uFF1A");
		StudentID.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_StudentID = new GridBagConstraints();
		gbc_StudentID.fill = GridBagConstraints.BOTH;
		gbc_StudentID.insets = new Insets(0, 0, 5, 5);
		gbc_StudentID.gridx = 2;
		gbc_StudentID.gridy = 0;
		panel.add(StudentID, gbc_StudentID);
		getID = new JTextField(9);
		getID.setColumns(9);
		GridBagConstraints gbc_getID = new GridBagConstraints();
		gbc_getID.fill = GridBagConstraints.BOTH;
		gbc_getID.insets = new Insets(0, 0, 5, 5);
		gbc_getID.gridx = 3;
		gbc_getID.gridy = 0;
		panel.add(getID, gbc_getID);
		
		JLabel sport = new JLabel("Favorite Sport\uFF1A");
		GridBagConstraints gbc_sport = new GridBagConstraints();
		gbc_sport.fill = GridBagConstraints.BOTH;
		gbc_sport.insets = new Insets(0, 0, 5, 5);
		gbc_sport.gridx = 0;
		gbc_sport.gridy = 1;
		panel.add(sport, gbc_sport);
		GridBagConstraints gbc_basketball = new GridBagConstraints();
		gbc_basketball.fill = GridBagConstraints.BOTH;
		gbc_basketball.insets = new Insets(0, 0, 5, 5);
		gbc_basketball.gridx = 1;
		gbc_basketball.gridy = 1;
		panel.add(basketball, gbc_basketball);
		bg1.add(basketball);
		GridBagConstraints gbc_baseball = new GridBagConstraints();
		gbc_baseball.fill = GridBagConstraints.BOTH;
		gbc_baseball.insets = new Insets(0, 0, 5, 5);
		gbc_baseball.gridx = 2;
		gbc_baseball.gridy = 1;
		panel.add(baseball, gbc_baseball);
		bg1.add(baseball);
		GridBagConstraints gbc_football = new GridBagConstraints();
		gbc_football.fill = GridBagConstraints.BOTH;
		gbc_football.insets = new Insets(0, 0, 5, 5);
		gbc_football.gridx = 3;
		gbc_football.gridy = 1;
		panel.add(football, gbc_football);
		bg1.add(football);
		GridBagConstraints gbc_table_tennis = new GridBagConstraints();
		gbc_table_tennis.fill = GridBagConstraints.BOTH;
		gbc_table_tennis.insets = new Insets(0, 0, 5, 0);
		gbc_table_tennis.gridx = 4;
		gbc_table_tennis.gridy = 1;
		panel.add(table_tennis, gbc_table_tennis);
		
		bg1.add(table_tennis);
		
		
		JLabel food = new JLabel("Favorite Food\uFF1A");
		GridBagConstraints gbc_food = new GridBagConstraints();
		gbc_food.fill = GridBagConstraints.BOTH;
		gbc_food.insets = new Insets(0, 0, 0, 5);
		gbc_food.gridx = 0;
		gbc_food.gridy = 2;
		panel.add(food, gbc_food);
		GridBagConstraints gbc_pasta = new GridBagConstraints();
		gbc_pasta.fill = GridBagConstraints.BOTH;
		gbc_pasta.insets = new Insets(0, 0, 0, 5);
		gbc_pasta.gridx = 1;
		gbc_pasta.gridy = 2;
		panel.add(pasta, gbc_pasta);
		bg2.add(pasta);
		GridBagConstraints gbc_potato = new GridBagConstraints();
		gbc_potato.fill = GridBagConstraints.BOTH;
		gbc_potato.insets = new Insets(0, 0, 0, 5);
		gbc_potato.gridx = 2;
		gbc_potato.gridy = 2;
		panel.add(potato, gbc_potato);
		bg2.add(potato);
		GridBagConstraints gbc_friedchicken = new GridBagConstraints();
		gbc_friedchicken.fill = GridBagConstraints.BOTH;
		gbc_friedchicken.insets = new Insets(0, 0, 0, 5);
		gbc_friedchicken.gridx = 3;
		gbc_friedchicken.gridy = 2;
		panel.add(friedchicken, gbc_friedchicken);
		bg2.add(friedchicken);
		GridBagConstraints gbc_pizza = new GridBagConstraints();
		gbc_pizza.fill = GridBagConstraints.BOTH;
		gbc_pizza.gridx = 4;
		gbc_pizza.gridy = 2;
		panel.add(pizza, gbc_pizza);
		
		bg2.add(pizza);
		
		
		JPanel controlPane = new JPanel();
		actionPerformed action = new actionPerformed();
		
		frame.getContentPane().add(controlPane);
		delete.addActionListener(action);		
		search.addActionListener(action);
		modify.addActionListener(action);
		add.addActionListener(action);
		reinput.addActionListener(action);
		clear.setSelectedIcon(null);
		clear.addActionListener(action);
		controlPane.setLayout(new BoxLayout(controlPane, BoxLayout.X_AXIS));
		controlPane.add(add);
		controlPane.add(modify);
		controlPane.add(search);
		controlPane.add(delete);		
		controlPane.add(reinput);		
		controlPane.add(clear);
		
		
		//t1.setRows(7);
		//t1.setColumns(55);
		
		
        BufferedReader reader = new BufferedReader(new FileReader("data.dat"));
        String line;
        while ((line = reader.readLine()) != null) {
          t1.append(line + "\n");
        }
        reader.close();
		t1.setEditable(false);
		
        
		t1.setFont(new Font("º–∑¢≈È",Font.BOLD,16));
		t1.setLineWrap(false);
		t1.setWrapStyleWord(true);
		
		JScrollPane scrollPane = new JScrollPane(t1);
		scrollPane.setPreferredSize(new Dimension(490, 160));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_1.add(scrollPane);
		frame.getContentPane().add(panel_1);
	}
	
	static class actionPerformed implements ActionListener{
		public void actionPerformed(ActionEvent e){
            String cmd = e.getActionCommand();
            
            if(e.getSource() == add)
            {

            	get_select();
            	String sname = getName.getText();
            	String sid = setLength(getID.getText()); 
            	boolean dup = duplicate(sname, sid);
            	
            	if((sname == null) || (sid == null))
            	{
            		JOptionPane.showMessageDialog(null, "Empty name or student ID!!", "Input Error", JOptionPane.WARNING_MESSAGE );
            	}
            	
            	else if(sid.length() < 8)
            	{
        			JOptionPane.showMessageDialog(null, "Length of studentID is 9!!", "Input Length Error", JOptionPane.WARNING_MESSAGE );
            	}
            	else if(dup == true){
            		JOptionPane.showMessageDialog(null, "Data already exists!!", "Duplicate Error", JOptionPane.WARNING_MESSAGE );
        			getName.setText("");
        			getID.setText("");
        			bg1.clearSelection();
        			bg2.clearSelection();	
            	
            	}	 
            	else if((dup == false) && (sid != null) && (sid.length() == 9) && (choosesport != null) && (choosefood != null)){
            		t1.append(sname + "\t" + sid + "\t" + choosesport +"\t" + choosefood +"\n");
            	}   
            	else
            		;
            	
    			getName.setText("");
    			getID.setText("");
    			bg1.clearSelection();
    			bg2.clearSelection();	
            	
            }
            else if(e.getSource() == modify)
            {
               	String name = getName.getText();
            	String id = getID.getText();
            	LinkedList<data> d = new LinkedList<data>();
            	String[] s = t1.getText().trim().split("\n");
            	
            	
            	if(name.trim().equalsIgnoreCase("") || id.trim().equalsIgnoreCase(""))
            	{
            		JOptionPane.showMessageDialog(null, "Please use SEARCH to select data first!!", "Modify Error", JOptionPane.WARNING_MESSAGE );   
            	}
            	else
            	{
            	
	            	for(int i = 0 ; i < s.length ; i++)
	            	{
	            		d.add(new data(s[i]));
	            	}
	            	
	    			int result=JOptionPane.showConfirmDialog(frame, "Are you sure to modify this data?", "Modify Data", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE );
	    			if (result==JOptionPane.YES_OPTION)
	    			{
		            	for(int j = 0 ; j < s.length ; j++)
		            	{
		            		
		            		if(d.get(j).getName().equals(name) || d.get(j).getID().equals(id))
		            		{
		            			get_select();
		            			d.get(j).setName(name);
		            			d.get(j).setID(id);
		            			d.get(j).setSport(choosesport);
		            			d.get(j).setFood(choosefood);
		            		}
		            		
		            	}
		            	
	        			getName.setText("");
	        			getID.setText("");
	        			bg1.clearSelection();
	        			bg2.clearSelection();
		            	t1.setText("");
		            	
		            	for(int k = 0 ; k < s.length ; k++)
		            	{
		            		t1.append(d.get(k).dump() + "\n");
		            	}  
		            	
		            	d.clear();
		            	
	    			}
            	}
            }
            else if(e.getSource() == search)
            {
            	String sname = getName.getText();
            	String sid = setLength(getID.getText()); 
            	
        		String st = "";
        		boolean found = false;
        		st = t1.getText();
        		String[] sString = t1.getText().split("\n");
        		
            	if(sname.trim().equals("") && sid.trim().equals(""))
            	{
            		JOptionPane.showMessageDialog(null, "Please enter name or student ID!!", "Search Error", JOptionPane.WARNING_MESSAGE );   
            	}
            	else
            	{
	        		for(int i = 0 ; i < sString.length ; i++)
	        		{
	        			AbstractButton ab = new JRadioButton();
	        			ButtonModel m1 = ab.getModel();
	        			ButtonModel m2 = ab.getModel();
	        			st = sString[i].trim();
	        			String[] temp = st.split("\t");		
	        			
	        			if(temp[0].equals(sname) || (temp[1].equals(sid)))
	        			{
	                    	getName.setText(temp[0]);
	                		getID.setText(temp[1]);
	        				
	        				if(temp[2].equals(table_tennis.getText())) 
	        					m1 = table_tennis.getModel();
	        				else if(temp[2].equals(baseball.getText()))
	        					m1 = baseball.getModel();
	        				else if(temp[2].equals(basketball.getText()))
	        					m1 = basketball.getModel();
	        				else if(temp[2].equals(football.getText()))
	        					m1 = football.getModel();
	        				else
	        					;
	        				bg1.setSelected(m1, true);
	        				
	        				if(temp[3].equals(potato.getText())) 
	        					m2 = potato.getModel();
	        				else if(temp[3].equals(pizza.getText()))
	        					m2 = pizza.getModel();
	        				else if(temp[3].equals(pasta.getText()))
	        					m2 = pasta.getModel();
	        				else if(temp[3].equals(friedchicken.getText()))
	        					m2 = friedchicken.getModel();
	        				else
	        					;
	        				bg2.setSelected(m2, true);
	        				found = true;
	        				break;
	        			}	
	        		}
            	}
        		
        		if(!found)
        		{
        			getName.setText("");
        			getID.setText("");
        			bg1.clearSelection();
        			bg2.clearSelection();
        			JOptionPane.showMessageDialog(null, "This data doesn't exist!!", "Data No Found", JOptionPane.WARNING_MESSAGE );
        			
        		}

        	}
           
            else if(e.getSource() == delete)
            {
            	String name = getName.getText().trim();
            	String id = getID.getText().trim();
            	LinkedList<String> d = new LinkedList<String>();
            	String[] s = t1.getText().trim().split("\n");
            	
            	if(name.trim().equalsIgnoreCase("") || id.trim().equalsIgnoreCase(""))
            	{
            		JOptionPane.showMessageDialog(null, "Please use SEARCH to select data first!!", "Dlete Error", JOptionPane.WARNING_MESSAGE );   
            	}
            	else
            	{
		           	for(int i = 0 ; i < s.length ; i++)
	            	{
	            		d.add(s[i]);
	            	}
			          
	    			int result=JOptionPane.showConfirmDialog(frame, "Are you sure to delete the data?", "Delete Data", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE );
	    			if (result==JOptionPane.YES_OPTION) 
	    			{
		            	for(int j = 0 ; j < d.size() ; j++)
		            	{
		            		String[] words = d.get(j).split("\t");
		            		if(words[0].equals(name) && words[1].equals(id))
		            			d.remove(j);
		            	}
		            	
	        			getName.setText("");
	        			getID.setText("");
	        			bg1.clearSelection();
	        			bg2.clearSelection();
		            	t1.setText("");
		            	
		            	for(int k = 0 ; k < d.size() ; k++)
		            	{
		            		t1.append(d.get(k) + "\n");
		            	}    				
	    			}            		
            	}    			
            }
            else if(e.getSource() == reinput)
            {
    			int result=JOptionPane.showConfirmDialog(frame, "Are you sure to reinput the data?", "Reinput Data", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE );
    			if (result==JOptionPane.YES_OPTION) 
    			{
	    			getName.setText("");
	    			getID.setText("");
	    			bg1.clearSelection();
	    			bg2.clearSelection();
    			}
            }
            else if(e.getSource() == clear)
            {
    			int result=JOptionPane.showConfirmDialog(frame, "Are you sure to clean all data?", "Clear Data", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE );
    			if (result==JOptionPane.YES_OPTION) 
    			{	    			
	    			try {
	    				
						BufferedWriter clear = new BufferedWriter(new FileWriter("data.dat"));
						clear.close();
					
		    			getName.setText("");
		    			getID.setText("");
		    			bg1.clearSelection();
		    			bg2.clearSelection();
		    			t1.setText("");	    			
	    			
	    			} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    			
    			}
            	
            }
            
        }
		
	}
	
	public static boolean duplicate(String name, String id)
	{
		String st = "";
		boolean control = false;

		st = t1.getText();
		String[] sString = t1.getText().split("\n");
		
		for(int i = 0 ; i < sString.length ; i++)
		{
			
			st = sString[i].trim();
			control = loop2(st, name, id);	
			if(control == true)
				break;
		}
		
		return control;
	}
	
	public static boolean loop2(String st, String name, String id) {
		
		
		boolean control = true;
		String[] temp = st.split("\t");		
		
		if(!temp[0].equals(name) && !(temp[1].equals(id)))
		{
			control = false;
		}
		
		return control;
	}
		
	public static void get_select() 
    {

        if(table_tennis.isSelected())
        	choosesport = table_tennis.getText().trim();
        else if(baseball.isSelected())
        	choosesport = baseball.getText().trim();
        else if(basketball.isSelected())
        	choosesport = basketball.getText().trim();
        else if(football.isSelected())
        	choosesport = football.getText().trim();
        else
        {
        	JOptionPane.showMessageDialog(null, "Please choose your favorite sport!!", "Choose Error", JOptionPane.WARNING_MESSAGE );
        	choosesport = null;
        }
        if(pasta.isSelected())
        	choosefood = pasta.getText().trim();
        else if(potato.isSelected())
        	choosefood = potato.getText().trim();
        else if(pizza.isSelected())
        	choosefood = pizza.getText().trim();
        else if (friedchicken.isSelected())
        	choosefood = friedchicken.getText().trim();
        else 
        {
        	JOptionPane.showMessageDialog(null, "Please choose your favorite food!!", "Choose Error", JOptionPane.WARNING_MESSAGE );   
        	choosefood = null;
        }
    }

	public static String setLength(String s)
	{
		String temp = null;
		if(s.length() > 8)
			temp = s.substring(0, 9);
		else 
			;
		
		return temp;
		
	}
	
}
