package businesslogicservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

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

	public ImageIcon getPlayerImage(String name);
}
