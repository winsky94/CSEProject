package po;

import java.io.Serializable;

public class TeamPO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id; // 编号
	private String teamName; // 球队全名
	private String abLocation; // 所在地(缩写)
	private String location; // 所在地
	private String conference; // 赛区
	private String partition; // 分区
	private String homeCourt; // 主场
	private int setUpTime; // 建立时间

	private int matchesNum; // 比赛场数
	private int shootHitNum; // 投篮命中数
	private int shootAttemptNum; // 投篮出手次数
	private int threeHitNum; // 三分命中数
	private int threeAttemptNum; // 三分出手数
	private int freeThrowHitNum; // 罚球命中数
	private int freeThrowAttemptNum; // 罚球出手数
	private int offenReboundNum; // 进攻篮板数
	private int defenReboundNum; // 防守篮板数
	private int reboundNum;// 篮板数
	private int assistNum;// 助攻数
	private int stealNum;// 抢断数
	private int blockNum;// 盖帽数
	private int turnOverNum;// 失误数
	private int foulNum;// 犯规数
	private int score;// 比赛得分
	private double shootHitRate;// 投篮命中率
	private double threeHitRate;// 三分命中率
	private double freeThrowHitRate;// 罚球命中率
	private double winRate; // 胜率
	private double offenRound; // 进攻回合
	private double offenEfficiency; // 进攻效率
	private double defenEfficiency; // 防守效率
	private double offenReboundEfficiency; // 进攻篮板效率
	private double defenReboundEfficiency; // 防守篮板效率
	private double stealEfficiency; // 抢断效率
	private double assistRate; // 助攻率

	public TeamPO(int id, String teamName, String abLocation, String location,
			String conference, String partition, String homeCourt, int setUpTime) {
		super();
		this.id = id;
		this.teamName = teamName;
		this.location = location;
		this.abLocation = abLocation;
		this.conference = conference;
		this.partition = partition;
		this.homeCourt = homeCourt;
		this.setUpTime = setUpTime;
	}

	public TeamPO(String abLocation, int matchesNum, int shootHitNum,
			int shootAttemptNum, int threeHitNum, int threeAttemptNum,
			int freeThrowHitNum, int freeThrowAttemptNum, int offenReboundNum,
			int defenReboundNum, int reboundNum, int assistNum, int stealNum,
			int blockNum, int turnOverNum, int foulNum, int score,
			double shootHitRate, double threeHitRate, double freeThrowHitRate,
			double winRate, double offenRound, double offenEfficiency,
			double defenEfficiency, double offenReboundEfficiency,
			double defenReboundEfficiency, double stealEfficiency,
			double assistRate) {
		super();
		this.abLocation = abLocation;
		this.matchesNum = matchesNum;
		this.shootHitNum = shootHitNum;
		this.shootAttemptNum = shootAttemptNum;
		this.threeHitNum = threeHitNum;
		this.threeAttemptNum = threeAttemptNum;
		this.freeThrowHitNum = freeThrowHitNum;
		this.freeThrowAttemptNum = freeThrowAttemptNum;
		this.offenReboundNum = offenReboundNum;
		this.defenReboundNum = defenReboundNum;
		this.reboundNum = reboundNum;
		this.assistNum = assistNum;
		this.stealNum = stealNum;
		this.blockNum = blockNum;
		this.turnOverNum = turnOverNum;
		this.foulNum = foulNum;
		this.score = score;
		this.shootHitRate = shootHitRate;
		this.threeHitRate = threeHitRate;
		this.freeThrowHitRate = freeThrowHitRate;
		this.winRate = winRate;
		this.offenRound = offenRound;
		this.offenEfficiency = offenEfficiency;
		this.defenEfficiency = defenEfficiency;
		this.offenReboundEfficiency = offenReboundEfficiency;
		this.defenReboundEfficiency = defenReboundEfficiency;
		this.stealEfficiency = stealEfficiency;
		this.assistRate = assistRate;
	}

	public int getId() {
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

	public int getSetUpTime() {
		return setUpTime;
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

	public double getAssistRate() {
		return assistRate;
	}

	public int getReboundNum() {
		return reboundNum;
	}

	public int getAssistNum() {
		return assistNum;
	}

	public double getShootHitRate() {
		return shootHitRate;
	}

	public double getThreeHitRate() {
		return threeHitRate;
	}

	public double getFreeThrowHitRate() {
		return freeThrowHitRate;
	}

	public int getStealNum() {
		return stealNum;
	}

	public int getBlockNum() {
		return blockNum;
	}

	public int getTurnOverNum() {
		return turnOverNum;
	}

	public int getFoulNum() {
		return foulNum;
	}

	public int getScore() {
		return score;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setAbLocation(String abLocation) {
		this.abLocation = abLocation;
	}

	public void setConference(String conference) {
		this.conference = conference;
	}

	public void setPartition(String partition) {
		this.partition = partition;
	}

	public void setHomeCourt(String homeCourt) {
		this.homeCourt = homeCourt;
	}

	public void setSetUpTime(int setUpTime) {
		this.setUpTime = setUpTime;
	}

	public void setMatchesNum(int matchesNum) {
		this.matchesNum = matchesNum;
	}

	public void setShootHitNum(int shootHitNum) {
		this.shootHitNum = shootHitNum;
	}

	public void setShootAttemptNum(int shootAttemptNum) {
		this.shootAttemptNum = shootAttemptNum;
	}

	public void setThreeHitNum(int threeHitNum) {
		this.threeHitNum = threeHitNum;
	}

	public void setThreeAttemptNum(int threeAttemptNum) {
		this.threeAttemptNum = threeAttemptNum;
	}

	public void setFreeThrowHitNum(int freeThrowHitNum) {
		this.freeThrowHitNum = freeThrowHitNum;
	}

	public void setFreeThrowAttemptNum(int freeThrowAttemptNum) {
		this.freeThrowAttemptNum = freeThrowAttemptNum;
	}

	public void setOffenReboundNum(int offenReboundNum) {
		this.offenReboundNum = offenReboundNum;
	}

	public void setDefenReboundNum(int defenReboundNum) {
		this.defenReboundNum = defenReboundNum;
	}

	public void setWinRate(double winRate) {
		this.winRate = winRate;
	}

	public void setOffenRound(double offenRound) {
		this.offenRound = offenRound;
	}

	public void setOffenEfficiency(double offenEfficiency) {
		this.offenEfficiency = offenEfficiency;
	}

	public void setDefenEfficiency(double defenEfficiency) {
		this.defenEfficiency = defenEfficiency;
	}

	public void setOffenReboundEfficiency(double offenReboundEfficiency) {
		this.offenReboundEfficiency = offenReboundEfficiency;
	}

	public void setDefenReboundEfficiency(double defenReboundEfficiency) {
		this.defenReboundEfficiency = defenReboundEfficiency;
	}

	public void setStealEfficiency(double stealEfficiency) {
		this.stealEfficiency = stealEfficiency;
	}

	public void setAssistRate(double assistRate) {
		this.assistRate = assistRate;
	}

	public void setReboundNum(int reboundNum) {
		this.reboundNum = reboundNum;
	}

	public void setAssistNum(int assistNum) {
		this.assistNum = assistNum;
	}

	public void setShootHitRate(double shootHitRate) {
		this.shootHitRate = shootHitRate;
	}

	public void setThreeHitRate(double threeHitRate) {
		this.threeHitRate = threeHitRate;
	}

	public void setFreeThrowHitRate(double freeThrowHitRate) {
		this.freeThrowHitRate = freeThrowHitRate;
	}

	public void setStealNum(int stealNum) {
		this.stealNum = stealNum;
	}

	public void setBlockNum(int blockNum) {
		this.blockNum = blockNum;
	}

	public void setTurnOverNum(int turnOverNum) {
		this.turnOverNum = turnOverNum;
	}

	public void setFoulNum(int foulNum) {
		this.foulNum = foulNum;
	}

	public void setScore(int score) {
		this.score = score;
	}
}
