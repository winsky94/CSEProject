package dataservice;

import java.rmi.Remote;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import vo.MatchVO;
import vo.TeamVO;

public interface TeamDataService extends Remote {
	public ArrayList<TeamVO> getTeamBaseInfo();

	public ArrayList<MatchVO> getRecentMatches(String teamName);

	public ArrayList<TeamVO> getSeasonHotTeam(String season, String column);

	public TeamVO getTeamBaseInfo(String name);

	public ArrayList<String> getPlayersByTeam(String teamAbLocation);
}
