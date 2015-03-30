package newui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import newui.tables.MyBaseTable;
import newui.tables.PlayerBaseInfoTableModel;
import newui.tables.TeamBaseInfoTableModel;

public class SearchResultPanel extends FatherPanel implements MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//-----------------
	boolean isTeamTable;
	//-----------------
	PlayerBaseInfoTableModel ptm;
	TeamBaseInfoTableModel ttm;
	JTable table;
	JScrollPane jsp;
	JLabel resultLbl,changeLbl;
	JPanel funcPnl;
	Font font = new Font("微软雅黑", Font.PLAIN, 15);
	public SearchResultPanel() {
		// ------funcPnl--------
		funcPnl = new JPanel();
		funcPnl.setBackground(Style.BACK_GREY);
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.weighty = 0.1;
		gbl.setConstraints(funcPnl, gbc);
		add(funcPnl);
		//----resultLbl---------
		resultLbl=new MyLabel("在球队中检索到88条符合关键字“I need listener”的结果。");
		funcPnl.add(resultLbl);
		funcPnl.add(new JLabel("                             "));
		//----changeLbl---------
		changeLbl=new MyLabel("切换搜索域");
		changeLbl.addMouseListener(this);
		changeLbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		funcPnl.add(changeLbl);
		//------jsp--------------------
		ttm=new TeamBaseInfoTableModel();
		table=new MyBaseTable(ttm);
		jsp=new JScrollPane(table);
		isTeamTable=true;
		gbc.gridy =2;
		gbc.gridheight = 10;
		gbc.weighty = 10;
		gbl.setConstraints(jsp, gbc);
		add(jsp);
	}
	class MyLabel extends JLabel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public MyLabel(String text){
			super(text);
			setFont(font);
			setForeground(Color.white);
		}
		
	}
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==changeLbl){
			//监听，换掉table里的tableModel
			if(isTeamTable){
				//换为ptm
				isTeamTable=false;
				resultLbl.setText("在球员中检索到……");
			}else{
				//换为ttm
				isTeamTable=true;
				resultLbl.setText("在球队中检索到……");
			}
		}
		
	}
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseEntered(MouseEvent e) {
		if(e.getSource()==changeLbl)
			changeLbl.setForeground(Style.FOCUS_BLUE);
		
	}
	public void mouseExited(MouseEvent e) {
		if(e.getSource()==changeLbl)
			changeLbl.setForeground(Color.white);
	}
}
