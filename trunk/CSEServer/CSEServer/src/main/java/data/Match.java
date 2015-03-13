package data;

import java.util.ArrayList;

import po.MatchPO;

public class Match {
	public ArrayList<MatchPO> getMatchesList() {
		ArrayList<MatchPO> matches = new ArrayList<MatchPO>();
		return matches;
	}

	public static void main(String[] args) {
		Match Match = new Match();
		Match.getMatchesList();
		System.out.println("success!");
	}
}
