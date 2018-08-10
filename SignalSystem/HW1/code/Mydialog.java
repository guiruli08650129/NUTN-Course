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
		m.setFont(new Font("新細明體", Font.BOLD, 18));

		n.setText("n =");
		n.setBounds(40, 50, 40, 20);
		n.setFont(new Font("新細明體", Font.BOLD, 18));

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

				setVisible(false); // 點了這個按鈕之後就讓Dialog隱藏
				textm = new returnValue(getm.getText());
				textn = new returnValue(getn.getText());
				dispose(); // 讓Dialog 關閉
			}
		});
	}

	public returnValue getTextm() {
		setVisible(true); // 呼叫此Function之後 讓Dialog顯示
		return textm;
	}

	public returnValue getTextn() {
		// setVisible(true); // 呼叫此Function之後 讓Dialog顯示
		return textn;
	}
}

class returnValue // 一個隨便定義的Class 表示 Dialog也可以回傳給Frame 自訂義Class
{
	String s;

	public returnValue(String ins) {
		s = ins;
	}

	public String getS() {
		return s;
	}
}