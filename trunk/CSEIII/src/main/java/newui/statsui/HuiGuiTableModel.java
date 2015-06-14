package newui.statsui;

import javax.swing.table.AbstractTableModel;

import data.PlayerStatistic;

public class HuiGuiTableModel extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[][] content=new String[2][4];
	String[] title={"效率值","GmSc效率值","得分-篮板-助攻"};
	public HuiGuiTableModel() {
		PlayerStatistic ps=new PlayerStatistic();
		
	}
	@Override
	public int getRowCount() {
		return 2;
	}

	@Override
	public int getColumnCount() {
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return content[rowIndex][columnIndex];
	}

}
