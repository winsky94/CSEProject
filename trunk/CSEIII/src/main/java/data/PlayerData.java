package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import vo.MatchVO;
import vo.PlayerVO;
import vo.RecordVO;
import vo.TeamVO;
import SQLHelper.SqlManager;
import dataservice.MatchDataService;
import dataservice.PlayerDataService;

/**
 * 
 * 从文件中读取数据，用于将球员基本数据存储到数据库中 表名：players
 * 
 *
 */
public class PlayerData  implements PlayerDataService{

	boolean isReadSqlActive = false;
	boolean isReadSqlHistoric = false;
	boolean isGetPlayerToday=false;
	ArrayList<PlayerVO> playersActive = new ArrayList<PlayerVO>();
	ArrayList<PlayerVO> playersHistoric = new ArrayList<PlayerVO>();
	ArrayList<PlayerVO> playersToday=new ArrayList<PlayerVO>();
	Map<String, TeamVO> teams;
	Map<Integer, MatchVO> matches = new HashMap<Integer, MatchVO>(1024);

	public ArrayList<PlayerVO> getPlayerActiveBaseInfo() {

		if (isReadSqlActive == true)
			return playersActive;

		Connection con;
		try {
			con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			String query = "select * from playersactive";
			ResultSet resultSet = sql.executeQuery(query);
			PlayerVO player;

			while (resultSet.next()) {
				String name;
				String number;
				String position;
				String height;
				int weight;
				String birth;
				int age;
				int exp;
				String school;
                String owingTeam;
				
				name = resultSet.getString("name");
				number = resultSet.getString("number");
				position = resultSet.getString("position");
				height = resultSet.getString("height");
				weight = resultSet.getInt("weight");
				birth = resultSet.getString("birth");
				age = resultSet.getInt("age");
				exp = resultSet.getInt("exp");
				school = resultSet.getString("school");
				owingTeam=resultSet.getString("owingTeam");

				player = new PlayerVO(name, number, position, height, weight,
						birth, age, exp, school,owingTeam);
				playersActive.add(player);
			}
			resultSet.close();
			sql.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		isReadSqlActive = true;
		return playersActive;
	}

	public ArrayList<PlayerVO> getPlayerHistoricBaseInfo() {

		if (isReadSqlHistoric == true)
			return playersHistoric;

		Connection con;
		try {
			con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			String query = "select * from playershistoric";
			ResultSet resultSet = sql.executeQuery(query);
			PlayerVO player;

			while (resultSet.next()) {
				String name;
				String number;
				String position;
				String height;
				int weight;
				String birth;
				int age;
				int exp;
				String school;
				String owingTeam;

				name = resultSet.getString("name");
				number = resultSet.getString("number");
				position = resultSet.getString("position");
				height = resultSet.getString("height");
				weight = resultSet.getInt("weight");
				birth = resultSet.getString("birth");
				age = resultSet.getInt("age");
				exp = resultSet.getInt("exp");
				school = resultSet.getString("school");
				owingTeam=resultSet.getString("owingTeam");

				player = new PlayerVO(name, number, position, height, weight,
						birth, age, exp, school,owingTeam);
				playersHistoric.add(player);
			}
			resultSet.close();
			sql.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		isReadSqlHistoric = true;
		return playersHistoric;
	}

	public PlayerVO getPlayerBaseInfo(String name) {

		PlayerVO player = new PlayerVO();
		Connection con;
		try {
			con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			if (name.contains("'"))
				name.replace("'", "''");
			String query = "select * from playershistoric where name='" + name
					+ "' limit 1";
			ResultSet resultSet = sql.executeQuery(query);

			resultSet.next();
			String thename;
			String number;
			String position;
			String height;
			int weight;
			String birth;
			int age;
			int exp;
			String school;
			String owingTeam;

			thename = resultSet.getString("name");
			number = resultSet.getString("number");
			position = resultSet.getString("position");
			height = resultSet.getString("height");
			weight = resultSet.getInt("weight");
			birth = resultSet.getString("birth");
			age = resultSet.getInt("age");
			exp = resultSet.getInt("exp");
			school = resultSet.getString("school");
			owingTeam=resultSet.getString("owingTeam");

			player = new PlayerVO(thename, number, position, height, weight,
					birth, age, exp, school,owingTeam);
			resultSet.close();
			sql.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return player;
	}

	public ArrayList<PlayerVO> getPlayerBaseInfoForVague(String name) {

		ArrayList<PlayerVO> pArrayList = new ArrayList<PlayerVO>();
		Connection con;
		try {
			con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			if (name.contains("'"))
				name.replace("'", "''");
			String query = "select * from playershistoric where name like'%"
					+ name + "%'";
			ResultSet resultSet = sql.executeQuery(query);
			PlayerVO player;
			while (resultSet.next()) {
				String thename;
				String number;
				String position;
				String height;
				int weight;
				String birth;
				int age;
				int exp;
				String school;
				String owingTeam;

				thename = resultSet.getString("name");
				number = resultSet.getString("number");
				position = resultSet.getString("position");
				height = resultSet.getString("height");
				weight = resultSet.getInt("weight");
				birth = resultSet.getString("birth");
				age = resultSet.getInt("age");
				exp = resultSet.getInt("exp");
				school = resultSet.getString("school");
				owingTeam=resultSet.getString("owingTeam");

				player = new PlayerVO(thename, number, position, height,
						weight, birth, age, exp, school,owingTeam);
				pArrayList.add(player);
			}
			resultSet.close();
			sql.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pArrayList;
	}
	
	public ArrayList<PlayerVO> getPlayerActiveBaseInfoForVague(String name) {

		ArrayList<PlayerVO> pArrayList = new ArrayList<PlayerVO>();
		Connection con;
		try {
			con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			if (name.contains("'"))
				name.replace("'", "''");
			String query = "select * from playersactive where name like'%"
					+ name + "%'";
			ResultSet resultSet = sql.executeQuery(query);
			PlayerVO player;
			while (resultSet.next()) {
				String thename;
				String number;
				String position;
				String height;
				int weight;
				String birth;
				int age;
				int exp;
				String school;
				String owingTeam;

				thename = resultSet.getString("name");
				number = resultSet.getString("number");
				position = resultSet.getString("position");
				height = resultSet.getString("height");
				weight = resultSet.getInt("weight");
				birth = resultSet.getString("birth");
				age = resultSet.getInt("age");
				exp = resultSet.getInt("exp");
				school = resultSet.getString("school");
				owingTeam=resultSet.getString("owingTeam");

				player = new PlayerVO(thename, number, position, height,
						weight, birth, age, exp, school,owingTeam);
				pArrayList.add(player);
			}
			resultSet.close();
			sql.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pArrayList;
	}
		    
		
		public ArrayList<PlayerVO> getPlayerSeasonInfo(String season,String type){
	     
			ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
			PlayerVO player;
			String seasonbuffer=season.replace("-", "");
			try {
				Connection con = SqlManager.getConnection();
				Statement sql = con.createStatement();
				String query = "select * from "+seasonbuffer+"_"+type+"_"+"playerSeason";
				ResultSet rs = sql.executeQuery(query);
				while (rs.next()) {
					player = new PlayerVO();
					player.setName(rs.getString("name"));
					player.setOwingTeam(rs.getString("owingTeam"));
					player.setLeague(rs.getString("league"));
					player.setPlayedGames(rs.getInt("playedGames"));
					player.setGameStartingNum(rs.getInt("gameStartingNum"));
					player.setReboundNum(rs.getDouble("reboundNum"));
					player.setAssistNum(rs.getDouble("assistNum"));
					player.setPresentTime(rs.getDouble("presentTime"));
					player.setShootHitRate(rs.getDouble("shootHitRate"));
					player.setThreeHitRate(rs.getDouble("threeHitRate"));
					player.setFreeThrowHitRate(rs.getDouble("freeThrowHitRate"));
					player.setOffenReboundNum(rs.getDouble("offenReboundNum"));
					player.setDefenReboundNum(rs.getDouble("defenReboundNum"));
					player.setStealNum(rs.getDouble("stealNum"));
					player.setBlockNum(rs.getDouble("blockNum"));
					player.setFoulNum(rs.getDouble("foulNum"));
					player.setTurnOverNum(rs.getDouble("turnOverNum"));
					player.setScore(rs.getDouble("score"));
					player.setEfficiency(rs.getDouble("efficiency"));
					player.setRecentFiveMatchesScoreUpRate(rs
							.getDouble("recentFiveMatchesScoreUpRate"));
					player.setRecentFiveMatchesReboundUpRate(rs
							.getDouble("recentFiveMatchesReboundUpRate"));
					player.setRecentFiveMatchesAssistUpRate(rs
							.getDouble("recentFiveMatchesAssistUpRate"));
					player.setGmScEfficiencyValue(rs
							.getDouble("GmScEfficiencyValue"));
					player.setTrueHitRate(rs.getDouble("trueHitRate"));
					player.setShootEfficiency(rs.getDouble("shootEfficiency"));
					player.setReboundRate(rs.getDouble("reboundRate"));
					player.setOffenReboundRate(rs.getDouble("offenReboundRate"));
					player.setDefenReboundRate(rs.getDouble("defenReboundRate"));
					player.setAssistRate(rs.getDouble("assistRate"));
					player.setStealRate(rs.getDouble("stealRate"));
					player.setBlockRate(rs.getDouble("blockRate"));
					player.setTurnOverRate(rs.getDouble("turnOverRate"));
					player.setUsageRate(rs.getDouble("usageRate"));
					player.setScore_rebound_assist(rs
							.getDouble("score_rebound_assist"));
					player.setDoubleDoubleNum(rs.getDouble("doubleDoubleNum"));
					result.add(player);
				}

				rs.close();
				con.close();
				sql.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			return result;
		}
		
		public ArrayList<PlayerVO> getPlayerSeasonInfo(String season,String type,String name){
			ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
			PlayerVO player;
			String seasonbuffer=season.replace("-", "");
			try {
				Connection con = SqlManager.getConnection();
				Statement sql = con.createStatement();
				String query = "select * from "+seasonbuffer+"_"+type+"_"+"playerSeason where name "
						+ "like'%"+ name + "%'";
				ResultSet rs = sql.executeQuery(query);
				while (rs.next()) {
					player = new PlayerVO();
					player.setName(rs.getString("name"));
					player.setOwingTeam(rs.getString("owingTeam"));
					player.setLeague(rs.getString("league"));
					player.setPlayedGames(rs.getInt("playedGames"));
					player.setGameStartingNum(rs.getInt("gameStartingNum"));
					player.setReboundNum(rs.getDouble("reboundNum"));
					player.setAssistNum(rs.getDouble("assistNum"));
					player.setPresentTime(rs.getDouble("presentTime"));
					player.setShootHitRate(rs.getDouble("shootHitRate"));
					player.setThreeHitRate(rs.getDouble("threeHitRate"));
					player.setFreeThrowHitRate(rs.getDouble("freeThrowHitRate"));
					player.setOffenReboundNum(rs.getDouble("offenReboundNum"));
					player.setDefenReboundNum(rs.getDouble("defenReboundNum"));
					player.setStealNum(rs.getDouble("stealNum"));
					player.setBlockNum(rs.getDouble("blockNum"));
					player.setFoulNum(rs.getDouble("foulNum"));
					player.setTurnOverNum(rs.getDouble("turnOverNum"));
					player.setScore(rs.getDouble("score"));
					player.setEfficiency(rs.getDouble("efficiency"));
					player.setRecentFiveMatchesScoreUpRate(rs
							.getDouble("recentFiveMatchesScoreUpRate"));
					player.setRecentFiveMatchesReboundUpRate(rs
							.getDouble("recentFiveMatchesReboundUpRate"));
					player.setRecentFiveMatchesAssistUpRate(rs
							.getDouble("recentFiveMatchesAssistUpRate"));
					player.setGmScEfficiencyValue(rs
							.getDouble("GmScEfficiencyValue"));
					player.setTrueHitRate(rs.getDouble("trueHitRate"));
					player.setShootEfficiency(rs.getDouble("shootEfficiency"));
					player.setReboundRate(rs.getDouble("reboundRate"));
					player.setOffenReboundRate(rs.getDouble("offenReboundRate"));
					player.setDefenReboundRate(rs.getDouble("defenReboundRate"));
					player.setAssistRate(rs.getDouble("assistRate"));
					player.setStealRate(rs.getDouble("stealRate"));
					player.setBlockRate(rs.getDouble("blockRate"));
					player.setTurnOverRate(rs.getDouble("turnOverRate"));
					player.setUsageRate(rs.getDouble("usageRate"));
					player.setScore_rebound_assist(rs
							.getDouble("score_rebound_assist"));
					player.setDoubleDoubleNum(rs.getDouble("doubleDoubleNum"));
					result.add(player);
				}

				rs.close();
				con.close();
				sql.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			return result;
		}


		public ArrayList<PlayerVO> getPlayerAverageInfo(String season,String type) {

			
			ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
			PlayerVO player;
			String seasonbuffer=season.replace("-", "");
			try {
				Connection con = SqlManager.getConnection();
				Statement sql = con.createStatement();
				String query = "select * from "+seasonbuffer+"_"+type+"_"+"playerAverage";
				ResultSet rs = sql.executeQuery(query);
				while (rs.next()) {
					player = new PlayerVO();
					player.setName(rs.getString("name"));
					player.setOwingTeam(rs.getString("owingTeam"));
					player.setLeague(rs.getString("league"));
					player.setPlayedGames(rs.getInt("playedGames"));
					player.setGameStartingNum(rs.getInt("gameStartingNum"));
					player.setReboundNum(rs.getDouble("reboundNum"));
					player.setAssistNum(rs.getDouble("assistNum"));
					player.setPresentTime(rs.getDouble("presentTime"));
					player.setShootHitRate(rs.getDouble("shootHitRate"));
					player.setThreeHitRate(rs.getDouble("threeHitRate"));
					player.setFreeThrowHitRate(rs.getDouble("freeThrowHitRate"));
					player.setOffenReboundNum(rs.getDouble("offenReboundNum"));
					player.setDefenReboundNum(rs.getDouble("defenReboundNum"));
					player.setStealNum(rs.getDouble("stealNum"));
					player.setBlockNum(rs.getDouble("blockNum"));
					player.setFoulNum(rs.getDouble("foulNum"));
					player.setTurnOverNum(rs.getDouble("turnOverNum"));
					player.setScore(rs.getDouble("score"));
					player.setEfficiency(rs.getDouble("efficiency"));
					player.setRecentFiveMatchesScoreUpRate(rs
							.getDouble("recentFiveMatchesScoreUpRate"));
					player.setRecentFiveMatchesReboundUpRate(rs
							.getDouble("recentFiveMatchesReboundUpRate"));
					player.setRecentFiveMatchesAssistUpRate(rs
							.getDouble("recentFiveMatchesAssistUpRate"));
					player.setGmScEfficiencyValue(rs
							.getDouble("GmScEfficiencyValue"));
					player.setTrueHitRate(rs.getDouble("trueHitRate"));
					player.setShootEfficiency(rs.getDouble("shootEfficiency"));
					player.setReboundRate(rs.getDouble("reboundRate"));
					player.setOffenReboundRate(rs.getDouble("offenReboundRate"));
					player.setDefenReboundRate(rs.getDouble("defenReboundRate"));
					player.setAssistRate(rs.getDouble("assistRate"));
					player.setStealRate(rs.getDouble("stealRate"));
					player.setBlockRate(rs.getDouble("blockRate"));
					player.setTurnOverRate(rs.getDouble("turnOverRate"));
					player.setUsageRate(rs.getDouble("usageRate"));
					player.setScore_rebound_assist(rs
							.getDouble("score_rebound_assist"));
					player.setDoubleDoubleNum(rs.getDouble("doubleDoubleNum"));
					result.add(player);
				}

				rs.close();
				con.close();
				sql.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			return result;
		}
		
		public ArrayList<PlayerVO> getPlayerAverageInfo(String season,String type,String name){
			ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
		PlayerVO player;
		String seasonbuffer=season.replace("-", "");
		try {
			Connection con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			String query = "select * from "+seasonbuffer+"_"+type+"_"+"playerAverage where name "
					+ "like'%"+ name + "%'";
			ResultSet rs = sql.executeQuery(query);
			while (rs.next()) {
				player = new PlayerVO();
				player.setName(rs.getString("name"));
				player.setOwingTeam(rs.getString("owingTeam"));
				player.setLeague(rs.getString("league"));
				player.setPlayedGames(rs.getInt("playedGames"));
				player.setGameStartingNum(rs.getInt("gameStartingNum"));
				player.setReboundNum(rs.getDouble("reboundNum"));
				player.setAssistNum(rs.getDouble("assistNum"));
				player.setPresentTime(rs.getDouble("presentTime"));
				player.setShootHitRate(rs.getDouble("shootHitRate"));
				player.setThreeHitRate(rs.getDouble("threeHitRate"));
				player.setFreeThrowHitRate(rs.getDouble("freeThrowHitRate"));
				player.setOffenReboundNum(rs.getDouble("offenReboundNum"));
				player.setDefenReboundNum(rs.getDouble("defenReboundNum"));
				player.setStealNum(rs.getDouble("stealNum"));
				player.setBlockNum(rs.getDouble("blockNum"));
				player.setFoulNum(rs.getDouble("foulNum"));
				player.setTurnOverNum(rs.getDouble("turnOverNum"));
				player.setScore(rs.getDouble("score"));
				player.setEfficiency(rs.getDouble("efficiency"));
				player.setRecentFiveMatchesScoreUpRate(rs
						.getDouble("recentFiveMatchesScoreUpRate"));
				player.setRecentFiveMatchesReboundUpRate(rs
						.getDouble("recentFiveMatchesReboundUpRate"));
				player.setRecentFiveMatchesAssistUpRate(rs
						.getDouble("recentFiveMatchesAssistUpRate"));
				player.setGmScEfficiencyValue(rs
						.getDouble("GmScEfficiencyValue"));
				player.setTrueHitRate(rs.getDouble("trueHitRate"));
				player.setShootEfficiency(rs.getDouble("shootEfficiency"));
				player.setReboundRate(rs.getDouble("reboundRate"));
				player.setOffenReboundRate(rs.getDouble("offenReboundRate"));
				player.setDefenReboundRate(rs.getDouble("defenReboundRate"));
				player.setAssistRate(rs.getDouble("assistRate"));
				player.setStealRate(rs.getDouble("stealRate"));
				player.setBlockRate(rs.getDouble("blockRate"));
				player.setTurnOverRate(rs.getDouble("turnOverRate"));
				player.setUsageRate(rs.getDouble("usageRate"));
				player.setScore_rebound_assist(rs
						.getDouble("score_rebound_assist"));
				player.setDoubleDoubleNum(rs.getDouble("doubleDoubleNum"));
				result.add(player);
			}

			rs.close();
			con.close();
			sql.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
		}

		public ArrayList<PlayerVO> getOrderedPlayersBySeason(String season,String type,
				String condition, String order, int num) {
			ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
			PlayerVO player;
			String seasonbuffer=season.replace("-", "");
			try {
				Connection con = SqlManager.getConnection();
				Statement sql = con.createStatement();
				String query = "select * from "+seasonbuffer+"_"+type+"_"+"playerSeason order by "+condition+" "+order+" limit "+num;
				ResultSet rs = sql.executeQuery(query);
				while (rs.next()) {
					player = new PlayerVO();
					player.setName(rs.getString("name"));
					player.setOwingTeam(rs.getString("owingTeam"));
					player.setLeague(rs.getString("league"));
					player.setPlayedGames(rs.getInt("playedGames"));
					player.setGameStartingNum(rs.getInt("gameStartingNum"));
					player.setReboundNum(rs.getDouble("reboundNum"));
					player.setAssistNum(rs.getDouble("assistNum"));
					player.setPresentTime(rs.getDouble("presentTime"));
					player.setShootHitRate(rs.getDouble("shootHitRate"));
					player.setThreeHitRate(rs.getDouble("threeHitRate"));
					player.setFreeThrowHitRate(rs.getDouble("freeThrowHitRate"));
					player.setOffenReboundNum(rs.getDouble("offenReboundNum"));
					player.setDefenReboundNum(rs.getDouble("defenReboundNum"));
					player.setStealNum(rs.getDouble("stealNum"));
					player.setBlockNum(rs.getDouble("blockNum"));
					player.setFoulNum(rs.getDouble("foulNum"));
					player.setTurnOverNum(rs.getDouble("turnOverNum"));
					player.setScore(rs.getDouble("score"));
					player.setEfficiency(rs.getDouble("efficiency"));
					player.setRecentFiveMatchesScoreUpRate(rs
							.getDouble("recentFiveMatchesScoreUpRate"));
					player.setRecentFiveMatchesReboundUpRate(rs
							.getDouble("recentFiveMatchesReboundUpRate"));
					player.setRecentFiveMatchesAssistUpRate(rs
							.getDouble("recentFiveMatchesAssistUpRate"));
					player.setGmScEfficiencyValue(rs
							.getDouble("GmScEfficiencyValue"));
					player.setTrueHitRate(rs.getDouble("trueHitRate"));
					player.setShootEfficiency(rs.getDouble("shootEfficiency"));
					player.setReboundRate(rs.getDouble("reboundRate"));
					player.setOffenReboundRate(rs.getDouble("offenReboundRate"));
					player.setDefenReboundRate(rs.getDouble("defenReboundRate"));
					player.setAssistRate(rs.getDouble("assistRate"));
					player.setStealRate(rs.getDouble("stealRate"));
					player.setBlockRate(rs.getDouble("blockRate"));
					player.setTurnOverRate(rs.getDouble("turnOverRate"));
					player.setUsageRate(rs.getDouble("usageRate"));
					player.setScore_rebound_assist(rs
							.getDouble("score_rebound_assist"));
					player.setDoubleDoubleNum(rs.getDouble("doubleDoubleNum"));
					result.add(player);
				}

				rs.close();
				con.close();
				sql.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			return result; 
		}

		public ArrayList<PlayerVO> getOrderedPlayersByAverage(String season,String type,String condition,
				String order, int num) {
			ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
			PlayerVO player;
			String seasonbuffer=season.replace("-", "");
			try {
				Connection con = SqlManager.getConnection();
				Statement sql = con.createStatement();
				String query = "select * from "+seasonbuffer+"_"+type+"_"+"playerAverage order by "+condition+" "+order+" limit "+num;
				ResultSet rs = sql.executeQuery(query);
				while (rs.next()) {
					player = new PlayerVO();
					player.setName(rs.getString("name"));
					player.setOwingTeam(rs.getString("owingTeam"));
					player.setLeague(rs.getString("league"));
					player.setPlayedGames(rs.getInt("playedGames"));
					player.setGameStartingNum(rs.getInt("gameStartingNum"));
					player.setReboundNum(rs.getDouble("reboundNum"));
					player.setAssistNum(rs.getDouble("assistNum"));
					player.setPresentTime(rs.getDouble("presentTime"));
					player.setShootHitRate(rs.getDouble("shootHitRate"));
					player.setThreeHitRate(rs.getDouble("threeHitRate"));
					player.setFreeThrowHitRate(rs.getDouble("freeThrowHitRate"));
					player.setOffenReboundNum(rs.getDouble("offenReboundNum"));
					player.setDefenReboundNum(rs.getDouble("defenReboundNum"));
					player.setStealNum(rs.getDouble("stealNum"));
					player.setBlockNum(rs.getDouble("blockNum"));
					player.setFoulNum(rs.getDouble("foulNum"));
					player.setTurnOverNum(rs.getDouble("turnOverNum"));
					player.setScore(rs.getDouble("score"));
					player.setEfficiency(rs.getDouble("efficiency"));
					player.setRecentFiveMatchesScoreUpRate(rs
							.getDouble("recentFiveMatchesScoreUpRate"));
					player.setRecentFiveMatchesReboundUpRate(rs
							.getDouble("recentFiveMatchesReboundUpRate"));
					player.setRecentFiveMatchesAssistUpRate(rs
							.getDouble("recentFiveMatchesAssistUpRate"));
					player.setGmScEfficiencyValue(rs
							.getDouble("GmScEfficiencyValue"));
					player.setTrueHitRate(rs.getDouble("trueHitRate"));
					player.setShootEfficiency(rs.getDouble("shootEfficiency"));
					player.setReboundRate(rs.getDouble("reboundRate"));
					player.setOffenReboundRate(rs.getDouble("offenReboundRate"));
					player.setDefenReboundRate(rs.getDouble("defenReboundRate"));
					player.setAssistRate(rs.getDouble("assistRate"));
					player.setStealRate(rs.getDouble("stealRate"));
					player.setBlockRate(rs.getDouble("blockRate"));
					player.setTurnOverRate(rs.getDouble("turnOverRate"));
					player.setUsageRate(rs.getDouble("usageRate"));
					player.setScore_rebound_assist(rs
							.getDouble("score_rebound_assist"));
					player.setDoubleDoubleNum(rs.getDouble("doubleDoubleNum"));
					result.add(player);
				}

				rs.close();
				con.close();
				sql.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			return result; 
		}


		public ArrayList<PlayerVO> selectPlayersBySeason(String season,String type,
				String position, String union, String column,
				String order, int num) {
			ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
			PlayerVO player;
			String seasonbuffer=season.replace("-", "");
			try {
				Connection con = SqlManager.getConnection();
				Statement sql = con.createStatement();
				String query = "select * from "+seasonbuffer+"_"+type+"_"+"playerSeason,playershistoric "
						+ "where playershistoric.position like '%"+position+"%' and league='"+union+"' "
						+ "order by "+column+" "+order+" limit "+num;
				ResultSet rs = sql.executeQuery(query);
				while (rs.next()) {
					player = new PlayerVO();
					player.setName(rs.getString("name"));
					player.setOwingTeam(rs.getString("owingTeam"));
					player.setLeague(rs.getString("league"));
					player.setPlayedGames(rs.getInt("playedGames"));
					player.setGameStartingNum(rs.getInt("gameStartingNum"));
					player.setReboundNum(rs.getDouble("reboundNum"));
					player.setAssistNum(rs.getDouble("assistNum"));
					player.setPresentTime(rs.getDouble("presentTime"));
					player.setShootHitRate(rs.getDouble("shootHitRate"));
					player.setThreeHitRate(rs.getDouble("threeHitRate"));
					player.setFreeThrowHitRate(rs.getDouble("freeThrowHitRate"));
					player.setOffenReboundNum(rs.getDouble("offenReboundNum"));
					player.setDefenReboundNum(rs.getDouble("defenReboundNum"));
					player.setStealNum(rs.getDouble("stealNum"));
					player.setBlockNum(rs.getDouble("blockNum"));
					player.setFoulNum(rs.getDouble("foulNum"));
					player.setTurnOverNum(rs.getDouble("turnOverNum"));
					player.setScore(rs.getDouble("score"));
					player.setEfficiency(rs.getDouble("efficiency"));
					player.setRecentFiveMatchesScoreUpRate(rs
							.getDouble("recentFiveMatchesScoreUpRate"));
					player.setRecentFiveMatchesReboundUpRate(rs
							.getDouble("recentFiveMatchesReboundUpRate"));
					player.setRecentFiveMatchesAssistUpRate(rs
							.getDouble("recentFiveMatchesAssistUpRate"));
					player.setGmScEfficiencyValue(rs
							.getDouble("GmScEfficiencyValue"));
					player.setTrueHitRate(rs.getDouble("trueHitRate"));
					player.setShootEfficiency(rs.getDouble("shootEfficiency"));
					player.setReboundRate(rs.getDouble("reboundRate"));
					player.setOffenReboundRate(rs.getDouble("offenReboundRate"));
					player.setDefenReboundRate(rs.getDouble("defenReboundRate"));
					player.setAssistRate(rs.getDouble("assistRate"));
					player.setStealRate(rs.getDouble("stealRate"));
					player.setBlockRate(rs.getDouble("blockRate"));
					player.setTurnOverRate(rs.getDouble("turnOverRate"));
					player.setUsageRate(rs.getDouble("usageRate"));
					player.setScore_rebound_assist(rs
							.getDouble("score_rebound_assist"));
					player.setDoubleDoubleNum(rs.getDouble("doubleDoubleNum"));
					result.add(player);
				}
				rs.close();
				con.close();
				sql.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			return result;
		}

		public ArrayList<PlayerVO> selectPlayersByAverage(String season,String type,String position,
				String union, String column, String order, int num) {
			ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
			PlayerVO player;
			String seasonbuffer=season.replace("-", "");
			try {
				Connection con = SqlManager.getConnection();
				Statement sql = con.createStatement();
				String query = "select * from "+seasonbuffer+"_"+type+"_"+"playerAverage,playershistoric "
						+ "where playershistoric.position like '%"+position+"%' and league='"+union+"' "
						+ "order by "+column+" "+order+" limit "+num;
				ResultSet rs = sql.executeQuery(query);
				while (rs.next()) {
					player = new PlayerVO();
					player.setName(rs.getString("name"));
					player.setOwingTeam(rs.getString("owingTeam"));
					player.setLeague(rs.getString("league"));
					player.setPlayedGames(rs.getInt("playedGames"));
					player.setGameStartingNum(rs.getInt("gameStartingNum"));
					player.setReboundNum(rs.getDouble("reboundNum"));
					player.setAssistNum(rs.getDouble("assistNum"));
					player.setPresentTime(rs.getDouble("presentTime"));
					player.setShootHitRate(rs.getDouble("shootHitRate"));
					player.setThreeHitRate(rs.getDouble("threeHitRate"));
					player.setFreeThrowHitRate(rs.getDouble("freeThrowHitRate"));
					player.setOffenReboundNum(rs.getDouble("offenReboundNum"));
					player.setDefenReboundNum(rs.getDouble("defenReboundNum"));
					player.setStealNum(rs.getDouble("stealNum"));
					player.setBlockNum(rs.getDouble("blockNum"));
					player.setFoulNum(rs.getDouble("foulNum"));
					player.setTurnOverNum(rs.getDouble("turnOverNum"));
					player.setScore(rs.getDouble("score"));
					player.setEfficiency(rs.getDouble("efficiency"));
					player.setRecentFiveMatchesScoreUpRate(rs
							.getDouble("recentFiveMatchesScoreUpRate"));
					player.setRecentFiveMatchesReboundUpRate(rs
							.getDouble("recentFiveMatchesReboundUpRate"));
					player.setRecentFiveMatchesAssistUpRate(rs
							.getDouble("recentFiveMatchesAssistUpRate"));
					player.setGmScEfficiencyValue(rs
							.getDouble("GmScEfficiencyValue"));
					player.setTrueHitRate(rs.getDouble("trueHitRate"));
					player.setShootEfficiency(rs.getDouble("shootEfficiency"));
					player.setReboundRate(rs.getDouble("reboundRate"));
					player.setOffenReboundRate(rs.getDouble("offenReboundRate"));
					player.setDefenReboundRate(rs.getDouble("defenReboundRate"));
					player.setAssistRate(rs.getDouble("assistRate"));
					player.setStealRate(rs.getDouble("stealRate"));
					player.setBlockRate(rs.getDouble("blockRate"));
					player.setTurnOverRate(rs.getDouble("turnOverRate"));
					player.setUsageRate(rs.getDouble("usageRate"));
					player.setScore_rebound_assist(rs
							.getDouble("score_rebound_assist"));
					player.setDoubleDoubleNum(rs.getDouble("doubleDoubleNum"));
					result.add(player);
				}
				rs.close();
				con.close();
				sql.close();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			return result;
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
		
		private ArrayList<MatchVO> getSeasonMatches(String season,String type) {
	          MatchDataService match=new MatchData();
	          return match.getMatchData(season,type,"all", "all", "all");
		}

		private ArrayList<MatchVO> getTodayMatch() {
			ArrayList<MatchVO> result = new ArrayList<MatchVO>();
			String[] buffer = getToday().split("_");
			String season = buffer[0];
			String date = buffer[1];
			MatchDataService theMatch=new MatchData();
			result=theMatch.getMatchData(season, "all", date, "all", "all");
			if(result.size()!=0){
				return result;
			}
			
			ArrayList<MatchVO> mostRecentDateMatches=new ArrayList<MatchVO>();
			String seasonRecent="";
			String dateRecent="";
			boolean isFirst=true;
				
			
			for (MatchVO match : getSeasonMatches(season, "Playoff")) {
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
					PlayerVO thisPlayer;
					thisPlayer = getPlayerBaseInfo(playerName);
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
		}

		public ArrayList<PlayerVO> getDayHotPlayer(String column, int num) {
			ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
	        if(isGetPlayerToday==false){
			    calCulateDayData();
			    isGetPlayerToday=true;
	        }
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
			return getOrderedPlayersBySeason(season, type, column, "desc", num);
		}

		public ArrayList<PlayerVO> getBestImprovedPlayer(String season,String type,String column, int num) {
			return getOrderedPlayersBySeason(season, type, column, "desc", num);
		}

		public ArrayList<PlayerVO> getPlayersByInitialName(char character) {
			ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
			ArrayList<PlayerVO> thePlayers = getPlayerActiveBaseInfo();
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
			ArrayList<MatchVO> allSeasonMatches=getSeasonMatches("14-15", "all");
			Collections.sort(allSeasonMatches, new SequenceOfMatch());
			for (MatchVO vo : allSeasonMatches) {
				for (RecordVO vv : vo.getRecords()) {
					if (vv.getPlayerName().equals(playerName)) {
						result.add(vo);
						count++;
						if (count >= num)
							return result;
				        break;
					}
				}
			}
			return result;
		}

		public ArrayList<MatchVO> getMatches(String playerName) {
			ArrayList<MatchVO> result = new ArrayList<MatchVO>();
			ArrayList<MatchVO> allSeasonMatches=getSeasonMatches("14-15", "all");
			Collections.sort(allSeasonMatches, new SequenceOfMatch());
			for (MatchVO vo : allSeasonMatches) {
				for (RecordVO vv : vo.getRecords()) {
					if (vv.getPlayerName().equals(playerName)){
						result.add(vo);
						break;
					}
				}
			}
			return result;
		}

		public ArrayList<PlayerVO> getPlayersByTeam(String teamAbLocation) {
			ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
			ArrayList<PlayerVO> thePlayers = getPlayerActiveBaseInfo();
			for (PlayerVO vo : thePlayers) {
				if (vo.getOwingTeam().equals(teamAbLocation))
					result.add(vo);
			}
			return result;
		}
		

		private int convertMinuteToSecond(String s) {
			if(!s.contains(":")){
				return 0;
			}
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
	
	

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		PlayerData playerDataReader = new PlayerData();
//		playerDataReader.getPlayerActiveBaseInfo();
//		playerDataReader.getPlayerHistoricBaseInfo();
//		playerDataReader.getPlayerBaseInfo("Aaron Brooks");
//		playerDataReader.getPlayerBaseInfoForVague("a");
//		System.out.println(playerDataReader.getPlayerSeasonInfo("14-15", "Team").size());
//		System.out.println(playerDataReader.getPlayerAverageInfo("13-14", "Playoff").size());
//		System.out.println(playerDataReader.selectPlayersBySeason("12-13", "Preseason", "F", "E", "score", "desc", 5).size());
//		System.out.println(playerDataReader.getDayHotPlayer("score", 5).size());
//	    playerDataReader.getPlayersByTeam("BKN");
		playerDataReader.getRecentMatches("Kobe Bryant", 5);
		long end = System.currentTimeMillis();
		System.out.println("运行时间：" + (end - start) + "毫秒");// 应该是end - start
	}
}
