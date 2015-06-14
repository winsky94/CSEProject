package newui.statsui;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;

import newui.FatherPanel;
import newui.Service;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;

import blService.PlayerBLService;

public class AgePanel extends FatherPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel titleLbl;
	ChartPanel chartPnl;

	public AgePanel() {
		titleLbl = new JLabel("球龄与效率值的显著性分析");
		titleLbl.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 10;
		gbc.gridheight = 1;
		gbc.weightx = 10;
		gbc.weighty = 0.1;
		gbl.setConstraints(titleLbl, gbc);
		add(titleLbl);
		// --------------------
		chartPnl = new ChartPanel(makeChart());
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 10;
		gbc.gridheight = 8;
		gbc.weightx = 10;
		gbc.weighty = 8;
		gbl.setConstraints(chartPnl, gbc);
		add(chartPnl);
	}

	JFreeChart makeChart() {
		PlayerBLService player = Service.player;
		ArrayList<double[]> list = player.singleElementVarianceAnalysis();
		double[][] data = new double[2][list.size()];
		for (int i = 0; i < list.size(); i++) {
			data[0][i] = list.get(i)[0];
			data[1][i] = list.get(i)[1];
		}
		DefaultXYDataset xydataset = new DefaultXYDataset();
		xydataset.addSeries("points", data);
		JFreeChart chart = ChartFactory.createScatterPlot(
				"SingleElementVarianceAnalysis", "Exp", "Fa", xydataset,
				PlotOrientation.VERTICAL, false, false, false);
		return chart;
	}
}
