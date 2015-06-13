package newui.tables;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import newui.Service;
import vo.MatchVO;
import vo.RecordVO;
import blService.PlayerBLService;

public class PlayerHistoryTableModel extends AbstractTableModel {

	static String[] head = { "比赛",/* "类型", */"日期", "首发", "时间", "投篮", "三分", "罚球",
			"前篮板", "后篮板", "总篮板", "助攻", "抢断", "盖帽", "失误", "犯规", "得分" };

	ArrayList<ArrayList<Object>> content = new ArrayList<ArrayList<Object>>();
	PlayerBLService player;// 接口是否应该细分
	public ArrayList<MatchVO> match;

	public int getRowCount() {
		// TODO Auto-generated method stub
		return content.size();
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return head.length;
	}

	public String getColumnName(int col) {
		return head[col];
	}

	public Class getColumnClass(int column) {
		return content.get(0).get(column).getClass();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return content.get(rowIndex).get(columnIndex);
	}

	public void Refresh(String pname) {
		player = Service.player;
		match = player.getMatches("14-15","Team",pname,30);
		if (match != null && match.size() != 0)
			Refresh(match, pname);

	}

	// "比赛","类型", "日期","首发", "时间", "投篮", "三分", "罚球","前篮板",
	// "后篮板", "总篮板","助攻","抢断","盖帽","失误","犯规","得分"
	// 不确定哪些是球员的数据 哪些是主客队数据 从前篮板开始为球员的该厂数据
	public void Refresh(ArrayList<MatchVO> match, String name) {
		content.clear();
		for (MatchVO m : match) {
			// 不懂 主客队比分 及对阵队伍
			ArrayList<Object> line = new ArrayList<Object>();
			String tname = "";
			boolean isHome = false;
			RecordVO v = null;
			ArrayList<RecordVO> r = m.getRecords();
			for (RecordVO rr : r) {
				if (rr.getPlayerName().equals(name)) {
					v = rr;
					tname = rr.getTeam();
				}
			}
			if (m.getHomeTeam().equals(tname)) {
				isHome = true;
				tname = m.getVisitingTeam();
			} else {
				tname = m.getHomeTeam();
			}

			line.add(m.getHomeScore() + "-" + m.getVisitingScore() + " "
					+ tname);
			// 项目里么有区分常规赛和季后赛肿么破
			// line.add("常规赛");
			line.add(m.getDate());
			if (isHome)
				line.add("是");
			else
				line.add("否");
			// 时间为球员的在场时间啦
			line.add(v.getPresentTime());
			// 不确定是不是队伍数据
			line.add(v.getShootHitNum() + "-" + v.getShootAttemptNum());
			line.add(v.getThreeHitNum() + "-" + v.getThreeAttemptNum());
			line.add(v.getFreeThrowHitNum() + "-" + v.getFreeThrowAttemptNum());
			// 开始为个人数据
			line.add(v.getOffenReboundNum());
			line.add(v.getDefenReboundNum());
			line.add(v.getReboundNum());
			line.add(v.getAssistNum());
			line.add(v.getStealNum());
			line.add(v.getBlockNum());
			line.add(v.getTurnOverNum());
			line.add(v.getFoulNum());
			line.add(v.getScore());
			content.add(line);
		}

	}

}
