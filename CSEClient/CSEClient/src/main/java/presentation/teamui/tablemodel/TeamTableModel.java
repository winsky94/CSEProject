package presentation.teamui.tablemodel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class TeamTableModel extends AbstractTableModel{
	ArrayList<ArrayList<String>> content=new ArrayList<ArrayList<String>>();
	static String[] head={"球队名称","比赛场数","投篮命中数","投篮出手次数","投篮命中率","三分命中数","三分出手数",
			"三分命中率","罚球命中数","罚球出手数","罚球命中率","进攻篮板数","防守篮板数","篮板数","篮板效率","进攻回合",
			"进攻效率","防守效率","助攻数","助攻率","抢断数","抢断效率","盖帽数","失误数","犯规数","比赛得分","胜率",
			};

	public int getRowCount() {
		// TODO Auto-generated method stub
		return content.size();
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return head.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return content.get(rowIndex).get(columnIndex);
	}
	
	public String getColumnName(int col) {
		return head[col];
	}
	
	public static String[] getHead(){
		return head;
	}

}
