package businesslogic;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;

import po.PlayerPO;
import vo.PlayerVO;
import businesslogicservice.PlayerBLService;
import dataservice.PlayerDataService;

public class Player implements PlayerBLService {
	private PlayerDataService service;

	public Player() {
		try {
			String host = "localhost";
			// String host = getServer.getServerHost();
			String url = "rmi://" + host + "/playerService";
			service = (PlayerDataService) Naming.lookup(url);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	public ArrayList<PlayerVO> getPlayerList() {
		// TODO 自动生成的方法存根
		try {
			ArrayList<PlayerPO> data = service.getPlayerList();

			ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
			for (PlayerPO po : data) {
				PlayerVO vo = poTovo(po);
				result.add(vo);
			}
			return result;
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}

	}

	public PlayerVO poTovo(PlayerPO po) {
		PlayerVO vo = new PlayerVO(po.getId(), po.getName(), po.getNumber(),
				po.getPosition(), po.getHeight(), po.getWeight(),
				po.getBirth(), po.getAge(), po.getExp(), po.getSchool());
		return vo;
	}
}
