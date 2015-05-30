package blService;

import java.util.ArrayList;

import vo.MatchVO;

public interface MatchBLService {
	/**
	 * 得到记录在案的所有赛季
	 * 
	 * @return 赛季列表
	 */
	public ArrayList<String> getAllSeasons();

	/**
	 * 得到比赛数据
	 * 
	 * @param season
	 *            赛季
	 * @param date
	 *            日期
	 * @param homeTeam
	 *            主队
	 * @param visitingTeam
	 *            客队
	 * @return 符合条件的比赛列表
	 */
	public ArrayList<MatchVO> getMatchData(String season, String date,
			String homeTeam, String visitingTeam);

	/**
	 * 根据球队名得到该球队参加过的所有比赛
	 * 
	 * @param name
	 *            球队名（缩写）
	 * @return 比赛列表
	 */
	public ArrayList<MatchVO> getMatchesByTeam(String name);// 可以模糊
}
