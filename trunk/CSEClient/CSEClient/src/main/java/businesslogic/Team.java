package businesslogic;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import po.MatchPO;
import po.TeamPO;
import vo.TeamVO;
import businesslogicservice.TeamBLService;
import dataservice.TeamDataService;

public class Team implements TeamBLService {
	private TeamDataService service = null;

	public Team() {
		try {
			// String host = "localhost";
			String host = GetServer.getServerHost();
			String url = "rmi://" + host + "/teamService";
			service = (TeamDataService) Naming.lookup(url);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	/**
	 * 查找球队基本信息
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

	/**
	 * 查找球队某个赛季的技术数据
	 * 
	 * @param season
	 *            赛季
	 */
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

	/**
	 * 查找球队某个赛季的场均技术数据
	 * 
	 * @param season
	 *            赛季
	 */
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

	/**
	 * 查找某个球员的基本信息
	 * 
	 * @param name
	 *            姓名
	 */
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

	/**
	 * 查找某个球队某个赛季的技术数据
	 * 
	 * @param season
	 *            赛季
	 * @param name
	 *            球队名
	 */
	public TeamVO getTeamSeasonInfo(String season, String name) {
		// TODO 自动生成的方法存根
		TeamVO teamVO = null;
		try {
			TeamPO po = service.getTeamSeasonInfo(season, name);
			teamVO = poToVo(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return teamVO;
	}

	/**
	 * 查找某个球队某个赛季的场均技术数据
	 * 
	 * @param season
	 *            赛季
	 * @param name
	 *            球队名
	 */
	public TeamVO getTeamAverageInfo(String season, String name) {
		// TODO 自动生成的方法存根
		TeamVO teamVO = null;
		try {
			TeamPO po = service.getTeamAverageInfo(season, name);
			teamVO = poToVo(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return teamVO;
	}

	public ArrayList<TeamVO> getOrderedTeamsBySeason(String season,
			String condition, String order) {
		// TODO 自动生成的方法存根
		ArrayList<TeamVO> result = new ArrayList<TeamVO>();
		try {
			ArrayList<TeamPO> teams = service.getOrderedTeamsBySeason(season,
					condition, order);
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

	public ArrayList<TeamVO> getOrderedTeamsByAverage(String season,
			String condition, String order) {
		// TODO 自动生成的方法存根
		ArrayList<TeamVO> result = new ArrayList<TeamVO>();
		try {
			ArrayList<TeamPO> teams = service.getOrderedTeamsByAverage(season,
					condition, order);
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

	public ImageIcon getTeamImage(String name) {
		ImageIcon icon = null;
		try {
			icon = service.getTeamImage(name);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return icon;
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

	public ArrayList<MatchPO> getRecentMatches(String teamName) {
		// TODO 自动生成的方法存根
		ArrayList<MatchPO> result = new ArrayList<MatchPO>();
		try {
			result = service.getRecentMatches(teamName);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<MatchPO> getMatches(String teamName) {
		// TODO 自动生成的方法存根
		ArrayList<MatchPO> result = new ArrayList<MatchPO>();
		try {
			result = service.getMatches(teamName);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<TeamPO> getSeasonHotTeam(String season, String column) {
		// TODO 自动生成的方法存根
		ArrayList<TeamPO> result = new ArrayList<TeamPO>();
		try {
			result = service.getSeasonHotTeam(season, column);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

}
