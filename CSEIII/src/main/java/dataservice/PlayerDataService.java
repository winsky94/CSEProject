package dataservice;

import java.util.ArrayList;
import java.util.Map;

import vo.PlayerVO;

public interface PlayerDataService{
	public Map<String, PlayerVO> getPlayerBaseInfo();

	public PlayerVO getPlayerBaseInfo(String name);

    public ArrayList<PlayerVO> getPlayerBaseInfoForVague(String name);
}
