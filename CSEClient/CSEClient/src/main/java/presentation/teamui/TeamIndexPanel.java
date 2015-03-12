package presentation.teamui;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import presentation.teamui.cardmode.TeamCardIndexPanel;

public class TeamIndexPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TeamIndexPanel() {
		this.setLayout(new GridLayout(1,1));
		this.add(new TeamCardIndexPanel());
	}
	public static void main(String args[]){
		JFrame jf=new JFrame();
		jf.setBounds(100,100,1162,679);
		jf.setVisible(true);
		jf.add(new TeamIndexPanel());
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
