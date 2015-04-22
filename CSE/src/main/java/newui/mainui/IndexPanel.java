package newui.mainui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import newui.FatherPanel;
import newui.Style;
import newui.UIhelper;
import vo.PlayerVO;
import vo.TeamVO;
import bl.player.Player;
import bl.team.Team;
import blservice.PlayerBLService;
import blservice.TeamBLService;

public class IndexPanel extends FatherPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel titlePnl, infoPnl;
	PlayerBLService player;
	TeamBLService team;
	String dname, sname, tname, tPlayername, pname;
	IndexCard todayPnl, seasonPnl, teamPnl, progressPnl;

	public IndexPanel() {
		removeAll();
		player = new Player();
		team = new Team();
		// -----获得热点球员们---------
		PlayerVO dayP = player.getDayHotPlayer("score", 1).get(0);
		PlayerVO seasonP = player.getSeasonHotPlayer("13-14", "score", 1)
				.get(0);
		TeamVO t = team.getSeasonHotTeam("13-14", "score", 1).get(0);
		PlayerVO proP = player.getBestImprovedPlayer("recentFiveMatchesScoreUpRate", 1).get(0);
		//
		dname = dayP.getName();
		sname = seasonP.getName();
		tname = t.getTeamName();
		tPlayername=player.getPlayersByTeam(t.getAbLocation()).get(1).getName();
		pname = proP.getName();
		// ----------------------------
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		setLayout(gbl);
		// -----titlePnl-----------
		titlePnl = new TitlePanel();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 10;
		gbc.gridheight = 3;
		gbc.weightx = 10;
		gbc.weighty = 0.1;
		gbl.setConstraints(titlePnl, gbc);
		add(titlePnl);
		// ----infoPnl-------------
		gbc.insets=new Insets(50,30,50,30);
		infoPnl = new JPanel();
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridheight = 7;
		gbc.weightx = 10;
		gbc.weighty = 15;
		gbl.setConstraints(infoPnl, gbc);
		add(infoPnl);
		infoPnl.setLayout(new GridLayout(1, 4));
		// -----todayPnl-----------
		IndexCard todayPnl = new IndexCard();
		todayPnl.setTitleAndName("今日得分王", dname);
		//todayPnl.iconPnl.setBackground(new Color(254,239,182));
		todayPnl.iconPnl.setBackground(Style.HOT_YELLOW);
		ImageIcon todayIcon = new ImageIcon("image/player/action/" + dname
				+ ".png");
		todayIcon.setImage(todayIcon.getImage().getScaledInstance(220, 350,
				Image.SCALE_DEFAULT));
		todayPnl.iconLbl.setIcon(todayIcon);
		//
		todayPnl.namePnl.setBackground(Style.HOT_YELLOWFOCUS);
		infoPnl.add(todayPnl);
		// -----seasonPnl-----------
		IndexCard seasonPnl = new IndexCard();
		seasonPnl.setTitleAndName("赛季得分王", sname);
		//seasonPnl.iconPnl.setBackground(new Color(255,147,147));
		seasonPnl.iconPnl.setBackground(Style.HOT_RED);
		ImageIcon seasonIcon = new ImageIcon("image/player/action/" + sname
				+ ".png");
		
		seasonIcon.setImage(seasonIcon.getImage().getScaledInstance(220, 350,
				Image.SCALE_DEFAULT));
		seasonPnl.iconLbl.setIcon(seasonIcon);
		//
		seasonPnl.namePnl.setBackground(Style.HOT_REDFOCUS);
		infoPnl.add(seasonPnl);
		// -----teamPnl-----------
		IndexCard teamPnl = new IndexCard();
		teamPnl.setTitleAndName("场均得分最高球队", tname);
		//teamPnl.iconPnl.setBackground(new Color(179,255,188));
		teamPnl.iconPnl.setBackground(Style.HOT_BLUE);
		ImageIcon teamIcon = new ImageIcon("image/player/action/" + tPlayername
				+ ".png");
		teamIcon.setImage(teamIcon.getImage().getScaledInstance(220,350,
				Image.SCALE_DEFAULT));
		teamPnl.iconLbl.setIcon(teamIcon);
		//
		teamPnl.namePnl.setBackground(Style.HOT_BLUEFOCUS);
		infoPnl.add(teamPnl);
		// -----progressPnl-----------
		IndexCard progressPnl = new IndexCard();
		progressPnl.setTitleAndName("得分进步最快球员", pname);
		//progressPnl.iconPnl.setBackground(new Color(204,153,255));
		progressPnl.iconPnl.setBackground(Style.HOT_PURPLE);
		String pImgName=pname;
		if(!UIhelper.isImgExists(pImgName))
			pImgName="null";
		ImageIcon progressIcon = new ImageIcon("image/player/action/" + pImgName
				+ ".png");
		progressIcon.setImage(progressIcon.getImage().getScaledInstance(220, 350,
				Image.SCALE_DEFAULT));
		progressPnl.iconLbl.setIcon(progressIcon);
		//
		progressPnl.namePnl.setBackground(Style.HOT_PURPLEFOCUS);
		infoPnl.add(progressPnl);
	}
}
