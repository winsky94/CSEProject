package presentation.player;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class PlayerIndexPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel searchPnl,contentPnl;
	public PlayerIndexPanel() {
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		this.setLayout(gbl);
		// ----检索栏--------------
		searchPnl=new JPanel();
		searchPnl.setBackground(Color.BLUE);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 7;
		gbc.gridheight = 1;
		gbc.weightx = 10;
		gbc.weighty = 0.3;
		gbl.setConstraints(searchPnl, gbc);
		this.add(searchPnl);
		//---contentPnl----------
		contentPnl=new JPanel();
		contentPnl.setBackground(Color.yellow);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 7;
		gbc.gridheight = 6;
		gbc.weightx = 10;
		gbc.weighty = 8.5;
		gbl.setConstraints(contentPnl, gbc);
		this.add(contentPnl);
	}

}
