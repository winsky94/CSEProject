package dataservice;

import java.util.ArrayList;

import vo.MatchVO;
import vo.TeamVO;

public interface TeamDataService {
	/**
	 * 获得球队的基础信息
	 * 
	 * @return 球队基础信息列表
	 */
	public ArrayList<TeamVO> getTeamBaseInfo();

	/**
	 * 根据球队名称模糊查找球队近期比赛
	 * 
	 * @param teamName
	 *            球队名称（缩写）
	 * @return 近期五场比赛的列表
	 */
	public ArrayList<MatchVO> getRecentMatches(String teamName);

	/**
	 * 根据球队名模糊查找球队的基础信息
	 * 
	 * @param name
	 *            球队名（缩写）
	 * @return 球队基础信息列表
	 */
	public ArrayList<TeamVO> getTeamBaseInfo(String name);

	/**
	 * 根据球队名找到该球队的所有球员姓名
	 * 
	 * @param teamAbLocation
	 *            球队名（缩写）
	 * @return 球员姓名列表
	 */
	public ArrayList<String> getPlayersByTeam(String teamAbLocation);

	/**
	 * 根据球队名称模糊查找球队全部比赛
	 * 
	 * @param teamName
	 *            球队名称（缩写）
	 * @return 近期全部比赛的列表
	 */
	public ArrayList<MatchVO> getMatches(String teamName);
}
