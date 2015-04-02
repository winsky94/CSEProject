package newui.teamui.details;

import javax.swing.JPanel;

import vo.TeamVO;
import businesslogic.Team;
import businesslogicservice.TeamBLService;

public class TeamDetailInfoPanel extends JPanel{

	/**
	 * 球队详情之基本信息
	 */
	private static final long serialVersionUID = 1L;
	String chName,enName,abbrName;
	TeamBLService team;
	TeamVO vo;
	public TeamDetailInfoPanel(String ch,String abbr){
		//-----名字获取及VO生成----------
		team=new Team();
		chName=ch;
		abbrName=abbr;
		vo=team.getTeamBaseInfo(abbr);
		enName=vo.getTeamName();
		//----------------------------
		
	}
}
