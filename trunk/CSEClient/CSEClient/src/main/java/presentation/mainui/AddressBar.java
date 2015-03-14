package presentation.mainui;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
public class AddressBar extends JPanel{

	private final int MAXSIZE=7;//最多可显示7个地址？
	private static AddressBar instance=null;
	private static ArrayList<JLabel>  address;//标签显示存储地址
	private JLabel voice,min,close;
	private AddressBar(){
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
		
		
		
		
	}
	
	
	public void RefreshAddress(String addex,JPanel p,MainFrame frame){
		JLabel temp=new JLabel(addex);
		temp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		temp.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
					
				
			}
		});
		
		
	}
	
	
	public static AddressBar getInstance(){
		if(instance==null){
			instance=new AddressBar();
			
		}
		return instance;
	}
	
	
	

}
