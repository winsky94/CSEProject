package dataservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import po.MatchPO;

public interface MatchDataService {
	public ArrayList<MatchPO> getMatchesList() throws RemoteException;
}
