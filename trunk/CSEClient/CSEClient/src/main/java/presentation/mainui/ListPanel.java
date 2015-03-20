package presentation.mainui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

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
	private int model;//模式 0球队，1球员
	private JTable table;
	private JScrollPane jsp;
	private MyTableModel tablemodel;
	public ListPanel(int mod){
		this.model=mod;
		if(mod==0)
			tablemodel=new TeamTableModel();
		else
			tablemodel=new PlayerTableModel();
		
		table=new JTable(tablemodel){
			public Component prepareRender(TableCellRenderer renderer,int row,int column){
				Component c=super.prepareRenderer(renderer, row, column);
				if(c instanceof JComponent)
					((JComponent)c).setOpaque(false);
				
				return c;
			}
		};
		jsp=new JScrollPane(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		setLayout(null);
		add(jsp);
		jsp.setBounds(Scale.LISTTABLE);
		
		//====双击显示详细信息===
		table.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(e.getClickCount()==2){
					Point p=e.getPoint();
					int row=table.rowAtPoint(p);//获取所在行
					if(model==1)//查看球员详细信息
					{	
						AddressBar.getInstance().RefreshAddress("球员查询》", (JPanel)ListPanel.this.getParent());
						MainFrame.getInstance().refresh(new PlayerInfoPanel(null));
						
					
					}
					
				}
			}
		});
		TableRowSorter<TableModel> sorter=new TableRowSorter<TableModel>(tablemodel);
		table.setRowSorter(sorter);
		table.setOpaque(false);
		jsp.setOpaque(false);
		//=====表格美化====
	
		tablemodel.Refresh("汇总");
		table.revalidate();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		ImageIcon icon=new ImageIcon("img/main/tableback.jpg");
		g.drawImage(icon.getImage(),icon.getIconWidth(),icon.getIconHeight(),
				icon.getImageObserver());
	}
	
	
	public void filterRefresh(FilterCondition condition){
		if(model==1)
			((PlayerTableModel)tablemodel).Filter(condition);
		table.revalidate();
	}
	
	

}
