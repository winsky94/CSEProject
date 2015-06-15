package newui.statsui;

import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.text.Keymap;

import newui.FatherPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;

import data.PlayerStatistic;

public class SalaryPanel extends FatherPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel titleLbl;
	ChartPanel chartPnl;
	JScrollPane hjsp, fjsp;
	JTable htable, ftable;
	HuiGuiTableModel htm;
	FangChaTableModel ftm;
	PlayerStatistic ps;
	public SalaryPanel() {
		ps = new PlayerStatistic();
		titleLbl = new JLabel("球员薪水与在场表现的回归分析");
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
		gbc.gridheight = 4;
		gbc.weightx = 10;
		gbc.weighty = 4;
		gbl.setConstraints(chartPnl, gbc);
		add(chartPnl);
		// ----------------------
		htm = new HuiGuiTableModel();
		htable = new JTable(htm);
		hjsp = new JScrollPane(htable);
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 10;
		gbc.gridheight = 3;
		gbc.weightx = 10;
		gbc.weighty = 3;
		gbl.setConstraints(hjsp, gbc);
		add(hjsp);
		// ----------------------
		
		ftm = new FangChaTableModel(ps.getVariance());
		ftable = new JTable(ftm);
		fjsp = new JScrollPane(ftable);
		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.gridwidth = 10;
		gbc.gridheight =3;
		gbc.weightx = 10;
		gbc.weighty = 3;
		gbl.setConstraints(fjsp, gbc);
		add(fjsp);
	}

	JFreeChart makeChart() {
		
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
		JFreeChart chart = ChartFactory.createScatterPlot("Linear Regression", "", "",
				xydataset, PlotOrientation.VERTICAL, false, false, false);
		return chart;
	}
}
