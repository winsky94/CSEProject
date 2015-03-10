package presentation;
/* 2015.3.10 wangning 创建，分配布局
 * 
 */
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class IndexPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public IndexPanel(){
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		this.setLayout(gbl);
		// -------topBar---------------
		JPanel topBar = new JPanel();
		topBar.setBackground(Color.GREEN);
		gbc.gridx=0;
		gbc.gridy = 0;
		gbc.gridwidth = 7;
		gbc.gridheight = 4;
		gbc.weightx = 10;
		gbc.weighty = 2.5;
		gbl.setConstraints(topBar, gbc);
		this.add(topBar);
		// -----imageRollerPnl---------
		JPanel imgRollerPnl = new JPanel();
		imgRollerPnl.setBackground(Color.RED);
		gbc.gridy =4;
		gbc.gridwidth = 4;
		gbc.gridheight = 7;
		gbc.weightx = 6;
		gbc.weighty = 5;
		gbl.setConstraints(imgRollerPnl, gbc);
		this.add(imgRollerPnl);
		// ------ourPnl----------------
		JPanel ourPnl = new JPanel();
		ourPnl.setBackground(Color.blue);
		gbc.gridx = 4;
		gbc.gridy =4;
		gbc.gridwidth = 3;
		gbc.gridheight = 3;
		gbc.weightx = 4;
		gbc.weighty = 2.5;
		gbl.setConstraints(ourPnl, gbc);
		this.add(ourPnl);
		// ------enterPnl----------------
		JPanel enterPnl = new JPanel();
		enterPnl.setBackground(Color.gray);
		gbc.gridx = 4;
		gbc.gridy = 7;
		gbc.gridwidth = 3;
		gbc.gridheight = 4;
		gbc.weightx = 4;
		gbc.weighty = 2.5;
		gbl.setConstraints(enterPnl, gbc);
		this.add(enterPnl);
	}

}
