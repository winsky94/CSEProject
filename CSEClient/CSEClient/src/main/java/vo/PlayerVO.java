package vo;

public class PlayerVO {
	private int id;// 编号
	private String name;// 球员名称
	private int number;// 球衣号码
	private String position;// 位置
	private String height;// 身高（英尺-英存）
	private int weight;// 体重（英镑）
	private String birth;// （月 日，年）
	private int age;// 年龄
	private String exp;// 球龄
	private String school;// 毕业学校

	private String teamName;// 所属球队
	private int playedGames;// 参赛场数
	private int gameStartingNum;// 先发场数
	private int reboundNum;// 篮板数
	private int assistNum;// 助攻数
	private String presentTime;// 在场时间
	private double shootHitRate;// 投篮命中率
	private double threeHitRate;// 三分命中率
	private double freeThrowHitRate;// 罚球命中率
	private int offenNum;// 进攻数
	private int defenNum;// 防守数
	private int stealNum;// 抢断数
	private int blockNum;// 盖帽数
	private int turnOverNum;// 失误数
	private int foulNum;// 犯规数
	private int score;// 得分
	private double efficiency;// 效率
	private double recentFiveMatchesScoreUpRate;// 近五场得分提升率
	private double recentFiveMatchesReboundUpRate;// 近五场篮板提升率
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
	private double usageRate;// 使用率
	private double score_rebound_assist;// 得分/篮板/助攻
	private int doubleDoubleNum;// 两双

	public PlayerVO(int id, String name, int number, String position,
			String height, int weight, String birth, int age, String exp,
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

	public PlayerVO(int id, String name, int number, String position,
			String height, int weight, String birth, int age, String exp,
			String school, String teamName, int playedGames,
			int gameStartingNum, int reboundNum, int assistNum,
			String presentTime, double shootHitRate, double threeHitRate,
			double freeThrowHitRate, int offenNum, int defenNum, int stealNum,
			int blockNum, int turnOverNum, int foulNum, int score,
			double efficiency, double recentFiveMatchesScoreUpRate,
			double recentFiveMatchesReboundUpRate, double gmScEfficiencyValue,
			double trueHitRate, double shootHitEfficiency, double reboundRate,
			double offenReboundRate, double defenReboundRate,
			double assistRate, double stealRate, double blockRate,
			double turnOverRate, double usageRate, double score_rebound_assist,
			int doubleDoubleNum) {
		super();
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
		this.teamName = teamName;
		this.playedGames = playedGames;
		this.gameStartingNum = gameStartingNum;
		this.reboundNum = reboundNum;
		this.assistNum = assistNum;
		this.presentTime = presentTime;
		this.shootHitRate = shootHitRate;
		this.threeHitRate = threeHitRate;
		this.freeThrowHitRate = freeThrowHitRate;
		this.offenNum = offenNum;
		this.defenNum = defenNum;
		this.stealNum = stealNum;
		this.blockNum = blockNum;
		this.turnOverNum = turnOverNum;
		this.foulNum = foulNum;
		this.score = score;
		this.efficiency = efficiency;
		this.recentFiveMatchesReboundUpRate = recentFiveMatchesReboundUpRate;
		this.recentFiveMatchesScoreUpRate = recentFiveMatchesScoreUpRate;
		GmScEfficiencyValue = gmScEfficiencyValue;
		this.trueHitRate = trueHitRate;
		this.shootHitEfficiency = shootHitEfficiency;
		this.reboundRate = reboundRate;
		this.offenReboundRate = offenReboundRate;
		this.defenReboundRate = defenReboundRate;
		this.assistRate = assistRate;
		this.stealRate = stealRate;
		this.blockRate = blockRate;
		this.turnOverRate = turnOverRate;
		this.usageRate = usageRate;
		this.score_rebound_assist = score_rebound_assist;
		this.doubleDoubleNum = doubleDoubleNum;
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

	public String getExp() {
		return exp;
	}

	public String getSchool() {
		return school;
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

	public int getReboundNum() {
		return reboundNum;
	}

	public int getAssistNum() {
		return assistNum;
	}

	public String getPresentTime() {
		return presentTime;
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

	public int getOffenNum() {
		return offenNum;
	}

	public int getDefenNum() {
		return defenNum;
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

	public double getEfficiency() {
		return efficiency;
	}

	public double getRecentFiveMatchesReboundUpRate() {
		return recentFiveMatchesReboundUpRate;
	}

	public double getRecentFiveMatchesScoreUpRate() {
		return recentFiveMatchesScoreUpRate;
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

	public double getUsageRate() {
		return usageRate;
	}

	public int getDoubleDoubleNum() {
		return doubleDoubleNum;
	}

	public double getScore_rebound_assist() {
		return score_rebound_assist;
	}
}
