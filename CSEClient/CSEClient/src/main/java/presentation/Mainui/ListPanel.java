package presentation.Mainui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import presentation.MyTableCellRenderer;
import presentation.playerui.detail.PlayerInfoPanel;
import presentation.playerui.tablemodel.PlayerTableModel;
import presentation.teamui.tablemodel.TeamTableModel;

/*
 * 列表模式下的表格所在JPanel
 * 考虑panel复用   通过参数值 决定表头样式及表格数据Refresh方法的具体调用
 * 通过new ListPanel添加到ListModelFPanel的下方 完成列表模式
 * 
 * 根据选择的筛选排序条件 将条件列置前
 * */
public class ListPanel extends JPanel {
	private int model;// 模式 0球队，1球员
	private JTable table;
	private JScrollPane jsp;
	private MyTableModel tablemodel;

	public ListPanel(int mod) {
		this.model = mod;
		if (mod == 0)
			tablemodel = new TeamTableModel();
		else
			tablemodel = new PlayerTableModel();

		table = new JTable(tablemodel);

		// table 渲染器，设置文字内容居中显示，设置背景色等
		// table.setBackground(new Color(248,248,255));//设置背景颜色204, 204, 255
		// table.setForeground(new Color(128, 0, 0));//
		// 设置字体颜色，但标题不会改变——新设置的字体颜色丑死了，我还是注释掉吧
		table.setSelectionBackground(new java.awt.Color(218, 112, 214));// 设置选择行的颜色——兰花紫
		table.setFont(new Font("微软雅黑", 0, 12));
		table.getTableHeader().setFont(new Font("微软雅黑", 0, 14));
		table.getTableHeader().setBackground(new Color(211, 211, 211));
		DefaultTableCellRenderer tcr = new MyTableCellRenderer();
		for (int i = 0; i < table.getColumnCount(); i++) {
			table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
		}
		jsp = new JScrollPane(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		setLayout(null);
		add(jsp);
		jsp.setBounds(Scale.LISTTABLE);

		// ====双击显示详细信息===
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					Point p = e.getPoint();
					int row = table.rowAtPoint(p);// 获取所在行
					if (model == 1)// 查看球员详细信息
					{
						AddressBar.getInstance().RefreshAddress("球员查询》",
								(JPanel) ListPanel.this.getParent());
						MainFrame.getInstance().refresh(
								new PlayerInfoPanel(null));

					}

				}
			}
		});
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(
				tablemodel);
		table.setRowSorter(sorter);
		table.setOpaque(false);
		jsp.setOpaque(false);
		this.setOpaque(false);
		// =====表格美化====

		tablemodel.Refresh("汇总");
		table.revalidate();
		jsp.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon icon = new ImageIcon("img/main/tableback.jpg");
		g.drawImage(icon.getImage(), icon.getIconWidth(), icon.getIconHeight(),
				icon.getImageObserver());
	}

	public void filterRefresh(FilterCondition condition) {
		if (model == 1)
			((PlayerTableModel) tablemodel).Filter(condition);
		table.revalidate();
	}

}