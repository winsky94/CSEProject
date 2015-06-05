package bl;

import java.awt.Image;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.ImageIcon;

import blService.MatchBLService;
import blService.PlayerBLService;
import data.PlayerData;
import dataservice.PlayerDataService;
import vo.LittleRecordVO;
import vo.MatchVO;
import vo.PlayerVO;
import vo.RecordVO;
import vo.TeamVO;

public class Player implements PlayerBLService{

	Map<String, PlayerVO> playersActive = new HashMap<String, PlayerVO>(32);
	Map<String, PlayerVO> playersHistoric = new HashMap<String, PlayerVO>(256);
	Map<String, TeamVO> teams;
	Map<Integer, MatchVO> matches = new HashMap<Integer, MatchVO>(1024);
	ArrayList<PlayerVO> playersAveragePreceding;
	ArrayList<PlayerVO> playersAverageRegular;
	ArrayList<PlayerVO> playersAveragePlayoff;
	boolean isCalculateAveragePreceding=false;
	boolean isCalculateAverageRegular=false;
	boolean isCalculateAveragePlayoff=false;
	
	private static String[] player_CH = new String[] { "全部", "前锋", "中锋",
		"后卫","前锋-中锋","中锋-后卫","前锋-后卫", "得分", "篮板数", "助攻数", "得分/篮板/助攻(1:1:1)", 
		"投篮命中率", "盖帽数", "抢断数", "罚球命中率", "犯规数", "失误数",
		"在场时间", "效率", "两双", "西部球队", "东部球队", 
		 "真实命中率", "GmSc效率值", "投篮效率", "篮板率", "进攻篮板数",
		 "防守篮板数", "进攻篮板率", "防守篮板率", "助攻率", "抢断率", 
		 "盖帽率", "失误率", "使用率"};
    private static String[] player_EN = new String[] { "all", "F", "C",
		"G", "F-C","C-G","F-G","score", "reboundNum", "assistNum", "score_rebound_assist", 
		"shootHitRate", "blockNum", "stealNum", "freeThrowHitRate", "foulNum", "turnOverNum",
		"presentTime", "efficiency", "doubleDoubleNum", "W", "E",
		"trueHitRate","GmScEfficiencyValue","shootEfficiency","reboundRate","offenReboundNum",
		"defenReboundNum","offenReboundRate","defenReboundRate","assistRate","stealRate",
		"blockRate","turnOverRate","usageRate"};
    
