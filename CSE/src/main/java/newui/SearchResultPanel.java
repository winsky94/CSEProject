package newui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bl.player.Player;
import bl.team.Team;
import blservice.PlayerBLService;
import blservice.TeamBLService;
import vo.PlayerVO;
import vo.TeamVO;
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
	private PlayerBLService pservice;
	private TeamBLService tservice;
	public String content;
	Font font = new Font("微软雅黑", Font.PLAIN, 15);
	public SearchResultPanel(String scontent) {
		// ------funcPnl--------
		funcPnl = new JPanel();
		content=scontent;
		funcPnl.setBackground(Style.BACK_GREY);
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.weighty = 0.1;
		gbl.setConstraints(funcPnl, gbc);
		add(funcPnl);
		//----resultLbl---------
		resultLbl=new MyLabel("在球队中检索到88条符合关键字"+scontent+"的结果...");
		funcPnl.add(resultLbl);
		funcPnl.add(new JLabel("                             "));
		//----changeLbl---------
		changeLbl=new MyLabel("切换搜索域");
		changeLbl.addMouseListener(this);
		changeLbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		funcPnl.add(changeLbl);
		//------jsp--------------------
		ttm=new TeamBaseInfoTableModel();
		ptm=new PlayerBaseInfoTableModel();
		table=new MyBaseTable(ttm);
		ptm.setCurrentTable(table);
		ttm.setCurrentTable(table);
		
		titleBar.searchBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				table=new MyBaseTable(ttm);
				titleBar.setCurrentTableModel(ttm);
				titleBar.setModelEnum(TableModel.TEAMBASEINFO);
				titleBar.setTable(table);
				content=titleBar.searchFld.getText();
			}
			
		});
		jsp=new JScrollPane(table);
		isTeamTable=true;
		gbc.gridy =2;
		gbc.gridheight = 10;
		gbc.weighty = 10;
		gbl.setConstraints(jsp, gbc);
		add(jsp);
		pservice=new Player();//这里有问题
		tservice=new Team();
		ArrayList<TeamVO> v=tservice.getTeamBaseInfo(scontent);
		resultLbl.setText("在球队中检索到"+v.size()+"条符合关键字"+scontent+"的结果...");
		ttm.SearchRefresh(v);
		table.revalidate();
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
				
				ArrayList<PlayerVO> re=pservice.getPlayerBaseInfo(content);
				table.setModel(ptm);
				
				resultLbl.setText("在球员中检索到"+re.size()+"条符合关键字"+content+"的结果...");
				ptm.SearchRefresh(re);
			
			}else{
				//换为ttm
				isTeamTable=true;
				
				ArrayList<TeamVO> re=tservice.getTeamBaseInfo(content);
				resultLbl.setText("在球员中检索到"+re.size()+"条符合关键字"+content+"的结果...");
				table.setModel(ttm);;
				ttm.SearchRefresh(re);
				
				
			}
			table.revalidate();
			jsp.revalidate();
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