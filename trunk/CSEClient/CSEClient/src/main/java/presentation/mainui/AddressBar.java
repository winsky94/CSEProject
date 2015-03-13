package presentation.mainui;

import java.util.HashMap;

import javax.swing.JFrame;
/*
 * author: jin
 * 保存地址栏
 * 
 * 
 * */
public class AddressBar {
	private HashMap<String,JFrame>  address;
	private final int MAXSIZE=7;//最多可显示7个地址？
	public AddressBar(MainFrame frame){
		address=new HashMap<String,JFrame>();
		//===初始化添加首页
		address.put("首页", frame);
		
	}
	
	
	
	

}
