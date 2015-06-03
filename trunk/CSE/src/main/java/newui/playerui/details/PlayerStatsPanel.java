package newui.playerui.details;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import newui.Style;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.AbstractCategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import vo.MatchVO;
import vo.RecordVO;
import bl.player.Player;
import blservice.PlayerBLService;

public class PlayerStatsPanel extends JPanel {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	// ---------------
	JPanel funcPnl, bottomPnl;
	JPanel infoPnl;
	ChartPanel chartPnl;
	// ----------------
	JComboBox<String> itemBox;
	MyLabel typeLbl;
	//
	Font font = new Font("微软雅黑", Font.PLAIN, 15);
	boolean isCurrent;// 是否为当前赛季图表
	PlayerBLService player;
	String name;

	//
	public PlayerStatsPanel(String pname) {
		player = new Player();
		name = pname;
		isCurrent = true;
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		setLayout(gbl);
		setBackground(Color.white);
		// --------上面的条状pnl----------
		funcPnl = new JPanel();
		funcPnl.setOpaque(false);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 10;
		gbc.gridheight = 1;
		gbc.weightx = 10;
		gbc.weighty = 0.1;
		gbl.setConstraints(funcPnl, gbc);
		add(funcPnl);
		// --------------------
		JLabel itemLbl = new MyLabel("项目：");
		funcPnl.add(itemLbl);
		String[] items = { "得分", "篮板", "助攻", "三分%", "罚球%" };
		itemBox = new MyBox(items);
		funcPnl.add(itemBox);
		itemBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chartPnl.setChart(createChart(
						(String) itemBox.getSelectedItem(), isCurrent));
			}
		});
		JLabel blank = new JLabel("                      ");
		funcPnl.add(blank);
		//
		typeLbl = new MyLabel("查看过往数据");
		funcPnl.add(typeLbl);
		typeLbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		typeLbl.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				typeLbl.setForeground(Style.DEEP_BLUE);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				typeLbl.setForeground(Style.FOCUS_BLUE);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (isCurrent) {
					typeLbl.setText("查看最近数据");
					isCurrent = false;
					chartPnl.setChart(createChart(
							(String) itemBox.getSelectedItem(), isCurrent));

				} else {
					typeLbl.setText("查看过往数据");
					isCurrent = true;
					chartPnl.setChart(createChart(
							(String) itemBox.getSelectedItem(), isCurrent));
				}

			}
		});
		// ---------------------------
		bottomPnl = new JPanel();
		bottomPnl.setOpaque(false);
		bottomPnl.setLayout(new GridLayout(2, 1));
		gbc.gridy = 1;
		gbc.gridheight = 10;
		gbc.weighty = 10;
		gbl.setConstraints(bottomPnl, gbc);
		add(bottomPnl);
		// ---绘图---------------
		chartPnl = new ChartPanel(createChart(
				(String) itemBox.getSelectedItem(), isCurrent));
		bottomPnl.add(chartPnl);
		// ---数据-----------------
		infoPnl = new JPanel();
		bottomPnl.add(infoPnl);
		// -----------------------

	}

	private JFreeChart createChart(String item, boolean isCurrent) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		// -----------------------------------------------------------------------
		// -----------------------------------------------------------------------
		if (isCurrent) {
			// 得到最近最多10场比赛的MatchVO
			ArrayList<MatchVO> matches = player.getRecentMatches(name, 10);
			int size = matches.size();
			// 解析出该球员的Record，存入数组,index越大，日期越远离今天
			ArrayList<RecordVO> record = new ArrayList<RecordVO>();
			String date[] = new String[size];
			for (int i = 0; i < size; i++) {
				date[i] = matches.get(i).getDate();
				ArrayList<RecordVO> temp = matches.get(i).getRecords();
				for (int j = 0; j < temp.size(); j++) {
					RecordVO tempRecord = temp.get(j);
					if (tempRecord.getPlayerName().equals(name))
						record.add(tempRecord);
				}
			}
			if (item.equals("得分")) {
				for (int i = 0; i < size; i++) {
					RecordVO r = record.get(i);
					dataset.addValue(r.getScore(), name, date[i]);
				}
				return makeChart("最近得分", "日期","得分", dataset);
			} else if (item.equals("篮板")) {
				for (int i = 0; i < size; i++) {
					RecordVO r = record.get(i);
					dataset.addValue(r.getReboundNum(), name, date[i]);
				}
				return makeChart("最近篮板", "日期","篮板数", dataset);
			} else if (item.equals("助攻")) {
				for (int i = 0; i < size; i++) {
					RecordVO r = record.get(i);
					dataset.addValue(r.getAssistNum(), name, date[i]);
				}
				return makeChart("最近助攻", "日期","助攻数", dataset);
			} else if (item.equals("三分%")) {
				for (int i = 0; i < size; i++) {
					RecordVO r = record.get(i);
					double rate = 0.0;
					if (r.getThreeAttemptNum() != 0.0)
						rate = (r.getThreeHitNum() + 0.0)
								/ (r.getThreeAttemptNum() + 0.0);
					dataset.addValue(rate, name, date[i]);
				}
				return makeChart("最近三分%", "日期","三分%", dataset);
			} else if (item.equals("罚球%")) {
				for (int i = 0; i < size; i++) {
					RecordVO r = record.get(i);
					double rate = 0.0;
					if (r.getFreeThrowAttemptNum() != 0.0)
						rate = (r.getFreeThrowHitNum() + 0.0)
								/ (r.getFreeThrowAttemptNum() + 0.0);
					dataset.addValue(rate, name, date[i]);
				}
				return makeChart("最近罚球%", "日期", "罚球%", dataset);
			}
		}
		// ---------------------------------------------------------------
		// ---------------------------------------------------------------
		else {

		}
		return null;
	}

	private JFreeChart makeChart(String title, String categoryAxisLabel,
			String valueAxisLabel, CategoryDataset dataset) {
		StandardChartTheme standardChartTheme = new StandardChartTheme("CN");
		standardChartTheme.setExtraLargeFont(new Font("微软雅黑", Font.PLAIN, 15));
		// 设置图例的字体
		standardChartTheme.setRegularFont(new Font("微软雅黑", Font.PLAIN, 15));
		// 设置轴向的字体
		standardChartTheme.setLargeFont(new Font("微软雅黑", Font.PLAIN, 15));
		// 应用主题样式
		ChartFactory.setChartTheme(standardChartTheme);
		JFreeChart chart = ChartFactory.createLineChart(title,
				categoryAxisLabel, valueAxisLabel, dataset,
				PlotOrientation.VERTICAL, false, false, false);
		CategoryPlot plot = chart.getCategoryPlot();
		// 设置折线图背景颜色
		// plot.setBackgroundPaint(Style.HOT_YELLOWFOCUS);
		AbstractCategoryItemRenderer renderer = (AbstractCategoryItemRenderer) plot
				.getRenderer();
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBaseItemLabelFont(new Font("微软雅黑", Font.PLAIN, 10));
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		ValueAxis rangeAxis = plot.getRangeAxis();
		rangeAxis.setUpperMargin(0.1);
		return chart;
	}

	class MyBox extends JComboBox<String> {

		private static final long serialVersionUID = 1L;

		public MyBox(String[] arr) {
			super(arr);
			setFont(font);
			this.setBackground(Color.white);
			this.setForeground(Style.DEEP_BLUE);
		}
	}

	class MyLabel extends JLabel {

		private static final long serialVersionUID = 1L;

		public MyLabel(String text) {
			super(text);
			setFont(font);
			setForeground(Style.DEEP_BLUE);
		}
	}
}
