import java.awt.*;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.BarPlot;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.lines.SmoothLineRenderer2D;
import de.erichseifert.gral.plots.points.*;
import de.erichseifert.gral.ui.InteractivePanel;

import java.awt.Color;
import java.awt.geom.*;
import java.math.*;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import de.erichseifert.gral.plots.areas.AreaRenderer;
import de.erichseifert.gral.plots.areas.LineAreaRenderer2D;
import de.erichseifert.gral.plots.axes.AxisRenderer;

public class show extends JFrame {
	private static final long serialVersionUID = 1L;
	protected static double[] y;
	protected static double[] x;
	public static double[] total;
	

	public static int cal = 0;

	public show()
	{}
	
	@SuppressWarnings("unchecked")
	public show(String[] sc, String[] sp, double[] absX, int num) {

		super("SIGNAL RESULT");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(800, 400);
		setLocationRelativeTo(null);

		double[] c = new double[sc.length];
		for (int i = 0; i < sc.length; i++)
			c[i] = Double.parseDouble(sc[i]);

		double[] p = new double[sp.length];
		for (int j = 0; j < sp.length; j++)
			p[j] = Double.parseDouble(sp[j]);

		x = new double[num];
		y = new double[num];
		total = new double[num];
		double[] temp = new double[num];
		for (int i = 0; i < num; i++) {			
			x[0] = 1;
			x[i] = 0;
			y[i] = 0;
			total[0] = c[0];
			total[i] = 0;	
			temp[i] = 0;
		}

		DataTable data = new DataTable(Double.class, Double.class);
		DataTable hn = new DataTable(Double.class, Double.class);
		DataTable xn = new DataTable(Double.class, Double.class);
		
		//uni-sample
		for (int n = 0; n < num; n += 1) {

			double tempX = 0.0;
			double tempY = 0.0;

			for (int m = 0; m < sp.length; m++) {
				if (n - m < 0)
					continue;
				else {
					tempX += p[m] * x[n - m];
				}
			}

			for (int k = 0; k < sc.length; k++) {
				if (n - k - 1 < 0)
					continue;
				else {
					tempY += c[k] * y[n - k - 1];
				}
			}
			total[n] = tempX + tempY;
			hn.add((double) n, total[n]);
		}

		for(int i = 0 ; i < num ; i++)
		{
			xn.add((double)i, absX[i]);
		}
		
		//convolution
		for(int n = 0 ; n < num ; n++)
		{		
			double segma = 0;
			for(int k = 0 ; k < num ; k++)
			{
				if((n-k) >= 0)
					temp[k] = absX[k] * total[n - k];
				else
					temp[k] = absX[k] * total[num + n - k];
			}
			for(int j = 0 ; j < num ; j++)
			{
				segma += temp[j];
			}
			data.add((double)n, segma);
		}
		
		
		XYPlot plot = new XYPlot(xn, hn, data);


		PointRenderer points1 = new DefaultPointRenderer2D();
		points1.setShape(new Ellipse2D.Double(-4.0, -4.0, 8.0, 8.0));
		points1.setColor(new Color(1.0f, 0.5f, 1.0f, 0.6f));
		plot.setPointRenderers(data, points1);
		


		PointRenderer points2 = new DefaultPointRenderer2D();
		points2.setShape(new Ellipse2D.Double(-4.0, -4.0, 8.0, 8.0));
		points2.setColor(new Color(0.3f, 0.3f, 0.6f, 0.6f));
		plot.setPointRenderers(xn, points2);
		
		
		PointRenderer points3 = new DefaultPointRenderer2D();
		points3.setShape(new Ellipse2D.Double(-4.0, -4.0, 8.0, 8.0));
		points3.setColor(new Color(0.5f, 1.0f, 0.2f, 0.8f));
		plot.setPointRenderers(hn, points3);

		plot.getAxisRenderer(BarPlot.AXIS_X).getLabel().setText("X-Axis = n");
		plot.getAxisRenderer(BarPlot.AXIS_Y).getLabel().setText("Y-Axis = y[n]");
		
		Container pn = getContentPane();
		pn.add(new InteractivePanel(plot));
		setContentPane(pn);
	}
	
	public show(DataTable fr, String s)
	{
		super("Magnitude Frequency Response");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 400);
		setLocationRelativeTo(null);
		
		XYPlot plot = new XYPlot(fr);


		LineRenderer lr = new SmoothLineRenderer2D();
		plot.setLineRenderers(fr, lr);
		lr.setColor(new Color(1.0f, 0.3f, 1.0f));
		PointRenderer points1 = new DefaultPointRenderer2D();
		points1.setShape(new Ellipse2D.Double(-4.0, -4.0, 8.0, 8.0));
		points1.setColor(new Color(0.3f, 0.5f, 1.0f, 0.6f));
		plot.setPointRenderers(fr, points1);
		
		if(s.equals("mag"))
			plot.getAxisRenderer(BarPlot.AXIS_Y).getLabel().setText("Y-Axis = |H(jw)|");	
		else
			plot.getAxisRenderer(BarPlot.AXIS_Y).getLabel().setText("Y-Axis = <|H(jw)|");
		plot.getAxisRenderer(BarPlot.AXIS_X).getLabel().setText("X-Axis = log(w)");

		
		Container pn = getContentPane();
		pn.add(new InteractivePanel(plot));
		setContentPane(pn);
		
		
	}
	


}
