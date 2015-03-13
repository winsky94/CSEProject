package data;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import dataservice.MatchDataService;
import po.MatchPO;

public class Match extends UnicastRemoteObject implements MatchDataService {
	private static final long serialVersionUID = 1L;

	public Match() throws RemoteException {
		super();
		// TODO 自动生成的构造函数存根
	}

	public ArrayList<MatchPO> getMatchesList() {
		ArrayList<MatchPO> matches = new ArrayList<MatchPO>();
		return matches;
	}

	public static void main(String[] args) {
		Match Match;
		try {
			Match = new Match();
			Match.getMatchesList();
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		System.out.println("success!");
	}
}
