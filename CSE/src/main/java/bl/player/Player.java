package bl.player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ImageIcon;

import vo.LittleRecordVO;
import vo.MatchVO;
import vo.PlayerVO;
import vo.RecordVO;
import vo.TeamVO;
import bl.DataSourse;
import bl.DirtyDataManager;
import bl.FileList;
import bl.match.SequenceOfMatch;
import bl.team.FinalTeam;
import blservice.AgeEnum;
import blservice.PlayerBLService;

public class Player implements PlayerBLService {

	Map<String, PlayerVO> players = new HashMap<String, PlayerVO>(32);
	Map<String, TeamVO> teams;
	Map<Integer, MatchVO> matches = new HashMap<Integer, MatchVO>(1024);
	ArrayList<MatchVO> allSeasonMatches = new ArrayList<MatchVO>();
	ArrayList<PlayerVO> playersSeason = new ArrayList<PlayerVO>();
	ArrayList<PlayerVO> playersAverage = new ArrayList<PlayerVO>();
	ArrayList<PlayerVO> playersToday = new ArrayList<PlayerVO>();
	boolean isCalculate = false;
	boolean isCalculatePlayersSeason = false;
	boolean isCalculatePlayersAverage = false;
	boolean isCalculatePlayersToday = false;
	private FileList matchFiles;
	int matchCount = 0;
	boolean needUpdatePlayerSeason=false;
	boolean needUpdatePlayerAverage=false;

	public Player() {
		baseInfoInit();
		teams = FinalTeam.getTeamsPartition();
		allMatchInfoInit();
//		updateMatch um = new updateMatch();    //!!!!注意，这个是检查性能时交代码时才交！！
//		um.startThread();
	}

