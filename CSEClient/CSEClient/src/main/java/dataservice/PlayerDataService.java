package dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import po.PlayerPO;

public interface PlayerDataService extends Remote {
	public ArrayList<PlayerPO> getPlayerBaseInfo() throws RemoteException;

	public ArrayList<PlayerPO> getPlayerSeasonInfo(String season)
			throws RemoteException;

	public PlayerPO getPlayerBaseInfo(String name) throws RemoteException;

	public PlayerPO getPlayerSeasonInfo(String season, String name)
			throws RemoteException;

	public PlayerPO getPlayerAverageInfo(String season, String name)
			throws RemoteException;

	public ArrayList<PlayerPO> getOrderedPlayersBySeason(String season,
			String condition, String order) throws RemoteException;

	public ArrayList<PlayerPO> getPlayerAverageInfo(String season)
			throws RemoteException;

	public ArrayList<PlayerPO> getOrderedPlayersByAverage(String season,
			String condition, String order) throws RemoteException;

	public ArrayList<PlayerPO> selectPlayersBySeason(String season,
			String position, String union, String column)
			throws RemoteException;

	public ArrayList<PlayerPO> selectPlayersByAverage(String season,
			String position, String union, String column)
			throws RemoteException;

	public ImageIcon getPlayerActionImage(String name) throws RemoteException;
	
	public ImageIcon getPlayerPortraitImage(String name) throws RemoteException;
}