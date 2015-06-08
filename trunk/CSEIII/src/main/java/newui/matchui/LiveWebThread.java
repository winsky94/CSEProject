﻿package newui.matchui;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import vo.MatchVO;

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
	private MatchDetailPanel ac;
	Pattern p;Matcher m;
	private boolean islasts=false;
	public LiveWebThread(LiveWebInc c,String id,MatchDetailPanel s) {
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
					if(/*line>=4&&(*/!islasts){
						m=p.matcher(s.get(i));
						while(m.find()){
							lastscore=m.group();
							islasts=true;
						}
						
					}
						
				
				}
					//ac.getString(s.get(i));
				//ac.refresh(res, line);
				ac.RefreshLiveAndScore(res, line, lastscore);
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
		String m=month+"";String d=day+"";String td=(day-1)+"";
		if(m.length()==1) m="0"+m;
		if(d.length()==1) {d="0"+d;}
		if(td.length()==1) td="0"+td;//to eng nab need to min one day
		//not complete day change
			
		String date=m+"%2F"+td+"%2F2015";
		ArrayList<ArrayList<String>> IdAndStatus=live.getGameStatus(date);
		if(IdAndStatus.size()==0)
			System.out.println("No game Today");
		for(ArrayList<String> line:IdAndStatus){
			if(!line.get(1).equals("1")){
				String s=line.get(2).split("/")[1];
				JFrame jFrame=new JFrame();	
				LiveTextPanel mPanel=new LiveTextPanel(s.substring(0, 3),s.substring(3, 6),"14-15",m+"-"+d);
				MatchVO v=new MatchVO("14-15",m+"-"+d,
						"Playoff",s.substring(0, 3),
						s.substring(3, 6));
				MatchDetailPanel mm=new MatchDetailPanel(v);
				//jFrame.getContentPane().add(mPanel);
				jFrame.getContentPane().add(mm);
				mm.setIsLive(true);
				jFrame.setLocation(150, 50);
				jFrame.setSize(1000,800);
				jFrame.setVisible(true);
				jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				LiveWebInc cc=new LiveWebInc();
			
				cc.setTeam(line.get(4),line.get(3), s.substring(0, 3), s.substring(3, 6));
				//give the panel info to save
				//need day Change;  make day change function available
				mPanel.initLiveData(season,m+"-"+d, s.substring(0, 3)+"-"+ s.substring(3, 6));
				mm.setIsLive(true);
				LiveWebThread th=new LiveWebThread(cc,line.get(0),mm);
				th.startThread();
			}
		}
	}
}
