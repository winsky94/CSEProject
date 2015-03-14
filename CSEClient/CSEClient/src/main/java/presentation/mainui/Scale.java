package presentation.mainui;

import java.awt.Rectangle;

public interface Scale {
	final  Rectangle TITLEBAR=new Rectangle(69,83,1000,35);//地址栏
	
	final Rectangle VOICE=new Rectangle(895,0,35,35);//声音开关
	final Rectangle MIN=new Rectangle(930,0,35,35);//最小化
	final Rectangle CLOSE=new Rectangle(965,0,35,35);//关闭
	
	final Rectangle PANE=new Rectangle(69,118,1000,600);//地址栏下的主pane 的bounds
	

}
