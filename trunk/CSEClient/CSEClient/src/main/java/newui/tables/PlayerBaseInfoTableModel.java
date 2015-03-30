package newui.tables;

import java.util.ArrayList;

import javax.swing.ImageIcon;

import vo.PlayerVO;
import businesslogic.Player;

public class PlayerBaseInfoTableModel extends MyTableModel {

	
	private static final long serialVersionUID = 1L;
	static String[] head = { "(头像)", "球员名", "所属球队", "位置", "身高", "体重","生日","年龄" ,"经验" };
	ArrayList<ArrayList<Object>> content = new ArrayList<ArrayList<Object>>();
	private Player player;
	private ArrayList<ImageIcon> imgList=new ArrayList<ImageIcon>();
	private ArrayList<PlayerVO> playerlist;
	public PlayerBaseInfoTableModel() {
		player=new Player();
		playerlist=new ArrayList<PlayerVO>();
	}

	public int getRowCount() {
		return content.size();
	}

	public int getColumnCount() {
		return head.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return content.get(rowIndex).get(columnIndex);
	}

	public String getColumnName(int col) {
		return head[col];
	}

	public static String[] getHead() {
		return head;
	}
	
	
	public void Refresh(){
		playerlist=player.getPlayerBaseInfo();
		if(playerlist==null||playerlist.size()==0){
			//显示没有符合消息的数据
			}
		else
			Refresh(playerlist);
		
	}
	//图片加载过慢1.每加载一条完整或一定数量行记录 即执行渲染或Revalidate
	//long a=System.currentTimeMillis();
	//
	//System.out.println("\r<br>执行耗时 : "+(System.currentTimeMillis()-a)/1000f+" 秒 ");
	//	static String[] head =
	//{ "(头像)", "球员名", "所属球队", "位置", "身高", "体重","生日","年龄" ,"经验" };
	//适应出网络连接方式来的数据
	public void Refresh(ArrayList<PlayerVO>  list){
		content.clear();
		imgList.clear();
		
		for(PlayerVO vo:list){
			ArrayList<Object> line=new ArrayList<Object>();
			String name=vo.getName();
			ImageIcon tou=player.getPlayerPortraitImage(name);
			imgList.add(tou);
			line.add(tou);
			line.add(name);
			line.add(vo.getTeamName());
			line.add(vo.getPosition());
			line.add(vo.getHeight());
			line.add(vo.getWeight());
			line.add(vo.getBirth());
			line.add(vo.getAge());
			line.add(vo.getExp());
			content.add(line);
			
		}
		
		
	}
	
	public ArrayList<ImageIcon> getImgList(){
		return imgList;
	}
	
}
