package newui.teamui.details;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import newui.tables.MySortableTable;

public class TeamDetailHistoryPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MySortableTable table;
	JScrollPane jsp;
	
	public TeamDetailHistoryPanel(String abbrName){
		GridBagLayout gbl=new GridBagLayout();
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.fill=GridBagConstraints.BOTH;
		setLayout(gbl);
		//------------------------
		
	}
}
