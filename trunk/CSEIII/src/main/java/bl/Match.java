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
		result=matchData.getAllSeasons();
		return result;
	}

	public ArrayList<MatchVO> getMatchData(String season, String date,
			String homeTeam, String visitingTeam) {
		// TODO 自动生成的方法存根
		ArrayList<MatchVO> result=new ArrayList<MatchVO>();
		result=matchData.getMatchData(season, date, homeTeam, visitingTeam);
		return result;
	}

	public ArrayList<MatchVO> getMatchesByTeam(String name) {
		// TODO 自动生成的方法存根
		ArrayList<MatchVO> result=new ArrayList<MatchVO>();
		result=matchData.getMatchesByTeam(name);
		return result;
	}

}
