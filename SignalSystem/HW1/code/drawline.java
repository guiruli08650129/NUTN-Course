import java.awt.Graphics;

import javax.swing.JPanel;


public class drawline extends JPanel{

	
	
	  public void paintComponent(Graphics g) {
		   super.paintComponent(g);
		   g.drawLine(5,5,25,5);
		  }
}
