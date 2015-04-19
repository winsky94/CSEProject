package newui.playerui.details;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import newui.teamui.details.TeamDetailRecentPanel;
import bl.player.Player;
import blservice.PlayerBLService;
import vo.PlayerVO;

public class PlayerDetailInfoPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel headPnl, infoPnl, recentPnl;
	PlayerVO vo;
	PlayerBLService pservice;
	public PlayerDetailInfoPanel(PlayerVO vo) {
		this.vo=vo;
		// -----------------
		pservice=new Player();
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		setLayout(gbl);
		// ------headPnl------------
		headPnl = new JPanel();
		headPnl.setOpaque(false);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 10;
		gbc.gridheight = 2;
		gbc.weightx = 10;
		gbc.weighty = 2;
		gbl.setConstraints(headPnl, gbc);
		add(headPnl);
		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.LEFT);
		fl.setHgap(20);
		headPnl.setLayout(fl);
		// ------------------
		ImageIcon icon=pservice.getPlayerPortraitImage(vo.getName());
		JLabel portraitLbl = new JLabel(icon);
		headPnl.add(portraitLbl);
		JLabel nameLbl = new JLabel(vo.getName());
		nameLbl.setFont(new Font("微软雅黑", Font.PLAIN, 28));
		headPnl.add(nameLbl);
		// -----infoPnl---------------
		infoPnl = new JPanel();
		infoPnl.setOpaque(false);
		gbc.gridy = 2;
		gbc.gridheight = 6;
		gbc.weighty = 6;
		gbl.setConstraints(infoPnl, gbc);
		add(infoPnl);
		//------------------
		infoPnl.setLayout(new GridLayout(4,2));
		MyLabel numLbl=new MyLabel("球衣号码："+vo.getNumber());
		infoPnl.add(numLbl);
		MyLabel positionLbl=new MyLabel("位置："+vo.getPosition());
		infoPnl.add(positionLbl);
		MyLabel heightLbl=new MyLabel("身高（英尺-英寸）："+vo.getHeight());
		infoPnl.add(heightLbl);
		MyLabel weightLbl=new MyLabel("体重（英镑）:"+vo.getWeight());
		infoPnl.add(weightLbl);
		MyLabel birthLbl=new MyLabel("生日："+vo.getBirth());
		infoPnl.add(birthLbl);
		MyLabel ageLbl=new MyLabel("年龄："+vo.getAge());
		infoPnl.add(ageLbl);
		MyLabel expLbl=new MyLabel("球龄："+vo.getExp());
		infoPnl.add(expLbl);
		MyLabel schoolLbl=new MyLabel("毕业学校："+vo.getSchool());
		infoPnl.add(schoolLbl);
		// -------recentPnl-----------
		/*
		 * 注意：加监听时把下一行的注释解除，传入其所属的球队
		 * 删除下面的recentPnl=new JPanel();
		 */
		recentPnl = new TeamDetailRecentPanel(vo.getName(),vo.getOwingTeam());
		//recentPnl=new JPanel();
		gbc.gridy = 8;
		gbc.gridheight = 2;
		gbc.weighty = 2;
		gbl.setConstraints(recentPnl, gbc);
		add(recentPnl);
	}
	class MyLabel extends JLabel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public MyLabel(String t){
			super(t);
			setFont(new Font("微软雅黑",Font.PLAIN,15));
		}
		
	}
}
