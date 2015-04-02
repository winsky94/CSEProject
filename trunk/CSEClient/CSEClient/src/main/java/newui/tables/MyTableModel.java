package newui.tables;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel{

	/**
	 * 
	 */
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
	public Class getColumnClass(int column) {
		Class returnValue;
		if ((column >= 0) && (column < getColumnCount())) {
			returnValue = getValueAt(0, column).getClass();
		} else {
			returnValue = Object.class;
		}
		//System.out.println(returnValue);
		return returnValue;
	}
	//====更新数据=====0汇总，1场均
	public void Refresh(String model){
		
	}
	
	

}
