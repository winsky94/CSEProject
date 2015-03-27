package newui.tables;

import javax.swing.JTable;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class MyTable extends JTable {

	/**
	 * 计划让所有表格都继承此类
	 */
	private static final long serialVersionUID = 1L;
	TableRowSorter<TableModel> sorter;

	public MyTable(TableModel model) {
		super(model);
		setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		sorter = new TableRowSorter<TableModel>(model);
		setRowSorter(sorter);
	}

}
