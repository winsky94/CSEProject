package newui.matchui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import newui.FatherPanel;
import newui.Service;
import newui.Style;
import vo.MatchVO;
import bl.Match;
import bl.Team;
import blService.MatchBLService;

import com.toedter.calendar.JDateChooser;

public class MatchIndexPanel extends FatherPanel {

	/**
	 * 刚进来该显示些什么呢
	 */
	private static final long serialVersionUID = 1L;
	JScrollPane jsp;
	JPanel funcPnl;
	MyComboBox seasonBox, seasonTypeBox, homeBox, visitingBox;
	//
	JPanel showPanel;
	JCheckBox fullSeasonBox;
	JDateChooser jdc;
	JLabel searchBtn;
	MatchBLService mservice;
	// 暂无赛季选择的bl层方法
	MatchBLService match=new Match();
	ArrayList<String> seasons=match.getAllSeasons();
	int size=seasons.size();
	String[] seasonText = (String[]) match.getAllSeasons().toArray(new String[size]);
	String[] teamArr = { "全部", "掘金", "森林狼", "雷霆", "开拓者", "爵士", "勇士", "快船",
			"湖人", "太阳", "国王", "小牛", "火箭", "灰熊", "鹈鹕", "马刺", "凯尔特人", "篮网",
			"尼克斯", "76人", "猛龙", "公牛", "骑士", "活塞", "步行者", "雄鹿", "老鹰", "黄蜂",
			"热火", "魔术", "奇才" };
	Font font = new Font("微软雅黑", Font.PLAIN, 13);

