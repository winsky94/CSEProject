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
			// String host = "localhost";
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

	public ImageIcon getPlayerImage(String name) {
		ImageIcon icon = null;
		try {
			icon = service.getPlayerImage(name);
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
				po.getDoubleDoubleNum());
		return vo;
	}
}
