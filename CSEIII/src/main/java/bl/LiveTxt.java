package bl;

import java.util.ArrayList;

import vo.LiveMatchDetailVO;
import blService.LiveTxtBLService;
import data.LiveTxtData;

public class LiveTxt implements LiveTxtBLService {
	private LiveTxtData liveTxtData;

	public LiveTxt() {
		liveTxtData = new LiveTxtData();
	}

	public ArrayList<LiveMatchDetailVO> getLiveTxt(String season, String date,
			String teams) {
		// TODO Auto-generated method stub
		return liveTxtData.getLiveTxt(season, date, teams);
	}

}
