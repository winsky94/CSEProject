package presentation.mainui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class ContentPanel extends JPanel {
	protected int x,y;
	protected int width,height;
	protected JPanel pane;
	protected MainFrame frame;
	protected ImageIcon back;//操作背景
	protected JPanel toolbar;
	protected ArrayList<JLabel> address;//地址栏
	public ContentPanel(MainFrame frame){
		back=new ImageIcon("img/main/back.png");
		this.setOpaque(false);
		x=frame.getY();y=frame.getY();
		width=frame.getWidth();
		height=frame.getHeight();
		this.setLayout(null);
		this.frame=frame;
		address=new ArrayList<JLabel>();
	
		
	}
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		Graphics2D gg=(Graphics2D) g;
		if(back!=null){
			gg.drawImage(back.getImage(), 0,0,
					width,height,this);
		}

	};
	
	
	
	public void RefreshAddress(String addex,JFrame f){
		
		
	}
}
