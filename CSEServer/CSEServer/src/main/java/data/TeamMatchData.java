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
		double threeHitRate = 0;// 三分命中率
		double freeThrowHitRate = 0;// 罚球命中率
		double winRate = 0; // 胜率
		double offenRound = 0; // 进攻回合
		double offenEfficiency = 0; // 进攻效率
		double defenEfficiency = 0; // 防守效率
		double offenReboundEfficiency = 0; // 进攻篮板效率
		double defenReboundEfficiency = 0; // 防守篮板效率
		double stealEfficiency = 0; // 抢断效率
		double assistEfficiency = 0; // 助攻率
		
		ArrayList<String> names = new ArrayList<String>();
		Connection con;

		try {
			con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			
				
				
			
				

			
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
