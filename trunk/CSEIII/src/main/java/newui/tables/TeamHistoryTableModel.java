package newui.tables;

import java.util.ArrayList;

import newui.MyUIDataFormater;
import newui.Service;
import vo.PlayerVO;
import bl.Player;
import blService.PlayerBLService;

public class TeamHistoryTableModel extends MyTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static String[] head = { "球员", "出场", "首发", "时间", "投篮", "三分", "罚球", "前篮板",
			"后篮板", "总篮板", "助攻", "抢断", "盖帽", "失误", "犯规", "得分" };
	PlayerBLService player;
	public ArrayList<PlayerVO> teamMember;
	String tname;
	// 最后一行有统计
	ArrayList<ArrayList<Object>> content = new ArrayList<ArrayList<Object>>();

	public TeamHistoryTableModel() {
		player = Service.player;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return content.size();
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return head.length;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int column) {
		return content.get(0).get(column).getClass();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return content.get(rowIndex).get(columnIndex);
	}

	public String getColumnName(int col) {
		return head[col];
	}

	public void Refresh(String teamName) {

		teamMember = player.getPlayersByTeam(teamName);
		if (teamMember != null && teamMember.size() != 0)
			Refresh(teamMember, teamName);
		this.tname = teamName;
	}

	// 当这个球员这个赛季不在该球队效力时，直接将其从teamMember中除去
	// teamMember得到的是所有曾在该球队打过比赛的球员姓名
	public void RefreshSeason(String season, String seasonType) {
		ArrayList<PlayerVO> vo = new ArrayList<PlayerVO>();
		for (int i = 0; i < teamMember.size(); i++) {
			PlayerVO v = teamMember.get(i);
			ArrayList<PlayerVO> playerVO = player.getPlayerSeasonInfo(season,
					seasonType, v.getName());
			if (playerVO == null || playerVO.size() == 0) {
				teamMember.remove(i);
				i--;
			} else {
				vo.add(playerVO.get(0));
			}
		}
		Refresh(vo, tname);
	}

	public void RefreshAverage(String seasonType) {
		ArrayList<PlayerVO> vo = new ArrayList<PlayerVO>();
		for (int i = 0; i < teamMember.size(); i++) {
			PlayerVO v = teamMember.get(i);
			ArrayList<PlayerVO> playerVO = player.getPlayerAverageInfo(
					seasonType, v.getName());
			if (playerVO == null || playerVO.size() == 0) {
				teamMember.remove(i);
				i--;
			} else {
				vo.add(playerVO.get(0));
			}
		}
		Refresh(vo, tname);
	}

	public void Refresh(ArrayList<PlayerVO> pp, String tname) {
		content.clear();
		// 据图 率以后的都是累加 之前的都是平均 首发为"-" 时间为"0" 不理解 为啥不平均一下呢
		ArrayList<Object> last = new ArrayList<Object>();
		int present = 0;// 出场次数
		double shootHitRate = 0;//
		double threeHitRate = 0;
		double freeHitRate = 0;// 罚球
		double offendNum = 0;// 进攻数=进攻篮板数 感觉好像是的呢
		double defendNum = 0;
		double reboundNum = 0;// 总篮板数
		double assistNum = 0;
		double stealNum = 0;
		double blockNum = 0;
		double turnOverNum = 0;
		double foulNum = 0;// 犯规数
		double score = 0;
		int n = pp.size();
		for (PlayerVO p : pp) {
			ArrayList<Object> line = new ArrayList<Object>();
			line.add(p.getName());
			line.add(p.getPlayedGames());
			present += p.getPlayedGames();
			line.add(p.getGameStartingNum());
			line.add(Player.changeSecondToTime(p.getPresentTime()));
			line.add(MyUIDataFormater.formatTo3(p.getShootHitRate()));
			shootHitRate += p.getShootHitRate();
			line.add(MyUIDataFormater.formatTo3(p.getThreeHitRate()));
			threeHitRate += p.getThreeHitRate();
			line.add(MyUIDataFormater.formatTo3(p.getFreeThrowHitRate()));
			freeHitRate += p.getFreeThrowHitRate();
			line.add(MyUIDataFormater.formatTo3(p.getOffenReboundNum()));
			offendNum += p.getOffenReboundNum();
			line.add(MyUIDataFormater.formatTo3(p.getDefenReboundNum()));
			defendNum += p.getDefenReboundNum();
			line.add(MyUIDataFormater.formatTo3(p.getReboundNum()));
			reboundNum += p.getReboundNum();
			line.add(MyUIDataFormater.formatTo3(p.getAssistNum()));
			assistNum += p.getAssistNum();
			line.add(MyUIDataFormater.formatTo3(p.getStealNum()));
			stealNum += p.getStealNum();
			line.add(MyUIDataFormater.formatTo3(p.getBlockNum()));
			blockNum += p.getBlockNum();
			line.add(MyUIDataFormater.formatTo3(p.getTurnOverNum()));
			turnOverNum += p.getTurnOverNum();
			line.add(MyUIDataFormater.formatTo3(p.getFoulNum()));
			foulNum += p.getFoulNum();
			line.add(p.getScore());
			score += p.getScore();
			content.add(line);
		}

		last.add(tname + "全队");
		last.add(present / n);
		last.add("-");
		last.add("-");
		last.add(MyUIDataFormater.formatTo3(shootHitRate / n));
		last.add(MyUIDataFormater.formatTo3(threeHitRate / n));
		last.add(MyUIDataFormater.formatTo3(freeHitRate / n));
		last.add(offendNum);
		last.add(defendNum);
		last.add(reboundNum);
		last.add(assistNum);
		last.add(stealNum);
		last.add(blockNum);
		last.add(turnOverNum);
		last.add(foulNum);
		last.add(score);
		content.add(last);

	}

}
