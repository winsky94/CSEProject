package dataservice;

import java.rmi.Remote;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import vo.MatchVO;
import vo.PlayerVO;

public interface PlayerDataService extends Remote {
	public ArrayList<PlayerVO> getPlayerBaseInfo();

	public ArrayList<PlayerVO> getPlayerSeasonInfo(String season);

	public PlayerVO getPlayerBaseInfo(String name);

	public PlayerVO getPlayerSeasonInfo(String season, String name);

	public PlayerVO getPlayerAverageInfo(String season, String name);

	public ArrayList<MatchVO> getRecentMatches(String playerName);

	public ArrayList<MatchVO> getTodayMatches(String playerName);

	public ArrayList<MatchVO> getSeasonMatches(String playerName, String season);

	public ArrayList<MatchVO> getMatches(String playerName);// 可以模糊

	public ArrayList<PlayerVO> getOrderedPlayersBySeason(String season,
			String condition, String order, int num);

	public ArrayList<PlayerVO> getPlayerAverageInfo(String season);

	public ArrayList<PlayerVO> getOrderedPlayersByAverage(String season,
			String condition, String order, int num);

	public ArrayList<PlayerVO> selectPlayersBySeason(String season,
			String position, String union, String column, int num);

	public ArrayList<PlayerVO> selectPlayersByAverage(String season,
			String position, String union, String column, int num);

	public ArrayList<PlayerVO> getDayHotPlayer(String column);

	public ArrayList<PlayerVO> getSeasonHotPlayer(String season, String column);

	public ArrayList<PlayerVO> getBestImprovedPlayer(String column);

	public ArrayList<PlayerVO> getPlayersByInitialName(char character);

	public ImageIcon getPlayerActionImage(String name);

	public ImageIcon getPlayerPortraitImage(String name);

}
