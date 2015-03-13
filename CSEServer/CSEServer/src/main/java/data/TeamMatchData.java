package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import po.TeamMatchDataPO;

public class TeamMatchData {
	String teamName;//球队区域缩写
	int matchesNum=0; // 比赛场数
    int shootHitNum=0; // 投篮命中数
    int shootAttemptNum=0; // 投篮出手次数
    int threeHitNum=0; // 三分命中数
    int threeAttemptNum=0; // 三分出手数
    int freeThrowHitNum=0; // 罚球命中数
    int freeThrowAttemptNum=0; // 罚球出手数
    int offenReboundNum=0; // 进攻篮板数
    int defenReboundNum=0; // 防守篮板数
    int reboundNum;// 篮板数
	int assistNum;// 助攻数
	int stealNum;// 抢断数
	int blockNum;// 盖帽数
	int turnOverNum;// 失误数
	int foulNum;// 犯规数
	int score;// 比赛得分
	double shootHitRate;//投篮命中率
	double threeHitRate;// 三分命中率
	double freeThrowHitRate;// 罚球命中率
    double winRate=0; // 胜率
    double offenRound=0; // 进攻回合
    double offenEfficiency=0; // 进攻效率
    double defenEfficiency=0; // 防守效率
    double offenReboundEfficiency=0; // 进攻篮板效率
    double defenReboundEfficiency=0; // 防守篮板效率
    double stealEfficiency=0; // 抢断效率
    double assistEfficiency=0; // 助攻率
	
	public ArrayList<TeamMatchDataPO> getTeamMatchData(){
		ArrayList<TeamMatchDataPO> datas=new ArrayList<TeamMatchDataPO>();
		ArrayList<String> names=new ArrayList<String>();
		 Connection con;
		 TeamMatchData tmd;
		try {
			con = SqlManager.getConnection();
			Statement sql = con.createStatement();
			String query = "select abLocation from teams";
            ResultSet result = sql.executeQuery(query);
            while(result.next()){
            	names.add(result.getString("abLocation"));
            }
            for(String name:names){
            	query="select * from records where team='"+name+"'";
            	result = sql.executeQuery(query);
            	int matchesNum=0; // 比赛场数
            	int temp=0;
        	    int shootHitNum=0; // 投篮命中数
        	    int shootAttemptNum=0; // 投篮出手次数
        	    int threeHitNum=0; // 三分命中数
        	    int threeAttemptNum=0; // 三分出手数
        	    int freeThrowHitNum=0; // 罚球命中数
        	    int freeThrowAttemptNum=0; // 罚球出手数
        	    int offenReboundNum=0; // 进攻篮板数
        	    int defenReboundNum=0; // 防守篮板数
        	    int reboundNum=0;// 篮板数
        		int assistNum=0;// 助攻数
        		int shootHitRate=0;// 抢断数
        		int blockNum=0;// 盖帽数
        		int turnOverNum=0;// 失误数
        		int foulNum=0;// 犯规数
        		int score=0;// 得分
        		double threeHitRate=0;// 三分命中率
        		double freeThrowHitRate=0;// 罚球命中率
        	    double winRate=0; // 胜率
        	    double offenRound=0; // 进攻回合
        	    double offenEfficiency=0; // 进攻效率
        	    double defenEfficiency=0; // 防守效率
        	    double offenReboundEfficiency=0; // 进攻篮板效率
        	    double defenReboundEfficiency=0; // 防守篮板效率
        	    double stealEfficiency=0; // 抢断效率
        	    double assistEfficiency=0; // 助攻率
        		 
            	while(result.next()){
            		
            	   //计算比赛场数
            	   int matchID=result.getInt("matchID");
                   if(temp!=matchID){
                	   matchesNum++;
                	   temp=matchID;
                   }
                   
                   //计算投篮命中数
                   shootHitNum+=result.getInt("shootHitNum");                   
                   //投篮出手次数
                   shootAttemptNum+=result.getInt("shootAttemptNum");                   
                   //三分命中数
                   threeHitNum+=result.getInt("threeHitNum");                  
                   //三分出手数
                   threeAttemptNum+=result.getInt("threeAttemptNum");
                   //罚球命中数
                   freeThrowHitNum+=result.getInt("freeThrowHitNum");
                   //罚球出手数
                   freeThrowAttemptNum+=result.getInt("freeThrowAttemptNum");
                   //进攻篮板数
                   offenReboundNum+=result.getInt("offenReboundNum");
                   //防守篮板数
                   defenReboundNum+=result.getInt("defenReboundNum");
                   //篮板数
                   reboundNum+=(offenReboundNum+defenReboundNum);
                   //助攻数
                   assistNum+=result.getInt("assistNum");
                   //抢断数
                   shootHitRate+=result.getInt("shootHitRate");
                   //盖帽数
                   blockNum+=result.getInt("blockNum");
                   //失误数
                   turnOverNum+=result.getInt("turnOverNum");
                   //犯规数
           		   foulNum+=result.getInt("foulNum");
                   //得分
           		   score+=result.getInt("score");
            	}
            	//三分命中率
            	threeHitRate=threeHitNum/threeAttemptNum;           	
            	//罚球命中率
            	freeThrowHitRate=freeThrowHitNum/freeThrowAttemptNum;
            	
            }
            	sql.close();
                con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return datas;
	}
	

	public static void main(String[] args) {
		TeamMatchData tmd=new TeamMatchData();
		tmd.getTeamMatchData();
	}

}
