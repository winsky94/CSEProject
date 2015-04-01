package newui.tables;

import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class MySortableTable extends JTable {

	/**
	 * 计划让所有需要排序的表格都继承此类
	 */
	private static final long serialVersionUID = 1L;
//	TableRowSorter<TableModel> sorter;
	TableSorter sorter;

	public MySortableTable(TableModel model) {
		super(model);
		setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		sorter = new TableSorter(model);
		setRowSorter(sorter);
	}

}
