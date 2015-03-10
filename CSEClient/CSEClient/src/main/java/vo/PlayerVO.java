package vo;

import po.CommonPO;

public class PlayerVO {
	private String id;// 编号
	private String name;// 球员名称
	private int number;// 球衣号码
	private String position;// 位置
	private String height;// 身高（英尺-英存）
	private String weight;// 体重（英镑）
	private String birth;// （月 日，年）
	private int age;// 年龄
	private int exp;// 球龄
	private String school;// 毕业学校

	private CommonPO commonPO;
	private String teamName;// 所属球队
	private String playedGames;// 参赛场数
	private double gameStartingNum;// 先发场数
	private double presentTime;// 在场时间
	private double offenNum;// 进攻数
	private double defenNum;// 防守数
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

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getNumber() {
		return number;
	}

	public String getPosition() {
		return position;
	}

	public String getHeight() {
		return height;
	}

	public String getWeight() {
		return weight;
	}

	public String getBirth() {
		return birth;
	}

	public int getAge() {
		return age;
	}

	public int getExp() {
		return exp;
	}

	public String getSchool() {
		return school;
	}

	public CommonPO getCommonPO() {
		return commonPO;
	}

	public String getTeamName() {
		return teamName;
	}

	public String getPlayedGames() {
		return playedGames;
	}

	public double getGameStartingNum() {
		return gameStartingNum;
	}

	public double getPresentTime() {
		return presentTime;
	}

	public double getOffenNum() {
		return offenNum;
	}

	public double getDefenNum() {
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
}
