package data;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import SQLHelper.SqlManager;
import po.PlayerPO;
import dataservice.PlayerDataService;

public class Player extends UnicastRemoteObject implements PlayerDataService {
	private static final long serialVersionUID = 1L;
	Connection con;

	public Player() throws RemoteException {
		super();
		// TODO 自动生成的构造函数存根
	}

	/**
	 * 从数据库中获得球员列表
	 * 
	 * @return 球员最基本信息的列表
	 */
	public ArrayList<PlayerPO> getPlayerBaseInfo()throws RemoteException {
		ArrayList<PlayerPO> players = new ArrayList<PlayerPO>();
		try {
			con=SqlManager.getConnection();
			Statement sql = con.createStatement();
			ResultSet resultSet = sql.executeQuery("select * from players");

			while (resultSet.next()) {
				int id = resultSet.getInt("id");// 编号
				String name = resultSet.getString("name");// 球员名称
				int number = resultSet.getInt("number");// 球衣号码
				String position = resultSet.getString("position");// 位置
				String height = resultSet.getString("height");// 身高（英尺-英存）
				int weight = resultSet.getInt("weight");// 体重（英镑）
				String birth = resultSet.getString("birth");// （月 日，年）
				int age = resultSet.getInt("age");// 年龄
				int exp = resultSet.getInt("exp");// 球龄
				String school = resultSet.getString("school");// 毕业学校

				PlayerPO playerPO = new PlayerPO(id, name, number, position,
						height, weight, birth, age, exp, school);
				players.add(playerPO);
			}
			resultSet.close();
			con.close();
			sql.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return players;
	}
	
	/**
	 * 得到球员的某个赛季的技术统计数据
	 * @param 赛季
	 * @return
	 */
	public ArrayList<PlayerPO> getPlayerSeasonInfo(String season) throws RemoteException{
		ArrayList<PlayerPO> players = new ArrayList<PlayerPO>();
		PlayerPO player;
		try {
			con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			String query = "select * from playermatchdataseason";
			ResultSet rs= sql.executeQuery(query);
			while(rs.next()){
				player=new PlayerPO();
				player.setName(rs.getString("playerName"));
				player.setTeamName(rs.getString("owingTeam"));
				player.setPlayedGames(rs.getInt("playedGames"));
				player.setGameStartingNum(rs.getInt("gameStartingNum"));
				player.setReboundNum(rs.getInt("reboundNum"));
				player.setAssistNum(rs.getInt("assistNum"));
				player.setPresentTime(rs.getString("presentTime"));
				player.setShootHitRate(rs.getDouble("shootHitRate"));
				player.setThreeHitRate(rs.getDouble("threeHitRate"));
				player.setFreeThrowHitRate(rs.getDouble("freeThrowHitRate"));
				player.setOffenNum(rs.getInt("offenNum"));
				player.setDefenNum(rs.getInt("defenNum"));
				player.setStealNum(rs.getInt("stealNum"));
				player.setBlockNum(rs.getInt("blockNum"));
				player.setFoulNum(rs.getInt("foulNum"));
				player.setTurnOverNum(rs.getInt("turnOverNum"));
				player.setScore(rs.getInt("score"));
				player.setEfficiency(rs.getDouble("efficiency"));
				player.setRecentFiveMatchesScoreUpRate(rs.getDouble("recentFiveMatchesScoreUpRate"));
				player.setRecentFiveMatchesReboundUpRate(rs.getDouble("recentFiveMatchesReboundUpRate"));
				player.setRecentFiveMatchesAssistUpRate(rs.getDouble("recentFiveMatchesAssistUpRate"));
				player.setGmScEfficiencyValue(rs.getDouble("GmScEfficiencyValue"));
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
				player.setDoubleDouble(rs.getInt("doubleDoubleNum"));
				players.add(player);
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
		
		return players;
	}

	/**
	 * 查询某球员的基础信息
	 * 
	 * @param name
	 *            球员名称
	 * @return PlayerPO对象
	 */
	public PlayerPO getPlayerBaseInfo(String name) throws RemoteException{
		String playername=name.replace("'", "''");
		PlayerPO player = null;
		try {
			con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			String query = "select * from players where name='" + playername + "'";
			ResultSet resultSet = sql.executeQuery(query);
			resultSet.next();
			player=new PlayerPO();		
			int id = resultSet.getInt("id");
			String playerName = resultSet.getString("name");
			int number= resultSet.getInt("number");
			String position = resultSet.getString("position");
			String height = resultSet.getString("height");
			int weight = resultSet.getInt("weight");
			String birth = resultSet.getString("birth");
			int age = resultSet.getInt("age");
			int exp=resultSet.getInt("exp");
			String school=resultSet.getString("school");
			player = new PlayerPO(id, playerName, number, position, height,
					weight, birth, age,exp,school);

			resultSet.close();
			con.close();
			sql.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return player;
	}

	/**
	 * 根据赛季和球员名称返回球员这个赛季的总的技术数据
	 * 
	 * @param season
	 * @param name
	 * @return
	 */
	public PlayerPO getPlayerSeasonInfo(String season, String name) throws RemoteException{
		String playername=name.replace("'", "''");
		PlayerPO player = null;
		try {
			con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			String query = "select * from playermatchdataseason where name='" + playername + "'";
			ResultSet rs = sql.executeQuery(query);
			rs.next();
			player=new PlayerPO();
			player.setName(rs.getString("playerName"));
			player.setTeamName(rs.getString("owingTeam"));
			player.setPlayedGames(rs.getInt("playedGames"));
			player.setGameStartingNum(rs.getInt("gameStartingNum"));
			player.setReboundNum(rs.getInt("reboundNum"));
			player.setAssistNum(rs.getInt("assistNum"));
			player.setPresentTime(rs.getString("presentTime"));
			player.setShootHitRate(rs.getDouble("shootHitRate"));
			player.setThreeHitRate(rs.getDouble("threeHitRate"));
			player.setFreeThrowHitRate(rs.getDouble("freeThrowHitRate"));
			player.setOffenNum(rs.getInt("offenNum"));
			player.setDefenNum(rs.getInt("defenNum"));
			player.setStealNum(rs.getInt("stealNum"));
			player.setBlockNum(rs.getInt("blockNum"));
			player.setFoulNum(rs.getInt("foulNum"));
			player.setTurnOverNum(rs.getInt("turnOverNum"));
			player.setScore(rs.getInt("score"));
			player.setEfficiency(rs.getDouble("efficiency"));
			player.setRecentFiveMatchesScoreUpRate(rs.getDouble("recentFiveMatchesScoreUpRate"));
			player.setRecentFiveMatchesReboundUpRate(rs.getDouble("recentFiveMatchesReboundUpRate"));
			player.setRecentFiveMatchesAssistUpRate(rs.getDouble("recentFiveMatchesAssistUpRate"));
			player.setGmScEfficiencyValue(rs.getDouble("GmScEfficiencyValue"));
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
			player.setDoubleDouble(rs.getInt("doubleDoubleNum"));
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
		return player;
	}
	
	/**
	 * 得到球员的某个赛季的技术统计数据
	 * @param 赛季
	 * @return
	 */
	public ArrayList<PlayerPO> getPlayerAverageInfo(String season) throws RemoteException{
		ArrayList<PlayerPO> players = new ArrayList<PlayerPO>();
		PlayerPO player;
		try {
			con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			String query = "select * from playermatchdataaverage";
			ResultSet rs= sql.executeQuery(query);
			while(rs.next()){
				player=new PlayerPO();
				player.setName(rs.getString("playerName"));
				player.setTeamName(rs.getString("owingTeam"));
				player.setPlayedGames(rs.getInt("playedGames"));
				player.setGameStartingNum(rs.getInt("gameStartingNum"));
				player.setReboundNum(rs.getInt("reboundNum"));
				player.setAssistNum(rs.getInt("assistNum"));
				player.setPresentTime(rs.getString("presentTime"));
				player.setShootHitRate(rs.getDouble("shootHitRate"));
				player.setThreeHitRate(rs.getDouble("threeHitRate"));
				player.setFreeThrowHitRate(rs.getDouble("freeThrowHitRate"));
				player.setOffenNum(rs.getInt("offenNum"));
				player.setDefenNum(rs.getInt("defenNum"));
				player.setStealNum(rs.getInt("stealNum"));
				player.setBlockNum(rs.getInt("blockNum"));
				player.setFoulNum(rs.getInt("foulNum"));
				player.setTurnOverNum(rs.getInt("turnOverNum"));
				player.setScore(rs.getInt("score"));
				player.setEfficiency(rs.getDouble("efficiency"));
				player.setRecentFiveMatchesScoreUpRate(rs.getDouble("recentFiveMatchesScoreUpRate"));
				player.setRecentFiveMatchesReboundUpRate(rs.getDouble("recentFiveMatchesReboundUpRate"));
				player.setRecentFiveMatchesAssistUpRate(rs.getDouble("recentFiveMatchesAssistUpRate"));
				player.setGmScEfficiencyValue(rs.getDouble("GmScEfficiencyValue"));
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
				player.setDoubleDouble(rs.getInt("doubleDoubleNum"));
				players.add(player);
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
		
		return players;
	}
	
	/**
	 * 根据赛季和球员名称返回球员这个赛季的技术数据
	 * 
	 * @param season
	 * @param name
	 * @return
	 */
	public PlayerPO getPlayerAverageInfo(String season, String name) throws RemoteException{
		String playername=name.replace("'", "''");
		PlayerPO player = null;
		try {
			con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			String query = "select * from playermatchdataaverage where name='" + playername + "'";
			ResultSet rs = sql.executeQuery(query);
			rs.next();
			player=new PlayerPO();
			player.setName(rs.getString("playerName"));
			player.setTeamName(rs.getString("owingTeam"));
			player.setPlayedGames(rs.getInt("playedGames"));
			player.setGameStartingNum(rs.getInt("gameStartingNum"));
			player.setReboundNum(rs.getInt("reboundNum"));
			player.setAssistNum(rs.getInt("assistNum"));
			player.setPresentTime(rs.getString("presentTime"));
			player.setShootHitRate(rs.getDouble("shootHitRate"));
			player.setThreeHitRate(rs.getDouble("threeHitRate"));
			player.setFreeThrowHitRate(rs.getDouble("freeThrowHitRate"));
			player.setOffenNum(rs.getInt("offenNum"));
			player.setDefenNum(rs.getInt("defenNum"));
			player.setStealNum(rs.getInt("stealNum"));
			player.setBlockNum(rs.getInt("blockNum"));
			player.setFoulNum(rs.getInt("foulNum"));
			player.setTurnOverNum(rs.getInt("turnOverNum"));
			player.setScore(rs.getInt("score"));
			player.setEfficiency(rs.getDouble("efficiency"));
			player.setRecentFiveMatchesScoreUpRate(rs.getDouble("recentFiveMatchesScoreUpRate"));
			player.setRecentFiveMatchesReboundUpRate(rs.getDouble("recentFiveMatchesReboundUpRate"));
			player.setRecentFiveMatchesAssistUpRate(rs.getDouble("recentFiveMatchesAssistUpRate"));
			player.setGmScEfficiencyValue(rs.getDouble("GmScEfficiencyValue"));
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
			player.setDoubleDouble(rs.getInt("doubleDoubleNum"));
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
		return player;
	}
	
	/**
	 * 根据某一项技术分析项，将球员按某个赛季的该项数据进行升降序排序
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
	public ArrayList<PlayerPO> getOrderedPlayersBySeason(String season,
			String condition, String order) throws RemoteException {
		ArrayList<PlayerPO> result = new ArrayList<PlayerPO>();
		result = getOrderedPlayers("playermatchdataseason", season, condition,
				order);
		return result;
	}

	/**
	 * 根据某一项技术分析项，将球员按某个赛季的该项数据进行升降序排序
	 * 
	 * @param season
	 *            赛季
	 * @param condition
	 *            排序条件，即某一项技术分析项，如：篮板率
	 * @param order
	 *            升序还是降序 asc 表示升序 , desc表示降序 , 未明确写明排序方式时默认是升序
	 * 
	 * @return 按照所给条件排好序的球员列表
	 */
	public ArrayList<PlayerPO> getOrderedPlayersByAverage(String season,
			String condition, String order) throws RemoteException {
		ArrayList<PlayerPO> result = new ArrayList<PlayerPO>();
		result = getOrderedPlayers("playermatchdataaverage", season, condition,
				order);
		return result;
	}
	
	private ArrayList<PlayerPO> getOrderedPlayers(String table, String season,
			String condition, String order) {
		ArrayList<PlayerPO> players = new ArrayList<PlayerPO>();
		PlayerPO player;
		// 未明确写明排序方式时默认是升序
		if (order == null || order.equals("null")) {
			order = "asc";
		}
		try {
			con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			String query = "select * from " + table + " where season='"
					+ season + "' order by " + condition + " " + order;
			ResultSet rs= sql.executeQuery(query);
			while (rs.next()) {
				player=new PlayerPO();
				player.setName(rs.getString("playerName"));
				player.setTeamName(rs.getString("owingTeam"));
				player.setPlayedGames(rs.getInt("playedGames"));
				player.setGameStartingNum(rs.getInt("gameStartingNum"));
				player.setReboundNum(rs.getInt("reboundNum"));
				player.setAssistNum(rs.getInt("assistNum"));
				player.setPresentTime(rs.getString("presentTime"));
				player.setShootHitRate(rs.getDouble("shootHitRate"));
				player.setThreeHitRate(rs.getDouble("threeHitRate"));
				player.setFreeThrowHitRate(rs.getDouble("freeThrowHitRate"));
				player.setOffenNum(rs.getInt("offenNum"));
				player.setDefenNum(rs.getInt("defenNum"));
				player.setStealNum(rs.getInt("stealNum"));
				player.setBlockNum(rs.getInt("blockNum"));
				player.setFoulNum(rs.getInt("foulNum"));
				player.setTurnOverNum(rs.getInt("turnOverNum"));
				player.setScore(rs.getInt("score"));
				player.setEfficiency(rs.getDouble("efficiency"));
				player.setRecentFiveMatchesScoreUpRate(rs.getDouble("recentFiveMatchesScoreUpRate"));
				player.setRecentFiveMatchesReboundUpRate(rs.getDouble("recentFiveMatchesReboundUpRate"));
				player.setRecentFiveMatchesAssistUpRate(rs.getDouble("recentFiveMatchesAssistUpRate"));
				player.setGmScEfficiencyValue(rs.getDouble("GmScEfficiencyValue"));
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
				player.setDoubleDouble(rs.getInt("doubleDoubleNum"));
				players.add(player);
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
		return players;
	}
	
	/**
	 * 对某个赛季中的球员信息，根据某一项属性里的值进行筛选
	 * @param 赛季  
	 * @param 哪一项属性，如"name"
	 * @param 筛选属性的输入值
	 * @return  符合条件的球员列表
	 */
	public ArrayList<PlayerPO> selectPlayersBySeason(String season,String position,String union,String column) throws RemoteException{
		ArrayList<PlayerPO> players =selectPlayers("playermatchdataseason", season, position,union,column);
		return players;
	}
	
	/**
	 * 对某个赛季中的球员信息，根据某一项属性里的值进行筛选
	 * @param 赛季  
	 * @param 哪一项属性，如"name"
	 * @param 筛选属性的输入值
	 * @return  符合条件的球员列表
	 */
	public ArrayList<PlayerPO> selectPlayersByAverage(String season,String position,String union,String column) throws RemoteException{
		ArrayList<PlayerPO> players =selectPlayers("playermatchdataaverage", season, position,union,column);
		return players;
	}
	//F是前锋 C是中锋 G是后卫
	private ArrayList<PlayerPO> selectPlayers(String table,String season,String position,String union,String column){
		ArrayList<PlayerPO> players = new ArrayList<PlayerPO>();
		PlayerPO player;
		try {
		   con = SqlManager.getConnection();
		   Statement sql = con.createStatement();
		   String query;
		   if(position.equals("all")){
			   query="select *" +" from "+table +" order by "+table+"."+column+" desc";
			   ResultSet rs= sql.executeQuery(query);
			   while (rs.next()) {
					if(isUnionRight(rs.getString("owingTeam"), union)){
						player=new PlayerPO();
						player.setName(rs.getString("playerName"));
						player.setTeamName(rs.getString("owingTeam"));
						player.setPlayedGames(rs.getInt("playedGames"));
						player.setGameStartingNum(rs.getInt("gameStartingNum"));
						player.setReboundNum(rs.getInt("reboundNum"));
						player.setAssistNum(rs.getInt("assistNum"));
						player.setPresentTime(rs.getString("presentTime"));
						player.setShootHitRate(rs.getDouble("shootHitRate"));
						player.setThreeHitRate(rs.getDouble("threeHitRate"));
						player.setFreeThrowHitRate(rs.getDouble("freeThrowHitRate"));
						player.setOffenNum(rs.getInt("offenNum"));
						player.setDefenNum(rs.getInt("defenNum"));
						player.setStealNum(rs.getInt("stealNum"));
						player.setBlockNum(rs.getInt("blockNum"));
						player.setFoulNum(rs.getInt("foulNum"));
						player.setTurnOverNum(rs.getInt("turnOverNum"));
						player.setScore(rs.getInt("score"));
						player.setEfficiency(rs.getDouble("efficiency"));
						player.setRecentFiveMatchesScoreUpRate(rs.getDouble("recentFiveMatchesScoreUpRate"));
						player.setRecentFiveMatchesReboundUpRate(rs.getDouble("recentFiveMatchesReboundUpRate"));
						player.setRecentFiveMatchesAssistUpRate(rs.getDouble("recentFiveMatchesAssistUpRate"));
						player.setGmScEfficiencyValue(rs.getDouble("GmScEfficiencyValue"));
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
						player.setDoubleDouble(rs.getInt("doubleDoubleNum"));
						players.add(player);
					}
						
			   }
			   rs.close();
		   }			   
		   else if(union.equals("all")){
			   query = "select players.name,players.position,"+table+".*" +" from players,"+table +" where players.name="+table+".playerName and players.position like '%"+position+"%' order by "+table+"."+column+" desc";
			   ResultSet rs= sql.executeQuery(query);
			   while (rs.next()) {
						player=new PlayerPO();
						player.setName(rs.getString("playerName"));
						player.setTeamName(rs.getString("owingTeam"));
						player.setPlayedGames(rs.getInt("playedGames"));
						player.setGameStartingNum(rs.getInt("gameStartingNum"));
						player.setReboundNum(rs.getInt("reboundNum"));
						player.setAssistNum(rs.getInt("assistNum"));
						player.setPresentTime(rs.getString("presentTime"));
						player.setShootHitRate(rs.getDouble("shootHitRate"));
						player.setThreeHitRate(rs.getDouble("threeHitRate"));
						player.setFreeThrowHitRate(rs.getDouble("freeThrowHitRate"));
						player.setOffenNum(rs.getInt("offenNum"));
						player.setDefenNum(rs.getInt("defenNum"));
						player.setStealNum(rs.getInt("stealNum"));
						player.setBlockNum(rs.getInt("blockNum"));
						player.setFoulNum(rs.getInt("foulNum"));
						player.setTurnOverNum(rs.getInt("turnOverNum"));
						player.setScore(rs.getInt("score"));
						player.setEfficiency(rs.getDouble("efficiency"));
						player.setRecentFiveMatchesScoreUpRate(rs.getDouble("recentFiveMatchesScoreUpRate"));
						player.setRecentFiveMatchesReboundUpRate(rs.getDouble("recentFiveMatchesReboundUpRate"));
						player.setRecentFiveMatchesAssistUpRate(rs.getDouble("recentFiveMatchesAssistUpRate"));
						player.setGmScEfficiencyValue(rs.getDouble("GmScEfficiencyValue"));
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
						player.setDoubleDouble(rs.getInt("doubleDoubleNum"));
						players.add(player);						
			   }
			   rs.close();
		   }
		   else{                                                                                                                                       
			   query="select players.name,players.position,"+table+".*" +" from players,"+table +" where players.name="+table+".playerName and players.position like '%"+position+"%' order by "+table+"."+column+" desc";
			   ResultSet rs= sql.executeQuery(query);
			   while (rs.next()) {
					if(isUnionRight(rs.getString("owingTeam"), union)){
						player=new PlayerPO();
						player.setName(rs.getString("playerName"));
						player.setTeamName(rs.getString("owingTeam"));
						player.setPlayedGames(rs.getInt("playedGames"));
						player.setGameStartingNum(rs.getInt("gameStartingNum"));
						player.setReboundNum(rs.getInt("reboundNum"));
						player.setAssistNum(rs.getInt("assistNum"));
						player.setPresentTime(rs.getString("presentTime"));
						player.setShootHitRate(rs.getDouble("shootHitRate"));
						player.setThreeHitRate(rs.getDouble("threeHitRate"));
						player.setFreeThrowHitRate(rs.getDouble("freeThrowHitRate"));
						player.setOffenNum(rs.getInt("offenNum"));
						player.setDefenNum(rs.getInt("defenNum"));
						player.setStealNum(rs.getInt("stealNum"));
						player.setBlockNum(rs.getInt("blockNum"));
						player.setFoulNum(rs.getInt("foulNum"));
						player.setTurnOverNum(rs.getInt("turnOverNum"));
						player.setScore(rs.getInt("score"));
						player.setEfficiency(rs.getDouble("efficiency"));
						player.setRecentFiveMatchesScoreUpRate(rs.getDouble("recentFiveMatchesScoreUpRate"));
						player.setRecentFiveMatchesReboundUpRate(rs.getDouble("recentFiveMatchesReboundUpRate"));
						player.setRecentFiveMatchesAssistUpRate(rs.getDouble("recentFiveMatchesAssistUpRate"));
						player.setGmScEfficiencyValue(rs.getDouble("GmScEfficiencyValue"));
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
						player.setDoubleDouble(rs.getInt("doubleDoubleNum"));
						players.add(player);
					}
						
			   }
			   rs.close();
		   }
			
		   con.close();
		   sql.close();
		} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			

		return players;
	}
	
	public String getActionPhotoPath(String name) throws RemoteException{
		return "src/迭代一数据/players/action/" + name + ".png";
	}

	public String getPortraitPhotoPath(String name) throws RemoteException{
		return "src/迭代一数据/players/protrait/" + name + ".png";
	}

	public static void main(String[] args) {
		Connection con;
		try {
			String table="playermatchdataaverage";
			String position="G";
			String column="playedGames";
			con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			String query="select players.name,players.position,"+table+".*" +" from players,"+table +" where players.name="+table+".playerName and players.position like '%"+position+"%' order by "+table+"."+column+" desc";
			ResultSet rs= sql.executeQuery(query);
			rs.next();
			System.out.println(rs.getString("reboundNum"));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	
	private boolean isUnionRight(String owingTeam,String union){
		String[] theTeams=owingTeam.split(" ");
	    for(int i=0;i<theTeams.length;i++){
	      String team=theTeams[i];	    
		  
		  try {
			con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			String query= "select conference,partition from teams where abLocation='"+team+"'";
			ResultSet rs= sql.executeQuery(query);
			rs.next();
			if(union.equals(rs.getString("conference"))||union.equals(rs.getString("partition")))
				return true;
		 } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }		 
	    } 
	    return false;
	}

}
