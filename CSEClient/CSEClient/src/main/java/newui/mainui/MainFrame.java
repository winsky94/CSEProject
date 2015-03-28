package newui.mainui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import newui.TopPanel;
import newui.UIhelper;


public class MainFrame extends JFrame{
	/**
	 * 需：去掉外框，增加移动位置代码
	 */
	private static final long serialVersionUID = 1L;
	private static MainFrame instance=null;
	int screenWidth=UIhelper.getScreenWidth();
	int screenHeight=UIhelper.getScreenHeight();
	int width=screenWidth*90/100;
	int height=screenHeight*95/100;
	JPanel topPnl;
	JPanel contentPnl=new JPanel();
	private MainFrame(){
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill=GridBagConstraints.BOTH;
		setLayout(gbl);
		setBounds((screenWidth-width)/2,(screenHeight-height)/2,width,height);
		setIconImage(UIhelper.getImage("image/appicon.png"));
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//-------下面是布局----------------
		//-------topPnl关闭按钮与最小化-----
		topPnl=new TopPanel();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 10;
		gbc.gridheight = 1;
		gbc.weightx = 10;
		gbc.weighty = 0.01;
		gbl.setConstraints(topPnl, gbc);
		add(topPnl);
		//------contentPnl内容部分-------
		gbc.gridy =1;
		gbc.gridheight=10;
		gbc.weighty=10;
		gbl.setConstraints(contentPnl, gbc);
		add(contentPnl);
		setContentPanel(new IndexPanel());
	}
	public void setContentPanel(JPanel pnl){
		contentPnl.removeAll();
		contentPnl.setLayout(new GridLayout(1,1));
		contentPnl.add(pnl);
		contentPnl.repaint();
		contentPnl.revalidate();
	}
	public static MainFrame getInstance(){
		if(instance==null)
			instance=new MainFrame();
		return instance;
	}
	public static void main(String[] args) {
		MainFrame.getInstance();
	}
	
}
