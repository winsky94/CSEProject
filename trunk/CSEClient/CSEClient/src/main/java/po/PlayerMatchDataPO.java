package po;

public class PlayerMatchDataPO extends MatchDataPO{
	private int playerID;    //player编号
	private String teamName;// 所属球队
	private int playedGames;// 参赛场数
	private int gameStartingNum;// 先发场数
	private String presentTime;// 在场时间
	private int offenNum;// 进攻数
	private int defenNum;// 防守数
	private double efficiency;// 效率
	private double GmScEfficiencyValue;// GmSc效率值
	private double trueHitRate;// 真实命中率
	private double shootHitEfficiency;// 投篮效率
	private double reboundRate;// 篮板率
	private double offenReboundRate;// 进攻篮板率
	private double defenReboundRate;// 防守篮板率
	private double assistRate;// 助攻率
	private double stealRate;// 抢断率
	private double blockRate;// 盖帽率
	private double turnOverRate;// 失误率
	public PlayerMatchDataPO(int id,String teamName,int playedGames,int gameStartingNum,String presentTime,
			int offenNum,int defenNum,double efficiency,double GmScEfficiencyValue,double trueHitRate,
			double shootHitEfficiency,double reboundRate,double offenReboundRate,double defenReboundRate,
			double assistRate,double stealRate,double blockRate,double turnOverRate,
			int reboundNum, int assistNum,double shootHitRate, double threeHitRate, double freeThrowHitRate,
			int stealNum, int blockNum, int turnOverNum, int foulNum, int score) {
		super(reboundNum, assistNum, shootHitRate, threeHitRate, freeThrowHitRate,
				stealNum, blockNum, turnOverNum, foulNum, score);
		this.playerID=id;
		this.teamName=teamName;
		this.playedGames=playedGames;
		this.gameStartingNum=gameStartingNum;
		this.presentTime=presentTime;
		this.offenNum=offenNum;
		this.defenNum=defenNum;
		this.efficiency=efficiency;
		this.GmScEfficiencyValue=GmScEfficiencyValue;
		this.trueHitRate=trueHitRate;
		this.shootHitEfficiency=shootHitEfficiency;
		this.reboundRate=reboundRate;
		this.offenReboundRate=offenReboundRate;
		this.defenReboundRate=defenReboundRate;
		this.assistRate=assistRate;
		this.stealRate=stealRate;
		this.blockRate=blockRate;
	    this.turnOverRate=turnOverRate;
	}
	
	public int getPlayerID(){
		return playerID;
	}
	
	public String getTeamName() {
		return teamName;
	}

	public int getPlayedGames() {
		return playedGames;
	}

	public int getGameStartingNum() {
		return gameStartingNum;
	}

	public String getPresentTime() {
		return presentTime;
	}

	public int getOffenNum() {
		return offenNum;
	}

	public int getDefenNum() {
		return defenNum;
	}

	public double getEfficiency() {
		return efficiency;
	}

	public double getGmScEfficiencyValue() {
		return GmScEfficiencyValue;
	}

	public double getTrueHitRate() {
		return trueHitRate;
	}

	public double getShootHitEfficiency() {
		return shootHitEfficiency;
	}

	public double getReboundRate() {
		return reboundRate;
	}

	public double getOffenReboundRate() {
		return offenReboundRate;
	}

	public double getDefenReboundRate() {
		return defenReboundRate;
	}

	public double getAssistRate() {
		return assistRate;
	}

	public double getStealRate() {
		return stealRate;
	}

	public double getBlockRate() {
		return blockRate;
	}

	public double getTurnOverRate() {
		return turnOverRate;
	}
	
	public void setPlayerID(int id){
		this.playerID=id;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public void setPlayedGames(int playedGames) {
		this.playedGames = playedGames;
	}

	public void setGameStartingNum(int gameStartingNum) {
		this.gameStartingNum = gameStartingNum;
	}

	public void setPresentTime(String presentTime) {
		this.presentTime = presentTime;
	}

	public void setOffenNum(int offenNum) {
		this.offenNum = offenNum;
	}

	public void setDefenNum(int defenNum) {
		this.defenNum = defenNum;
	}

	public void setEfficiency(double efficiency) {
		this.efficiency = efficiency;
	}

	public void setGmScEfficiencyValue(double gmScEfficiencyValue) {
		GmScEfficiencyValue = gmScEfficiencyValue;
	}

	public void setTrueHitRate(double trueHitRate) {
		this.trueHitRate = trueHitRate;
	}

	public void setShootHitEfficiency(double shootHitEfficiency) {
		this.shootHitEfficiency = shootHitEfficiency;
	}

	public void setReboundRate(double reboundRate) {
		this.reboundRate = reboundRate;
	}

	public void setOffenReboundRate(double offenReboundRate) {
		this.offenReboundRate = offenReboundRate;
	}

	public void setDefenReboundRate(double defenReboundRate) {
		this.defenReboundRate = defenReboundRate;
	}

	public void setAssistRate(double assistRate) {
		this.assistRate = assistRate;
	}

	public void setStealRate(double stealRate) {
		this.stealRate = stealRate;
	}

	public void setBlockRate(double blockRate) {
		this.blockRate = blockRate;
	}

	public void setTurnOverRate(double turnOverRate) {
		this.turnOverRate = turnOverRate;
	}

}
