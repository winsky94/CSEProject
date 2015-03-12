package po;

import java.io.Serializable;

public class CommonPO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int reboundNum;// 篮板数
	private int assistNum;// 助攻数
	private double shootHitRate;// 投篮命中率
	private double threeHitRate;// 三分命中率
	private double freeThrowHitRate;// 罚球命中率
	private int stealNum;// 抢断数
	private int blockNum;// 盖帽数
	private int turnOverNum;// 失误数
	private int foulNum;// 犯规数
	private int score;// 得分

	public CommonPO(int reboundNum, int assistNum, double shootHitRate,
			double threeHitRate, double freeThrowHitRate, int stealNum,
			int blockNum, int turnOverNum, int foulNum, int score) {
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
