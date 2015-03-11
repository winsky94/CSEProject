package presentation.mainui;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
/*
 * 覆盖在mainFrame背景之上
 * by jin
 * 
 * */
public class ContentPanel extends JPanel {
	private JLabel player,team;
	private ImageIcon p,t;
	private double X,Y;
	private int width,height;
	private int bw=180,by=90;
	
	
	public ContentPanel(Rectangle r,int w,int h){
		
		
	    this.setOpaque(false);
		t=new ImageIcon("img/main/beforeteam.png");
		
		X=r.getX(); Y=r.getY();
		width=w;height=h;
		Image i=t.getImage().getScaledInstance(bw, by, Image.SCALE_SMOOTH);
		
		t=new ImageIcon(i);
		
		team=new JLabel(t);
		add(team);
		this.setLayout(null);
		team.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		team.setBounds((int)(X+570),(int)(Y+298), 160, 
				70);
		initView();
		
		
		
	}

	
	private void initView() {
		// TODO Auto-generated method stub
		//player.setOpaque(false);
	/*	player.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		*/
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
