package businesslogic;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.TeamPO;
import vo.TeamVO;
import businesslogicservice.TeamBLService;
import dataservice.TeamDataService;

public class Team implements TeamBLService {
	private TeamDataService service = null;

	public Team() {
		try {
			String host = "localhost";
			// String host = getServer.getServerHost();
			String url = "rmi://" + host + "/teamService";
			service = (TeamDataService) Naming.lookup(url);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	/**
	 * 查找球队信息
	 * 
	 * @return 返回球队的ArrayList<TeamVO>
	 */
	public ArrayList<TeamVO> getTeamBaseInfo() {
		// TODO 自动生成的方法存根
		ArrayList<TeamVO> result = new ArrayList<TeamVO>();
		try {
			ArrayList<TeamPO> teams = service.getTeamBaseInfo();
			for (TeamPO po : teams) {
				TeamVO vo = poToVo(po);
				result.add(vo);
			}
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<TeamVO> getTeamSeasonInfo(String season) {
		// TODO 自动生成的方法存根
		ArrayList<TeamVO> result = new ArrayList<TeamVO>();
		try {
			ArrayList<TeamPO> teams = service.getTeamSeasonInfo(season);
			for (TeamPO po : teams) {
				TeamVO vo = poToVo(po);
				result.add(vo);
			}
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<TeamVO> getTeamAverageInfo(String season) {
		// TODO 自动生成的方法存根
		ArrayList<TeamVO> result = new ArrayList<TeamVO>();
		try {
			ArrayList<TeamPO> teams = service.getTeamAverageInfo(season);
			for (TeamPO po : teams) {
				TeamVO vo = poToVo(po);
				result.add(vo);
			}
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
	}

	public TeamVO getTeamBaseInfo(String name) {
		// TODO 自动生成的方法存根
		TeamVO teamVO = null;
		try {
			TeamPO po = service.getTeamBaseInfo(name);
			teamVO = poToVo(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return teamVO;
	}

	public TeamVO getTeamSeasonInfo(String season, String name) {
		// TODO 自动生成的方法存根
		TeamVO teamVO = null;
		try {
			TeamPO po = service.getTeamSeasonInfo(season,name);
			teamVO = poToVo(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return teamVO;
	}
	
	public TeamVO getTeamAverageInfo(String season, String name) {
		// TODO 自动生成的方法存根
		TeamVO teamVO = null;
		try {
			TeamPO po = service.getTeamAverageInfo(season,name);
			teamVO = poToVo(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return teamVO;
	}

	public TeamVO poToVo(TeamPO po) {
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
				po.getStealEfficiency(), po.getAssistRate());

		return teamVO;
	}
}
