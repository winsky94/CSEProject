package presentation.mainui;

/*2015.3.9 wangning 创建
 * 2015.3.10 wangning 界面分区
 * 
 * 
 * 
 */
import java.awt.Color;
import java.awt.Cursor;
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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import com.sun.awt.AWTUtilities;


import presentation.UIhelper;

/*bug1: 界面可以完全移动到 边界直至消失
 *
 * 
 * */
public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static int screenWidth;
	static int screenHeight;
	static int frameWidth;
	static int frameHeight;
	private int fx,fy;//实时坐标位置
	//private Image img;
	private Point origin;
	JPanel titlePnl, contentPnl= new JPanel();
	private JPanel backpanel;
	private ImageIcon ic;
	private static MainFrame instance=null;
	
	
	public MainFrame(final ImageIcon ic) {
		// ------设置窗口大小、位置---------
		screenWidth = UIhelper.getScreenWidth();
		screenHeight = UIhelper.getScreenHeight();
		frameWidth = screenWidth * 85 / 100;
		frameHeight = screenHeight * 90 / 100;
		
		this.setBounds((screenWidth - frameWidth) / 2,
				(screenHeight - frameHeight) / 2, frameWidth, frameHeight);
		this.ic=ic;
		this.setTitle("NBA信息查询平台");
		
		this.setIconImage(new ImageIcon("img/main/logo.jpg").getImage());
		
		//====不规则窗体背景======
	//	MediaTracker mt=new MediaTracker(this);
		// img=Toolkit.getDefaultToolkit().createImage("img/main/backmain.png");
	//	  ic=new ImageIcon("img/main/backmain.png");

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
	            fx=p.x + e.getX() - origin.x;
	            fy=p.y + e.getY() - origin.y;
	            setLocation(p.x + e.getX() - origin.x, p.y + e.getY() - origin.y );
	          }          
	        }
	    );  
		
	 
		
	
	this.setLayout(null);
	this.setVisible(true);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	  

	

	public void refresh(JPanel panel){
		MainFrame.this.setContentPane(panel);
		MainFrame.this.getContentPane().revalidate();;
	}
	


	public static MainFrame getInstance(){
		if(instance==null){
			instance=new MainFrame(new ImageIcon("img/main/backmain.png"));
		}
		return instance;
	}
	
	public void setInstance(MainFrame frame){
		MainFrame.this.instance=frame;
	}
	
	
	

	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame(new ImageIcon("img/main/backmain.png"));
		//=====会明显有组件变动的痕迹  肿么办=========
		mainFrame.setInstance(mainFrame);
		mainFrame.refresh(new MainPanel());
	}

}
