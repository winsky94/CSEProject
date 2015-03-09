package po;

public class TeamPO {
	String teamName;               //球队全名
	String location;               //所在地
	String abLocation;             //所在地(缩写)
	String conference;             //赛区
	String partition;              //分区
	String homeCourt;              //主场
	String setUpTime;              //建立时间
	int matchesNum;                //比赛场数
	int shootHitNum;               //投篮命中率
	int shootAttemptNum;           //投篮出手次数   
	int threeHitNum;               //三分命中数
	int threeAttemptNum;           //三分出手数    
	int freeThrowHitNum;           //罚球命中数   
	int freeThrowAttemptNum;       //罚球出手数       
	int offenReboundNum;           //进攻篮板数
	int defenReboundNum;           //防守篮板数
    double winRate;                 //胜率
    double offenRound;              //进攻回合
    double offenEfficiency;         //进攻效率
    double defenEfficiency;         //防守效率
    double offenReboundEfficiency;  //进攻篮板效率
    double defenReboundEfficiency;  //防守篮板效率
    double stealEfficiency;         //抢断效率
    double assistEfficiency;        //助攻率
}
