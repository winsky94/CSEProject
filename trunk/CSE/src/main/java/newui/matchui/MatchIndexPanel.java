package newui.matchui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import newui.FatherPanel;
import newui.Style;
import newui.UIhelper;
import vo.MatchVO;
import bl.match.Match;
import blservice.MatchBLService;

import com.toedter.calendar.JDateChooser;

public class MatchIndexPanel extends FatherPanel {

	/**
	 * 刚进来该显示些什么呢
	 */
	private static final long serialVersionUID = 1L;
	JScrollPane jsp;
	JPanel funcPnl;
	MyComboBox seasonBox, homeBox, visitingBox;
	//
	JPanel showPanel;
	JCheckBox fullSeasonBox;
	JDateChooser jdc;
	JLabel searchBtn;
	MatchBLService mservice;
	// 暂无赛季选择的bl层方法
	String[] seasonText = { "13-14" };
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
		gbc.weighty = 0.1;
		gbl.setConstraints(funcPnl, gbc);
		add(funcPnl);
		// -----赛季选择--------
		MyLabel seasonLbl = new MyLabel("赛季：");
		funcPnl.add(seasonLbl);
		seasonBox = new MyComboBox(seasonText);
		funcPnl.add(seasonBox);
		// ----是否全季-----------
		fullSeasonBox = new JCheckBox("全季");
		// bl方法暂无该参数传递 ，先选着吧
		fullSeasonBox.setSelected(true);
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
		// ----主队客队---------
		MyLabel homeLbl = new MyLabel("主队：");
		funcPnl.add(homeLbl);
		homeBox = new MyComboBox(teamArr);
		funcPnl.add(homeBox);
		MyLabel visitingLbl = new MyLabel("客队：");
		funcPnl.add(visitingLbl);
		visitingBox = new MyComboBox(teamArr);
		funcPnl.add(visitingBox);
		mservice = new Match();
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
				String season = seasonBox.getSelectedItem().toString();

				SimpleDateFormat fmt = new SimpleDateFormat("yyyyMM-dd");
				String date = fmt.format(jdc.getDate());
				date = date.substring(4);
				String hometeam = homeBox.getSelectedItem().toString();
				String visitingteam = visitingBox.getSelectedItem().toString();
				// 符合搜索条件的MatchVo
				ArrayList<MatchVO> mlist = mservice.getMatchData(season, date,
						hometeam, visitingteam);
				ArrayList<MatchCard> matchCardList = new ArrayList<MatchCard>();
				for (MatchVO m : mlist) {
					MatchCard card = new MatchCard(m);
					// System.out.println("我有啦");
					matchCardList.add(card);
				}

				JPanel BIGPNL = new JPanel();
				int row = matchCardList.size() / 2;
				if (matchCardList.size() % 2 != 0)
					row += 1;
				BIGPNL.setLayout(new GridLayout(row, 2));
				for (int i = 0; i < matchCardList.size(); i++) {
					BIGPNL.add(matchCardList.get(i));
				}
				int heightOfBIGPNL = 100 * matchCardList.size();// 这里一定要设置为180-200之间的值，代表每个卡片的高度，乘以卡片数量之后是整个BIGPNL的高度
				int screenWidth = UIhelper.getScreenWidth();

				int width = screenWidth * 90 / 100;
				BIGPNL.setPreferredSize(new Dimension(width, heightOfBIGPNL));
				jsp.getViewport().add(BIGPNL);
			}
		});
		funcPnl.add(searchBtn);
		//

		jsp = new JScrollPane();
		// jsp.setLayout(new ScrollPaneLayout());
		gbc.gridy = 2;
		gbc.gridheight = 10;
		gbc.weighty = 10;
		gbl.setConstraints(jsp, gbc);
		add(jsp);
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
}
