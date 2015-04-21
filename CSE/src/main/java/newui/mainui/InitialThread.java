package newui.mainui;

//界面服务初始化Thread
public class InitialThread extends Thread{
	boolean stop=false;
	
	public InitialThread(){
		
	}
	public void run(){
		
	}
	
	public void startThread(){
		this.start();		
	}
	public void stopThead(){
		this.stop=true;
	}
	

}
