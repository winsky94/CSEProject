package newui.playerui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import newui.FatherPanel;
import newui.Style;
import newui.mainui.MainFrame;
import newui.playerui.PlayerIndexPanel.MyLabel;
import newui.tables.MyTable;
import newui.tables.PlayerTableModel;

public class PlayerRankPanel extends FatherPanel implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel funcPnl;
	// --------------
	JScrollPane jsp;
	JTable table;
	//PlayerTableModel ptm = new PlayerTableModel();
	// ---------------
	JLabel refreshLbl, filterLbl,modeLbl;
	JComboBox<String> locationBox, partitionBox, filterRankBox, seasonBox,
			typeBox;
	Font font = new Font("微软雅黑", Font.PLAIN, 13);
	String[] locationText = { "全部", "前锋", "中锋", "后卫" };
	String[] partitionText = { "全部", "西部球队", "西北分区", "太平洋分区", "西南分区", "东部球队",
			"大西洋分区", "中央分区", "东南分区" };
	String[] filterRankText = { "得分", "篮板", "助攻", "得分/篮板/助攻(1:1:1)", "投篮",
			"盖帽", "抢断", "罚球", "犯规","失误","分钟","效率","两双" };

	public PlayerRankPanel() {
		// ------funcPnl--------
		funcPnl = new JPanel();
		funcPnl.setBackground(Style.BACK_GREY);
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.weighty = 0.8;
		gbl.setConstraints(funcPnl, gbc);
		FlowLayout fl=new FlowLayout(FlowLayout.LEFT);
		funcPnl.setLayout(fl);
		add(funcPnl);
		// -------筛选---------------------------------------
		// -----位置---------
		JLabel locationLbl = new MyJLabel("球员位置：");
		funcPnl.add(locationLbl);
		locationBox = new MyComboBox(locationText);
		funcPnl.add(locationBox);
		// -----分区---------
		JLabel partitionLbl = new MyJLabel("分区：");
		funcPnl.add(partitionLbl);
		partitionBox = new MyComboBox(partitionText);
		partitionBox.setMaximumRowCount(10);
		funcPnl.add(partitionBox);
		// ----排序条件--------
		JLabel filterRankLbl = new MyJLabel("排序条件：");
		funcPnl.add(filterRankLbl);
		filterRankBox = new MyComboBox(filterRankText);
		filterRankBox.setMaximumRowCount(13);
		funcPnl.add(filterRankBox);
		//-----filterLbl-----
		filterLbl=new MyJLabel("筛选",new ImageIcon("image/player/filterWhite.png"));
		filterLbl.addMouseListener(this);
		funcPnl.add(filterLbl);
		funcPnl.add(new JLabel("       "));
		// -----season----------
		JLabel seasonBoxLbl = new MyJLabel("赛季：");
		funcPnl.add(seasonBoxLbl);
		String[] seasonBoxText = { "我需要监听了啦" };
		seasonBox = new MyComboBox(seasonBoxText);
		funcPnl.add(seasonBox);
		// ----DataType---------
		JLabel typeLbl = new MyJLabel("数据类型：");
		funcPnl.add(typeLbl);
		String[] typeText = { "场均", "赛季" };
		typeBox = new MyComboBox(typeText);
		funcPnl.add(typeBox);
		funcPnl.add(new JLabel("       "));
		// -----refreshLbl------
		refreshLbl = new MyJLabel("刷新", new ImageIcon("image/refreshWhite.png"));
		refreshLbl.addMouseListener(this);
		funcPnl.add(refreshLbl);
		//-----modeLbl---------
		modeLbl=new MyJLabel("至快速查询模式",new ImageIcon("image/player/headmode.png"));
		modeLbl.addMouseListener(this);
		funcPnl.add(modeLbl);
		// ----jsp--------------
		table = new JTable();
		jsp = new JScrollPane(table);
		gbc.gridy = 2;
		gbc.gridheight = 10;
		gbc.weighty = 10;
		gbl.setConstraints(jsp, gbc);
		add(jsp);
	}

	class MyJLabel extends JLabel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public MyJLabel(String text) {
			super(text);
			setFont(font);
			setForeground(Color.white);
		}

		public MyJLabel(String text, ImageIcon img) {
			super(text, img, JLabel.CENTER);
			setFont(font);
			setForeground(Color.white);
		}

	}

	class MyComboBox extends JComboBox<String> {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public MyComboBox(String[] text) {
			super(text);
			setFont(font);
		}
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==modeLbl)
			MainFrame.getInstance().setContentPanel(new PlayerIndexPanel());

	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == refreshLbl) {
			refreshLbl.setForeground(Style.FOCUS_BLUE);
			refreshLbl.setIcon(new ImageIcon("image/refreshFocus.png"));
			refreshLbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		if(e.getSource()==filterLbl){
			filterLbl.setForeground(Style.FOCUS_BLUE);
			filterLbl.setIcon(new ImageIcon("image/player/filterFocus.png"));
			filterLbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		if (e.getSource() == modeLbl) {
			modeLbl.setForeground(Style.FOCUS_BLUE);
			modeLbl.setIcon(new ImageIcon("image/player/headmodeBlue.png"));
			modeLbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
	}

	public void mouseExited(MouseEvent e) {
		if (e.getSource() == refreshLbl) {
			refreshLbl.setForeground(Color.white);
			refreshLbl.setIcon(new ImageIcon("image/refreshWhite.png"));
			refreshLbl.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		if(e.getSource()==filterLbl){
			filterLbl.setForeground(Color.white);
			filterLbl.setIcon(new ImageIcon("image/player/filterWhite.png"));
			filterLbl.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		if (e.getSource() == modeLbl) {
			modeLbl.setForeground(Color.white);
			modeLbl.setIcon(new ImageIcon("image/player/headmode.png"));
			modeLbl.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}
}
