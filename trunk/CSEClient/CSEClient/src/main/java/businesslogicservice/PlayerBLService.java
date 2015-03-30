package businesslogicservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import po.MatchPO;
import po.PlayerPO;
import vo.PlayerVO;

public interface PlayerBLService {
	public ArrayList<PlayerVO> getPlayerBaseInfo();

	public ArrayList<PlayerVO> getPlayerSeasonInfo(String season);

	public ArrayList<PlayerVO> getPlayerAverageInfo(String season);

	public PlayerVO getPlayerBaseInfo(String name);

	public PlayerVO getPlayerSeasonInfo(String season, String name);

	public PlayerVO getPlayerAverageInfo(String season, String name);

	public ArrayList<PlayerVO> getOrderedPlayersBySeason(String season,
			String condition, String order) throws RemoteException;

	public ArrayList<PlayerVO> getOrderedPlayersByAverage(String season,
			String condition, String order);

	public ArrayList<PlayerVO> selectPlayersBySeason(String season,
			String position, String union, String column);

	public ArrayList<PlayerVO> selectPlayersByAverage(String season,
			String position, String union, String column);

	public ImageIcon getPlayerPortraitImage(String name);

	public ImageIcon getPlayerActionImage(String name);

	public ArrayList<PlayerPO> getDayHotPlayer(String column)
			throws RemoteException;

	public ArrayList<PlayerPO> getSeasonHotPlayer(String season, String column);

	public ArrayList<PlayerPO> getBestImprovedPlayer(String column);

	public ArrayList<MatchPO> getRecentMatches(String playerName)
			throws RemoteException;

	public ArrayList<MatchPO> getTodayMatches(String playerName);

	public ArrayList<MatchPO> getMatches(String playerName);// 可以模糊
}