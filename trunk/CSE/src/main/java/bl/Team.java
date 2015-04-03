package bl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;

import vo.MatchVO;
import vo.RecordVO;
import vo.TeamVO;
import blservice.TeamBLService;

public class Team implements TeamBLService {
	private ArrayList<TeamVO> teamsBaseInfo = new ArrayList<TeamVO>();
	private ArrayList<MatchVO> matches = new ArrayList<MatchVO>();
	private ArrayList<TeamVO> teamAverageInfo = new ArrayList<TeamVO>();

	// 删去了readFromMatchFile和getMatches两个私有方法，改为创建一个match类，从中得到比赛信息
	public Team() {
		// TODO 自动生成的构造函数存根
		teamsBaseInfo = getTeams();
		Match match = new Match();
		matches = match.getMatchData("全部", "全部", "全部", "全部");
		teamAverageInfo = calculateTeamAverageInfo();
	}

	public static void main(String[] args) {
		Team team = new Team();
		String season = "13-14";
		ArrayList<TeamVO> result = new ArrayList<TeamVO>();
		// ArrayList<MatchVO> result = new ArrayList<MatchVO>();
		// result = team.getTeamSeasonInfo(season);
		result = team.getTeamAverageInfo();
		result = team.getSeasonHotTeam(season, "比赛得分");
		// result=team.getTeamSeasonInfo(season);
		// result = team.getRecentMatches("ATL");
		// result=team.getMatches();
		System.out.println(result.size());

		for (TeamVO vo : result) {
			System.out.println(vo.getAbLocation() + " " + vo.getScore());
			// System.out.println(vo.getAbLocation());
			// System.out.println("winRate：" + vo.getWinRate());
			// System.out.println("shootHitNum：" + vo.getShootHitNum());
			// System.out.println("shootAttemptNum：" + vo.getShootAttemptNum());
			// System.out.println("threeHitNum：" + vo.getThreeHitNum());
			// System.out.println("threeAttemptNum：" + vo.getThreeAttemptNum());
			// System.out.println("freeThrowHitNum：" + vo.getFreeThrowHitNum());
			// System.out.println("freeThrowAttemptNum："
			// + vo.getFreeThrowAttemptNum());
			// System.out.println("offenReboundNum：" + vo.getOffenReboundNum());
			// System.out.println("defenReboundNum：" + vo.getDefenReboundNum());
			// System.out.println("reboundNum：" + vo.getReboundNum());
			// System.out.println("assistNum：" + vo.getAssistNum());
			// System.out.println("stealNum：" + vo.getStealNum());
			// System.out.println("blockNum：" + vo.getBlockNum());
			// System.out.println("turnOverNum：" + vo.getTurnOverNum());
			// System.out.println("foulNum：" + vo.getFoulNum());
			// System.out.println("score：" + vo.getScore());
			// System.out.println("shootHitRate:" + vo.getShootHitRate());
			// System.out.println("threeHitRate:" + vo.getThreeHitRate());
			// System.out.println("freeThrowHitRate:" +
			// vo.getFreeThrowHitRate());
			// System.out.println("offenRound:" + vo.getOffenRound());
			// System.out.println("offenEfficiency:" + vo.getOffenEfficiency());
			// System.out.println("defenEfficiency:" + vo.getDefenEfficiency());
			// System.out.println("offenReboundEfficiency:"
			// + vo.getOffenReboundEfficiency());
			// System.out.println("defenReboundEfficiency:"
			// + vo.getDefenReboundEfficiency());
			// System.out.println("stealEfficiency:" + vo.getStealEfficiency());
			// System.out.println("assistRate:" + vo.getAssistRate());
			// System.out.println("----------------------------------");

		}

		// for (MatchVO vo : result) {
		// System.out.println(vo.getVisitingScore() + ":" + vo.getHomeScore());
		// }

	}

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

