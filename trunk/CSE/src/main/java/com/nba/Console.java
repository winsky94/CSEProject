package com.nba;

import java.io.PrintStream;
import java.util.ArrayList;

import test.data.PlayerHighInfo;
import test.data.PlayerHotInfo;
import test.data.PlayerKingInfo;
import test.data.PlayerNormalInfo;
import test.data.TeamHighInfo;
import test.data.TeamHotInfo;
import test.data.TeamNormalInfo;
import vo.PlayerVO;
import vo.TeamVO;
import bl.DataSourse;
import bl.Player;
import bl.Team;
import blservice.PlayerBLService;
import blservice.TeamBLService;

public class Console {

	private String[] player_sort = { "point", "rebound", "assist", "blockShot",
			"steal", "foul", "fault", "minute", "efficient", "shot", "three",
			"penalty", "doubleTwo", "realShot", "GmSc", "shotEfficient",
			"reboundEfficient", "offendReboundEfficient",
			"defendReboundEfficient", "assistEfficent", "stealEfficient",
			"blockShotEfficient", "faultEfficient", "frequency" };
	private String[] player_real_sort = { "score", "reboundNum", "assistNum",
			"blockNum", "stealNum", "foulNum", "turnOverNum", "presentTime",
			"efficiency", "shootHitRate", "threeHitRate", "freeThrowHitRate",
			"doubleDoubleNum", "trueHitRate", "GmScEfficiencyValue",
			"shootEfficiency", "reboundRate", "offenReboundRate",
			"defenReboundRate", "assistRate", "stealRate", "blockRate",
			"turnOverRate", "usageRate" };

	private String[] team_sort = { "point", "rebound", "assist", "blockShot",
			"steal", "foul", "fault", "shot", "three", "penalty",
			"defendRebound", "offendRebound", "offendRound", "offendEfficient",
			"defendEfficient", "offendReboundEfficient",
			"defendReboundEfficient", "stealEfficeint", "assistEfficient" };
	private String[] team_sort_real = { "score", "reboundNum", "assistNum",
			"blockNum", "stealNum", "foulNum", "turnOverNum", "shootHitRate",
			"threeHitRate", "freeThrowHitRate", "defenReboundNum",
			"offenReboundNum", "offenRound", "offenEfficiency",
			"defenEfficiency", "offenReboundEfficiency",
			"defenReboundEfficiency", "stealEfficiency", "assistRate" };

	private boolean isPTotal = false;
	private boolean isTTotal = false;
	private String playerhotField = "";
	private String teamhotField = "";
	private String playerkingField = "";
	private boolean isSeason = false;
	private int teamNum = 30;
	private int playerNum = 50;
	private boolean isPHigh = false;// 是否返回高阶数据
	private boolean isTHigh = false;
	private String pAge = "All";
	private String pUnion = "All";
	private String pPosition = "All";
	private ArrayList<String> playerSort = new ArrayList<String>();//
	private ArrayList<String> sortP = new ArrayList<String>();
	private ArrayList<String> playerFilter = new ArrayList<String>();// 优先级
																		// field.value
	private ArrayList<String> teamSort = new ArrayList<String>();// aas.dsec
																	// ddd.asc
																	// 邮件及
	private ArrayList<String> sortT = new ArrayList<String>();// 可以不声明

	PlayerBLService player = new Player(3);
	TeamBLService team = new Team();

