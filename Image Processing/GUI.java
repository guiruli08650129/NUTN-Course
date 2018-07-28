import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.io.*;


public class GUI extends JFrame{

	JFrame frmBmp;
	JTextField fileName;
	static JLabel jlh;
	static JLabel jlw;
	static MyPanel panel;
	static int hight = 0;
	static int width = 0;
	static int colorMap[][];

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmBmp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public GUI() {

		frmBmp = new JFrame();
		frmBmp.setTitle("Show BMP");
		frmBmp.setBounds(100, 100, 530, 600);
		frmBmp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBmp.getContentPane().setLayout(null);
		frmBmp.setResizable(true);
		
		JPanel p1 = new JPanel();
		p1.setLocation(0, 0);
		p1.setLayout(null);
		p1.setSize(new Dimension(520, 75));
		
		jlh = new JLabel();
		jlh.setBounds(55, 45, 80, 20);
		jlh.setText("HEIGHT：");
		p1.add(jlh);
		
		jlw = new JLabel();
		jlw.setBounds(230, 45, 80, 20);
		jlw.setText("WIDTH：");
		p1.add(jlw);
		
		fileName = new JTextField();
		fileName.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		fileName.setBounds(85, 10, 330, 25);
		fileName.setEditable(false);
		fileName.setColumns(35);
		p1.add(fileName);
		
		JButton open = new JButton("Open");
		open.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		open.setBounds(420, 10, 70, 25);
		open.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				 JFileChooser jfc = new JFileChooser();
				 jfc.setFileFilter(new FileNameExtensionFilter("24 bits(*.bmp)", "bmp"));
			     if(jfc.showOpenDialog(frmBmp)==JFileChooser.APPROVE_OPTION )
			     {
			    	 String s = jfc.getSelectedFile().getAbsolutePath();
			    	 fileName.setText(s);
			    	 getPhoto(s);

			     }
			}
		});
		
		p1.add(open);		
		frmBmp.getContentPane().add(p1);
		
		JLabel lblNewLabel = new JLabel("Choose File:");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 10, 75, 25);
		p1.add(lblNewLabel);
		
		panel=new MyPanel();
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(new Dimension(400,200));
		panel.setBounds(0, 65, 520, 500);
		frmBmp.getContentPane().add(panel);
		
	}

	class MyPanel extends JPanel{
		public void paint(Graphics g) {
			super.paint(g);
			if(colorMap!=null)
			{
				for(int i=0;i<colorMap.length;i++)
				{
					for(int j=0;j<colorMap[i].length;j++)
					{
						g.setColor(new Color(colorMap[i][j]));
						g.drawLine(j, hight-i, j,hight- i);
					}
				}
			}
		}
	}	

	public static void getPhoto(String name) {	    
	    try 
	    {
	        BufferedInputStream br = new BufferedInputStream(new FileInputStream(name));	
	        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("data.txt"));
	        int i;
	        
	        while((i=br.read())!=-1)
	        {
	        	out.write(i);
	        }
	        
	        out.flush();	
	        out.close();
	        br.close();
	        System.out.println("SUCCESS");	   
	        readBMP(name);

	    } catch (IOException ex) {

	    }
	}
	
	public static void readBMP(String selectFile)
	{
		try {
			FileInputStream fis=new FileInputStream("data.txt");
			BufferedInputStream bis=new BufferedInputStream(fis);
			byte[] wb=new byte[4];//讀取寬度的byte數
			byte[] hb=new byte[4];//讀取高度的byte數
			
			bis.skip(18);
			bis.read(wb);//讀取寬度
			bis.read(hb);//讀取高度
			width=byteToint(wb);
			hight=byteToint(hb);
			
			System.out.println("SIZE = " + width+"*"+hight);
			jlh.setText("HEIGHT：" + hight);
			jlw.setText("WIDTH：" + width);
			
			colorMap=new int[hight][width];
			int skip=4-width*3%4;//跳過補零			
			bis.skip(28);
			for(int i=0;i<hight;i++)
			{
				for(int j=0;j<width;j++)
				{
					int blue=bis.read();
					int green=bis.read();
					int red=bis.read();
					Color c=new Color(red,green,blue);
					colorMap[i][j]=c.getRGB();
				}
				if(skip!=4)
				bis.skip(skip);
			}
			bis.close();
			panel.setPreferredSize(new Dimension(width,hight));
			javax.swing.SwingUtilities.updateComponentTreeUI(panel);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static  int byteToint(byte b[])
	{
		int t1=(b[3]&0xff)<<24;
		int t2=(b[2]&0xff)<<16;
		int t3=(b[1]&0xff)<<8;
		int t4=b[0]&0xff;
		
		return t1+t2+t3+t4;
	}
}