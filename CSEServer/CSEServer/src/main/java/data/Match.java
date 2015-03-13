package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import po.MatchPO;
import po.RecordPO;

public class Match {
	int count = 1;

	public MatchPO readFromMatchFile(String fileName) {
		MatchPO matchPO;
		String season;// 赛季
		String date = null;// 比赛日期
		String teams = null;// 对阵队伍
		String score = null;// 比分
		ArrayList<String> detailScores = new ArrayList<String>();// 各节比分
		ArrayList<RecordPO> records = new ArrayList<RecordPO>();// 球员比分数据记录

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

			boolean isComplete = false;
			temp = br.readLine();
			while (temp != null) {
				if (fileName.contains(temp)) {
					team = temp;
					isComplete = false;
				} else {
					String[] line = temp.split(";");
					playerName = DirtyDataManager
							.checkString(fileName, line[0]);
					position = line[1];
					presentTime = line[2];// 在场时间
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
					RecordPO recordPO = new RecordPO(team, playerName,
							position, presentTime, shootHitNum,
							shootAttemptNum, threeHitNum, threeAttemptNum,
							freeThrowHitNum, freeThrowAttemptNum,
							offenReboundNum, defenReboundNum, reboundNum,
							assistNum, stealNum, blockNum, turnOverNum,
							foulNum, personScore);
					records.add(recordPO);
					// System.out.println(playerName);
					isComplete = false;
				}
				temp = br.readLine();
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		matchPO = new MatchPO(count, season, date, teams, score, detailScores,
				records);
		count++;
		return matchPO;
	}

	public ArrayList<MatchPO> getMatchesList() {
		ArrayList<MatchPO> matches = new ArrayList<MatchPO>();
		try {
			FileList fl = new FileList("src/迭代一数据/matches");
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

	public void exportToSql() {
		ArrayList<MatchPO> matches = getMatchesList();
		try {
			Connection con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			sql.execute("drop table if exists matches");
			// sql.execute("drop table if exists detailScores");
			// sql.execute("drop table if exists records");

			// 创建matches表
			sql.execute("create table matches(matchID int not null auto_increment,"
					+ "season varchar(20) not null default 'null',"
					+ "date varchar(20) not null default 'null',"
					+ "visitingTeam varchar(20) not null default 'null',"
					+ "visitingScore int not null default 0,"
					+ "homeTeam varchar(20) not null default 'null',"
					+ "homeScore int not null default 0,"
					+ "primary key(matchID));");

			// // 创建detailScores表
			// sql.execute("create table detailScores(id int not null auto_increment,"
			// + "matchID int not null default -1,"
			// + "part int not null default -1,"
			// + "score varchar(20) not null default 'null',"
			// + "primary key(id));");
			//
			// // 创建records表
			// sql.execute("create table records(id int not null auto_increment,"
			// + "matchID int not null default -1,"
			// + "team varchar(20) not null default 'null',"
			// + "playerName varchar(40) not null default 'null',"
			// + "presentTime varchar(20) not null default 'null',"
			// + "position varchar(20) not null default 'null',"
			// + "shootHitNum int not null default 0,"
			// + "shootAttemptNum int not null default 0,"
			// + "threeHitNum int not null default 0,"
			// + "threeAttemptNum int not null default 0,"
			// + "freeThrowHitNum int not null default 0,"
			// + "freeThrowAttemptNum int not null default 0,"
			// + "offenReboundNum int not null default 0,"
			// + "defenReboundNum int not null default 0,"
			// + "reboundNum int not null default 0,"
			// + "assistNum int not null default 0,"
			// + "stealNum int not null default 0,"
			// + "blockNum int not null default 0,"
			// + "turnOverNum int not null default 0,"
			// + "foulNum int not null default 0,"
			// + "score int not null default 0," + "primary key(id));");
			//
			// // index分别表示各表的id
			// int scoreIndex = 1;
			// int recordIndex = 1;

			int test = 1;// 用于标示数据录入过程的，无多大实际意义
			for (MatchPO matchPO : matches) {
				// 向matches表中插入数据
				String[] teams = matchPO.getTeams().split("-");
				String visitingTeam = teams[0];
				String homeTeam = teams[1];
				String[] scores = matchPO.getScore().split("-");
				int visitingScore = Integer.parseInt(scores[0]);
				int homeScore = Integer.parseInt(scores[1]);
				sql.execute("insert matches values(" + matchPO.getMatchID()
						+ ",'" + matchPO.getSeason() + "','"
						+ matchPO.getDate() + "','" + visitingTeam + "',"
						+ visitingScore + ",'" + homeTeam + "'," + homeScore
						+ ")");

				// // 向detailScores表中插入数据
				// ArrayList<String> detailScore = matchPO.getDetailScores();
				// int partIndex = 1;
				// for (String s : detailScore) {
				// sql.execute("insert detailScores values(" + scoreIndex
				// + "," + matchPO.getMatchID() + "," + partIndex
				// + ",'" + s + "')");
				// partIndex++;
				// scoreIndex++;
				// }
				//
				// // 向records表中插入数据
				// ArrayList<RecordPO> records = matchPO.getRecords();
				// for (RecordPO recordPO : records) {
				// sql.execute("insert records values(" + recordIndex + ","
				// + matchPO.getMatchID() + ",'" + recordPO.getTeam()
				// + "','" + recordPO.getPlayerName() + "','"
				// + recordPO.getPresentTime() + "','"
				// + recordPO.getPosition() + "',"
				// + recordPO.getShootHitNum() + ","
				// + recordPO.getShootAttemptNum() + ","
				// + recordPO.getThreeHitNum() + ","
				// + recordPO.getThreeAttemptNum() + ","
				// + recordPO.getFreeThrowHitNum() + ","
				// + recordPO.getFreeThrowAttemptNum() + ","
				// + recordPO.getOffenReboundNum() + ","
				// + recordPO.getDefenReboundNum() + ","
				// + recordPO.getReboundNum() + ","
				// + recordPO.getAssistNum() + ","
				// + recordPO.getStealNum() + ","
				// + recordPO.getBlockNum() + ","
				// + recordPO.getTurnOverNum() + ","
				// + recordPO.getFoulNum() + "," + recordPO.getScore()
				// + ")");
				// recordIndex++;
				// }
				System.out.println(test++);
			}
			sql.close();
			con.close();
		} catch (java.lang.ClassNotFoundException e) {
			System.err.println("ClassNotFoundException:" + e.getMessage());
		} catch (SQLException ex) {
			System.out.println("SQLException:" + ex.getMessage());
		}
	}

	public static void main(String[] args) {
		Match Match = new Match();
		Match.exportToSql();
		// Match.getMatchesList();
		System.out.println("success!");
	}
}