    public Player(){
    	PlayerDataService player=new PlayerData();
    	playersActive=player.getPlayerActiveBaseInfo();
    	playersHistoric=player.getPlayerHistoricBaseInfo();
    	teams=Team.getTeamsPartition();
    }
    
    
    public ArrayList<PlayerVO> getPlayerActiveBaseInfo(){
    	ArrayList<PlayerVO> pp=new ArrayList<PlayerVO>();
    	Iterator<Entry<String, PlayerVO>> iter = playersActive.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			PlayerVO p = (PlayerVO) entry.getValue();
			pp.add(p);
		}
		return pp;	
    }
    
    public ArrayList<PlayerVO> getPlayerHistoricBaseInfo(){
    	ArrayList<PlayerVO> pp=new ArrayList<PlayerVO>();
    	Iterator<Entry<String, PlayerVO>> iter = playersHistoric.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			PlayerVO p = (PlayerVO) entry.getValue();
			pp.add(p);
		}
		return pp;	
    }
    
    public ArrayList<PlayerVO> getPlayerBaseInfo(String name){
    	ArrayList<PlayerVO> pp=new ArrayList<PlayerVO>();
    	Iterator<Entry<String, PlayerVO>> iter = playersHistoric.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			PlayerVO p = (PlayerVO) entry.getValue();
			if(p.getName().contains(name))
			    pp.add(p);
		}
		return pp;	
    }
    
    private ArrayList<MatchVO> getAllMatches() {
        MatchBLService match=new Match();
        return match.getMatchData("all","all","all", "all", "all");
	}
    
    private ArrayList<MatchVO> getTypeMatches(String type){
    	MatchBLService match=new Match();
        return match.getMatchData("all",Match.changeTypeCHToEN(type),"all", "all", "all");
    }

	private ArrayList<MatchVO> getSeasonMatches(String season,String type) {
          MatchBLService match=new Match();
          return match.getMatchData(season,Match.changeTypeCHToEN(type),"all", "all", "all");
	}

	
	public ArrayList<PlayerVO> getPlayerSeasonInfo(String season,String type){
     
		Map<String, PlayerVO> theSeasonPlayers=new HashMap<String, PlayerVO>(32);
		ArrayList<PlayerVO> result;
		matches.clear();
		
		for (MatchVO match : getSeasonMatches(season,Match.changeTypeCHToEN(type))) {
			getPlayerMatchInfo(theSeasonPlayers,match);
		}

	    result=calculatePlayer(theSeasonPlayers);
	    Collections.sort(result, new SequenceOfPlayer());
        return result;
	}
	
	public ArrayList<PlayerVO> getPlayerSeasonInfo(String season,String type,String name){
		Map<String, PlayerVO> theSeasonPlayers=new HashMap<String, PlayerVO>(32);
		ArrayList<PlayerVO> result=new ArrayList<PlayerVO>();
		ArrayList<PlayerVO> buffer;
		matches.clear();
		
		for (MatchVO match : getSeasonMatches(season,Match.changeTypeCHToEN(type))) {
			getPlayerMatchInfo(theSeasonPlayers,match);
		}

		buffer=calculatePlayer(theSeasonPlayers);
		
		for(int i=0;i<buffer.size();i++){
			PlayerVO vo=buffer.get(i);
			if(vo.getName().contains(name))
				result.add(vo);
		}
	    return result;
	}

    private ArrayList<PlayerVO> calculatePlayer(Map<String, PlayerVO> playersSeason){
	  ArrayList<PlayerVO> result=new ArrayList<PlayerVO>();
	  Iterator<Entry<String, PlayerVO>> iter = playersSeason.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			PlayerVO playerSeason = (PlayerVO) entry.getValue();
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
			double allEfficiency=playerSeason.getEfficiency();
			double allGmScEfficiency=playerSeason.getGmScEfficiencyValue();

//			DecimalFormat dec = new DecimalFormat("0.00");

			shootHitRate = allshootHitNum / allshootAttemptNum;
//			shootHitRate = Double.parseDouble(dec.format(shootHitRate));
			if (allthreeAttemptNum != 0) {
				threeHitRate = allthreeHitNum / allthreeAttemptNum;
//				threeHitRate = Double.parseDouble(dec.format(threeHitRate));
			} else {
				threeHitRate = 0;
			}
			if (allfreeThrowAttemptNum != 0) {
				freeThrowHitRate = allfreeThrowHitNum / allfreeThrowAttemptNum;
//				freeThrowHitRate = Double.parseDouble(dec
//						.format(freeThrowHitRate));
			} else {
				freeThrowHitRate = 0;
			}
//			efficiency = (allscore + allreboundNum + allassistNum + allstealNum + allblockNum)
//					- (allshootAttemptNum - allshootHitNum)
//					- (allfreeThrowAttemptNum - allfreeThrowHitNum)
//					- allturnOverNum;
//			efficiency = Double.parseDouble(dec.format(efficiency));
			efficiency=allEfficiency/playedGames;
			ArrayList<Integer> matchIDs = playerSeason.getMatchesID();
			Map<Integer, Boolean> isVisitingTeam = playerSeason
					.getIsVisitingTeam();
			if (matchIDs.size() <= 5) {
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
				double beforeRecentAssistNum = allassistNum - recentAssistNum;
				if (beforeRecentFiveScore != 0) {
					recentFiveMatchesScoreUpRate = ((recentFiveScore / 5) - beforeRecentFiveScore
							/ (playedGames - 5))
							/ (beforeRecentFiveScore / (playedGames - 5));
//					recentFiveMatchesScoreUpRate = Double.parseDouble(dec
//							.format(recentFiveMatchesScoreUpRate));
				} else {
					recentFiveMatchesScoreUpRate = 0;
				}
				if (beforeRecentReboundNum != 0) {
					recentFiveMatchesReboundUpRate = ((recentReboundNum / 5) - beforeRecentReboundNum
							/ (playedGames - 5))
							/ (beforeRecentReboundNum / (playedGames - 5));
//					recentFiveMatchesReboundUpRate = Double.parseDouble(dec
//							.format(recentFiveMatchesReboundUpRate));
				} else {
					recentFiveMatchesReboundUpRate = 0;
				}
				if (beforeRecentAssistNum != 0) {
					recentFiveMatchesAssistUpRate = ((recentAssistNum / 5) - beforeRecentAssistNum
							/ (playedGames - 5))
							/ (beforeRecentAssistNum / (playedGames - 5));
//					recentFiveMatchesAssistUpRate = Double.parseDouble(dec
//							.format(recentFiveMatchesAssistUpRate));
				} else {
					recentFiveMatchesAssistUpRate = 0;
				}
			}

//			GmScEfficiencyValue = allscore + 0.4 * allshootHitNum - 0.7
//					* allshootAttemptNum - 0.4
//					* (allfreeThrowAttemptNum - allfreeThrowHitNum) + 0.7
//					* alloffenReboundNum + 0.3 * alldefenReboundNum
//					+ allstealNum + 0.7 * allassistNum + 0.7 * allblockNum
//					- 0.4 * allfoulNum - allturnOverNum;
//			GmScEfficiencyValue = Double.parseDouble(dec
//					.format(GmScEfficiencyValue));
			GmScEfficiencyValue=allGmScEfficiency/playedGames;
			trueHitRate = (double) allscore
					/ (2 * (allshootAttemptNum + 0.44 * allfreeThrowAttemptNum));
//			trueHitRate = Double.parseDouble(dec.format(trueHitRate));
			if (allshootAttemptNum != 0) {
				shootEfficiency = (double) (allshootHitNum + 0.5 * allthreeHitNum)
						/ allshootAttemptNum;
//				shootEfficiency = Double.parseDouble(dec
//						.format(shootEfficiency));
			} else {
				shootEfficiency = 0;
			}
			for (Integer id : matchIDs) {
				MatchVO match = matches.get(id);
				allMatchTime += match.getMatchTime();
				if (isVisitingTeam.get(id) == true) {
					teamOffenReboundNum += match.getVisitingOffenReboundNum();
					dsOffenReboundNum += match.getHomeOffenReboundNum();
					teamDefenReboundNum += match.getVisitingDefenReboundNum();
					dsDefenReboundNum += match.getHomeDefenReboundNum();
					teamShootHitNum += match.getVisitingShootHitNum();
					dsTwoAttemptNum += match.getHomeShootAttemptNum()
							- match.getHomeThreeAttemptNum();
					teamShootAttemptNum += match.getVisitingShootAttemptNum();
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

//			reboundRate = Double.parseDouble(dec.format(reboundRate));

			offenReboundRate = alloffenReboundNum * allMatchTime
					/ allpresentTime
					/ (teamOffenReboundNum + dsOffenReboundNum);

//			offenReboundRate = Double.parseDouble(dec.format(offenReboundRate));

			defenReboundRate = alldefenReboundNum * allMatchTime
					/ allpresentTime
					/ (teamDefenReboundNum + dsDefenReboundNum);

//			defenReboundRate = Double.parseDouble(dec.format(defenReboundRate));

			assistRate = allassistNum
					/ (allpresentTime / allMatchTime * teamShootHitNum - allshootHitNum);
//			assistRate = Double.parseDouble(dec.format(assistRate));
			stealRate = (double) allstealNum * allMatchTime / allpresentTime
					/ dsOffenRoundNum;
//			stealRate = Double.parseDouble(dec.format(stealRate));
			blockRate = allblockNum * allMatchTime / allpresentTime
					/ dsTwoAttemptNum;
//			blockRate = Double.parseDouble(dec.format(blockRate));
			turnOverRate = allturnOverNum
					/ (allshootAttemptNum - allthreeAttemptNum + 0.44
							* allfreeThrowAttemptNum + allturnOverNum);
//			turnOverRate = Double.parseDouble(dec.format(turnOverRate));
			usageRate = (allshootAttemptNum + 0.44 * allfreeThrowAttemptNum + allturnOverNum)
					* allMatchTime
					/ allpresentTime
					/ (teamShootAttemptNum + 0.44 * teamFreeThrowAttemptNum + teamTurnOverNum);
//			usageRate = Double.parseDouble(dec.format(usageRate));
			score_rebound_assist = allscore + allreboundNum + allassistNum;
//			score_rebound_assist = Double.parseDouble(dec
//					.format(score_rebound_assist));
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
		result.add(playerSeason);
	  }
		return result;
	}

	private void getPlayerMatchInfo(Map<String, PlayerVO> theSeasonPlayers,MatchVO match) {

		int matchCount = 0;
		String season = match.getSeason();
		String date = match.getDate();
		String homeTeam = match.getHomeTeam();
		String visitingTeam = match.getVisitingTeam();
		String type=match.getType();

		MatchVO thisMatch = new MatchVO(season, date, type,visitingTeam, homeTeam);
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
			PlayerVO thisPlayer=theSeasonPlayers.get(playerName);
			if(thisPlayer==null){
				PlayerVO pp;
				if(season.equals("14-15"))
			       pp=playersActive.get(playerName);
				else 
				   pp=playersHistoric.get(playerName);			
			   if(pp!=null){
			      thisPlayer =new PlayerVO(pp.getName(), pp.getNumber(), pp.getPosition(), pp.getHeight(), pp.getWeight(), pp.getBirth(), pp.getAge(), pp.getExp(), pp.getSchool());
                  theSeasonPlayers.put(playerName, thisPlayer);
			   }
			}
			if (thisPlayer == null){
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
					thisMatch.addVisitingFreeThrowAttemptNum(freeThrowAttemptNum);
					thisMatch.addVisitingOffenReboundNum(offenReboundNum);
					thisMatch.addVisitingDefenReboundNum(defenReboundNum);
					thisMatch.addVisitingAssistNum(assistNum);
					thisMatch.addVisitingStealNum(stealNum);
					thisMatch.addVisitingBlockNum(blockNum);
					thisMatch.addVisitingTurnOverNum(turnOverNum);
					thisMatch.addVisitingFoulNum(foulNum);
				}
				continue;
			}
			LittleRecordVO littleRecordVO = new LittleRecordVO(season, date,
					personScore, reboundNum, assistNum);

			thisPlayer.addPlayedGames();
			if (!position.equals(""))
				thisPlayer.addGameStartingNum();

			if (thisPlayer.getMostRecentMatch() == null) {
				thisPlayer.setOwingTeam(team);				
				TeamVO teamVO = teams.get(team);
				if (teamVO == null) {
					teamVO = teams.get("NOP");
				}
				thisPlayer.setLeague(teamVO.getConference());
				thisPlayer.setMostRecentMatch(season + "_" + date);
			} else {
				if (compare(thisPlayer.getMostRecentMatch(),season + "_" + date) < 0) {
					thisPlayer.setOwingTeam(team);
					TeamVO teamVO = teams.get(team);
					if (teamVO == null) {
						teamVO = teams.get("NOP");
					}
					thisPlayer.setLeague(teamVO.getConference());
					thisPlayer.setMostRecentMatch(season + "_" + date);
				}
			}

            int theEffiency= (personScore + reboundNum + assistNum + stealNum + blockNum)
					- (shootAttemptNum - shootHitNum)
					- (freeThrowAttemptNum - freeThrowHitNum)
					- turnOverNum;
            double theGmScEffiency=personScore + 0.4 * shootHitNum - 0.7* shootAttemptNum - 0.4
					* (freeThrowAttemptNum - freeThrowHitNum) + 0.7
					* offenReboundNum + 0.3 * defenReboundNum
					+ stealNum + 0.7 * assistNum + 0.7 * blockNum
					- 0.4 * foulNum - turnOverNum;
            thisPlayer.addEfficiency(theEffiency);
            thisPlayer.addGmScEfficiencyValue(theGmScEffiency);
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
				thisMatch.addVisitingFreeThrowAttemptNum(freeThrowAttemptNum);
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
	
	private int compare(String s1,String s2){
		String[] buffer1=s1.split("_");
		String[] buffer2=s2.split("_");
		String season1=buffer1[0];
		String date1=buffer1[1];
		String season2=buffer2[0];
		String date2=buffer2[1];
		
		if(season1.compareTo(season2)<0)
			return -1;
		else if(season1.compareTo(season2)>0)
			return 1;
		else{
			String month1=date1.split("-")[0];
			String month2=date2.split("-")[0];
			int type1=0;
			int type2=0; //type=0代表在6月前，=1代表在6月后
			if(month1.compareTo("06")>0){
				type1=1;
			}
			if(month2.compareTo("06")>0){
				type2=1;
			}
			
			if(type1==0&&type2==1){
				return 1;
			}
			
			if(type1==1&&type2==0){
				return-1;
			}
			
			//下面是2个都是6月前或6月后的情况
		     	if(date1.compareTo(date2)<0)
				   return -1;
			    else if(date1.compareTo(date2)>0)
				   return 1;
			    else 
				   return 0;
		     	
		}
	}

	public ArrayList<PlayerVO> getPlayerAverageInfo(String type) {

		int matchType=0;
		//季前matchType:1
		//常规matchType:2
		//季后matchType:3
		
		if(type.equals("Team")){
			matchType=2;
			if(isCalculateAverageRegular==true){
				Collections.sort(playersAverageRegular, new SequenceOfPlayer());
				return playersAverageRegular;
			}
		}
		else if(type.equals("Playoff")){
			matchType=3;
			if(isCalculateAveragePlayoff==true){
				Collections.sort(playersAveragePlayoff, new SequenceOfPlayer());
				return playersAveragePlayoff;
			}
		}
		else{
			matchType=1;
			if(isCalculateAveragePreceding==true){
				Collections.sort(playersAveragePreceding, new SequenceOfPlayer());
				return playersAveragePreceding;
			}
		}
		
		
		Map<String, PlayerVO> theSeasonPlayers=new HashMap<String, PlayerVO>(32);
		ArrayList<PlayerVO> result=new ArrayList<PlayerVO>();
		ArrayList<PlayerVO> buffer;
		matches.clear();
			
		for (MatchVO match : getTypeMatches(type)) {
			getPlayerMatchInfo(theSeasonPlayers,match);
		}

	    buffer=calculatePlayer(theSeasonPlayers);
	    Collections.sort(buffer, new SequenceOfPlayer());
       
		for(PlayerVO playerSeason:buffer){
			int playedGames = playerSeason.getPlayedGames();
			PlayerVO newPlayer;
			if (playedGames != 0) {
//				DecimalFormat dec = new DecimalFormat("0.00");
				newPlayer = new PlayerVO(playerSeason.getName(),
						playerSeason.getNumber(), playerSeason.getPosition(),
						playerSeason.getHeight(), playerSeason.getWeight(),
						playerSeason.getBirth(), playerSeason.getAge(),
						playerSeason.getExp(), playerSeason.getSchool(),
						playerSeason.getOwingTeam(), playerSeason.getLeague(),
						playedGames, playerSeason.getGameStartingNum(),
						playerSeason
								.getReboundNum() / playedGames,
						playerSeason
								.getAssistNum() / playedGames,
						playerSeason
								.getPresentTime() / playedGames,
						playerSeason
								.getShootHitNum() / playedGames,
						playerSeason
								.getShootAttemptNum() / playedGames,
						playerSeason.getShootHitRate(), playerSeason.getThreeHitNum()
										/ playedGames, playerSeason.getThreeAttemptNum()
										/ playedGames,
						playerSeason.getThreeHitRate(), playerSeason.getFreeThrowHitNum()
										/ playedGames, playerSeason.getFreeThrowAttemptNum()
										/ playedGames,
						playerSeason.getFreeThrowHitRate(),
						playerSeason
								.getOffenReboundNum() / playedGames,
						playerSeason
								.getDefenReboundNum() / playedGames,
						playerSeason
								.getStealNum() / playedGames,
						playerSeason
								.getBlockNum() / playedGames,
						playerSeason
								.getTurnOverNum() / playedGames,
						playerSeason.getFoulNum()
								/ playedGames,
						playerSeason.getScore()
								/ playedGames, playerSeason.getEfficiency(),
						playerSeason.getRecentFiveMatchesScoreUpRate(),
						playerSeason.getRecentFiveMatchesReboundUpRate(),
						playerSeason.getRecentFiveMatchesAssistUpRate(),
						playerSeason.getGmScEfficiencyValue(),
						playerSeason.getTrueHitRate(),
						playerSeason.getShootHitEfficiency(),
						playerSeason.getReboundRate(),
						playerSeason.getOffenReboundRate(),
						playerSeason.getDefenReboundRate(),
						playerSeason.getAssistRate(),
						playerSeason.getStealRate(),
						playerSeason.getBlockRate(),
						playerSeason.getTurnOverRate(),
						playerSeason.getUsageRate(),playerSeason.getScore_rebound_assist()
										/ playedGames, playerSeason.getDoubleDoubleNum()
										/ playedGames);
			} else {
				newPlayer = new PlayerVO(playerSeason.getName(),
						playerSeason.getNumber(), playerSeason.getPosition(),
						playerSeason.getHeight(), playerSeason.getWeight(),
						playerSeason.getBirth(), playerSeason.getAge(),
						playerSeason.getExp(), playerSeason.getSchool(),
						playerSeason.getOwingTeam(), playerSeason.getLeague(),
						playedGames, playerSeason.getGameStartingNum(), 0, 0,
						0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
						0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
			}

			result.add(newPlayer);
		}
		
		if(matchType==1){
			playersAveragePreceding=result;
			isCalculateAveragePreceding=true;
		}
		else if(matchType==2){
			playersAverageRegular=result;
			isCalculateAverageRegular=true;
		}
		else{
			playersAveragePlayoff=result;
			isCalculateAveragePlayoff=true;
		}
		return result;
	}
	
	public ArrayList<PlayerVO> getPlayerAverageInfo(String type,String name){
		ArrayList<PlayerVO> vos=getPlayerAverageInfo(type);
		ArrayList<PlayerVO> result=new ArrayList<PlayerVO>();
		PlayerVO vo=new PlayerVO();
		for(int i=0;i<vos.size();i++){
			vo=vos.get(i);
			if(vo.getName().equals(name))
				result.add(vo);
		}
		return result;
	}

	public ArrayList<PlayerVO> getOrderedPlayersBySeason(String season,String type,
			String condition, String order, int num) {
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
		ArrayList<PlayerVO> allPlayers = getPlayerSeasonInfo(season,Match.changeTypeCHToEN(type));
//		condition=changePlayerCHToEN(condition);
		Collections.sort(allPlayers, new SequenceOfPlayer(condition, order));
		int count = 0;
		for (PlayerVO vo : allPlayers) {
			if(vo.getPlayedGames()==0)
				continue;
			
			result.add(vo);
			count++;
			if (count >= num)
				break;
		}
		return result;
	}

	public ArrayList<PlayerVO> getOrderedPlayersByAverage(String type,String condition,
			String order, int num) {
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
		ArrayList<PlayerVO> allPlayers = getPlayerAverageInfo(Match.changeTypeCHToEN(type));
//		condition=changePlayerCHToEN(condition);
		Collections.sort(allPlayers, new SequenceOfPlayer(condition, order));
		int count = 0;
		for (PlayerVO vo : allPlayers) {
			if(vo.getPlayedGames()==0)
				continue;
			
			result.add(vo);
			count++;
			if (count >= num)
				break;
		}
		return result;
	}

	private ArrayList<PlayerVO> selectPlayers(ArrayList<PlayerVO> thePlayers,
			String position, String union, String column,
			String order, int num) {
//		position = changePlayerCHToEN(position);
//		union = changePlayerCHToEN(union);
//		column = changePlayerCHToEN(column);
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
		String[] positions=position.split("-");
		int size=position.length();
		String p1="";
		String p2="";
		
		if (!position.equals("all")&&!position.equals("All")) {
			if(size==1)
				p1=positions[0];
			else{
				p1=positions[0];
				p2=positions[1];
			}
			for (int i = 0; i < thePlayers.size(); i++) {
				if(size==1){
				  if (!thePlayers.get(i).getPosition().contains(p1)){
					  thePlayers.remove(i);
					  i--;
				  }
				}
				else{
					if (!(thePlayers.get(i).getPosition().contains(p1)&&thePlayers.get(i).getPosition().contains(p2))){
						thePlayers.remove(i);
						i--;
					}
				}
			}
		}
		if (!union.equals("all")&&!union.equals("All")) {
			for (int i = 0; i < thePlayers.size(); i++) {
				if (!thePlayers.get(i).getLeague().equals(union)){
					thePlayers.remove(i);
					i--;
				}
			}
		}

		Collections.sort(thePlayers, new SequenceOfPlayer(column, order));

		int count = 0;
		for (PlayerVO vo : thePlayers) {
			if(vo.getPlayedGames()==0)
				continue;
			
			result.add(vo);
			count++;
			if (count >= num)
				break;
		}
		return result;
	}

	public ArrayList<PlayerVO> selectPlayersBySeason(String season,String type,
			String position, String union, String column,
			String order, int num) {
		ArrayList<PlayerVO> result=new ArrayList<PlayerVO>();
		ArrayList<PlayerVO> thePlayers = getPlayerSeasonInfo(season,Match.changeTypeCHToEN(type));
		for(PlayerVO vo:thePlayers){
			result.add(vo);
		}
		return selectPlayers(result, position, union,  column,
				order, num);
	}

	public ArrayList<PlayerVO> selectPlayersByAverage(String type,String position,
			String union, String column, String order, int num) {
		ArrayList<PlayerVO> result=new ArrayList<PlayerVO>();
		ArrayList<PlayerVO> thePlayers = getPlayerAverageInfo(Match.changeTypeCHToEN(type));
		for(PlayerVO vo:thePlayers){
			result.add(vo);
		}
		return selectPlayers(result, position, union,  column,
				order, num);
	}

	public static ImageIcon getPlayerPortraitImage(String name) {
		ImageIcon imageIcon;
		File file=new File("src/data/players/portrait/" + name+ ".png");    
		if(!file.exists()){
			imageIcon=new ImageIcon("src/data/players/portrait/None.png");
			imageIcon.setImage(imageIcon.getImage().getScaledInstance(60, 50,Image.SCALE_SMOOTH ));
		}
		else{
			imageIcon = new ImageIcon("src/data/players/portrait/" + name
				+ ".png");
			imageIcon.setImage(imageIcon.getImage().getScaledInstance(60, 50,Image.SCALE_SMOOTH ));
		}
		return imageIcon;
	}

	public static ImageIcon getPlayerActionImage(String name) {
		ImageIcon imageIcon = new ImageIcon("image/player/action/" + name
				+ ".png");
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
		MatchBLService theMatch=new Match();
		result=theMatch.getMatchData(season, "all", date, "all", "all");
		if(result.size()!=0){
			return result;
		}
		
		ArrayList<MatchVO> mostRecentDateMatches=new ArrayList<MatchVO>();
		String seasonRecent="";
		String dateRecent="";
		boolean isFirst=true;
			
		
		for (MatchVO match : getSeasonMatches(season, "all")) {
			String theMatchSeason=match.getSeason();
			String theMatchDate=match.getDate();
			
			if(isFirst==true){
				seasonRecent=theMatchSeason;
				dateRecent=theMatchDate;
				isFirst=false;
				continue;
			}
			
			String sd1=theMatchSeason+"_"+theMatchDate;
			String sd2=seasonRecent+"_"+dateRecent;
			if(sd1.compareTo(sd2)==0){
				mostRecentDateMatches.add(match);
				continue;
			}
			if(isMostRecent(theMatchSeason, theMatchDate, seasonRecent, dateRecent)){
				mostRecentDateMatches.clear();
				mostRecentDateMatches.add(match);
				seasonRecent=theMatchSeason;
				dateRecent=theMatchDate;
			}
		}
		
		   return mostRecentDateMatches;
		
	}
	/**
	 * 
	 * @param season1
	 * @param date1
	 * @param season2
	 * @param date2
	 * @return 是否日期一比日期二更靠近现在,由于老师说"默认读入的最后一场比赛就是最近的",所以，日期一与日期二不同为日期一更近
	 */
	private boolean isMostRecent(String season1,String date1,String season2,String date2){
		if(season1.compareTo(season2)<0)
  			return false;
  		else if(season1.compareTo(season2)>0)
  			return true;
  		else{
  			String month1=date1.split("-")[0];
  			String month2=date2.split("-")[0];
  			int type1=0;
  			int type2=0; //type=0代表在6月前，=1代表在6月后
  			if(month1.compareTo("06")>0){
  				type1=1;
  			}
  			if(month2.compareTo("06")>0){
  				type2=1;
  			}
  			
  			if(type1==0&&type2==1){
  				return true;
  			}
  			
  			if(type1==1&&type2==0){
  				return false;
  			}
  			
  			//下面是2个都是6月前或6月后的情况
  		     	if(date1.compareTo(date2)<0)
  				   return false;
  			    else if(date1.compareTo(date2)>0)
  				   return true;
  			    else 
  				   return false;
  		     	
  		}
	}

	private ArrayList<PlayerVO> calCulateDayData() {

		ArrayList<MatchVO> theMatches = getTodayMatch();
		ArrayList<PlayerVO>  playersToday=new ArrayList<PlayerVO>();
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
				PlayerVO thisPlayer;
				if(match.getSeason().equals("14-15"))
				   thisPlayer = playersActive.get(playerName);
				else
				   thisPlayer =playersHistoric.get(playerName);
				PlayerVO player=new PlayerVO();
				if (thisPlayer != null){
					player = new PlayerVO(thisPlayer.getName(),
							thisPlayer.getNumber(), thisPlayer.getPosition(),
							thisPlayer.getHeight(), thisPlayer.getWeight(),
							thisPlayer.getBirth(), thisPlayer.getAge(),
							thisPlayer.getExp(), thisPlayer.getSchool());
				}
					
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
        return playersToday;
	}

	public ArrayList<PlayerVO> getDayHotPlayer(String column, int num) {
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
          
		ArrayList<PlayerVO> playersToday=calCulateDayData();
		Collections.sort(playersToday,new SequenceOfPlayer());
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

	public ArrayList<PlayerVO> getSeasonHotPlayer(String season,String type,String column,
			int num) {

		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
		ArrayList<PlayerVO> thePlayers = getPlayerAverageInfo(type);

		Collections.sort(thePlayers, new SequenceOfPlayer(column, "desc"));
		int count = 0;
		for (PlayerVO vo : thePlayers) {
			if(vo.getPlayedGames()==0)
				continue;
			
			result.add(vo);
			count++;
			if (count >= num)
				break;
		}

		return result;
	}

	public ArrayList<PlayerVO> getBestImprovedPlayer(String type,String column, int num) {
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
		ArrayList<PlayerVO> thePlayers = getPlayerAverageInfo(Match.changeTypeCHToEN(type));

		Collections.sort(thePlayers, new SequenceOfPlayer(column, "desc"));
		int count = 0;
		for (PlayerVO vo : thePlayers) {
			if(vo.getPlayedGames()==0)
				continue;
			
			result.add(vo);
			count++;
			if (count >= num)
				break;
		}

		return result;
	}

	public ArrayList<PlayerVO> getPlayersByInitialName(char character) {
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
		ArrayList<PlayerVO> thePlayers = getPlayerHistoricBaseInfo();
		for (PlayerVO vo : thePlayers) {
			String[] name=vo.getName().split(" ");
			int size=name.length;
			String lastName=name[size-1];
			
			if (lastName.startsWith(character + ""))
					result.add(vo);			
			
		}
		return result;
	}

	public ArrayList<MatchVO> getRecentMatches(String playerName, int num) {
		ArrayList<MatchVO> result = new ArrayList<MatchVO>();
		int count = 0;
		ArrayList<MatchVO> allSeasonMatches=getAllMatches();
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
		ArrayList<MatchVO> allSeasonMatches=getAllMatches();
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
		ArrayList<PlayerVO> thePlayers = getPlayerAverageInfo("Team");
//		teamAbLocation=changePlayerCHToEN(teamAbLocation);
		for (PlayerVO vo : thePlayers) {
			if (vo.getOwingTeam().equals(teamAbLocation))
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

	public static double convertSecondToMinuete(double second) {
		double minute= second / 60;
	//	DecimalFormat dec=new DecimalFormat("0.00");
		return minute;
	}
	
	public static String changeSecondToTime(double second) {
		double time=convertSecondToMinuete(second);
		String result;
		String temp = String.valueOf(time);
		String[] tempp = temp.split("\\.");
		if (tempp.length == 2) {
			result = tempp[0] + ":";
			double xiaoshu = Double.parseDouble("0." + tempp[1]);

			DecimalFormat dec = new DecimalFormat("0");
			String xiaoshu60 = dec.format(xiaoshu * 60);
			result = result + xiaoshu60;
		} else {
			result = tempp[0];
		}
		return result;
	}
	
	/**
	 * 将球员数据中文名转为相应的英文缩写
	 * 
	 * @param 球员中文名
	 * @return 英文缩写
	 */
	public static String changePlayerCHToEN(String CH) {
		int index = -1;
		for (int i = 0; i < player_CH.length; i++) {
			if (player_CH[i].equals(CH)) {
				index = i;
			}
		}
		if (index != -1) {
			return player_EN[index];
		} else {
			return CH;
		}
	}
	
	/**
	 * 将球员数据英文缩写转为相应的中文名
	 * 
	 * @param EN
	 *            球员数据英文名
	 * @return 中文名
	 */
	public static String changePlayerENToCH(String EN) {
		int index = -1;
		for (int i = 0; i < player_CH.length; i++) {
			if (player_EN[i].equals(EN)) {
				index = i;
			}
		}
		if (index != -1) {
			return player_CH[index];
		} else {
			return EN;
		}
	}

	public ArrayList<PlayerVO> selectPlayersUptheTimeAverage(String type,String position,
			String union, String column, String order,int minute,int num) {
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
		ArrayList<PlayerVO> thePlayers = getPlayerAverageInfo(Match.changeTypeCHToEN(type));
		if(minute>0){
           int second=minute*60;
		   for (PlayerVO vo : thePlayers) {
			 if (vo.getPresentTime()>=second)
				  result.add(vo);
		     }
		}
		else{
			 for (PlayerVO vo : thePlayers) 
					  result.add(vo);			     
		}
	
		result=selectPlayers(result, position, union, column, order, num);
		return result;
	}
	
	public ArrayList<PlayerVO> selectPlayersUptheTimeSeason(String season,String type,String position,
			String union, String column, String order,int minute,int num) {
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
		ArrayList<PlayerVO> thePlayers = getPlayerSeasonInfo(season,Match.changeTypeCHToEN(type));
		if(minute>0){
           int second=minute*60;
		   for (PlayerVO vo : thePlayers) {
			  if (vo.getPresentTime()>=second)
				  result.add(vo);
		    }
		}
		else{
			for (PlayerVO vo : thePlayers) {
					  result.add(vo);
			    }   
		}
		
	    result=selectPlayers(result, position, union, column, order, num);
		return result;
	}

	/**
//	 * 向match中增加比赛信息
//	 * 
//	 * @param newName
//	 *            动态增加的比赛的文件名
//	 */
//	private void add(ArrayList<String> newName) {
//		for (String str : newName) {
//			MatchVO vo = readMatchInfoFromFile(str);
//			getPlayerMatchInfo(vo);
//			ArrayList<RecordVO> records = vo.getRecords();
//			for (RecordVO recordVO : records) {
//				PlayerVO thisPlayer = players.get(recordVO.getPlayerName());
//				if (thisPlayer != null) {
//					calculatePlayerSeason(thisPlayer);
//					needUpdatePlayerSeason = true;
//					needUpdatePlayerAverage = true;
//					needUpdatePlayersToday = true;
//				}
//			}
//		}
//	}

	// 放在Filelist或Match里均可
//	class updateMatch extends Thread {
//		boolean stop;
//
//		public updateMatch() {
//			stop = false;
//		}
//
//		public void run() {
//			while (!stop) {
//				ArrayList<String> addPaths = matchFiles.checkMatchesChange();
//				if (addPaths != null) {
//					add(addPaths);
//					// System.out.println("我在很认真的检查呀，港荣蒸蛋糕真的好吃，字打错了灭");
//					System.out.println("有新文件啦！！");
//					ArrayList<PlayerVO> oo = getPlayerAverageInfo();
//					System.out.println(oo.get(405).getName() + " "
//							+ oo.get(405).getPlayedGames() + " "
//							+ oo.get(405).getEfficiency());
//				}
//				try {
//					this.sleep(2000);// 话说 能不能不睡
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//
//		public void startThread() {
//			this.start();
//		}
//
//		public void stopThead() {
//			this.stop = true;
//		}
//	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		Player player = new Player();
		// result = team.getTeamBaseInfo();
		// result = team.getTeamAverageInfo("all");
		// result = team.getTeamAverageInfo("Playoff", "NOP");
		// result=team.getTeamSeasonInfo("12-13", "Team");
		// result=team.getTeamSeasonInfo("12-13", "Playoff", "NOP");
		// result=team.getOrderedTeamsByAverage( "all", "score", "desc", 5);
		
//		ArrayList<PlayerVO> players=player.getPlayerBaseInfo();
//		ArrayList<PlayerVO> players=player.getPlayerSeasonInfo("12-13", "Playoff");
//		ArrayList<PlayerVO> players=player.getPlayerAverageInfo("Team");
//		ArrayList<PlayerVO> players=player.selectPlayersBySeason("12-13", "Team", "all", "all", "score", "desc", 5);
//		ArrayList<PlayerVO> players=player.selectPlayersByAverage("Team", "all", "all", "score", "desc", 5);
//		ArrayList<PlayerVO> players=player.getOrderedPlayersBySeason("12-13", "Team", "score", "asc", 5);
		ArrayList<PlayerVO> players=player.getOrderedPlayersByAverage( "Team", "score", "desc", 5);
		long end1= System.currentTimeMillis();
		System.out.println("运行时间：" + (end1 - start) + "毫秒");// 应该是end - start
		ArrayList<PlayerVO> players2=player.getOrderedPlayersByAverage( "Preseason", "score", "desc", 5);
		long end2 = System.currentTimeMillis();
		System.out.println("运行时间：" + (end2 - end1) + "毫秒");// 应该是end - start
		ArrayList<PlayerVO> players3=player.getOrderedPlayersByAverage( "Playoff", "score", "desc", 5);
		long end3 = System.currentTimeMillis();
		System.out.println("运行时间：" + (end3 - end2) + "毫秒");// 应该是end - start
		ArrayList<PlayerVO> player4=player.getOrderedPlayersByAverage( "Team", "score", "desc", 5);
		long end4 = System.currentTimeMillis();
		System.out.println("运行时间：" + (end4 - end3) + "毫秒");// 应该是end - start
		for(PlayerVO vv:players){
			System.out.println(vv.getName());
			System.out.println(vv.getScore());
		}
		
	//	PlayerVO vo = player.getPlayerAverageInfo("Kevin Durant");
	//	PlayerVO vo = player.getPlayerAverageInfo("LeBron James");
//		PlayerVO vo = player.getPlayerSeasonInfo("13-14", "Kevin Durant");
//		 System.out.println("name: "+vo.getName());
//		 System.out.println("owingTeam: "+vo.getOwingTeam());
//		 System.out.println("league: "+vo.getLeague());
//		 System.out.println("playedGames: "+vo.getPlayedGames());
//		 System.out.println("gameStartingNum: "+vo.getGameStartingNum());
//		 System.out.println("reboundNum: "+vo.getReboundNum());
//		 System.out.println("assistNum: "+vo.getAssistNum());
//		 System.out.println("presentTime: "+vo.getPresentTime());
//		 System.out.println("shootHitRate: "+vo.getShootHitRate());
//		 System.out.println("threeHitRate: "+vo.getThreeHitRate());
//		 System.out.println("freeThrowHitRate: "+vo.getFreeThrowHitRate());
//		 System.out.println("offenReboundNum: "+vo.getOffenReboundNum());
//		 System.out.println("defenReboundNum: "+vo.getDefenReboundNum());
//		 System.out.println("stealnum:   "+vo.getStealNum());
//		 System.out.println("blockNum: "+vo.getBlockNum());
//		 System.out.println("foulNum: "+vo.getFoulNum());
//		 System.out.println("turnOverNum: "+vo.getTurnOverNum());
//		 System.out.println("score: "+vo.getScore());
//		 System.out.println("efficiency:"+vo.getEfficiency());
//		 System.out.println("recentFiveMatchesScoreUpRate: "+vo.getRecentFiveMatchesScoreUpRate());
//		 System.out.println("recentFiveMatchesReboundUpRate: "+vo.getRecentFiveMatchesReboundUpRate());
//		 System.out.println("recentFiveMatchesAssistRate: "+vo.getRecentFiveMatchesAssistUpRate());
//		 System.out.println("gmscEfficiency: "+vo.getGmScEfficiencyValue());
//		 System.out.println("truehitrate:  "+vo.getTrueHitRate());
//		 System.out.println("shootHitEfficiency: "+vo.getShootHitEfficiency());
//		 System.out.println("reboundRate: "+vo.getReboundRate());
//		 System.out.println("offenReboundRate: "+vo.getOffenReboundRate());
//		 System.out.println("defenReboundRate: "+vo.getDefenReboundRate());
//		 System.out.println("assistRate: "+vo.getAssistRate());
//		 System.out.println("stealRate: "+vo.getStealRate());
//		 System.out.println("blockRate: "+vo.getBlockRate());
//		 System.out.println("turnOverRate: "+vo.getTurnOverRate());
//		 System.out.println("usageRate: "+vo.getUsageRate());
//		 System.out.println("score_rebound_assist: "+vo.getScore_rebound_assist());
//		 System.out.println("doubleDoubleNum: "+vo.getDoubleDoubleNum());

		long end = System.currentTimeMillis();
		System.out.println("运行时间：" + (end - start) + "毫秒");// 应该是end - start
	}

}