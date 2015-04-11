package newui.tables;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class PlayerHistoryTableModel extends AbstractTableModel{
	
	static String[] head = { "比赛","类型", "日期","首发", "时间", "投篮", "三分", "罚球","前篮板",
		"后篮板", "总篮板","助攻","抢断","盖帽","失误","犯规","得分" };

	ArrayList<ArrayList<Object>> content = new ArrayList<ArrayList<Object>>();
	public int getRowCount() {
		// TODO Auto-generated method stub
		return content.size();
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return head.length;
	}
	

	public Class getColumnClass(int column) {
		return content.get(0).get(column).getClass();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return content.get(rowIndex).get(columnIndex);
	}
	
	public void Refresh(String pname){
		
	}
	

}
