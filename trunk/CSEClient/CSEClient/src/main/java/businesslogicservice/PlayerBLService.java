package businesslogicservice;

import java.util.ArrayList;

import vo.PlayerVO;

public interface PlayerBLService {
	public ArrayList<PlayerVO> getPlayerBaseInfo();

	public ArrayList<PlayerVO> getPlayerSeasonInfo(String season);

	public ArrayList<PlayerVO> getPlayerAverageInfo(String season);

	public PlayerVO getPlayerBaseInfo(String name);

	public PlayerVO getPlayerSeasonInfo(String season, String name);

	public PlayerVO getPlayerAverageInfo(String season, String name);

}
