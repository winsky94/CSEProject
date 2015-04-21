package newui.teamui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import newui.FatherPanel;
import newui.Style;
import newui.TableModel;
import newui.mainui.MainFrame;
import newui.tables.MySortableTable;
import newui.tables.MyTableCellRenderer;
import newui.tables.RowHeaderTable;
import newui.tables.TeamTableModel;
import vo.TeamVO;
import bl.team.Team;
import blservice.TeamBLService;

public class TeamIndexPanel extends FatherPanel implements MouseListener
		 {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel funcPnl;
	//
	JScrollPane jsp;
	JTable table;
	TeamTableModel ttm;
	//
	JLabel refreshLbl, fieldLbl;
	JComboBox<String> seasonBox, typeBox;
	Font font = new Font("微软雅黑", Font.PLAIN, 13);
	boolean isHighInfo = false;

	public TeamIndexPanel() {
		super();
		// ------funcPnl--------
		funcPnl = new JPanel();
		funcPnl.setBackground(Style.BACK_GREY);
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.weighty = 0.1;
		gbl.setConstraints(funcPnl, gbc);
		add(funcPnl);
		// -----season----------
		JLabel seasonBoxLbl = new MyJLabel("赛季：");
		funcPnl.add(seasonBoxLbl);
		// 暂时没有bl方法
		String[] seasonBoxText = { "13-14" };
		seasonBox = new MyComboBox(seasonBoxText);
		//seasonBox.addItemListener(this);
		funcPnl.add(seasonBox);
		funcPnl.add(new JLabel("       "));
		// ----DataType---------
		JLabel typeLbl = new MyJLabel("数据类型：");
		funcPnl.add(typeLbl);
		String[] typeText = { "赛季", "场均" };
		typeBox = new MyComboBox(typeText);
		//typeBox.addItemListener(this);
		funcPnl.add(typeBox);
		funcPnl.add(new JLabel("       "));
		// ------fieldLbl--------
		fieldLbl = new MyJLabel("查看高阶数据");
		fieldLbl.addMouseListener(this);
		fieldLbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		funcPnl.add(fieldLbl);
		// -----refreshLbl------
		refreshLbl = new MyJLabel("刷新", new ImageIcon("image/refreshWhite.png"));
		refreshLbl.addMouseListener(this);
		refreshLbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		funcPnl.add(refreshLbl);
		// -----table-----------
		ttm = new TeamTableModel(0);
		table = new MySortableTable(ttm, 1);

		// table 渲染器，设置文字内容居中显示，设置背景色等
	
		table.addMouseListener(this);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		titleBar.setCurrentTableModel(ttm);
		titleBar.setModelEnum(TableModel.TEAMRANK);
		titleBar.setTable(table);
		jsp = new JScrollPane(table);

		gbc.gridy = 2;
		gbc.gridheight = 10;
		gbc.weighty = 10;
		gbl.setConstraints(jsp, gbc);
		add(jsp);

		// ====初始化数据=====
		ttm.Refresh(typeBox.getSelectedItem().toString());
		table.revalidate();
		CellRender();
		MyTableCellRenderer.adjustTableColumnWidths(table);// 自动设置列宽
		// 设置表头颜色
		

		// 设置显示行号
		jsp.setRowHeaderView(new RowHeaderTable(table, 20));

		JLabel jb = new JLabel();
		jb.setOpaque(true);
		jb.setBackground(Color.black);
		jb.setBorder(BorderFactory.createLineBorder(new Color(158, 158, 158),
				20));
		jsp.setCorner(JScrollPane.UPPER_RIGHT_CORNER, jb);
		titleBar.setSeason(seasonBox.getSelectedItem().toString());
		titleBar.setAveOrAll(typeBox.getSelectedItem().toString());

		typeBox.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				String type = typeBox.getSelectedItem().toString();
				ttm.Refresh(type);
				table.revalidate();
				// table.repaint();

			}

		});

	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

		if (e.getSource() == refreshLbl) {
			ttm.Refresh(typeBox.getSelectedItem().toString());
			table.revalidate();
		}

		if (e.getSource() == table) {
			if (e.getClickCount() == 2) {
				int row = table.getSelectedRow();
				String tname = table.getValueAt(row, 1).toString();
				MainFrame.getInstance().setContentPanel(
						new TeamDetailPanel(tname));
			}
		}
		if (e.getSource() == fieldLbl) {
			String type = typeBox.getSelectedItem().toString();
			if (isHighInfo) {
				// 监听,切换到基础数据表格
				fieldLbl.setText("查看高阶数据");
				isHighInfo = false;
				ttm=new TeamTableModel(0);
				
			} else {
				// 监听,切换到高阶数据表格
				fieldLbl.setText("查看基础数据");
				isHighInfo = true;
				ttm=new TeamTableModel(1);
				
			}
			ttm.Refresh(type);
			table.revalidate();;
			jsp.remove(table);
			table=new JTable(ttm);
			
			jsp.getViewport().add(table);
			CellRender();
		}

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
		}
		if (e.getSource() == fieldLbl) {
			fieldLbl.setForeground(Style.FOCUS_BLUE);
		}
	}

	public void mouseExited(MouseEvent e) {
		if (e.getSource() == refreshLbl) {
			refreshLbl.setForeground(Color.white);
			refreshLbl.setIcon(new ImageIcon("image/refreshWhite.png"));
		}
		if (e.getSource() == fieldLbl) {
			fieldLbl.setForeground(Color.white);
		}
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

	public void CellRender(){
		table.setSelectionBackground(new Color(225, 255, 255));// 设置选择行的颜色——淡蓝色
		table.setFont(new Font("微软雅黑", 0, 12));
		table.getTableHeader().setFont(new Font("微软雅黑", 0, 14));
		table.getTableHeader().setBackground(new Color(211, 211, 211));
		DefaultTableCellRenderer tcr = new MyTableCellRenderer();
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
		}
		table.getTableHeader().setBackground(new Color(158, 158, 158));
	}
}
