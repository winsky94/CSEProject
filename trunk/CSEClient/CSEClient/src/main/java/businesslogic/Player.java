package businesslogic;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import po.PlayerPO;
import vo.PlayerVO;
import businesslogicservice.PlayerBLService;
import dataservice.PlayerDataService;

public class Player implements PlayerBLService {
	private PlayerDataService service;

	public Player() {
		try {
			String host = getServer.getServerHost();
			String url = "rmi://" + host + "/playerService";
			service = (PlayerDataService) Naming.lookup(url);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	/**
	 * 得到球员信息
	 * 
	 * @return 返回ArrayList<PlayerVO>
	 */
	public ArrayList<PlayerVO> getPlayerBaseInfo() {
		// TODO 自动生成的方法存根
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
		try {
			ArrayList<PlayerPO> data = service.getPlayerBaseInfo();

			for (PlayerPO po : data) {
				PlayerVO vo = poTovo(po);
				result.add(vo);
			}

		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<PlayerVO> getPlayerSeasonInfo(String season) {
		// TODO 自动生成的方法存根
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
		try {
			ArrayList<PlayerPO> data = service.getPlayerSeasonInfo(season);

			for (PlayerPO po : data) {
				PlayerVO vo = poTovo(po);
				result.add(vo);
			}

		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<PlayerVO> getPlayerAverageInfo(String season) {
		// TODO 自动生成的方法存根
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
		try {
			ArrayList<PlayerPO> data = service.getPlayerAverageInfo(season);

			for (PlayerPO po : data) {
				PlayerVO vo = poTovo(po);
				result.add(vo);
			}

		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
	}

	public PlayerVO getPlayerBaseInfo(String name) {
		// TODO 自动生成的方法存根
		PlayerVO result = null;
		try {
			PlayerPO po = service.getPlayerBaseInfo(name);
			result = poTovo(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
	}

	public PlayerVO getPlayerSeasonInfo(String season, String name) {
		// TODO 自动生成的方法存根
		PlayerVO result = null;
		try {
			PlayerPO po = service.getPlayerSeasonInfo(season, name);
			result = poTovo(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
	}

	public PlayerVO getPlayerAverageInfo(String season, String name) {
		// TODO 自动生成的方法存根
		PlayerVO result = null;
		try {
			PlayerPO po = service.getPlayerAverageInfo(season, name);
			result = poTovo(po);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<PlayerVO> getOrderedPlayersBySeason(String season,
			String condition, String order) throws RemoteException {
		// TODO 自动生成的方法存根
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
		try {
			ArrayList<PlayerPO> data = service.getOrderedPlayersBySeason(
					season, condition, order);

			for (PlayerPO po : data) {
				PlayerVO vo = poTovo(po);
				result.add(vo);
			}

		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<PlayerVO> getOrderedPlayersByAverage(String season,
			String condition, String order) {
		// TODO 自动生成的方法存根
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
		try {
			ArrayList<PlayerPO> data = service.getOrderedPlayersByAverage(
					season, condition, order);

			for (PlayerPO po : data) {
				PlayerVO vo = poTovo(po);
				result.add(vo);
			}

		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<PlayerVO> selectPlayersBySeason(String season,
			String position, String union, String column) {
		// TODO 自动生成的方法存根
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
		try {
			position = changePositionCHToEN(position);
			union = changeUnionCHToEN(union);
			column = changeColumnCHToEN(column);
			ArrayList<PlayerPO> data = service.selectPlayersBySeason(season,
					position, union, column);

			for (PlayerPO po : data) {
				PlayerVO vo = poTovo(po);
				result.add(vo);
			}

		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<PlayerVO> selectPlayersByAverage(String season,
			String position, String union, String column) {
		// TODO 自动生成的方法存根
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
		try {
			position = changePositionCHToEN(position);
			union = changeUnionCHToEN(union);
			column = changeColumnCHToEN(column);
			ArrayList<PlayerPO> data = service.selectPlayersByAverage(season,
					position, union, column);

			for (PlayerPO po : data) {
				PlayerVO vo = poTovo(po);
				result.add(vo);
			}

		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return result;
	}

	public ImageIcon getPlayerPortraitImage(String name) {
		ImageIcon icon = null;
		try {
			icon = service.getPlayerPortraitImage(name);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return icon;
	}

	public ImageIcon getPlayerActionImage(String name) {
		ImageIcon icon = null;
		try {
			icon = service.getPlayerActionImage(name);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return icon;
	}

	public PlayerVO poTovo(PlayerPO po) {
		int exp = po.getExp();
		String expString = "";
		if (exp > 0) {
			expString = String.valueOf(exp);
		} else if (exp == 0) {
			expString = "R";
		}
		PlayerVO vo = new PlayerVO(po.getId(), po.getName(), po.getNumber(),
				po.getPosition(), po.getHeight(), po.getWeight(),
				po.getBirth(), po.getAge(), expString, po.getSchool(),
				po.getTeamName(), po.getPlayedGames(), po.getGameStartingNum(),
				po.getReboundNum(), po.getAssistNum(), po.getPresentTime(),
				po.getShootHitRate(), po.getThreeHitRate(),
				po.getFreeThrowHitRate(), po.getOffenNum(), po.getDefenNum(),
				po.getStealNum(), po.getBlockNum(), po.getTurnOverNum(),
				po.getFoulNum(), po.getScore(), po.getEfficiency(),
				po.getRecentFiveMatchesScoreUpRate(),
				po.getRecentFiveMatchesReboundUpRate(),
				po.getGmScEfficiencyValue(), po.getTrueHitRate(),
				po.getShootEfficiency(), po.getReboundRate(),
				po.getOffenReboundRate(), po.getDefenReboundRate(),
				po.getAssistRate(), po.getStealRate(), po.getBlockRate(),
				po.getTurnOverRate(), po.getUsageRate(),
				po.getScore_rebound_assist(), po.getDoubleDoubleNum());
		return vo;
	}

	/**
	 * 将table的列的中文名转换为对应的英文名，便于数据库查找
	 * 
	 * @param CH
	 *            table列的中文名
	 * @return 对应的英文名
	 */
	private String changeColumnCHToEN(String CH) {
		String EN = null;
		if (CH.equals("得分")) {
			EN = "score";
		} else if (CH.equals("篮板")) {
			EN = "reboundNum";
		} else if (CH.equals("助攻")) {
			EN = "assistNum";
		} else if (CH.equals("得分/篮板/助攻（加权比为1:1:1）")) {
			EN = "score_rebound_assist";
		} else if (CH.equals("盖帽")) {
			EN = "blockNum";
		} else if (CH.equals("抢断")) {
			EN = "stealNum";
		} else if (CH.equals("犯规")) {
			EN = "foulNum";
		} else if (CH.equals("失误")) {
			EN = "turnOverNum";
		} else if (CH.equals("分钟")) {
			EN = "presentTime";
		} else if (CH.equals("效率")) {
			EN = "efficiency";
		} else if (CH.equals("投篮")) {
			EN = "shootHitRate";
		} else if (CH.equals("三分")) {
			EN = "threeHitRate";
		} else if (CH.equals("罚球")) {
			EN = "freeThrowHitRate";
		} else if (CH.equals("两双")) {
			EN = "doubleDoubleNum";
		} else {
			EN = CH;
		}
		return EN;
	}

	/**
	 * 将球员位置中文转为英文便于数据库查找
	 * 
	 * @param CH
	 *            位置的中文名
	 * @return 对应的英文
	 */
	private String changePositionCHToEN(String CH) {
		String EN = null;
		if (CH.equals("全部")) {
			EN = "all";
		} else if (CH.equals("前锋")) {
			EN = "F";
		} else if (CH.equals("中锋")) {
			EN = "C";
		} else if (CH.equals("后卫")) {
			EN = "G";
		} else {
			EN = CH;
		}
		return EN;
	}

	/**
	 * 将球员联盟中文转为英文便于数据库查找
	 * 
	 * @param CH
	 *            球员联盟
	 * @return 对应的英文
	 */
	private String changeUnionCHToEN(String CH) {
		String EN = null;
		if (CH.equals("全部")) {
			EN = "all";
		} else {
			EN = CH;
		}
		return EN;
	}

//	/**
//	 * 将球员的位置由英文转为中文
//	 * @param position
//	 * @return
//	 */
//	private String changePositionToCH(String position){
//		String CH="";
//		if (position.equals("F")) {
//			CH = "前锋";
//		} else if (position.equals("C")) {
//			CH = "中锋";
//		} else if (position.equals("G")) {
//			CH = "后卫";
//		} else {
//			CH = "";
//		}
//		return CH;
//	}
}
