import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
class Mydialog extends JDialog {

	private JButton ok = new JButton("OK");
	private JTextField getm = new JTextField();
	private JTextField getn = new JTextField();
	private JLabel m = new JLabel();
	private JLabel n = new JLabel();
	returnValue textm;
	returnValue textn;

	public Mydialog() {

		getm.setColumns(5);
		getm.setBounds(100, 20, 70, 20);
		getm.setText("1");

		getn.setColumns(5);
		getn.setBounds(100, 50, 70, 20);
		getn.setText("1");

		m.setText("m =");
		m.setBounds(40, 20, 40, 20);
		m.setFont(new Font("�s�ө���", Font.BOLD, 18));

		n.setText("n =");
		n.setBounds(40, 50, 40, 20);
		n.setFont(new Font("�s�ө���", Font.BOLD, 18));

		ok.setBounds(70, 90, 60, 30);

		JPanel JP = new JPanel(null);
		JP.setLayout(null);
		add(JP);
		JP.add(m);
		JP.add(getm);
		JP.add(n);
		JP.add(getn);
		JP.add(ok);

		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				setVisible(false); // �I�F�o�ӫ��s����N��Dialog����
				textm = new returnValue(getm.getText());
				textn = new returnValue(getn.getText());
				dispose(); // ��Dialog ����
			}
		});
	}

	public returnValue getTextm() {
		setVisible(true); // �I�s��Function���� ��Dialog���
		return textm;
	}

	public returnValue getTextn() {
		// setVisible(true); // �I�s��Function���� ��Dialog���
		return textn;
	}
}

class returnValue // �@���H�K�w�q��Class ��� Dialog�]�i�H�^�ǵ�Frame �ۭq�qClass
{
	String s;

	public returnValue(String ins) {
		s = ins;
	}

	public String getS() {
		return s;
	}
}