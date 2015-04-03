package bl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import vo.MatchVO;
import vo.PlayerVO;
import vo.RecordVO;
import vo.TeamVO;
import blservice.PlayerBLService;

public class Player implements PlayerBLService{

	ArrayList<PlayerVO> players;
	ArrayList<TeamVO> teams;
	ArrayList<MatchVO> matches;
	
	/**
	 * 
	 * @param flag    
	 * flag=0   看球员的基本信息
	 * flag=1   看球员的赛季信息
	 * flag=2  看球员的场均信息
	 */
	public Player(int flag){
		players=new ArrayList<PlayerVO>();
		if(flag==0){
			baseInfoInit();
		}
		else if(flag==2){
			matches=new ArrayList<MatchVO>();
		}
		
		else{
			matchInfoInit();
		}
	}
	
	private void baseInfoInit(){
	
		try{					
			FileList fl = new FileList("src/data/players/info");
			ArrayList<String> names = fl.getList();
	      for(String name:names){
	    	  players.add(readBaseInfoFromFile(name));
	      }
		}
		catch (IOException e){
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
				// System.out.println(temp);
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
		player = new PlayerVO(name, number, position, height, weight,birth, age, exp, school);
		return player;
	}
	
	private void matchInfoInit(){

		try{					
			FileList fl = new FileList("src/data/matches");
			ArrayList<String> names = fl.getList();
	      for(String name:names){
	    	  readMatchInfoFromFile(name);
	      }
		}
		catch (IOException e){
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void readMatchInfoFromFile(String fileName){
		String season;// 赛季
		String date = null;// 比赛日期
		String teams = null;// 对阵队伍
		String score = null;// 比分

		String tp[] = fileName.split("matches");
		season = tp[1].substring(1, 6);
		ArrayList<RecordVO> records=new ArrayList<RecordVO>();
		MatchVO match;

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

			String team = null;// 球队
			String playerName = null;// 球员名
			String position = null;// 位置
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

			temp = br.readLine();
			while (temp != null) {
				if (fileName.contains(temp)) {
					team = temp;
				} else {
					String[] line = temp.split(";");
					playerName = line[0];
					position = line[1];
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
				
					RecordVO recordVO = new RecordVO(team, playerName,
							position, presentTime, shootHitNum,
							shootAttemptNum, threeHitNum, threeAttemptNum,
							freeThrowHitNum, freeThrowAttemptNum,
							offenReboundNum, defenReboundNum, reboundNum,
							assistNum, stealNum, blockNum, turnOverNum,
							foulNum, personScore);
					
					records.add(recordVO);
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

		String[] s=score.split("-");
		int visitingScore=Integer.parseInt(s[0]);
		int homeScore=Integer.parseInt(s[1]);
		
		match = new MatchVO(season, date, visitingTeam, homeTeam,
				visitingScore,homeScore, null, records);
		matches.add(match);
	}
	
	private boolean isMoreRecent(String season1,String date1,String season2,String date2){
		if(season1.compareTo(season2)<0){
			return true;
		}
		else if(season1.compareTo(season2)>0){
			return false;
		}
		else{
			if(date1.compareTo(date2)<0){
				return true;
			}
			else if(date1.compareTo(date2)>0){
				return false;
			}
			else{
				return true;
			}
		}
	}
	
 	public ArrayList<PlayerVO> getPlayerBaseInfo() {
		return players;
	}

	public ArrayList<PlayerVO> getPlayerSeasonInfo(String season) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<PlayerVO> getPlayerAverageInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<PlayerVO> getPlayerBaseInfo(String name) {
		ArrayList<PlayerVO> result=new ArrayList<PlayerVO>();
		for(PlayerVO vo:players){
			if(vo.getName().contains(name)){
				result.add(vo);
			}
		}
		return result;
	}

	public PlayerVO getPlayerSeasonInfo(String season, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public PlayerVO getPlayerAverageInfo(String name) {
		PlayerVO player=new PlayerVO();
		String owingTeam="";
		int playedGames=0;
		int gameStartingNum=0;
		int reboundNum=0;
		int assistNum=0;
		String presentTime="";
		int shootHitNum=0;
		int shootAttemptNum=0;
		double shootHitRate=0;
		int threeHitNum=0;
		int threeAttemptNum=0;
		double threeHitRate=0;
		int freeThrowHitNum=0;
		int freeThrowAttemptNum=0;
		double freeThrowHitRate=0;
		int offenReboundNum=0;
		int defenReboundNum=0;
		int stealNum=0;
		int blockNum=0;
		int foulNum=0;
		int turnOverNum=0;
		int score=0;
		double efficiency=0;
		double recentFiveMatchesScoreUpRate=0;
		double recentFiveMatchesReboundUpRate=0;
		double recentFiveMatchesAssistUpRate=0;
		double GmScEfficiencyValue=0;
		double trueHitRate=0;
		double shootEfficiency=0;
		double reboundRate=0;
		double offenReboundRate=0;
		double defenReboundRate=0;
		double assistRate=0;
		double stealRate=0;
		double blockRate=0;
		double turnOverRate=0;
		double usageRate=0;
		double score_rebound_assist=0;
		double doubleDoubleNum=0;
		boolean isThePlayerInTheMatch=false;
		String thePlayerTeam;
		
		for(MatchVO match:matches){
		   isThePlayerInTheMatch=false;
		   thePlayerTeam="";
			for(RecordVO record:match.getRecords()){
				if(record.getPlayerName().equals(name)){
					isThePlayerInTheMatch=true;
					thePlayerTeam=record.getTeam();
					playedGames++;
					if(!record.getPosition().equals("")){
						gameStartingNum++;
					}
					reboundNum+=record.getReboundNum();
					assistNum+=record.getAssistNum();
					presentTime =addPresentTime(presentTime, record.getPresentTime());
					shootHitNum += record.getShootHitNum();
					shootAttemptNum += record.getShootAttemptNum();
					threeHitNum += record.getThreeHitNum();
					threeAttemptNum += record.getThreeAttemptNum();
					freeThrowHitNum+= record.getFreeThrowHitNum();
					freeThrowAttemptNum += record.getFreeThrowAttemptNum();
					offenReboundNum += record.getOffenReboundNum();
					defenReboundNum += record.getDefenReboundNum();
					stealNum += record.getStealNum();
					blockNum += record.getBlockNum();
					turnOverNum += record.getTurnOverNum();
					foulNum+= record.getFoulNum();
					score += record.getScore();
					int temp = 0;
					if (record.getScore() >= 10)
						temp++;
					if (record.getReboundNum() >= 10)
						temp++;
					if (record.getAssistNum() >= 10)
						temp++;
					if (record.getStealNum() >= 10)
						temp++;
					if (record.getBlockNum() >= 10)
						temp++;
					if (temp >= 2)
						doubleDoubleNum++;
					
				}
			}
			if(isThePlayerInTheMatch==true){
				if(!owingTeam.contains(thePlayerTeam)){
					owingTeam=owingTeam+thePlayerTeam+" ";
				}
			}
		}
		
		if(playedGames)
	}

	public ArrayList<PlayerVO> getOrderedPlayersBySeason(String season,
			String condition, String order, int num) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<PlayerVO> getOrderedPlayersByAverage(
			String condition, String order, int num) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<PlayerVO> selectPlayersBySeason(String season,
			String position, String union, String column, int num) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<PlayerVO> selectPlayersByAverage(
			String position, String union, String column, int num) {
		// TODO Auto-generated method stub
		return null;
	}

	public ImageIcon getPlayerPortraitImage(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public ImageIcon getPlayerActionImage(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<PlayerVO> getDayHotPlayer(String column)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<PlayerVO> getSeasonHotPlayer(String season, String column) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<PlayerVO> getBestImprovedPlayer(String column) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<PlayerVO> getPlayersByInitialName(char character) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<MatchVO> getRecentMatches(String playerName)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<MatchVO> getTodayMatches(String playerName) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<MatchVO> getMatches(String playerName) {
		// TODO Auto-generated method stub
		return null;
	}
		
	public ArrayList<String> getPlayersByTeam(String teamAbLocation) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String addPresentTime(String a, String b) {
		String[] aTemp = a.split(":");
		String[] bTemp = b.split(":");
		int a1 = Integer.parseInt(aTemp[0]);
		int a2 = Integer.parseInt(aTemp[1]);
		int b1 = Integer.parseInt(bTemp[0]);
		int b2 = Integer.parseInt(bTemp[1]);
		String result1;
		String result2;
		if (a2 + b2 < 60) {
			result2 = String.valueOf(a2 + b2);
			result1 = String.valueOf(a1 + b1);
		} else {
			result2 = String.valueOf(a2 + b2 - 60);
			result1 = String.valueOf(a1 + b1 + 1);
		}
		return result1 + ":" + result2;
	}


}

