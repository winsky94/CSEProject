package vo;

import java.util.ArrayList;

public class MatchVO {
	private String season;// 赛季
	private String date;// 时间
	private String visitingTeam;// 对阵队伍
	private String homeTeam;
	private ArrayList<String> detailScores;// 各节比分
	private ArrayList<RecordVO> records;// 球员比分数据记录
	int visitingShootHitNum = 0; // 投篮命中数
	int visitingShootAttemptNum = 0; // 投篮出手次数
	int visitingThreeHitNum = 0; // 三分命中数
	int visitingThreeAttemptNum = 0; // 三分出手数
	int visitingFreeThrowHitNum = 0; // 罚球命中数
	int visitingFreeThrowAttemptNum = 0; // 罚球出手数
	int visitingOffenReboundNum = 0; // 进攻篮板数
	int visitingDefenReboundNum = 0; // 防守篮板数
	int visitingAssistNum = 0;// 助攻数
	int visitingStealNum = 0;// 抢断数
	int visitingBlockNum = 0;// 盖帽数
	int visitingTurnOverNum = 0;// 失误数
	int visitingFoulNum = 0;// 犯规数
	int visitingScore = 0;// 得分

	int homeShootHitNum = 0; // 投篮命中数
	int homeShootAttemptNum = 0; // 投篮出手次数
	int homeThreeHitNum = 0; // 三分命中数
	int homeThreeAttemptNum = 0; // 三分出手数
	int homeFreeThrowHitNum = 0; // 罚球命中数
	int homeFreeThrowAttemptNum = 0; // 罚球出手数
	int homeOffenReboundNum = 0; // 进攻篮板数
	int homeDefenReboundNum = 0; // 防守篮板数
	int homeAssistNum = 0;// 助攻数
	int homeStealNum = 0;// 抢断数
	int homeBlockNum = 0;// 盖帽数
	int homeTurnOverNum = 0;// 失误数
	int homeFoulNum = 0;// 犯规数
	int homeScore = 0;// 得分

