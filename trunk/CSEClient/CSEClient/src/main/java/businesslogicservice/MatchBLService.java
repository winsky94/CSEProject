package businesslogicservice;

import java.util.ArrayList;

import po.MatchPO;

public interface MatchBLService {
	public ArrayList<MatchPO> getMatchData(String season, String date,
			String homeTeam, String visitingTeam);
}
