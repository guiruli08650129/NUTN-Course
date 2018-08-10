import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.util.LinkedList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.*;
import javax.swing.text.DefaultCaret;
import javax.swing.tree.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class gui {

	public static ftp3 ftp;
	
	private JFrame frame;
	private static FileSystemModel fileSystemModel;
	private String localFolder = "c:\\";
	private static JTree localtree;
	private DefaultTreeModel localtreeModel;	
	private static Path remoteFolder = Paths.get("C://Program Files (x86)//FileZilla Server//kay");
	private static JTree remotetree;
	private static DefaultTreeModel remotetreeModel;
	public static JTextArea cmdTextArea = new JTextArea();
	public static JTextField remotePath;
	public static JTextField localPath;
	public static LinkedList<String> loginS;
	public static String choo;
	
	/*contorl button*/
	private static JButton put;
	private static JButton get;
	private static JButton list;
	private static JButton delete;
	private static JRadioButton ascii;
	private static JRadioButton binary;
	private static JButton mkd;
	private static JButton rmd;
	private static JButton pwd;
	private static JButton nlist;
	private static JButton quit;
	
	/*login*/
	private static JTextField gethost;
	private static JTextField username;
	private static JTextField password;
	private static String host;
	public static String user;
	public static String key;
	public static boolean control = false;
	
	
	public static void Login()
	{
		gethost = new JTextField();
		username = new JTextField();
		password = new JPasswordField();
		Object[] message = { "Host：", gethost, "Username:", username, "Password:", password };

		int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) 
		{
			host = gethost.getText();
			user = username.getText();
			key = password.getText();	        	 
		}	
		else
			System.exit(1);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Login();
					ftp = new ftp3();

					ftp.openConnection(host);					
					ftp.getMsgs();
					ftp.doLogin(user, key);
					
					int opt = JOptionPane.showConfirmDialog(null, "Ready to log in?", "Ready", JOptionPane.YES_NO_OPTION);
					if(opt == JOptionPane.YES_OPTION)
					{
						if(control)
						{
							JOptionPane.showMessageDialog(null, "SUCCESS", "Login", JOptionPane.INFORMATION_MESSAGE);
						
							gui window = new gui();
							window.frame.setVisible(true);
							
						}
						else
						{
							JOptionPane.showMessageDialog(null, "FAILED! System Exit!", "Login", JOptionPane.ERROR_MESSAGE);
							ftp.closeConnection();
						}						
					}
					else
					{
						JOptionPane.showMessageDialog(null,  "Bye Bye!", "Exit", JOptionPane.WARNING_MESSAGE);
						ftp.closeConnection();
					}

					
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(0);
				}
			}
		});
	}

	public gui() {
		initialize();
	}

	private void initialize() {
		
		frame = new JFrame("FTP Client");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png"));
		frame.setBounds(100, 100, 800, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(3, 3));
		
		JPanel gui = new JPanel(new BorderLayout(3, 3));
		gui.setBorder(new EmptyBorder(5,5,5,5));
		frame.getContentPane().add(gui);
		
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BorderLayout(5, 0));
		controlPanel.setPreferredSize(new Dimension(580, 70));
		controlPanel.setBackground(SystemColor.controlHighlight);
		gui.add(controlPanel, BorderLayout.NORTH);
		
		JPanel btnPanel = new JPanel();
		btnPanel.setBackground(new Color(255, 200, 100));
		controlPanel.add(btnPanel, BorderLayout.NORTH);
		
		actionPerformed action = new actionPerformed();
		
		put = new JButton("PUT");
		put.addActionListener(action);
		btnPanel.add(put);
		get = new JButton("GET");
		get.addActionListener(action);
		btnPanel.add(get);
		list = new JButton("LIST");
		list.addActionListener(action);
		btnPanel.add(list);		
		delete = new JButton("DELETE");
		delete.addActionListener(action);
		btnPanel.add(delete);		
		mkd = new JButton("MKD");
		mkd.addActionListener(action);
		btnPanel.add(mkd);
		rmd = new JButton("RMD");
		rmd.addActionListener(action);
		btnPanel.add(rmd);
		pwd = new JButton("PWD");
		pwd.addActionListener(action);
		btnPanel.add(pwd);
		nlist = new JButton("NLST");
		nlist.addActionListener(action);
		btnPanel.add(nlist);
		quit = new JButton("QUIT");
		quit.addActionListener(action);
		btnPanel.add(quit);
		
		
		

		//local path panel
		JPanel localPathpanel = new JPanel();
		localPathpanel.setLayout(new FlowLayout());
		localPathpanel.setPreferredSize(new Dimension( (int)(frame.getWidth()/2), 40));
		controlPanel.add(localPathpanel);
		JLabel localPathlbl = new JLabel("本地站台");
		localPathlbl.setPreferredSize(new Dimension(70, 25));
		localPathlbl.setFont(new Font("新細明體", Font.PLAIN, 16));
		localPath = new JTextField();
		localPath.setEditable(false);
		localPath.setFont(new Font("新細明體", Font.PLAIN, 16));
		localPath.setPreferredSize(new Dimension(280, 25));
		localPath.setText(localFolder);
		localPathpanel.add(localPathlbl);
		localPathpanel.add(localPath);
		controlPanel.add(localPathpanel, BorderLayout.WEST);
		
		
		
		//remote path panel
		JPanel remotePathpanel = new JPanel();
		remotePathpanel.setLayout(new FlowLayout());
		controlPanel.add(remotePathpanel);
		JLabel remotePathlbl = new JLabel("遠端站台");
		remotePathlbl.setPreferredSize(new Dimension(70, 25));
		remotePathlbl.setFont(new Font("新細明體", Font.PLAIN, 16));
		remotePath = new JTextField();
		remotePath.setEditable(false);
		remotePath.setFont(new Font("新細明體", Font.PLAIN, 16));
		remotePath.setPreferredSize(new Dimension(280, 25));
		remotePath.setText(remoteFolder.toString());
		remotePathpanel.add(remotePathlbl);
		remotePathpanel.add(remotePath);
		controlPanel.add(remotePathpanel, BorderLayout.CENTER);

		
		
		//local tree and detail panel
        DefaultMutableTreeNode localroot = new DefaultMutableTreeNode();
        localtreeModel = new DefaultTreeModel(localroot);
        localtree = new JTree(localtreeModel);
        JTextArea localTextArea = new JTextArea();
        localTextArea.setEditable(false);
	    fileSystemModel = new FileSystemModel(new File(localFolder));
	    localtree = new JTree(fileSystemModel);
	    localtree.setEditable(true);
	    localtree.addTreeSelectionListener(new TreeSelectionListener() {
	      public void valueChanged(TreeSelectionEvent event) {
	    	  Object ob = localtree.getLastSelectedPathComponent();
	        File file = (File)ob;
	        localTextArea.setText(getFileDetails(file));
	        localPath.setText(file.getParent());
	      }
	    });  
        localtree.setRootVisible(false);
        localtree.expandRow(0);
        JScrollPane localtreeScroll = new JScrollPane(localtree);
        localtree.setVisibleRowCount(15);
        Dimension widePreferred = new Dimension( (int)(frame.getWidth()/2), (int)(frame.getHeight()-220)/2);
        localtreeScroll.setPreferredSize( widePreferred );        
        JPanel localdetailView = new JPanel(new BorderLayout(3,3));
        JScrollPane localtableScroll = new JScrollPane(localTextArea);
        Dimension d = localtableScroll.getPreferredSize();
        localtableScroll.setPreferredSize(new Dimension((int)d.getWidth(), (int)d.getHeight()/2));
        localdetailView.add(localtableScroll, BorderLayout.CENTER);        
        JSplitPane local = new JSplitPane(JSplitPane.VERTICAL_SPLIT, localtreeScroll, localtableScroll);
        gui.add(local, BorderLayout.WEST);
        
        
        
        //remote tree and detail panel
        DefaultMutableTreeNode remoteroot = new DefaultMutableTreeNode();
        remotetreeModel = new DefaultTreeModel(remoteroot);
        remotetree = new JTree(remotetreeModel);
        JTextArea remoteTextArea = new JTextArea();
        remoteTextArea.setEditable(false);
	    fileSystemModel = new FileSystemModel(new File(remoteFolder.toString()));
	    remotetree = new JTree(fileSystemModel);
	    remotetree.setEditable(true);
	    remotetree.addTreeSelectionListener(new TreeSelectionListener() {
	      public void valueChanged(TreeSelectionEvent event) {
	    	  Object ob = remotetree.getLastSelectedPathComponent();
	        File file = (File)ob;
	        remoteTextArea.setText(getFileDetails(file));
	        remotePath.setText(file.getParent());
	      }
	    }); 
        remotetree.setRootVisible(false);
        remotetree.expandRow(0);
        JScrollPane remotetreeScroll = new JScrollPane(remotetree);
        remotetree.setVisibleRowCount(15);
        remotetreeScroll.setPreferredSize( widePreferred );        
        JPanel remotedetailView = new JPanel(new BorderLayout(3,3));
        JScrollPane remotetableScroll = new JScrollPane(remoteTextArea);
        Dimension d2 = remotetableScroll.getPreferredSize();
        remotetableScroll.setPreferredSize(new Dimension((int)d2.getWidth(), (int)d2.getHeight()/2));
        remotedetailView.add(remotetableScroll, BorderLayout.CENTER);        
        JSplitPane remote = new JSplitPane(JSplitPane.VERTICAL_SPLIT, remotetreeScroll, remotetableScroll);
        gui.add(remote, BorderLayout.CENTER);
        
        
        //show detail using textArea 
        cmdTextArea = new JTextArea();
        cmdTextArea.setEditable(false);
        cmdTextArea.setFont(new Font("新細明體", Font.PLAIN, 12));
        DefaultCaret caret = (DefaultCaret)cmdTextArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        JScrollPane cmdScroll = new JScrollPane(cmdTextArea);
		JPanel cmdPanel = new JPanel(new BorderLayout(3,3));
		cmdPanel.setPreferredSize(new Dimension(580,180));
		cmdPanel.add(cmdScroll);
		gui.add(cmdPanel, BorderLayout.SOUTH);		
	
	}	
	
	public static String getFileDetails(File file) {		
	    
		if (file == null)
	    	return "";
	    
	    StringBuffer buffer = new StringBuffer();	    
	    buffer.append("Name: " + file.getName() + "\n");
	    buffer.append("Path: " + file.getPath() + "\n");
	    buffer.append("Size(bytes): " + file.length() + "\n");
	    
	    return buffer.toString();
	}
	
	static class actionPerformed implements ActionListener{
		public void actionPerformed(ActionEvent e){
            
            if(e.getSource() == put)
            {
            	boolean cont = true;
            	if(localtree.isSelectionEmpty())
            	{
            		JOptionPane.showMessageDialog(null,  "請在本地端選擇一個檔案!", "PUT", JOptionPane.WARNING_MESSAGE);
    				cont = false;
            	}
            	else
            	{
            		String toRoot = remotePath.getText();
	            	while(!toRoot.equals(remoteFolder.toString()))
	    			{
	            		ftp.doCdup();
	            		int n = toRoot.lastIndexOf("\\");
	            		toRoot = toRoot.substring(0, n);
	    			}

					Object remoteb = remotetree.getLastSelectedPathComponent();
					File remotefile = (File)remoteb;
				    String remoteS = "";
	            	if(((remotetree.isSelectionEmpty()) && (cont)) || ((remotePath.getText().equals(remoteFolder.toString()) && (remotefile.isFile()))))
	            	{
	            		//put in root
		            	String s = remotePath.getText();
		            	while(!s.equals(remoteFolder.toString()))
		    			{
		            		ftp.doCdup();
		            		int n = s.lastIndexOf("\\");
		            		s = s.substring(0, n);
		    			}
		            	
					    Object localb = localtree.getLastSelectedPathComponent();
					    File localfile = (File)localb;		            	
						File file = new File(localfile.getPath());
						if(!file.exists())
						{
							try {
								file.createNewFile();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
	
	        			int opt = JOptionPane.showConfirmDialog(null, "Do you want put "+localfile.getName()+" in "+remoteFolder.toString().substring(remoteFolder.toString().lastIndexOf("\\"), remoteFolder.toString().length())+"?", "PUT", JOptionPane.OK_CANCEL_OPTION);
	        			if(opt == JOptionPane.OK_OPTION)
	        			{
	        				ButtonGroup bg = new ButtonGroup();
	        				ascii = new JRadioButton("ASCII");
	        				bg.add(ascii);
	        				binary = new JRadioButton("BINARY");
	        				bg.add(binary);
	        				binary.setSelected(true);
	        				Object[] message = { "請選擇檔案的傳輸格式: ", ascii, binary};

	        				int option = JOptionPane.showConfirmDialog(null, message, "TYPE", JOptionPane.OK_OPTION);
	        				
		        			if(option == JOptionPane.OK_OPTION)
		        			{
		        				if(ascii.isSelected())
		        					ftp.doPut(file, "A");
		        				else
		        					ftp.doPut(file, "I");
		        			}

		    			    remotetree.updateUI();
	        			} 
	        			
	        			
	            	}
	            	else if(!(remotetree.isSelectionEmpty()) && (cont))
	            	{
						remoteb = remotetree.getLastSelectedPathComponent();
						remotefile = (File)remoteb;
					    remoteS = remotefile.getPath();
					    Object localb = localtree.getLastSelectedPathComponent();
					    File localfile = (File)localb;	
		
						if(!localfile.isFile())
						{
							JOptionPane.showMessageDialog(null,  "請在本地端選擇文字檔案!", "PUT", JOptionPane.WARNING_MESSAGE);
						}
						else if(!remotefile.isDirectory())
						{
							JOptionPane.showMessageDialog(null,  "請在遠端選擇要放入的資料夾!", "PUT", JOptionPane.WARNING_MESSAGE);
						}
						else
						{
						   
							try {
								
								File file = new File(localfile.getPath());
								if(!file.exists())
								{
									file.createNewFile();
								}
								/*
								JFileChooser chooser = new JFileChooser();
							    File f = new File(new File("filename.txt").getCanonicalPath());
							    chooser.setSelectedFile(f);
							    chooser.showOpenDialog(null);
							    File curFile = chooser.getSelectedFile();*/
								
								File curDir = file;
								
							
				            	String s = remoteS;
				            	while(!s.equals(remoteFolder.toString()))
				    			{
				            		ftp.doCdup();
				            		int n = s.lastIndexOf("\\");
				            		s = s.substring(0, n);
				    			}
				            	
				            	String ss = remoteS;
				            	int n = ss.lastIndexOf("\\");
				            	String target = ss.substring(n, ss.length());
				        		int x = remoteFolder.toString().lastIndexOf("\\");
				        		String temp = ss.substring(x, ss.length());   
				        		int x2 = temp.indexOf("\\", 1);
				        		String temp2 = temp.substring(x2, temp.length());
				        		temp2 = temp2.substring(1, temp2.length());
				        		String[] path = temp2.split("\\\\");
			
				            	int i = 0;
				            	while(!path[i].equals(target))
				            	{	            		
				            		ftp.doCd(path[i]);
				            		i++;
				            		if(i == path.length)
				            			break;
				            	}
				            	
			        			int opt = JOptionPane.showConfirmDialog(null, "Do you want put "+localfile.getName()+" in "+remotefile.getName()+"?", "PUT", JOptionPane.OK_CANCEL_OPTION);
			        			if(opt == JOptionPane.OK_OPTION)
			        			{	
			        				ButtonGroup bg = new ButtonGroup();
			        				ascii = new JRadioButton("ASCII");
			        				bg.add(ascii);
			        				binary = new JRadioButton("BINARY");
			        				bg.add(binary);
			        				binary.setSelected(true);
			        				Object[] message = { "請選擇檔案的傳輸格式: ", ascii, binary};

			        				int option = JOptionPane.showConfirmDialog(null, message, "TYPE", JOptionPane.OK_OPTION);
			        				
				        			if(option == JOptionPane.OK_OPTION)
				        			{
				        				if(ascii.isSelected())
				        					ftp.doPut(curDir, "A");
				        				else
				        					ftp.doPut(curDir, "I");
				        			}

				    			    remotetree.updateUI();
			        			}  	            	
			            	
							} catch (Exception e1) {
								e1.printStackTrace();
								System.exit(0);
							}
			            	
						}            		
	            	}
	            	else
	            		;            		
            	}
            }
            else if(e.getSource() == get)
            {			
            	if(remotetree.isSelectionEmpty())
            	{
            		JOptionPane.showMessageDialog(null,  "請在遠端選擇一個要下載的檔案!", "GET", JOptionPane.WARNING_MESSAGE);
    			}
            	else
            	{
					Object remoteb = remotetree.getLastSelectedPathComponent();
				    File remotefile = (File)remoteb;					
					String remoteS = remotefile.getPath();
	            
					if(!remotefile.isFile())
					{
						JOptionPane.showMessageDialog(null,  "請在遠端選擇檔案!", "GET", JOptionPane.WARNING_MESSAGE);
					}
					else
					{
		            	String s = remoteS;
		            	while(!s.equals(remoteFolder.toString()))
		    			{
		            		ftp.doCdup();
		            		int n = s.lastIndexOf("\\");
		            		s = s.substring(0, n);
		    			}
		            	
		            	String ss = remoteS;
		            	int n = ss.lastIndexOf("\\");
		            	String target = ss.substring(n, ss.length());
		        		int x = remoteFolder.toString().lastIndexOf("\\");
		        		String temp = ss.substring(x, ss.length());   
		        		int x2 = temp.indexOf("\\", 1);
		        		String temp2 = temp.substring(x2, temp.length());
		        		temp2 = temp2.substring(1, temp2.length());
		        		String[] path = temp2.split("\\\\");

		        		if(!temp2.equals(remotefile.getName()))
		        		{
			            	int i = 0;
			            	while(!path[i].equals(target))
			            	{	            		
			            		ftp.doCd(path[i]);
			            		i++;
			            		if(i == (path.length-1))
			            			break;
			            	}
		        		}
		            	
	        			int opt = JOptionPane.showConfirmDialog(null, "Do you want get "+remotefile.getName() +"?", "PUT", JOptionPane.OK_CANCEL_OPTION);
	        			if(opt == JOptionPane.OK_OPTION)
	        			{	
	        				ButtonGroup bg = new ButtonGroup();
	        				ascii = new JRadioButton("ASCII");
	        				bg.add(ascii);
	        				binary = new JRadioButton("BINARY");
	        				bg.add(binary);
	        				binary.setSelected(true);
	        				Object[] message = { "請選擇檔案的傳輸格式: ", ascii, binary};

	        				int option = JOptionPane.showConfirmDialog(null, message, "TYPE", JOptionPane.OK_OPTION);
	        				
		        			if(option == JOptionPane.OK_OPTION)
		        			{
		        				if(ascii.isSelected())
		        					ftp.doGet(remotefile.getName(), "A");
		        				else
		        					ftp.doGet(remotefile.getName(), "I");
		        			}
		        			
	        				String tr = remoteFolder.toString().substring(0, remoteFolder.toString().lastIndexOf("\\"));
		                	fileSystemModel = new FileSystemModel(new File(tr));
		    			    remotetree.updateUI();
	        			} 
					}            		
            	}

            }
            else if(e.getSource() == list)
            {
				Object remoteb = remotetree.getLastSelectedPathComponent();
			    File remotefile = (File)remoteb;					
				String s = remotefile.getPath();
            	while(!s.equals(remoteFolder.toString()))
    			{
            		ftp.doCdup();
            		int n = s.lastIndexOf("\\");
            		s = s.substring(0, n);
    			}
            	
            	String ss = remotePath.getText().trim();
        		if(!ss.equals(remoteFolder.toString()))
        		{            	
	            	int n = ss.lastIndexOf("\\");
	            	String target = ss.substring(n, ss.length());
	        		int x = remoteFolder.toString().lastIndexOf("\\");
	        		String temp = ss.substring(x, ss.length());   
	        		int x2 = temp.indexOf("\\", 1);
	        		String temp2 = temp.substring(x2, temp.length());
	        		temp2 = temp2.substring(1, temp2.length());
	        		String[] path = temp2.split("\\\\");


        			int i = 0;
	            	while(!path[i].equals(target))
	            	{           
	            		
	            		ftp.doCd(path[i]);
	            		i++;
	            		if(i == path.length)
	            			break;
	            	}
        		}
            	ftp.doLs();
            	
            }
            else if(e.getSource() == delete)
            {
				Object oob = remotetree.getLastSelectedPathComponent();
			    File file = (File)oob;					
				String s = file.getPath();
				if(!file.isFile())
				{
					JOptionPane.showMessageDialog(null,  "請在遠端選擇要刪除的檔案!", "DELETE", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					String remove = s.substring(s.lastIndexOf("\\")+1, s.length());					
					
                	while(!s.equals(remoteFolder.toString()))
        			{
                		ftp.doCdup();
                		int n = s.lastIndexOf("\\");
                		s = s.substring(0, n);
        			}
                	
                	if(!remotePath.getText().trim().equals(remoteFolder.toString()))
                	{
	                	String ss = file.getPath().substring(0, file.getPath().lastIndexOf("\\"));                	
	                	String target = ss.substring(ss.lastIndexOf("\\"), ss.length());            		
	            		String temp = ss.substring(remoteFolder.toString().lastIndexOf("\\"), ss.length()); 
	            		
	            		int x2 = temp.indexOf("\\", 1);
	            		String temp2 = temp.substring(x2, temp.length());
	            		temp2 = temp2.substring(1, temp2.length());
	            		String[] path = temp2.split("\\\\");
	
	                	int i = 0;
	                	while(!path[i].equals(target))
	                	{           
	                		
	                		ftp.doCd(path[i]);
	                		i++;
	                		if(i == path.length)
	                			break;
	                	} 
                	}
                	
        			int opt = JOptionPane.showConfirmDialog(null, "Do you want to delete "+remove+"?", "DELETE", JOptionPane.OK_CANCEL_OPTION);
        			if(opt == JOptionPane.OK_OPTION)
        			{
	                	ftp.delete(remove);
	                	fileSystemModel = new FileSystemModel(new File(remoteFolder.toString()));
	    			    remotetree.updateUI();
        			}                	
				}
				
            }
            else if(e.getSource() == mkd)
            {
        		JTextField newpath = new JTextField();
        		Object[] ob = {"請輸入新資料夾名稱:", newpath};
    			int opt = JOptionPane.showConfirmDialog(null, ob, "MKD", JOptionPane.OK_CANCEL_OPTION);
    			if(opt == JOptionPane.OK_OPTION)
    			{
    				if(!remotetree.isSelectionEmpty())
    				{
	    				Object oob = remotetree.getLastSelectedPathComponent();
	    			    File file = (File)oob;	
	    				
	    			    if(!remotePath.getText().equals(remoteFolder.toString()))
	    			    {
		    				String s = file.getPath();
		    				if(file.isFile())
		    				{
		    					s = s.substring(0, s.lastIndexOf("\\"));
		    				}
		
		                	while(!s.equals(remoteFolder.toString()))
		        			{
		                		ftp.doCdup();
		                		int n = s.lastIndexOf("\\");
		                		s = s.substring(0, n);
		        			}
	    			    }
	                	
	                	String ss = file.getPath();                	
	                	String target = ss.substring(ss.lastIndexOf("\\"), ss.length());            		
	            		String temp = ss.substring(remoteFolder.toString().lastIndexOf("\\"), ss.length()); 
	            		            		
	            		int x2 = temp.indexOf("\\", 1);
	            		String temp2 = temp.substring(x2, temp.length());
	            		temp2 = temp2.substring(1, temp2.length());
	            		String[] path = temp2.split("\\\\");
	
	                	int i = 0;
	                	while(!path[i].equals(target))
	                	{           
	                		
	                		ftp.doCd(path[i]);
	                		i++;
	                		if(i == path.length)
	                			break;
	                	}
    				}
    				
    				ftp.doMkd(newpath.getText().trim());   				    				
    			    fileSystemModel = new FileSystemModel(new File(remoteFolder.toString()));
    			    remotetree.updateUI();
    			    
    			}
    			
            }
            else if(e.getSource() == rmd)
            {
				Object oob = remotetree.getLastSelectedPathComponent();
			    File file = (File)oob;					
				String s = file.getPath();
				if(file.isFile())
				{
					JOptionPane.showMessageDialog(null,  "請在遠端選擇要刪除的資料夾!", "DELETE", JOptionPane.WARNING_MESSAGE);
				}
				else
				{
					String remove = s.substring(s.lastIndexOf("\\")+1, s.length());					
					
                	while(!s.equals(remoteFolder.toString()))
        			{
                		ftp.doCdup();
                		int n = s.lastIndexOf("\\");
                		s = s.substring(0, n);
        			}
                	
                	if(!remotePath.getText().equals(remoteFolder.toString()))
                	{
	                	String ss = file.getPath().substring(0, file.getPath().lastIndexOf("\\"));  
	                	String target = ss.substring(ss.lastIndexOf("\\"), ss.length());            		
	            		String temp = ss.substring(remoteFolder.toString().lastIndexOf("\\"), ss.length()); 
	            		
	            		int x2 = temp.indexOf("\\", 1);
	            		String temp2 = temp.substring(x2, temp.length());
	            		temp2 = temp2.substring(1, temp2.length());
	            		String[] path = temp2.split("\\\\");
	
	                	int i = 0;
	                	while(!path[i].equals(target))
	                	{           
	                		
	                		ftp.doCd(path[i]);
	                		i++;
	                		if(i == path.length)
	                			break;
	                	}     
                	}
                	
        			int opt = JOptionPane.showConfirmDialog(null, "Do you want to delete "+remove+"?", "DELETE", JOptionPane.OK_CANCEL_OPTION);
        			if(opt == JOptionPane.OK_OPTION)
        			{
	                	ftp.doRmd(remove);
	                	fileSystemModel = new FileSystemModel(new File(remoteFolder.toString()));
	    			    remotetree.updateUI();
        			}                	
				}
            }
            else if(e.getSource() == pwd)
            {
				Object remoteb = remotetree.getLastSelectedPathComponent();
			    File remotefile = (File)remoteb;					
				String s = remotefile.getPath();
            	while(!s.equals(remoteFolder.toString()))
    			{
            		ftp.doCdup();
            		int n = s.lastIndexOf("\\");
            		s = s.substring(0, n);
    			}            	

            	String ss = remotePath.getText().trim();
        		if(!ss.equals(remoteFolder.toString()))
        		{              	
	            	int n = ss.lastIndexOf("\\");
	            	String target = ss.substring(n, ss.length());
	        		int x = remoteFolder.toString().lastIndexOf("\\");
	        		String temp = ss.substring(x, ss.length());   
	        		int x2 = temp.indexOf("\\", 1);
	        		String temp2 = temp.substring(x2, temp.length());
	        		temp2 = temp2.substring(1, temp2.length());
	        		String[] path = temp2.split("\\\\");
	
	            	int i = 0;
	            	while(!path[i].equals(target))
	            	{       
	            		ftp.doCd(path[i]);
	            		i++;
	            		if(i == path.length)
	            			break;
	            	}   
        		}
            	
            	ftp.doPwd();
            }
            else if(e.getSource() == nlist)
            {
				Object remoteb = remotetree.getLastSelectedPathComponent();
			    File remotefile = (File)remoteb;					
				String s = remotefile.getPath();
            	while(!s.equals(remoteFolder.toString()))
    			{
            		ftp.doCdup();
            		int n = s.lastIndexOf("\\");
            		s = s.substring(0, n);
    			}            	

            	String ss = remotePath.getText().trim();
        		if(!ss.equals(remoteFolder.toString()))
        		{              	
	            	int n = ss.lastIndexOf("\\");
	            	String target = ss.substring(n, ss.length());
	        		int x = remoteFolder.toString().lastIndexOf("\\");
	        		String temp = ss.substring(x, ss.length());   
	        		int x2 = temp.indexOf("\\", 1);
	        		String temp2 = temp.substring(x2, temp.length());
	        		temp2 = temp2.substring(1, temp2.length());
	        		String[] path = temp2.split("\\\\");
	
	            	int i = 0;
	            	while(!path[i].equals(target))
	            	{       
	            		ftp.doCd(path[i]);
	            		i++;
	            		if(i == path.length)
	            			break;
	            	}   
        		}
            	ftp.doNlst();
            }
            else if(e.getSource() == quit)
            {
            	int opt = JOptionPane.showConfirmDialog(null, "Are you sure to exit?", "Exit", JOptionPane.YES_NO_OPTION);
            	if(opt == JOptionPane.YES_OPTION)
            	{            		
						System.exit(1);
            	}
            }
            else
            	;
        }
	}
	
}



