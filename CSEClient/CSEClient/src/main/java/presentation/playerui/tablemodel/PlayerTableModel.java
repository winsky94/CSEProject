package presentation.playerui.tablemodel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class PlayerTableModel extends AbstractTableModel{

	/**
	 * 表格球员信息列表模式model
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<ArrayList<String>> content=new ArrayList<ArrayList<String>>();
	static String[] head={"球员名称","所属球队","参赛场数","先发场数","篮板数","助攻数","在场时间",
			"投篮命中率","三分命中率","罚球命中率","进攻数","防守数","抢断数","盖帽数",
			"失误数","犯规数","得分","效率","GmSc效率值","真实命中率","投篮效率","篮板率",
			"进攻篮板率","防守篮板率","助攻率","抢断率","盖帽率","失误率","使用率"};
	
	
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
	
	//改变表头顺序  同时列内容也要变动
	public void setHead(int front){
		//front为被置前的搜索项  球队名称和所属球队始终置前
		int len=head.length;
		String[] newHead=new String[head.length];
		newHead[0]=head[0];newHead[1]=head[1];
	//	newHead[]
		
	}

}
