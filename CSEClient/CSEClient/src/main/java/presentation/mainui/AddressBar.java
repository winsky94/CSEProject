package presentation.mainui;

import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentation.teamui.cardmode.TeamIndex;
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
	private AddressBar(){
		frame=MainFrame.getInstance();
		showAddress=new ArrayList<JLabel>();
		address=new ArrayList<JLabel>();
		voice=new JLabel("音");
		min=new JLabel("小");
		close=new JLabel("关");
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
		JLabel first=new JLabel("首页》");
		showAddress.add(first);
		address.add(first);
		
		
	}
	
	
	public void RefreshAddress(String addex,final JPanel p){
		JLabel temp=new JLabel(addex);
		temp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		temp.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//地址栏 数据更新
					MainFrame.getInstance().refresh(p);
					
				
			}
		});
		address.add(temp);
		//若多余最大显示数 则表示为: 首页》...地址》地址》地址》
		if(address.size()>=MAXSIZE){
			showAddress.clear();
		}
		
		
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
