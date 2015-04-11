package newui.tables;

import java.util.ArrayList;

public class TeamHistoryTableModel extends MyTableModel{
	static String[] head = { "球员","出场", "首发", "时间", "投篮", "三分", "罚球","前篮板",
		"后篮板", "总篮板","助攻","抢断","盖帽","失误","犯规","得分" };
	//最后一行有统计
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
	
	public void Refresh(String teamName){
		
	}
}
