package presentation;
/*2015.3.9 wangning 创建
 * 
 * 
 * 
 * 
 */
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int screenWidth,screenHeight,frameWidth,frameHeight;
	JPanel pnl;
	public MainFrame(){
		//------设置窗口大小、位置---------
		screenWidth=UIhelper.getScreenWidth();
		screenHeight=UIhelper.getScreenHeight();
		frameWidth=screenWidth*85/100;
		frameHeight=screenHeight*90/100;
		this.setBounds((screenWidth-frameWidth)/2,(screenHeight-frameHeight)/2, frameWidth, frameHeight);
		//-----------------------------
		
		this.setUndecorated(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		MainFrame mainFrame=new MainFrame();

	}

}
