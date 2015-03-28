package po;

import java.io.Serializable;
import java.util.ArrayList;

public class MatchPO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int matchID;// 编号
	private String season;// 赛季
	private String date;// 时间
	private String visingTeam;// 对阵队伍
	private String homeTeam;
	private String score; // 比分
	private ArrayList<String> detailScores;// 各节比分
	private ArrayList<RecordPO> records;// 球员比分数据记录

	public MatchPO(int matchID, String season, String date, String visingTeam,
			String homeTeam, String score, ArrayList<String> detailScores,
			ArrayList<RecordPO> records) {
		super();
		this.matchID = matchID;
		this.season = season;
		this.date = date;
		this.visingTeam = visingTeam;
		this.homeTeam = homeTeam;
		this.score = score;
		this.detailScores = detailScores;
		this.records = records;
	}

	public int getMatchID() {
		return matchID;
	}

	public String getSeason() {
		return season;
	}

	public String getDate() {
		return date;
	}

	public String getVisingTeam() {
		return visingTeam;
	}

	public String getHomeTeam() {
		return homeTeam;
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

	public void setMatchID(int matchID) {
		this.matchID = matchID;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setVisingTeam(String visingTeam) {
		this.visingTeam = visingTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
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
