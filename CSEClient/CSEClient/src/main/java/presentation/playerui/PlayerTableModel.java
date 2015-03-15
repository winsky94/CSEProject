package presentation.playerui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class PlayerTableModel extends AbstractTableModel{

	/**
	 * 表格球员信息列表模式model
	 */
	String[] all,average;//总数据  场均数据
	ArrayList<ArrayList<String>> content=new ArrayList<ArrayList<String>>();
	private static final long serialVersionUID = 1L;
	
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return null;
	}

}
