package dataservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PlayerPO;

public interface PlayerDataService extends Remote {
	public ArrayList<PlayerPO> getPlayerList() throws RemoteException;
}
