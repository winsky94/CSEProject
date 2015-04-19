package newui.playerui;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import vo.PlayerVO;
import bl.player.Player;
import blservice.PlayerBLService;
import newui.FatherPanel;
import newui.UIhelper;
import newui.playerui.details.PlayerDetailHistoryPanel;
import newui.playerui.details.PlayerDetailInfoPanel;

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
		gbc.weightx=1;
		gbc.weighty=100;
		gbl.setConstraints(actionPicLbl, gbc);
		add(actionPicLbl);
		//----------------
		tab=new JTabbedPane();
		gbc.gridx=1;
		gbc.gridwidth=9;
		gbc.weightx=50;
		gbl.setConstraints(tab, gbc);
		add(tab);
		tab.addTab("基本信息", new PlayerDetailInfoPanel(vo));
		tab.addTab("过往数据",new PlayerDetailHistoryPanel(vo));
	}
	public static void main(String[] args) {
		JFrame f=new JFrame();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(50, 50, 1100, 670);
		f.add(new PlayerDetailPanel("Kobe Bryant"));
		f.repaint();
	}
}
