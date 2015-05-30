package bl;

import java.util.ArrayList;

import vo.MatchVO;
import blService.MatchBLService;

public class Match implements MatchBLService {

	public static void main(String[] args) {
		MatchBLService match = new Match();
		ArrayList<String> result = new ArrayList<String>();
		result = match.getAllSeasons();
		System.out.println(result.get(0));
	}

	public ArrayList<String> getAllSeasons() {
		// TODO 自动生成的方法存根
		ArrayList<String> result = new ArrayList<String>();

		return result;
	}

	public ArrayList<MatchVO> getMatchData(String season, String date,
			String homeTeam, String visitingTeam) {
		// TODO 自动生成的方法存根
		return null;
	}

	public ArrayList<MatchVO> getMatches(String name) {
		// TODO 自动生成的方法存根
		return null;
	}

}
