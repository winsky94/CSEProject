package SQLHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TeamMatchDataReader {
	public void exportToSQL() {
		String teamName = null;
		double shootHitRate = 0;// 投篮命中率
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
			String query = "select * from teamMatchTemp";
			ResultSet result = sql.executeQuery(query);
			while (result.next()) {
				teamName = result.getString("teamName");
				// 投篮命中率
				shootHitRate = (double) result.getInt("shootHitNum")
						/ result.getInt("shootAttemptNum");
				// 三分命中率
				threeHitRate = (double) result.getInt("threeHitNum")
						/ result.getInt("threeAttemptNum");
				// 罚球命中率
				freeThrowHitRate = (double) result.getInt("freeThrowHitNum")
						/ result.getInt(" freeThrowAttemptNum");
				// 胜率
				winRate = (double) result.getInt("winNum")
						/ (result.getInt("winNum") + result.getInt("loseNum"));
				// 进攻效率
				offenEfficiency = (double) result.getInt("score")
						/ result.getInt("offenRound") * 100;
				// 防守效率
				Statement sql2 = con.createStatement();
				query = "select * from matches";
				ResultSet rs = sql.executeQuery(query);
				ArrayList<String>
				
				defenEfficiency = (double) result.getInt("dsScore") /  * 100;
				// 进攻篮板效率
				// 防守篮板效率
				// 抢断效率
				// 助攻率
			}

			sql.execute("drop table if exists teams");
			// sql.execute("create table teams(id int not null auto_increment,teamName varchar(40) not null default 'null',"
			// +
			// "abLocation varchar(20) not null default 'null',location varchar(20) not null default 'null',"
			// +
			// "conference varchar(20) not null default 'null',partition varchar(20) not null default 'null',"
			// +
			// "homeCourt varchar(40) not null default 'null',setUpTime int not null default 0,primary key(id));");

			// 进攻回合
			int dfScore = 0;
			int dfDefenseReboundNum = 0;
			int dfOffenseReboundNum = 0;
			int dfOffenseRound = 0;
			for (Integer in : matchIDs) {
				ResultSet rs = sql
						.executeQuery("select * from records where matchID="
								+ in + " and team!=" + name);
				while (rs.next()) {
					dfScore += rs.getInt("score");
					dfDefenseReboundNum += rs.getInt("defenReboundNum");
					dfOffenseReboundNum += rs.getInt("offenReboundNum");
				}
				rs.close();
			}
			offenRound = shootAttemptNum
					+ 0.4
					* freeThrowAttemptNum
					- 1.07
					* ((double) offenReboundNum
							/ (offenReboundNum + dfDefenseReboundNum) * (shootAttemptNum - shootHitNum))
					+ 1.07 * foulNum;

			sql.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
