package presentation.playerui.detail;

import java.awt.Color;

import javax.swing.JPanel;


import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import businesslogic.Player;
import vo.PlayerVO;
/*
 * 大背景 
 * 左侧球员找
 * 右侧 tab1基本信息  球队 半身照 和球队信息  和身高体重
 *    tab2场均数据  选择 赛季  
 *    tab3赛季总数据 选择 赛季
 * */
public class PlayerInfoPanel extends ContentPanel{
	private JPanel leftImage;//左侧全身照
	private JTabbedPane rightInfo;
	private JPanel base,average,season;
	private JPanel baseInfo;
	//private Player player=new Player();
	public PlayerInfoPanel(PlayerVO vo){
		addTitleBar();
		leftImage=new JPanel();
		//back=new ImageIcon();//背景
		base=new JPanel();
		average=new JPanel();
		season=new JPanel();
		rightInfo=new JTabbedPane(SwingConstants.TOP);
		rightInfo.add("基本信息", base);
		rightInfo.add("场均数据", average);
		rightInfo.add("赛季总数据",season);
		this.setLayout(null);
		add(leftImage);
		leftImage.setBounds(Scale.LeftImage);
		add(rightInfo);
		rightInfo.setBounds(Scale.RightInfo);
		baseInfo=new JPanel();
		baseInfo.setBackground(Color.pink);
		base.setLayout(null);
		base.add(baseInfo);
		baseInfo.setBounds(Scale.BaseInfo);
		
		
		
		
		
		
	}
	
	
	public void initAverage(){
		
	}
	
	public void initSeason(){
		
	}
	
	public void initBase(){
		
	}
	public static void main(String[] args) {
		MainFrame.getInstance().refresh(new PlayerInfoPanel(null));
	}

}
