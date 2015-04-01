package newui.teamui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import newui.FatherPanel;

public class TeamDetailPanel extends FatherPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel playerPnl,infoPnl,namePnl;
	public TeamDetailPanel(String name){
		//----------------------
		namePnl=new JPanel();
		namePnl.setBackground(Color.black);
		gbc.gridx=0;
		gbc.gridy=1;
		gbc.gridwidth=3;
		gbc.gridheight=3;
		gbc.weightx=3;
		gbc.weighty=30;
		gbl.setConstraints(namePnl, gbc);
		add(namePnl);
		//-----------------------
		playerPnl=new JPanel();
		playerPnl.setBackground(Color.red);
		gbc.gridy=4;
		gbc.gridheight=7;
		gbc.weighty=70;
		gbl.setConstraints(playerPnl, gbc);
		add(playerPnl);
		//-----------------------
		infoPnl=new JPanel();
		infoPnl.setBackground(Color.yellow);
		gbc.gridx=3;
		gbc.gridy=1;
		gbc.gridwidth=7;
		gbc.gridheight=10;
		gbc.weightx=7;
		gbc.weighty=100;
		gbl.setConstraints(infoPnl, gbc);
		add(infoPnl);
	}
	public static void main(String[] args) {
		JFrame f=new JFrame();
		f.add(new TeamDetailPanel("马刺"));
		f.setBounds(100,100,1000,600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
