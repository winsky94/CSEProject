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

import SQLHelper.SqlManager;

public class PlayerSalaryData {
	ArrayList<PlayerSalary> players = new ArrayList<PlayerSalary>();
	Connection con;

	public void openSql() {
		try {
			con = SqlManager.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeSql() {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void readInfoFromFile() {
		PlayerSalary ps;
		try {
			File file = new File("src/data/players/14-15.txt");
			if (!file.exists()) {
				System.err.println("file does not exist!");
				return;
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "UTF-8"));
			String temp = null;
			temp = br.readLine();
			while (temp != null) {
				String[] nit = temp.split(",");
				String name = nit[0].replace(",", "");
				String s="";
				for(int i=1;i<nit.length;i++){
					s+=nit[i];
				}
				int salary = Integer.parseInt(s.replace("$", ""));
				ps = new PlayerSalary(name, salary);
				players.add(ps);
				temp = br.readLine();
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void exportToSql() {
		try {
			readInfoFromFile();
			Connection con = SqlManager.getConnection();
			con.setAutoCommit(false);
			Statement sql = con.createStatement();
			sql.execute("drop table if exists playersalary");
			sql.execute("create table playersalary(no int not null auto_increment,"
					+ "name varchar(40) not null default 'null',"
					+ "salary int not null default 0,primary key(no));");
			PreparedStatement statement = con
					.prepareStatement("INSERT INTO playersalary VALUES(?, ?,?)");
			int count = 1;

			for (PlayerSalary player : players) {

				statement.setInt(1, count++);
				statement.setString(2, player.getName());
				statement.setInt(3, player.getSalary());
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
			ex.printStackTrace();
		}

	}

	public int getSalary(String name){
		int salary=0;
		try {
			con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			String query = "select * from playersalary where name='"+name+"' limit 1";
			ResultSet resultSet = sql.executeQuery(query);
			if(resultSet.next()){
				salary = resultSet.getInt("salary");
			}
			resultSet.close();
			sql.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			System.out.println(name);
//			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
//			e.printStackTrace();
		}
		return salary;
	}
	
	public static void main(String[] args) {
		PlayerSalaryData p=new PlayerSalaryData();
		long start = System.currentTimeMillis();
		p.exportToSql();
		long end = System.currentTimeMillis();
		System.out.println("运行时间：" + (end - start) + "毫秒");// 应该是end - start
	}

	class PlayerSalary {
		private String name;
		private int salary;

		public PlayerSalary(String name, int salary) {
			this.name = name;
			this.salary = salary;
		}

		public int getSalary() {
			return salary;
		}

		public String getName() {
			return name;
		}

	}

}
