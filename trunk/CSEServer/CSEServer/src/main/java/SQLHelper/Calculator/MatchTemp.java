package SQLHelper.Calculator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import SQLHelper.SqlManager;

/**
 * 
 * 从records表中读取数据，统计每场比赛主客队的相应的技术数据 存储于表：matchtemp
 *
 */
public class MatchTemp {
	Connection con;

	public MatchTemp() {
		try {
			con = SqlManager.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	public void matchTempInit() {
		int visitingShootHitNum = 0; // 投篮命中数
		int visitingShootAttemptNum = 0; // 投篮出手次数
		int visitingThreeHitNum = 0; // 三分命中数
		int visitingThreeAttemptNum = 0; // 三分出手数
		int visitingFreeThrowHitNum = 0; // 罚球命中数
		int visitingFreeThrowAttemptNum = 0; // 罚球出手数
		int visitingOffenReboundNum = 0; // 进攻篮板数
		int visitingDefenReboundNum = 0; // 防守篮板数
		int visitingAssistNum = 0;// 助攻数
		int visitingStealNum = 0;// 抢断数
		int visitingBlockNum = 0;// 盖帽数
		int visitingTurnOverNum = 0;// 失误数
		int visitingFoulNum = 0;// 犯规数
		int visitingScore = 0;// 得分

		int homeShootHitNum = 0; // 投篮命中数
		int homeShootAttemptNum = 0; // 投篮出手次数
		int homeThreeHitNum = 0; // 三分命中数
		int homeThreeAttemptNum = 0; // 三分出手数
		int homeFreeThrowHitNum = 0; // 罚球命中数
		int homeFreeThrowAttemptNum = 0; // 罚球出手数
		int homeOffenReboundNum = 0; // 进攻篮板数
		int homeDefenReboundNum = 0; // 防守篮板数
		int homeAssistNum = 0;// 助攻数
		int homeStealNum = 0;// 抢断数
		int homeBlockNum = 0;// 盖帽数
		int homeTurnOverNum = 0;// 失误数
		int homeFoulNum = 0;// 犯规数
		int homeScore = 0;// 得分

		try {
			Statement sql3 = con.createStatement();
			createTable();
			int count = 1;
			Statement sql = con.createStatement();
			String query = "select * from records";
			ResultSet resultSet = sql.executeQuery(query);
			int tempID = 0;
			String visitingTeam = null;
			String homeTeam = null;
			String season = null;
			while (resultSet.next()) {
				int matchID = resultSet.getInt("matchID");
				season = resultSet.getString("season");

				if (tempID == matchID || tempID == 0) {
					tempID = matchID;
					String team = resultSet.getString("team");
					if (team.equals(visitingTeam)) {
						visitingShootHitNum += resultSet.getInt("shootHitNum"); // 投篮命中数
						visitingShootAttemptNum += resultSet
								.getInt("shootAttemptNum"); // 投篮出手次数
						visitingThreeHitNum += resultSet.getInt("threeHitNum"); // 三分命中数
						visitingThreeAttemptNum += resultSet
								.getInt("threeAttemptNum"); // 三分出手数
						visitingFreeThrowHitNum += resultSet
								.getInt("freeThrowHitNum"); // 罚球命中数
						visitingFreeThrowAttemptNum += resultSet
								.getInt("freeThrowAttemptNum"); // 罚球出手数
						visitingOffenReboundNum += resultSet
								.getInt("offenReboundNum"); // 进攻篮板数
						visitingDefenReboundNum += resultSet
								.getInt("defenReboundNum"); // 防守篮板数
						visitingAssistNum += resultSet.getInt("assistNum");// 助攻数
						visitingStealNum += resultSet.getInt("stealNum");// 抢断数
						visitingBlockNum += resultSet.getInt("blockNum");// 盖帽数
						visitingTurnOverNum += resultSet.getInt("turnOverNum");// 失误数
						visitingFoulNum += resultSet.getInt("foulNum");// 犯规数
						visitingScore += resultSet.getInt("score");// 得分
					} else if (team.equals(homeTeam)) {
						homeShootHitNum += resultSet.getInt("shootHitNum"); // 投篮命中数
						homeShootAttemptNum += resultSet
								.getInt("shootAttemptNum"); // 投篮出手次数
						homeThreeHitNum += resultSet.getInt("threeHitNum"); // 三分命中数
						homeThreeAttemptNum += resultSet
								.getInt("threeAttemptNum"); // 三分出手数
						homeFreeThrowHitNum += resultSet
								.getInt("freeThrowHitNum"); // 罚球命中数
						homeFreeThrowAttemptNum += resultSet
								.getInt("freeThrowAttemptNum"); // 罚球出手数
						homeOffenReboundNum += resultSet
								.getInt("offenReboundNum"); // 进攻篮板数
						homeDefenReboundNum += resultSet
								.getInt("defenReboundNum"); // 防守篮板数
						homeAssistNum += resultSet.getInt("assistNum");// 助攻数
						homeStealNum += resultSet.getInt("stealNum");// 抢断数
						homeBlockNum += resultSet.getInt("blockNum");// 盖帽数
						homeTurnOverNum += resultSet.getInt("turnOverNum");// 失误数
						homeFoulNum += resultSet.getInt("foulNum");// 犯规数
						homeScore += resultSet.getInt("score");// 得分
					}
				} else {
					sql3.execute("insert MatchTemp values(" + (count++) + ","
							+ tempID + ",'" + season + "','" + visitingTeam
							+ "'," + visitingShootHitNum + ","
							+ visitingShootAttemptNum + ","
							+ visitingThreeHitNum + ","
							+ visitingThreeAttemptNum + ","
							+ visitingFreeThrowHitNum + ","
							+ visitingFreeThrowAttemptNum + ","
							+ visitingOffenReboundNum + ","
							+ visitingDefenReboundNum + "," + visitingAssistNum
							+ "," + visitingStealNum + "," + visitingBlockNum
							+ "," + visitingTurnOverNum + "," + visitingFoulNum
							+ "," + visitingScore + ",'" + homeTeam + "',"
							+ homeShootHitNum + "," + homeShootAttemptNum + ","
							+ homeThreeHitNum + "," + homeThreeAttemptNum + ","
							+ homeFreeThrowHitNum + ","
							+ homeFreeThrowAttemptNum + ","
							+ homeOffenReboundNum + "," + homeDefenReboundNum
							+ "," + homeAssistNum + "," + homeStealNum + ","
							+ homeBlockNum + "," + homeTurnOverNum + ","
							+ homeFoulNum + "," + homeScore + ")");

					tempID = 0;

					visitingShootHitNum = 0; // 投篮命中数
					visitingShootAttemptNum = 0; // 投篮出手次数
					visitingThreeHitNum = 0; // 三分命中数
					visitingThreeAttemptNum = 0; // 三分出手数
					visitingFreeThrowHitNum = 0; // 罚球命中数
					visitingFreeThrowAttemptNum = 0; // 罚球出手数
					visitingOffenReboundNum = 0; // 进攻篮板数
					visitingDefenReboundNum = 0; // 防守篮板数
					visitingAssistNum = 0;// 助攻数
					visitingStealNum = 0;// 抢断数
					visitingBlockNum = 0;// 盖帽数
					visitingTurnOverNum = 0;// 失误数
					visitingFoulNum = 0;// 犯规数
					visitingScore = 0;// 得分

					homeShootHitNum = 0; // 投篮命中数
					homeShootAttemptNum = 0; // 投篮出手次数
					homeThreeHitNum = 0; // 三分命中数
					homeThreeAttemptNum = 0; // 三分出手数
					homeFreeThrowHitNum = 0; // 罚球命中数
					homeFreeThrowAttemptNum = 0; // 罚球出手数
					homeOffenReboundNum = 0; // 进攻篮板数
					homeDefenReboundNum = 0; // 防守篮板数
					homeAssistNum = 0;// 助攻数
					homeStealNum = 0;// 抢断数
					homeBlockNum = 0;// 盖帽数
					homeTurnOverNum = 0;// 失误数
					homeFoulNum = 0;// 犯规数
					homeScore = 0;// 得分

					tempID = matchID;
					String team = resultSet.getString("team");
					if (team.equals(visitingTeam)) {
						visitingShootHitNum += resultSet.getInt("shootHitNum"); // 投篮命中数
						visitingShootAttemptNum += resultSet
								.getInt("shootAttemptNum"); // 投篮出手次数
						visitingThreeHitNum += resultSet.getInt("threeHitNum"); // 三分命中数
						visitingThreeAttemptNum += resultSet
								.getInt("threeAttemptNum"); // 三分出手数
						visitingFreeThrowHitNum += resultSet
								.getInt("freeThrowHitNum"); // 罚球命中数
						visitingFreeThrowAttemptNum += resultSet
								.getInt("freeThrowAttemptNum"); // 罚球出手数
						visitingOffenReboundNum += resultSet
								.getInt("offenReboundNum"); // 进攻篮板数
						visitingDefenReboundNum += resultSet
								.getInt("defenReboundNum"); // 防守篮板数
						visitingAssistNum += resultSet.getInt("assistNum");// 助攻数
						visitingStealNum += resultSet.getInt("stealNum");// 抢断数
						visitingBlockNum += resultSet.getInt("blockNum");// 盖帽数
						visitingTurnOverNum += resultSet.getInt("turnOverNum");// 失误数
						visitingFoulNum += resultSet.getInt("foulNum");// 犯规数
						visitingScore += resultSet.getInt("score");// 得分
					} else if (team.equals(homeTeam)) {
						homeShootHitNum += resultSet.getInt("shootHitNum"); // 投篮命中数
						homeShootAttemptNum += resultSet
								.getInt("shootAttemptNum"); // 投篮出手次数
						homeThreeHitNum += resultSet.getInt("threeHitNum"); // 三分命中数
						homeThreeAttemptNum += resultSet
								.getInt("threeAttemptNum"); // 三分出手数
						homeFreeThrowHitNum += resultSet
								.getInt("freeThrowHitNum"); // 罚球命中数
						homeFreeThrowAttemptNum += resultSet
								.getInt("freeThrowAttemptNum"); // 罚球出手数
						homeOffenReboundNum += resultSet
								.getInt("offenReboundNum"); // 进攻篮板数
						homeDefenReboundNum += resultSet
								.getInt("defenReboundNum"); // 防守篮板数
						homeAssistNum += resultSet.getInt("assistNum");// 助攻数
						homeStealNum += resultSet.getInt("stealNum");// 抢断数
						homeBlockNum += resultSet.getInt("blockNum");// 盖帽数
						homeTurnOverNum += resultSet.getInt("turnOverNum");// 失误数
						homeFoulNum += resultSet.getInt("foulNum");// 犯规数
						homeScore += resultSet.getInt("score");// 得分
					}
					System.out.println(count);
				}

				Statement sql2 = con.createStatement();
				String query2 = "select visitingTeam,homeTeam from matches where matchID="
						+ matchID;
				ResultSet rs = sql2.executeQuery(query2);
				rs.next();
				visitingTeam = rs.getString("visitingTeam");
				homeTeam = rs.getString("homeTeam");

				rs.close();
				sql2.close();

			}

			sql3.execute("insert MatchTemp values(" + (count++) + "," + tempID
					+ ",'" + season + "','" + visitingTeam + "',"
					+ visitingShootHitNum + "," + visitingShootAttemptNum + ","
					+ visitingThreeHitNum + "," + visitingThreeAttemptNum + ","
					+ visitingFreeThrowHitNum + ","
					+ visitingFreeThrowAttemptNum + ","
					+ visitingOffenReboundNum + "," + visitingDefenReboundNum
					+ "," + visitingAssistNum + "," + visitingStealNum + ","
					+ visitingBlockNum + "," + visitingTurnOverNum + ","
					+ visitingFoulNum + "," + visitingScore + ",'" + homeTeam
					+ "'," + homeShootHitNum + "," + homeShootAttemptNum + ","
					+ homeThreeHitNum + "," + homeThreeAttemptNum + ","
					+ homeFreeThrowHitNum + "," + homeFreeThrowAttemptNum + ","
					+ homeOffenReboundNum + "," + homeDefenReboundNum + ","
					+ homeAssistNum + "," + homeStealNum + "," + homeBlockNum
					+ "," + homeTurnOverNum + "," + homeFoulNum + ","
					+ homeScore + ")");

			sql3.close();
			resultSet.close();
			sql.close();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void createTable() {
		try {
			Statement sql = con.createStatement();
			sql.execute("drop table if exists MatchTemp");
			sql.execute("create table MatchTemp(matchTempID int not null auto_increment,"
					+ "matchID int not null default 0,"
					+ "season varchar(20) not null default 'null',"
					+ "visitingTeam varchar(20) not null default 'null',"
					+ "visitingShootHitNum int not null default 0,"
					+ "visitingShootAttemptNum int not null default 0,"
					+ "visitingThreeHitNum int not null default 0,"
					+ "visitingThreeAttemptNum int not null default 0,"
					+ "visitingFreeThrowHitNum int not null default 0,"
					+ "visitingFreeThrowAttemptNum int not null default 0,"
					+ "visitingOffenReboundNum int not null default 0,"
					+ "visitingDefenReboundNum int not null default 0,"
					+ "visitingAssistNum int not null default 0,"
					+ "visitingStealNum int not null default 0,"
					+ "visitingBlockNum int not null default 0,"
					+ "visitingTurnOverNum int not null default 0,"
					+ "visitingFoulNum int not null default 0,"
					+ "visitingScore int not null default 0,"
					+ "homeTeam varchar(20) not null default 'null',"
					+ "homeShootHitNum int not null default 0,"
					+ "homeShootAttemptNum int not null default 0,"
					+ "homeThreeHitNum int not null default 0,"
					+ "homeThreeAttemptNum int not null default 0,"
					+ "homeFreeThrowHitNum int not null default 0,"
					+ "homeFreeThrowAttemptNum int not null default 0,"
					+ "homeOffenReboundNum int not null default 0,"
					+ "homeDefenReboundNum int not null default 0,"
					+ "homeAssistNum int not null default 0,"
					+ "homeStealNum int not null default 0,"
					+ "homeBlockNum int not null default 0,"
					+ "homeTurnOverNum int not null default 0,"
					+ "homeFoulNum int not null default 0,"
					+ "homeScore int not null default 0," + "primary key(matchTempID));");
			sql.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		MatchTemp matchTemp = new MatchTemp();
		matchTemp.matchTempInit();
	}
}
