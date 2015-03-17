package data;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import SQLHelper.SqlManager;
import po.TeamPO;
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
			// ArrayList<TeamPO> teams = team.getTeamBaseInfo();
			// for (TeamPO teamPO : teams) {
			// System.out.println(teamPO.getTeamName());
			// }
			TeamPO po = team.getTeamBaseInfo("Wizards");
			System.out.println(po.getAbLocation());
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
				int id = resultSet.getInt("id");
				String teamName = resultSet.getString("teamName");
				String location = resultSet.getString("location");
				String abLocation = resultSet.getString("abLocation");
				String conference = resultSet.getString("conference");
				String partition = resultSet.getString("partition");
				String homeCourt = resultSet.getString("homeCourt");
				int setUpTime = resultSet.getInt("setUpTime");
				TeamPO team = new TeamPO(id, teamName, location, abLocation,
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
	 * 得到球队的所有赛季的技术统计数据
	 * 
	 * @return
	 */
	public ArrayList<TeamPO> getTeamSeasonInfo() {
		ArrayList<TeamPO> teams = new ArrayList<TeamPO>();
		teams = getTeamBaseInfo();
		try {
			connection = SqlManager.getConnection();
			sql = connection.createStatement();
			for (TeamPO po : teams) {
				String query = "select * from teammatchdataseason where team='"
						+ po.getAbLocation() + "'";
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

}
