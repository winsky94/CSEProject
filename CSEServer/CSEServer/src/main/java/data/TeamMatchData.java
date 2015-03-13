package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import po.TeamMatchDataPO;
import SQLHelper.SqlManager;

public class TeamMatchData {


	public ArrayList<TeamMatchDataPO> getTeamMatchData() {
		ArrayList<TeamMatchDataPO> datas = new ArrayList<TeamMatchDataPO>();
		ArrayList<String> names = new ArrayList<String>();
		Connection con;

		try {
			con = SqlManager.getConnection();
			Statement sql = con.createStatement();
//			sql.execute("create table teamMatchTemp(id int not null auto_increment,"
//					+ "season varchar(20) not null default 'null',"
//					+ "teamName varchar(20) not null default 'null',"
//					+ "shootAttemptNum int not null default 0,"
//					+ "freeThrowAttemptNum int not null default 0,"
//					+ "offenReboundNum int not null default 0,"
//					+ "defenReboundNum int not null default 0,"
//					+ "shootHitNum int not null default 0,"
//					+ "foulNum int not null default 0,"
//					+ "score int not null default 0,"
//					+ "stealNum int not null default 0,"
//					+ "assistNum int not null default 0,"
//					+ "threeHitNum int not null default 0,"
//					+ "threeAttemptNum int not null default 0,"
//					+ "freeThrowHitNum int not null default 0,"
//					+ "blockNum int not null default 0,"
//					+ "turnOverNum int not null default 0,"
//					+ "winNum int not null default 0,"
//					+ "loseNum int not null default 0," + "primary key(id));");
//			String query = "select abLocation from teams";
//			ResultSet result = sql.executeQuery(query);
//			while (result.next()) {
//				names.add(result.getString("abLocation"));
//			}
//			int count = 1;
//			for (String name : names) {
//				query = "select * from records where team='" + name + "'";
//				result = sql.executeQuery(query);
//				int matchesNum = 0; // 比赛场数
//				int temp = 0;
//				int shootHitNum = 0; // 投篮命中数
//				int shootAttemptNum = 0; // 投篮出手次数
//				int threeHitNum = 0; // 三分命中数
//				int threeAttemptNum = 0; // 三分出手数
//				int freeThrowHitNum = 0; // 罚球命中数
//				int freeThrowAttemptNum = 0; // 罚球出手数
//				int offenReboundNum = 0; // 进攻篮板数
//				int defenReboundNum = 0; // 防守篮板数
//				int reboundNum = 0;// 篮板数
//				int assistNum = 0;// 助攻数
//				int stealNum = 0;// 抢断数
//				int blockNum = 0;// 盖帽数
//				int turnOverNum = 0;// 失误数
//				int foulNum = 0;// 犯规数
//				int score = 0;// 得分
//				double threeHitRate = 0;// 三分命中率
//				double freeThrowHitRate = 0;// 罚球命中率
//				double winRate = 0; // 胜率
//				double offenRound = 0; // 进攻回合
//				double offenEfficiency = 0; // 进攻效率
//				double defenEfficiency = 0; // 防守效率
//				double offenReboundEfficiency = 0; // 进攻篮板效率
//				double defenReboundEfficiency = 0; // 防守篮板效率
//				double stealEfficiency = 0; // 抢断效率
//				double assistEfficiency = 0; // 助攻率
//
//				ArrayList<Integer> matchIDs = new ArrayList<Integer>();
//				while (result.next()) {
//					// 计算比赛场数
//					int matchID = result.getInt("matchID");
//					if (temp != matchID) {
//						matchesNum++;
//						temp = matchID;
//						matchIDs.add(matchID);
//					}
//
//					// 计算投篮命中数
//					shootHitNum += result.getInt("shootHitNum");
//					// 投篮出手次数
//					shootAttemptNum += result.getInt("shootAttemptNum");
//					// 三分命中数
//					threeHitNum += result.getInt("threeHitNum");
//					// 三分出手数
//					threeAttemptNum += result.getInt("threeAttemptNum");
//					// 罚球命中数
//					freeThrowHitNum += result.getInt("freeThrowHitNum");
//					// 罚球出手数
//					freeThrowAttemptNum += result.getInt("freeThrowAttemptNum");
//					// 进攻篮板数
//					offenReboundNum += result.getInt("offenReboundNum");
//					// 防守篮板数
//					defenReboundNum += result.getInt("defenReboundNum");
//					// 篮板数
//					reboundNum += (offenReboundNum + defenReboundNum);
//					// 助攻数
//					assistNum += result.getInt("assistNum");
//					// 抢断数
//					stealNum += result.getInt("stealNum");
//					// 盖帽数
//					blockNum += result.getInt("blockNum");
//					// 失误数
//					turnOverNum += result.getInt("turnOverNum");
//					// 犯规数
//					foulNum += result.getInt("foulNum");
//					// 得分
//					score += result.getInt("score");
//				}
//
//				
//				query = "select season,homeTeam,visitingScore,homeScore from matches where visitingTeam='"
//						+ name + "'or homeTeam='" + name + "'";
//				ResultSet resultSet = sql.executeQuery(query);
//				int winNum = 0;
//				int loseNum = 0;
//				String season = null;
//				while (resultSet.next()) {
//					season = resultSet.getString("season");
//					int team1Score = resultSet.getInt("visitingScore");
//					int team2Score = resultSet.getInt("homeScore");
//					String home = resultSet.getString("homeTeam");
//					if (home.equals(name)) {
//						if (team2Score > team1Score)
//							winNum++;
//						else {
//							loseNum++;
//						}
//					} else {
//						if (team2Score < team1Score)
//							winNum++;
//						else {
//							loseNum++;
//						}
//					}
//				}
//
//				sql.execute("insert teamMatchTemp values(" + (count++) + ",'"
//						+ season + "','" + name + "'," + shootAttemptNum + ","
//						+ freeThrowAttemptNum + "," + offenReboundNum + ","
//						+ defenReboundNum + "," + shootHitNum + "," + foulNum
//						+ "," + score + "," + stealNum + "," + assistNum + ","
//						+ threeHitNum + "," + threeAttemptNum + ","
//						+ freeThrowHitNum + "," + blockNum + "," + turnOverNum
//						+ "," + winNum + "," + loseNum + ")");
//
//				resultSet.close();
				
				
			
				// 三分命中率
				threeHitRate = threeHitNum / threeAttemptNum;
				// 罚球命中率
				freeThrowHitRate = freeThrowHitNum / freeThrowAttemptNum;
				// 胜率
				winRate = (double) winNum / (winNum + loseNum);

				 // 进攻回合
				 int dfScore=0;
				 int dfDefenseReboundNum=0;
				 int dfOffenseReboundNum=0;
				 int dfOffenseRound=0;
				 for(Integer in:matchIDs){
				 ResultSet
				 rs=sql.executeQuery("select * from records where matchID="+in+" and team!="+name);
				 while(rs.next()){
				 dfScore+=rs.getInt("score");
				 dfDefenseReboundNum+=rs.getInt("defenReboundNum");
				 dfOffenseReboundNum+=rs.getInt("offenReboundNum");
				 }
				 rs.close();
				 }
				 offenRound=shootAttemptNum+0.4*freeThrowAttemptNum-1.07*((double)offenReboundNum/(offenReboundNum+dfDefenseReboundNum)*(shootAttemptNum-shootHitNum))+1.07*foulNum;
				
				 // 进攻效率
				 offenEfficiency=(double)score/offenRound*100;
				 // 防守效率
				 defenEfficiency=(double)dfScore/offenRound*100;
				 // 进攻篮板效率
				 // 防守篮板效率
				 // 抢断效率
				 // 助攻率

			}
			sql.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return datas;
	}

	public static void main(String[] args) {
		TeamMatchData tmd = new TeamMatchData();
		tmd.getTeamMatchData();
	}

}
