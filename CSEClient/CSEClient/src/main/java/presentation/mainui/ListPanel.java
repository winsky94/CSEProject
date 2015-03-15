package presentation.mainui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import presentation.playerui.PlayerTableModel;
import presentation.teamui.TableModel.TeamTableModel;

/*
 * 列表模式下的表格所在JPanel
 * 考虑panel复用   通过参数值 决定表头样式及表格数据Refresh方法的具体调用
 * 通过new ListPanel添加到ListModelFPanel的下方 完成列表模式
 * 
 * 根据选择的筛选排序条件 将条件列置前
 * */
public class ListPanel extends JPanel{
	private int model;//模式 0球队，1球员
	private JTable table;
	private JScrollPane jsp;
	private AbstractTableModel tablemodel;
	public ListPanel(int mod){
		this.model=mod;
		if(mod==0)
			tablemodel=new TeamTableModel();
		else
			tablemodel=new PlayerTableModel();
		
		table=new JTable(tablemodel);
		jsp=new JScrollPane(table);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		setLayout(null);
		add(jsp);
		jsp.setBounds(Scale.LISTTABLE);
	}

}
