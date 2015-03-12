package dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.TeamPO;

public interface TeamDataService extends Remote {
	public ArrayList<TeamPO> getTeamList() throws RemoteException;
}
