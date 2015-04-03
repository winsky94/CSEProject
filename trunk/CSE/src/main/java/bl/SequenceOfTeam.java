package bl;

import java.util.Comparator;

import vo.TeamVO;

public class SequenceOfTeam implements Comparator<TeamVO> {
	private String column;
	private String order;

	public SequenceOfTeam(String column, String order) {
		this.column = column;
		this.order = order;
	}

	public int compare(TeamVO t1, TeamVO t2) {
		// TODO 自动生成的方法存根
		int result = 0;

		if (column.equals("球队名称")) {
			result = t1.getTeamName().compareTo(t1.getTeamName());
		} else if (column.equals("比赛场数")) {
			result = compareInt(t1.getMatchesNum(), t2.getMatchesNum());
		} else if (column.equals("投篮命中数")) {
			result = compareDouble(t1.getShootHitNum(), t2.getShootHitNum());
		} else if (column.equals("投篮出手次数")) {
			result = compareDouble(t1.getShootAttemptNum(),
					t2.getShootAttemptNum());
		} else if (column.equals("投篮命中率")) {
			result = compareDouble(t1.getShootHitRate(), t2.getShootHitRate());
		} else if (column.equals("三分命中数")) {
			result = compareDouble(t1.getThreeHitNum(), t2.getThreeHitNum());
		} else if (column.equals("三分出手数")) {
			result = compareDouble(t1.getThreeAttemptNum(),
					t2.getThreeAttemptNum());
		} else if (column.equals("三分命中率")) {
			result = compareDouble(t1.getThreeHitRate(), t2.getThreeHitRate());
		} else if (column.equals("罚球命中数")) {
			result = compareDouble(t1.getFreeThrowHitNum(),
					t2.getFreeThrowHitNum());
		} else if (column.equals("罚球出手数")) {
			result = compareDouble(t1.getFreeThrowAttemptNum(),
					t2.getFreeThrowAttemptNum());
		} else if (column.equals("罚球命中率")) {
			result = compareDouble(t1.getFreeThrowHitRate(),
					t2.getFreeThrowHitRate());
		} else if (column.equals("进攻篮板数")) {
			result = compareDouble(t1.getOffenReboundNum(),
					t2.getOffenReboundNum());
		} else if (column.equals("防守篮板数")) {
			result = compareDouble(t1.getDefenReboundNum(),
					t2.getDefenReboundNum());
		} else if (column.equals("篮板数")) {
			result = compareDouble(t1.getReboundNum(), t2.getReboundNum());
		} else if (column.equals("进攻篮板效率")) {
			result = compareDouble(t1.getOffenReboundEfficiency(),
					t2.getOffenReboundEfficiency());
		} else if (column.equals("防守篮板效率")) {
			result = compareDouble(t1.getDefenReboundEfficiency(),
					t2.getDefenReboundEfficiency());
		} else if (column.equals("进攻回合")) {
			result = compareDouble(t1.getOffenRound(), t2.getOffenRound());
		} else if (column.equals("进攻效率")) {
			result = compareDouble(t1.getOffenEfficiency(),
					t2.getOffenEfficiency());
		} else if (column.equals("防守效率")) {
			result = compareDouble(t1.getDefenEfficiency(),
					t2.getDefenEfficiency());
		} else if (column.equals("助攻数")) {
			result = compareDouble(t1.getAssistNum(), t2.getAssistNum());
		} else if (column.equals("助攻率")) {
			result = compareDouble(t1.getAssistEfficiency(),
					t2.getAssistEfficiency());
		} else if (column.equals("抢断数")) {
			result = compareDouble(t1.getStealNum(), t2.getStealNum());
		} else if (column.equals("抢断效率")) {
			result = compareDouble(t1.getStealEfficiency(),
					t2.getStealEfficiency());
		} else if (column.equals("盖帽数")) {
			result = compareDouble(t1.getBlockNum(), t2.getBlockNum());
		} else if (column.equals("失误数")) {
			result = compareDouble(t1.getTurnOverNum(), t2.getTurnOverNum());
		} else if (column.equals("犯规数")) {
			result = compareDouble(t1.getFoulNum(), t2.getFoulNum());
		} else if (column.equals("比赛得分")) {
			result = compareDouble(t1.getScore(), t2.getScore());
		} else if (column.equals("胜率")) {
			result = compareDouble(t1.getWinRate(), t2.getWinRate());
		}
		if (order.equals("desc")) {
			// 降序
			return (-result);
		} else {
			// 升序(默认)
			return result;
		}

	}

	private int compareInt(int a, int b) {
		return Integer.compare(a, b);
	}

	private int compareDouble(Double a, Double b) {
		return Double.compare(a, b);
	}
}