	public void execute(PrintStream out, String[] args) {
		ArrayList<String> command = new ArrayList<String>();
		for (int i = 1; i < args.length; i++)
			command.add(args[i]);
		if (args[0].equals("-player")) {
			// 球队命令解析
			if (command.size() == 0) {
				ArrayList<PlayerVO> result = player.getOrderedPlayersByAverage(
						"score", "desc", 50);
				for (PlayerVO vo : result) {
					// =====================================================
					PlayerNormalInfo playerNormalInfo = setPlayerNormalInfo(vo);
					out.append(playerNormalInfo.toString());
				}
				return;// 返回得分前50的球员的场均比赛数据 调用
			}
			int i = 0;
			if ((i = command.indexOf("-n")) >= 0)
				playerNum = Integer.parseInt(command.get(i + 1));
			else
				playerNum = 50;

			if ((i = command.indexOf("-hot")) >= 0) {
				playerhotField = command.get(i + 1);
				PlayerHotFieldChange();
				// 调用 player hot 方法
				ArrayList<PlayerVO> result = player.selectPlayersByAverage(
						"all", "all", 5,playerhotField,"desc", playerNum);
				for (PlayerVO vo : result) {
					// =====================================================
					PlayerHotInfo playerHotInfo = setplayerHotInfo(vo,
							playerhotField);
					out.append(playerHotInfo.toString());
				}
			} else if ((i = command.indexOf("-king")) >= 0) {
				playerkingField = command.get(i + 1);
				if (command.get(i + 2).equals("-season"))
					isSeason = true;
				else
					isSeason = false;
				PlayerKingFieldChange();
				// 调用返回数据王信息
				ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
				if (isSeason) {
					result = player.getSeasonHotPlayer("all", playerkingField,playerNum);
					for (PlayerVO vo : result) {
						// =====================================================
						PlayerKingInfo playerKingInfo = setPlayerKingInfo(vo,
								playerkingField);
						out.append(playerKingInfo.toString());
					}
				} else {
					result = player.getDayHotPlayer(playerkingField,playerNum);
					for (PlayerVO vo : result) {
						// =====================================================
						PlayerKingInfo playerKingInfo = setPlayerKingInfo(vo,
								playerkingField);
						out.append(playerKingInfo.toString());
					}
				}
			} else {
				// contain all
				playerSort.clear();
				sortP.clear();
				if ((i = command.indexOf("-sort")) >= 0) {
					String[] t = command.get(i + 1).split(",");
					for (String s : t) {
						String[] p = s.split(".");
						playerSort.add(p[0]);
						sortP.add(p[1]);
					}
				} else {
					playerSort.add("score");
					playerSort.add("trueHitRate");
					sortP.add("desc");
					sortP.add("desc");
				}
				if (command.indexOf("-high") >= 0) {
					isPHigh = true;
					// 返回高阶数据的 sort方法 其他返回均未基本数据
				} else
					isPHigh = false;

				if (command.indexOf("-total") >= 0)
					// 返回的是总和数据
					isPTotal = true;
				else
					isPTotal = false;
				PlayerSortFieldChange();
				if ((i = command.indexOf("-filter")) > 0) {
					playerFilter.clear();
					String[] t = command.get(i + 1).split(",");
					for (String s : t)
						playerFilter.add(s);
					PalyerFilterChange();
					// 调用sort+filter方法
					ArrayList<PlayerVO> result = new ArrayList<PlayerVO>();
					if (isPTotal) {
						result = player.selectPlayersBySeason("all", pPosition,
								pUnion, pAge, playerNum);
						for (PlayerVO vo : result) {
							// =====================================================
							PlayerNormalInfo playerNormalInfo = setPlayerNormalInfo(vo);
							out.append(playerNormalInfo.toString());
						}
					} else {
						result = player.selectPlayersByAverage(pPosition,
								pUnion, pAge, playerNum);
						for (PlayerVO vo : result) {
							// =====================================================
							PlayerNormalInfo playerNormalInfo = setPlayerNormalInfo(vo);
							out.append(playerNormalInfo.toString());
						}
					}
					clearFilter();
				} else {
					// 调用sort方法
					if (isPTotal) {
						ArrayList<PlayerVO> result = player
								.selectPlayersBySeason("all",
										playerSort.get(0), sortP.get(0), "all",
										playerNum);
						for (PlayerVO vo : result) {
							// =====================================================
							if (isPHigh) {
								PlayerHighInfo playerHighInfo = setPlayerHighInfo(vo);
								out.append(playerHighInfo.toString());
							} else {
								PlayerNormalInfo playerNormalInfo = setPlayerNormalInfo(vo);
								out.append(playerNormalInfo.toString());
							}
						}
					} else {
						ArrayList<PlayerVO> result = player
								.selectPlayersByAverage("all", "all", "all",
										playerNum);
						for (PlayerVO vo : result) {
							// =====================================================
							if (isPHigh) {
								PlayerHighInfo playerHighInfo = setPlayerHighInfo(vo);
								out.append(playerHighInfo.toString());
							} else {
								PlayerNormalInfo playerNormalInfo = setPlayerNormalInfo(vo);
								out.append(playerNormalInfo.toString());
							}
						}
					}
				}
			}
		} else if (args[0].equals("-team")) {
			// 球员命令解析
			if (command.size() == 0) {
				ArrayList<TeamVO> result = team.getOrderedTeamsByAverage(
						"score", "desc", 30);
				for (TeamVO vo : result) {
					// ===================================================
					TeamNormalInfo teamNormalInfo = setTeamNormalInfo(vo);
					out.append(teamNormalInfo.toString());
				}
				return;// 返回得分前30的球队的场均比赛数据
			}
			int i = 0;
			if ((i = command.indexOf("-n")) >= 0)
				teamNum = Integer.parseInt(command.get(i + 1));
			else
				teamNum = 50;

			if ((i = command.indexOf("-hot")) >= 0) {
				teamhotField = command.get(i + 1);
				TeamHotFieldChange();
				// 调用 team hot 方法
				ArrayList<TeamVO> result = team.getSeasonHotTeam("all",
						teamhotField, teamNum);
				for (TeamVO vo : result) {
					// ===================================================
					TeamHotInfo teamHotInfo = setTeamHotInfo(vo, teamhotField);
					out.append(teamHotInfo.toString());
				}
			} else {
				// contain all
				teamSort.clear();
				sortT.clear();
				if ((i = command.indexOf("-sort")) >= 0) {

					String[] t = command.get(i + 1).split(",");
					for (String s : t) {
						String[] p = s.split(".");
						teamSort.add(p[0]);
						sortT.add(p[1]);
					}

				} else {
					teamSort.add("score");
					teamSort.add("winRate");
					sortT.add("desc");
					sortT.add("desc");
				}
				if (command.indexOf("-high") >= 0) {
					isTHigh = true;
					// 返回高阶数据的 sort方法 其他返回均未基本数据
				} else
					isTHigh = false;

				if (command.indexOf("-total") >= 0)
					// 返回的是总和数据
					isTTotal = true;
				else
					isTTotal = false;
				TeamSortFieldChange();
				// 调用 sort方法
				ArrayList<TeamVO> result = new ArrayList<TeamVO>();
				if (isTTotal) {
					result = team.getOrderedTeamsBySeason("all",
							teamSort.get(0), sortT.get(0), teamNum);
					for (TeamVO vo : result) {
						// ===================================================
						if (isTHigh) {
							TeamHighInfo teamHighInfo = setTeamHighInfo(vo);
							out.append(teamHighInfo.toString());
						} else {
							TeamNormalInfo teamNormalInfo = setTeamNormalInfo(vo);
							out.append(teamNormalInfo.toString());
						}
					}
				} else {
					result = team.getOrderedTeamsByAverage(teamSort.get(0),
							sortT.get(0), teamNum);
					for (TeamVO vo : result) {
						// ===================================================
						if (isTHigh) {
							TeamHighInfo teamHighInfo = setTeamHighInfo(vo);
							out.append(teamHighInfo.toString());
						} else {
							TeamNormalInfo teamNormalInfo = setTeamNormalInfo(vo);
							out.append(teamNormalInfo.toString());
						}
					}
				}
			}
		} else {
			// 设置数据源
			setData(args[1]);
		}

	}

