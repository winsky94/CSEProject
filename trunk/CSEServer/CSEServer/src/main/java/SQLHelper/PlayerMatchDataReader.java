package SQLHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PlayerMatchDataReader {
	public void exportToSQL() {
		String playerName = null;
		String owingTeam = "";
		int playedGames = 0;
		int gameStartingNum = 0;
		int reboundNum = 0;
		int assistNum = 0;
		String presentTime = "0:0";
		double shootHitRate = 0;
		int threeHitNum = 0;
		int threeAttemptNum = 0;
		double threeHitRate = 0;
		double freeThrowHitRate = 0;
		int offenNum = 0;
		int defenNum = 0;
		int stealNum = 0;
		int blockNum = 0;
		int foulNum = 0;
		int turnOverNum = 0;
		int score = 0;
		double efficiency = 0;
		double GmScEfficiencyValue = 0;
		double trueHitRate = 0;
		double shootEfficiency = 0;
		double reboundRate = 0;
		double offenReboundRate = 0;
		double defenReboundRate = 0;
		double assistRate = 0;
		double stealRate = 0;
		double blockRate = 0;
		double foulRate = 0;
		double usageRate = 0;
		int reboundNumSeason = 0;
		int assistNumSeason = 0;
		String presentTimeSeason = "0:0";
		int shootHitNum = 0;
		int shootAttemptNum = 0;
		double shootHitRateSeason = 0;
		double threeHitRateSeason = 0;
		int freeThrowHitNum = 0;
		int freeThrowAttemptNum = 0;
		double freeThrowHitRateSeason = 0;
		int offenNumSeason = 0;
		int defenNumSeason = 0;
		int stealNumSeason = 0;
		int blockNumSeason = 0;
		int foulNumSeason = 0;
		int turnOverNumSeason = 0;
		int scoreSeason = 0;
		double efficienySeason = 0;
		double GmScEfficiencyValueSeason = 0;
		double trueHitRateSeason = 0;
		double shootEfficiencySeason = 0;
		double reboundRateSeason = 0;
		double offenReboundRateSeason = 0;
		double defenReboundRateSeason = 0;
		double assistRateSeason = 0;
		double stealRateSeason = 0;
		double blockRateSeason = 0;
		double foulRateSeason = 0;
		double usageRateSeason = 0;

		createTable();
		ArrayList<String> names = findPlayerNames();
		try {
			Connection con = SqlManager.getConnection();
			Statement sql0 = con.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = sql0
					.executeQuery("select * from matches order by season desc,date desc");

			for (String name : names) {
				// 得到排好序的该球员参加的比赛的id集合
				Statement sql = con.createStatement();
				String query = "select * from records where playerName=" + name;
				ResultSet resultSet = sql.executeQuery(query);
				ArrayList<Integer> matchIDs = new ArrayList<Integer>();
				while (resultSet.next()) {
					matchIDs.add(resultSet.getInt("matchID"));
					if (!owingTeam.contains(resultSet.getString("team"))) {
						owingTeam = owingTeam + resultSet.getString("team")
								+ "  ";
					}
					playedGames++;
					if (resultSet.getString("position") != null)
						gameStartingNum++;
					reboundNumSeason += resultSet.getInt("reboundNum");
					assistNumSeason += resultSet.getInt("assistNum");
					presentTimeSeason = addPresentTime(presentTime,
							resultSet.getString("presentTime"));
					shootHitNum += resultSet.getInt("shootHitNum");
					shootAttemptNum += resultSet.getInt("shootAttemptNum");
					shootHitRate += (double) resultSet.getInt("shootHitNum")
							/ resultSet.getInt("shootAttemptNum");
					threeHitNum += resultSet.getInt("threeHitNum");
					threeAttemptNum += resultSet.getInt("threeAttemptNum");
					threeHitRate += (double) resultSet.getInt("threeHitNum")
							/ resultSet.getInt("threeAttemptNum");
					freeThrowHitNum += resultSet.getInt("freeThrowHitNum");
					freeThrowAttemptNum += resultSet
							.getInt("freeThrowAttemptNum");
					freeThrowHitRate += (double) resultSet
							.getInt("freeThrowHitNum")
							/ resultSet.getInt("freeThrowAttemptNum");
					// 进攻数不造是啥啊啊啊啊啊啊啊啊啊啊啊啊
					// ，，，，，，，，，，，，，
					// 防守数也不造。。。。。。。
					// ................
					stealNumSeason += resultSet.getInt("stealNum");
					blockNumSeason += resultSet.getInt("blockNum");
					turnOverNumSeason += resultSet.getInt("turnOverNum");
					foulNumSeason += resultSet.getInt("foulNum");
					scoreSeason += resultSet.getInt("score");
					efficiency += (resultSet.getInt("score")
							+ resultSet.getInt("reboundNum")
							+ resultSet.getInt("assistNum")
							+ resultSet.getInt("stealNum") + resultSet
								.getInt("blockNum"))
							- (resultSet.getInt("shootAttemptNum") - resultSet
									.getInt("shootHitNum"))
							- (resultSet.getInt("freeThrowAttemptNum") - resultSet
									.getInt("freeThrowHitNum"))
							- resultSet.getInt("turnOverNum");
					GmScEfficiencyValue += resultSet.getInt("score")
							+ 0.4
							* resultSet.getInt("shootHitNum")
							- 0.7
							* resultSet.getInt("shootAttemptNum")
							- 0.4
							* (resultSet.getInt("freeThrowAttemptNum") - resultSet
									.getInt("freeThrowHitNum")) + 0.7
							* resultSet.getInt("offenReboundNum") + 0.3
							* resultSet.getInt("defenReboundNum")
							+ resultSet.getInt("stealNum") + 0.7
							* resultSet.getInt("assistNum") + 0.7
							* resultSet.getInt("blockNum") - 0.4
							* resultSet.getInt("foulNum")
							- resultSet.getInt("turnOverNum");
					trueHitRate += (double) resultSet.getInt("score")
							/ (2 * (resultSet.getInt("shootAttemptNum") + 0.44 * resultSet
									.getInt("freeThrowAttemptNum")));
					shootEfficiency += (double) (resultSet
							.getInt("shootHitNum") + 0.5 * resultSet
							.getInt("threeHitNum"))
							/ resultSet.getInt("shootAttemptNum");
					reboundRate += resultSet.getInt("reboundNum")
							* getMatchTime(resultSet.getInt("matchID"))
							/ (double) changeTimeToMinute(resultSet
									.getString("presentTime"))
							/ (getReboundNum(resultSet.getString("team"),
									resultSet.getInt("matchID")) + getReboundNum(
									getDSTeamName(resultSet.getString("team"),
											resultSet.getInt("matchID")),
									resultSet.getInt("matchID")));
				}
				ArrayList<Integer> orderMatchIDs = new ArrayList<Integer>();
				while (rs.next()) {
					for (int id : matchIDs) {
						if (rs.getString("matchID").equals(id))
							orderMatchIDs.add(id);
					}
				}

				rs.beforeFirst();
				resultSet.close();
				sql.close();
			}
			rs.close();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	/**
	 * 根据比赛id和当前已知的球队，返回对手球队的名称
	 * 
	 * @param team
	 * @param matchID
	 * @return
	 */
	private String getDSTeamName(String team, int matchID) {
		String result = null;
		try {
			Connection con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			String query = "select homeTeam,visitingTeam from matchtemp where matchID="
					+ matchID;
			ResultSet resultSet = sql.executeQuery(query);
			resultSet.next();
			if (resultSet.getString("homeTeam").equals(team)) {
				result = resultSet.getString("visitingTeam");
			} else {
				result = resultSet.getString("homeTeam");
			}
			resultSet.close();
			sql.close();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据队名和比赛id返回该队伍该场比赛的篮板数
	 * 
	 * @param team
	 * @param matchID
	 * @return
	 */
	private int getReboundNum(String team, int matchID) {
		int result = 0;
		try {
			Connection con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			// 去matchtemp里面找
			String query = "select homeTeam,visitingTeam,homeReboundNum,visitingReboundNum from matchtemp where matchID="
					+ matchID;
			ResultSet resultSet = sql.executeQuery(query);
			resultSet.next();
			if (resultSet.getString("homeTeam").equals(team)) {
				result = resultSet.getInt("homeReboundNum");
			} else {
				result = resultSet.getInt("visitingReboundNum");
			}
			resultSet.close();
			sql.close();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 将球员的上场时间转换为以分钟为单位的时间返回
	 * 
	 * @param presentTime
	 * @return
	 */
	public double changeTimeToMinute(String presentTime) {
		double result = 0;
		String[] temp = presentTime.split(":");
		result = Double.parseDouble(temp[0]) + Double.parseDouble(temp[1])
				/ 60.0;
		return result;
	}

	/**
	 * 根据比赛的id得到该场比赛的总时间
	 * 
	 * @param matchID
	 * @return 该场比赛的总时间
	 */
	public int getMatchTime(int matchID) {
		int result = 0;
		try {
			Connection con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			String query = "select time from matches where matchID=" + matchID;
			ResultSet resultSet = sql.executeQuery(query);
			resultSet.next();
			result = resultSet.getInt("time");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
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

	private ArrayList<String> findPlayerNames() {
		ArrayList<String> result = new ArrayList<String>();
		try {
			Connection con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			String query = "select name from players";
			ResultSet resultSet = sql.executeQuery(query);
			while (resultSet.next()) {
				result.add(resultSet.getString("name"));
			}
			resultSet.close();
			sql.close();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	private void createTable() {
		try {
			Connection con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			sql.execute("drop table if exists MatchTemp");
			String query = "create table PlayerTechnologyData(id int not null auto_increment,"
					+ "playerName varchar(40) not null default 'null',"
					+ "owingTeam varchar(20) not null default 'null',"
					+ "playedGames int not null default 0,"
					+ "gameStartingNum int not null default 0,"
					+ "reboundNum int not null default 0,"
					+ "assistNum int not null default 0,"
					+ "presentTime varchar(20) not null default 'null',"
					+ "shootHitRate double not null default 0,"
					+ "threeHitRate double not null default 0,"
					+ "freeThrowHitRate double not null default 0,"
					+ "offenNum int not null default 0,"
					+ "defenNum int not null default 0,"
					+ "stealNum int not null default 0,"
					+ "blockNum int not null default 0,"
					+ "foulNum int not null default 0,"
					+ "turnOverNum int not null default 0,"
					+ "score int not null default 0,"
					+ "efficiency double not null default 0,"
					+ "GmScEfficiencyValue double not null default 0,"
					+ "trueHitRate double not null default 0,"
					+ "shootEfficiency double not null default 0,"
					+ "reboundRate double not null default 0,"
					+ "offenReboundRate double not null default 0,"
					+ "defenReboundRate double not null default 0,"
					+ "assistRate double not null default 0,"
					+ "stealRate double not null default 0,"
					+ "blockRate double not null default 0,"
					+ "foulRate double not null default 0,"
					+ "usageRate double not null default 0,"
					+ "primary key(id));";
			sql.execute(query);
			sql.close();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
