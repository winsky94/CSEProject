package data;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import po.TeamPO;
import SQLHelper.SqlManager;
import dataservice.TeamDataService;

public class Team extends UnicastRemoteObject implements TeamDataService {
	private static final long serialVersionUID = 1L;

	Connection connection = null;
	Statement sql = null;
	ResultSet resultSet = null;

	public Team() throws RemoteException {
		super();
		// TODO 自动生成的构造函数存根
	}

	public static void main(String[] args) {
		Team team;
		try {
			team = new Team();
			// TeamPO po = team.getTeamSeasonInfo("13-14", "WAS");
			// System.out.println(po.getScore());

			ArrayList<TeamPO> teams = team.getOrderedTeamsBySeason("13-14",
					"score", null);
			for (TeamPO po : teams) {
				System.out.println(po.getScore());
			}
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

	/**
	 * 从数据库中获得球队列表teams
	 * 
	 * @return 球队最基本信息的列表
	 */
	public ArrayList<TeamPO> getTeamBaseInfo() {
		ArrayList<TeamPO> teams = new ArrayList<TeamPO>();
		try {
			connection = SqlManager.getConnection();
			sql = connection.createStatement();
			String query = "select * from teams";
			resultSet = sql.executeQuery(query);
			while (resultSet.next()) {
				int id = resultSet.getInt("teamID");
				String teamName = resultSet.getString("teamName");
				String location = resultSet.getString("location");
				String abLocation = resultSet.getString("abLocation");
				String conference = resultSet.getString("conference");
				String partition = resultSet.getString("partition");
				String homeCourt = resultSet.getString("homeCourt");
				int setUpTime = resultSet.getInt("setUpTime");
				TeamPO team = new TeamPO(id, teamName, abLocation, location,
						conference, partition, homeCourt, setUpTime);
				teams.add(team);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeMySql();
		}
		return teams;
	}

	/**
	 * 得到球队的该赛季的技术统计数据
	 * 
	 * @param season
	 * @return
	 * 
	 */
	public ArrayList<TeamPO> getTeamSeasonInfo(String season) {
		ArrayList<TeamPO> teams = new ArrayList<TeamPO>();
		teams = getTeamInfo("teamMatchDataSeason", season);
		return teams;
	}

	/**
	 * 得到球队的该赛季的技术统计数据
	 * 
	 * @param season
	 * @return
	 * 
	 */
	public ArrayList<TeamPO> getTeamAverageInfo(String season) {
		ArrayList<TeamPO> teams = new ArrayList<TeamPO>();
		teams = getTeamInfo("teamMatchDataAverage", season);
		return teams;
	}

	/**
	 * 查询某球队的基础信息
	 * 
	 * @param name
	 *            球队名称
	 * @return teamPO对象
	 */
	public TeamPO getTeamBaseInfo(String name) {
		TeamPO teamPO = null;
		try {
			connection = SqlManager.getConnection();
			sql = connection.createStatement();
			String query = "select * from teams where teamName='" + name + "'";
			resultSet = sql.executeQuery(query);
			resultSet.next();
			int id = resultSet.getInt("id");
			String teamName = resultSet.getString("teamName");
			String location = resultSet.getString("location");
			String abLocation = resultSet.getString("abLocation");
			String conference = resultSet.getString("conference");
			String partition = resultSet.getString("partition");
			String homeCourt = resultSet.getString("homeCourt");
			int setUpTime = resultSet.getInt("setUpTime");
			teamPO = new TeamPO(id, teamName, abLocation, location, conference,
					partition, homeCourt, setUpTime);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeMySql();
		}
		return teamPO;
	}

	/**
	 * 根据赛季和球队名称返回球队这个赛季的技术数据
	 * 
	 * @param season
	 * @param name
	 * @return
	 */
	public TeamPO getTeamSeasonInfo(String season, String name) {
		TeamPO po = null;
		po = getTeamPO("teamMatchDataSeason", season, name);
		return po;
	}

	/**
	 * 根据赛季和球队名称返回球队这个赛季的场均技术数据
	 * 
	 * @param season
	 * @param name
	 * @return
	 */
	public TeamPO getTeamAverageInfo(String season, String name) {
		TeamPO po = null;
		po = getTeamPO("teamMatchDataAverage", season, name);
		return po;
	}

	/**
	 * 根据某一项技术分析项，将球队按某个赛季的该项数据进行升降序排序
	 * 
	 * @param season
	 *            赛季
	 * @param condition
	 *            排序条件，即某一项技术分析项，如：篮板率
	 * @param order
	 *            升序还是降序 asc 表示升序 , desc表示降序 , 未明确写明排序方式时默认是升序
	 * 
	 * @return 按照所给条件排好序的球队列表
	 */
	public ArrayList<TeamPO> getOrderedTeamsBySeason(String season,
			String condition, String order) throws RemoteException {
		ArrayList<TeamPO> result = new ArrayList<TeamPO>();
		result = getOrderedTeams("teamMatchDataSeason", season, condition,
				order);
		return result;
	}

	/**
	 * 根据某一项技术分析项，将球队按某个赛季的场均该项数据进行升降序排序
	 * 
	 * @param season
	 *            赛季
	 * @param condition
	 *            排序条件，即某一项技术分析项，如：篮板率
	 * @param order
	 *            升序还是降序 asc 表示升序 , desc表示降序 , 未明确写明排序方式时默认是升序
	 * 
	 * @return 按照所给条件排好序的球队列表
	 */
	public ArrayList<TeamPO> getOrderedTeamsByAverage(String season,
			String condition, String order) throws RemoteException {
		ArrayList<TeamPO> result = new ArrayList<TeamPO>();
		result = getOrderedTeams("teamMatchDataAverage", season, condition,
				order);
		return result;
	}

	public String getPhotoPath(String abLocation) {
		return "src/迭代一数据/teams/" + abLocation + ".svg";
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

	/**
	 * 排序抽象方法，用于按赛季或场均的排序
	 * 
	 * @param table
	 *            表名，区分是按赛季还是按场均
	 * @param season
	 *            赛季
	 * @param condition
	 *            排序条件
	 * @param order
	 *            升序还是降序 asc 表示升序 , desc表示降序 , 未明确写明排序方式时默认是升序
	 * @return
	 */
	private ArrayList<TeamPO> getOrderedTeams(String table, String season,
			String condition, String order) {
		ArrayList<TeamPO> result = new ArrayList<TeamPO>();
		// 未明确写明排序方式时默认是升序
		if (order == null || order.equals("null")) {
			order = "asc";
		}
		try {
			connection = SqlManager.getConnection();
			sql = connection.createStatement();
			String query = "select * from " + table + " where season='"
					+ season + "' order by " + condition + " " + order;
			resultSet = sql.executeQuery(query);
			while (resultSet.next()) {
				String name = resultSet.getString("team");
				int matchesNum = resultSet.getInt("matchesNum"); // 比赛场数
				int shootHitNum = resultSet.getInt("shootHitNum"); // 投篮命中数
				int shootAttemptNum = resultSet.getInt("shootAttemptNum"); // 投篮出手次数
				int threeHitNum = resultSet.getInt("threeHitNum"); // 三分命中数
				int threeAttemptNum = resultSet.getInt("threeAttemptNum"); // 三分出手数
				int freeThrowHitNum = resultSet.getInt("freeThrowHitNum"); // 罚球命中数
				int freeThrowAttemptNum = resultSet
						.getInt("freeThrowAttemptNum"); // 罚球出手数
				int offenReboundNum = resultSet.getInt("offenReboundNum"); // 进攻篮板数
				int defenReboundNum = resultSet.getInt("defenReboundNum"); // 防守篮板数
				int reboundNum = resultSet.getInt("reboundNum");// 篮板数
				int assistNum = resultSet.getInt("assistNum");// 助攻数
				int stealNum = resultSet.getInt("stealNum");// 抢断数
				int blockNum = resultSet.getInt("blockNum");// 盖帽数
				int turnOverNum = resultSet.getInt("turnOverNum");// 失误数
				int foulNum = resultSet.getInt("foulNum");// 犯规数
				int score = resultSet.getInt("score");// 比赛得分
				double shootHitRate = resultSet.getDouble("shootHitRate");// 投篮命中率
				double threeHitRate = resultSet.getDouble("threeHitRate");// 三分命中率
				double freeThrowHitRate = resultSet
						.getDouble("freeThrowHitRate");// 罚球命中率
				double winRate = resultSet.getDouble("winRate"); // 胜率
				double offenRound = resultSet.getDouble("offenRound"); // 进攻回合
				double offenEfficiency = resultSet.getDouble("offenEfficiency"); // 进攻效率
				double defenEfficiency = resultSet.getDouble("defenEfficiency"); // 防守效率
				double offenReboundEfficiency = resultSet
						.getDouble("offenReboundEfficiency"); // 进攻篮板效率
				double defenReboundEfficiency = resultSet
						.getDouble("defenReboundEfficiency"); // 防守篮板效率
				double stealEfficiency = resultSet.getDouble("stealEfficiency"); // 抢断效率
				double assistRate = resultSet.getDouble("assistRate"); // 助攻率

				TeamPO po = new TeamPO(name, matchesNum, shootHitNum,
						shootAttemptNum, threeHitNum, threeAttemptNum,
						freeThrowHitNum, freeThrowAttemptNum, offenReboundNum,
						defenReboundNum, reboundNum, assistNum, stealNum,
						blockNum, turnOverNum, foulNum, score, shootHitRate,
						threeHitRate, freeThrowHitRate, winRate, offenRound,
						offenEfficiency, defenEfficiency,
						offenReboundEfficiency, defenReboundEfficiency,
						stealEfficiency, assistRate);
				result.add(po);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeMySql();
		}
		return result;
	}

	/**
	 * 得到球队信息的抽象方法，用于获得球队某个赛季的技术数据
	 * 
	 * @param table
	 *            表名，区分是按赛季还是按场均
	 * @param season
	 *            赛季
	 * @return
	 */
	private ArrayList<TeamPO> getTeamInfo(String table, String season) {
		ArrayList<TeamPO> teams = new ArrayList<TeamPO>();
		teams = getTeamBaseInfo();
		try {
			connection = SqlManager.getConnection();
			sql = connection.createStatement();
			for (TeamPO po : teams) {
				String query = "select * from " + table + " where team='"
						+ po.getAbLocation() + "' and season ='" + season + "'";
				resultSet = sql.executeQuery(query);
				resultSet.next();
				po.setMatchesNum(resultSet.getInt("matchesNum")); // 比赛场数
				po.setShootHitNum(resultSet.getInt("shootHitNum")); // 投篮命中数
				po.setShootAttemptNum(resultSet.getInt("shootAttemptNum")); // 投篮出手次数
				po.setThreeHitNum(resultSet.getInt("threeHitNum")); // 三分命中数
				po.setThreeAttemptNum(resultSet.getInt("threeAttemptNum")); // 三分出手数
				po.setFreeThrowHitNum(resultSet.getInt("freeThrowHitNum")); // 罚球命中数
				po.setFreeThrowAttemptNum(resultSet
						.getInt("freeThrowAttemptNum")); // 罚球出手数
				po.setOffenReboundNum(resultSet.getInt("offenReboundNum")); // 进攻篮板数
				po.setDefenReboundNum(resultSet.getInt("defenReboundNum")); // 防守篮板数
				po.setReboundNum(resultSet.getInt("reboundNum"));// 篮板数
				po.setAssistNum(resultSet.getInt("assistNum"));// 助攻数
				po.setStealNum(resultSet.getInt("stealNum"));// 抢断数
				po.setBlockNum(resultSet.getInt("blockNum"));// 盖帽数
				po.setTurnOverNum(resultSet.getInt("turnOverNum")); // 失误数
				po.setFoulNum(resultSet.getInt("foulNum"));// 犯规数
				po.setScore(resultSet.getInt("score"));// 比赛得分
				po.setShootHitRate(resultSet.getDouble("shootHitRate"));// 投篮命中率
				po.setThreeHitRate(resultSet.getDouble("threeHitRate"));// 三分命中率
				po.setFreeThrowHitRate(resultSet.getDouble("freeThrowHitRate"));// 罚球命中率
				po.setWinRate(resultSet.getDouble("winRate")); // 胜率
				po.setOffenRound(resultSet.getDouble("offenRound")); // 进攻回合
				po.setOffenEfficiency(resultSet.getDouble("offenEfficiency")); // 进攻效率
				po.setDefenEfficiency(resultSet.getDouble("defenEfficiency")); // 防守效率
				po.setOffenReboundEfficiency(resultSet
						.getDouble("offenReboundEfficiency")); // 进攻篮板效率
				po.setDefenReboundEfficiency(resultSet
						.getDouble("defenReboundEfficiency")); // 防守篮板效率
				po.setStealEfficiency(resultSet.getDouble("stealEfficiency")); // 抢断效率
				po.setAssistRate(resultSet.getDouble("assistRate")); // 助攻率

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeMySql();
		}
		return teams;
	}

	/**
	 * 得到某个球队信息的抽象方法，用于获得该球队的某赛季的技术数据
	 * 
	 * @param table
	 *            表名，区分是按赛季还是按场均
	 * @param season
	 *            赛季
	 * @param name
	 *            球队缩写
	 * @return
	 */
	private TeamPO getTeamPO(String table, String season, String name) {
		// TeamPO teamPO = null;
		// ArrayList<TeamPO> teams = new ArrayList<TeamPO>();
		// teams = getTeamSeasonInfo(season);
		// for (TeamPO po : teams) {
		// if (po.getAbLocation().equals(name)
		// || po.getTeamName().equals(name)) {
		// teamPO = po;
		// break;
		// }
		// }
		// return teamPO;

		TeamPO po = null;
		try {
			connection = SqlManager.getConnection();
			sql = connection.createStatement();
			String query = "select * from teams where teamName='" + name
					+ "' or abLocation='" + name + "'";
			resultSet = sql.executeQuery(query);
			resultSet.next();

			int id = resultSet.getInt("id");
			String teamName = resultSet.getString("teamName");
			String location = resultSet.getString("location");
			String abLocation = resultSet.getString("abLocation");
			String conference = resultSet.getString("conference");
			String partition = resultSet.getString("partition");
			String homeCourt = resultSet.getString("homeCourt");
			int setUpTime = resultSet.getInt("setUpTime");
			po = new TeamPO(id, teamName, abLocation, location, conference,
					partition, homeCourt, setUpTime);
			resultSet.close();

			query = "select * from " + table + " where team='" + abLocation
					+ "' and season ='" + season + "'";
			resultSet = sql.executeQuery(query);
			resultSet.next();

			po.setMatchesNum(resultSet.getInt("matchesNum")); // 比赛场数
			po.setShootHitNum(resultSet.getInt("shootHitNum")); // 投篮命中数
			po.setShootAttemptNum(resultSet.getInt("shootAttemptNum")); // 投篮出手次数
			po.setThreeHitNum(resultSet.getInt("threeHitNum")); // 三分命中数
			po.setThreeAttemptNum(resultSet.getInt("threeAttemptNum")); // 三分出手数
			po.setFreeThrowHitNum(resultSet.getInt("freeThrowHitNum")); // 罚球命中数
			po.setFreeThrowAttemptNum(resultSet.getInt("freeThrowAttemptNum")); // 罚球出手数
			po.setOffenReboundNum(resultSet.getInt("offenReboundNum")); // 进攻篮板数
			po.setDefenReboundNum(resultSet.getInt("defenReboundNum")); // 防守篮板数
			po.setReboundNum(resultSet.getInt("reboundNum"));// 篮板数
			po.setAssistNum(resultSet.getInt("assistNum"));// 助攻数
			po.setStealNum(resultSet.getInt("stealNum"));// 抢断数
			po.setBlockNum(resultSet.getInt("blockNum"));// 盖帽数
			po.setTurnOverNum(resultSet.getInt("turnOverNum")); // 失误数
			po.setFoulNum(resultSet.getInt("foulNum"));// 犯规数
			po.setScore(resultSet.getInt("score"));// 比赛得分
			po.setShootHitRate(resultSet.getDouble("shootHitRate"));// 投篮命中率
			po.setThreeHitRate(resultSet.getDouble("threeHitRate"));// 三分命中率
			po.setFreeThrowHitRate(resultSet.getDouble("freeThrowHitRate"));// 罚球命中率
			po.setWinRate(resultSet.getDouble("winRate")); // 胜率
			po.setOffenRound(resultSet.getDouble("offenRound")); // 进攻回合
			po.setOffenEfficiency(resultSet.getDouble("offenEfficiency")); // 进攻效率
			po.setDefenEfficiency(resultSet.getDouble("defenEfficiency")); // 防守效率
			po.setOffenReboundEfficiency(resultSet
					.getDouble("offenReboundEfficiency")); // 进攻篮板效率
			po.setDefenReboundEfficiency(resultSet
					.getDouble("defenReboundEfficiency")); // 防守篮板效率
			po.setStealEfficiency(resultSet.getDouble("stealEfficiency")); // 抢断效率
			po.setAssistRate(resultSet.getDouble("assistRate")); // 助攻率

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeMySql();
		}
		return po;
	}
}
