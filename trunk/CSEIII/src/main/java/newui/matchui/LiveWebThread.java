package newui.matchui;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class LiveWebThread extends Thread{
//only process four QT game
	private boolean stop=false;
	private LiveWebInc c;
	private String gameid="";
	private int size=0;
	private int line=1;
//	private LiveStringAccept ac;
	private MyPanel2 ac;
	public LiveWebThread(LiveWebInc c,String id,MyPanel2 s) {
		super();
		this.c = c;
		this.gameid=id;
		this.ac=s;
	}

	//判断今日是否有比赛  然后去爬取直播
	//什么时候庭呢！
	public void run(){
		while(!stop){
			//ArrayList<String> s=c.getELiveInfo(gameid);
			ArrayList<String> s=c.getGameLive(gameid, line);
			
			int n=s.size()-size;
			if(n>0){
				ArrayList<String> res=new ArrayList<String>();
				for(int i=0;i<n;i++)
					res.add(s.get(i));
					//ac.getString(s.get(i));
				ac.refresh(res, line);
				size=s.size();
				
			}
			System.out.println(s.size());
			if(s.size()>0)
				if(s.get(0).contains("00:00.0"))
					{line+=1;size=0;}
			if(line==6)
				this.stop=true;
			try {
				//System.out.println("我到这里啦");
				this.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	

	public void startThread(){
		this.start();
	}
	
	public void stopThread(){
		this.stop=true;
	}
	public static void main(String[] args) {
		//ArrayList<LiveWebThread>
		LiveWebInc cc=new LiveWebInc();
		Calendar c=Calendar.getInstance();
		int year=c.get(Calendar.YEAR);
		int month=c.get(Calendar.MONTH)+1;
		int day=c.get(Calendar.DATE);
		LiveWebInc live=new LiveWebInc();
		//String date=month+"%2F"+day+"%2F"+year;
		String date="06%2F04%2F2015";
		ArrayList<ArrayList<String>> IdAndStatus=cc.getGameStatus(date);
		if(IdAndStatus.size()==0)
			System.out.println("No game Today");
		for(ArrayList<String> line:IdAndStatus){
			if(line.get(1).equals("3")){
				JFrame jFrame=new JFrame();	
				MyPanel2 mPanel=new MyPanel2("ATL","BKN","14-15","03-25");
				jFrame.getContentPane().add(mPanel);
				jFrame.setLocation(150, 50);
				jFrame.setSize(1000,600);
				jFrame.setVisible(true);
				jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				LiveWebThread th=new LiveWebThread(cc,line.get(0),mPanel);
				th.startThread();
			}
		}
	}
}
