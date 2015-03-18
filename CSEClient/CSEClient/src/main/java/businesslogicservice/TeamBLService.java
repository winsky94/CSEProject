package businesslogicservice;

import java.util.ArrayList;

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
}
