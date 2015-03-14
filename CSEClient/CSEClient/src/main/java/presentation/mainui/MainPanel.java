package presentation.mainui;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentation.playerui.PlayerIndexPanel;
import presentation.teamui.cardmode.TeamIndex;
/*
 * 覆盖在mainFrame背景之上
 * by jin
 * 
 * */
public class MainPanel extends ContentPanel{
	private JLabel player,team;
	private ImageIcon p,t;
	private int bw=180,by=90;
	private JPanel pane;
	private MainFrame frame;
	
	
	public MainPanel(MainFrame frame){
		super(frame);
	//	this.setOpaque(false);
		back=new ImageIcon("img/main/backmain.png");
		this.frame=frame;
		t=new ImageIcon("img/main/beforeteam.png");
		p=new ImageIcon("img/main/beforeplay.png");
		//====关键===
		pane=(JPanel) frame.getContentPane();
	
		Image i=t.getImage().getScaledInstance(bw, by, Image.SCALE_SMOOTH);
		
		t=new ImageIcon(i);
		p.setImage(p.getImage().getScaledInstance(bw, by , Image.SCALE_DEFAULT));
		player=new JLabel(p);
		team=new JLabel(t);
		//pane.add(team);
		//pane.add(player);
		//pane.setLayout(null);
		add(team);
		add(player);
		setLayout(null);
		team.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		player.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		team.setBounds((int)(x+570),(int)(y+298), 160, 
				70);
		player.setBounds((int)(x+760),(int)(y+298), 160, 
				70);
		initView();
		
		
		
	}
	
	
	private void initView() {
		// TODO Auto-generated method stub
		//player.setOpaque(false);
		player.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				p=new ImageIcon("img/main/beforeplay.png");
				p.setImage(p.getImage().getScaledInstance(bw, by, Image.SCALE_DEFAULT));
				player.setIcon(p);
			}
			
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				p=new ImageIcon("img/main/afterplay.png");
				p.setImage(p.getImage().getScaledInstance(bw, by, Image.SCALE_DEFAULT));
				player.setIcon(p);
			}
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
	
				frame.refresh(new TeamIndex(frame));
				
			}
		});
		
		team.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				t=new ImageIcon("img/main/beforeteam.png");
				t.setImage(t.getImage().getScaledInstance(bw, by, Image.SCALE_DEFAULT));
				team.setIcon(t);
			}
			
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				t=new ImageIcon("img/main/enterteam.png");
				t.setImage(t.getImage().getScaledInstance(bw, by, Image.SCALE_DEFAULT));
				team.setIcon(t);
			}
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
				
				
				
			}
		});
		
		
	}
	
	

}
