package newui.matchui;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bl.team.Team;
import blservice.TeamBLService;
import newui.Style;
import vo.MatchVO;

public class TinyCard extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean isHomeBigger = false;
	TeamBLService team;
	public TinyCard(MatchVO vo,String tName) {
		setOpaque(false);
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.insets=new Insets(10,20,10,10);
		setLayout(gbl);
		isHomeBigger = MatchHelper.isHomeBigger(vo);
		// ----------------
		team=new Team();
		ImageIcon imgicon;
		if(tName.equals(vo.getHomeTeam()))
			imgicon=team.getTeamImage(vo.getVisitingTeam());
		else
			imgicon=team.getTeamImage(tName);
		imgicon.setImage(imgicon.getImage().getScaledInstance(90, 90,Image.SCALE_DEFAULT ));
		JLabel icon = new JLabel(imgicon);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 3;
		gbc.weightx = 2;
		gbc.weighty = 3;
		gbl.setConstraints(icon, gbc);
		add(icon);
		// ----------------
		JLabel nameLbl = new JLabel(vo.getHomeTeam() + "  -  "
				+ vo.getVisitingTeam());
		nameLbl.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		gbc.gridy = 3;
		gbc.gridheight = 1;
		gbc.weighty = 1;
		gbc.insets=new Insets(10,40,10,10);
		gbl.setConstraints(nameLbl, gbc);
		add(nameLbl);
		// -----------------
		JLabel homeScoreLbl = new JLabel(String.valueOf(vo.getHomeScore()));
		if (isHomeBigger)
			homeScoreLbl.setForeground(Style.WINNER_RED);
		homeScoreLbl.setFont(new Font("微软雅黑", Font.BOLD, 24));
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		gbc.weightx = 1;
		gbc.weighty = 2;
		gbc.insets=new Insets(10,20,10,10);
		gbl.setConstraints(homeScoreLbl, gbc);
		add(homeScoreLbl);
		//---------------------
		JLabel visitingScoreLbl = new JLabel(String.valueOf(vo.getVisitingScore()));
		if (!isHomeBigger)
			visitingScoreLbl.setForeground(Style.WINNER_RED);
		visitingScoreLbl.setFont(new Font("微软雅黑", Font.BOLD, 24));
		gbc.gridx = 1;
		gbl.setConstraints(visitingScoreLbl, gbc);
		add(visitingScoreLbl);
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
}
