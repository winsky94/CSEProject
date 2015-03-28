package newui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import newui.tables.PlayerBaseInfoTableModel;
import newui.tables.TeamBaseInfoTableModel;

public class SearchResultPanel extends FatherPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PlayerBaseInfoTableModel ptm;
	TeamBaseInfoTableModel ttm;
	JTable teamTable, playerTable;
	JScrollPane teamJsp, playerJsp;
	JLabel resultLbl;
	JPanel funcPnl;
	public SearchResultPanel() {
		// ------funcPnl--------
		funcPnl = new JPanel();
		funcPnl.setBackground(Style.BACK_GREY);
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.weighty = 0.1;
		gbl.setConstraints(funcPnl, gbc);
		add(funcPnl);
	}

}
