package newui.matchui;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;

import newui.tables.MyTableModel;
import bl.Player;
import bl.PlayerID;
import blService.PlayerIdBLService;
import vo.LiveMatchDetailVO;


public class MediaTableModel  extends MyTableModel {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Vector<String> columnNames=new Vector<String>();
	 ArrayList<ArrayList<Object>> rowData=new ArrayList<ArrayList<Object>>();
	 ArrayList<String> names=new ArrayList<String>();
	 PlayerIdBLService playerID=new PlayerID();
	
	public MediaTableModel(ArrayList<LiveMatchDetailVO> vos){

		columnNames.add("球队");
		columnNames.add("时间");
		columnNames.add("参与球员");
		columnNames.add("文字直播");
		
		String content;
		
		playerID.openSql();
		for(LiveMatchDetailVO vo:vos){
			content=vo.getContent();
			String[] buffer=content.split(",");
			ArrayList<Object> a=new ArrayList<Object>();
			
			if(buffer[0].equals("0"))
				a.add("");
			else 
				a.add(buffer[0]);
			
			a.add(buffer[1]);
			
			if(buffer[0].equals("0")&&buffer[2].equals("0")){
				a.add("");
			    names.add("");
			}
			else{
				int id=Integer.parseInt(buffer[2]);
				if(id==0){
					a.add("");
					names.add("");
				}
				else{
				    String name=playerID.getPlayerName(id);
				    names.add(name);
				    ImageIcon image=Player.getPlayerPortraitImage(name);
				    a.add(image);
				}
			}
			
			a.add(buffer[3]);
			rowData.add(a);
			
		}
        
		playerID.closeSql();
	}
	
	public Class getColumnClass(int c) {
		return rowData.get(0).get(c).getClass();
	}
	
//	得到共有多少列
	public int getColumnCount() {
		 int length=columnNames.size();
		return length;
	}
	

    public void setValueAt(Object value, int row, int column) {
		rowData.get(row).set(column, value);
	}
//得到共有多少行
	public int getRowCount() {
		//rowData=new Vector<String[]>();
		
		int length=rowData.size();
		return length;
	}
//得到某行某列的数据
	public Object getValueAt(int rowIndex, int columnIndex) {
		ArrayList<Object> buffer=rowData.get(rowIndex);
		Object it=buffer.get(columnIndex);
		return it;
	}
//得到某列的名字	
	public String getColumnName(int columnIndex){
		
		String columnName=columnNames.get(columnIndex);
		return columnName;
	}
	
	public void addRow(ArrayList<Object> hang){
		rowData.add(hang);
	}
	
	public ArrayList<String> getNameList(){
		return names;
	}

	public void clearAll(){
		rowData.clear();
	}
	
	public void refresh(ArrayList<LiveMatchDetailVO> vos){
		rowData.clear();
		names.clear();
		
        String content;
		playerID.openSql();
		for(LiveMatchDetailVO vo:vos){
			content=vo.getContent();
			String[] buffer=content.split(",");
			ArrayList<Object> a=new ArrayList<Object>();
			
			if(buffer[0].equals("0"))
				a.add("");
			else 
				a.add(buffer[0]);
			
			a.add(buffer[1]);
			
			if(buffer[0].equals("0")&&buffer[2].equals("0")){
				a.add("");
			    names.add("");
			}
			else{
				int id=Integer.parseInt(buffer[2]);
				if(id==0){
					a.add("");
					names.add("");
				}
				else{
				    String name=playerID.getPlayerName(id);
				    names.add(name);
				    ImageIcon image=Player.getPlayerPortraitImage(name);
				    a.add(image);
				}
			}
			
			a.add(buffer[3]);
			
			rowData.add(a);
			
		}
		playerID.closeSql();
	}
}
