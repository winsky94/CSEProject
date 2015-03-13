package data;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import po.TeamPO;
import dataservice.TeamDataService;

public class Team extends UnicastRemoteObject implements TeamDataService {
	private static final long serialVersionUID = 1L;

	public Team() throws RemoteException {
		super();
		// TODO 自动生成的构造函数存根
	}

	public ArrayList<TeamPO> getTeamList() {
		String name;
		String location;
		String abLocation;
		String conference;
		String partition;
		String homeCourt;
		int setUpTime;

		ArrayList<TeamPO> teams = new ArrayList<TeamPO>();
		TeamPO team;
		return teams;
	}

	public String getPhotoPath(String abLocation) {
		return "src/迭代一数据/teams/" + abLocation + ".svg";
	}

	public static void main(String[] args) {
		Team team;
		try {
			team = new Team();
			team.getTeamList();
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

}
