package newui.hotui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import newui.FatherPanel;
import newui.Style;

public class HotIndexPanel extends FatherPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel hotTodayPnl, hotSeasonPnl, hotTeamPnl, progressPnl;
	JLabel hotTodayIcon, hotSeasonIcon, hotTeamIcon, progressIcon;
	JPanel hotTodayBoard, hotSeasonBoard, hotTeamBoard, progressBoard;

	public HotIndexPanel() {
		hotTodayPnl = new JPanel();
		hotTodayPnl.setBackground(Style.HOT_YELLOW);
		hotTodayIcon = new JLabel(new ImageIcon(
				"image/player/action/Ray Allen.png"));
		hotTodayPnl.add(hotTodayIcon);
		gbc.gridy = 1;
		gbc.gridwidth = 3;
		gbc.gridheight = 8;
		gbc.weightx = 3;
		gbc.weighty = 8;
		gbl.setConstraints(hotTodayPnl, gbc);
		add(hotTodayPnl);
		// --------------------
		hotSeasonPnl = new JPanel();
		hotSeasonPnl.setBackground(Style.HOT_RED);
		hotSeasonIcon = new JLabel(new ImageIcon(
				"image/player/action/Kobe Bryant.png"));
		hotSeasonPnl.add(hotSeasonIcon);
		gbc.gridx = 3;
		gbl.setConstraints(hotSeasonPnl, gbc);
		add(hotSeasonPnl);
		// --------------------
		hotTeamPnl = new JPanel();
		hotTeamPnl.setBackground(Style.HOT_BLUE);
		hotTeamIcon = new JLabel(new ImageIcon(
				"image/teamIcon/teamsPng150/SAS.png"));
		hotTeamPnl.add(hotTeamIcon);
		gbc.gridx = 6;
		gbl.setConstraints(hotTeamPnl, gbc);
		add(hotTeamPnl);
		// --------------------
		progressPnl = new JPanel();
		progressPnl.setBackground(Style.HOT_PURPLE);
		progressIcon = new JLabel(new ImageIcon(
				"image/player/action/Alex Len.png"));
		progressPnl.add(progressIcon);
		gbc.gridx = 9;
		gbl.setConstraints(progressPnl, gbc);
		add(progressPnl);
	}

}