	public void PlayerHotFieldChange() {
		if (!playerhotField.equals("score"))
			playerhotField += "Num";
	}

	public void TeamHotFieldChange() {
		for (int i = 1; i < 12; i++)
			if (teamhotField.equals(team_sort[i])) {
				teamhotField = team_sort_real[i];
				break;
			}
	}

	public void PlayerKingFieldChange() {
		if (!playerkingField.equals("score"))
			playerkingField += "Num";
	}

	public void PlayerSortFieldChange() {
		for (int i = 0; i < playerSort.size(); i++) {
			String s = playerSort.get(i);
			for (int j = 0; j < player_sort.length; j++)
				if (s.equals(player_sort[j])) {
					playerSort.set(i, player_real_sort[j]);
					break;
				}
		}
	}

	public void TeamSortFieldChange() {
		for (int i = 0; i < teamSort.size(); i++) {
			String s = teamSort.get(i);
			for (int j = 0; j < team_sort.length; j++)
				if (s.equals(team_sort[j])) {
					teamSort.set(i, team_sort_real[j]);
					break;
				}
		}
	}

	public void PalyerFilterChange() {
		for (String s : playerFilter) {
			if (s.contains("position"))
				pPosition = s.split(".")[1];
			else if (s.contains("league"))
				pUnion = s.split(".")[1];
			else
				pAge = s.split(".")[1];
		}

	}

