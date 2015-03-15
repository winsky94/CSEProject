package presentation.mainui;

import java.awt.Rectangle;

public interface Scale {
	final  Rectangle TITLEBAR=new Rectangle(69,83,1000,35);//地址栏
	
	final Rectangle VOICE=new Rectangle(895,0,35,35);//声音开关
	final Rectangle MIN=new Rectangle(930,0,35,35);//最小化
	final Rectangle CLOSE=new Rectangle(965,0,35,35);//关闭
	
	final Rectangle FUNCTIONBUTTON=new Rectangle(964,83,105,35);
	
	final Rectangle SEARCHPANE=new Rectangle(69,118,1000,45);//地址栏下的主pane 的bounds
	final Rectangle LISTPANE=new Rectangle(69,163,1000,450);
	
	
	//======搜索栏设置；
	final Rectangle FILTER=new Rectangle(0,0,150,50);//条件筛选
	final Rectangle SEASON=new Rectangle(160,0,60,50);//赛季选择
	final Rectangle SEARCH=new Rectangle(230,0,200,50);//搜索框
    final Rectangle SEARCHBUTTON=new Rectangle(440,0,80,50);//搜索按钮
    final Rectangle REFRESH=new Rectangle(840,0,40,40);//刷新按钮
    final Rectangle MODEL=new Rectangle(880,0,40,40);//模式切换按钮
    final Rectangle UP=new Rectangle(920,0,40,40);//升序按钮
    final Rectangle DOWN=new Rectangle(960,0,40,40);//降序按钮
    
	
	
	

}
