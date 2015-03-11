package presentation.mainui;

/*2015.3.9 wangning 创建
 * 2015.3.10 wangning 界面分区
 * 
 * 
 * 
 */
import java.awt.Color;
import java.awt.Graphics;
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
		 ImageIcon ic=new ImageIcon(img);
		 
		img=img.getScaledInstance(getWidth(),getHeight(),Image.SCALE_DEFAULT);
		 
		 
		 mt.addImage(img, 0);
		 try {
			mt.waitForAll();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	

		//=====
		 this.origin=new Point();
		 this.setUndecorated(true);
		//调用AWTUtilities的setWindowShape方法设定本窗体为制定的Shape形状
		AWTUtilities.setWindowShape(this,getImageShape(img));        
		this.setLocationRelativeTo(null);
	    
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
		
		
		
		
	 //   this.add(titlePnl);
	   // this.add(contentPnl);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	public Shape getImageShape(Image img) {
	    ArrayList<Integer> x=new ArrayList<Integer>();
	    ArrayList<Integer> y=new ArrayList<Integer>();    
	    int width=img.getWidth(null);//图像宽度
	    int height=img.getHeight(null);//图像高度

	    //筛选像素
	    //首先获取图像所有的像素信息
	    PixelGrabber pgr = new PixelGrabber(img, 0, 0, -1, -1, true);
	    try {
	      pgr.grabPixels();
	    } catch (InterruptedException ex) {
	      ex.getStackTrace();
	    }
	    int pixels[] = (int[]) pgr.getPixels();
	    
	    //循环像素
	    for (int i = 0; i < pixels.length; i++) {
	      //筛选，将不透明的像素的坐标加入到坐标ArrayList x和y中      
	      int alpha = getAlpha(pixels[i]);
	      if (alpha == 0){
	        continue;        
	      }else{
	        x.add(i%width>0 ? i%width-1:0);
	        y.add(i%width==0 ? (i==0 ? 0:i/width-1):i/width);
	      }      
	    }
	    
	    //建立图像矩阵并初始化(0为透明,1为不透明)
	    int[][] matrix=new int[height][width];    
	    for(int i=0;i<height;i++){
	      for(int j=0;j<width;j++){
	        matrix[i][j]=0;
	      }
	    }
	    
	    //导入坐标ArrayList中的不透明坐标信息
	    for(int c=0;c<x.size();c++){
	      matrix[y.get(c)][x.get(c)]=1;
	    }
	    
	    /* 由于Area类所表示区域可以进行合并，我们逐一水平"扫描"图像矩阵的每一行，
	     * 将不透明的像素生成为Rectangle，再将每一行的Rectangle通过Area类的rec
	     * 对象进行合并，最后形成一个完整的Shape图形
	     */
	    Area rec=new Area();
	    int temp=0;
	    
	    for(int i=0;i<height;i++){
	      for(int j=0;j<width;j++){
	        if(matrix[i][j]==1){
	          if(temp==0)
	            temp=j;  
	          else if(j==width){
	            if(temp==0){
	              Rectangle rectemp=new Rectangle(j,i,1,1);
	              rec.add(new Area(rectemp));
	            }else{
	              Rectangle rectemp=new Rectangle(temp,i,j-temp,1);
	              rec.add(new Area(rectemp));
	              temp=0;
	            }
	          }
	        }else{
	          if(temp!=0){
	            Rectangle rectemp=new Rectangle(temp,i,j-temp,1);
	            rec.add(new Area(rectemp));
	            temp=0;
	          }
	        }
	      }
	      temp=0;
	    }
	    return rec;
	  }

	  
	  /**
	    * 获取像素的Alpha值
	    * @param pixel
	    * @return 像素的Alpha值
	    */
	  private int getAlpha(int pixel) {
	    return (pixel >> 24) & 0xff;
	  }
	  

	@Override
	  public void paint(Graphics g) {
	    super.paint(g);
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
