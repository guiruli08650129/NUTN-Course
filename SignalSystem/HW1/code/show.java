import java.awt.*;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.BarPlot;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.lines.SmoothLineRenderer2D;
import de.erichseifert.gral.plots.points.*;
import de.erichseifert.gral.ui.InteractivePanel;

import java.awt.Color;
import java.awt.geom.*;

import javax.swing.JFrame;

import de.erichseifert.gral.plots.areas.AreaRenderer;
import de.erichseifert.gral.plots.areas.LineAreaRenderer2D;

public class show extends JFrame {
	private static final long serialVersionUID = 1L;
	protected static double[] y;
	protected static double[] x;
	public static double[] total;
	protected static int num = 0;

	@SuppressWarnings("unchecked")
	public show(String[] sc, String[] sp, String select, String dtct,
			String numvalue) {

		super("SIGNAL RESULT");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 400);
		setLocationRelativeTo(null);
		num = Integer.parseInt(numvalue);

		double[] c = new double[sc.length];
		for (int i = 0; i < sc.length; i++)
			c[i] = Double.parseDouble(sc[i]);

		double[] p = new double[sp.length];
		for (int j = 0; j < sp.length; j++)
			p[j] = Double.parseDouble(sp[j]);

		x = new double[num];
		y = new double[num];

		for (int i = 0; i < num; i++) {
			if (select.contains("sample")) {
				x[0] = 1;
				x[i] = 0;
			} else if (select.contains("step")) {
				x[i] = 1;
			}
		}

		for (int i = 0; i < num; i++)
			y[i] = 0;

		total = new double[num];
		for (int i = 0; i < num; i++) {
			total[0] = c[0];
			total[i] = 0;
		}

		DataTable data = new DataTable(Double.class, Double.class);
		for (double i = (0.0 - num); i < 0; i += 1)
			data.add(i, 0.0);

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
			y[n] = tempX + tempY;
			total[n] = tempX + tempY;
			data.add((double) n, total[n]);
		}

		BarPlot plot = new BarPlot(data);

		if (dtct.contains("discrete")) {
			AreaRenderer r = new LineAreaRenderer2D();
			plot.setAreaRenderers(data, r);
			r.setColor(new Color(1.0f, 0.3f, 1.0f));
		} else {
			LineRenderer lr = new SmoothLineRenderer2D();
			plot.setLineRenderers(data, lr);
			lr.setColor(new Color(1.0f, 0.3f, 1.0f));
		}

		PointRenderer points1 = new DefaultPointRenderer2D();
		points1.setShape(new Ellipse2D.Double(-4.0, -4.0, 8.0, 8.0));
		points1.setColor(new Color(0.0f, 0.3f, 1.0f, 0.3f));
		plot.setPointRenderers(data, points1);

		Container pn = getContentPane();
		pn.add(new InteractivePanel(plot));
		setContentPane(pn);
	}

}
