package bl;

import java.util.ArrayList;

import vo.MatchVO;
import blService.MatchBLService;
import data.MatchData;
import dataservice.MatchDataService;

public class Match implements MatchBLService {
	MatchDataService matchData;

	public static void main(String[] args) {
		MatchBLService match = new Match();
		ArrayList<String> result = new ArrayList<String>();
		result = match.getAllSeasons();
		System.out.println(result.get(0));
	}

	public Match() {
		matchData = new MatchData();
	}

	public ArrayList<String> getAllSeasons() {
		// TODO 自动生成的方法存根
		ArrayList<String> result = new ArrayList<String>();
		result = matchData.getAllSeasons();
		return result;
	}

	public ArrayList<MatchVO> getMatchData(String season, String type,
			String date, String homeTeam, String visitingTeam) {
		// TODO 自动生成的方法存根
		ArrayList<MatchVO> result = new ArrayList<MatchVO>();
		result = matchData.getMatchData(season, Match.changeTypeCHToEN(type), date, homeTeam,
				visitingTeam);
		return result;
	}

	public ArrayList<MatchVO> getMatchesByTeam(String season, String type,
			String name) {
		// TODO 自动生成的方法存根
		ArrayList<MatchVO> result = new ArrayList<MatchVO>();
		result = matchData.getMatchesByTeam(season, Match.changeTypeCHToEN(type), name);
		return result;
	}

	/**
	 * 将比赛类型的中文转为英文
	 * @param CH 比赛类型的中文
	 * @return 对应的英文
	 */
	public static String changeTypeCHToEN(String CH) {
		String CHs[] = { "全部", "季前赛", "常规赛", "季后赛" };
		String ENs[] = { "all", "Preseason", "Team", "Playoff" };
		String EN = CH;
		for (int i = 0; i < CHs.length; i++) {
			if (CHs[i].equals(CH)) {
				EN = ENs[i];
				break;
			}
		}
		return EN;
	}

}