	public void clearFilter() {
		pAge = pPosition = pUnion = "All";
	}

	/**
	 * 设置数据源地址
	 * 
	 * @param address
	 */
	private void setData(String address) {
		DataSourse.dataSourse = address;
	}

	private PlayerNormalInfo setPlayerNormalInfo(PlayerVO vo) {
		PlayerNormalInfo playerNormalInfo = new PlayerNormalInfo();
		playerNormalInfo.setAge(vo.getAge());
		playerNormalInfo.setAssist(vo.getAssistNum());
		playerNormalInfo.setBlockShot(vo.getBlockNum());
		playerNormalInfo.setDefend(vo.getDefenReboundNum());
		playerNormalInfo.setEfficiency(vo.getEfficiency());
		playerNormalInfo.setFault(vo.getTurnOverNum());
		playerNormalInfo.setFoul(vo.getFoulNum());
		playerNormalInfo.setMinute(vo.getPresentTime());
		playerNormalInfo.setName(vo.getName());
		playerNormalInfo.setNumOfGame(vo.getPlayedGames());
		playerNormalInfo.setOffend(vo.getOffenReboundNum());
		playerNormalInfo.setPenalty(vo.getShootHitRate());
		playerNormalInfo.setPoint(vo.getScore());
		playerNormalInfo.setRebound(vo.getReboundNum());
		playerNormalInfo.setShot(vo.getShootHitRate());
		playerNormalInfo.setStart(vo.getGameStartingNum());
		playerNormalInfo.setSteal(vo.getStealNum());
		playerNormalInfo.setTeamName(vo.getOwingTeam());
		playerNormalInfo.setThree(vo.getThreeHitRate());

		return playerNormalInfo;
	}

	// @倩倩
	private PlayerHighInfo setPlayerHighInfo(PlayerVO vo) {
		PlayerHighInfo playerHighInfo = new PlayerHighInfo();
		playerHighInfo.setAssistEfficient(vo.getAssistRate());
		playerHighInfo.setBlockShotEfficient(vo.getBlockRate());
		playerHighInfo.setDefendReboundEfficient(vo.getDefenReboundRate());
		playerHighInfo.setFaultEfficient(vo.getTurnOverRate());
		playerHighInfo.setFrequency(vo.getUsageRate());
		playerHighInfo.setGmSc(vo.getGmScEfficiencyValue());
		// ===============球员联盟是个什么鬼@倩倩
		playerHighInfo.setLeague(vo.getLeague());
		playerHighInfo.setName(vo.getName());
		playerHighInfo.setOffendReboundEfficient(vo.getOffenReboundRate());
		playerHighInfo.setPosition(vo.getPosition());
		playerHighInfo.setRealShot(vo.getTrueHitRate());
		playerHighInfo.setReboundEfficient(vo.getReboundRate());
		playerHighInfo.setShotEfficient(vo.getShootHitEfficiency());
		playerHighInfo.setStealEfficient(vo.getStealRate());
		playerHighInfo.setTeamName(vo.getOwingTeam());
		return playerHighInfo;
	}

	private PlayerHotInfo setplayerHotInfo(PlayerVO vo, String field) {
		PlayerHotInfo playerHotInfo = new PlayerHotInfo();
		playerHotInfo.setField(field);
		playerHotInfo.setName(vo.getName());
		playerHotInfo.setPosition(vo.getPosition());
		playerHotInfo.setTeamName(vo.getOwingTeam());
		double upgradeRate = 0;
		double value = 0;
		if (field.equals("score")) {
			upgradeRate = vo.getRecentFiveMatchesScoreUpRate();
			value = vo.getScore();
		} else if (field.equals("reboundNum")) {
			upgradeRate = vo.getRecentFiveMatchesReboundUpRate();
			value = vo.getReboundNum();
		} else if (field.equals("assistNum")) {
			upgradeRate = vo.getRecentFiveMatchesAssistUpRate();
			value = vo.getAssistNum();
		}
		playerHotInfo.setUpgradeRate(upgradeRate);
		playerHotInfo.setValue(value);
		return playerHotInfo;
	}

