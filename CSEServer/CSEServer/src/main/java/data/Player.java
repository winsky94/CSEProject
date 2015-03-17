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
	public ArrayList<PlayerPO> getPlayerBaseInfo() {
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
	public ArrayList<PlayerPO> getPlayerSeasonInfo(String season) {
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
	public PlayerPO getPlayerBaseInfo(String name) {
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
	public PlayerPO getPlayerSeasonInfo(String season, String name) {
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
	 * 根据赛季和球员名称返回球员这个赛季的技术数据
	 * 
	 * @param season
	 * @param name
	 * @return
	 */
	public PlayerPO getPlayerAverageInfo(String season, String name) {
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
	
	public String getActionPhotoPath(String name) {
		return "src/迭代一数据/players/action/" + name + ".png";
	}

	public String getPortraitPhotoPath(String name) {
		return "src/迭代一数据/players/protrait/" + name + ".png";
	}

	public static void main(String[] args) {
		Player player;
		try {
			player = new Player();
			PlayerPO playerPO=player.getPlayerBaseInfo("E'Twaun Moore");
			System.out.println(playerPO.getBirth());
			ArrayList<PlayerPO> players=player.getPlayerSeasonInfo("13-14");
			System.out.println(players.get(1).getPlayedGames());
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	public ArrayList<PlayerPO> getPlayerList() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
