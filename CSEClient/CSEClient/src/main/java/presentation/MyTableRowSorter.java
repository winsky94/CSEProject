package presentation;

import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class MyTableRowSorter extends TableRowSorter<TableModel>{
	public MyTableRowSorter(TableModel model){
		super(model);
	}
	
}
