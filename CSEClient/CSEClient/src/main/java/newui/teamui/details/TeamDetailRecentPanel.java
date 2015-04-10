package newui.teamui.details;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vo.MatchVO;
import businesslogic.Team;
import businesslogicservice.TeamBLService;

public class TeamDetailRecentPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TeamBLService team;
	String abbrName;
	ArrayList<MatchVO> matches=new ArrayList<MatchVO>();
	public TeamDetailRecentPanel(String abbrName){
		team=new Team();
		matches=team.getRecentMatches(abbrName);
		//-------------------
		setLayout(new GridLayout(1,5));
		
		
	}
	public static void main(String[] args){
		JFrame f=new JFrame();
		f.setBounds(100, 100, 600, 200);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new TeamDetailRecentPanel("MEM"));
	}
}
