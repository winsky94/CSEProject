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
import vo.TeamVO;
import SQLHelper.SqlManager;
import dataservice.TeamDataService;

/**
 * 从文件中读取数据，用于将球队的基本数据存储到数据库中 表名：teams
 */
public class TeamData implements TeamDataService {
	Connection connection = null;
	Statement sql = null;
	ResultSet resultSet = null;

	public static void main(String[] args) {
		TeamData teamDataReader = new TeamData();
		teamDataReader.exportToSql();
	}

	public ArrayList<String[]> readFromFile(String fileName) {

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

	public ArrayList<TeamVO> getTeamListFromFile() {
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

	public void exportToSql() {
		ArrayList<TeamVO> teams = getTeamListFromFile();
		try {
			Connection con = SqlManager.getConnection();
			con.setAutoCommit(false);
			Statement sql = con.createStatement();
			sql.execute("drop table if exists teams");
			sql.execute("create table teams(teamID int not null auto_increment,teamName varchar(40) not null default 'null',"
					+ "abLocation varchar(20) not null default 'null',location varchar(20) not null default 'null',"
					+ "conference varchar(20) not null default 'null',partition varchar(20) not null default 'null',"
					+ "homeCourt varchar(40) not null default 'null',setUpTime int not null default 0,primary key(teamID));");
			int count = 1;
			sql.close();
			PreparedStatement statement = con
					.prepareStatement("INSERT INTO teams VALUES(?, ?,?,?,?,?,?,?)");
			for (TeamVO team : teams) {
				statement.setInt(1, count++);
				statement.setString(2, team.getTeamName());
				statement.setString(3, team.getAbLocation());
				statement.setString(4, team.getLocation());
				statement.setString(5, team.getConference());
				statement.setString(6, team.getPartition());
				statement.setString(7, team.getHomeCourt());
				statement.setInt(8, team.getSetUpTime());
				statement.addBatch();
				/*
				 * sql.execute("insert teams values(" + (count++) + ",'" +
				 * team.getTeamName() + "','" + team.getAbLocation() + "','" +
				 * team.getLocation() + "','" + team.getConference() + "','" +
				 * team.getPartition() + "','" + team.getHomeCourt() + "'," +
				 * team.getSetUpTime() + ")");
				 */

				System.out.println(count);
			}
			statement.executeBatch();
			con.commit();
			statement.close();
			con.close();
		} catch (java.lang.ClassNotFoundException e) {
			System.err.println("ClassNotFoundException:" + e.getMessage());
		} catch (SQLException ex) {
			System.err.println("SQLException:" + ex.getMessage());
		}
	}

	public ArrayList<TeamVO> getTeamBaseInfo() {
		// TODO 自动生成的方法存根
		ArrayList<TeamVO> teams = new ArrayList<TeamVO>();
		try {
			connection = SqlManager.getConnection();
			sql = connection.createStatement();
			String query = "select * from teams";
			resultSet = sql.executeQuery(query);
			while (resultSet.next()) {
				// int id = resultSet.getInt("teamID");
				String teamName = resultSet.getString("teamName");
				String location = resultSet.getString("location");
				String abLocation = resultSet.getString("abLocation");
				String conference = resultSet.getString("conference");
				String partition = resultSet.getString("partition");
				String homeCourt = resultSet.getString("homeCourt");
				int setUpTime = resultSet.getInt("setUpTime");
				TeamVO team = new TeamVO(teamName, abLocation, location,
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

	public ArrayList<MatchVO> getRecentMatches(String teamName) {
		// TODO 自动生成的方法存根
		return getRencentMatches(teamName, 5);
	}

	public ArrayList<MatchVO> getMatches(String teamName) {
		return getRencentMatches(teamName, -1);
	}

	public ArrayList<TeamVO> getTeamBaseInfo(String name) {
		// TODO 自动生成的方法存根
		ArrayList<TeamVO> teams = new ArrayList<TeamVO>();
		try {
			connection = SqlManager.getConnection();
			sql = connection.createStatement();
			String query = "select * from teams where abLocation like '%"
					+ name + "%'or teamName like '%" + name + "%'";
			resultSet = sql.executeQuery(query);
			resultSet.next();
			String teamName = resultSet.getString("teamName");
			String location = resultSet.getString("location");
			String abLocation = resultSet.getString("abLocation");
			String conference = resultSet.getString("conference");
			String partition = resultSet.getString("partition");
			String homeCourt = resultSet.getString("homeCourt");
			int setUpTime = resultSet.getInt("setUpTime");
			TeamVO teamVO = new TeamVO(teamName, abLocation, location,
					conference, partition, homeCourt, setUpTime);
			teams.add(teamVO);

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

	public ArrayList<String> getPlayersByTeam(String teamAbLocation) {
		// TODO 自动生成的方法存根
		ArrayList<String> result = new ArrayList<String>();
		try {
			connection = SqlManager.getConnection();
			Statement sql = connection.createStatement();
			String query = "select playerName from playerMatchDataSeason where owingTeam like '%"
					+ teamAbLocation + "%'";
			ResultSet rs = sql.executeQuery(query);
			while (rs.next()) {
				result.add(rs.getString("playerName"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeMySql();
		}
		return result;
	}

	/**
	 * 根据球队名得到近期比赛的某种数据 模糊查找（觉得好像意义不大）
	 * 
	 * @param teamName
	 * @param num
	 *            得到比赛的场数 ，-1则是取出全部数据
	 * @return
	 */
	private ArrayList<MatchVO> getRencentMatches(String teamName, int num) {
		ArrayList<MatchVO> result = new ArrayList<MatchVO>();
		try {
			connection = SqlManager.getConnection();
			Statement sql = connection.createStatement();
			// 模糊查找
			String query = "select * from matches where homeTeam like '%"
					+ teamName + "%' or visitingTeam like '%" + teamName
					+ "%' order by date desc";
			ResultSet resultSet = sql.executeQuery(query);
			int count = 0;
			while (resultSet.next()) {
				count++;

				int matchID = resultSet.getInt("matchID");
				String season = resultSet.getString("season");
				String date = resultSet.getString("date");
				String type = resultSet.getString("type");
				String visingTeam = resultSet.getString("visitingTeam");
				String homeTeam = resultSet.getString("homeTeam");
				int visitingScore = resultSet.getInt("visitingScore");
				int homeScore = resultSet.getInt("homeScore");

				ArrayList<String> detailScores = new ArrayList<String>();
				Statement statement = connection.createStatement();
				String query2 = "select score from detailscores where matchID="
						+ matchID;
				ResultSet rs = statement.executeQuery(query2);
				while (rs.next()) {
					detailScores.add(rs.getString("score"));
				}
				statement.close();
				rs.close();

				ArrayList<RecordVO> records = new ArrayList<RecordVO>();
				Statement stat = connection.createStatement();
				String query3 = "select * from records where matchID="
						+ matchID;
				ResultSet rSet = stat.executeQuery(query3);
				while (rSet.next()) {
					String team = rSet.getString("team");
					String playerName = rSet.getString("playerName");
					String position = rSet.getString("position");
					String presentTime = rSet.getString("presentTime");
					int shootHitNum = rSet.getInt("shootHitNum");
					int shootAttemptNum = rSet.getInt("shootAttemptNum");
					double shootHitRate = rSet.getDouble("shootHitRate");
					int threeHitNum = rSet.getInt("threeHitNum");
					int threeAttemptNum = rSet.getInt("threeAttemptNum");
					double threeHitRate = rSet.getDouble("threeHitRate");
					int freeThrowHitNum = rSet.getInt("freeThrowHitNum");
					int freeThrowAttemptNum = rSet
							.getInt("freeThrowAttemptNum");
					double freeThrowHitRate = rSet
							.getDouble("freeThrowHitRate");
					int offenReboundNum = rSet.getInt("offenReboundNum");
					int defenReboundNum = rSet.getInt("defenReboundNum");
					int reboundNum = rSet.getInt("reboundNum");
					int assistNum = rSet.getInt("assistNum");
					int stealNum = rSet.getInt("stealNum");
					int blockNum = rSet.getInt("blockNum");
					int turnOverNum = rSet.getInt("turnOverNum");
					int foulNum = rSet.getInt("foulNum");
					int score = rSet.getInt("score");
					RecordVO recordPO = new RecordVO(team, playerName,
							position, presentTime, shootHitNum,
							shootAttemptNum, shootHitRate, threeHitNum,
							threeAttemptNum, threeHitRate, freeThrowHitNum,
							freeThrowAttemptNum, freeThrowHitRate,
							offenReboundNum, defenReboundNum, reboundNum,
							assistNum, stealNum, blockNum, turnOverNum,
							foulNum, score);
					records.add(recordPO);
				}
				rSet.close();
				stat.close();

				MatchVO matchVO = new MatchVO(matchID, season, date, type,
						visingTeam, homeTeam, visitingScore, homeScore,
						detailScores, records);
				result.add(matchVO);
				if (num > 0) {// -1则是取出全部数据
					if (count >= num) {
						break;
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeMySql();
		}
		return result;
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
