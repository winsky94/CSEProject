package businesslogic;

import java.rmi.Naming;
import java.util.ArrayList;

import po.CommonPO;
import po.PlayerPO;
import vo.CommonVO;
import vo.PlayerVO;
import businesslogicservice.PlayerBLService;
import dataservice.PlayerDataService;

public class Player implements PlayerBLService {
	private PlayerDataService service;

	public Player() {
		try {
			String host = "localhost";
//			String host = getServer.getServer();
			String url = "rmi://" + host + "/goodsService";
			service = (PlayerDataService) Naming.lookup(url);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	public ArrayList<PlayerVO> getPlayerList() {
		// TODO 自动生成的方法存根
		ArrayList<PlayerPO> data = service.getPlayerList();
		ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
		for (PlayerPO po : data) {
			PlayerVO vo = poTovo(po);
			result.add(vo);
		}
		return result;
	}

	public PlayerVO poTovo(PlayerPO po) {
		CommonPO commonPO = po.getCommonPO();
		CommonVO commonVO = new CommonVO(commonPO.getReboundNum(),
				commonPO.getAssistNum(), commonPO.getShootHitRate(),
				commonPO.getThreeHitRate(), commonPO.getFreeThrowHitRate(),
				commonPO.getStealNum(), commonPO.getBlockNum(),
				commonPO.getTurnOverNum(), commonPO.getFoulNum(),
				commonPO.getScore());

		PlayerVO vo = new PlayerVO(po.getId(), po.getName(), po.getNumber(),
				po.getPosition(), po.getHeight(), po.getWeight(),
				po.getBirth(), po.getAge(), po.getExp(), po.getSchool(),
				commonVO, po.getTeamName(), po.getPlayedGames(),
				po.getGameStartingNum(), po.getPresentTime(), po.getOffenNum(),
				po.getDefenNum(), po.getEfficiency(),
				po.getGmScEfficiencyValue(), po.getTrueHitRate(),
				po.getShootHitEfficiency(), po.getReboundRate(),
				po.getOffenReboundRate(), po.getDefenReboundRate(),
				po.getAssistRate(), po.getStealRate(), po.getBlockRate(),
				po.getTurnOverRate());
		return vo;
	}
}