	private PlayerKingInfo setPlayerKingInfo(PlayerVO vo, String field) {
		PlayerKingInfo playerKingInfo = new PlayerKingInfo();
		playerKingInfo.setField(field);
		playerKingInfo.setName(vo.getName());
		playerKingInfo.setPosition(vo.getPosition());
		playerKingInfo.setTeamName(vo.getOwingTeam());
		double value = 0;
		if (field.equals("score")) {
			value = vo.getScore();
		} else if (field.equals("reboundNum")) {
			value = vo.getReboundNum();
		} else if (field.equals("assistNum")) {
			value = vo.getAssistNum();
		}
		playerKingInfo.setValue(value);
		return playerKingInfo;
	}

	private TeamNormalInfo setTeamNormalInfo(TeamVO vo) {
		TeamNormalInfo teamNormalInfo = new TeamNormalInfo();
		teamNormalInfo.setAssist(vo.getAssistNum());
		teamNormalInfo.setBlockShot(vo.getBlockNum());
		teamNormalInfo.setDefendRebound(vo.getDefenReboundNum());
		teamNormalInfo.setFault(vo.getTurnOverNum());
		teamNormalInfo.setFoul(vo.getFoulNum());
		teamNormalInfo.setNumOfGame(vo.getMatchesNum());
		teamNormalInfo.setOffendRebound(vo.getOffenReboundNum());
		teamNormalInfo.setPenalty(vo.getFreeThrowHitRate());
		teamNormalInfo.setPoint(vo.getScore());
		teamNormalInfo.setRebound(vo.getReboundNum());
		teamNormalInfo.setShot(vo.getShootHitRate());
		teamNormalInfo.setSteal(vo.getStealNum());
		teamNormalInfo.setTeamName(vo.getTeamName());
		teamNormalInfo.setThree(vo.getThreeHitRate());
		return teamNormalInfo;
	}

	private TeamHighInfo setTeamHighInfo(TeamVO vo) {
		TeamHighInfo teamHighInfo = new TeamHighInfo();
		teamHighInfo.setAssistEfficient(vo.getAssistRate());
		teamHighInfo.setDefendEfficient(vo.getDefenEfficiency());
		teamHighInfo.setDefendReboundEfficient(vo.getDefenReboundEfficiency());
		teamHighInfo.setOffendEfficient(vo.getOffenEfficiency());
		teamHighInfo.setOffendReboundEfficient(vo.getOffenReboundEfficiency());
		teamHighInfo.setOffendRound(vo.getOffenRound());
		teamHighInfo.setStealEfficient(vo.getStealEfficiency());
		teamHighInfo.setTeamName(vo.getAbLocation());
		teamHighInfo.setWinRate(vo.getWinRate());
		return teamHighInfo;
	}

	private TeamHotInfo setTeamHotInfo(TeamVO vo, String hotField) {
		TeamHotInfo teamHotInfo = new TeamHotInfo();
		teamHotInfo.setLeague(vo.getConference());
		teamHotInfo.setTeamName(vo.getAbLocation());
		teamHotInfo.setField(hotField);
		double value = 0;

		if (hotField.equals("score")) {
			value = vo.getScore();
		} else if (hotField.equals("reboundNum")) {
			value = vo.getReboundNum();
		} else if (hotField.equals("assistNum")) {
			value = vo.getAssistNum();
		} else if (hotField.equals("blockNum")) {
			value = vo.getBlockNum();
		} else if (hotField.equals("stealNum")) {
			value = vo.getStealNum();
		} else if (hotField.equals("foulNum")) {
			value = vo.getFoulNum();
		} else if (hotField.equals("turnOverNum")) {
			value = vo.getTurnOverNum();
		} else if (hotField.equals("shootHitRate")) {
			value = vo.getShootHitRate();
		} else if (hotField.equals("threeHitRate")) {
			value = vo.getThreeHitRate();
		} else if (hotField.equals("freeThrowHitRate")) {
			value = vo.getFreeThrowHitRate();
		} else if (hotField.equals("defenReboundNum")) {
			value = vo.getDefenReboundNum();
		} else if (hotField.equals("offendReboundNum")) {
			value = vo.getOffenReboundNum();
		}
		teamHotInfo.setValue(value);

		return teamHotInfo;
	}
}
