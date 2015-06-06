package bl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ImageIcon;

import vo.MatchVO;
import vo.RecordVO;
import vo.TeamVO;
import blService.MatchBLService;
import blService.TeamBLService;
import data.TeamData;
import dataservice.TeamDataService;

public class Team implements TeamBLService {
	private TeamDataService teamData;
	private ArrayList<TeamVO> teamBaseList = new ArrayList<TeamVO>();
	private Map<String, TeamVO> teamsBaseInfo = new LinkedHashMap<String, TeamVO>();
	private ArrayList<MatchVO> matchList = new ArrayList<MatchVO>();
	private Map<String, Map<String, MatchVO>> matches = new LinkedHashMap<String, Map<String, MatchVO>>();
	private Map<String, Map<String, MatchVO>> allMatch = new LinkedHashMap<String, Map<String, MatchVO>>();;
	private MatchBLService match;
	private Boolean isSeason = false;
	private Map<String, TeamVO> teamAverageInfo = new LinkedHashMap<String, TeamVO>();

	private static String[] teamsName_CH = new String[] { "太阳", "马刺", "国王",
			"火箭", "湖人", "掘金", "森林狼", "小牛", "快船", "爵士", "勇士", "灰熊", "雷霆", "鹈鹕",
			"开拓者", "凯尔特人", "篮网", "尼克斯", "76人", "猛龙", "公牛", "骑士", "活塞", "步行者",
			"雄鹿", "老鹰", "黄蜂", "热火", "魔术", "奇才" };
	private static String[] teamsName_EN = new String[] { "PHX", "SAS", "SAC",
			"HOU", "LAL", "DEN", "MIN", "DAL", "LAC", "UTA", "GSW", "MEM",
			"OKC", "NOP", "POR", "BOS", "BKN", "NYK", "PHI", "TOR", "CHI",
			"CLE", "DET", "IND", "MIL", "ATL", "CHA", "MIA", "ORL", "WAS" };