	private void baseInfoInit() {

		try {
			FileList fl = new FileList(DataSourse.dataSourse + "/players/info");
			ArrayList<String> names = fl.getList();
			for (String name : names) {
				readBaseInfoFromFile(name);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private PlayerVO readBaseInfoFromFile(String fileName) {
		PlayerVO player;
		String name;
		String number;
		String position;
		String height;
		int weight;
		String birth;
		int age;
		int exp;
		String school;
		String[] content = new String[9];
		try {
			File file = new File(fileName);
			if (!file.exists()) {

				file.createNewFile();
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "UTF-8"));
			int i = 0;
			String temp = null;
			temp = br.readLine();
			while (temp != null) {
				if (temp.contains("│")) {
					String[] it = temp.split("│");
					String[] nit = it[1].split("║");
					content[i++] = nit[0].trim();
				}
				temp = br.readLine();
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		name = content[0];
		number = content[1];
		position = content[2];
		height = content[3];
		weight = Integer.parseInt(content[4]);
		birth = content[5];
		age = Integer.parseInt(content[6]);
		exp = DirtyDataManager.checkExp(content[7]);
		school = content[8];
		player = new PlayerVO(name, number, position, height, weight, birth,
				age, exp, school);
		players.put(name, player);
		return player;
	}

	private void allMatchInfoInit() {

		try {
			matchFiles = new FileList(DataSourse.dataSourse + "/matches");
			ArrayList<String> names = matchFiles.getList();
			for (String name : names) {
				readMatchInfoFromFile(name);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 初始化后 开启线程：
		// updateMatch um = new updateMatch();
		// um.startThread();
	}

	private MatchVO readMatchInfoFromFile(String fileName) {
		MatchVO theMatch=null;
		String season;// 赛季
		String date = null;// 比赛日期
		String teams = null;// 对阵队伍
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

			String[] teamstemp = teams.split("-");
			String visitingTeam = teamstemp[0];
			String homeTeam = teamstemp[1];

			temp = br.readLine();
			String[] scoresData = temp.split(";");
			int partNum = scoresData.length;

			String team = null;// 球队
			String playerName = null;// 球员名
			String position = null;// 位置
			String presentTimeString = null;
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

			temp = br.readLine();
			while (temp != null) {
				if (fileName.contains(temp)) {
					team = temp;
				} else {
					String[] line = temp.split(";");
					playerName = line[0];
					position = line[1];
					presentTimeString = DirtyDataManager.checkPresentTime(
							fileName, team, playerName, line[2]);// 在场时间
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

					RecordVO recordVO = new RecordVO(team, playerName,
							position, presentTimeString, shootHitNum,
							shootAttemptNum, threeHitNum, threeAttemptNum,
							freeThrowHitNum, freeThrowAttemptNum,
							offenReboundNum, defenReboundNum, reboundNum,
							assistNum, stealNum, blockNum, turnOverNum,
							foulNum, personScore);
					records.add(recordVO);
				}
				temp = br.readLine();

			}
			theMatch = new MatchVO(season, date, visitingTeam,
					homeTeam, partNum, records);
			allSeasonMatches.add(theMatch);

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return theMatch;

	}

	public ArrayList<PlayerVO> getPlayerSeasonInfo(String season) {

		if (isCalculatePlayersSeason == true&& needUpdatePlayerSeason==false) {
			Collections.sort(playersSeason, new SequenceOfPlayer());
			return playersSeason;
		}

		if (isCalculate == false) {
			getPlayerAllMatchesInfo();
			isCalculate = true;
		}
		
		playersSeason.clear();
		Iterator<Entry<String, PlayerVO>> iter = players.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			PlayerVO playerSeason = (PlayerVO) entry.getValue();
			playersSeason.add(playerSeason);
		}

		Collections.sort(playersSeason, new SequenceOfPlayer());
		isCalculatePlayersSeason = true;
		needUpdatePlayerSeason=false;
		return playersSeason;
	}

	
	
	private void getPlayerAllMatchesInfo(){
		for (MatchVO match : allSeasonMatches) {
			getPlayerMatchInfo(match);
		}
		
		Iterator<Entry<String, PlayerVO>> iter = players.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			PlayerVO playerSeason = (PlayerVO) entry.getValue();
            calculatePlayerSeason(playerSeason);
		}

	}
	
	
	private void calculatePlayerSeason(PlayerVO playerSeason){
		int playedGames;

		playedGames = playerSeason.getPlayedGames();

		if (playedGames != 0) {

			int allMatchTime = 0;
			int teamReboundNum = 0;
			int dsReboundNum = 0;
			int teamOffenReboundNum = 0;
			int dsOffenReboundNum = 0;
			int teamDefenReboundNum = 0;
			int dsDefenReboundNum = 0;
			int teamShootHitNum = 0;
			int dsTwoAttemptNum = 0;
			int teamShootAttemptNum = 0;
			int teamFreeThrowAttemptNum = 0;
			int teamTurnOverNum = 0;
			double dsOffenRoundNum = 0;
			double shootHitRate = 0;
			double threeHitRate = 0;
			double freeThrowHitRate = 0;
			double efficiency = 0;
			double recentFiveMatchesScoreUpRate = 0;
			double recentFiveMatchesReboundUpRate = 0;
			double recentFiveMatchesAssistUpRate = 0;
			double GmScEfficiencyValue = 0;
			double trueHitRate = 0;
			double shootEfficiency = 0;
			double reboundRate = 0;
			double offenReboundRate = 0;
			double defenReboundRate = 0;
			double assistRate = 0;
			double stealRate = 0;
			double blockRate = 0;
			double turnOverRate = 0;
			double usageRate = 0;
			double score_rebound_assist = 0;

			double allreboundNum = playerSeason.getReboundNum();
			double allassistNum = playerSeason.getAssistNum();
			double allpresentTime = playerSeason.getPresentTime();
			double allshootHitNum = playerSeason.getShootHitNum();
			double allshootAttemptNum = playerSeason.getShootAttemptNum();
			double allthreeHitNum = playerSeason.getThreeHitNum();
			double allthreeAttemptNum = playerSeason.getThreeAttemptNum();
			double allfreeThrowHitNum = playerSeason.getFreeThrowHitNum();
			double allfreeThrowAttemptNum = playerSeason
					.getFreeThrowAttemptNum();
			double alloffenReboundNum = playerSeason.getOffenReboundNum();
			double alldefenReboundNum = playerSeason.getDefenReboundNum();
			double allstealNum = playerSeason.getStealNum();
			double allblockNum = playerSeason.getBlockNum();
			double allfoulNum = playerSeason.getFoulNum();
			double allturnOverNum = playerSeason.getTurnOverNum();
			double allscore = playerSeason.getScore();

			shootHitRate = allshootHitNum / allshootAttemptNum;
			threeHitRate = allthreeHitNum / allthreeAttemptNum;
			freeThrowHitRate = allfreeThrowHitNum / allfreeThrowAttemptNum;
			efficiency = (allscore + allreboundNum + allassistNum
					+ allstealNum + allblockNum)
					- (allshootAttemptNum - allshootHitNum)
					- (allfreeThrowAttemptNum - allfreeThrowHitNum)
					- allturnOverNum;

			ArrayList<Integer> matchIDs = playerSeason.getMatchesID();
			Map<Integer, Boolean> isVisitingTeam = playerSeason
					.getIsVisitingTeam();
			if (matchIDs.size() < 5) {
				recentFiveMatchesScoreUpRate = 0;
				recentFiveMatchesReboundUpRate = 0;
				recentFiveMatchesAssistUpRate = 0;
			} else {
				LittleRecordVO[] fiveRecordVOs = playerSeason
						.getFiveRecentRecords();

				double recentFiveScore = 0;
				double recentReboundNum = 0;
				double recentAssistNum = 0;
				for (int i = 0; i < 5; i++) {
					LittleRecordVO vo = fiveRecordVOs[i];
					recentFiveScore += vo.getScore();
					recentReboundNum += vo.getReboundNum();
					recentAssistNum += vo.getAssistNum();
				}
				double beforeRecentFiveScore = allscore - recentFiveScore;
				double beforeRecentReboundNum = allreboundNum
						- recentReboundNum;
				double beforeRecentAssistNum = allassistNum
						- recentAssistNum;
				recentFiveMatchesScoreUpRate = ((recentFiveScore / 5) - beforeRecentFiveScore
						/ (playedGames - 5))
						/ (beforeRecentFiveScore / (playedGames - 5));
				recentFiveMatchesReboundUpRate = ((recentReboundNum / 5) - beforeRecentReboundNum
						/ (playedGames - 5))
						/ (beforeRecentReboundNum / (playedGames - 5));
				recentFiveMatchesAssistUpRate = ((recentAssistNum / 5) - beforeRecentAssistNum
						/ (playedGames - 5))
						/ (beforeRecentAssistNum / (playedGames - 5));
			}

			GmScEfficiencyValue = allscore + 0.4 * allshootHitNum - 0.7
					* allshootAttemptNum - 0.4
					* (allfreeThrowAttemptNum - allfreeThrowHitNum) + 0.7
					* alloffenReboundNum + 0.3 * alldefenReboundNum
					+ allstealNum + 0.7 * allassistNum + 0.7 * allblockNum
					- 0.4 * allfoulNum - allturnOverNum;
			trueHitRate = (double) allscore
					/ (2 * (allshootAttemptNum + 0.44 * allfreeThrowAttemptNum));
			shootEfficiency = (double) (allshootHitNum + 0.5 * allthreeHitNum)
					/ allshootAttemptNum;
			for (Integer id : matchIDs) {
				MatchVO match = matches.get(id);
				allMatchTime += match.getMatchTime();
				if (isVisitingTeam.get(id) == true) {
					teamOffenReboundNum += match
							.getVisitingOffenReboundNum();
					dsOffenReboundNum += match.getHomeOffenReboundNum();
					teamDefenReboundNum += match
							.getVisitingDefenReboundNum();
					dsDefenReboundNum += match.getHomeDefenReboundNum();
					teamShootHitNum += match.getVisitingShootHitNum();
					dsTwoAttemptNum += match.getHomeShootAttemptNum()
							- match.getHomeThreeAttemptNum();
					teamShootAttemptNum += match
							.getVisitingShootAttemptNum();
					teamFreeThrowAttemptNum += match
							.getVisitingFreeThrowAttemptNum();
					teamTurnOverNum += match.getVisitingTurnOverNum();
					dsOffenRoundNum += match.getHomeShootAttemptNum()
							+ 0.4
							* match.getHomeFreeThrowAttemptNum()
							- 1.07
							* ((double) match.getHomeOffenReboundNum()
									/ (match.getHomeOffenReboundNum() + match
											.getVisitingDefenReboundNum()) * (match
									.getHomeShootAttemptNum() - match
									.getHomeShootHitNum())) + 1.07
							* match.getHomeTurnOverNum();
				} else {
					teamOffenReboundNum += match.getHomeOffenReboundNum();
					dsOffenReboundNum += match.getVisitingOffenReboundNum();
					teamDefenReboundNum += match.getHomeDefenReboundNum();
					dsDefenReboundNum += match.getVisitingDefenReboundNum();
					teamShootHitNum += match.getHomeShootHitNum();
					dsTwoAttemptNum += match.getVisitingShootAttemptNum()
							- match.getVisitingThreeAttemptNum();
					teamShootAttemptNum += match.getHomeShootAttemptNum();
					teamFreeThrowAttemptNum += match
							.getHomeFreeThrowAttemptNum();
					teamTurnOverNum += match.getHomeTurnOverNum();
					dsOffenRoundNum += match.getVisitingShootAttemptNum()
							+ 0.4
							* match.getVisitingFreeThrowAttemptNum()
							- 1.07
							* ((double) match.getVisitingOffenReboundNum()
									/ (match.getVisitingOffenReboundNum() + match
											.getHomeDefenReboundNum()) * (match
									.getVisitingShootAttemptNum() - match
									.getVisitingShootHitNum())) + 1.07
							* match.getVisitingTurnOverNum();

				}
			}
			teamReboundNum = teamOffenReboundNum + teamDefenReboundNum;
			dsReboundNum = dsOffenReboundNum + dsDefenReboundNum;

			reboundRate = allreboundNum * allMatchTime / allpresentTime
					/ (teamReboundNum + dsReboundNum);
			offenReboundRate = alloffenReboundNum * allMatchTime
					/ allpresentTime
					/ (teamOffenReboundNum + dsOffenReboundNum);
			defenReboundRate = alldefenReboundNum * allMatchTime
					/ allpresentTime
					/ (teamDefenReboundNum + dsDefenReboundNum);
			assistRate = allassistNum
					/ (allpresentTime / allMatchTime * teamShootHitNum - allshootHitNum);
			stealRate = (double) allassistNum * allMatchTime
					/ allpresentTime / dsOffenRoundNum;
			blockRate = allblockNum * allMatchTime / allpresentTime
					/ dsTwoAttemptNum;
			turnOverRate = allturnOverNum
					/ (allshootAttemptNum - allthreeAttemptNum + 0.4
							* allfreeThrowAttemptNum + allturnOverNum);
			usageRate = (allshootAttemptNum + 0.44 * allfreeThrowAttemptNum + allturnOverNum)
					* allMatchTime
					/ allpresentTime
					/ (teamShootAttemptNum + 0.44 * teamFreeThrowAttemptNum + teamTurnOverNum);
			score_rebound_assist = (allscore + allreboundNum + allassistNum) / 3;

			playerSeason.setShootHitRate(shootHitRate);
			playerSeason.setThreeHitRate(threeHitRate);
			playerSeason.setFreeThrowHitRate(freeThrowHitRate);
			playerSeason.setEfficiency(efficiency);
			playerSeason
					.setRecentFiveMatchesScoreUpRate(recentFiveMatchesScoreUpRate);
			playerSeason
					.setRecentFiveMatchesReboundUpRate(recentFiveMatchesReboundUpRate);
			playerSeason
					.setRecentFiveMatchesAssistUpRate(recentFiveMatchesAssistUpRate);
			playerSeason.setGmScEfficiencyValue(GmScEfficiencyValue);
			playerSeason.setTrueHitRate(trueHitRate);
			playerSeason.setShootEfficiency(shootEfficiency);
			playerSeason.setReboundRate(reboundRate);
			playerSeason.setOffenReboundRate(offenReboundRate);
			playerSeason.setDefenReboundRate(defenReboundRate);
			playerSeason.setAssistRate(assistRate);
			playerSeason.setStealRate(stealRate);
			playerSeason.setBlockRate(blockRate);
			playerSeason.setTurnOverRate(turnOverRate);
			playerSeason.setUsageRate(usageRate);
			playerSeason.setScore_rebound_assist(score_rebound_assist);
		}
	}
	
	private void getPlayerMatchInfo(MatchVO match) {

			String season = match.getSeason();
			String date = match.getDate();
			String homeTeam = match.getHomeTeam();
			String visitingTeam = match.getVisitingTeam();

			MatchVO thisMatch = new MatchVO(season, date, visitingTeam,
					homeTeam);
			thisMatch.setMatchTime(match.getMatchTime());

			String team = null;// 球队
			String playerName = null;// 球员名
			String position = null;// 位置
			int presentTime = 0;// 在场时间
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

			for (RecordVO record : match.getRecords()) {
				team = record.getTeam();
				playerName = record.getPlayerName();
				position = record.getPosition();
				presentTime = convertMinuteToSecond(record.getPresentTime());
				shootHitNum = record.getShootHitNum();// 投篮命中数
				shootAttemptNum = record.getShootAttemptNum();// 投篮出手数
				threeHitNum = record.getThreeHitNum();// 三分命中数
				threeAttemptNum = record.getThreeAttemptNum();// 三分出手数
				freeThrowHitNum = record.getFreeThrowHitNum();// 罚球命中数
				freeThrowAttemptNum = record.getFreeThrowAttemptNum();// 罚球出手数
				offenReboundNum = record.getOffenReboundNum();// 进攻（前场）篮板数
				defenReboundNum = record.getDefenReboundNum();// 防守（后场）篮板数
				reboundNum = record.getReboundNum();// 总篮板数
				assistNum = record.getAssistNum();// 助攻数
				stealNum = record.getStealNum();// 抢断数
				blockNum = record.getBlockNum();// 盖帽数
				turnOverNum = record.getTurnOverNum();// 失误数
				foulNum = record.getFoulNum();// 犯规数
				personScore = record.getScore();// 个人得分
				PlayerVO thisPlayer = players.get(playerName);
				if (thisPlayer == null)
					continue;
				LittleRecordVO littleRecordVO = new LittleRecordVO(season,
						date, personScore, reboundNum, assistNum);

				thisPlayer.addPlayedGames();
				if (!position.equals(""))
					thisPlayer.addGameStartingNum();

				if (thisPlayer.getMostRecentMatch() == null) {
					thisPlayer.setOwingTeam(team);
					TeamVO teamVO=teams.get(team);
					if(teamVO==null){
						teamVO=teams.get("NOP");
					}
					thisPlayer.setLeague(teamVO.getConference());
					thisPlayer.setMostRecentMatch(season + "_" + date);
				} else {
					if (thisPlayer.getMostRecentMatch().compareTo(
							season + "_" + date) < 0) {
						thisPlayer.setOwingTeam(team);
						TeamVO teamVO=teams.get(team);
						if(teamVO==null){
							teamVO=teams.get("NOP");
						}
						thisPlayer.setLeague(teamVO.getConference());
						thisPlayer.setMostRecentMatch(season + "_" + date);
					}
				}

				thisPlayer.addPresentTime(presentTime);
				thisPlayer.addShootHitNum(shootHitNum);
				thisPlayer.addShootAttemptNum(shootAttemptNum);
				thisPlayer.addThreeHitNum(threeHitNum);
				thisPlayer.addThreeAttemptNum(threeAttemptNum);
				thisPlayer.addFreeThrowHitNum(freeThrowHitNum);
				thisPlayer.addFreeThrowAttemptNum(freeThrowAttemptNum);
				thisPlayer.addOffenReboundNum(offenReboundNum);
				thisPlayer.addDefenReboundNum(defenReboundNum);
				thisPlayer.addReboundNum(reboundNum);
				thisPlayer.addAssistNum(assistNum);
				thisPlayer.addStealNum(stealNum);
				thisPlayer.addBlockNum(blockNum);
				thisPlayer.addTurnOverNum(turnOverNum);
				thisPlayer.addFoulNum(foulNum);
				thisPlayer.addScore(personScore);

				if (team.equals(visitingTeam))
					thisPlayer.addMatchesID(matchCount, true);
				else
					thisPlayer.addMatchesID(matchCount, false);

				thisPlayer.addFiveRecentRecords(littleRecordVO);

				// 计算两双
				int tempDouble = 0;
				if (personScore >= 10)
					tempDouble++;
				if (reboundNum >= 10)
					tempDouble++;
				if (assistNum >= 10)
					tempDouble++;
				if (stealNum >= 10)
					tempDouble++;
				if (blockNum >= 10)
					tempDouble++;
				if (tempDouble >= 2)
					thisPlayer.addDoubleDoubleNum();

				if (team.equals(homeTeam)) {
					thisMatch.addHomeShootHitNum(shootHitNum);
					thisMatch.addHomeShootAttemptNum(shootAttemptNum);
					thisMatch.addHomeThreeHitNum(threeHitNum);
					thisMatch.addHomeThreeAttemptNum(threeAttemptNum);
					thisMatch.addHomeFreeThrowHitNum(freeThrowHitNum);
					thisMatch.addHomeFreeThrowAttemptNum(freeThrowAttemptNum);
					thisMatch.addHomeOffenReboundNum(offenReboundNum);
					thisMatch.addHomeDefenReboundNum(defenReboundNum);
					thisMatch.addHomeAssistNum(assistNum);
					thisMatch.addHomeStealNum(stealNum);
					thisMatch.addHomeBlockNum(blockNum);
					thisMatch.addHomeTurnOverNum(turnOverNum);
					thisMatch.addHomeFoulNum(foulNum);
				} else {
					thisMatch.addVisitingShootHitNum(shootHitNum);
					thisMatch.addVisitingShootAttemptNum(shootAttemptNum);
					thisMatch.addVisitingThreeHitNum(threeHitNum);
					thisMatch.addVisitingThreeAttemptNum(threeAttemptNum);
					thisMatch.addVisitingFreeThrowHitNum(freeThrowHitNum);
					thisMatch
							.addVisitingFreeThrowAttemptNum(freeThrowAttemptNum);
					thisMatch.addVisitingOffenReboundNum(offenReboundNum);
					thisMatch.addVisitingDefenReboundNum(defenReboundNum);
					thisMatch.addVisitingAssistNum(assistNum);
					thisMatch.addVisitingStealNum(stealNum);
					thisMatch.addVisitingBlockNum(blockNum);
					thisMatch.addVisitingTurnOverNum(turnOverNum);
					thisMatch.addVisitingFoulNum(foulNum);
				}

			}
			matches.put(matchCount, thisMatch);
			matchCount++;

	}

	public ArrayList<PlayerVO> getPlayerAverageInfo() {

		if (isCalculatePlayersAverage == true&&needUpdatePlayerAverage==false) {
			Collections.sort(playersAverage, new SequenceOfPlayer());
			return playersAverage;
		}

		if (isCalculate == false) {
			getPlayerAllMatchesInfo();
			isCalculate = true;
		}
		
		playersAverage.clear();
		Iterator<Entry<String, PlayerVO>> iter = players.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			PlayerVO playerSeason = (PlayerVO) entry.getValue();
			int playedGames = playerSeason.getPlayedGames();
			
			PlayerVO newPlayer=new PlayerVO(playerSeason.getName(), playerSeason.getNumber(),
					playerSeason.getPosition(), playerSeason.getHeight(), playerSeason.getWeight(), 
					playerSeason.getBirth(), playerSeason.getAge(), playerSeason.getExp(), 
					playerSeason.getSchool(), playerSeason.getOwingTeam(),playerSeason.getLeague(),
					playedGames, playerSeason.getGameStartingNum(),
					playerSeason.getReboundNum()/ playedGames, playerSeason.getAssistNum() / playedGames,
					playerSeason.getPresentTime()/ playedGames, playerSeason.getShootHitNum()/ playedGames,
					playerSeason.getShootAttemptNum()/ playedGames, playerSeason.getShootHitRate(),
					playerSeason.getThreeHitNum()/ playedGames, playerSeason.getThreeAttemptNum()/ playedGames,
					playerSeason.getThreeHitRate(),playerSeason.getFreeThrowHitNum()/ playedGames,
					playerSeason.getFreeThrowAttemptNum() / playedGames, playerSeason.getFreeThrowHitRate(),
					playerSeason.getOffenReboundNum()/ playedGames, playerSeason.getDefenReboundNum()/ playedGames,
					playerSeason.getStealNum() / playedGames, playerSeason.getBlockNum() / playedGames,
					playerSeason.getTurnOverNum()/ playedGames, playerSeason.getFoulNum() / playedGames,
					playerSeason.getScore() / playedGames, playerSeason.getEfficiency(),
					playerSeason.getRecentFiveMatchesScoreUpRate(),playerSeason.getRecentFiveMatchesReboundUpRate(),
					playerSeason.getRecentFiveMatchesAssistUpRate(), playerSeason.getGmScEfficiencyValue(),
					playerSeason.getTrueHitRate(), playerSeason.getShootHitEfficiency(),
					playerSeason.getReboundRate(), playerSeason.getOffenReboundRate(),
					playerSeason.getDefenReboundRate(), playerSeason.getAssistRate(), playerSeason.getStealRate(),
					playerSeason.getBlockRate(), playerSeason.getTurnOverRate(), playerSeason.getUsageRate(),
					playerSeason.getScore_rebound_assist() / playedGames, playerSeason.getDoubleDoubleNum()/ playedGames);


			playersAverage.add(newPlayer);
		}

		Collections.sort(playersAverage, new SequenceOfPlayer());
		isCalculatePlayersAverage = true;
		needUpdatePlayerAverage=false;
		return playersAverage;
	}

	public PlayerVO getPlayerSeasonInfo(String season, String name) {
		if (isCalculate == false) {
			getPlayerAllMatchesInfo();
			isCalculate = true;
		}
		return players.get(name);
	}

	public PlayerVO getPlayerAverageInfo(String name) {
		ArrayList<PlayerVO> result = getPlayerAverageInfo();
		for (PlayerVO vo : result) {
			if (vo.getName().equals(name)) {
				return vo;
			}
		}
		return null;
	}

	public ArrayList<PlayerVO> getOrderedPlayersBySeason(String season,
			String condition, String order, int num) throws RemoteException {
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
		ArrayList<PlayerVO> allPlayers = getPlayerSeasonInfo(season);
		Collections.sort(allPlayers, new SequenceOfPlayer(condition, order));
		int count = 0;
		for (PlayerVO vo : allPlayers) {
			result.add(vo);
			count++;
			if (count >= num)
				break;
		}
		return result;
	}

	public ArrayList<PlayerVO> getOrderedPlayersByAverage(String condition,
			String order, int num) {
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
		ArrayList<PlayerVO> allPlayers = getPlayerAverageInfo();
		Collections.sort(allPlayers, new SequenceOfPlayer(condition, order));
		int count = 0;
		for (PlayerVO vo : allPlayers) {
			result.add(vo);
			count++;
			if (count >= num)
				break;
		}
		return result;
	}

	private ArrayList<PlayerVO> selectPlayers(ArrayList<PlayerVO> thePlayers,
			String position, String union, AgeEnum ageClass, String column,
			String order, int num) {
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
		if (!position.equals("all")) {
			for (int i = 0; i < thePlayers.size(); i++) {
				if (!thePlayers.get(i).getPosition().contains(position))
					thePlayers.remove(i);
			}
		}
		if (!union.equals("all")) {
			for (int i = 0; i < thePlayers.size(); i++) {
				if (!thePlayers.get(i).getLeague().equals(union))
					thePlayers.remove(i);
			}
		}
		if (ageClass != AgeEnum.ALL) {
			for (int i = 0; i < thePlayers.size(); i++) {
				int age = thePlayers.get(i).getAge();
				if (ageClass == AgeEnum.LE22) {
					if (age > 22)
						thePlayers.remove(i);
				} else if (ageClass == AgeEnum.M22_LE25) {
					if (age <= 22 || age > 25)
						thePlayers.remove(i);
				} else if (ageClass == AgeEnum.M25_LE30) {
					if (age <= 25 || age > 30)
						thePlayers.remove(i);
				} else {
					if (age <= 30)
						thePlayers.remove(i);
				}
			}
		}

		Collections.sort(thePlayers, new SequenceOfPlayer(column, order));

		int count = 0;
		for (PlayerVO vo : thePlayers) {
			result.add(vo);
			count++;
			if (count >= num)
				break;
		}
		return result;
	}

	public ArrayList<PlayerVO> selectPlayersBySeason(String season,
			String position, String union, AgeEnum ageClass, String column,
			String order, int num) {
		ArrayList<PlayerVO> thePlayers = getPlayerSeasonInfo(season);
		return selectPlayers(thePlayers, position, union, ageClass, column,
				order, num);
	}

	public ArrayList<PlayerVO> selectPlayersByAverage(String position,
			String union, AgeEnum ageClass, String column, String order, int num) {
		ArrayList<PlayerVO> thePlayers = getPlayerAverageInfo();
		return selectPlayers(thePlayers, position, union, ageClass, column,
				order, num);
	}

	public ImageIcon getPlayerPortraitImage(String name) {
		ImageIcon imageIcon = new ImageIcon(DataSourse.dataSourse
				+ "/players/portrait/" + name + ".png");
		return imageIcon;
	}

	public ImageIcon getPlayerActionImage(String name) {
		ImageIcon imageIcon = new ImageIcon(DataSourse.dataSourse
				+ "/players/action/" + name + ".png");
		return imageIcon;
	}

	private String getToday() {
		String result;
		int year;
		int month;
		Calendar cal = Calendar.getInstance();
		java.text.SimpleDateFormat f = new java.text.SimpleDateFormat("MM-dd");
		String todayDate = f.format(cal.getTime());
		year = cal.get(Calendar.YEAR);
		year -= 2000;
		month = cal.get(Calendar.MONTH);
		if (month <= 6) {
			result = (year - 1) + "-" + year;
		} else {
			result = year + "-" + (year + 1);
		}
		String todaySeason = result;
		result = todaySeason + "_" + todayDate;
		return result;
	}

	private ArrayList<MatchVO> getTodayMatch() {
		ArrayList<MatchVO> result = new ArrayList<MatchVO>();
		String[] buffer = getToday().split("_");
		String season = buffer[0];
		String date = buffer[1];
		for (MatchVO match : allSeasonMatches) {
			if (match.getSeason().equals(season)
					&& match.getDate().equals(date))
				result.add(match);
		}
		return result;
	}

	private void calCulateDayData() {

		ArrayList<MatchVO> theMatches = getTodayMatch();
		for (MatchVO match : theMatches) {

			String team = null;// 球队名
			String playerName = null;// 球员名
			int presentTime = 0;// 在场时间
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

			for (RecordVO record : match.getRecords()) {
				team = record.getTeam();
				playerName = record.getPlayerName();
				presentTime = convertMinuteToSecond(record.getPresentTime());
				shootHitNum = record.getShootHitNum();// 投篮命中数
				shootAttemptNum = record.getShootAttemptNum();// 投篮出手数
				threeHitNum = record.getThreeHitNum();// 三分命中数
				threeAttemptNum = record.getThreeAttemptNum();// 三分出手数
				freeThrowHitNum = record.getFreeThrowHitNum();// 罚球命中数
				freeThrowAttemptNum = record.getFreeThrowAttemptNum();// 罚球出手数
				offenReboundNum = record.getOffenReboundNum();// 进攻（前场）篮板数
				defenReboundNum = record.getDefenReboundNum();// 防守（后场）篮板数
				reboundNum = record.getReboundNum();// 总篮板数
				assistNum = record.getAssistNum();// 助攻数
				stealNum = record.getStealNum();// 抢断数
				blockNum = record.getBlockNum();// 盖帽数
				turnOverNum = record.getTurnOverNum();// 失误数
				foulNum = record.getFoulNum();// 犯规数
				personScore = record.getScore();// 个人得分
				PlayerVO thisPlayer = players.get(playerName);
				PlayerVO player = new PlayerVO(thisPlayer.getName(),
						thisPlayer.getNumber(), thisPlayer.getPosition(),
						thisPlayer.getHeight(), thisPlayer.getWeight(),
						thisPlayer.getBirth(), thisPlayer.getAge(),
						thisPlayer.getExp(), thisPlayer.getSchool());
				player.setOwingTeam(team);
				player.setPresentTime(presentTime);
				player.setShootHitNum(shootHitNum);
				player.setShootAttemptNum(shootAttemptNum);
				player.setThreeHitNum(threeHitNum);
				player.setThreeAttemptNum(threeAttemptNum);
				player.setFreeThrowHitNum(freeThrowHitNum);
				player.setFreeThrowAttemptNum(freeThrowAttemptNum);
				player.setOffenReboundNum(offenReboundNum);
				player.setDefenReboundNum(defenReboundNum);
				player.setReboundNum(reboundNum);
				player.setAssistNum(assistNum);
				player.setStealNum(stealNum);
				player.setBlockNum(blockNum);
				player.setTurnOverNum(turnOverNum);
				player.setFoulNum(foulNum);
				player.setScore(personScore);

				// 计算两双
				int tempDouble = 0;
				if (personScore >= 10)
					tempDouble++;
				if (reboundNum >= 10)
					tempDouble++;
				if (assistNum >= 10)
					tempDouble++;
				if (stealNum >= 10)
					tempDouble++;
				if (blockNum >= 10)
					tempDouble++;
				if (tempDouble >= 2)
					player.addDoubleDoubleNum();

				playersToday.add(player);
			}
		}

	}

	public ArrayList<PlayerVO> getDayHotPlayer(String column, int num) {
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();

		if (isCalculatePlayersToday == false) {
			calCulateDayData();
			isCalculatePlayersToday = true;
		}

		Collections.sort(playersToday, new SequenceOfPlayer(column, "desc"));

		int count = 0;
		for (PlayerVO vo : playersToday) {
			result.add(vo);
			count++;
			if (count >= num)
				break;
		}

		return result;
	}

	public ArrayList<PlayerVO> getSeasonHotPlayer(String season, String column,
			int num) {

		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
		ArrayList<PlayerVO> thePlayers = getPlayerAverageInfo();

		Collections.sort(thePlayers, new SequenceOfPlayer(column, "desc"));
		int count = 0;
		for (PlayerVO vo : thePlayers) {
			result.add(vo);
			count++;
			if (count >= num)
				break;
		}

		return result;
	}

	public ArrayList<PlayerVO> getBestImprovedPlayer(String column, int num) {
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
		ArrayList<PlayerVO> thePlayers = getPlayerAverageInfo();

		Collections.sort(thePlayers, new SequenceOfPlayer(column, "desc"));
		int count = 0;
		for (PlayerVO vo : thePlayers) {
			result.add(vo);
			count++;
			if (count >= num)
				break;
		}

		return result;
	}

	public ArrayList<PlayerVO> getPlayersByInitialName(char character) {
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
		ArrayList<PlayerVO> thePlayers = getPlayerAverageInfo();
		for (PlayerVO vo : thePlayers) {
			if (vo.getName().startsWith(character + ""))
				result.add(vo);
		}
		return result;
	}

	public ArrayList<MatchVO> getRecentMatches(String playerName, int num) {
		ArrayList<MatchVO> result = new ArrayList<MatchVO>();
		int count = 0;
		Collections.sort(allSeasonMatches, new SequenceOfMatch());
		for (MatchVO vo : allSeasonMatches) {
			for (RecordVO vv : vo.getRecords()) {
				if (vv.getPlayerName().equals(playerName)) {
					result.add(vo);
					count++;
					if (count >= num)
						return result;
				}
			}
		}
		return result;
	}

	public ArrayList<MatchVO> getMatches(String playerName) {
		ArrayList<MatchVO> result = new ArrayList<MatchVO>();
		Collections.sort(allSeasonMatches, new SequenceOfMatch());
		for (MatchVO vo : allSeasonMatches) {
			for (RecordVO vv : vo.getRecords()) {
				if (vv.getPlayerName().equals(playerName))
					result.add(vo);
			}
		}
		return result;
	}

	public ArrayList<PlayerVO> getPlayersByTeam(String teamAbLocation) {
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
		ArrayList<PlayerVO> thePlayers = getPlayerAverageInfo();
		for (PlayerVO vo : thePlayers) {
			if (vo.getOwingTeam().equals(teamAbLocation))
				result.add(vo);
		}
		return result;
	}

	public ArrayList<PlayerVO> getPlayerBaseInfo() {
		return getPlayerAverageInfo();
	}

	public ArrayList<PlayerVO> getPlayerBaseInfo(String name) {
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
		ArrayList<PlayerVO> thePlayers = getPlayerAverageInfo();
		for (PlayerVO vo : thePlayers) {
			if (vo.getName().contains(name))
				result.add(vo);
		}
		return result;
	}

	private int convertMinuteToSecond(String s) {
		String[] temp = s.split(":");
		int minute = Integer.parseInt(temp[0]);
		int second = Integer.parseInt(temp[1]);
		return minute * 60 + second;
	}

	/**
	 * 向match中增加比赛信息
	 * 
	 * @param newName
	 *            动态增加的比赛的文件名
	 */
	private void add(ArrayList<String> newName) {
		for (String str : newName) {
			MatchVO vo=readMatchInfoFromFile(str);
			getPlayerMatchInfo(vo);
			ArrayList<RecordVO> records=vo.getRecords();
			for(RecordVO recordVO:records){
				PlayerVO thisPlayer=players.get(recordVO.getPlayerName());
				if(thisPlayer!=null){
				  calculatePlayerSeason(thisPlayer);
				  needUpdatePlayerSeason=true;
				  needUpdatePlayerAverage=true;
				}
			}
		}
	}

	// 放在Filelist或Match里均可
	class updateMatch extends Thread {
		boolean stop;

		public updateMatch() {
			stop = false;
		}

		public void run() {
			while (!stop) {
				ArrayList<String> addPaths=matchFiles.checkMatchesChange();
				if(addPaths!=null){
					add(addPaths);
				// System.out.println("我在很认真的检查呀，港荣蒸蛋糕真的好吃，字打错了灭");
					System.out.println("有新文件啦！！");
					ArrayList<PlayerVO> oo=getPlayerAverageInfo();
					System.out.println(oo.get(405).getName()+" "+oo.get(405).getPlayedGames()+" "+oo.get(405).getEfficiency());
				}
				try {
					this.sleep(2000);// 话说 能不能不睡
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		public void startThread() {
			this.start();
		}

		public void stopThead() {
			this.stop = true;
		}
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Player player = new Player();
		PlayerVO vo = player.getPlayerAverageInfo("Al Horford");
		 ArrayList<PlayerVO> players=player.getPlayerAverageInfo();
		 System.out.println(players.get(405).getName()+" "+players.get(405).getPlayedGames()+" "+players.get(405).getEfficiency());
//		 PlayerVO vo=players.get(0);
		 System.out.println(vo.getName());
		 System.out.println(vo.getOwingTeam());
		 System.out.println(vo.getLeague());
		 System.out.println(vo.getPlayedGames());
		 System.out.println(vo.getGameStartingNum());
		 System.out.println(vo.getReboundNum());
		 System.out.println(vo.getAssistNum());
		 System.out.println(vo.getPresentTime());
		 System.out.println(vo.getShootHitRate());
		 System.out.println(vo.getThreeHitRate());
		 System.out.println(vo.getFreeThrowHitRate());
		 System.out.println(vo.getOffenReboundNum());
		 System.out.println(vo.getDefenReboundNum());
		 System.out.println(vo.getStealNum());
		 System.out.println(vo.getBlockNum());
		 System.out.println(vo.getFoulNum());
		 System.out.println(vo.getTurnOverNum());
		 System.out.println(vo.getScore());
		 System.out.println(vo.getEfficiency());
		 System.out.println(vo.getRecentFiveMatchesScoreUpRate());
		 System.out.println(vo.getRecentFiveMatchesReboundUpRate());
		 System.out.println(vo.getRecentFiveMatchesAssistUpRate());
		 System.out.println(vo.getGmScEfficiencyValue());
		 System.out.println(vo.getTrueHitRate());
		 System.out.println(vo.getShootHitEfficiency());
		 System.out.println(vo.getReboundRate());
		 System.out.println(vo.getOffenReboundRate());
		 System.out.println(vo.getDefenReboundRate());
		 System.out.println(vo.getAssistRate());
		 System.out.println(vo.getStealRate());
		 System.out.println(vo.getBlockRate());
		 System.out.println(vo.getTurnOverRate());
		 System.out.println(vo.getUsageRate());
		 System.out.println(vo.getScore_rebound_assist());
		 System.out.println(vo.getDoubleDoubleNum());
		long end = System.currentTimeMillis();
		System.out.println("运行时间：" + (end - start) + "毫秒");// 应该是end - start
	}

}