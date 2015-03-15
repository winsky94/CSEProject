package SQLHelper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TeamMatchDataReader {
	int sqlID = 1;
	String teamName = null;
	int matchesNum = 0;
	int winNum = 0;
	// 场均
	int shootHitNum = 0;// 投篮命中数
	int shootAttemptNum = 0;// 投篮出手次数
	int threeHitNum = 0;// 三分命中数
	int threeAttemptNum = 0;// 三分出手次数
	int freeThrowHitNum = 0;// 罚球命中数
	int freeThrowAttemptNum = 0;// 罚球出手次数
	int offenReboundNum = 0;
	int defenReboundNum = 0;
	int reboundNum = 0;
	int assistNum = 0;
	int stealNum = 0;
	int blockNum = 0;
	int turnOverNum = 0;// 失误数
	int foulNum = 0;// 犯规数
	int score = 0;

	double shootHitNumAverage = 0;// 投篮命中数
	double shootAttemptNumAverage = 0;// 投篮出手次数
	double threeHitNumAverage = 0;// 三分命中数
	double threeAttemptNumAverage = 0;// 三分出手次数
	double freeThrowHitNumAverage = 0;// 罚球命中数
	double freeThrowAttemptNumAverage = 0;// 罚球出手次数
	double offenReboundNumAverage = 0;
	double defenReboundNumAverage = 0;
	double reboundNumAverage = 0;
	double assistNumAverage = 0;
	double stealNumAverage = 0;
	double blockNumAverage = 0;
	double turnOverNumAverage = 0;// 失误数
	double foulNumAverage = 0;// 犯规数
	double scoreAverage = 0;

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

	// 赛季
	// 赛季平均的数字型数值跟场均是一样的，胜率也是一样的
	double shootHitRateSeason = 0;// 投篮命中率
	double threeHitRateSeason = 0;// 三分命中率
	double freeThrowHitRateSeason = 0;// 罚球命中率
	double offenRoundSeason = 0; // 进攻回合
	double offenEfficiencySeason = 0; // 进攻效率
	double defenEfficiencySeason = 0; // 防守效率
	double offenReboundEfficiencySeason = 0; // 进攻篮板效率
	double defenReboundEfficiencySeason = 0; // 防守篮板效率
	double stealEfficiencySeason = 0; // 抢断效率
	double assistEfficiencySeason = 0; // 助攻率
	int dsDefenReboundNumSeason = 0;// 对手防守篮板数
	int dsOffenReboundNumSeason = 0;// 对手进攻篮板数
	// temp
	int dsScoreSeason = 0;// 总赛季对手得分
	int dsOffenRoundSeason = 0;// 总赛季对手进攻回合，即总赛季球队防守回合

	public static void main(String[] args) {
		TeamMatchDataReader teamMatchDataReader = new TeamMatchDataReader();
		teamMatchDataReader.calculate();
	}

	public void calculate() {
		createTable();
		ArrayList<String> names = new ArrayList<String>();
		names = getTeams();
		try {
			Connection con = SqlManager.getConnection();
			Statement sql = con.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String query = "select * from matchtemp";
			ResultSet result = sql.executeQuery(query);

			for (String name : names) {
				System.out.println(name);
				teamName = name;
				// 遍历一次matchTemp找该球队的信息
				while (result.next()) {
					int matchID = result.getInt("matchID");
					Map<String, String> teams = getTeamsByMathcID(matchID);
					String homeTeam = teams.get("homeTeam");
					String visitingTeam = teams.get("visitingTeam");
					if (name.equals(homeTeam)) {
						// 当前球队在该比赛中是主队
						getTeamData(result, "home");
					} else if (name.equals(visitingTeam)) {
						// 当前球队在该比赛中是客队
						getTeamData(result, "visiting");
					} else {
						// 该球队未参加这场比赛
						// 跳出当前循环，进行下一次循环
						continue;
					}
				}

				// 上面计算的的是每场比赛的效率和，接下来除以总的比赛场数得到场均
				getAverageData();

				exportToSQL();
				// 将resultSet光标返回,便于进行下一次遍历
				result.beforeFirst();
				reset();
			}
			result.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 计算篮板数、投篮数等的赛季场均数据
	 */
	private void getAverageData() {
		shootHitNumAverage = (double) shootHitNum / matchesNum;// 投篮命中数
		shootAttemptNumAverage = (double) shootAttemptNum / matchesNum;// 投篮出手次数
		threeHitNumAverage = (double) threeHitNum / matchesNum;// 三分命中数
		threeAttemptNumAverage = (double) threeAttemptNum / matchesNum;// 三分出手次数
		freeThrowHitNumAverage = (double) freeThrowHitNum / matchesNum;// 罚球命中数
		freeThrowAttemptNumAverage = (double) freeThrowAttemptNum / matchesNum;// 罚球出手次数
		offenReboundNumAverage = (double) offenReboundNum / matchesNum;
		defenReboundNumAverage = (double) defenReboundNum / matchesNum;
		reboundNumAverage = (double) reboundNum / matchesNum;
		assistNumAverage = (double) assistNum / matchesNum;
		stealNumAverage = (double) stealNum / matchesNum;
		blockNumAverage = (double) blockNum / matchesNum;
		turnOverNumAverage = (double) turnOverNum / matchesNum;// 失误数
		foulNumAverage = (double) foulNum / matchesNum;// 犯规数
		scoreAverage = (double) score / matchesNum;

		shootHitRate = (double) shootHitRate / matchesNum;
		threeHitRate = (double) threeHitRate / matchesNum;
		freeThrowHitRate = (double) freeThrowHitRate / matchesNum;
		winRate = (double) winNum / matchesNum;
		offenRound = (double) offenRound / matchesNum;
		offenEfficiency = (double) offenEfficiency / matchesNum;
		defenEfficiency = (double) defenEfficiency / matchesNum;
		offenReboundEfficiency = (double) offenReboundEfficiency / matchesNum;
		defenReboundEfficiency = (double) defenReboundEfficiency / matchesNum;
		stealEfficiency = (double) stealEfficiency / matchesNum;
		assistEfficiency = (double) assistEfficiency / matchesNum;

		// 计算赛季的总数据平均，对于数量而言，场均和赛季平均一样，胜率也是一样的
		shootHitRateSeason = (double) shootHitNum / shootAttemptNum;
		threeHitRateSeason = (double) threeHitNum / threeAttemptNum;
		freeThrowHitRateSeason = (double) freeThrowHitNum / freeThrowAttemptNum;
		offenRoundSeason = (double) offenRound / matchesNum;
		offenEfficiencySeason = (double) score / offenRound * 100;
		defenEfficiencySeason = (double) dsScoreSeason / dsOffenRoundSeason;
		offenReboundEfficiencySeason = (double) offenReboundNum
				/ (offenReboundNum + dsDefenReboundNumSeason);
		defenReboundEfficiencySeason = (double) defenReboundNum
				/ (defenReboundNum + dsOffenReboundNumSeason);
		stealEfficiencySeason = (double) stealNum / dsOffenRoundSeason;
		assistEfficiencySeason = (double) assistNum / offenRound;
	}

	private void createTable() {
		try {
			Connection con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			sql.execute("drop table if exists teamMatchDataAverage");
			sql.execute("create table teamMatchDataAverage(id int not null auto_increment,"
					+ "team varchar(20) not null default 'null',"
					+ "matchesNum int not null default 0,"
					+ "shootHitNum int not null default 0,"
					+ "shootAttemptNum int not null default 0,"
					+ "threeHitNum int not null default 0,"
					+ "threeAttemptNum int not null default 0,"
					+ "freeThrowHitNum int not null default 0,"
					+ "freeThrowAttemptNum int not null default 0,"
					+ "offenReboundNum int not null default 0,"
					+ "defenReboundNum int not null default 0,"
					+ "reboundNum int not null default 0,"
					+ "assistNum int not null default 0,"
					+ "stealNum int not null default 0,"
					+ "blockNum int not null default 0,"
					+ "turnOverNum int not null default 0,"
					+ "foulNum int not null default 0,"
					+ "score int not null default 0,"

					+ "shootHitRate double not null default 0,"
					+ "threeHitRate double not null default 0,"
					+ "freeThrowHitRate double not null default 0,"
					+ "offenRound double not null default 0,"
					+ "offenEfficiency double not null default 0,"
					+ "defenEfficiency double not null default 0,"
					+ "offenReboundRate double not null default 0,"
					+ "defenReboundRate double not null default 0,"
					+ "stealRate double not null default 0,"
					+ "assistRate double not null default 0,"
					+ "primary key(id));");
			sql.close();

			Statement sql2 = con.createStatement();
			sql2.execute("drop table if exists teamMatchDataSeason");
			sql2.execute("create table teamMatchDataSeason(id int not null auto_increment,"
					+ "team varchar(20) not null default 'null',"
					+ "matchesNum int not null default 0,"
					+ "shootHitNum int not null default 0,"
					+ "shootAttemptNum int not null default 0,"
					+ "threeHitNum int not null default 0,"
					+ "threeAttemptNum int not null default 0,"
					+ "freeThrowHitNum int not null default 0,"
					+ "freeThrowAttemptNum int not null default 0,"
					+ "offenReboundNum int not null default 0,"
					+ "defenReboundNum int not null default 0,"
					+ "reboundNum int not null default 0,"
					+ "assistNum int not null default 0,"
					+ "stealNum int not null default 0,"
					+ "blockNum int not null default 0,"
					+ "turnOverNum int not null default 0,"
					+ "foulNum int not null default 0,"
					+ "score int not null default 0,"

					+ "shootHitRate double not null default 0,"
					+ "threeHitRate double not null default 0,"
					+ "freeThrowHitRate double not null default 0,"
					+ "offenRound double not null default 0,"
					+ "offenEfficiency double not null default 0,"
					+ "defenEfficiency double not null default 0,"
					+ "offenReboundRate double not null default 0,"
					+ "defenReboundRate double not null default 0,"
					+ "stealRate double not null default 0,"
					+ "assistRate double not null default 0,"
					+ "primary key(id));");

			sql2.close();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 规范率值的小数位数
	 */
	private void foramt() {
		DecimalFormat dec = new DecimalFormat("0.000");

		shootHitNumAverage = Double.parseDouble(dec.format(shootHitNumAverage));// 投篮命中数
		shootAttemptNumAverage = Double.parseDouble(dec
				.format(shootAttemptNumAverage));// 投篮出手次数
		threeHitNumAverage = Double.parseDouble(dec.format(threeHitNumAverage));// 三分命中数
		threeAttemptNumAverage = Double.parseDouble(dec
				.format(threeAttemptNumAverage));// 三分出手次数
		freeThrowHitNumAverage = Double.parseDouble(dec
				.format(freeThrowHitNumAverage));// 罚球命中数
		freeThrowAttemptNumAverage = Double.parseDouble(dec
				.format(freeThrowAttemptNumAverage));// 罚球出手次数
		offenReboundNumAverage = Double.parseDouble(dec
				.format(offenReboundNumAverage));
		defenReboundNumAverage = Double.parseDouble(dec
				.format(defenReboundNumAverage));
		reboundNumAverage = Double.parseDouble(dec.format(reboundNumAverage));
		assistNumAverage = Double.parseDouble(dec.format(assistNumAverage));
		stealNumAverage = Double.parseDouble(dec.format(stealNumAverage));
		blockNumAverage = Double.parseDouble(dec.format(blockNumAverage));
		turnOverNumAverage = Double.parseDouble(dec.format(turnOverNumAverage));// 失误数
		foulNumAverage = Double.parseDouble(dec.format(foulNumAverage));// 犯规数
		scoreAverage = Double.parseDouble(dec.format(scoreAverage));

		shootHitRate = Double.parseDouble(dec.format(shootHitRate));
		shootHitRateSeason = Double.parseDouble(dec.format(shootHitRateSeason));
		threeHitRate = Double.parseDouble(dec.format(threeHitRate));
		threeHitRateSeason = Double.parseDouble(dec.format(threeHitRateSeason));
		freeThrowHitRate = Double.parseDouble(dec.format(freeThrowHitRate));
		freeThrowHitRateSeason = Double.parseDouble(dec
				.format(freeThrowHitRateSeason));
		offenRound = Double.parseDouble(dec.format(offenRound));
		offenRoundSeason = Double.parseDouble(dec.format(offenRoundSeason));
		offenEfficiency = Double.parseDouble(dec.format(offenEfficiency));
		offenEfficiencySeason = Double.parseDouble(dec
				.format(offenEfficiencySeason));
		defenEfficiency = Double.parseDouble(dec.format(defenEfficiency));
		defenEfficiencySeason = Double.parseDouble(dec
				.format(defenEfficiencySeason));
		offenReboundEfficiency = Double.parseDouble(dec
				.format(offenReboundEfficiency));
		offenReboundEfficiencySeason = Double.parseDouble(dec
				.format(offenReboundEfficiencySeason));
		defenReboundEfficiency = Double.parseDouble(dec
				.format(defenReboundEfficiency));
		defenReboundEfficiencySeason = Double.parseDouble(dec
				.format(defenReboundEfficiencySeason));
		stealEfficiency = Double.parseDouble(dec.format(stealEfficiency));
		stealEfficiencySeason = Double.parseDouble(dec
				.format(stealEfficiencySeason));
		assistEfficiency = Double.parseDouble(dec.format(assistEfficiency));
		assistEfficiencySeason = Double.parseDouble(dec
				.format(assistEfficiencySeason));
	}

	private void exportToSQL() {
		foramt();

		try {
			Connection connection = SqlManager.getConnection();
			Statement sql = connection.createStatement();

			sql.execute("insert teamMatchDataSeason values(" + sqlID + ",'"
					+ teamName + "'," + matchesNum + "," + shootHitNumAverage
					+ "," + shootAttemptNumAverage + "," + threeHitNumAverage
					+ "," + threeAttemptNumAverage + ","
					+ freeThrowHitNumAverage + "," + freeThrowAttemptNumAverage
					+ "," + offenReboundNumAverage + ","
					+ defenReboundNumAverage + "," + reboundNumAverage + ","
					+ assistNumAverage + "," + stealNumAverage + ","
					+ blockNumAverage + "," + turnOverNumAverage + ","
					+ foulNumAverage + "," + scoreAverage + ","
					+ shootHitRateSeason + "," + threeHitRateSeason + ","
					+ freeThrowHitRateSeason + "," + offenRoundSeason + ","
					+ offenEfficiencySeason + "," + defenEfficiencySeason + ","
					+ offenReboundEfficiencySeason + ","
					+ defenReboundEfficiencySeason + ","
					+ stealEfficiencySeason + "," + assistEfficiencySeason
					+ ")");
			sql.close();

			Statement sql2 = connection.createStatement();
			sql2.execute("insert teamMatchDataAverage values(" + sqlID + ",'"
					+ teamName + "'," + matchesNum + "," + shootHitNumAverage
					+ "," + shootAttemptNumAverage + "," + threeHitNumAverage
					+ "," + threeAttemptNumAverage + ","
					+ freeThrowHitNumAverage + "," + freeThrowAttemptNumAverage
					+ "," + offenReboundNumAverage + ","
					+ defenReboundNumAverage + "," + reboundNumAverage + ","
					+ assistNumAverage + "," + stealNumAverage + ","
					+ blockNumAverage + "," + turnOverNumAverage + ","
					+ foulNumAverage + "," + scoreAverage + "," + shootHitRate
					+ "," + threeHitRate + "," + freeThrowHitRate + ","
					+ offenRound + "," + offenEfficiency + ","
					+ defenEfficiency + "," + offenReboundEfficiency + ","
					+ defenReboundEfficiency + "," + stealEfficiency + ","
					+ assistEfficiency + ")");
			sql2.close();

			sqlID++;
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 根据球队是主队还是客队，修改作为全局变量的那些球队的技术属性——每场数据之和
	 * 
	 * @param resultSet
	 * @param flag
	 *            标志球队是主队还是客队
	 */
	private void getTeamData(ResultSet resultSet, String flag) {
		matchesNum++;
		try {
			// 计算胜利场数
			int homeScore = resultSet.getInt("homeScore");
			int visitingScore = resultSet.getInt("visitingScore");
			if (flag.equals("home") && homeScore > visitingScore) {
				winNum++;
			} else if (flag.equals("visiting") && homeScore < visitingScore) {
				winNum++;
			}

			shootHitNum += resultSet.getInt(flag + "ShootHitNum");
			shootAttemptNum += resultSet.getInt(flag + "ShootAttemptNum");
			threeHitNum += resultSet.getInt(flag + "ThreeHitNum");
			threeAttemptNum += resultSet.getInt(flag + "ThreeAttemptNum");
			freeThrowHitNum += resultSet.getInt(flag + "FreeThrowHitNum");
			freeThrowAttemptNum += resultSet.getInt(flag
					+ "FreeThrowAttemptNum");
			offenReboundNum += resultSet.getInt(flag + "OffenReboundNum");
			defenReboundNum += resultSet.getInt(flag + "DefenReboundNum");
			reboundNum += (resultSet.getInt(flag + "OffenReboundNum") + resultSet
					.getInt(flag + "DefenReboundNum"));
			assistNum += resultSet.getInt(flag + "AssistNum");
			stealNum += resultSet.getInt(flag + "StealNum");
			blockNum += resultSet.getInt(flag + "BlockNum");
			turnOverNum += resultSet.getInt(flag + "TurnOverNum");
			foulNum += resultSet.getInt(flag + "FoulNum");
			score += resultSet.getInt(flag + "Score");

			shootHitRate += (double) resultSet.getInt(flag + "ShootHitNum")
					/ resultSet.getInt(flag + "ShootAttemptNum");
			threeHitRate += (double) resultSet.getInt(flag + "ThreeHitNum")
					/ resultSet.getInt(flag + "ThreeAttemptNum");
			freeThrowHitRate += (double) resultSet.getInt(flag
					+ "FreeThrowHitNum")
					/ resultSet.getInt(flag + "FreeThrowAttemptNum");

			int perHomeShootAttemptNum = resultSet
					.getInt("homeShootAttemptNum");
			int perHomeFreeThrowAttemptNum = resultSet
					.getInt("homeFreeThrowAttemptNum");
			int perHomeOffenReboundNum = resultSet
					.getInt("homeOffenReboundNum");
			int perHomeDefenReboundNum = resultSet
					.getInt("homeDefenReboundNum");
			int perHomeMissShoot = perHomeShootAttemptNum
					- resultSet.getInt("homeShootAttemptNum");
			int perHomeTurnOverNum = resultSet.getInt("homeTurnOverNum");

			int perVisitingShootAttemptNum = resultSet
					.getInt("visitingShootAttemptNum");
			int perVisitingFreeThrowAttemptNum = resultSet
					.getInt("visitingFreeThrowAttemptNum");
			int perVisitingOffenReboundNum = resultSet
					.getInt("visitingOffenReboundNum");
			int perVisitingDefenReboundNum = resultSet
					.getInt("visitingDefenReboundNum");
			int perVisitingMissShoot = perVisitingShootAttemptNum
					- resultSet.getInt("visitingShootAttemptNum");
			int perVisitingTurnOverNum = resultSet
					.getInt("visitingTurnOverNum");

			double perHomeOfenRound = perHomeShootAttemptNum
					+ 0.4
					* perHomeFreeThrowAttemptNum
					- 1.07
					* (perHomeOffenReboundNum
							/ (double) (perHomeOffenReboundNum + perVisitingDefenReboundNum) * perHomeMissShoot)
					+ 1.07 * perHomeTurnOverNum;
			double perVisitingOfenRound = perVisitingShootAttemptNum
					+ 0.4
					* perVisitingFreeThrowAttemptNum
					- 1.07
					* (perVisitingOffenReboundNum
							/ (double) (perVisitingOffenReboundNum + perHomeDefenReboundNum) * perVisitingMissShoot)
					+ 1.07 * perVisitingTurnOverNum;
			if (flag.equals("home")) {
				offenRound += perHomeOfenRound;

				offenEfficiency += (double) resultSet.getInt("homeScore")
						/ perHomeOfenRound * 100;
				defenEfficiency += (double) resultSet.getInt("visitingScore")
						/ perVisitingOfenRound * 100;
				offenReboundEfficiency += (double) perHomeOffenReboundNum
						/ (perHomeOffenReboundNum + perVisitingDefenReboundNum);
				defenReboundEfficiency += (double) perHomeDefenReboundNum
						/ (perHomeDefenReboundNum + perVisitingOffenReboundNum);

				stealEfficiency += (double) resultSet.getInt("homeStealNum")
						/ perVisitingOfenRound * 100;
				assistEfficiency += (double) resultSet.getInt("homeAssistNum")
						/ perHomeOfenRound * 100;

				dsScoreSeason += resultSet.getInt("visitingScore");
				dsOffenRoundSeason += perVisitingOfenRound;
				dsOffenReboundNumSeason += perVisitingOffenReboundNum;
				dsDefenReboundNumSeason += perVisitingDefenReboundNum;
			} else {
				offenRound += perVisitingOfenRound;

				offenEfficiency += (double) resultSet.getInt("visitingScore")
						/ perVisitingOfenRound * 100;
				defenEfficiency += (double) resultSet.getInt("homeScore")
						/ perHomeOfenRound * 100;
				offenReboundEfficiency += (double) perVisitingOffenReboundNum
						/ (perVisitingOffenReboundNum + perHomeDefenReboundNum);
				defenReboundEfficiency += (double) perVisitingDefenReboundNum
						/ (perVisitingDefenReboundNum + perHomeOffenReboundNum);

				stealEfficiency += (double) resultSet
						.getInt("visitingStealNum") / perHomeOfenRound * 100;
				assistEfficiency += (double) resultSet
						.getInt("visitingAssistNum")
						/ perVisitingOfenRound
						* 100;

				dsScoreSeason += resultSet.getInt("homeScore");
				dsOffenRoundSeason += perHomeOfenRound;
				dsOffenReboundNumSeason += perHomeOffenReboundNum;
				dsDefenReboundNumSeason += perHomeDefenReboundNum;
			}

		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	/**
	 * 将全局变量置为初始值，便于统计下一球队的数据，该方法在每统计完一个球队信息后调用
	 */
	private void reset() {
		teamName = null;
		matchesNum = 0;
		winNum = 0;
		// 场均
		shootHitNum = 0;// 投篮命中数
		shootAttemptNum = 0;// 投篮出手次数
		threeHitNum = 0;// 三分命中数
		threeAttemptNum = 0;// 三分出手次数
		freeThrowHitNum = 0;// 罚球命中数
		freeThrowAttemptNum = 0;// 罚球出手次数
		offenReboundNum = 0;
		defenReboundNum = 0;
		reboundNum = 0;
		assistNum = 0;
		stealNum = 0;
		blockNum = 0;
		turnOverNum = 0;// 失误数
		foulNum = 0;// 犯规数
		score = 0;

		shootHitNumAverage = 0;// 投篮命中数
		shootAttemptNumAverage = 0;// 投篮出手次数
		threeHitNumAverage = 0;// 三分命中数
		threeAttemptNumAverage = 0;// 三分出手次数
		freeThrowHitNumAverage = 0;// 罚球命中数
		freeThrowAttemptNumAverage = 0;// 罚球出手次数
		offenReboundNumAverage = 0;
		defenReboundNumAverage = 0;
		reboundNumAverage = 0;
		assistNumAverage = 0;
		stealNumAverage = 0;
		blockNumAverage = 0;
		turnOverNumAverage = 0;// 失误数
		foulNumAverage = 0;// 犯规数
		scoreAverage = 0;

		shootHitRate = 0;// 投篮命中率
		threeHitRate = 0;// 三分命中率
		freeThrowHitRate = 0;// 罚球命中率
		winRate = 0; // 胜率
		offenRound = 0; // 进攻回合
		offenEfficiency = 0; // 进攻效率
		defenEfficiency = 0; // 防守效率
		offenReboundEfficiency = 0;
		defenReboundEfficiency = 0;
		stealEfficiency = 0; // 抢断效率
		assistEfficiency = 0; // 助攻率

		// 赛季
		shootHitRateSeason = 0;// 投篮命中率
		threeHitRateSeason = 0;// 三分命中率
		freeThrowHitRateSeason = 0;// 罚球命中率
		offenRoundSeason = 0; // 进攻回合
		offenEfficiencySeason = 0; // 进攻效率
		defenEfficiencySeason = 0; // 防守效率
		offenReboundEfficiencySeason = 0;
		defenReboundEfficiencySeason = 0;
		stealEfficiencySeason = 0; // 抢断效率
		assistEfficiencySeason = 0; // 助攻率
		dsDefenReboundNumSeason = 0;// 对手防守篮板数
		dsOffenReboundNumSeason = 0;// 对手进攻篮板数
		dsScoreSeason = 0;// 总赛季对手得分
		dsOffenRoundSeason = 0;// 总赛季对手进攻回合，即总赛季球队防守回合
	}

	/**
	 * 根据比赛id找到主队是谁，客队是谁
	 * 
	 * @param id
	 * @return
	 */
	private Map<String, String> getTeamsByMathcID(int id) {
		Map<String, String> result = new HashMap<String, String>();
		try {
			Connection con = SqlManager.getConnection();
			Statement sql = con.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String query = "select homeTeam,visitingTeam from matches where matchID="
					+ id;
			ResultSet resultSet = sql.executeQuery(query);
			resultSet.next();
			String homeTeam = resultSet.getString("homeTeam");
			String visitingTeam = resultSet.getString("visitingTeam");

			result.put("homeTeam", homeTeam);
			result.put("visitingTeam", visitingTeam);

			resultSet.close();
			sql.close();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return result;
	}

	private ArrayList<String> getTeams() {
		ArrayList<String> result = new ArrayList<String>();
		try {
			Connection con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			String query = "select abLocation from teams";
			ResultSet resultSet = sql.executeQuery(query);

			while (resultSet.next()) {
				String name = resultSet.getString("abLocation");
				result.add(name);
			}

			resultSet.close();
			sql.close();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
}
