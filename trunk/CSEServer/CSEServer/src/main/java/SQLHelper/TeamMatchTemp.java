package SQLHelper;

/*
 * 舍弃
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TeamMatchTemp {

	public static void teamMatchTempInit() {
		ArrayList<String> names = new ArrayList<String>();
		Connection con;

		try {
			con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			Statement sql2 = con.createStatement();
			sql.execute("drop table if exists teamMatchTemp");
			sql.execute("create table teamMatchTemp(id int not null auto_increment,"
					+ "season varchar(20) not null default 'null',"
					+ "teamName varchar(20) not null default 'null',"
					+ "shootAttemptNum int not null default 0,"
					+ "freeThrowAttemptNum int not null default 0,"
					+ "offenReboundNum int not null default 0,"
					+ "defenReboundNum int not null default 0,"
					+ "shootHitNum int not null default 0,"
					+ "foulNum int not null default 0,"
					+ "score int not null default 0,"
					+ "stealNum int not null default 0,"
					+ "assistNum int not null default 0,"
					+ "threeHitNum int not null default 0,"
					+ "threeAttemptNum int not null default 0,"
					+ "freeThrowHitNum int not null default 0,"
					+ "blockNum int not null default 0,"
					+ "turnOverNum int not null default 0,"
					+ "winNum int not null default 0,"
					+ "loseNum int not null default 0,"
					+ "dsOffenReboundNum int not null default 0,"
					+ "dsDefenReboundNum int not null default 0,"
					+ "dsScore int not null default 0," + "primary key(id));");
			String query = "select abLocation from teams";
			ResultSet result = sql.executeQuery(query);
			while (result.next()) {
				names.add(result.getString("abLocation"));
			}
			result.close();
			int count = 1;
			for (String name : names) {
				System.out.println(name);
				query = "select * from records where team='" + name + "'";
				ResultSet rs = sql.executeQuery(query);
				int temp = 0;
				int shootHitNum = 0; // 投篮命中数
				int shootAttemptNum = 0; // 投篮出手次数
				int threeHitNum = 0; // 三分命中数
				int threeAttemptNum = 0; // 三分出手数
				int freeThrowHitNum = 0; // 罚球命中数
				int freeThrowAttemptNum = 0; // 罚球出手数
				int offenReboundNum = 0; // 进攻篮板数
				int defenReboundNum = 0; // 防守篮板数
				int assistNum = 0;// 助攻数
				int stealNum = 0;// 抢断数
				int blockNum = 0;// 盖帽数
				int turnOverNum = 0;// 失误数
				int foulNum = 0;// 犯规数
				int score = 0;// 得分
				int dsOffenReboundNum = 0; // 对手进攻篮板数
				int dsDefenReboundNum = 0;// 对手防守篮板数
				int dsScore = 0;// 对手得分

				while (rs.next()) {
					int matchID = rs.getInt("matchID");
					if (temp != matchID) {
						temp = matchID;
						query = "select * from records where matchID="
								+ matchID + " and team!='" + name + "'";
						ResultSet rss = sql2.executeQuery(query);
						while (rss.next()) {
							dsOffenReboundNum += rss.getInt("offenReboundNum");
							dsDefenReboundNum += rss.getInt("defenReboundNum");
							dsScore += rss.getInt("score");
						}
						rss.close();
					}

					// 计算投篮命中数
					shootHitNum += rs.getInt("shootHitNum");
					// 投篮出手次数
					shootAttemptNum += rs.getInt("shootAttemptNum");
					// 三分命中数
					threeHitNum += rs.getInt("threeHitNum");
					// 三分出手数
					threeAttemptNum += rs.getInt("threeAttemptNum");
					// 罚球命中数
					freeThrowHitNum += rs.getInt("freeThrowHitNum");
					// 罚球出手数
					freeThrowAttemptNum += rs.getInt("freeThrowAttemptNum");
					// 进攻篮板数
					offenReboundNum += rs.getInt("offenReboundNum");
					// 防守篮板数
					defenReboundNum += rs.getInt("defenReboundNum");
					// 助攻数
					assistNum += rs.getInt("assistNum");
					// 抢断数
					stealNum += rs.getInt("stealNum");
					// 盖帽数
					blockNum += rs.getInt("blockNum");
					// 失误数
					turnOverNum += rs.getInt("turnOverNum");
					// 犯规数
					foulNum += rs.getInt("foulNum");
					// 得分
					score += rs.getInt("score");
				}
				rs.close();

				query = "select season,homeTeam,visitingScore,homeScore from matches where visitingTeam='"
						+ name + "'or homeTeam='" + name + "'";
				ResultSet resultSet = sql.executeQuery(query);
				int winNum = 0;
				int loseNum = 0;
				String season = null;
				while (resultSet.next()) {
					season = resultSet.getString("season");
					int team1Score = resultSet.getInt("visitingScore");
					int team2Score = resultSet.getInt("homeScore");
					String home = resultSet.getString("homeTeam");
					if (home.equals(name)) {
						if (team2Score > team1Score)
							winNum++;
						else {
							loseNum++;
						}
					} else {
						if (team2Score < team1Score)
							winNum++;
						else {
							loseNum++;
						}
					}
				}

				sql.execute("insert teamMatchTemp values(" + (count++) + ",'"
						+ season + "','" + name + "'," + shootAttemptNum + ","
						+ freeThrowAttemptNum + "," + offenReboundNum + ","
						+ defenReboundNum + "," + shootHitNum + "," + foulNum
						+ "," + score + "," + stealNum + "," + assistNum + ","
						+ threeHitNum + "," + threeAttemptNum + ","
						+ freeThrowHitNum + "," + blockNum + "," + turnOverNum
						+ "," + winNum + "," + loseNum + ","
						+ dsOffenReboundNum + "," + dsDefenReboundNum + ","
						+ dsScore + ")");

				resultSet.close();
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
	}

	public static void main(String[] args) {
		teamMatchTempInit();
	}
}
