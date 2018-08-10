import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class gg extends JPanel {

	private JTextField getPopServer;
	private static JTextField getPopUserName;
	private static JPasswordField getPopPassword;
	public static JButton btnPopLogin;	
	public FuDisplayPanel displayPanel = new FuDisplayPanel();

	private JTextField getSmtpServer;
	private static JTextField getSmtpUserName;
	private static JPasswordField getSmtpPassword;
	public static JButton btnSmtpLogin;
	public FuDisplayPanel displayPanel2 = new FuDisplayPanel(350);

	public static receieveEmail r = new receieveEmail();
	public static receieveEmail r2 = new receieveEmail();
	public static sendMail se = new sendMail();

	public static JButton btnRefresh = new JButton("Refresh");
	public static JButton btnStat = new JButton("Stat");
	public static JButton btnDelete = new JButton("Delete");
	public static JButton btnLast = new JButton("Last");
	public static JButton btnReset = new JButton("Reset");
	public static JButton btnUidl = new JButton("UIDL");
	public static JButton btnNoop = new JButton("Noop");
	private final static JButton btnQuit = new JButton("Quit");

	public static boolean b = false;
	public static boolean c = false;
	
	static LinkedList<String> writeout = new LinkedList<String>();
	public static DefaultListModel model = new DefaultListModel();
	public static ArrayList<String> detail = new ArrayList<String>();
	public static LinkedList<String> ll = new LinkedList<String>();
	
	public static JButton clear = new JButton("Clear");
	public static JButton send = new JButton("Send");
	public static JButton btnReply = new JButton("Reply");
	public static JButton btnForward = new JButton("Forward");
	public static JButton btnSave = new JButton("Save");
	public static JButton btnSearch = new JButton("Search");
	public static JButton btnSmtpQuit = new JButton("Quit");
	public static JTextField getReceiever = new JTextField();
	public static JTextArea t1 = new JTextArea();
	public static JTextField getSubject = new JTextField();
	public static JList list;

	public gg() {

		super(new GridLayout(1, 1));

		JTabbedPane tabbedPane = new JTabbedPane();

		JComponent panel1 = pop3();
		tabbedPane.addTab("RECEIEVE", null, panel1, "POP3");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		JComponent panel2 = smtp();
		tabbedPane.addTab("SEND", null, panel2, "SMTP");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		
		JComponent panel3 = local();
		tabbedPane.addTab("LOCAL", null, panel3, "Save");
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

		add(tabbedPane);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

	}

	protected JComponent smtp() {

		JPanel panel = new JPanel();

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel loginPane = new JPanel();
		panel.add(loginPane);

		JLabel lblSmtpServer = new JLabel("SMTP Server:");
		lblSmtpServer.setFont(new Font("新細明體", Font.PLAIN, 14));
		loginPane.add(lblSmtpServer);

		getSmtpServer = new JTextField("stumail.nutn.edu.tw");
		getSmtpServer.setFont(new Font("新細明體", Font.PLAIN, 14));
		getSmtpServer.setEditable(false);
		loginPane.add(getSmtpServer);
		getSmtpServer.setColumns(11);

		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setFont(new Font("新細明體", Font.PLAIN, 14));
		loginPane.add(lblUserName);

		getSmtpUserName = new JTextField();
		getSmtpUserName.setFont(new Font("新細明體", Font.PLAIN, 14));
		loginPane.add(getSmtpUserName);
		getSmtpUserName.setColumns(19);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("新細明體", Font.PLAIN, 14));
		loginPane.add(lblPassword);

		getSmtpPassword = new JPasswordField();
		getSmtpPassword.setFont(new Font("新細明體", Font.PLAIN, 14));
		loginPane.add(getSmtpPassword);
		getSmtpPassword.setColumns(18);

		btnSmtpLogin = new JButton("Login");
		btnSmtpLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String server = getSmtpServer.getText();
				String name = getSmtpUserName.getText();
				String pwd = getSmtpPassword.getText();

				try {
					se.setAddress(server, name, pwd);
					if (c) {
						b = false;
						r2.authorization(server, name, pwd);
						if (b) {
							
							String s = r2.getSingleLine("STAT");
							String[] temp = s.split(" ");

							for (int i = 0; i < Integer.parseInt(temp[1]); i++) {
								r2.getLines("RETR " + (i + 1));
							}
							LinkedList<String> dd = r2.listDetail();
							for (int i = 0; i < dd.size() - 2; i += 3) {
								displayPanel2.addRow(dd.get(i + 2), dd.get(i + 1), dd.get(i));
							}
							
							displayPanel2.repaint();
							r2.update();
							r2.clear();
							
							btnReply.setEnabled(true);
							btnSmtpQuit.setEnabled(true);
							btnForward.setEnabled(true);
							send.setEnabled(true);
							clear.setEnabled(true);
							btnSmtpLogin.setEnabled(false);
						}

					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});
		loginPane.add(btnSmtpLogin);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));

		JPanel underPanel = new JPanel();
		panel_1.add(underPanel);
		underPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		underPanel.setLayout(new BoxLayout(underPanel, BoxLayout.X_AXIS));

		JPanel writePanel = new JPanel();
		writePanel.setLayout(new BoxLayout(writePanel, BoxLayout.Y_AXIS));
		underPanel.add(writePanel);

		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		writePanel.add(p1);
		p1.add(new JLabel("Receiever:"));

		getReceiever.setColumns(25);
		getReceiever.setFont(new Font("新細明體", Font.PLAIN, 14));
		p1.add(getReceiever);

		JPanel p2 = new JPanel();
		p2.setLayout(new FlowLayout());
		writePanel.add(p2);
		p2.add(new JLabel("Subject:"));

		getSubject.setColumns(25);
		getSubject.setFont(new Font("新細明體", Font.PLAIN, 14));
		p2.add(getSubject);


		t1.setFont(new Font("新細明體", Font.PLAIN, 14));
		t1.setLineWrap(false);
		t1.setWrapStyleWord(true);

		JScrollPane scrollPane = new JScrollPane(t1);
		scrollPane.setPreferredSize(new Dimension(350, 200));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		JPanel p3 = new JPanel();
		p3.add(scrollPane);
		writePanel.add(p3);

		JPanel p4 = new JPanel();
		p4.setLayout(new FlowLayout());
		send = new JButton("Send");
		send.setEnabled(false);
		send.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				if(getSubject.getText().equalsIgnoreCase(""))
					JOptionPane.showMessageDialog(null,  "Please enter title!!", "Send", JOptionPane.ERROR_MESSAGE);
				else if(getReceiever.getText().equalsIgnoreCase(""))
					JOptionPane.showMessageDialog(null,  "Please enter receiever!!", "Send", JOptionPane.ERROR_MESSAGE);
				else if(t1.getText().equalsIgnoreCase(""))
					JOptionPane.showMessageDialog(null,  "Please enter content!!", "Send", JOptionPane.ERROR_MESSAGE);
				else
				{
					try {
						
						String sub = getSubject.getText();
						String[] to = getReceiever.getText().split(",");
						String[] ss = t1.getText().split(" ");						
						se.send(sub, to, ss);
						
					} catch (IOException e1) {
	
						e1.printStackTrace();
					}
				}
				
				
			}
		});
		
		clear = new JButton("Clear");
		clear.setEnabled(false);
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getReceiever.setText("");
				getSubject.setText("");
				t1.setText("");
			}
		});
		p4.add(send);
		p4.add(clear);
		writePanel.add(p4);

		JPanel p5 = new JPanel();
		panel_1.add(p5);
		p5.setLayout(new BoxLayout(p5, BoxLayout.Y_AXIS));

		JPanel controlPane = new JPanel();
		controlPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		p5.add(controlPane);
		btnReply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				

				int t = displayPanel2.table.getSelectedRow();
				if(t < 0)
					JOptionPane.showMessageDialog(null,  "Please select one mail!!", "Reply", JOptionPane.ERROR_MESSAGE);
				else
				{					
					String k2 = (String)displayPanel2.table.getValueAt(t, 1);
					getReceiever.setText(k2);						
					
					String k1 = (String)displayPanel2.table.getValueAt(t, 0);
					getSubject.setText("Re:"+k1);
				}
			}
		});
		btnReply.setEnabled(false);
		controlPane.add(btnReply);

		btnForward.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String server = getSmtpServer.getText();
				String name = getSmtpUserName.getText();
				String pwd = getSmtpPassword.getText();
				int k = displayPanel2.table.getSelectedRow();
				if(k < 0)
				{
					JOptionPane.showMessageDialog(null,  "Please select one mail!!", "Forward", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					try {
						
						r2.clear();
						b = false;
						r2.authorization(server, name, pwd);
						if (b) {
							r2.getLines("RETR " + (k+1));
							LinkedList<String> de = r2.saveDetail();
							
							for(int i = 0 ; i < de.size() ; i++)
							{
								if(de.get(i).startsWith("*"))
									getSubject.setText(de.get(i).substring(1, de.get(i).length()).trim());
								else if(de.get(i).startsWith("@"))
									;
								else
								{
									t1.append(de.get(i).trim()+"\n");
								}
							}
						
						r2.update();
						r2.clear();
						de.clear();
						
						}
					} catch (IOException e1) {
	
						e1.printStackTrace();
					}
				}
			}
		});
		btnForward.setEnabled(false);
		controlPane.add(btnForward);

		btnSmtpQuit.setEnabled(false);
		btnSmtpQuit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				try {

					int y = JOptionPane.showConfirmDialog(null, "Do you want to log out?", "Quit",
							JOptionPane.OK_CANCEL_OPTION);
					if (y == JOptionPane.OK_OPTION) {
						
						btnReply.setEnabled(false);
						btnSmtpQuit.setEnabled(false);
						btnSmtpLogin.setEnabled(true);
						btnForward.setEnabled(false);
						send.setEnabled(false);
						clear.setEnabled(false);
						getReceiever.setText("");
						getSubject.setText("");
						getSmtpUserName.setText("");
						getSmtpPassword.setText("");
						displayPanel2.remove();
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		controlPane.add(btnSmtpQuit);
		
		p5.add(displayPanel2);

		return panel;
	}

	protected JComponent pop3() {

		JPanel panel = new JPanel();

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel loginPane = new JPanel();
		panel.add(loginPane);

		JLabel lblPopServer = new JLabel("Pop Server:");
		lblPopServer.setFont(new Font("新細明體", Font.PLAIN, 14));
		loginPane.add(lblPopServer);

		getPopServer = new JTextField("stumail.nutn.edu.tw");
		getPopServer.setFont(new Font("新細明體", Font.PLAIN, 14));
		getPopServer.setEditable(false);
		loginPane.add(getPopServer);
		getPopServer.setColumns(15);

		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setFont(new Font("新細明體", Font.PLAIN, 14));
		loginPane.add(lblUserName);

		getPopUserName = new JTextField();
		getPopUserName.setFont(new Font("新細明體", Font.PLAIN, 14));
		loginPane.add(getPopUserName);
		getPopUserName.setColumns(19);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("新細明體", Font.PLAIN, 14));
		loginPane.add(lblPassword);

		getPopPassword = new JPasswordField();
		getPopPassword.setFont(new Font("新細明體", Font.PLAIN, 14));
		loginPane.add(getPopPassword);
		getPopPassword.setColumns(18);

		btnPopLogin = new JButton("Login");
		btnPopLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String server = getPopServer.getText();
				String name = getPopUserName.getText();
				String pwd = getPopPassword.getText();

				try {
					r.clear();
					r.authorization(server, name, pwd);
					if (b) {
						String s = r.getSingleLine("STAT");
						String[] temp = s.split(" ");

						for (int i = 0; i < Integer.parseInt(temp[1]); i++) {
							r.getLines("RETR " + (i + 1));
						}
						LinkedList<String> d = r.listDetail();
						for (int i = 0; i < d.size() - 2; i += 3) {
							displayPanel.addRow(d.get(i + 2), d.get(i + 1), d.get(i));
						}

						displayPanel.repaint();
						btnSave.setEnabled(true);
						btnRefresh.setEnabled(true);
						btnStat.setEnabled(true);
						btnDelete.setEnabled(true);
						btnLast.setEnabled(true);
						btnSearch.setEnabled(true);
						btnReset.setEnabled(true);
						btnUidl.setEnabled(true);
						btnNoop.setEnabled(true);
						btnQuit.setEnabled(true);
						btnPopLogin.setEnabled(false);
						d.clear();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		});
		loginPane.add(btnPopLogin);

		JPanel controlPane = new JPanel();
		panel.add(controlPane);

		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					
					r.clear();
					r = new receieveEmail();
					String server = getPopServer.getText();
					String name = getPopUserName.getText();
					String pwd = getPopPassword.getText();
					b = false;
					r.authorization2(server, name, pwd);
					if(b)
					{
						String s = r.getSingleLine("STAT");
						String[] temp = s.split(" ");
	
						for (int i = 0; i < Integer.parseInt(temp[1]); i++) {
							r.getLines("RETR " + (i + 1));
						}
						displayPanel.remove();
						LinkedList<String> d = r.listDetail();
						for (int i = 0; i < d.size() - 2; i += 3) {
							displayPanel.addRow(d.get(i + 2), d.get(i + 1), d.get(i));
						}
	
						displayPanel.repaint();
					}
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
		});
		btnRefresh.setEnabled(false);
		controlPane.add(btnRefresh);

		btnSearch.setEnabled(false);
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String s = r.getSingleLine("STAT");
					String[] ss = s.split(" ");
					String s1 = "Number of mail: ";
					JSpinner js = new JSpinner();
					js.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), new Integer(Integer.parseInt(ss[1])), new Integer(1)));
					
					int ok = JOptionPane.showConfirmDialog(null, new Object[] { s1, js }, "Search", JOptionPane.OK_CANCEL_OPTION);
					if(ok == JOptionPane.OK_OPTION)
					{
						int n = (int) js.getValue();
						String title = "";
						String sender = "";					
						r.clear();
						r.getLines("RETR " + (n));
						LinkedList<String> de = r.saveDetail();
						for(int i = 0 ; i < de.size() ; i++)
						{
							if(de.get(i).startsWith("@"))
								sender = de.get(i).substring(2,  de.get(i).length()-1);
							else if(de.get(i).startsWith("*"))
								title = de.get(i).substring(1,  de.get(i).length());
							else
								;
						}
						String[] arr = new String[de.size()+1];
						arr[0] = "Title: "+title;
						arr[1] = "Sender: "+sender+"\n";
						arr[2] = "---------------------------------------------------------------";
						for(int i =2 ; i < de.size() ; i++)
						{
							arr[i+1] = de.get(i).trim();
						}
						
						JOptionPane.showMessageDialog(null,  arr, "Search", JOptionPane.INFORMATION_MESSAGE);
						de.clear();
						
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		controlPane.add(btnSearch);
		
		
		btnStat.setEnabled(false);
		btnStat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String s = r.getSingleLine("STAT");
					String[] ss = s.split(" ");
					String s1 = "Number of total mail: " + ss[1];
					String s2 = "Total Size: " + ss[2];
					JOptionPane.showMessageDialog(null, new String[] { s1, s2 }, "Stat",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		controlPane.add(btnStat);

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int k = displayPanel.table.getSelectedRow();
				if(k<0)
				{
					JOptionPane.showMessageDialog(null,  "Please select one email!!", "Save", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					try {
						String title = "";
						String sender = "";					
						r.clear();
						r.getLines("RETR " + (k+1));
						LinkedList<String> de = r.saveDetail();
						for(int i = 0 ; i < de.size() ; i++)
						{
							if(de.get(i).startsWith("@"))
								sender = de.get(i).substring(1,  de.get(i).length());
							else if(de.get(i).startsWith("*"))
								title = de.get(i).substring(1,  de.get(i).length());
							else
								;
						}
	
						String temp = title+"&"+sender+"&";
						String tt = "";
						for(int i = 0 ; i < de.size() ; i++)
						{
							
							if(!de.get(i).startsWith("@"))
							{
								if(!de.get(i).startsWith("*"))
								{
									tt += de.get(i).trim()+"&";
								}
							}						
						}
						temp = temp+tt;
						ll.add(temp);
						JOptionPane.showMessageDialog(null,  "Save NO."+(k+1)+" mail to local!!", "Save", JOptionPane.INFORMATION_MESSAGE);
						
						de.clear();
					} catch (IOException e1) {
	
						e1.printStackTrace();
					}
					
					model.removeAllElements();
					for(int i = 0 ; i < ll.size() ; i++)
					{
						String[] q = ll.get(i).trim().split("&");
						model.add(i, q[0]);
					}
					
					list.updateUI();				
					
				}
			}
		});
		btnSave.setEnabled(false);
		controlPane.add(btnSave);
		
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					int k = displayPanel.table.getSelectedRow();
					if(k < 0)
					{
						JOptionPane.showMessageDialog(null, "Please select one email!", "UIDL", JOptionPane.ERROR_MESSAGE);
					}
					else
					{
						int y = JOptionPane.showConfirmDialog(null, "Do you want to delete NO." + (k + 1) + "?", "Delete",
								JOptionPane.OK_CANCEL_OPTION);
						if (y == JOptionPane.OK_OPTION) {
							String s = r.getSingleLine("DELE " + Integer.toString(k + 1));
							String out = s.substring(3, s.length());
							JOptionPane.showMessageDialog(null, out, "Delete", JOptionPane.INFORMATION_MESSAGE);
							
							displayPanel.model.removeRow(k);
							displayPanel.repaint();
						}	
						
					}


				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnDelete.setEnabled(false);
		controlPane.add(btnDelete);

		btnLast.setEnabled(false);
		btnLast.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				try {
					String s = r.getSingleLine("LAST");
					JOptionPane.showMessageDialog(null, "ID of next mail: " + (Integer.parseInt(s.substring(3, s.length()).trim())+1), "Last",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		controlPane.add(btnLast);

		btnReset.setEnabled(false);
		btnReset.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				try {
					String ss = r.getSingleLine("RSET");
					JOptionPane.showMessageDialog(null, ss.substring(3, ss.length()).trim(), "Rset", JOptionPane.INFORMATION_MESSAGE);
					
					
					r.clear();
					String s = r.getSingleLine("STAT");
					String[] temp = s.split(" ");

					for (int i = 0; i < Integer.parseInt(temp[1]); i++) {
						r.getLines("RETR " + (i + 1));
					}
					displayPanel.remove();
					LinkedList<String> d = r.listDetail();
					for (int i = 0; i < d.size() - 2; i += 3) {
						displayPanel.addRow(d.get(i + 2), d.get(i + 1), d.get(i));
					}

					displayPanel.repaint();
					
					
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		controlPane.add(btnReset);

		btnUidl.setEnabled(false);
		btnUidl.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				try {

					int k = displayPanel.table.getSelectedRow();
					if (k < 0)
					{
						JOptionPane.showMessageDialog(null, "Please select one email!", "UIDL", JOptionPane.ERROR_MESSAGE);
					}
					else {
						String s = r.getSingleLine("UIDL " + Integer.toString(k + 1));

						String[] out = s.split(" ");
						JOptionPane.showMessageDialog(null,
								new Object[] { "Email Number: " + out[1], "Email ID: " + out[2] }, "STAT",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		controlPane.add(btnUidl);

		btnNoop.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				try {
					String s = r.getSingleLine("NOOP");
					JOptionPane.showMessageDialog(null, s, "Noop", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNoop.setEnabled(false);
		controlPane.add(btnNoop);

		btnQuit.setEnabled(false);
		btnQuit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				try {

					int y = JOptionPane.showConfirmDialog(null, "Do you want to log out?", "Quit",
							JOptionPane.OK_CANCEL_OPTION);
					if (y == JOptionPane.OK_OPTION) {
						r.update();
						btnRefresh.setEnabled(false);
						btnStat.setEnabled(false);
						btnSearch.setEnabled(false);
						btnDelete.setEnabled(false);
						btnLast.setEnabled(false);
						btnReset.setEnabled(false);
						btnUidl.setEnabled(false);
						btnNoop.setEnabled(false);
						btnQuit.setEnabled(false);
						btnSave.setEnabled(false);
						btnPopLogin.setEnabled(true);
						getPopUserName.setText("");
						getPopPassword.setText("");

						displayPanel.remove();
						

						
					}

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		controlPane.add(btnQuit);

		displayPanel = new FuDisplayPanel();
		panel.add(displayPanel);

		return panel;
	}

	protected JComponent local()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		   try {
			    FileInputStream ufin = new FileInputStream("mail.dat");
			    ObjectInputStream uois = new ObjectInputStream(ufin);

			    ll = (LinkedList<String>) uois.readObject();	    
			    uois.close();
			    ufin.close();
			    }
			   catch (Exception e) { e.printStackTrace(); }

		
		JPanel listPanel = new JPanel();
		listPanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Local Database", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.add(listPanel);
		
		for(int i = 0 ; i < ll.size() ; i++)
		{
			String[] t = ll.get(i).split("&");
			model.add(i, t[0]);
		}
		
		
		
		
		JPanel detailPanel = new JPanel();
		detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));
		panel.add(detailPanel);
		
		JLabel sub = new JLabel("Subject:");
		sub.setFont(new Font("新細明體", Font.PLAIN, 14));
		JPanel pp1 = new JPanel();
		pp1.setLayout(new FlowLayout());
		pp1.add(sub);
		JTextField getsub = new JTextField();
		getsub.setColumns(20);
		getsub.setEditable(false);
		pp1.add(getsub);
		detailPanel.add(pp1);
		
		
		JPanel pp2 = new JPanel();
		pp2.setLayout(new FlowLayout());
		detailPanel.add(pp2);
		JLabel sender = new JLabel("Sender:");
		sender.setFont(new Font("新細明體", Font.PLAIN, 14));
		pp2.add(sender);
		JTextField getsen = new JTextField();
		getsen.setColumns(20);
		getsen.setEditable(false);
		pp2.add(getsen);
		
		JTextArea t2 = new JTextArea();
		t2.setFont(new Font("新細明體", Font.PLAIN, 14));
		t2.setLineWrap(false);
		t2.setEditable(false);
		t2.setWrapStyleWord(true);

		JScrollPane scrollPane = new JScrollPane(t2);
		scrollPane.setPreferredSize(new Dimension(350, 300));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		detailPanel.add(scrollPane);
		
		
		list = new JList(model);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int r = list.getSelectedIndex();
				String[] t = ll.get(r).split("&");
				getsub.setText(t[0]);
				getsen.setText(t[1].substring(1,  t[1].length()-1));
				t2.setText("");
				for(int k = 2 ; k < t.length ; k++)
				{
					t2.append(t[k]+"\n");
				}
			}
		});
		JScrollPane jp = new JScrollPane(list);
		jp.setPreferredSize(new Dimension(350, 200));
		jp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		listPanel.add(jp);
		
		
		return panel;
	}
	
	private static void createAndShowGUI() {

		JFrame frame = new JFrame("Email");
		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
		      public void windowClosing(WindowEvent e) {
		        int result=JOptionPane.showConfirmDialog(frame,
		                   "\u78ba\u5b9a\u8981\u7d50\u675f\u7a0b\u5f0f\u55ce?",
		                   "\u78ba\u8a8d\u8a0a\u606f",
		                   JOptionPane.YES_NO_OPTION,
		                   JOptionPane.WARNING_MESSAGE);
		        if (result==JOptionPane.YES_OPTION) 
		        {
		    	    try {		    	    	
		    	        FileOutputStream fout = new FileOutputStream("mail.dat");
		    	        ObjectOutputStream oos = new ObjectOutputStream(fout);
		    	        oos.writeObject(ll);
		    	        oos.close();
		    	        fout.close();
		    	        ll.clear();
		    	        }
		    	     catch (Exception e2) { e2.printStackTrace(); }	
		        	System.exit(0);
		        }
		        }   
		      });
		frame.getContentPane().add(new gg(), BorderLayout.CENTER);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {

				UIManager.put("swing.boldMetal", Boolean.FALSE);
				createAndShowGUI();
			}
		});

	}

	class FuDisplayPanel extends JPanel {

		private String[] COLUMNS = { "Title", "Sender", "Date" };
		private DefaultTableModel model = new DefaultTableModel(COLUMNS, 0);
		public JTable table = new JTable(model);

		public FuDisplayPanel() {
			setLayout(new BorderLayout());
			table.setPreferredScrollableViewportSize(new Dimension(760, 200));
			JScrollPane jp = new JScrollPane(table);
			jp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			jp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			add(new JScrollPane(table));
		}

		public FuDisplayPanel(int size) {
			setLayout(new BorderLayout());
			table.setPreferredScrollableViewportSize(new Dimension(size, 200));
			JScrollPane jp = new JScrollPane(table);
			jp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			jp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			add(new JScrollPane(table));
		}

		public void addRow(String s1, String s2, String s3) {
			Object[] row = new Object[3];
			String title = new String();
			String sender = new String();
			String date = new String();

			
			if (s1.startsWith("1"))
				title = s1.substring(1, s1.length());
			else if (s1.startsWith("2"))
				sender = s1.substring(1, s1.length());
			else if (s1.startsWith("3"))
				date = s1.substring(1, s1.length());

			if (s2.startsWith("1"))
				title = s2.substring(1, s2.length());
			else if (s2.startsWith("2"))
				sender = s2.substring(1, s2.length());
			else if (s2.startsWith("3"))
				date = s2.substring(1, s2.length());

			if (s3.startsWith("1"))
				title = s3.substring(1, s3.length());
			else if (s3.startsWith("2"))
				sender = s3.substring(1, s3.length());
			else if (s3.startsWith("3"))
				date = s3.substring(1, s3.length());

			row[0] = title.trim();
			row[1] = sender.substring(1, sender.length()-1).trim();
			row[2] = date.trim();
			model.addRow(row);
		}

		public void remove() {
			model.setRowCount(0);
		}

	}

}