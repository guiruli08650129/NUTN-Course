import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;

import javax.swing.*;

import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextField;

public class hw1 {

	private JFrame frmSignalSystem;
	private JTextField numvalue;
	public JPanel typePanel = new JPanel();
	public JPanel DT_CT_Panel = new JPanel();
	public JButton gobtn = new JButton("GO");
	public ButtonGroup bg = new ButtonGroup();
	public ButtonGroup bg2 = new ButtonGroup();
	public static returnValue tm;
	public static returnValue tn;
	public static int sizeP = 0;
	public static int sizeC = 0;
	public static JTextField[] getp;
	public static JTextField[] getc;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					Mydialog ID = new Mydialog();
					ID.setModal(true);
					ID.setBounds(100, 100, 220, 180);
					ID.setTitle("Initialize");
					tm = ID.getTextm();
					tn = ID.getTextn();

					hw1 window = new hw1();

					window.frmSignalSystem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public hw1() {
		initialize();
	}

	public String getSelectedButtonText(ButtonGroup buttonGroup) {
		for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons
				.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				return button.getText();
			}
		}
		return null;
	}

	private void initialize() {
		frmSignalSystem = new JFrame();
		frmSignalSystem.setTitle("Signal & System Project #1");
		frmSignalSystem.setBounds(100, 100, 570, 400);
		
		frmSignalSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSignalSystem.getContentPane().setLayout(null);


		JLabel equation = new JLabel(new ImageIcon("equation.png"));
		equation.setBounds(10, 10, 534, 80);
		frmSignalSystem.getContentPane().add(equation);
		equation.setFont(new Font("新細明體", Font.BOLD, 18));

		sizeP = Integer.parseInt(tm.getS());
		
		if((9-sizeP) < 0)
			frmSignalSystem.setBounds(100, 100, 570, 400 + 30*(sizeP-9));
			
		
		JLabel[] p = new JLabel[sizeP];
		getp = new JTextField[sizeP];
		for (int i = 0; i < sizeP; i++) {
			p[i] = new JLabel("p" + Integer.toString(i) + " =");
			p[i].setBounds(30, 130 + 25 * i, 40, 20);
			p[i].setFont(new Font("新細明體", Font.BOLD, 14));
			frmSignalSystem.getContentPane().add(p[i]);

			getp[i] = new JTextField();
			getp[i].setBounds(75, 130 + 25 * i, 50, 20);
			getp[i].setText("0.0");
			getp[i].setColumns(5);
			frmSignalSystem.getContentPane().add(getp[i]);
		}

		sizeC = Integer.parseInt(tn.getS());
		JLabel[] c = new JLabel[sizeC];
		getc = new JTextField[sizeC];
		for (int i = 0; i < sizeC; i++) {
			c[i] = new JLabel("c" + Integer.toString(i) + " =");
			c[i].setBounds(130, 130 + 25 * i, 40, 20);
			c[i].setFont(new Font("新細明體", Font.BOLD, 14));
			frmSignalSystem.getContentPane().add(c[i]);

			getc[i] = new JTextField();
			getc[i].setBounds(180, 130 + 25 * i, 50, 20);
			getc[i].setText("0.0");
			getc[i].setColumns(5);
			frmSignalSystem.getContentPane().add(getc[i]);
		}		
		
		typePanel.setBackground(SystemColor.controlHighlight);
		typePanel.setBounds(298, 100, 220, 90);
		frmSignalSystem.getContentPane().add(typePanel);
		JRadioButton samplebtn = new JRadioButton("unit sample response");
		samplebtn.setBackground(SystemColor.controlHighlight);
		samplebtn.setSelected(true);
		samplebtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		samplebtn.setActionCommand("sample");
		bg.add(samplebtn);

		JRadioButton stepbtn = new JRadioButton("unit step response");
		stepbtn.setBackground(SystemColor.controlHighlight);
		stepbtn.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		stepbtn.setActionCommand("step");
		bg.add(stepbtn);
		GroupLayout gl_typePanel = new GroupLayout(typePanel);
		gl_typePanel.setHorizontalGroup(
			gl_typePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_typePanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_typePanel.createParallelGroup(Alignment.LEADING)
						.addComponent(stepbtn, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
						.addComponent(samplebtn, GroupLayout.PREFERRED_SIZE, 209, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_typePanel.setVerticalGroup(
			gl_typePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_typePanel.createSequentialGroup()
					.addGap(14)
					.addComponent(samplebtn, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(stepbtn, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(10, Short.MAX_VALUE))
		);
		typePanel.setLayout(gl_typePanel);

		numvalue = new JTextField();
		numvalue.setBounds(160, 100, 51, 21);
		frmSignalSystem.getContentPane().add(numvalue);
		numvalue.setText("0");
		numvalue.setColumns(5);

		JLabel NumberOfValue = new JLabel("Number of value =");
		NumberOfValue.setBounds(30, 100, 120, 18);
		frmSignalSystem.getContentPane().add(NumberOfValue);
		NumberOfValue.setFont(new Font("Times New Roman", Font.BOLD, 14));

		JPanel DT_CT_Panel = new JPanel();
		DT_CT_Panel.setBackground(SystemColor.controlHighlight);
		DT_CT_Panel.setBounds(298, 205, 221, 75);
		frmSignalSystem.getContentPane().add(DT_CT_Panel);

		JRadioButton DT = new JRadioButton("discrete time");
		DT.setSelected(true);
		DT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		DT.setBackground(SystemColor.controlHighlight);
		DT.setActionCommand("sample");

		JRadioButton CT = new JRadioButton("continue time");
		CT.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		CT.setBackground(SystemColor.controlHighlight);
		CT.setActionCommand("step");

		bg2.add(CT);
		bg2.add(DT);

		GroupLayout gl_DT_CT_Panel = new GroupLayout(DT_CT_Panel);
		gl_DT_CT_Panel.setHorizontalGroup(
			gl_DT_CT_Panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_DT_CT_Panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_DT_CT_Panel.createParallelGroup(Alignment.LEADING)
						.addComponent(CT, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
						.addComponent(DT, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(36, Short.MAX_VALUE))
		);
		gl_DT_CT_Panel.setVerticalGroup(
			gl_DT_CT_Panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_DT_CT_Panel.createSequentialGroup()
					.addGap(14)
					.addComponent(DT, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(CT, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(23, Short.MAX_VALUE))
		);
		DT_CT_Panel.setLayout(gl_DT_CT_Panel);

		gobtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				String[] sc = new String[sizeC];
				for (int i = 0; i < sizeC; i++) {
					sc[i] = getc[i].getText().trim();
				}

				String[] sp = new String[sizeP];
				for (int i = 0; i < sizeP; i++) {
					sp[i] = getp[i].getText().trim();
				}

				String num = numvalue.getText().trim();
				String select = getSelectedButtonText(bg);
				String dt_ct = getSelectedButtonText(bg2);

				show s = new show(sc, sp, select, dt_ct, num);
				s.setVisible(true);
			}
		});

		gobtn.setFont(new Font("Times New Roman", Font.BOLD, 17));
		gobtn.setBounds(298, 290, 221, 50);
		frmSignalSystem.getContentPane().add(gobtn);

	}
}
