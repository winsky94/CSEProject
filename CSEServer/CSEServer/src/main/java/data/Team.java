package data;

import java.util.ArrayList;

import po.TeamPO;
import dataservice.TeamDataService;

public class Team implements TeamDataService {

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

	public static void main(String[] args) {
		Team team = new Team();
		team.getTeamList();
	}

}
