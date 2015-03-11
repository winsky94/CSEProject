package presentation.mainui;

/*2015.3.9 wangning 创建
 * 2015.3.10 wangning 界面分区
 * 
 * 
 * 
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;


import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Area;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.PixelGrabber;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.awt.AWTUtilities;

import presentation.IndexPanel;
import presentation.UIhelper;
import presentation.teamui.TeamIndexPanel;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int screenWidth, screenHeight, frameWidth, frameHeight;
	private Image img;
	private Point origin;
	JPanel titlePnl, contentPnl= new JPanel();
	
	public MainFrame() {
		// ------设置窗口大小、位置---------
		screenWidth = UIhelper.getScreenWidth();
		screenHeight = UIhelper.getScreenHeight();
		frameWidth = screenWidth * 85 / 100;
		frameHeight = screenHeight * 90 / 100;
		this.setBounds((screenWidth - frameWidth) / 2,
				(screenHeight - frameHeight) / 2, frameWidth, frameHeight);
		// --------分配Panel-------------
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		this.setLayout(gbl);
		// ---------title---------------
		titlePnl = new JPanel();
		titlePnl.setBackground(Color.white);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 7;
		gbc.gridheight = 1;
		gbc.weightx = 10;
		gbc.weighty = 0.3;
		gbl.setConstraints(titlePnl, gbc);
		
		setPanel(new IndexPanel());
		//-------content----------------
		contentPnl = new JPanel();
		gbc.gridy = 1;
		gbc.gridheight =12;
		gbc.weightx = 10;
		gbc.weighty = 13;
		gbl.setConstraints(contentPnl, gbc);
		
		setPanel(new IndexPanel());
		//
		
		
		this.setIconImage(new ImageIcon("img/main/logo.jpg").getImage());
		
		//====不规则窗体背景======
		 MediaTracker mt=new MediaTracker(this);
		 img=Toolkit.getDefaultToolkit().createImage("img/main/backmain.png");
		 final ImageIcon ic=new ImageIcon(img);
		 
		//img=img.getScaledInstance(getWidth(),getHeight(),Image.SCALE_DEFAULT);
		 
	

	

		//=====
		 this.origin=new Point();
		 this.setUndecorated(true);
	
		AWTUtilities.setWindowOpaque(this, false);

	
	    
	    //由于取消了默认的窗体结构，所以我们要手动设置一下移动窗体的方法
	   addMouseListener( 
	        new MouseAdapter(){
	          public void mousePressed(MouseEvent e){
	            origin.x = e.getX();
	            origin.y = e.getY();
	          }
	          //窗体上单击鼠标右键关闭程序
	          public void mouseClicked(MouseEvent e) {
	            if(e.getButton()==MouseEvent.BUTTON3)
	              System.exit(0);
	          }
	          public void mouseReleased(MouseEvent e) {
	            super.mouseReleased(e);
	          }
	          @Override
	          public void mouseEntered(MouseEvent e) {
	            repaint();              
	          }            
	        }
	    );

	    addMouseMotionListener(
	        new MouseMotionAdapter(){
	          public void mouseDragged(MouseEvent e){
	            Point p = getLocation();
	            setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y );
	          }          
	        }
	    );  
		
		
		
		
	  this.setLayout(null);
	  ContentPanel c=new ContentPanel(this.getBounds()){
		  public void paintComponent(Graphics g){
				super.paintComponent(g);
				Graphics2D gg=(Graphics2D) g;
				if(ic!=null){
					gg.drawImage(ic.getImage(), 0,0,
							frameWidth,frameHeight,MainFrame.this);
				}
				
				
				
			}
	  };
	  setContentPane(c);
	 
	 // c.setBounds(getBounds());
	  
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	  

	public void paintComponent(Graphics g) {
		super.paintComponents(g);
	    
	    g.drawImage(img, 0, 0, null);
	   
	  }


	public void setPanel(JPanel pnl) {
		contentPnl.removeAll();
		contentPnl.setLayout(new GridLayout(1, 1));
		contentPnl.add(pnl);
	}

	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();
		//mainFrame.setPanel(new TeamIndexPanel());
	}

}
