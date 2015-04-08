package newui.teamui.details;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;

import vo.MatchVO;
import businesslogic.Team;
import businesslogicservice.TeamBLService;

public class TeamDetailRecentPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TeamBLService team;
	ArrayList<MatchVO> matches=new ArrayList<MatchVO>();
	public TeamDetailRecentPanel(String abbrName){
		team=new Team();
		matches=team.getRecentMatches(abbrName);
		//-------------------
		setLayout(new GridLayout(1,5));
		
	}
}
