package presentation;

/*2015.3.9 wangning 创建
 * 2015.3.10 wangning 界面分区
 * 
 * 
 * 
 */
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import presentation.team.TeamIndexPanel;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int screenWidth, screenHeight, frameWidth, frameHeight;
	JPanel titlePnl, contentPnl= new JPanel();
	
	public MainFrame() {
		// ------设置窗口大小、位置---------
		screenWidth = UIhelper.getScreenWidth();
		screenHeight = UIhelper.getScreenHeight();
		frameWidth = screenWidth * 85 / 100;
		frameHeight = screenHeight * 90 / 100;
		this.setBounds((screenWidth - frameWidth) / 2,
				(screenHeight - frameHeight) / 2, frameWidth, frameHeight);
		// --------分配Panel-------------
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		this.setLayout(gbl);
		// ---------title---------------
		titlePnl = new JPanel();
		titlePnl.setBackground(Color.white);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 7;
		gbc.gridheight = 1;
		gbc.weightx = 10;
		gbc.weighty = 0.3;
		gbl.setConstraints(titlePnl, gbc);
		this.add(titlePnl);
		setPanel(new IndexPanel());
		//-------content----------------
		contentPnl = new JPanel();
		gbc.gridy = 1;
		gbc.gridheight =12;
		gbc.weightx = 10;
		gbc.weighty = 13;
		gbl.setConstraints(contentPnl, gbc);
		this.add(contentPnl);
		setPanel(new IndexPanel());
		//
		this.setUndecorated(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void setPanel(JPanel pnl) {
		contentPnl.removeAll();
		contentPnl.setLayout(new GridLayout(1, 1));
		contentPnl.add(pnl);
	}

	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();
		//mainFrame.setPanel(new TeamIndexPanel());
	}

}
