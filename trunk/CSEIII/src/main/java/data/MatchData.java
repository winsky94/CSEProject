package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import vo.MatchVO;
import vo.RecordVO;
import SQLHelper.FileList;
import SQLHelper.SqlManager;
import dataservice.MatchDataService;

/**
 * 从文件中读取数据，用于将比赛数据读到数据库中 数据分为三个表存储
 * matches：存储比赛的编号、赛季、日期、主客队名称、比分等（主客队和相应的比分都分开存）
 * records：存储比赛的每个球员的比赛ID、所属球队名称以及一些基本的技术数据
 * detailscores：存储比赛的ID，每节的比分信息(考虑到加时赛，所以一节比分是一个元组)
 */
public class MatchData implements MatchDataService {
	Connection connection = null;
	Statement sql = null;
	ResultSet resultSet = null;

	int count = 1;
	int time = 0;// 比赛时间

	public static void main(String[] args) {
		MatchData matchData = new MatchData();
		matchData.exportToSql();
//		System.out.println("MatchData.main()");
//		ArrayList<MatchVO> result = matchData.getMatchData("all", "all",
//				"all", "all");
//		System.out.println(result.size());
	}

	public ArrayList<String> getAllSeasons() {
		// TODO 自动生成的方法存根
		ArrayList<String> result = new ArrayList<String>();
		try {
			connection = SqlManager.getConnection();
			sql = connection.createStatement();
			String query = "select season from matches group by season";
			resultSet = sql.executeQuery(query);
			while (resultSet.next()) {
				String season = resultSet.getString("season");
				result.add(season);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeMySql();
		}

		return result;
	}

	public ArrayList<MatchVO> getMatchesBySeason(String season) {
		// TODO 自动生成的方法存根
		ArrayList<MatchVO> result = new ArrayList<MatchVO>();
		try {
			connection = SqlManager.getConnection();
			sql = connection.createStatement();
			String query = "select * from matches where season='" + season
					+ "'";
			resultSet = sql.executeQuery(query);
			while (resultSet.next()) {
				int matchID = resultSet.getInt("matchID");
				String date = resultSet.getString("date");
				String type = resultSet.getString("type");
				String visitingTeam = resultSet.getString("visitingTeam");
				String homeTeam = resultSet.getString("homeTeam");
				int visitingScore = resultSet.getInt("visitingScore");
				int homeScore = resultSet.getInt("homeScore");
				ArrayList<String> detailScores = new ArrayList<String>();
				Statement sql1 = connection.createStatement();
				ResultSet rs1 = sql1
						.executeQuery("select score from detailscores where matchID="
								+ matchID);
				detailScores = new ArrayList<String>();
				while (rs1.next()) {
					detailScores.add(rs1.getString("score"));
				}
				rs1.close();
				sql1.close();

				ArrayList<RecordVO> records = new ArrayList<RecordVO>();
				Statement sql2 = connection.createStatement();
				ResultSet rs2 = sql2
						.executeQuery("select * from records where matchID="
								+ matchID);
				RecordVO vo;
				while (rs2.next()) {
					vo = new RecordVO(rs2.getString("team"),
							rs2.getString("playerName"),
							rs2.getString("position"),
							rs2.getString("presentTime"),
							rs2.getInt("shootHitNum"),
							rs2.getInt("shootAttemptNum"),
							rs2.getDouble("shootHitRate"),
							rs2.getInt("threeHitNum"),
							rs2.getInt("threeAttemptNum"),
							rs2.getDouble("threeHitRate"),
							rs2.getInt("freeThrowHitNum"),
							rs2.getInt("freeThrowAttemptNum"),
							rs2.getDouble("freeThrowHitRate"),
							rs2.getInt("offenReboundNum"),
							rs2.getInt("defenReboundNum"),
							rs2.getInt("reboundNum"), rs2.getInt("assistNum"),
							rs2.getInt("stealNum"), rs2.getInt("blockNum"),
							rs2.getInt("turnOverNum"), rs2.getInt("foulNum"),
							rs2.getInt("score"));
					records.add(vo);
				}
				rs2.close();
				sql2.close();

				MatchVO matchVO = new MatchVO(season, date, type, visitingTeam,
						homeTeam, visitingScore, homeScore, detailScores,
						records);
				result.add(matchVO);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeMySql();
		}
		return result;
	}

	public ArrayList<MatchVO> getMatchesByTeam(String name) {
		// TODO 自动生成的方法存根
		ArrayList<MatchVO> result = new ArrayList<MatchVO>();
		try {
			connection = SqlManager.getConnection();
			sql = connection.createStatement();
			String query = "select * from matches where homeTeam='%" + name
					+ "%' or visitingTeam='%" + name + "%'";
			resultSet = sql.executeQuery(query);
			while (resultSet.next()) {
				int matchID = resultSet.getInt("matchID");
				String season = resultSet.getString("season");
				String date = resultSet.getString("date");
				String type = resultSet.getString("type");
				String visitingTeam = resultSet.getString("visitingTeam");
				String homeTeam = resultSet.getString("homeTeam");
				int visitingScore = resultSet.getInt("visitingScore");
				int homeScore = resultSet.getInt("homeScore");
				ArrayList<String> detailScores = new ArrayList<String>();
				Statement sql1 = connection.createStatement();
				ResultSet rs1 = sql1
						.executeQuery("select score from detailscores where matchID="
								+ matchID);
				detailScores = new ArrayList<String>();
				while (rs1.next()) {
					detailScores.add(rs1.getString("score"));
				}
				rs1.close();
				sql1.close();

				ArrayList<RecordVO> records = new ArrayList<RecordVO>();
				Statement sql2 = connection.createStatement();
				ResultSet rs2 = sql2
						.executeQuery("select * from records where matchID="
								+ matchID);
				RecordVO vo;
				while (rs2.next()) {
					vo = new RecordVO(rs2.getString("team"),
							rs2.getString("playerName"),
							rs2.getString("position"),
							rs2.getString("presentTime"),
							rs2.getInt("shootHitNum"),
							rs2.getInt("shootAttemptNum"),
							rs2.getDouble("shootHitRate"),
							rs2.getInt("threeHitNum"),
							rs2.getInt("threeAttemptNum"),
							rs2.getDouble("threeHitRate"),
							rs2.getInt("freeThrowHitNum"),
							rs2.getInt("freeThrowAttemptNum"),
							rs2.getDouble("freeThrowHitRate"),
							rs2.getInt("offenReboundNum"),
							rs2.getInt("defenReboundNum"),
							rs2.getInt("reboundNum"), rs2.getInt("assistNum"),
							rs2.getInt("stealNum"), rs2.getInt("blockNum"),
							rs2.getInt("turnOverNum"), rs2.getInt("foulNum"),
							rs2.getInt("score"));
					records.add(vo);
				}
				rs2.close();
				sql2.close();

				MatchVO matchVO = new MatchVO(season, date, type, visitingTeam,
						homeTeam, visitingScore, homeScore, detailScores,
						records);
				result.add(matchVO);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeMySql();
		}
		return result;
	}

	public ArrayList<MatchVO> getMatchData(String season, String date,
			String homeTeam, String visitingTeam) {
		// TODO 自动生成的方法存根
		String query = "select * from matches where";
		ArrayList<MatchVO> matches = new ArrayList<MatchVO>();

		int flag = 0;

		if (!season.equals("all")) {
			if (flag == 1)
				query = query + " and season like '%" + season + "%'";
			else {
				query = query + " season like '%" + season + "%'";
				flag = 1;
			}
		}

		if (!date.equals("all")) {
			if (flag == 1)
				query = query + " and date like '%" + date + "%'";
			else {
				query = query + " date like '%" + date + "%'";
				flag = 1;
			}
		}

		if (!homeTeam.equals("all")) {
			if (flag == 1)
				query = query + " and homeTeam like '%" + homeTeam + "%'";
			else {
				query = query + " homeTeam like '%" + homeTeam + "%'";
				flag = 1;
			}
		}

		if (!visitingTeam.equals("all")) {
			if (flag == 1)
				query = query + " and visitingTeam like '%" + visitingTeam
						+ "%'";
			else {
				query = query + " visitingTeam like '%" + visitingTeam + "%'";
				flag = 1;
			}
		}

		if (season.equals("all") && date.equals("all")
				&& homeTeam.equals("all") && visitingTeam.equals("all")) {
			query = "select * from matches";
		}

		try {
			connection = SqlManager.getConnection();
			Statement sql = connection.createStatement();
			ResultSet rs = sql.executeQuery(query);
			int matchID = 0;
			String myseason = "";
			String mydate = "";
			String type = "";
			String myvisingTeam = "";
			String myhomeTeam = "";
			int visitingScore = 0;
			int homeScore = 0;
			ArrayList<String> detailScores;
			ArrayList<RecordVO> records;
			MatchVO matchVO;

			while (rs.next()) {
				matchID = rs.getInt("matchID");
				myseason = rs.getString("season");
				mydate = rs.getString("date");
				type = rs.getString("type");
				myvisingTeam = rs.getString("visitingTeam");
				myhomeTeam = rs.getString("homeTeam");
				visitingScore = rs.getInt("visitingScore");
				homeScore = rs.getInt("homeScore");
				Statement sql2 = connection.createStatement();
				ResultSet rs1 = sql2
						.executeQuery("select score from detailscores where matchID="
								+ matchID);
				detailScores = new ArrayList<String>();
				while (rs1.next()) {
					detailScores.add(rs1.getString("score"));
				}
				rs1.close();
				sql2.close();
				Statement sql3 = connection.createStatement();
				ResultSet rs2 = sql3
						.executeQuery("select * from records where matchID="
								+ matchID);
				records = new ArrayList<RecordVO>();
				RecordVO vo;
				while (rs2.next()) {
					vo = new RecordVO(rs2.getString("team"),
							rs2.getString("playerName"),
							rs2.getString("position"),
							rs2.getString("presentTime"),
							rs2.getInt("shootHitNum"),
							rs2.getInt("shootAttemptNum"),
							rs2.getDouble("shootHitRate"),
							rs2.getInt("threeHitNum"),
							rs2.getInt("threeAttemptNum"),
							rs2.getDouble("threeHitRate"),
							rs2.getInt("freeThrowHitNum"),
							rs2.getInt("freeThrowAttemptNum"),
							rs2.getDouble("freeThrowHitRate"),
							rs2.getInt("offenReboundNum"),
							rs2.getInt("defenReboundNum"),
							rs2.getInt("reboundNum"), rs2.getInt("assistNum"),
							rs2.getInt("stealNum"), rs2.getInt("blockNum"),
							rs2.getInt("turnOverNum"), rs2.getInt("foulNum"),
							rs2.getInt("score"));
					records.add(vo);
				}
				rs2.close();
				sql3.close();
				matchVO = new MatchVO(matchID, myseason, mydate, type,
						myvisingTeam, myhomeTeam, visitingScore, homeScore,
						detailScores, records);
				matches.add(matchVO);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeMySql();
		}

		return matches;
	}

	public MatchVO readFromMatchFile(String fileName) {
		MatchVO matchPO;
		String season;// 赛季
		String date = null;// 比赛日期
		String teams = null;// 对阵队伍
		String score = null;// 比分
		String type = null; // 比赛类型

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
			type = fisrtContent[3];

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
			double shootHitRate = 0;// 投篮命中率
			int threeHitNum = 0;// 三分命中数
			int threeAttemptNum = 0;// 三分出手数
			double threeHitRate = 0;// 三分命中率
			int freeThrowHitNum = 0;// 罚球命中数
			int freeThrowAttemptNum = 0;// 罚球出手数
			double freeThrowHitRate = 0;// 罚球命中率
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
					position = line[1];
					presentTime = line[2];// 在场时间
					shootHitNum = Integer.parseInt(line[3]);// 投篮命中数
					shootAttemptNum = Integer.parseInt(line[4]);// 投篮出手数
					shootHitRate = Integer.parseInt(line[5]);
					threeHitNum = Integer.parseInt(line[6]);// 三分命中数
					threeAttemptNum = Integer.parseInt(line[7]);// 三分出手数
					threeHitRate = Integer.parseInt(line[8]);
					freeThrowHitNum = Integer.parseInt(line[9]);// 罚球命中数
					freeThrowAttemptNum = Integer.parseInt(line[10]);// 罚球出手数
					freeThrowHitRate = Integer.parseInt(line[11]);
					offenReboundNum = Integer.parseInt(line[12]);// 进攻（前场）篮板数
					defenReboundNum = Integer.parseInt(line[13]);// 防守（后场）篮板数
					reboundNum = Integer.parseInt(line[14]);// 总篮板数
					assistNum = Integer.parseInt(line[15]);// 助攻数
					stealNum = Integer.parseInt(line[16]);// 抢断数
					blockNum = Integer.parseInt(line[17]);// 盖帽数
					turnOverNum = Integer.parseInt(line[18]);// 失误数
					foulNum = Integer.parseInt(line[19]);// 犯规数
					personScore = Integer.parseInt(line[20]);// 个人得分
					// String[] line = temp.split(";");
					// playerName = line[0];
					// position = line[1];
					// presentTime = DirtyDataManager.checkPresentTime(fileName,
					// team, playerName, line[2]);// 在场时间
					// shootHitNum = Integer.parseInt(line[3]);// 投篮命中数
					// shootAttemptNum = DirtyDataManager.checkShootAndHitNum(
					// fileName, Integer.parseInt(line[4]), shootHitNum);//
					// 投篮出手数
					// shootHitRate = Integer.parseInt(line[5]);
					// threeHitNum = Integer.parseInt(line[6]);// 三分命中数
					// threeAttemptNum = DirtyDataManager.checkShootAndHitNum(
					// fileName, Integer.parseInt(line[7]), threeHitNum);//
					// 三分出手数
					// threeHitRate =Integer.parseInt(line[8]);
					// freeThrowHitNum = Integer.parseInt(line[9]);// 罚球命中数
					// freeThrowAttemptNum =
					// DirtyDataManager.checkShootAndHitNum(
					// fileName, Integer.parseInt(line[10]),
					// freeThrowHitNum);// 罚球出手数
					// freeThrowHitRate=Integer.parseInt(line[11]);
					// offenReboundNum = Integer.parseInt(line[12]);// 进攻（前场）篮板数
					// defenReboundNum = Integer.parseInt(line[13]);// 防守（后场）篮板数
					// reboundNum = DirtyDataManager.checkReboundNum(fileName,
					// offenReboundNum, defenReboundNum,
					// Integer.parseInt(line[14]));// 总篮板数
					// assistNum = Integer.parseInt(line[15]);// 助攻数
					// stealNum = Integer.parseInt(line[16]);// 抢断数
					// blockNum = Integer.parseInt(line[17]);// 盖帽数
					// turnOverNum = Integer.parseInt(line[18]);// 失误数
					// foulNum = Integer.parseInt(line[19]);// 犯规数
					// personScore = DirtyDataManager.checkPersonScore(fileName,
					// line[20], temp);// 个人得分

					isComplete = true;
				}
				if (isComplete) {
					RecordVO recordPO = new RecordVO(team, playerName,
							position, presentTime, shootHitNum,
							shootAttemptNum, shootHitRate, threeHitNum,
							threeAttemptNum, threeHitRate, freeThrowHitNum,
							freeThrowAttemptNum, freeThrowHitRate,
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

		String[] temp = teams.split("-");
		String visitingTeam = temp[0];
		String homeTeam = temp[1];

		String[] s = score.split("-");
		int visitingScore = Integer.parseInt(s[0]);
		int homeScore = Integer.parseInt(s[1]);

		matchPO = new MatchVO(count, season, date, type, visitingTeam,
				homeTeam, visitingScore, homeScore, detailScores, records);
		count++;
		return matchPO;
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

	public void exportToSql() {
		ArrayList<MatchVO> matches = getMatchesListFromFile();
		try {
			Connection con = SqlManager.getConnection();
			con.setAutoCommit(false);
			Statement sql = con.createStatement();
			sql.execute("drop table if exists matches");
			sql.execute("drop table if exists detailScores");
			sql.execute("drop table if exists records");

			// 创建matches表
			sql.execute("create table matches(matchID int not null auto_increment,"
					+ "season varchar(20) not null default 'null',"
					+ "date varchar(20) not null default 'null',"
					+ "type varchar(20) not null default 'null',"
					+ "visitingTeam varchar(20) not null default 'null',"
					+ "visitingScore int not null default 0,"
					+ "homeTeam varchar(20) not null default 'null',"
					+ "homeScore int not null default 0,"
					+ "time int not null default 0," + "primary key(matchID));");

			// 创建detailScores表
			sql.execute("create table detailScores(detailID int not null auto_increment,"
					+ "matchID int not null default -1,"
					+ "part int not null default -1,"
					+ "score varchar(20) not null default 'null',"
					+ "primary key(detailID));");

			// 创建records表
			sql.execute("create table records(recordID int not null auto_increment,"
					+ "matchID int not null default -1,"
					+ "team varchar(20) not null default 'null',"
					+ "season varchar(20) not null default 'null',"
					+ "date varchar(20) not null default 'null',"
					+ "type varchar(20) not null default 'null',"
					+ "playerName varchar(40) not null default 'null',"
					+ "presentTime varchar(20) not null default 'null',"
					+ "position varchar(20) not null default 'null',"
					+ "shootHitNum int not null default 0,"
					+ "shootAttemptNum int not null default 0,"
					+ "shootHitRate double not null default 0,"
					+ "threeHitNum int not null default 0,"
					+ "threeAttemptNum int not null default 0,"
					+ "threeHitRate double not null default 0,"
					+ "freeThrowHitNum int not null default 0,"
					+ "freeThrowAttemptNum int not null default 0,"
					+ "freeThrowHitRate double not null default 0,"
					+ "offenReboundNum int not null default 0,"
					+ "defenReboundNum int not null default 0,"
					+ "reboundNum int not null default 0,"
					+ "assistNum int not null default 0,"
					+ "stealNum int not null default 0,"
					+ "blockNum int not null default 0,"
					+ "turnOverNum int not null default 0,"
					+ "foulNum int not null default 0,"
					+ "score int not null default 0,"
					+ "primary key(recordID));");

			// index分别表示各表的id
			int scoreIndex = 1;
			int recordIndex = 1;

			int test = 1;// 用于标示数据录入过程的，无多大实际意义

			sql.close();
			PreparedStatement matchesStatement = con
					.prepareStatement("INSERT INTO matches VALUES(?,?,?,?,?,?,?,?,?)");
			PreparedStatement detailScoreStatement = con
					.prepareStatement("INSERT INTO detailScores VALUES(?, ?,?,?)");
			PreparedStatement recordsStatement = con
					.prepareStatement("INSERT INTO records VALUES(?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			for (MatchVO matchVO : matches) {
				// 向matches表中插入数据
				String visitingTeam = matchVO.getVisitingTeam();
				String homeTeam = matchVO.getHomeTeam();
				int visitingScore = matchVO.getVisitingScore();
				int homeScore = matchVO.getHomeScore();
				int parts = matchVO.getDetailScores().size();
				if (parts <= 4) {
					time = 48;
				} else {
					time = 48 + (parts - 4) * 5;
				}
				time = time * 60;
				matchesStatement.setInt(1, matchVO.getMatchID());
				matchesStatement.setString(2, matchVO.getSeason());
				matchesStatement.setString(3, matchVO.getDate());
				matchesStatement.setString(4, matchVO.getType());
				matchesStatement.setString(5, visitingTeam);
				matchesStatement.setInt(6, visitingScore);
				matchesStatement.setString(7, homeTeam);
				matchesStatement.setInt(8, homeScore);
				matchesStatement.setInt(9, time);
				matchesStatement.addBatch();

				// 向detailScores表中插入数据
				ArrayList<String> detailScore = matchVO.getDetailScores();
				int partIndex = 1;
				for (String s : detailScore) {
					detailScoreStatement.setInt(1, scoreIndex);
					detailScoreStatement.setInt(2, matchVO.getMatchID());
					detailScoreStatement.setInt(3, partIndex);
					detailScoreStatement.setString(4, s);
					detailScoreStatement.addBatch();
					partIndex++;
					scoreIndex++;
				}

				// 向records表中插入数据
				ArrayList<RecordVO> records = matchVO.getRecords();
				for (RecordVO recordPO : records) {
					recordsStatement.setInt(1, recordIndex);
					recordsStatement.setInt(2, matchVO.getMatchID());
					recordsStatement.setString(3, recordPO.getTeam());
					recordsStatement.setString(4, matchVO.getSeason());
					recordsStatement.setString(5, matchVO.getDate());
					recordsStatement.setString(6, matchVO.getType());
					recordsStatement.setString(7, recordPO.getPlayerName());
					recordsStatement.setString(8, recordPO.getPresentTime());
					recordsStatement.setString(9, recordPO.getPosition());
					recordsStatement.setInt(10, recordPO.getShootHitNum());
					recordsStatement.setInt(11, recordPO.getShootAttemptNum());
					recordsStatement.setDouble(12, recordPO.getShootHitRate());
					recordsStatement.setInt(13, recordPO.getThreeHitNum());
					recordsStatement.setInt(14, recordPO.getThreeAttemptNum());
					recordsStatement.setDouble(15, recordPO.getThreeHitRate());
					recordsStatement.setInt(16, recordPO.getFreeThrowHitNum());
					recordsStatement.setInt(17,
							recordPO.getFreeThrowAttemptNum());
					recordsStatement.setDouble(18,
							recordPO.getFreeThrowHitRate());
					recordsStatement.setInt(19, recordPO.getOffenReboundNum());
					recordsStatement.setInt(20, recordPO.getDefenReboundNum());
					recordsStatement.setInt(21, recordPO.getReboundNum());
					recordsStatement.setInt(22, recordPO.getAssistNum());
					recordsStatement.setInt(23, recordPO.getStealNum());
					recordsStatement.setInt(24, recordPO.getBlockNum());
					recordsStatement.setInt(25, recordPO.getTurnOverNum());
					recordsStatement.setInt(26, recordPO.getFoulNum());
					recordsStatement.setInt(27, recordPO.getScore());
					recordsStatement.addBatch();
					recordIndex++;
				}
				System.out.println(test++);
			}
			matchesStatement.executeBatch();
			detailScoreStatement.executeBatch();
			recordsStatement.executeBatch();
			con.commit();
			matchesStatement.close();
			detailScoreStatement.close();
			recordsStatement.close();
			con.close();
		} catch (java.lang.ClassNotFoundException e) {
			System.err.println("ClassNotFoundException:" + e.getMessage());
		} catch (SQLException ex) {
			System.out.println("SQLException:" + ex.getMessage());
		}
	}

	private void closeMySql() {
		try {
			if (resultSet != null) {
				resultSet.close();
				resultSet = null;
			}
			if (sql != null) {
				sql.close();
				sql = null;
			}
			if (connection != null) {
				connection.close();
				connection = null;
			}

		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
