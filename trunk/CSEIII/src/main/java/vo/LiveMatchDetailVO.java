package vo;

/**
 * 比赛直播的VO
 * @author Administrator
 *
 */
public class LiveMatchDetailVO {
	private String season;
	private String date;
	private String teams;
	private String content;

	public LiveMatchDetailVO(String season, String date, String teams,
			String content) {
		this.season = season;
		this.date = date;
		this.teams = teams;
		this.content = content;
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

	public String getContent() {
		return content;
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

	public void setContent(String content) {
		this.content = content;
	}

}
