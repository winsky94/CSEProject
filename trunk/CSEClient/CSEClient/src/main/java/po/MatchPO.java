package po;

import java.util.ArrayList;

public class MatchPO {
	private int id;// 编号
	private String season;// 赛季
	private String date;// 时间
	private String teams;// 对阵队伍
	private String score; // 比分
	private ArrayList<String> detailScores;// 各节比分
	private ArrayList<RecordPO> records;// 球员比分数据记录

	public MatchPO(int id, String season, String date, String teams,
			String score, ArrayList<String> detailScores,
			ArrayList<RecordPO> records) {
		super();
		this.id = id;
		this.season = season;
		this.date = date;
		this.teams = teams;
		this.score = score;
		this.detailScores = detailScores;
		this.records = records;
	}

	public int getId() {
		return id;
	}

	public String getSeason() {
		return season;
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

	public ArrayList<String> getDetailScores() {
		return detailScores;
	}

	public ArrayList<RecordPO> getRecords() {
		return records;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSeason(String season) {
		this.season = season;
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

	public void setDetailScores(ArrayList<String> detailScores) {
		this.detailScores = detailScores;
	}

	public void setRecords(ArrayList<RecordPO> records) {
		this.records = records;
	}

}
