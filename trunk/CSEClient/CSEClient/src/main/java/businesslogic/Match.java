package businesslogic;

import java.rmi.Naming;
import java.util.ArrayList;

import po.MatchPO;
import businesslogicservice.MatchBLService;
import dataservice.MatchDataService;

public class Match implements MatchBLService {
	private MatchDataService service;

	public Match() {
		try {
			String host = getServer.getServerHost();
			String url = "rmi://" + host + "/matchService";
			service = (MatchDataService) Naming.lookup(url);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	public ArrayList<MatchPO> getMatchData(String season, String date,
			String homeTeam, String visitingTeam) {
		// TODO 自动生成的方法存根
		ArrayList<MatchPO> result = new ArrayList<MatchPO>();
		try {
			result = service.getMatchData(season, date, homeTeam, visitingTeam);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

}
