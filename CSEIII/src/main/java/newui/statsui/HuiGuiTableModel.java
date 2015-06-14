package newui.statsui;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.table.AbstractTableModel;

import data.PlayerStatistic;

public class HuiGuiTableModel extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String[][] content=new String[2][7];
	String[] title={"因素","效率值","GmSc效率值","得分-篮板-助攻","得分","篮板","助攻"};
	public HuiGuiTableModel() {
		PlayerStatistic ps=new PlayerStatistic();
		HashMap<String, ArrayList<String>> map=ps.start();
		ArrayList<String> efflist=map.get("efficiency");
		ArrayList<String> gmsclist=map.get("GmScEfficiencyValue");
		ArrayList<String> scorelist=map.get("score_rebound_assist");
		ArrayList<String> sclist=map.get("score");
		ArrayList<String> relist=map.get("rebound");
		ArrayList<String> aslist=map.get("assist");
		content[0][0]="F值";
		content[1][0]="拟合度";
		content[0][1]=efflist.get(0);
		content[1][1]=efflist.get(1);
		content[0][2]=gmsclist.get(0);
		content[1][2]=gmsclist.get(1);
		content[0][3]=scorelist.get(0);
		content[1][3]=scorelist.get(1);
		content[0][4]=sclist.get(0);
		content[1][4]=sclist.get(1);
		content[0][5]=relist.get(0);
		content[1][5]=relist.get(1);
		content[0][6]=aslist.get(0);
		content[1][6]=aslist.get(1);
	}
	@Override
	public int getRowCount() {
		return 2;
	}

	@Override
	public int getColumnCount() {
		return 7;
	}
	public String getColumnName(int c){
		return title[c];
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return content[rowIndex][columnIndex];
	}

}
