package bl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import blservice.TeamBLService;
import vo.MatchVO;
import vo.TeamVO;

public class Team implements TeamBLService{

	private ArrayList<String[]> readFromFile(String fileName) {
		ArrayList<String[]> result = new ArrayList<String[]>();
		String[] content;
		try {
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "UTF-8"));
			String temp = null;
			temp = br.readLine();
			while (temp != null) {
				content = new String[7];
				if (temp.contains("│")) {
					String[] it = temp.split("│");
					String[] first = it[0].split("║");
					content[0] = first[1].trim();
					content[1] = it[1].trim();
					content[2] = it[2].trim();
					content[3] = it[3].trim();
					content[4] = it[4].trim();
					content[5] = it[5].trim();
					String[] last = it[6].split("║");
					content[6] = last[0].trim();
					result.add(content);
				}
				temp = br.readLine();
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}


	public ArrayList<TeamVO> getTeamBaseInfo() {
		// TODO 自动生成的方法存根
		String name;
		String location;
		String abLocation;
		String conference;
		String partition;
		String homeCourt;
		int setUpTime;

		ArrayList<TeamVO> teams = new ArrayList<TeamVO>();
		TeamVO team;
		ArrayList<String[]> result = readFromFile("src/data/teams/teams");
		for (String[] content : result) {
			name = content[0];
			abLocation = content[1];
			location = content[2];
			conference = content[3];
			partition = content[4];
			homeCourt = content[5];
			setUpTime = Integer.parseInt(content[6]);

			team = new TeamVO(0, name, abLocation, location, conference,
					partition, homeCourt, setUpTime);
			teams.add(team);
		}
		return teams;
	}

	
	public ArrayList<TeamVO> getTeamSeasonInfo(String season) {
		// TODO 自动生成的方法存根
		return null;
	}

	public ArrayList<TeamVO> getTeamAverageInfo(String season) {
		// TODO 自动生成的方法存根
		return null;
	}

	public TeamVO getTeamBaseInfo(String name) {
		// TODO 自动生成的方法存根
		return null;
	}

	public TeamVO getTeamSeasonInfo(String season, String name) {
		// TODO 自动生成的方法存根
		return null;
	}

	public TeamVO getTeamAverageInfo(String season, String name) {
		// TODO 自动生成的方法存根
		return null;
	}

	public ArrayList<TeamVO> getOrderedTeamsBySeason(String season,
			String condition, String order) {
		// TODO 自动生成的方法存根
		return null;
	}

	public ArrayList<TeamVO> getOrderedTeamsByAverage(String season,
			String condition, String order) {
		// TODO 自动生成的方法存根
		return null;
	}

	public ImageIcon getTeamImage(String name) {
		// TODO 自动生成的方法存根
		return null;
	}

	public ArrayList<MatchVO> getRecentMatches(String teamName) {
		// TODO 自动生成的方法存根
		return null;
	}

	public ArrayList<MatchVO> getMatches(String teamName) {
		// TODO 自动生成的方法存根
		return null;
	}

	public ArrayList<TeamVO> getSeasonHotTeam(String season, String column) {
		// TODO 自动生成的方法存根
		return null;
	}

	public ArrayList<String> getPlayersByTeam(String teamAbLocation) {
		// TODO 自动生成的方法存根
		return null;
	}

}
