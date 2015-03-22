package presentation.Mainui;

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
	protected boolean isMain=false;
	protected static AddressBar toolbar;
	protected static ArrayList<JLabel> address;//地址栏
	static int i=0;
  
	public ContentPanel(/*MainFrame frame*/){
		this.frame=MainFrame.getInstance();
		back=new ImageIcon("img/main/back.png");
		this.setOpaque(false);
		x=frame.getX();y=frame.getY();
		width=frame.getWidth();
		height=frame.getHeight();
		this.setLayout(null);
	
		address=new ArrayList<JLabel>();
	
		
	
		
	}
	
	
	public void addTitleBar(){
		toolbar=AddressBar.getInstance();/*{
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				ImageIcon bar=new ImageIcon("img/main/titlebar.png");
				g.drawImage(bar.getImage(), 0, 0, bar.getImageObserver());
			}
		};*/
			if(isMain)
				toolbar.setOpaque(false);;
		
			add(toolbar);
			toolbar.setBounds(Scale.TITLEBAR);			
		
	}
	public void paintComponent(Graphics g){
		addTitleBar();
		super.paintComponent(g);
		
		Graphics2D gg=(Graphics2D) g;
		if(back!=null){
			gg.drawImage(back.getImage(), 0,0,
					width,height,this);
		}

	};
	
	
	
	
}
