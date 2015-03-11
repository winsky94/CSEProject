package po;

public class MatchPO {
	private int id;// 编号——构造函数里不要做参数
	private String date;// 时间
	private String homeTeam;// 主场
	private String visitingTeam;// 客场
	private int score; // 比分
	private int firstScore;// 第一节比分
	private int secondScore;// 第二节比分
	private int thirdScore;// 第三节比分
	private int fourthScore;// 第四节比分
	private int overTimeScore;// 加时赛比分

	public MatchPO(int id, String date, String homeTeam, String visitingTeam,
			int score, int firstScore, int secondScore, int thirdScore,
			int fourthScore, int overTimeScore) {
		super();
		this.id = id;
		this.date = date;
		this.homeTeam = homeTeam;
		this.visitingTeam = visitingTeam;
		this.score = score;
		this.firstScore = firstScore;
		this.secondScore = secondScore;
		this.thirdScore = thirdScore;
		this.fourthScore = fourthScore;
		this.overTimeScore = overTimeScore;
	}

	public int getId() {
		return id;
	}

	public String getDate() {
		return date;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public String getVisitingTeam() {
		return visitingTeam;
	}

	public int getScore() {
		return score;
	}

	public int getFirstScore() {
		return firstScore;
	}

	public int getSecondScore() {
		return secondScore;
	}

	public int getThirdScore() {
		return thirdScore;
	}

	public int getFourthScore() {
		return fourthScore;
	}

	public int getOverTimeScore() {
		return overTimeScore;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public void setVisitingTeam(String visitingTeam) {
		this.visitingTeam = visitingTeam;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setFirstScore(int firstScore) {
		this.firstScore = firstScore;
	}

	public void setSecondScore(int secondScore) {
		this.secondScore = secondScore;
	}

	public void setThirdScore(int thirdScore) {
		this.thirdScore = thirdScore;
	}

	public void setFourthScore(int fourthScore) {
		this.fourthScore = fourthScore;
	}

	public void setOverTimeScore(int overTimeScore) {
		this.overTimeScore = overTimeScore;
	}
}
