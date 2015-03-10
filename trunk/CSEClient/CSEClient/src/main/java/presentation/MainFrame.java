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
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int screenWidth, screenHeight, frameWidth, frameHeight;
	JPanel pnl;

	public MainFrame() {
		// ------设置窗口大小、位置---------
		screenWidth = UIhelper.getScreenWidth();
		screenHeight = UIhelper.getScreenHeight();
		frameWidth = screenWidth * 85 / 100;
		frameHeight = screenHeight * 90 / 100;
		this.setBounds((screenWidth - frameWidth) / 2,
				(screenHeight - frameHeight) / 2, frameWidth, frameHeight);
		// --------底层Panel-------------
		pnl = new JPanel();
		pnl.setBackground(Color.black);
		this.add(pnl);

		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		pnl.setLayout(gbl);
		// ---------title---------------
		JPanel titlePnl = new JPanel();
		titlePnl.setBackground(Color.white);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 7;
		gbc.gridheight = 1;
		gbc.weightx = 10;
		gbc.weighty = 0.3;
		gbl.setConstraints(titlePnl, gbc);
		pnl.add(titlePnl);
		// -------topBar---------------
		JPanel topBar = new JPanel();
		topBar.setBackground(Color.GREEN);
		gbc.gridy = 1;
		gbc.gridwidth = 7;
		gbc.gridheight = 4;
		gbc.weightx = 10;
		gbc.weighty = 2.5;
		gbl.setConstraints(topBar, gbc);
		pnl.add(topBar);
		// -----imageRollerPnl---------
		JPanel imgRollerPnl = new JPanel();
		imgRollerPnl.setBackground(Color.RED);
		gbc.gridy = 5;
		gbc.gridwidth = 4;
		gbc.gridheight = 7;
		gbc.weightx = 6;
		gbc.weighty = 5;
		gbl.setConstraints(imgRollerPnl, gbc);
		pnl.add(imgRollerPnl);
		// ------ourPnl----------------
		JPanel ourPnl = new JPanel();
		ourPnl.setBackground(Color.blue);
		gbc.gridx = 4;
		gbc.gridy = 5;
		gbc.gridwidth = 3;
		gbc.gridheight = 3;
		gbc.weightx = 4;
		gbc.weighty = 2.5;
		gbl.setConstraints(ourPnl, gbc);
		pnl.add(ourPnl);
		// ------enterPnl----------------
		JPanel enterPnl = new JPanel();
		enterPnl.setBackground(Color.gray);
		gbc.gridx = 4;
		gbc.gridy = 8;
		gbc.gridwidth = 3;
		gbc.gridheight = 4;
		gbc.weightx = 4;
		gbc.weighty = 2.5;
		gbl.setConstraints(enterPnl, gbc);
		pnl.add(enterPnl);
		//
		this.setUndecorated(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();

	}

}
