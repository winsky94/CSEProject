package newui.playerui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import newui.FatherPanel;
import newui.Service;
import newui.Style;
import newui.playerui.details.PlayerDetailHistoryPanel;
import newui.playerui.details.PlayerDetailInfoPanel;
import newui.playerui.details.PlayerPKPanel;
import newui.playerui.details.PlayerStatsPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import vo.PlayerVO;
import blService.PlayerBLService;

public class PlayerDetailPanel extends FatherPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	JLabel actionPicLbl;
	JTabbedPane tab;
	PlayerBLService pservice;
	PlayerVO vo;
	ChartPanel spiderPnl;

	public PlayerDetailPanel(String pname) {
		isDetail = true;
		pservice = Service.player;// is this?
		// pservice=p;
		vo = pservice.getPlayerBaseInfo(pname).get(0);

		name = pname;
		// -------spiderPnl-----------
		spiderPnl = new ChartPanel(createChart());
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 3;
		gbc.weightx = 0.1;
		gbc.weighty = 30;
		gbl.setConstraints(spiderPnl, gbc);
		add(spiderPnl);
		// ------actionPicLbl--------

		String actionIconName = name;
		File file = new File("image/player/action/" + actionIconName + ".png");
		if (!file.exists())
			actionIconName = "unknown";
		ImageIcon i = new ImageIcon("image/player/action/" + actionIconName
				+ ".png");
		i.setImage(i.getImage().getScaledInstance(300, 478, Image.SCALE_SMOOTH));
		actionPicLbl = new JLabel(i);
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.gridheight = 7;
		gbc.weightx = 0.1;
		gbc.weighty = 70;
		gbl.setConstraints(actionPicLbl, gbc);
		add(actionPicLbl);
		// ----------------
		tab = new JTabbedPane();
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridheight=10;
		gbc.gridwidth = 9;
		gbc.weightx = 500;
		gbc.weighty=100;
		gbl.setConstraints(tab, gbc);
		add(tab);
		tab.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		tab.setBackground(Color.white);
		tab.setForeground(Style.DEEP_BLUE);
		double pre = System.currentTimeMillis();
		tab.addTab("基本信息", new PlayerDetailInfoPanel(vo));
		double pp = System.currentTimeMillis();
		System.out.println("detaibaseinfo:" + (pp - pre));
		tab.addTab("过往数据", new PlayerDetailHistoryPanel(vo));
		double post = System.currentTimeMillis();
		System.out.println("detailhistory:" + (post - pp));
		/**
		 * 2015.5.30 新增图表分析标签页
		 */
		tab.addTab("图表分析", new PlayerStatsPanel(name));
		tab.addTab("球员对比", new PlayerPKPanel(vo));
	}

	JFreeChart createChart() {
//		pservice.getPlayerAverageInfo(season, "all", name);
//		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//		dataset.addValue(value, rowKey, columnKey);
		return null;
	}
}
