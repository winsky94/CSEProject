package businesslogicservice;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import po.MatchPO;
import po.TeamPO;
import vo.TeamVO;

public interface TeamBLService {
	public ArrayList<TeamVO> getTeamBaseInfo();

	public ArrayList<TeamVO> getTeamSeasonInfo(String season);

	public ArrayList<TeamVO> getTeamAverageInfo(String season);

	public TeamVO getTeamBaseInfo(String name);

	public TeamVO getTeamSeasonInfo(String season, String name);

	public TeamVO getTeamAverageInfo(String season, String name);

	public ArrayList<TeamVO> getOrderedTeamsBySeason(String season,
			String condition, String order);

	public ArrayList<TeamVO> getOrderedTeamsByAverage(String season,
			String condition, String order);

	public ImageIcon getTeamImage(String name);

	public ArrayList<MatchPO> getRecentMatches(String teamName);

	public ArrayList<MatchPO> getMatches(String teamName);// 可以模糊

	public ArrayList<TeamPO> getSeasonHotTeam(String season, String column);
}
