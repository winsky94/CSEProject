package po;

public class MatchPO {
	private int id;// 编号——构造函数里不要做参数
	private String date;// 时间
	private String teams;// 对阵队伍
	private String score; // 比分
	private String firstScore;// 第一节比分
	private String secondScore;// 第二节比分
	private String thirdScore;// 第三节比分
	private String fourthScore;// 第四节比分
	private String overTimeScore;// 加时赛比分
	
	public MatchPO(String date, String teams, String score, String firstScore,
			String secondScore, String thirdScore, String fourthScore,
			String overTimeScore) {
		super();
		this.date = date;
		this.teams = teams;
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
	public String getTeams() {
		return teams;
	}
	public String getScore() {
		return score;
	}
	public String getFirstScore() {
		return firstScore;
	}
	public String getSecondScore() {
		return secondScore;
	}
	public String getThirdScore() {
		return thirdScore;
	}
	public String getFourthScore() {
		return fourthScore;
	}
	public String getOverTimeScore() {
		return overTimeScore;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setTeams(String teams) {
		this.teams = teams;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public void setFirstScore(String firstScore) {
		this.firstScore = firstScore;
	}
	public void setSecondScore(String secondScore) {
		this.secondScore = secondScore;
	}
	public void setThirdScore(String thirdScore) {
		this.thirdScore = thirdScore;
	}
	public void setFourthScore(String fourthScore) {
		this.fourthScore = fourthScore;
	}
	public void setOverTimeScore(String overTimeScore) {
		this.overTimeScore = overTimeScore;
	}

	
}
