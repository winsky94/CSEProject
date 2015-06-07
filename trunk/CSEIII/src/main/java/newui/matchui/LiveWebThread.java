package newui.matchui;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/*
 * use the last score to ...if game over
 * 
 * */
public class LiveWebThread extends Thread{
//only process four QT game
	private boolean stop=false;
	private LiveWebInc c;
	private String gameid="";
	private int size=0;
	private int line=1;
	private String regex="[\\d]+-[\\d]+";//比分
	private String lastscore;
//	private LiveStringAccept ac;
	private LiveTextPanel ac;
	Pattern p;Matcher m;
	private boolean islasts=false;
	public LiveWebThread(LiveWebInc c,String id,LiveTextPanel s) {
		super();
		this.c = c;
		this.gameid=id;
		this.ac=s;
		p=Pattern.compile(regex);
		//Matcher m=p.matcher(destStr);
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
				for(int i=0;i<n;i++){
					res.add(s.get(i));
					if(line>=4&&(!islasts)){
						m=p.matcher(s.get(i));
						while(m.find()){
							lastscore=m.group();
							islasts=true;
						}
						
					}
						
				
				}
					//ac.getString(s.get(i));
				ac.refresh(res, line);
				size=s.size();
				
			}
			System.out.println(s.size());
			if(s.size()>0)
				if(s.get(0).contains("00:00.0"))
					{line+=1;size=0;islasts=false;}
			if(line>4){
				String[] ss=lastscore.split("-");
				if(!ss[0].equals(ss[1]))
					this.stop=true;
				else
					islasts=false;
			}
			try {
				//System.out.println("我到这里啦");
				this.sleep(5000);
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
		
		Calendar c=Calendar.getInstance();
		int year=c.get(Calendar.YEAR);
		int month=c.get(Calendar.MONTH)+1;
		int day=c.get(Calendar.DATE);
		LiveWebInc live=new LiveWebInc();
		//String date=month+"%2F"+day+"%2F"+year;
		String season="14-15";//need to change if real use
		String m=month+"";String d=day+"";
		if(m.length()==1) m="0"+m;
		if(d.length()==1) d="0"+m;
			
		String date="06%2F04%2F2015";
		ArrayList<ArrayList<String>> IdAndStatus=live.getGameStatus(date);
		if(IdAndStatus.size()==0)
			System.out.println("No game Today");
		for(ArrayList<String> line:IdAndStatus){
			if(line.get(1).equals("3")){
				JFrame jFrame=new JFrame();	
				LiveTextPanel mPanel=new LiveTextPanel("ATL","BKN","14-15","03-25");
				jFrame.getContentPane().add(mPanel);
				jFrame.setLocation(150, 50);
				jFrame.setSize(1000,600);
				jFrame.setVisible(true);
				jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				LiveWebInc cc=new LiveWebInc();
				String s=line.get(2).split("/")[1];
				cc.setTeam(line.get(4),line.get(3), s.substring(0, 3), s.substring(3, 6));
				//give the panel info to save
				mPanel.initLiveData(season,m+"-"+d, s.substring(0, 3)+"-"+ s.substring(3, 6));
				LiveWebThread th=new LiveWebThread(cc,line.get(0),mPanel);
				th.startThread();
			}
		}
	}
}
