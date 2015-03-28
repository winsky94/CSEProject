package newui.tables;

import java.util.ArrayList;

public class TeamBaseInfoTableModel extends MyTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static String[] head ={"(logo)","球队","缩写","联盟","分区","主场","成立时间"};
	ArrayList<ArrayList<Object>> content = new ArrayList<ArrayList<Object>>();
	public int getRowCount() {
		return content.size();
	}

	public int getColumnCount() {
		return head.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return content.get(rowIndex).get(columnIndex);
	}

	public String getColumnName(int col) {
		return head[col];
	}

	public static String[] getHead() {
		return head;
	}
}
