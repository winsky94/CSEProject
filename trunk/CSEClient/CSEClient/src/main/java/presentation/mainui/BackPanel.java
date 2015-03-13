package presentation.mainui;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BackPanel extends JPanel{
	private JLabel player,team;
	private ImageIcon p,t;
	private double X,Y;
	private int width,height;
	private int bw=180,by=90;
	private JPanel pane;
	private MainFrame frame;
	
	
	public BackPanel(MainFrame frame){
		
		this.frame=frame;
		t=new ImageIcon("img/main/beforeteam.png");
		p=new ImageIcon("img/main/beforeplay.png");
		//====关键===
		pane=(JPanel) frame.getContentPane();
		X=frame.getX(); Y=frame.getY();
		width=frame.getWidth();height=frame.getHeight();
		Image i=t.getImage().getScaledInstance(bw, by, Image.SCALE_SMOOTH);
		
		t=new ImageIcon(i);
		p.setImage(p.getImage().getScaledInstance(bw, by , Image.SCALE_DEFAULT));
		player=new JLabel(p);
		team=new JLabel(t);
		repaint();
		add(team);
		add(player);
		setLayout(null);
	    this.setOpaque(false);
		team.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		player.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		team.setBounds((int)(X+570),(int)(Y+298), 160, 
				70);
		player.setBounds((int)(X+760),(int)(Y+298), 160, 
				70);
		initView();
		
		
		
		
	}
	
	public void paintComponent(Graphics g){
	
		super.paintComponent(g);
		ImageIcon ic=new ImageIcon("img/back/backmain.png");
		Graphics2D gg=(Graphics2D) g;
		if(ic!=null){
			gg.drawImage(ic.getImage(), 0,0,
					width,height,this);
		}

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
			/*	frame.dispose();
				JFrame frame=new JFrame();
				PlayerIndexPanel index=new PlayerIndexPanel();
				frame.add(index);
				frame.setVisible(true);
				frame.setLocation(100,100);
				frame.setSize(1000, 800);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/
				frame.RefreshBack(new ImageIcon("img/main/backmain.png"));
				
				
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
