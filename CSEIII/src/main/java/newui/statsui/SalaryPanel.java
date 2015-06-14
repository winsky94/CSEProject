package newui.statsui;

import java.util.ArrayList;

import javax.swing.JLabel;

import newui.FatherPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.DefaultXYDataset;

import data.PlayerStatistic;

public class SalaryPanel extends FatherPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel titleLbl;
	ChartPanel chartPnl;

	public SalaryPanel() {
		
		PlayerStatistic ps = new PlayerStatistic();
		ArrayList<Double> xlist = ps.testX();
		ArrayList<Double> ylist = ps.testY();
		int size = xlist.size();
		double[][] data = new double[2][size];
		for (int i = 0; i < size; i++) {
			data[0][i] = xlist.get(i);
			data[1][i] = ylist.get(i);
		}
		DefaultXYDataset xydataset = new DefaultXYDataset(); 
		xydataset.addSeries("points", data);
		JFreeChart chart=ChartFactory.createScatterPlot("img", "x", "y", xydataset);
		add(new ChartPanel(chart));
	}
}