	public MatchIndexPanel() {
		// ------funcPnl--------
		funcPnl = new JPanel();
		funcPnl.setBackground(Style.BACK_GREY);
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.weighty = 0.01;
		gbl.setConstraints(funcPnl, gbc);
		add(funcPnl);
		// -----赛季选择--------
		MyLabel seasonLbl = new MyLabel("赛季：");
		funcPnl.add(seasonLbl);
		seasonBox = new MyComboBox(seasonText);
		funcPnl.add(seasonBox);
		String[] seasonTypeText = {  "季后赛 ","常规赛", "季前赛"  };
		seasonTypeBox = new MyComboBox(seasonTypeText);
		funcPnl.add(seasonTypeBox);
		funcPnl.add(new JLabel("       "));
		// ----是否全季-----------
		fullSeasonBox = new JCheckBox("全季");
		// bl方法暂无该参数传递 ，先选着吧
		fullSeasonBox.setSelected(true);
		fullSeasonBox.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (fullSeasonBox.isSelected()) {
					jdc.setEnabled(false);
				} else
					jdc.setEnabled(true);

			}
		});
		fullSeasonBox.setFont(font);
		fullSeasonBox.setForeground(Color.WHITE);
		fullSeasonBox.setBackground(Style.BACK_GREY);
		funcPnl.add(fullSeasonBox);
		// ---日期选择------------
		/*
		 * 若选择全季，那么忽略日期，若没有勾选全季，则查询指定日期的比赛
		 */
		jdc = new JDateChooser();
		funcPnl.add(jdc);
		funcPnl.add(new JLabel("       "));
		jdc.setEnabled(false);
		// ----主队客队---------
		MyLabel homeLbl = new MyLabel("主队：");
		funcPnl.add(homeLbl);
		homeBox = new MyComboBox(teamArr);
		funcPnl.add(homeBox);
		funcPnl.add(new JLabel("       "));
		MyLabel visitingLbl = new MyLabel("客队：");
		funcPnl.add(visitingLbl);
		visitingBox = new MyComboBox(teamArr);
		funcPnl.add(visitingBox);
		funcPnl.add(new JLabel("       "));
		mservice = Service.match;
		// ------搜索按钮---------
		searchBtn = new MyLabel("搜索");
		searchBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		searchBtn.addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseExited(MouseEvent e) {
				searchBtn.setForeground(Color.white);

			}

			public void mouseEntered(MouseEvent e) {
				searchBtn.setForeground(Style.FOCUS_BLUE);

			}

			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				// 按条件搜索比赛啦
				String date = "all";
				if (jdc.isEnabled()) {

					SimpleDateFormat fmt = new SimpleDateFormat("yyyyMM-dd");
					date = fmt.format(jdc.getDate());
					date = date.substring(4);
				}

				String hometeam = homeBox.getSelectedItem().toString();
				hometeam = Team.changeTeamNameCHToEN(hometeam);
				String visitingteam = visitingBox.getSelectedItem().toString();
				visitingteam = Team.changeTeamNameCHToEN(visitingteam);
				// 符合搜索条件的MatchVo
				searchRefresh(hometeam, visitingteam, date);

			}
		});
		funcPnl.add(searchBtn);
		//
		gbc.insets = new Insets(0, 2, 1, 2);
		jsp = new JScrollPane();
		// jsp.setLayout(new ScrollPaneLayout());
		gbc.gridy = 2;
		gbc.gridheight = 10;
		gbc.weighty = 10;
		gbl.setConstraints(jsp, gbc);
		add(jsp);
	//	searchRefresh("全部", "全部", "01-01");
		TodayMatchRefresh();
	}

	class MyComboBox extends JComboBox<String> {
		private static final long serialVersionUID = 1L;

		public MyComboBox(String[] arr) {
			super(arr);
			setFont(font);
			setForeground(Color.white);
			setBackground(Style.BACK_GREY);
		}
	}

	class MyLabel extends JLabel {
		private static final long serialVersionUID = 1L;

		public MyLabel(String text) {
			super(text);
			setFont(font);
			setForeground(Color.white);
		}
	}
	public void TodayMatchRefresh(){
		boolean isTodayLive=true;
		LiveWebInc c=new LiveWebInc();
		ArrayList<ArrayList<String>> result=c.getGameStatus(c.getTodayEDate());
		if(result.size()==0){
			//today no game
			// show no game or recent game tips
		}else{
			Calendar time=Calendar.getInstance();
			String month=(time.get(Calendar.MONTH)+1)+"";
			String day=(time.get(Calendar.DATE))+"";
			if(month.length()==1)
				month="0"+month;
			if(day.length()==1)
				day="0"+day;
			String date=month+"-"+day;
			//only process ing game now
			// other need to translate to matchvo if over
			ArrayList<MatchVO> list=new ArrayList<MatchVO>();
			//ArrayList<String> gameid=new ArrayList<String>();
			int n=result.size();
			for(ArrayList<String> line:result){
			//	gameid.add(line.get(0));
				String[] s=line.get(2).split("/");
				if(!line.get(1).equals("3")){
//			
					ArrayList<String> detail=getDetailScore(line.get(0));
					String[] ss=detail.get(0).split("-");
					detail.remove(0);
					MatchVO v=new MatchVO("14-15",date,"Playoff",s[1].substring(0, 3),
							s[1].substring(3, 6),Integer.parseInt(ss[0]),
							Integer.parseInt(ss[1]),detail);
					list.add(v);
					
				}else{
					isTodayLive=false;
					searchRefresh("all","all",date);
				}
			}
			if(isTodayLive){
			JPanel BIGPNL = new JPanel();
			BIGPNL.setBackground(Color.white);
			jsp.getViewport().add(BIGPNL);
			MatchCardThread th = new MatchCardThread(list, BIGPNL, jsp, result);
			th.start();
			}
		}
	}

	public void searchRefresh(String h, String v, String date) {
		String season = seasonBox.getSelectedItem().toString();
		String seasonType=(String)seasonTypeBox.getSelectedItem();
		if(seasonType.equals("全部"))
			seasonType="all";
		ArrayList<MatchVO> mlist = mservice.getMatchData(season,seasonType, date, Team.changeTeamNameCHToEN(h), Team.changeTeamNameCHToEN(v));
		// ArrayList<MatchCard> matchCardList = new ArrayList<MatchCard>();

		JPanel BIGPNL = new JPanel();
		BIGPNL.setBackground(Color.WHITE);
		// BIGPNL.setLayout();
		/*
		 * int row = mlist.size() / 2; if (mlist.size() % 2 != 0) row += 1;
		 * 
		 * BIGPNL.setLayout(new GridLayout(row, 2));
		 * 
		 * int heightOfBIGPNL = 170 * row;//
		 * 这里一定要设置为180-200之间的值，代表每个卡片的高度，乘以卡片数量之后是整个BIGPNL的高度 int screenWidth =
		 * UIhelper.getScreenWidth();
		 * 
		 * int width = screenWidth * 85/ 100;
		 * 
		 * 
		 * BIGPNL.setPreferredSize(new Dimension(width, heightOfBIGPNL));
		 */
		jsp.getViewport().add(BIGPNL);
		MatchCardThread th = new MatchCardThread(mlist, BIGPNL, jsp);
		th.start();
	}
	
	public ArrayList<String> getDetailScore(String gameid){
	
		ScoresWebInc inc=new ScoresWebInc();
		ArrayList<String> sumList=inc.getScoreSummary(gameid);
		
		ArrayList<String> descore=new ArrayList<String>();
		if(sumList.size()!=0){
		String[] vist=sumList.get(0).replace("\"", "").split(",");
		String[] homt=sumList.get(1).replace("\"", "").split(",");
		String vsteam=vist[0]+"-"+homt[0];
		String vsscore=vist[18]+"-"+homt[18];
		//���ж�
		//String type="";
		descore.add(vsscore);
		for(int i=4;i<18;i++){
			if(!(vist[i].equals("0")||vist[i].equals("null"))){
				String scor=vist[i]+"-"+homt[i];
				descore.add(scor);
			}
		}
		}
		if(descore.size()<5){
		for(int i=descore.size();i<5;i++)
			descore.add("0-0");
		}
		return descore;
	}
}
