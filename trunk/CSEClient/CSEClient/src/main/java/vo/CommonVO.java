package vo;

public class CommonVO {
	private double reboundNum;// 篮板数
	private double assistNum;// 助攻数
	private double shootHitRate;// 投篮命中率
	private double threeHitRate;// 三分命中率
	private double freeThrowHitRate;// 罚球命中率
	private double stealNum;// 抢断数
	private double blockNum;// 盖帽数
	private double turnOverNum;// 失误数
	private double foulNum;// 犯规数
	private double score;// 得分

	public CommonVO(double reboundNum, double assistNum, double shootHitRate,
			double threeHitRate, double freeThrowHitRate, double stealNum,
			double blockNum, double turnOverNum, double foulNum, double score) {
		super();
		this.reboundNum = reboundNum;
		this.assistNum = assistNum;
		this.shootHitRate = shootHitRate;
		this.threeHitRate = threeHitRate;
		this.freeThrowHitRate = freeThrowHitRate;
		this.stealNum = stealNum;
		this.blockNum = blockNum;
		this.turnOverNum = turnOverNum;
		this.foulNum = foulNum;
		this.score = score;
	}

	public double getReboundNum() {
		return reboundNum;
	}

	public double getAssistNum() {
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
}
