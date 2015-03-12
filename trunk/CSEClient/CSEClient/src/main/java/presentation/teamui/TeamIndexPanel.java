package presentation.teamui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TeamIndexPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel listPnl, contentPnl;

	public TeamIndexPanel() {
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		this.setLayout(gbl);
		// ---listPnl球队列表-------------
		listPnl = new JPanel();
		listPnl.setBackground(Color.blue);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.gridheight = 9;
		gbc.weightx = 3;
		gbc.weighty = 10;
		gbl.setConstraints(listPnl, gbc);
		this.add(listPnl);
		// ---contentPnl详细信息-------------
		contentPnl = new JPanel();
		contentPnl.setBackground(Color.yellow);
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.gridwidth = 7;
		gbc.gridheight = 9;
		gbc.weightx =7;
		gbc.weighty = 10;
		gbl.setConstraints(contentPnl, gbc);
		this.add(contentPnl);
	}
	public static void main(String args[]){
		JFrame jf=new JFrame();
		jf.setBounds(100,100,1162,679);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
