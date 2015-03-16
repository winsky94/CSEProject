package businesslogic;

import java.util.ArrayList;

import po.TeamPO;
import vo.TeamVO;
import businesslogicservice.TeamBLService;

public class Team implements TeamBLService {

	/**
	 * 查找球队信息
	 * 
	 * @return 返回球队的ArrayList<TeamVO>
	 */
	public ArrayList<TeamVO> getTeamInfo() {
		// TODO 自动生成的方法存根
		return null;
	}

	public TeamVO voToPo(TeamPO po) {
		TeamVO teamVO = new TeamVO(po.getId(), po.getTeamName(),
				po.getAbLocation(), po.getLocation(), po.getConference(),
				po.getPartition(), po.getHomeCourt(), po.getSetUpTime(),
				po.getMatchesNum(), po.getShootHitNum(),
				po.getShootAttemptNum(), po.getThreeHitNum(),
				po.getThreeAttemptNum(), po.getFreeThrowHitNum(),
				po.getFreeThrowAttemptNum(), po.getOffenReboundNum(),
				po.getDefenReboundNum(), po.getReboundNum(), po.getAssistNum(),
				po.getStealNum(), po.getBlockNum(), po.getTurnOverNum(),
				po.getFoulNum(), po.getScore(), po.getShootHitRate(),
				po.getThreeHitRate(), po.getFreeThrowHitRate(),
				po.getWinRate(), po.getOffenRound(), po.getOffenEfficiency(),
				po.getDefenEfficiency(), po.getReboundEfficiency(),
				po.getOffenReboundEfficiency(), po.getDefenReboundEfficiency(),
				po.getStealEfficiency(), po.getAssistEfficiency());

		return teamVO;
	}
}
