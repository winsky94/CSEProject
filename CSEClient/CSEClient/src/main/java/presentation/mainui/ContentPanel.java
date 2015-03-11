package presentation.mainui;

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
	
	
	public ContentPanel(Rectangle r){
		
		
	    this.setOpaque(false);
		t=new ImageIcon("img/main/beforeteam.png");
		team=new JLabel(t);
		add(team);
		X=r.getX(); Y=r.getY();
		
		
		team.setBounds((int)(X+X/3), (int)(Y+Y/2), t.getImage().getWidth(t.getImageObserver()), 
				t.getImage().getHeight(t.getImageObserver()));
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
				team.setIcon(t);
			}
			
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				t=new ImageIcon("img/main/enterteam.png");
				team.setIcon(t);
			}
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
	}
	
	

}
