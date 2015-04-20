package newui.hotui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import newui.Style;
import newui.tables.HotTableModel;
import newui.tables.MyTableCellRenderer;
import vo.PlayerVO;
import bl.player.Player;
import blservice.PlayerBLService;

public class ProgressPanel extends HotFatherPanel implements MouseListener {
	private static final long serialVersionUID = 1L;
	// ---bestPnl------------
	JLabel bestHead, bestName, bestTeamIcon, positionAndTeamName, data;
	// --剩余四人的表格---------
	/**
	 * 需要新的表格及TableModel jsp已经在HotFatherPanel里建好了，这里只要table和tableModel
	 * 表头：排名（2，3，4，5），头像，球员名，所属球队，位置，最近五场数据，提升率
	 */
	// ------bottomBar-----
	BottomButton scoreBtn, reboundBtn, assistBtn, currentBtn;
	PlayerBLService player;
	ProgressModel model;
	JTable table;
	ArrayList<PlayerVO> vlist;
	String[] head = { "排名", "", "球员名称", "所属球队", "位置", "场均得分", "提升率" };

	public ProgressPanel() {
		player = new Player();
		GridBagLayout bl = new GridBagLayout();
		GridBagConstraints bc = new GridBagConstraints();
		bc.fill = GridBagConstraints.BOTH;
		bestPnl.setLayout(bl);
		// -------bestPnl--------------
		bestHead = new JLabel();
		// 有需要就加上bestHead.setPreferredSize(new Dimension(width, height));
		bc.gridx = 0;
		bc.gridy = 0;
		bc.gridwidth = 2;
		bc.gridheight = 5;
		bc.weightx = 2;
		bc.weighty = 5;
		bl.setConstraints(bestHead, bc);
		bestPnl.add(bestHead);
		// ------------
		JPanel midPnl = new JPanel();
		midPnl.setOpaque(false);
		bc.gridx = 2;
		bc.gridwidth = 5;
		bc.weightx = 5;
		bl.setConstraints(midPnl, bc);
		bestPnl.add(midPnl);
		midPnl.setLayout(new GridLayout(2, 1));
		bestName = new JLabel();
		bestName.setHorizontalAlignment(JLabel.CENTER);
		bestName.setFont(new Font("微软雅黑", Font.PLAIN, 28));
		midPnl.add(bestName);
		positionAndTeamName = new JLabel();
		positionAndTeamName.setHorizontalAlignment(JLabel.CENTER);
		positionAndTeamName.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		midPnl.add(positionAndTeamName);
		// -------------
		data = new JLabel("40/120%");
		data.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		data.setForeground(Style.BACK_GREY);
		bc.gridx = 7;
		bc.gridwidth = 1;
		bc.weightx = 8;
		bl.setConstraints(data, bc);
		bestPnl.add(data);
		// ------------
		bestTeamIcon = new JLabel();
		bc.gridx = 8;
		bc.gridwidth = 2;
		bc.weightx = 2;
		bl.setConstraints(bestTeamIcon, bc);
		bestPnl.add(bestTeamIcon);
		// ------------
		bottomBar.setLayout(new GridLayout(1, 8));
		// ------------
		scoreBtn = new BottomButton("场均得分");
		scoreBtn.setBackground(Style.HOT_PURPLEFOCUS);
		scoreBtn.addMouseListener(this);
		bottomBar.add(scoreBtn);
		currentBtn = scoreBtn;
		// -----------
		reboundBtn = new BottomButton("场均篮板");
		reboundBtn.setBackground(Style.HOT_PURPLE);
		reboundBtn.addMouseListener(this);
		bottomBar.add(reboundBtn);
		// -----------
		assistBtn = new BottomButton("场均助攻");
		assistBtn.setBackground(Style.HOT_PURPLE);
		assistBtn.addMouseListener(this);
		bottomBar.add(assistBtn);
		model = new ProgressModel(head);
		table = new JTable(model);

		// table 渲染器，设置文字内容居中显示，设置背景色等
		table.setSelectionBackground(new Color(225, 255, 255));// 设置选择行的颜色——淡蓝色
		table.setFont(new Font("微软雅黑", 0, 12));
		table.getTableHeader().setFont(new Font("微软雅黑", 0, 14));
		table.getTableHeader().setBackground(new Color(211, 211, 211));
		DefaultTableCellRenderer tcr = new MyTableCellRenderer();
		table.getColumn(table.getColumnName(0)).setCellRenderer(tcr);
		for (int i = 2; i < table.getColumnCount(); i++) {
			table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
		}

		jsp.getViewport().add(table);
	}

