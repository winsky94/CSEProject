package dataservice;

import java.util.ArrayList;
import java.util.Map;

import vo.PlayerVO;

public interface PlayerDataService{
	/**
	 * 
	 * @return 历史上所有球员的基础数据（包括当季球员）
	 */
	public Map<String, PlayerVO> getPlayerHistoricBaseInfo();
	
	/**
	 * 
	 * @return 当季所有球员的基础数据
	 */
	public Map<String, PlayerVO> getPlayerActiveBaseInfo();

	/**
	 * 准确查找
	 * @param name
	 * @return 根据准确的名字返回该球员
	 */
	public PlayerVO getPlayerBaseInfo(String name);

	/**
	 * 模糊查找
	 * @param name
	 * @return  根据名字（模糊）返回符合条件的所有球员
	 */
    public ArrayList<PlayerVO> getPlayerBaseInfoForVague(String name);
}
