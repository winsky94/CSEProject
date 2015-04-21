package newui.playerui;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import newui.FatherPanel;
import newui.UIhelper;
import newui.playerui.details.PlayerDetailHistoryPanel;
import newui.playerui.details.PlayerDetailInfoPanel;
import vo.PlayerVO;
import bl.player.Player;
import blservice.PlayerBLService;

public class PlayerDetailPanel extends FatherPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	JLabel actionPicLbl;
	JTabbedPane tab;
	PlayerBLService pservice;
	PlayerVO vo;
	public PlayerDetailPanel(String pname){
		isDetail=true;
		pservice=new Player();// is this? 
		vo=pservice.getPlayerBaseInfo(pname).get(0);
		
		name=pname;
		//----------------
		//ImageIcon icon=pservice.getPlayerActionImage(pname);
		//设置icon大小
		int screenWidth = UIhelper.getScreenWidth();
		int screenHeight=UIhelper.getScreenHeight();
		int width=screenWidth*90/100;
		int height=screenHeight*95/100;
		
		
		//icon.setImage(icon.getImage().getScaledInstance(width*1/3, height*7/8, Image.SCALE_DEFAULT));
		ImageIcon i=new ImageIcon("image/player/action/"+name+".png");
		
	
		
		actionPicLbl=new JLabel(i/*new ImageIcon("image/player/action/"+name+".png")*/);
		gbc.gridx=0;
		gbc.gridy=1;
		gbc.gridwidth=1;
		gbc.gridheight=10;
		gbc.weightx=0.1;
		gbc.weighty=100;
		gbl.setConstraints(actionPicLbl, gbc);
		add(actionPicLbl);
		//----------------
		tab=new JTabbedPane();
		gbc.gridx=1;
		gbc.gridwidth=9;
		gbc.weightx=500;
		gbl.setConstraints(tab, gbc);
		add(tab);
		double pre=System.currentTimeMillis();
		tab.addTab("基本信息", new PlayerDetailInfoPanel(vo));
		double pp=System.currentTimeMillis();
		System.out.println("detaibaseinfo:"+(pp-pre));
		tab.addTab("过往数据",new PlayerDetailHistoryPanel(vo));
		double post=System.currentTimeMillis();
		System.out.println("detailhistory:"+(post-pp));
	}
	
}
