package bl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import vo.MatchVO;
import vo.RecordVO;
import vo.TeamVO;
import blservice.TeamBLService;

public class Team implements TeamBLService {

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

	private MatchVO readFromMatchFile(String fileName) {
		MatchVO matchVO;
		String season;// 赛季
		String date = null;// 比赛日期
		String teams = null;// 对阵队伍
		String score = null;// 比分

		ArrayList<String> detailScores = new ArrayList<String>();// 各节比分
		ArrayList<RecordVO> records = new ArrayList<RecordVO>();// 球员比分数据记录

		String tp[] = fileName.split("matches");
		season = tp[1].substring(1, 6);

		try {
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "UTF-8"));
			String temp = null;

			temp = br.readLine();
			String[] fisrtContent = temp.split(";");
			date = fisrtContent[0];
			teams = fisrtContent[1];
			score = fisrtContent[2];

			temp = br.readLine();
			String[] scoresData = temp.split(";");
			for (int i = 0; i < scoresData.length; i++) {
				detailScores.add(scoresData[i]);
			}

			String team = null;// 球队
			String playerName = null;// 球员名
			String VOsition = null;// 位置
			String presentTime = null;// 在场时间
			int shootHitNum = 0;// 投篮命中数
			int shootAttemptNum = 0;// 投篮出手数
			int threeHitNum = 0;// 三分命中数
			int threeAttemptNum = 0;// 三分出手数
			int freeThrowHitNum = 0;// 罚球命中数
			int freeThrowAttemptNum = 0;// 罚球出手数
			int offenReboundNum = 0;// 进攻（前场）篮板数
			int defenReboundNum = 0;// 防守（后场）篮板数
			int reboundNum = 0;// 总篮板数
			int assistNum = 0;// 助攻数
			int stealNum = 0;// 抢断数
			int blockNum = 0;// 盖帽数
			int turnOverNum = 0;// 失误数
			int foulNum = 0;// 犯规数
			int personScore = 0;// 个人得分

			boolean isComplete = false;
			temp = br.readLine();
			while (temp != null) {
				if (fileName.contains(temp)) {
					team = temp;
					isComplete = false;
				} else {
					String[] line = temp.split(";");
					playerName = line[0];
					VOsition = line[1];
					presentTime = DirtyDataManager.checkPresentTime(fileName,
							team, playerName, line[2]);// 在场时间
					shootHitNum = Integer.parseInt(line[3]);// 投篮命中数
					shootAttemptNum = DirtyDataManager.checkShootAndHitNum(
							fileName, Integer.parseInt(line[4]), shootHitNum);// 投篮出手数
					threeHitNum = Integer.parseInt(line[5]);// 三分命中数
					threeAttemptNum = DirtyDataManager.checkShootAndHitNum(
							fileName, Integer.parseInt(line[6]), threeHitNum);// 三分出手数
					freeThrowHitNum = Integer.parseInt(line[7]);// 罚球命中数
					freeThrowAttemptNum = DirtyDataManager.checkShootAndHitNum(
							fileName, Integer.parseInt(line[8]),
							freeThrowHitNum);// 罚球出手数
					offenReboundNum = Integer.parseInt(line[9]);// 进攻（前场）篮板数
					defenReboundNum = Integer.parseInt(line[10]);// 防守（后场）篮板数
					reboundNum = DirtyDataManager.checkReboundNum(fileName,
							offenReboundNum, defenReboundNum,
							Integer.parseInt(line[11]));// 总篮板数
					assistNum = Integer.parseInt(line[12]);// 助攻数
					stealNum = Integer.parseInt(line[13]);// 抢断数
					blockNum = Integer.parseInt(line[14]);// 盖帽数
					turnOverNum = Integer.parseInt(line[15]);// 失误数
					foulNum = Integer.parseInt(line[16]);// 犯规数
					personScore = DirtyDataManager.checkPersonScore(fileName,
							line[17], temp);// 个人得分

					isComplete = true;
				}
				if (isComplete) {
					RecordVO recordVO = new RecordVO(team, playerName,
							VOsition, presentTime, shootHitNum,
							shootAttemptNum, threeHitNum, threeAttemptNum,
							freeThrowHitNum, freeThrowAttemptNum,
							offenReboundNum, defenReboundNum, reboundNum,
							assistNum, stealNum, blockNum, turnOverNum,
							foulNum, personScore);
					records.add(recordVO);
					isComplete = false;
				}
				temp = br.readLine();
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		String[] temp = teams.split("-");
		String visitingTeam = temp[0];
		String homeTeam = temp[1];

		String[] s = score.split("-");
		int visitingScore = Integer.parseInt(s[0]);
		int homeScore = Integer.parseInt(s[1]);

		matchVO = new MatchVO(season, date, visitingTeam, homeTeam,
				visitingScore, homeScore, detailScores, records);
		return matchVO;
	}

	public ArrayList<MatchVO> getMatchesListFromFile() {
		// TODO 自动生成的方法存根
		ArrayList<MatchVO> matches = new ArrayList<MatchVO>();
		try {
			FileList fl = new FileList("src/data/matches");
			ArrayList<String> names = fl.getList();
			for (String name : names) {
				matches.add(readFromMatchFile(name));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return matches;
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