	public MatchVO(String season, String date, String visingTeam,
			String homeTeam){
		this.season = season;
		this.date = date;
		this.visitingTeam = visingTeam;
		this.homeTeam = homeTeam;
	}
	public MatchVO(String season, String date, String visitingTeam,
			String homeTeam, int visitingScore, int homeScore,
			ArrayList<String> detailScores, ArrayList<RecordVO> records) {
		super();
		this.season = season;
		this.date = date;
		this.visitingTeam = visitingTeam;
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
	
	public void addVisitingScore(int visitingScore){
		this.visitingScore+=visitingScore;
	}

	public int getHomeScore() {
		return homeScore;
	}
	
	public void addHomeScore(int homeScore){
		this.homeScore+=homeScore;
	}

	public ArrayList<String> getDetailScores() {
		return detailScores;
	}

	public ArrayList<RecordVO> getRecords() {
		return records;
	}
	
	public int getVisitingShootHitNum(){
		return visitingShootHitNum;
	}
	
	public int getVisitingShootAttemptNum(){
		return visitingShootAttemptNum;
	}
	
	public int getVisitingThreeHitNum(){
		return visitingThreeHitNum;
	}
	
	public int getVisitingThreeAttemptNum(){
		return visitingThreeAttemptNum;
	}
	
	public int getVisitingFreeThrowHitNum(){
		return visitingFreeThrowHitNum;
	}
	
	public int getVisitingFreeThrowAttemptNum(){
		return visitingFreeThrowAttemptNum;
	}
	
	public int getVisitingOffenReboundNum(){
		return visitingOffenReboundNum;
	}
	
	public int getVisitingDefenReboundNum(){
		return visitingDefenReboundNum;
	}
	
	public int getVisitingAssistNum(){
		return visitingAssistNum;
	}
	
	public int getVisitingStealNum(){
		return visitingStealNum;
	}
	
	public int getVisitingBlockNum(){
		return visitingBlockNum;
	}
	
	public int getVisitingTurnOverNum(){
		return visitingTurnOverNum;
	}
	
	public int getVisitingFoulNum(){
		return visitingFoulNum;
	}
	


	
	public void addVisitingShootHitNum(int visitingShootHitNum){
		this.visitingShootHitNum+=visitingShootHitNum;
	}
	
	public void addVisitingShootAttemptNum(int visitingShootAttemptNum){
		this.visitingShootAttemptNum+=visitingShootAttemptNum;
	}
	
	public void addVisitingThreeHitNum(int visitingThreeHitNum){
		this.visitingThreeHitNum+=visitingThreeHitNum;
	}
	
	public void addVisitingThreeAttemptNum(int visitingThreeAttemptNum){
		this.visitingThreeAttemptNum+=visitingThreeAttemptNum;
	}
	
	public void addVisitingFreeThrowHitNum(int visitingFreeThrowHitNum){
		this.visitingFreeThrowHitNum+=visitingFreeThrowHitNum;
	}
	
	public void addVisitingFreeThrowAttemptNum(int visitingFreeThrowAttemptNum){
		this.visitingFreeThrowAttemptNum+=visitingFreeThrowAttemptNum;
	}
	
	public void addVisitingOffenReboundNum(int visitingOffenReboundNum){
		this.visitingOffenReboundNum+=visitingOffenReboundNum;
	}
	
	public void addVisitingDefenReboundNum(int visitingDefenReboundNum){
		this.visitingDefenReboundNum+=visitingDefenReboundNum;
	}
	
	public void addVisitingAssistNum(int visitingAssistNum){
		this.visitingAssistNum+=visitingAssistNum;
	}
	
	public void addVisitingStealNum(int visitingStealNum){
		this.visitingStealNum+=visitingStealNum;
	}
	
	public void addVisitingBlockNum(int visitingBlockNum){
		this.visitingBlockNum+=visitingBlockNum;
	}
	
	public void addVisitingTurnOverNum(int visitingTurnOverNum){
		this.visitingTurnOverNum+=visitingTurnOverNum;
	}
	
	public void addVisitingFoulNum(int visitingFoulNum){
		this.visitingFoulNum+=visitingFoulNum;
	}
	
	
	
	
	
	
	public int getHomeShootHitNum(){
		return homeShootHitNum;
	}
	
	public int getHomeShootAttemptNum(){
		return homeShootAttemptNum;
	}
	
	public int getHomeThreeHitNum(){
		return homeThreeHitNum;
	}
	
	public int getHomeThreeAttemptNum(){
		return homeThreeAttemptNum;
	}
	
	public int getHomeFreeThrowHitNum(){
		return homeFreeThrowHitNum;
	}
	
	public int getHomeFreeThrowAttemptNum(){
		return homeFreeThrowAttemptNum;
	}
	
	public int getHomeOffenReboundNum(){
		return homeOffenReboundNum;
	}
	
	public int getHomeDefenReboundNum(){
		return homeDefenReboundNum;
	}
	
	public int getHomeAssistNum(){
		return homeAssistNum;
	}
	
	public int getHomeStealNum(){
		return homeStealNum;
	}
	
	public int getHomeBlockNum(){
		return homeBlockNum;
	}
	
	public int getHomeTurnOverNum(){
		return homeTurnOverNum;
	}
	
	public int getHomeFoulNum(){
		return homeFoulNum;
	}
	
	
	public void addHomeShootHitNum(int visitingShootHitNum){
		this.visitingShootHitNum+=visitingShootHitNum;
	}
	
	public void addHomeShootAttemptNum(int visitingShootAttemptNum){
		this.visitingShootAttemptNum+=visitingShootAttemptNum;
	}
	
	public void addHomeThreeHitNum(int visitingThreeHitNum){
		this.visitingThreeHitNum+=visitingThreeHitNum;
	}
	
	public void addHomeThreeAttemptNum(int visitingThreeAttemptNum){
		this.visitingThreeAttemptNum+=visitingThreeAttemptNum;
	}
	
	public void addHomeFreeThrowHitNum(int visitingFreeThrowHitNum){
		this.visitingFreeThrowHitNum+=visitingFreeThrowHitNum;
	}
	
	public void addHomeFreeThrowAttemptNum(int visitingFreeThrowAttemptNum){
		this.visitingFreeThrowAttemptNum+=visitingFreeThrowAttemptNum;
	}
	
	public void addHomeOffenReboundNum(int visitingOffenReboundNum){
		this.visitingOffenReboundNum+=visitingOffenReboundNum;
	}
	
	public void addHomeDefenReboundNum(int visitingDefenReboundNum){
		this.visitingDefenReboundNum+=visitingDefenReboundNum;
	}
	
	public void addHomeAssistNum(int visitingAssistNum){
		this.visitingAssistNum+=visitingAssistNum;
	}
	
	public void addHomeStealNum(int visitingStealNum){
		this.visitingStealNum+=visitingStealNum;
	}
	
	public void addHomeBlockNum(int visitingBlockNum){
		this.visitingBlockNum+=visitingBlockNum;
	}
	
	public void addHomeTurnOverNum(int visitingTurnOverNum){
		this.visitingTurnOverNum+=visitingTurnOverNum;
	}
	
	public void addHomeFoulNum(int visitingFoulNum){
		this.visitingFoulNum+=visitingFoulNum;
	}
	
	
}
