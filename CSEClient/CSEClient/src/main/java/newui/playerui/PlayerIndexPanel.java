package newui.playerui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import newui.FatherPanel;
import newui.Style;
import newui.mainui.MainFrame;
import newui.tables.PlayerBaseInfoTableModel;

public class PlayerIndexPanel extends FatherPanel implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel funcPnl;
	MyLabel refreshLbl,modeLbl;
	String[] teams = { "按球队查找", "掘金", "森林狼", "雷霆", "开拓者", "爵士", "勇士", "快船",
			"湖人", "太阳", "国王", "小牛", "火箭", "灰熊", "鹈鹕", "马刺", "凯尔特人", "篮网",
			"尼克斯", "76人", "猛龙", "公牛", "骑士", "活塞", "步行者", "雄鹿", "老鹰", "黄蜂",
			"热火", "魔术", "奇才" };
	// --------------
	JScrollPane jsp;
	JTable table;
	PlayerBaseInfoTableModel pitm = new PlayerBaseInfoTableModel();
	// ---------------
	Font font = new Font("微软雅黑", Font.PLAIN, 15);
	// ---------------
	ArrayList<MyCharacter> characterLblList = new ArrayList<MyCharacter>(26);
	JComboBox<String> teamBox;

	public PlayerIndexPanel() {
		super();
		// ------funcPnl--------
		funcPnl = new JPanel();
		funcPnl.setBackground(Style.BACK_GREY);
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.weighty = 0.8;
		gbl.setConstraints(funcPnl, gbc);
		add(funcPnl);
		FlowLayout fl = new FlowLayout(FlowLayout.LEFT, 15, 0);
		funcPnl.setLayout(fl);
		// -----A至Z按钮----------
		for (int i = 0; i < 26; i++) {
			MyCharacter temp = new MyCharacter(i);
			characterLblList.add(temp);
		}
		for (int i = 0; i < 26; i++)
			funcPnl.add(characterLblList.get(i));
		// ----按球队查看---------
		funcPnl.add(new JLabel("       "));
		teamBox = new JComboBox<String>(teams);
		teamBox.setBackground(Style.BACK_GREY);
		teamBox.setForeground(Color.white);
		teamBox.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		teamBox.setMaximumRowCount(20);
		funcPnl.add(teamBox);
		// -----刷新--------------
		refreshLbl = new MyLabel("刷新", new ImageIcon("image/refreshWhite.png"));
		funcPnl.add(refreshLbl);
		//------切换至列表排名模式----
		modeLbl=new MyLabel("至排名模式",new ImageIcon("image/player/rankmode.png"));
		funcPnl.add(modeLbl);
		// ----jsp--------------
		table = new JTable(pitm);
		jsp = new JScrollPane(table);
		gbc.gridy = 2;
		gbc.gridheight = 10;
		gbc.weighty = 10;
		gbl.setConstraints(jsp, gbc);
		add(jsp);

	}

	class MyCharacter extends JLabel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public MyCharacter(int i) {
			char character = (char) ('A' + i);
			String text = " " + character + " ";
			setText(text);
			setForeground(Color.white);
			setFont(font);
			addMouseListener(PlayerIndexPanel.this);
		}
	}
	class MyLabel extends JLabel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public MyLabel(String text,ImageIcon img){
			super(text,img,JLabel.LEFT);
			setFont(new Font("微软雅黑", Font.PLAIN, 13));
			setForeground(Color.white);
			addMouseListener(PlayerIndexPanel.this);
		}
		
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==modeLbl)
			MainFrame.getInstance().setContentPanel(new PlayerRankPanel());

	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		if (e.getSource().getClass() == MyCharacter.class) {
			MyCharacter lbl = (MyCharacter) e.getSource();
			lbl.setOpaque(true);
			lbl.setBackground(Style.FOCUS_GREY);
			lbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		if (e.getSource() == refreshLbl) {
			refreshLbl.setForeground(Style.FOCUS_BLUE);
			refreshLbl.setIcon(new ImageIcon("image/refreshFocus.png"));
			refreshLbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		if (e.getSource() == modeLbl) {
			modeLbl.setForeground(Style.FOCUS_BLUE);
			modeLbl.setIcon(new ImageIcon("image/player/rankmodeBlue.png"));
			modeLbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
	}

	public void mouseExited(MouseEvent e) {
		if (e.getSource().getClass() == MyCharacter.class) {
			MyCharacter lbl = (MyCharacter) e.getSource();
			lbl.setOpaque(false);
			lbl.setBackground(Style.BACK_GREY);
			lbl.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		if (e.getSource() == refreshLbl) {
			refreshLbl.setForeground(Color.white);
			refreshLbl.setIcon(new ImageIcon("image/refreshWhite.png"));
			refreshLbl.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		if (e.getSource() == modeLbl) {
			modeLbl.setForeground(Color.white);
			modeLbl.setIcon(new ImageIcon("image/player/rankmode.png"));
			modeLbl.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}
}
