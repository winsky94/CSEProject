package newui.teamui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import newui.FatherPanel;
import newui.TabbedPaneUI;

public class TeamDetailPanel extends FatherPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel playerPnl,namePnl,tabPnl;
	JPanel infoPnl,recentPnl,historyPnl;
	Font font = new Font("微软雅黑", Font.PLAIN, 14);
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
		tabPnl=new JPanel();
		gbc.gridx=3;
		gbc.gridy=1;
		gbc.gridwidth=7;
		gbc.gridheight=1;
		gbc.weightx=7;
		gbc.weighty=10;
		gbl.setConstraints(tabPnl, gbc);
		add(tabPnl);
		//-------------------------
		infoPnl=new JPanel();
		gbc.gridx=3;
		gbc.gridy=1;
		gbc.gridwidth=7;
		gbc.gridheight=1;
		gbc.weightx=7;
		gbc.weighty=10;
		gbl.setConstraints(tabPnl, gbc);
		add(tabPnl);
	}
	public static void main(String[] args) {
		JFrame f=new JFrame();
		f.add(new TeamDetailPanel("马刺"));
		f.setBounds(100,100,1000,600);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
