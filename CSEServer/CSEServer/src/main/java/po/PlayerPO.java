package po;

import java.io.Serializable;

public class PlayerPO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;// 编号
	private String name;// 球员名称
	private int number;// 球衣号码
	private String position;// 位置
	private String height;// 身高（英尺-英存）
	private int weight;// 体重（英镑）
	private String birth;// （月 日，年）
	private int age;// 年龄
	private int exp;// 球龄
	private String school;// 毕业学校

	private CommonPO commonPO;
	private String teamName;// 所属球队
	private int playedGames;// 参赛场数
	private int gameStartingNum;// 先发场数
	private double presentTime;// 在场时间
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

	public PlayerPO(int id, String name, int number, String position,
			String height, int weight, String birth, int age, int exp,
			String school) {
		this.id = id;
		this.name = name;
		this.number = number;
		this.position = position;
		this.height = height;
		this.weight = weight;
		this.birth = birth;
		this.age = age;
		this.exp = exp;
		this.school = school;
	}

	public int getId() {
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

	public int getWeight() {
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

	public int getPlayedGames() {
		return playedGames;
	}

	public int getGameStartingNum() {
		return gameStartingNum;
	}

	public double getPresentTime() {
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

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public void setCommonPO(CommonPO commonPO) {
		this.commonPO = commonPO;
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

	public void setPresentTime(double presentTime) {
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
