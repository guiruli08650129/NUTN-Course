import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class gui {

	private JFrame frame;
	private final JPanel panel = new JPanel();
	private final JPanel panel_1 = new JPanel();
	private final JPanel panel_2 = new JPanel();
	public final static JButton btnDownload = new JButton("Download");
	public final static JButton btnConnect = new JButton("Connect");
	public final static JButton btnDisconnect = new JButton("Disconnect");
	public static JScrollPane jp;
	public static JList<String> list;
	public static DefaultListModel<String> model = new DefaultListModel<String>();
	public static udpClient client = new udpClient();

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
		frame = new JFrame("\u7db2\u8def\u5f71\u97f3\u9ede\u64ad\u7cfb\u7d71");
		frame.setResizable(false);
		frame.setBounds(100, 100, 400, 310);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(panel, BorderLayout.NORTH);

		JLabel title = new JLabel("Media List");
		title.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panel.add(title);

		frame.getContentPane().add(panel_1, BorderLayout.CENTER);

		list = new JList<String>(model);

		jp = new JScrollPane(list);
		jp.setPreferredSize(new Dimension(300, 200));
		jp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jp.setViewportView(list);
		panel_1.add(jp);
		frame.getContentPane().add(panel_1);

		btnDownload.setEnabled(false);

		actionPerformed action = new actionPerformed();
		btnConnect.addActionListener(action);
		btnDownload.addActionListener(action);
		btnDisconnect.addActionListener(action);
		panel_2.add(btnConnect);
		panel_2.add(btnDownload);
		panel_2.add(btnDisconnect);
		frame.getContentPane().add(panel_2, BorderLayout.SOUTH);

	}

	static class actionPerformed implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btnConnect) {

				client.getList();

				for (int i = 0; i < udpClient.mediaList.size(); i++) {
					model.add(0, udpClient.mediaList.get(i).trim());
				}

				list.setModel(model);

				btnDownload.setEnabled(true);
				btnConnect.setEnabled(false);

			} else if (e.getSource() == btnDownload) {
				String file = list.getSelectedValue();
				if (file == null)
					JOptionPane
							.showMessageDialog(
									null,
									"\u8acb\u9078\u64c7\u8981\u4e0b\u8f09\u7684\u6a94\u6848",
									"WARNING", JOptionPane.WARNING_MESSAGE);
				else {
					try {
						client.getFile(file);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}

			} else if (e.getSource() == btnDisconnect) {
				System.exit(0);
			} else
				;
		}
	}
}
