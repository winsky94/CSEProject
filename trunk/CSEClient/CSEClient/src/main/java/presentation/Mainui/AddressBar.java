package presentation.Mainui;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
/*
 * author: jin
 * 保存地址栏
 * 
 * 
 * */
public class AddressBar extends JPanel implements MouseListener{

	private final int MAXSIZE=7;//最多可显示7个地址？
	private static AddressBar instance=null;
	private static ArrayList<JLabel>  address;//标签显示存储地址
	private ArrayList<JLabel> showAddress;//显示的框架
	private static JLabel voice,min,close;
	private JLabel more=new JLabel("....");
	private MainFrame frame;
	private JLabel first;
	private AddressBar(){
		frame=MainFrame.getInstance();
		showAddress=new ArrayList<JLabel>();
		address=new ArrayList<JLabel>();
		ImageIcon musicImg=new ImageIcon("img/main/music.png");
		musicImg.setImage(musicImg.getImage().getScaledInstance(12, 12, Image.SCALE_DEFAULT));
		voice=new JLabel(musicImg);
		ImageIcon minImg = new ImageIcon("img/main/min.png"); 
		minImg.setImage(minImg.getImage().getScaledInstance(13,13,Image.SCALE_DEFAULT)); 
		min=new JLabel(minImg);
		ImageIcon exitImg = new ImageIcon("img/main/exit.png"); 
		exitImg.setImage(exitImg.getImage().getScaledInstance(11,11,Image.SCALE_DEFAULT)); 
		close=new JLabel(exitImg);
		setLayout(null);
		add(voice);
		add(min);
		add(close);
		voice.setBounds(Scale.VOICE);
		min.setBounds(Scale.MIN);
		close.setBounds(Scale.CLOSE);
		voice.addMouseListener(this);
		min.addMouseListener(this);
		close.addMouseListener(this);
		voice.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		min.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		close.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		//=======初始化地址栏数据====
		first=new JLabel("首页》");
		first.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		//showAddress.add(first);
		//address.add(first);
		
		
	}
	protected void paintComponent(Graphics g) {
		ImageIcon icon = new ImageIcon("img/main/TitleBar.png");
		Image img = icon.getImage();
		g.drawImage(img, 0,0, icon.getIconWidth(),
				icon.getIconHeight(), icon.getImageObserver());
	}
	//===bug  bug又有bug了==========
	public void RefreshAddress(String addex, final JPanel p){
	final JLabel temp=new JLabel(addex);
		temp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		temp.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//地址栏 数据更新
					//int index1=showAddress.indexOf(temp);
					int index2=address.indexOf(temp);
					//for(int i=index1;i<showAddress.size();i++)j
					//	showAddress.remove(i);
					for(int i=index2;i<address.size();i++)
						address.remove(i);
					paintAddress();
					p.revalidate();
					MainFrame.getInstance().refresh(p);				
			}
		});
		if(addex.equals("首页》"))
			first=temp;
		address.add(temp);
		//若多余最大显示数 则表示为: 首页》...地址》地址》地址》
	
		paintAddress();
		
		
	}
	
	public void paintAddress(){
		for(JLabel old:showAddress)
			remove(old);
		showAddress.clear();
		if(address.size()>=MAXSIZE){
			
			showAddress.add(first);showAddress.add(more);
			int start=address.size()-MAXSIZE+1;
			for(int i=start;i<address.size();i++)
				showAddress.add(address.get(i));
			
		}else{
			int i=address.size();
			if(i>0){
				for(JLabel temp:address)
				showAddress.add(temp);
			}				
		}
		
		//===添加到TitleBar上
		int i=0;
		for(JLabel add:showAddress){
			add(add);
			add.setBounds(0,i*100,100,35);
			i++;
		}
		revalidate();
		
	}

	public static AddressBar getInstance(){
		if(instance==null){
			instance=new AddressBar();
			
		}
		instance.setOpaque(true);
		return instance;
	}


	
	
	//======功能键事件响应
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		switch(getType(e)){
		case MIN:
			 frame.setExtendedState(JFrame.HIDE_ON_CLOSE);break;
		case CLOSE:
			System.exit(0);
			
		}
	}


	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private enum ButtonType{
		MIN,CLOSE,VOICE
	}
	
	public  ButtonType  getType(MouseEvent e){
		if(e.getSource()==min)
			return ButtonType.MIN;
		else if(e.getSource()==close)
			return ButtonType.CLOSE;
		else
			return ButtonType.VOICE;
		
	}
	
	

}
