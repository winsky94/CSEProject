package newui.tables;

import java.util.ArrayList;

public class PlayerBaseInfoTableModel extends MyTableModel {

	/**
	 * 并没有写刷新的方法
	 */
	private static final long serialVersionUID = 1L;
	static String[] head = { "(头像)", "球员名", "所属球队", "位置", "身高", "体重","生日","年龄" ,"经验" };
	ArrayList<ArrayList<Object>> content = new ArrayList<ArrayList<Object>>();

	public PlayerBaseInfoTableModel() {

	}

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
