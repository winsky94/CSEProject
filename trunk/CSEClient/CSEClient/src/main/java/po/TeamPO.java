package po;

public class TeamPO {
	private String id;                     //编号
	private String teamName;               //球队全名
	private String location;               //所在地
	private String abLocation;             //所在地(缩写)
	private String conference;             //赛区
	private String partition;              //分区
	private String homeCourt;              //主场
	private String setUpTime;              //建立时间
	
	private CommonPO commonPO;
	private int matchesNum;                //比赛场数
	private int shootHitNum;               //投篮命中率
	private int shootAttemptNum;           //投篮出手次数   
	private int threeHitNum;               //三分命中数
	private int threeAttemptNum;           //三分出手数    
	private int freeThrowHitNum;           //罚球命中数   
	private int freeThrowAttemptNum;       //罚球出手数       
	private int offenReboundNum;           //进攻篮板数
	private int defenReboundNum;           //防守篮板数
	private double winRate;                 //胜率
	private double offenRound;              //进攻回合
	private double offenEfficiency;         //进攻效率
	private double defenEfficiency;         //防守效率
	private double offenReboundEfficiency;  //进攻篮板效率
	private double defenReboundEfficiency;  //防守篮板效率
	private double stealEfficiency;         //抢断效率
	private double assistEfficiency;        //助攻率
	
	public String getId() {
		return id;
	}
	
	public String getTeamName() {
		return teamName;
	}
	
	public String getLocation() {
		return location;
	}
	
	public String getAbLocation() {
		return abLocation;
	}
	
	public String getConference() {
		return conference;
	}
	
	public String getPartition() {
		return partition;
	}
	
	public String getHomeCourt() {
		return homeCourt;
	}
	
	public String getSetUpTime() {
		return setUpTime;
	}
	
	public CommonPO getCommonPO() {
		return commonPO;
	}
	
	
	public int getMatchesNum() {
		return matchesNum;
	}
	
	public int getShootHitNum() {
		return shootHitNum;
	}
	
	public int getShootAttemptNum() {
		return shootAttemptNum;
	}
	
	public int getThreeHitNum() {
		return threeHitNum;
	}
	
	public int getThreeAttemptNum() {
		return threeAttemptNum;
	}
	
	public int getFreeThrowHitNum() {
		return freeThrowHitNum;
	}
	
	public int getFreeThrowAttemptNum() {
		return freeThrowAttemptNum;
	}
	
	public int getOffenReboundNum() {
		return offenReboundNum;
	}
	
	public int getDefenReboundNum() {
		return defenReboundNum;
	}
	
	public double getWinRate() {
		return winRate;
	}
	
	public double getOffenRound() {
		return offenRound;
	}
	
	public double getOffenEfficiency() {
		return offenEfficiency;
	}
	
	public double getDefenEfficiency() {
		return defenEfficiency;
	}
	
	public double getOffenReboundEfficiency() {
		return offenReboundEfficiency;
	}
	
	public double getDefenReboundEfficiency() {
		return defenReboundEfficiency;
	}
	
	public double getStealEfficiency() {
		return stealEfficiency;
	}
	
	public double getAssistEfficiency() {
		return assistEfficiency;
	}
	
	public void setId(String id) {
		this.id=id;
	}
	
	public void setTeamName(String teamName) {
		this.teamName=teamName;
	}
	
	public void setLocation(String location) {
		this.location=location;
	}
	
	public void setAbLocation(String abLocation) {
		this.abLocation=abLocation;
	}
	
	public void setConference(String conference) {
		this.conference=conference;
	}
	
	public void setPartition(String partition) {
		this.partition=partition;
	}
	
	public void setHomeCourt(String homeCourt) {
		this.homeCourt=homeCourt;
	}
	
	public void setSetUpTime(String setUpTime) {
		this.setUpTime=setUpTime;
	}
	
	public void setCommonPO(CommonPO po) {
		this.commonPO=po;
	}
	
	
	public void setMatchesNum(int matchesNum) {
		this.matchesNum=matchesNum;
	}
	
	public void setShootHitNum(int shootHitNum) {
		this.shootHitNum=shootHitNum;
	}
	
	public void setShootAttemptNum(int shootAttemptNum) {
		this.shootAttemptNum=shootAttemptNum;
	}
	
	public void setThreeHitNum(int threeHitNum) {
		this.threeHitNum=threeHitNum;
	}
	
	public void setThreeAttemptNum(int threeAttemptNum) {
		this.threeAttemptNum=threeAttemptNum;
	}
	
	public void setFreeThrowHitNum(int freeThrowHitNum) {
		this.freeThrowHitNum=freeThrowHitNum;
	}
	
	public void setFreeThrowAttemptNum(int freeThrowAttemptNum) {
		this.freeThrowAttemptNum=freeThrowAttemptNum;
	}
	
	public void setOffenReboundNum(int offenReboundNum) {
		this.offenReboundNum=offenReboundNum;
	}
	
	public void setDefenReboundNum(int defenReboundNum) {
		this.defenReboundNum=defenReboundNum;
	}
	
	public void setWinRate(double winRate) {
		this.winRate=winRate;
	}
	
	public void setOffenRound(double offenRound) {
		this.offenRound=offenRound;
	}
	
	public void setOffenEfficiency(double offenEfficiency) {
		this.offenEfficiency=offenEfficiency;
	}
	
	public void setDefenEfficiency(double defenEfficiency) {
		this.defenEfficiency=defenEfficiency;
	}
	
	public void setOffenReboundEfficiency(double offenReboundEfficiency) {
		this.offenReboundEfficiency=offenReboundEfficiency;
	}
	
	public void setDefenReboundEfficiency(double defenReboundEfficiency) {
		this.defenReboundEfficiency=defenReboundEfficiency;
	}
	
	public void setStealEfficiency(double stealEfficiency) {
		this.stealEfficiency=stealEfficiency;
	}
	
	public void setAssistEfficiency(double assistEfficiency) {
		this.assistEfficiency=assistEfficiency;
	}
}
