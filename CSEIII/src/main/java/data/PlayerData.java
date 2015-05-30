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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import dataservice.PlayerDataService;
import vo.MatchVO;
import vo.PlayerVO;
import vo.RecordVO;
import vo.TeamVO;
import SQLHelper.FileList;
import SQLHelper.SqlManager;


/**
 * 
 * 从文件中读取数据，用于将球员基本数据存储到数据库中 表名：players
 * 
 *
 */
public class PlayerData implements PlayerDataService{
	
	ArrayList <PlayerVO> players = new ArrayList<PlayerVO>();
	Map<String, TeamVO> teams;
	Map<Integer, MatchVO> matches = new HashMap<Integer, MatchVO>(1024);
	Map<String, ArrayList<MatchVO>> allSeasonMatches = new HashMap<String, ArrayList<MatchVO>>();
	static int count=1;
	boolean isReadSql=false;
	Map<String, PlayerVO> playersForBL = new HashMap<String, PlayerVO>(32);
	
	private void baseInfoInit() {
		try {
			FileList fl = new FileList("src/data/players/info");
			ArrayList<String> names = fl.getList();
			for (String name : names) {
				readBaseInfoFromFile(name);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void readBaseInfoFromFile(String fileName) {
		PlayerVO player;
		String name;
		String number;
		String position;
		String height;
		int weight;
		String birth;
		int age;
		int exp;
		String school;
		String[] content = new String[9];
		try {
			File file = new File(fileName);
			if (!file.exists()) {

				file.createNewFile();
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "UTF-8"));
			int i = 0;
			String temp = null;
			temp = br.readLine();
			while (temp != null) {
				if (temp.contains("│")) {
					String[] it = temp.split("│");
					String[] nit = it[1].split("║");
					content[i++] = nit[0].trim();
				}
				temp = br.readLine();
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		name = content[0];
		number = content[1];
		position = content[2];
		height = content[3];
		weight = Integer.parseInt(content[4]);
		birth = content[5];
		age = Integer.parseInt(content[6]);
		if(!content[7].equals("R")){
			exp = Integer.parseInt(content[7]);
		}
		else{
			exp = 0;
		}
		school = content[8];
		player = new PlayerVO(name, number, position, height, weight, birth,
				age, exp, school);
		players.add(player);
	}

	public void exportToSql() {
        baseInfoInit();
		try {
			Connection con = SqlManager.getConnection();
			con.setAutoCommit(false);
			Statement sql = con.createStatement();
			sql.execute("drop table if exists players");
			sql.execute("create table players(playerID int not null auto_increment,name varchar(40) not null default 'null',"
					+ "number varchar(40) not null default 'null',position varchar(20) not null default 'null',"
					+ "height varchar(20) not null default 'null',weight int not null default 0,"
					+ "birth varchar(20) not null default 'null',age int not null default 0,exp int not null default 0,"
					+ "school varchar(40)not null default 'null',primary key(playerID));");
			sql.close();
			PreparedStatement statement = con
					.prepareStatement("INSERT INTO players VALUES(?, ?,?,?,?,?,?,?,?,?)");
			int count = 1;
			
			for(PlayerVO player:players) {
					
				statement.setInt(1, count++);
				statement.setString(2, player.getName());
				statement.setString(3, player.getNumber());
				statement.setString(4, player.getPosition());
				statement.setString(5, player.getHeight());
				statement.setInt(6, player.getWeight());
				statement.setString(7, player.getBirth());
				statement.setInt(8, player.getAge());
				statement.setInt(9, player.getExp());
				statement.setString(10, player.getSchool());
				statement.addBatch();

				System.out.println(count - 1);
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


	public Map<String, PlayerVO> getPlayerBaseInfo() {
		
		if(isReadSql==true)
			return playersForBL;
		
		Connection con;
		try {
			con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			String query = "select * from players ";
			ResultSet resultSet = sql.executeQuery(query);
			PlayerVO player;
			
			while(resultSet.next()){
				String name;
				String number;
				String position;
				String height;
				int weight;
				String birth;
				int age;
				int exp;
				String school;
				
				name = resultSet.getString("name");
				number=resultSet.getString("number");
				position=resultSet.getString("position");
				height=resultSet.getString("height");
				weight=resultSet.getInt("weight");
				birth=resultSet.getString("birth");
				age=resultSet.getInt("age");
				exp=resultSet.getInt("exp");
				school=resultSet.getString("school");
	            
			    player = new PlayerVO(name, number, position, height, weight, birth,
							age, exp, school);
                playersForBL.put(name, player);
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
		isReadSql=true;
		return playersForBL;
	}

	public PlayerVO getPlayerBaseInfo(String name) {
		if(isReadSql==true)
			return playersForBL.get(name);
		
		PlayerVO player=new PlayerVO();
		Connection con;
		try {
			con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			if(name.contains("'"))
				name.replace("'", "''");
			String query = "select * from players where name='"+name+"' limit 1";
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
				
				thename = resultSet.getString("name");
				number=resultSet.getString("number");
				position=resultSet.getString("position");
				height=resultSet.getString("height");
				weight=resultSet.getInt("weight");
				birth=resultSet.getString("birth");
				age=resultSet.getInt("age");
				exp=resultSet.getInt("exp");
				school=resultSet.getString("school");
	            
			    player = new PlayerVO(thename, number, position, height, weight, birth,
							age, exp, school);
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
        
		ArrayList<PlayerVO> pArrayList=new ArrayList<PlayerVO>();
		Connection con;
		try {
			con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			if(name.contains("'"))
				name.replace("'", "''");
			String query = "select * from players where name like'%"+name+"%'";
			ResultSet resultSet = sql.executeQuery(query);
			PlayerVO player;
			while(resultSet.next()){
			    String thename;
				String number;
				String position;
				String height;
				int weight;
				String birth;
				int age;
				int exp;
				String school;
				
				thename = resultSet.getString("name");
				number=resultSet.getString("number");
				position=resultSet.getString("position");
				height=resultSet.getString("height");
				weight=resultSet.getInt("weight");
				birth=resultSet.getString("birth");
				age=resultSet.getInt("age");
				exp=resultSet.getInt("exp");
				school=resultSet.getString("school");
	            
			    player = new PlayerVO(thename, number, position, height, weight, birth,
							age, exp, school);
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
	
	public static void main(String[] args) {
		PlayerData playerDataReader = new PlayerData();
		playerDataReader.exportToSql();
		playerDataReader.getPlayerBaseInfo();
		playerDataReader.getPlayerBaseInfoForVague("a");
	}
}