	public void Refresh(String sort) {
		vlist = player.getBestImprovedPlayer(sort, 5);
		if (vlist != null && vlist.size() != 0) {
			model.setHead(head);
			PlayerVO topOne = vlist.get(0);
			bestHead.setIcon(new ImageIcon("image/player/portrait/"
					+ topOne.getName() + ".png"));
			bestName.setText(topOne.getName());
			positionAndTeamName.setText(topOne.getPosition() + "/"
					+ topOne.getOwingTeam());
			// data.setText(topOne.getScore()+"");
			bestTeamIcon.setIcon(new ImageIcon("image/teamIcon/teamsPng150/"
					+ topOne.getOwingTeam() + ".png"));
			if (currentBtn == scoreBtn)
				data.setText(topOne.getScore() + "/"
						+ topOne.getRecentFiveMatchesScoreUpRate() * 100 + "%");
			else if (currentBtn == reboundBtn)
				data.setText(topOne.getReboundNum() + "/"
						+ topOne.getRecentFiveMatchesReboundUpRate() * 100
						+ "%");
			else
				data.setText(topOne.getAssistNum() + "/"
						+ topOne.getRecentFiveMatchesAssistUpRate() * 100 + "%");
			model.Refresh(vlist);
			table.revalidate();
			jsp.getViewport().remove(table);
			table = new JTable(model);

			// table 渲染器，设置文字内容居中显示，设置背景色等
			table.setSelectionBackground(new Color(225, 255, 255));// 设置选择行的颜色——淡蓝色
			table.setFont(new Font("微软雅黑", 0, 12));
			table.getTableHeader().setFont(new Font("微软雅黑", 0, 14));
			table.getTableHeader().setBackground(new Color(211, 211, 211));
			DefaultTableCellRenderer tcr = new MyTableCellRenderer();
			table.getColumn(table.getColumnName(0)).setCellRenderer(tcr);
			for (int i = 2; i < table.getColumnCount(); i++) {
				table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
			}

			jsp.getViewport().add(table);
			jsp.repaint();
		}
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		currentBtn.setBackground(Style.HOT_PURPLE);
		BottomButton m = (BottomButton) e.getSource();
		PlayerVO v = vlist.get(0);
		if (m == scoreBtn) {
			head[5] = "场均得分";
			currentBtn = scoreBtn;
			Refresh("recentFiveMatchesScoreUpRate");
			// data.setText(v.getScore()+"/"+v.getRecentFiveMatchesScoreUpRate()*100+"%");
		} else if (m == reboundBtn) {
			head[5] = "场均篮板";
			currentBtn = reboundBtn;
			Refresh("recentFiveMatchesReboundUpRate");
			// data.setText(v.getReboundNum()+"/"+v.getRecentFiveMatchesReboundUpRate()*100+"%");
		} else {
			head[5] = "场均助攻";
			currentBtn = assistBtn;
			Refresh("recentFiveMatchesAssistUpRate");
			// data.setText(v.getAssistNum()+"/"+v.getRecentFiveMatchesAssistUpRate()*100+"%");
		}

		currentBtn.setBackground(Style.HOT_PURPLEFOCUS);
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		if (e.getSource().getClass() == BottomButton.class) {
			BottomButton btn = (BottomButton) e.getSource();
			btn.setBackground(Style.HOT_PURPLEFOCUS);
		}

	}

	public void mouseExited(MouseEvent e) {
		if (e.getSource().getClass() == BottomButton.class) {
			BottomButton btn = (BottomButton) e.getSource();
			if (currentBtn != btn)
				btn.setBackground(Style.HOT_PURPLE);
		}

	}

	class ProgressModel extends HotTableModel {
		public ProgressModel(String[] head) {
			super(head);
		}

		public void Refresh(ArrayList<PlayerVO> vlist) {
			content.clear();
			num = 2;
			for (int i = 1; i < vlist.size(); i++) {
				PlayerVO v = vlist.get(i);
				ArrayList<Object> line = new ArrayList<Object>();
				line.add(num);
				num++;
				ImageIcon tou = new ImageIcon("image/player/portrait/"
						+ v.getName() + ".png");
				// 设置宽高
				ImageIcon icon = new ImageIcon(
						tou.getImage()
								.getScaledInstance(
								/*
								 * table.getColumn(table.getColumnName(0))
								 * .getWidth()
								 */100, 80
								/* currentTable.getRowHeight(i) */,
										Image.SCALE_DEFAULT));

				line.add(icon);
				line.add(v.getName());
				line.add(v.getOwingTeam());
				line.add(v.getPosition());
				if (currentBtn == scoreBtn) {
					line.add(v.getScore());
					line.add(v.getRecentFiveMatchesScoreUpRate() * 100 + "%");
				} else if (currentBtn == reboundBtn) {
					line.add(v.getReboundNum());
					line.add(v.getRecentFiveMatchesReboundUpRate() * 100 + "%");
				} else {
					line.add(v.getAssistNum());
					line.add(v.getRecentFiveMatchesAssistUpRate() * 100 + "%");
				}
				content.add(line);
			}
		}
	}
}
