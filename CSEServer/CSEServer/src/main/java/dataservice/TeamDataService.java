package dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.TeamPO;

public interface TeamDataService extends Remote {
	public ArrayList<TeamPO> getTeamBaseInfo() throws RemoteException;

	public ArrayList<TeamPO> getTeamSeasonInfo(String season)
			throws RemoteException;

	public ArrayList<TeamPO> getTeamAverageInfo(String season)
			throws RemoteException;

	public TeamPO getTeamBaseInfo(String name) throws RemoteException;

	public TeamPO getTeamSeasonInfo(String season, String name)
			throws RemoteException;

	public TeamPO getTeamAverageInfo(String season, String name)
			throws RemoteException;

	public ArrayList<TeamPO> getOrderedTeamsBySeason(String season,
			String condition, String order) throws RemoteException;

	public ArrayList<TeamPO> getOrderedTeamsByAverage(String season,
			String condition, String order) throws RemoteException;
}
