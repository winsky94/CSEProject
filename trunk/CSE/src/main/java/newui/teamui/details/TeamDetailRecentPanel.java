package newui.teamui.details;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import bl.player.Player;
import bl.team.Team;
import blservice.PlayerBLService;
import blservice.TeamBLService;
import newui.matchui.TinyCard;
import vo.MatchVO;


public class TeamDetailRecentPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PlayerBLService pp;
	String abbrName;
	TeamBLService team;
	ArrayList<MatchVO> matches=new ArrayList<MatchVO>();
	public TeamDetailRecentPanel(String pName,String tName){
		pp=new Player();
		setBackground(Color.white);
		matches=pp.getRecentMatches(pName,5);
		//-------------------
		setLayout(new GridLayout(1,matches.size()));
		double pre=System.currentTimeMillis();
		for(int i=0;i<matches.size();i++)
			add(new TinyCard(matches.get(i),tName));
		double post=System.currentTimeMillis();
		System.out.println("FiveTinyCard:"+(post-pre));
		
		
	}
	
	public TeamDetailRecentPanel(String abbrName){
		team=new Team();
		setBackground(Color.white);
		matches=team.getRecentMatches(abbrName);
		//-------------------
		setLayout(new GridLayout(1,matches.size()));
		for(int i=0;i<matches.size();i++)
			add(new TinyCard(matches.get(i),abbrName));
	}
	public static void main(String[] args){
		JFrame f=new JFrame();
		f.setBounds(100, 100, 600, 200);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(new TeamDetailRecentPanel(" "," "));
	}
}
