package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import po.MatchPO;
import po.PlayerPO;
import po.RecordPO;

public class Match {

	public MatchPO readFromMatchFile(String fileName) {
		MatchPO matchPO;
		String date;// 比赛日期
		String teams;// 对阵队伍
		String score;// 比分
		String firstScore;// 第一节比分
		String secondScore;// 第二节比分
		String thirdScore;// 第三节比分
		String fourthScore;// 第四节比分
		String overTimeScore;// 加时赛比分
		String[] fisrtContent = new String[3];
		String[] detailScores = new String[5];

		try {
			// File file=new File("13-14_01-01_CHA-LAC");
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "UTF-8"));
			String temp = null;
			temp = br.readLine();
			fisrtContent = temp.split(";");
			temp = br.readLine();
			detailScores = temp.split(";");

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		date = fisrtContent[0];
		teams = fisrtContent[1];
		score = fisrtContent[2];

		firstScore = detailScores[0];
		secondScore = detailScores[1];
		thirdScore = detailScores[2];
		fourthScore = detailScores[3];
		if (detailScores.length > 4) {
			overTimeScore = detailScores[4];
		} else {
			overTimeScore = null;
		}
		matchPO = new MatchPO(date, teams, score, firstScore, secondScore,
				thirdScore, fourthScore, overTimeScore);
		return matchPO;
	}

	public ArrayList<RecordPO> readFromMatchRecordFile(String fileName) {
		String id;
		String team;// 球队
		String playerName;// 球员名
		String position;// 位置
		double presentTime;// 在场时间
		int shootHitNum;// 投篮命中数
		int shootAttemptNum;// 投篮出手数
		int threeHitNum;// 三分命中数
		int threeAttemptNum;// 三分出手数
		int freeThrowHitNum;// 罚球命中数
		int freeThrowAttemptNum;// 罚球出手数
		int offenReboundNum;// 进攻（前场）篮板数
		int defenReboundNum;// 防守（后场）篮板数
		int reboundNum;// 总篮板数
		int assistNum;// 助攻数
		int stealNum;// 抢断数
		int blockNum;// 盖帽数
		int turnOverNum;// 失误数
		int foulNum;// 犯规数
		int score;// 个人得分

		String tp[] = fileName.split("matches");
		id = tp[1].substring(1);

		try {
			// File file=new File("13-14_01-01_CHA-LAC");
			File file = new File(fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(file), "UTF-8"));
			String temp = null;
			temp = br.readLine();
			temp = br.readLine();
			temp = br.readLine();
			while (temp != null) {
				if(id.contains(temp)){
					
				}
			}

			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<MatchPO> getPlayerList() {
		ArrayList<MatchPO> matches = new ArrayList<MatchPO>();
		try {
			FileList fl = new FileList("src/迭代一数据/players/info");
			// FileList fl = new FileList(
			// "D:/LUCY/Documents/软件工程与计算III/data/迭代一数据/players/info");
			ArrayList<String> names = fl.getList();
			for (String name : names) {
				matches.add(readFromMatchFile(name));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return matches;
	}

	public static void main(String[] args) {
		FileList fl;
		try {
			fl = new FileList("src/迭代一数据/matches");
			// FileList fl = new FileList(
			// "D:/LUCY/Documents/软件工程与计算III/data/迭代一数据/players/info");
			ArrayList<String> names = fl.getList();
			for (String name : names) {
				String tp[] = name.split("matches");
				String result = tp[1].substring(1);
				System.out.println(result);
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}

	// public void exportToSql() {
	// ArrayList<PlayerPO> players = getPlayerList();
	// try {
	// Connection con = SqlManager.getConnection();
	// Statement sql = con.createStatement();
	// sql.execute("drop table if exists players");
	// sql.execute("create table players(id int not null auto_increment,name varchar(40) not null default 'name',"
	// +
	// "number int not null default 0,position varchar(20) not null default 'null',"
	// +
	// "height varchar(20) not null default 'null',weight int not null default 0,"
	// +
	// "brith varchar(20) not null default 'null',age int not null default 0,exp int not null default 0,"
	// + "school varchar(40)not null default 'null',primary key(id));");
	// int count = 1;
	// for (PlayerPO player : players) {
	// //
	// sql.execute("insert players values("+(count++)+",'Lucy',1,'F','1-1',1,'1111',1,1,'11')");
	// sql.execute("insert players values(" + (count++) + ",'"
	// + player.getName() + "'," + player.getNumber() + ",'"
	// + player.getPosition() + "','" + player.getHeight()
	// + "'," + player.getWeight() + ",'" + player.getBirth()
	// + "'," + player.getAge() + "," + player.getExp() + ",'"
	// + player.getSchool() + "')");
	//
	// System.out.println(count);
	// }
	// String query = "select * from players";
	// ResultSet result = sql.executeQuery(query);
	// System.out.println("players表数据如下：");
	// System.out.println("---------------------------------");
	// System.out.println("学号" + " " + "姓名" + " " + "数学成绩");
	// System.out.println("---------------------------------");
	// int number;
	// String name;
	// int math;
	// while (result.next()) {
	// number = result.getInt("id");
	// name = result.getString("name");
	// math = result.getInt("number");
	// System.out.println(number + " " + name + " " + math);
	// }
	// sql.close();
	// con.close();
	// } catch (java.lang.ClassNotFoundException e) {
	// System.err.println("ClassNotFoundException:" + e.getMessage());
	// } catch (SQLException ex) {
	// System.err.println("SQLException:" + ex.getMessage());
	// }
	//
	// }
}
