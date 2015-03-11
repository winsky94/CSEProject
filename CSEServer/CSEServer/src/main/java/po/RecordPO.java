package po;

public class RecordPO {
	private int id;// 比赛编号 1314_0101_CHALAC
	private String team;// 球队
	private String playerName;// 球员名
	private String position;// 位置
	private double presentTime;// 在场时间
	private double shootHitNum;// 投篮命中数
	private double shootAttemptNum;// 投篮出手数
	private double threeHitNum;// 三分命中数
	private double threeAttemptNum;// 三分出手数
	private double freeThrowHitNum;// 罚球命中数
	private double freeThrowAttemptNum;// 罚球出手数
	private double offenReboundNum;// 进攻（前场）篮板数
	private double defenReboundNum;// 防守（后场）篮板数
	private double reboundNum;// 总篮板数
	private double assistNum;// 助攻数
	private double stealNum;// 抢断数
	private double blockNum;// 盖帽数
	private double turnOverNum;// 失误数
	private double foulNum;// 犯规数
	private double score;// 个人得分

	public RecordPO(int id, String team, String playerName, String position,
			double presentTime, double shootHitNum, double shootAttemptNum,
			double threeHitNum, double threeAttemptNum, double freeThrowHitNum,
			double freeThrowAttemptNum, double offenReboundNum,
			double defenReboundNum, double reboundNum, double assistNum,
			double stealNum, double blockNum, double turnOverNum,
			double foulNum, double score) {
		super();
		this.id = id;
		this.team = team;
		this.playerName = playerName;
		this.position = position;
		this.presentTime = presentTime;
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
	}

	public int getId() {
		return id;
	}

	public String getTeam() {
		return team;
	}

	public String getPlayerName() {
		return playerName;
	}

	public String getPosition() {
		return position;
	}

	public double getPresentTime() {
		return presentTime;
	}

	public double getShootHitNum() {
		return shootHitNum;
	}

	public double getShootAttemptNum() {
		return shootAttemptNum;
	}

	public double getThreeHitNum() {
		return threeHitNum;
	}

	public double getThreeAttemptNum() {
		return threeAttemptNum;
	}

	public double getFreeThrowHitNum() {
		return freeThrowHitNum;
	}

	public double getFreeThrowAttemptNum() {
		return freeThrowAttemptNum;
	}

	public double getOffenReboundNum() {
		return offenReboundNum;
	}

	public double getDefenReboundNum() {
		return defenReboundNum;
	}

	public double getReboundNum() {
		return reboundNum;
	}

	public double getAssistNum() {
		return assistNum;
	}

	public double getStealNum() {
		return stealNum;
	}

	public double getBlockNum() {
		return blockNum;
	}

	public double getTurnOverNum() {
		return turnOverNum;
	}

	public double getFoulNum() {
		return foulNum;
	}

	public double getScore() {
		return score;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public void setPresentTime(double presentTime) {
		this.presentTime = presentTime;
	}

	public void setShootHitNum(double shootHitNum) {
		this.shootHitNum = shootHitNum;
	}

	public void setShootAttemptNum(double shootAttemptNum) {
		this.shootAttemptNum = shootAttemptNum;
	}

	public void setThreeHitNum(double threeHitNum) {
		this.threeHitNum = threeHitNum;
	}

	public void setThreeAttemptNum(double threeAttemptNum) {
		this.threeAttemptNum = threeAttemptNum;
	}

	public void setFreeThrowHitNum(double freeThrowHitNum) {
		this.freeThrowHitNum = freeThrowHitNum;
	}

	public void setFreeThrowAttemptNum(double freeThrowAttemptNum) {
		this.freeThrowAttemptNum = freeThrowAttemptNum;
	}

	public void setOffenReboundNum(double offenReboundNum) {
		this.offenReboundNum = offenReboundNum;
	}

	public void setDefenReboundNum(double defenReboundNum) {
		this.defenReboundNum = defenReboundNum;
	}

	public void setReboundNum(double reboundNum) {
		this.reboundNum = reboundNum;
	}

	public void setAssistNum(double assistNum) {
		this.assistNum = assistNum;
	}

	public void setStealNum(double stealNum) {
		this.stealNum = stealNum;
	}

	public void setBlockNum(double blockNum) {
		this.blockNum = blockNum;
	}

	public void setTurnOverNum(double turnOverNum) {
		this.turnOverNum = turnOverNum;
	}

	public void setFoulNum(double foulNum) {
		this.foulNum = foulNum;
	}

	public void setScore(double score) {
		this.score = score;
	}

}