	public static void main(String[] args) {
		System.out.println("Team.main()");
		ArrayList<TeamVO> result = new ArrayList<TeamVO>();
		TeamBLService team = new Team();
		// result = team.getTeamBaseInfo();
		// result = team.getTeamAverageInfo("all");
		// result = team.getTeamAverageInfo("Playoff", "NOP");
		// result=team.getTeamSeasonInfo("12-13", "Team");
		// result=team.getTeamSeasonInfo("12-13", "Playoff", "NOP");
		// result=team.getOrderedTeamsByAverage( "all", "score", "desc", 5);
		result = team.getSeasonHotTeam("12-13", "Playoff", "score", 5);
		System.out.println("结果大小：" + result.size());

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
			System.out.println("----------------------------------");

		}
	}

	public Team() {
		teamData = new TeamData();
		match = new Match();
		matchList = match.getMatchData("all", "all", "all", "all", "all");
		matches = changeMatchListToMap(matchList);
		allMatch = matches;
		teamBaseList = getTeamBaseInfo();
		teamsBaseInfo = changeListToMap(teamBaseList);
	}

	/**
	 * 为球员服务的map
	 * 
	 */
	public static Map<String, TeamVO> getTeamsPartition() {
		// TODO 自动生成的方法存根
		String abLocation;
		String conference;
		String partition;

		TeamDataService teamData = new TeamData();
		ArrayList<TeamVO> result = teamData.getTeamBaseInfo();
		Map<String, TeamVO> teams = new HashMap<String, TeamVO>();
		TeamVO team;

		for (TeamVO vo : result) {
			abLocation = vo.getAbLocation();
			conference = vo.getConference();// 东西部
			partition = vo.getPartition();

			team = new TeamVO(abLocation, conference, partition);
			teams.put(abLocation, team);
		}
		return teams;
	}

	public ArrayList<TeamVO> getTeamBaseInfo() {
		// TODO 自动生成的方法存根
		ArrayList<TeamVO> result = new ArrayList<TeamVO>();
		result = teamData.getTeamBaseInfo();
		return result;
	}

	public ArrayList<TeamVO> getTeamSeasonInfo(String season, String type) {
		// TODO 自动生成的方法存根
		ArrayList<TeamVO> result = new ArrayList<TeamVO>();
		Iterator<Entry<String, TeamVO>> iter = teamsBaseInfo.entrySet()
				.iterator();
		while (iter.hasNext()) {
			Map.Entry<String, TeamVO> entry = (Map.Entry<String, TeamVO>) iter
					.next();
			TeamVO vo = (TeamVO) entry.getValue();

			TeamVO teamVO = calculateTeamInfo(vo, season,
					Match.changeTypeCHToEN(type));
			if (teamVO != null) {
				result.add(teamVO);
			}
		}
		return result;
	}

	public ArrayList<TeamVO> getTeamAverageInfo(String type) {
		// TODO 自动生成的方法存根
		calculateTeamAverageInfo(Match.changeTypeCHToEN(type));
		ArrayList<TeamVO> result = changeMapToList(teamAverageInfo);
		return result;
	}

	public ArrayList<TeamVO> getTeamBaseInfo(String name) {
		// TODO 自动生成的方法存根
		ArrayList<TeamVO> result = new ArrayList<TeamVO>();
		result = teamData.getTeamBaseInfo(name);
		return result;
	}

	public ArrayList<TeamVO> getTeamSeasonInfo(String season, String type,
			String name) {
		// TODO 自动生成的方法存根
		ArrayList<TeamVO> result = new ArrayList<TeamVO>();
		Iterator<Entry<String, TeamVO>> iter = teamsBaseInfo.entrySet()
				.iterator();
		while (iter.hasNext()) {
			Map.Entry<String, TeamVO> entry = (Map.Entry<String, TeamVO>) iter
					.next();
			TeamVO vo = (TeamVO) entry.getValue();

			String teamName = vo.getTeamName();
			String team = vo.getAbLocation();

			boolean flag = false;
			if (name.equals("NOP") || name.equals("NOH")) {
				name = teamsBaseInfo.get("NOP").getTeamName();
				if (name == null) {
					name = teamsBaseInfo.get("NOH").getTeamName();
				}
			}
			name = name.toLowerCase();
			flag = teamName.toLowerCase().contains(name)
					|| team.toLowerCase().contains(name);
			if (!flag) {
				// 当前球队不是我要的球队，就跳过他不进行计算
				continue;
			}

			TeamVO teamVO = calculateTeamInfo(vo, season,
					Match.changeTypeCHToEN(type));
			if (teamVO != null) {
				result.add(teamVO);
			}
		}
		return result;
	}

	public ArrayList<TeamVO> getTeamAverageInfo(String type, String name) {
		// TODO 自动生成的方法存根
		ArrayList<TeamVO> result = new ArrayList<TeamVO>();
		Iterator<Entry<String, TeamVO>> iter = teamsBaseInfo.entrySet()
				.iterator();
		while (iter.hasNext()) {
			Map.Entry<String, TeamVO> entry = (Map.Entry<String, TeamVO>) iter
					.next();
			TeamVO vo = (TeamVO) entry.getValue();
			String teamName = vo.getTeamName();
			String team = vo.getAbLocation();

			boolean flag = false;
			if (name.equals("NOP") || name.equals("NOH")) {
				name = teamsBaseInfo.get("NOP").getTeamName();
				if (name == null) {
					name = teamsBaseInfo.get("NOH").getTeamName();
				}
			}

			name = name.toLowerCase();
			flag = teamName.toLowerCase().contains(name)
					|| team.toLowerCase().contains(name);
			if (!flag) {
				// 当前球队不是我要的球队，就跳过他不进行计算
				continue;
			}
			TeamVO teamVO = calculateTeamInfo(vo, "all",
					Match.changeTypeCHToEN(type));
			if (teamVO != null) {
				result.add(teamVO);
			}
		}
		return result;
	}

	public ArrayList<TeamVO> getOrderedTeamsBySeason(String season,
			String type, String condition, String order, int num) {
		// TODO 自动生成的方法存根
		if (num < 0) {
			num = 30;
		}
		ArrayList<TeamVO> teams = getTeamSeasonInfo(season,
				Match.changeTypeCHToEN(type));
		// 未明确写明排序方式时默认是升序
		if (order == null || order.equals("null")) {
			order = "asc";
		}
		Collections.sort(teams, new SequenceOfTeam(condition, order));
		ArrayList<TeamVO> result = new ArrayList<TeamVO>();
		for (int i = 0; i < num; i++) {
			result.add(teams.get(i));
		}
		return result;
	}

	public ArrayList<TeamVO> getOrderedTeamsByAverage(String type,
			String condition, String order, int num) {
		// TODO 自动生成的方法存根
		if (num < 0) {
			num = 30;
		}
		ArrayList<TeamVO> teams = getTeamAverageInfo(Match
				.changeTypeCHToEN(type));
		// 未明确写明排序方式时默认是升序
		if (order == null || order.equals("null")) {
			order = "asc";
		}
		Collections.sort(teams, new SequenceOfTeam(condition, order));
		ArrayList<TeamVO> result = new ArrayList<TeamVO>();
		for (int i = 0; i < num; i++) {
			result.add(teams.get(i));
		}
		return result;
	}

	public ImageIcon getTeamImage(String name) {
		// TODO 自动生成的方法存根
		ImageIcon imageIcon = null;
		if (name.equals("NOH")) {
			imageIcon = new ImageIcon("src/data/teamsPng/" + "NOP" + ".png");
		}
		imageIcon = new ImageIcon("src/data/teamsPng/" + name + ".png");
		return imageIcon;
	}

	public ArrayList<MatchVO> getRecentMatches(String teamName, int num) {
		// TODO 自动生成的方法存根
		ArrayList<MatchVO> result = new ArrayList<MatchVO>();
		ArrayList<MatchVO> allMatchList = new ArrayList<MatchVO>();
		allMatchList = matchList;
		Collections.sort(allMatchList, new SequenceOfMatch());
		for (int i = 0; i < num; i++) {
			result.add(allMatchList.get(i));
		}
		return result;
	}

	public ArrayList<MatchVO> getMatches(String teamName) {
		// TODO 自动生成的方法存根
		ArrayList<MatchVO> result = new ArrayList<MatchVO>();
		result = teamData.getMatches(teamName);
		return result;
	}

	public ArrayList<TeamVO> getSeasonHotTeam(String season, String type,
			String column, int num) {
		// TODO 自动生成的方法存根
		if (num < 0) {
			num = 30;
		}
		ArrayList<TeamVO> result = new ArrayList<TeamVO>();
		ArrayList<TeamVO> teamSeasonInfo = getTeamAverageInfo(Match
				.changeTypeCHToEN(type));
		Collections.sort(teamSeasonInfo, new SequenceOfTeam(column, "desc"));

		for (int i = 0; i < num; i++) {
			result.add(teamSeasonInfo.get(i));
		}
		return result;
	}

	/**
	 * 将球队中文名转为相应的英文缩写
	 * 
	 * @param 球队中文名
	 * @return 英文缩写
	 */
	public static String changeTeamNameCHToEN(String CH) {
		int index = -1;
		for (int i = 0; i < teamsName_CH.length; i++) {
			if (teamsName_CH[i].equals(CH)) {
				index = i;
			}
		}
		if (index != -1) {
			return teamsName_EN[index];
		} else {
			return CH;
		}
	}

	/**
	 * 将球队中文名转为相应的英文缩写
	 * 
	 * @param EN
	 *            球队英文名
	 * @return 中文名
	 */
	public static String changeTeamNameENToCH(String EN) {
		int index = -1;
		for (int i = 0; i < teamsName_CH.length; i++) {
			if (teamsName_EN[i].equals(EN)) {
				index = i;
			}
		}
		if (index != -1) {
			return teamsName_CH[index];
		} else {
			return EN;
		}
	}

	private TeamVO calculateTeamInfo(TeamVO vo, String season, String type) {
		String teamName = vo.getTeamName();
		String team = vo.getAbLocation();
		String location = vo.getLocation();
		String abLocation = vo.getAbLocation();
		String conference = vo.getConference();
		String partition = vo.getPartition();
		String homeCourt = vo.getHomeCourt();
		int setUpTime = vo.getSetUpTime();

		String testSeason = "";
		boolean isCalculate = false;// 标记是否有参加过比赛，防止有球队没有参加这种类型的比赛但是仍对其检索

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
		int dsOffenReboundNumAll = 0;
		int dsDefenReboundNumAll = 0;
		double dsOffenRound = 0;

		boolean isBad = false;

		Map<String, MatchVO> allMatches = new HashMap<String, MatchVO>();

		if (team.equals("NOP")) {
			isBad = true;
		}

		if (season.equals("all")) {
			// 得到全部比赛数据的，还是觉得有点傻逼
			// 思路是将按赛季分的二维map读出来加到一个新的不按赛季分的map中
			int flag = 0;

			// ====================================
			matches = allMatch;

			Iterator<Entry<String, Map<String, MatchVO>>> iter = matches
					.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry<String, Map<String, MatchVO>> entry = (Map.Entry<String, Map<String, MatchVO>>) iter
						.next();
				if (flag == 0) {
					allMatches = entry.getValue();
				} else {
					Map<String, MatchVO> map = new HashMap<String, MatchVO>();
					map = entry.getValue();

					Iterator<Entry<String, MatchVO>> matchIterator = map
							.entrySet().iterator();
					while (matchIterator.hasNext()) {
						Map.Entry<String, MatchVO> matchEntry = (Map.Entry<String, MatchVO>) matchIterator
								.next();
						String key = matchEntry.getKey();
						MatchVO matchVO = matchEntry.getValue();
						allMatches.put(key, matchVO);
					}
				}
				flag++;
			}
		} else {
			// ====================================
			matches = allMatch;
			allMatches = matches.get(season);
		}

		if (allMatches != null) {
			Iterator<Entry<String, MatchVO>> allMatchIter = allMatches
					.entrySet().iterator();
			while (allMatchIter.hasNext()) {
				Map.Entry<String, MatchVO> matchEntry = (Map.Entry<String, MatchVO>) allMatchIter
						.next();
				MatchVO matchVO = (MatchVO) matchEntry.getValue();
				// 筛选比赛类型
				String currentType = matchVO.getType();
				if (!type.equals("all")) {
					if (!currentType.equals(type)) {
						continue;
					}
				}
				testSeason = matchVO.getSeason();
				int homeScore = matchVO.getHomeScore();
				int visitingScore = matchVO.getVisitingScore();
				String homeTeam = matchVO.getHomeTeam();
				String visitingTeam = matchVO.getVisitingTeam();

				boolean isHomeTeam = false;
				boolean isVisitingTeam = false;

				if (team.equals("NOP") || team.equals("NOH")) {
					isHomeTeam = homeTeam.equals("NOP")
							|| homeTeam.equals("NOH");
					isVisitingTeam = visitingTeam.equals("NOP")
							|| visitingTeam.equals("NOH");
				} else {
					isHomeTeam = homeTeam.equals(team);
					isVisitingTeam = visitingTeam.equals(team);
				}
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
					isCalculate = true;
					int perShootAttemptNum = 0;
					int perFreeThrowAttemptNum = 0;
					int perOffenReboundNum = 0;
					int perDefenReboundNum = 0;
					int perMissShoot = 0;
					int perTurnOverNum = 0;

					ArrayList<RecordVO> records = matchVO.getRecords();
					for (RecordVO recordVO : records) {
						boolean isRecord = false;
						if (team.equals("NOP") || team.equals("NOH")) {
							isRecord = recordVO.getTeam().equals("NOP")
									|| recordVO.getTeam().equals("NOH");
						} else {
							isRecord = recordVO.getTeam().equals(team);
						}
						if (isRecord) {
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

							perShootAttemptNum += recordVO.getShootAttemptNum();
							perFreeThrowAttemptNum += recordVO
									.getFreeThrowAttemptNum();
							perOffenReboundNum += recordVO.getOffenReboundNum();
							perDefenReboundNum += recordVO.getDefenReboundNum();
							perMissShoot += recordVO.getShootAttemptNum()
									- recordVO.getShootHitNum();
							perTurnOverNum += recordVO.getTurnOverNum();

						} else {
							// 计算对手的
							dsShootHitNum += recordVO.getShootHitNum();
							dsShootAttempNum += recordVO.getShootAttemptNum();
							dsFreeThrowAttemptNum += recordVO
									.getFreeThrowAttemptNum();
							dsTurnOverNum += recordVO.getTurnOverNum();
							dsOffenReboundNum += recordVO.getOffenReboundNum();
							dsOffenReboundNumAll += recordVO
									.getOffenReboundNum();
							dsDefenReboundNum += recordVO.getDefenReboundNum();
							dsDefenReboundNumAll += recordVO
									.getDefenReboundNum();
						}

					}

					shootHitRate = (double) shootHitNum / shootAttemptNum;// 投篮命中率
					threeHitRate = (double) threeHitNum / threeAttemptNum;// 三分命中率
					freeThrowHitRate = (double) freeThrowHitNum
							/ freeThrowAttemptNum;// 罚球命中率
					winRate = (double) winNum / matchesNum; // 胜率
					// 进攻回合——每场的进攻回合累加
					double temp = perShootAttemptNum
							+ 0.4
							* perFreeThrowAttemptNum
							- 1.07
							* (perOffenReboundNum
									/ (double) (perOffenReboundNum + dsDefenReboundNum) * (perMissShoot))
							+ 1.07 * perTurnOverNum;
					offenRound += temp;

					double dstemp = dsShootAttempNum
							+ 0.4
							* dsFreeThrowAttemptNum
							- 1.07
							* (dsOffenReboundNum
									/ (double) (dsOffenReboundNum + perDefenReboundNum) * (dsShootAttempNum - dsShootHitNum))
							+ 1.07 * dsTurnOverNum;
					dsOffenRound += dstemp;

					dsShootHitNum = 0;
					dsShootAttempNum = 0;
					dsFreeThrowAttemptNum = 0;
					dsTurnOverNum = 0;
					dsOffenReboundNum = 0;
					dsDefenReboundNum = 0;
				}
			}
			if (isCalculate) {
				offenEfficiency = (double) score / offenRound * 100; // 进攻效率
				defenEfficiency = (double) dsScore / dsOffenRound * 100; // 防守效率
				offenReboundEfficiency = (double) offenReboundNum
						/ (offenReboundNum + dsDefenReboundNumAll); // 进攻篮板效率
				defenReboundEfficiency = (double) defenReboundNum
						/ (defenReboundNum + dsOffenReboundNumAll); // 防守篮板效率
				stealEfficiency = (double) stealNum / dsOffenRound * 100; // 抢断效率
				assistEfficiency = (double) assistNum / offenRound * 100; // 助攻率

				if (season.equals("all")) {
					// 需要的是场均数据，要除以比赛场数
					offenRound = offenRound / (double) matchesNum;
					shootHitNum = shootHitNum / (double) matchesNum;
					shootAttemptNum = shootAttemptNum / (double) matchesNum;
					threeHitNum = threeHitNum / (double) matchesNum;
					threeAttemptNum = threeAttemptNum / (double) matchesNum;
					freeThrowHitNum = freeThrowHitNum / (double) matchesNum;
					freeThrowAttemptNum = freeThrowAttemptNum
							/ (double) matchesNum;
					offenReboundNum = offenReboundNum / (double) matchesNum;
					defenReboundNum = defenReboundNum / (double) matchesNum;
					reboundNum = reboundNum / (double) matchesNum;
					assistNum = assistNum / (double) matchesNum;
					stealNum = stealNum / (double) matchesNum;
					blockNum = blockNum / (double) matchesNum;
					turnOverNum = turnOverNum / (double) matchesNum;
					foulNum = foulNum / (double) matchesNum;
					score = score / (double) matchesNum;

					if (testSeason.equals("12-13") && isBad) {
						abLocation = "NOH";
					}
				}
			}
			if (season.equals("12-13") && abLocation.equals("NOP")) {
				abLocation = "NOH";
				isSeason = true;
			}
			// DecimalFormat dec = new DecimalFormat("0.000");
			// TeamVO teamVO = new TeamVO(teamName, abLocation, location,
			// conference, partition, homeCourt, setUpTime, matchesNum,
			// Double.parseDouble(dec.format(shootHitNum)),
			// Double.parseDouble(dec.format(shootAttemptNum)),
			// Double.parseDouble(dec.format(threeHitNum)),
			// Double.parseDouble(dec.format(threeAttemptNum)),
			// Double.parseDouble(dec.format(freeThrowHitNum)),
			// Double.parseDouble(dec.format(freeThrowAttemptNum)),
			// Double.parseDouble(dec.format(offenReboundNum)),
			// Double.parseDouble(dec.format(defenReboundNum)),
			// Double.parseDouble(dec.format(reboundNum)),
			// Double.parseDouble(dec.format(assistNum)),
			// Double.parseDouble(dec.format(stealNum)),
			// Double.parseDouble(dec.format(blockNum)),
			// Double.parseDouble(dec.format(turnOverNum)),
			// Double.parseDouble(dec.format(foulNum)),
			// Double.parseDouble(dec.format(score)),
			// Double.parseDouble(dec.format(shootHitRate)),
			// Double.parseDouble(dec.format(threeHitRate)),
			// Double.parseDouble(dec.format(freeThrowHitRate)),
			// Double.parseDouble(dec.format(winRate)),
			// Double.parseDouble(dec.format(offenRound)),
			// Double.parseDouble(dec.format(offenEfficiency)),
			// Double.parseDouble(dec.format(defenEfficiency)),
			// Double.parseDouble(dec.format(offenReboundEfficiency)),
			// Double.parseDouble(dec.format(defenReboundEfficiency)),
			// Double.parseDouble(dec.format(stealEfficiency)),
			// Double.parseDouble(dec.format(assistEfficiency)));

			TeamVO teamVO = new TeamVO(teamName, abLocation, location,
					conference, partition, homeCourt, setUpTime, matchesNum,
					shootHitNum, shootAttemptNum, threeHitNum, threeAttemptNum,
					freeThrowHitNum, freeThrowAttemptNum, offenReboundNum,
					defenReboundNum, reboundNum, assistNum, stealNum, blockNum,
					turnOverNum, foulNum, score, shootHitRate, threeHitRate,
					freeThrowHitRate, winRate, offenRound, offenEfficiency,
					defenEfficiency, offenReboundEfficiency,
					defenReboundEfficiency, stealEfficiency, assistEfficiency);
			return teamVO;
		} else {
			return null;
		}

	}

	/**
	 * 计算球队所有比赛的平均数据，初始化全局变量teamAverageInfo
	 * 
	 * @return
	 */
	private void calculateTeamAverageInfo(String type) {
		// TODO 自动生成的方法存根
		Iterator<Entry<String, TeamVO>> iter = teamsBaseInfo.entrySet()
				.iterator();
		while (iter.hasNext()) {
			Map.Entry<String, TeamVO> entry = (Map.Entry<String, TeamVO>) iter
					.next();
			TeamVO vo = (TeamVO) entry.getValue();
			String abLocation = vo.getAbLocation();
			TeamVO teamVO = calculateTeamInfo(vo, "all",
					Match.changeTypeCHToEN(type));
			if (teamVO != null) {
				if (isSeason && abLocation.equals("NOP")) {
					abLocation = "NOH";
				}
				teamAverageInfo.put(abLocation, teamVO);
			}
		}
	}

	private HashMap<String, TeamVO> changeListToMap(ArrayList<TeamVO> list) {
		HashMap<String, TeamVO> result = new HashMap<String, TeamVO>();
		for (int i = 0; i < list.size(); i++) {
			TeamVO vo = list.get(i);
			String key = vo.getAbLocation();
			result.put(key, vo);
		}
		return result;
	}

	private HashMap<String, Map<String, MatchVO>> changeMatchListToMap(
			ArrayList<MatchVO> list) {
		HashMap<String, Map<String, MatchVO>> matchesMap = new HashMap<String, Map<String, MatchVO>>();
		for (MatchVO vo : list) {
			String key = vo.getSeason() + "_" + vo.getDate() + "_"
					+ vo.getVisitingTeam() + "-" + vo.getHomeTeam();
			String season = vo.getSeason();
			Map<String, MatchVO> test = matchesMap.get(season);
			if (test == null) {
				Map<String, MatchVO> map = new HashMap<String, MatchVO>();
				map.put(key, vo);
				matchesMap.put(season, map);
			} else {
				test.put(key, vo);
			}
		}
		return matchesMap;
	}

	private ArrayList<TeamVO> changeMapToList(Map<String, TeamVO> teams) {
		ArrayList<TeamVO> result = new ArrayList<TeamVO>();
		Iterator<Entry<String, TeamVO>> iter = teams.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, TeamVO> entry = (Map.Entry<String, TeamVO>) iter
					.next();
			TeamVO vo = (TeamVO) entry.getValue();
			result.add(vo);
		}
		return result;
	}
}