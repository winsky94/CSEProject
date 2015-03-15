package presentation.teamui.cardmode;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;






import javax.swing.JLabel;

import presentation.mainui.ContentPanel;
import presentation.mainui.MainFrame;
import presentation.mainui.MainPanel;

public class TeamIndex extends ContentPanel{
	private JButton backbtn;
	private ImageIcon ic;
	
	public TeamIndex(){
		super();
		addTitleBar();
		back=new ImageIcon("img/main/back.png");
		ic=new ImageIcon("img/main/1.jpg");
		backbtn=new JButton(ic);
		add(backbtn);
		backbtn.setBounds(x+100, y+200, 100,100 );
		backbtn.addMouseListener(new MouseListener() {
			
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
			
				frame.refresh(new MainPanel());
				
			}
		});
		
		
	}
	


}
