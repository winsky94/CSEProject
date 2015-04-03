package vo;

import java.util.ArrayList;

public class MatchVO {
	private String season;// 赛季
	private String date;// 时间
	private String visitingTeam;// 对阵队伍
	private String homeTeam;
	private int visitingScore; // 比分
	private int homeScore;
	private ArrayList<String> detailScores;// 各节比分
	private ArrayList<RecordVO> records;// 球员比分数据记录

	public MatchVO(String season, String date, String visingTeam,
			String homeTeam, int visitingScore, int homeScore,
			ArrayList<String> detailScores, ArrayList<RecordVO> records) {
		super();
		this.season = season;
		this.date = date;
		this.visitingTeam = visingTeam;
		this.homeTeam = homeTeam;
		this.visitingScore = visitingScore;
		this.homeScore = homeScore;
		this.detailScores = detailScores;
		this.records = records;
	}

	public String getSeason() {
		return season;
	}

	public String getDate() {
		return date;
	}

	public String getVisitingTeam() {
		return visitingTeam;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public int getVisitingScore() {
		return visitingScore;
	}

	public int getHomeScore() {
		return homeScore;
	}

	public ArrayList<String> getDetailScores() {
		return detailScores;
	}

	public ArrayList<RecordVO> getRecords() {
		return records;
	}
}
