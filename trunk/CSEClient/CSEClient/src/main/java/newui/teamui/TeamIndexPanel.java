package newui.teamui;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import newui.FatherPanel;

public class TeamIndexPanel extends FatherPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JScrollPane jsp;
	JTable table;
	public TeamIndexPanel(){
		super();
		table=new JTable();
		table.setOpaque(false);
		jsp=new JScrollPane(table);
		gbc.gridy = 1;
		gbc.gridheight = 10;
		gbc.weighty = 10;
		gbl.setConstraints(jsp, gbc);
		add(jsp);
	}
}