	private ArrayList<TeamVO> getTeams() {
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

			team = new TeamVO(name, abLocation, location, conference,
					partition, homeCourt, setUpTime);
			teams.add(team);
		}
		return teams;
	}

	private ArrayList<TeamVO> calculateTeamAverageInfo() {
		// TODO 自动生成的方法存根
		ArrayList<TeamVO> result = new ArrayList<TeamVO>();
		ArrayList<TeamVO> teams = new ArrayList<TeamVO>();
		teams = getTeamBaseInfo();
		for (TeamVO vo : teams) {
			String teamName = vo.getTeamName();
			String team = vo.getAbLocation();
			String location = vo.getLocation();
			String abLocation = vo.getAbLocation();
			String conference = vo.getConference();
			String partition = vo.getPartition();
			String homeCourt = vo.getHomeCourt();
			int setUpTime = vo.getSetUpTime();

			int matchesNum = 0; // 比赛场数
			int winNum = 0;
			double shootHitNum = 0; // 投篮命中数
			double shootAttemptNum = 0; // 投篮出手次数
			double threeHitNum = 0; // 三分命中数
			double threeAttemptNum = 0; // 三分出手数
			double freeThrowHitNum = 0; // 罚球命中数
			double freeThrowAttemptNum = 0; // 罚球出手数
			double offenReboundNum = 0; // 进攻篮板数
			double defenReboundNum = 0; // 防守篮板数
			double reboundNum = 0;// 篮板数
			double assistNum = 0;// 助攻数
			double stealNum = 0;// 抢断数
			double blockNum = 0;// 盖帽数
			double turnOverNum = 0;// 失误数
			double foulNum = 0;// 犯规数
			double score = 0;// 比赛得分
			double shootHitRate = 0;// 投篮命中率
			double threeHitRate = 0;// 三分命中率
			double freeThrowHitRate = 0;// 罚球命中率
			double winRate = 0; // 胜率
			double offenRound = 0; // 进攻回合
			double offenEfficiency = 0; // 进攻效率
			double defenEfficiency = 0; // 防守效率
			double offenReboundEfficiency = 0; // 进攻篮板效率
			double defenReboundEfficiency = 0; // 防守篮板效率
			double stealEfficiency = 0; // 抢断效率
			double assistEfficiency = 0; // 助攻率

			int dsShootHitNum = 0;
			int dsShootAttempNum = 0;
			int dsFreeThrowAttemptNum = 0;
			int dsTurnOverNum = 0;
			int dsScore = 0;
			int dsOffenReboundNum = 0;
			int dsDefenReboundNum = 0;
			double dsOffenRound = 0;
			for (MatchVO matchVO : matches) {
				int homeScore = matchVO.getHomeScore();
				int visitingScore = matchVO.getVisitingScore();
				String homeTeam = matchVO.getHomeTeam();
				String visitingTeam = matchVO.getVisitingTeam();

				boolean isHomeTeam = homeTeam.equals(team);
				boolean isVisitingTeam = visitingTeam.equals(team);
				if (isHomeTeam) {
					matchesNum++;
					score += homeScore;
					dsScore += visitingScore;
					if (homeScore > visitingScore) {
						winNum++;
					}
				} else if (isVisitingTeam) {
					matchesNum++;
					score += visitingScore;
					dsScore += homeScore;
					if (visitingScore > homeScore) {
						winNum++;
					}
				}
				if (isHomeTeam || isVisitingTeam) {
					ArrayList<RecordVO> records = matchVO.getRecords();
					for (RecordVO recordVO : records) {
						if (recordVO.getTeam().equals(team)) {
							shootHitNum += recordVO.getShootHitNum(); // 投篮命中数
							shootAttemptNum += recordVO.getShootAttemptNum(); // 投篮出手次数
							threeHitNum += recordVO.getThreeHitNum(); // 三分命中数
							threeAttemptNum += recordVO.getThreeAttemptNum(); // 三分出手数
							freeThrowHitNum += recordVO.getFreeThrowHitNum(); // 罚球命中数
							freeThrowAttemptNum += recordVO
									.getFreeThrowAttemptNum(); // 罚球出手数
							offenReboundNum += recordVO.getOffenReboundNum(); // 进攻篮板数
							defenReboundNum += recordVO.getDefenReboundNum(); // 防守篮板数
							reboundNum += recordVO.getReboundNum();// 篮板数
							assistNum += recordVO.getAssistNum();// 助攻数
							stealNum += recordVO.getStealNum();// 抢断数
							blockNum += recordVO.getBlockNum();// 盖帽数
							turnOverNum += recordVO.getTurnOverNum();// 失误数
							foulNum += recordVO.getFoulNum();// 犯规数
						} else {
							// 计算对手的
							dsShootHitNum += recordVO.getShootHitNum();
							dsShootAttempNum += recordVO.getShootAttemptNum();
							dsFreeThrowAttemptNum += recordVO
									.getFreeThrowAttemptNum();
							dsTurnOverNum += recordVO.getTurnOverNum();
							dsOffenReboundNum += recordVO.getOffenReboundNum();
							dsDefenReboundNum += recordVO.getDefenReboundNum();
						}
					}

					shootHitRate = (double) shootHitNum / shootAttemptNum;// 投篮命中率
					threeHitRate = (double) threeHitNum / threeAttemptNum;// 三分命中率
					freeThrowHitRate = (double) freeThrowHitNum
							/ freeThrowAttemptNum;// 罚球命中率
					winRate = (double) winNum / matchesNum; // 胜率
					// 进攻回合
					offenRound = shootAttemptNum
							+ 0.4
							* freeThrowAttemptNum
							- 1.07
							* (offenReboundNum
									/ (double) (offenReboundNum + dsDefenReboundNum) * (shootAttemptNum - shootHitNum))
							+ 1.07 * turnOverNum;
					dsOffenRound = dsShootAttempNum
							+ 0.4
							* dsFreeThrowAttemptNum
							- 1.07
							* (dsOffenReboundNum
									/ (double) (dsOffenReboundNum + defenReboundNum) * (dsShootAttempNum - dsShootHitNum))
							+ 1.07 * dsTurnOverNum;

					offenEfficiency = (double) score / offenRound * 100; // 进攻效率
					defenEfficiency = (double) dsScore / dsOffenRound * 100; // 防守效率
					offenReboundEfficiency = (double) offenReboundNum
							/ (offenReboundNum + dsDefenReboundNum); // 进攻篮板效率
					defenReboundEfficiency = (double) defenReboundNum
							/ (defenReboundNum + dsOffenReboundNum); // 防守篮板效率
					stealEfficiency = (double) stealNum / dsOffenRound * 100; // 抢断效率
					assistEfficiency = (double) assistNum / offenRound * 100; // 助攻率
				}
			}
			TeamVO teamVO = new TeamVO(teamName, abLocation, location,
					conference, partition, homeCourt, setUpTime, matchesNum,
					shootHitNum, shootAttemptNum, threeHitNum, threeAttemptNum,
					freeThrowHitNum, freeThrowAttemptNum, offenReboundNum,
					defenReboundNum, reboundNum, assistNum, stealNum, blockNum,
					turnOverNum, foulNum, score, shootHitRate, threeHitRate,
					freeThrowHitRate, winRate, offenRound, offenEfficiency,
					defenEfficiency, offenReboundEfficiency,
					defenReboundEfficiency, stealEfficiency, assistEfficiency);
			result.add(teamVO);
		}

		return result;
	}

	public ArrayList<TeamVO> getTeamBaseInfo() {
		return teamsBaseInfo;
	}

	public ArrayList<TeamVO> getTeamSeasonInfo(String season) {
		// TODO 自动生成的方法存根
		ArrayList<TeamVO> result = new ArrayList<TeamVO>();
		ArrayList<TeamVO> teams = new ArrayList<TeamVO>();
		teams = getTeamBaseInfo();
		for (TeamVO vo : teams) {
			String teamName = vo.getTeamName();
			String team = vo.getAbLocation();
			String location = vo.getLocation();
			String abLocation = vo.getAbLocation();
			String conference = vo.getConference();
			String partition = vo.getPartition();
			String homeCourt = vo.getHomeCourt();
			int setUpTime = vo.getSetUpTime();

			int matchesNum = 0; // 比赛场数
			int winNum = 0;
			double shootHitNum = 0; // 投篮命中数
			double shootAttemptNum = 0; // 投篮出手次数
			double threeHitNum = 0; // 三分命中数
			double threeAttemptNum = 0; // 三分出手数
			double freeThrowHitNum = 0; // 罚球命中数
			double freeThrowAttemptNum = 0; // 罚球出手数
			double offenReboundNum = 0; // 进攻篮板数
			double defenReboundNum = 0; // 防守篮板数
			double reboundNum = 0;// 篮板数
			double assistNum = 0;// 助攻数
			double stealNum = 0;// 抢断数
			double blockNum = 0;// 盖帽数
			double turnOverNum = 0;// 失误数
			double foulNum = 0;// 犯规数
			double score = 0;// 比赛得分
			double shootHitRate = 0;// 投篮命中率
			double threeHitRate = 0;// 三分命中率
			double freeThrowHitRate = 0;// 罚球命中率
			double winRate = 0; // 胜率
			double offenRound = 0; // 进攻回合
			double offenEfficiency = 0; // 进攻效率
			double defenEfficiency = 0; // 防守效率
			double offenReboundEfficiency = 0; // 进攻篮板效率
			double defenReboundEfficiency = 0; // 防守篮板效率
			double stealEfficiency = 0; // 抢断效率
			double assistEfficiency = 0; // 助攻率

			int dsShootHitNum = 0;
			int dsShootAttempNum = 0;
			int dsFreeThrowAttemptNum = 0;
			int dsTurnOverNum = 0;
			int dsScore = 0;
			int dsOffenReboundNum = 0;
			int dsDefenReboundNum = 0;
			double dsOffenRound = 0;
			for (MatchVO matchVO : matches) {
				if (matchVO.getSeason().equals(season)) {
					int homeScore = matchVO.getHomeScore();
					int visitingScore = matchVO.getVisitingScore();
					String homeTeam = matchVO.getHomeTeam();
					String visitingTeam = matchVO.getVisitingTeam();

					boolean isHomeTeam = homeTeam.equals(team);
					boolean isVisitingTeam = visitingTeam.equals(team);
					if (isHomeTeam) {
						matchesNum++;
						score += homeScore;
						dsScore += visitingScore;
						if (homeScore > visitingScore) {
							winNum++;
						}
					} else if (isVisitingTeam) {
						matchesNum++;
						score += visitingScore;
						dsScore += homeScore;
						if (visitingScore > homeScore) {
							winNum++;
						}
					}
					if (isHomeTeam || isVisitingTeam) {
						ArrayList<RecordVO> records = matchVO.getRecords();
						for (RecordVO recordVO : records) {
							if (recordVO.getTeam().equals(team)) {
								shootHitNum += recordVO.getShootHitNum(); // 投篮命中数
								shootAttemptNum += recordVO
										.getShootAttemptNum(); // 投篮出手次数
								threeHitNum += recordVO.getThreeHitNum(); // 三分命中数
								threeAttemptNum += recordVO
										.getThreeAttemptNum(); // 三分出手数
								freeThrowHitNum += recordVO
										.getFreeThrowHitNum(); // 罚球命中数
								freeThrowAttemptNum += recordVO
										.getFreeThrowAttemptNum(); // 罚球出手数
								offenReboundNum += recordVO
										.getOffenReboundNum(); // 进攻篮板数
								defenReboundNum += recordVO
										.getDefenReboundNum(); // 防守篮板数
								reboundNum += recordVO.getReboundNum();// 篮板数
								assistNum += recordVO.getAssistNum();// 助攻数
								stealNum += recordVO.getStealNum();// 抢断数
								blockNum += recordVO.getBlockNum();// 盖帽数
								turnOverNum += recordVO.getTurnOverNum();// 失误数
								foulNum += recordVO.getFoulNum();// 犯规数
							} else {
								// 计算对手的
								dsShootHitNum += recordVO.getShootHitNum();
								dsShootAttempNum += recordVO
										.getShootAttemptNum();
								dsFreeThrowAttemptNum += recordVO
										.getFreeThrowAttemptNum();
								dsTurnOverNum += recordVO.getTurnOverNum();
								dsOffenReboundNum += recordVO
										.getOffenReboundNum();
								dsDefenReboundNum += recordVO
										.getDefenReboundNum();
							}
						}

						shootHitRate = (double) shootHitNum / shootAttemptNum;// 投篮命中率
						threeHitRate = (double) threeHitNum / threeAttemptNum;// 三分命中率
						freeThrowHitRate = (double) freeThrowHitNum
								/ freeThrowAttemptNum;// 罚球命中率
						winRate = (double) winNum / matchesNum; // 胜率
						// 进攻回合
						offenRound = shootAttemptNum
								+ 0.4
								* freeThrowAttemptNum
								- 1.07
								* (offenReboundNum
										/ (double) (offenReboundNum + dsDefenReboundNum) * (shootAttemptNum - shootHitNum))
								+ 1.07 * turnOverNum;
						dsOffenRound = dsShootAttempNum
								+ 0.4
								* dsFreeThrowAttemptNum
								- 1.07
								* (dsOffenReboundNum
										/ (double) (dsOffenReboundNum + defenReboundNum) * (dsShootAttempNum - dsShootHitNum))
								+ 1.07 * dsTurnOverNum;

						offenEfficiency = (double) score / offenRound * 100; // 进攻效率
						defenEfficiency = (double) dsScore / dsOffenRound * 100; // 防守效率
						offenReboundEfficiency = (double) offenReboundNum
								/ (offenReboundNum + dsDefenReboundNum); // 进攻篮板效率
						defenReboundEfficiency = (double) defenReboundNum
								/ (defenReboundNum + dsOffenReboundNum); // 防守篮板效率
						stealEfficiency = (double) stealNum / dsOffenRound
								* 100; // 抢断效率
						assistEfficiency = (double) assistNum / offenRound
								* 100; // 助攻率
					}
				}
			}
			TeamVO teamVO = new TeamVO(teamName, abLocation, location,
					conference, partition, homeCourt, setUpTime, matchesNum,
					shootHitNum, shootAttemptNum, threeHitNum, threeAttemptNum,
					freeThrowHitNum, freeThrowAttemptNum, offenReboundNum,
					defenReboundNum, reboundNum, assistNum, stealNum, blockNum,
					turnOverNum, foulNum, score, shootHitRate, threeHitRate,
					freeThrowHitRate, winRate, offenRound, offenEfficiency,
					defenEfficiency, offenReboundEfficiency,
					defenReboundEfficiency, stealEfficiency, assistEfficiency);
			result.add(teamVO);
		}

		return result;
	}

	public ArrayList<TeamVO> getTeamAverageInfo() {
		// TODO 自动生成的方法存根
		return teamAverageInfo;
	}

	public ArrayList<TeamVO> getTeamBaseInfo(String name) {
		// TODO 自动生成的方法存根
		ArrayList<TeamVO> result = new ArrayList<TeamVO>();
		for (TeamVO teamVO : teamsBaseInfo) {
			String teamName = teamVO.getTeamName().toLowerCase();
			String abLocation = teamVO.getAbLocation().toLowerCase();
			name = name.toLowerCase();
			if (teamName.contains(name) || abLocation.contains(name)) {
				result.add(teamVO);
			}
		}
		return result;
	}

	public ArrayList<TeamVO> getTeamSeasonInfo(String season, String name) {
		// TODO 自动生成的方法存根
		ArrayList<TeamVO> result = new ArrayList<TeamVO>();
		ArrayList<TeamVO> teams = new ArrayList<TeamVO>();
		teams = getTeamSeasonInfo(season);
		for (TeamVO vo : teams) {
			String teamName = vo.getTeamName().toLowerCase();
			String abLocation = vo.getAbLocation().toLowerCase();
			name = name.toLowerCase();
			if (teamName.contains(name) || abLocation.contains(name)) {
				result.add(vo);
			}
		}
		return result;
	}

	public ArrayList<TeamVO> getTeamAverageInfo(String name) {
		// TODO 自动生成的方法存根
		ArrayList<TeamVO> result = new ArrayList<TeamVO>();
		ArrayList<TeamVO> teams = new ArrayList<TeamVO>();
		teams = getTeamAverageInfo();
		for (TeamVO vo : teams) {
			String teamName = vo.getTeamName().toLowerCase();
			String abLocation = vo.getAbLocation().toLowerCase();
			name = name.toLowerCase();
			if (teamName.contains(name) || abLocation.contains(name)) {
				result.add(vo);
			}
		}
		return result;
	}

	public ArrayList<TeamVO> getOrderedTeamsBySeason(String season,
			String condition, String order) {
		// TODO 自动生成的方法存根
		ArrayList<TeamVO> teams = new ArrayList<TeamVO>();
		teams = getTeamSeasonInfo(season);
		Collections.sort(teams, new SequenceOfTeam(condition, order));
		return teams;
	}

	public ArrayList<TeamVO> getOrderedTeamsByAverage(String condition,
			String order) {
		// TODO 自动生成的方法存根
		ArrayList<TeamVO> teams = new ArrayList<TeamVO>();
		teams = teamAverageInfo;
		Collections.sort(teams, new SequenceOfTeam(condition, order));
		return teams;
	}

	/**
	 * @param name
	 *            球队缩写
	 */
	public ImageIcon getTeamImage(String name) {
		// TODO 自动生成的方法存根
		ImageIcon imageIcon = new ImageIcon("src/data/teamsPng/" + name
				+ ".png");
		return imageIcon;
	}

	public ArrayList<MatchVO> getRecentMatches(String teamName) {
		// TODO 自动生成的方法存根
		ArrayList<MatchVO> result = new ArrayList<MatchVO>();
		result = getRecentMatches(teamName, 5);
		return result;
	}

	public ArrayList<MatchVO> getMatches(String teamName) {
		// TODO 自动生成的方法存根
		ArrayList<MatchVO> result = new ArrayList<MatchVO>();
		result = getRecentMatches(teamName, -1);
		return result;
	}

	/**
	 * 根据球队名得到近期比赛的某种数据
	 * 
	 * @param teamName
	 *            球队名缩写，完全匹配
	 * @param num
	 *            得到比赛的场数 ，-1则是取出全部数据
	 * @return
	 */
	private ArrayList<MatchVO> getRecentMatches(String teamName, int num) {
		ArrayList<MatchVO> result = new ArrayList<MatchVO>();
		int count = 0;
		for (MatchVO matchVO : matches) {
			if (num > 0) {// -1则是取出全部数据
				if (count >= num) {
					break;
				}
			}
			String visitingTeam = matchVO.getVisitingTeam();
			String homeTeam = matchVO.getHomeTeam();
			if (homeTeam.equals(teamName) || visitingTeam.equals(teamName)) {
				result.add(matchVO);
				count++;
			}
		}
		// 要对比赛进行排序吗==
		return result;
	}

	public ArrayList<TeamVO> getSeasonHotTeam(String season, String column) {
		// TODO 自动生成的方法存根
		ArrayList<TeamVO> result = new ArrayList<TeamVO>();
		ArrayList<TeamVO> teamSeasonInfo = new ArrayList<TeamVO>();
		teamSeasonInfo = getTeamSeasonInfo(season);
		Collections.sort(teamSeasonInfo, new SequenceOfTeam(column, "desc"));
		for (int i = 0; i < 5; i++) {
			TeamVO vo = teamSeasonInfo.get(i);
			result.add(vo);
		}
		return result;
	}

}
