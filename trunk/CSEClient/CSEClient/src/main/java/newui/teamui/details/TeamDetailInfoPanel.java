package newui.teamui.details;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import vo.TeamVO;
import businesslogic.Team;
import businesslogicservice.TeamBLService;

public class TeamDetailInfoPanel extends JPanel{

	/**
	 * 球队详情之基本信息
	 */
	private static final long serialVersionUID = 1L;
	String chName,enName,abbrName;
	TeamBLService team;
	TeamVO basevo;
	public TeamDetailInfoPanel(String ch,String abbr){
		//-----名字获取及VO生成----------
		team=new Team();
		chName=ch;
		abbrName=abbr;
		basevo=team.getTeamBaseInfo(abbr).get(0);
		enName=basevo.getTeamName();
		//----------------------------
		GridBagLayout gbl=new GridBagLayout();
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.fill=GridBagConstraints.BOTH;
		setLayout(gbl);
		//----------------------------
	}
	@Override
	protected void paintComponent(Graphics g) {
		ImageIcon icon = new ImageIcon("image/teamIcon/teamBase.png");
		Image img = icon.getImage();
		g.drawImage(img, 0, 0, icon.getImageObserver());
	}
}
