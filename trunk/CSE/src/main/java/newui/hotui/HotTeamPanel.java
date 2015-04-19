package newui.hotui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import bl.team.Team;
import blservice.TeamBLService;
import vo.PlayerVO;
import vo.TeamVO;
import newui.Style;
import newui.hotui.HotFatherPanel.BottomButton;
import newui.hotui.HotSeasonPanel.HotSeasonModel;
import newui.tables.HotTableModel;

public class HotTeamPanel extends HotFatherPanel implements MouseListener {
	private static final long serialVersionUID = 1L;
	JLabel teamIcon, unionLbl, teamNameLbl, data;
	// --剩余四人的表格---------
	/**
	 * 需要新的表格及TableModel jsp已经在HotFatherPanel里建好了，这里只要table和tableModel
	 * 表头：排名（2，3，4，5），teamIcon，球队全名，缩写，所属联盟，比赛数据
	 */
	// ------bottomBar-----
	BottomButton scoreBtn, reboundBtn, assistBtn, blockBtn, stealBtn,
			threeRateBtn, shootRateBtn, freeRateBtn,currentBtn;
	
	String[] head={"排名","(logo)","球队名称","球队缩写","所属联盟","场均得分"};
	JTable table;
	HotTeamModel model;
	TeamBLService team;
	ArrayList<TeamVO> vlist;
	public HotTeamPanel() {
		team=new Team();
		GridBagLayout bl = new GridBagLayout();
		GridBagConstraints bc = new GridBagConstraints();
		bc.fill = GridBagConstraints.BOTH;
		bestPnl.setLayout(bl);
		// -------bestPnl--------------
		teamIcon = new JLabel();
		// 有需要就加上teamIcon.setPreferredSize(new Dimension(width, height));
		bc.gridx = 0;
		bc.gridy = 0;
		bc.gridwidth = 2;
		bc.gridheight = 5;
		bc.weightx = 2;
		bc.weighty = 5;
		bl.setConstraints(teamIcon, bc);
		bestPnl.add(teamIcon);
		// ------------
		JPanel midPnl = new JPanel();
		midPnl.setOpaque(false);
		bc.gridx = 2;
		bc.gridwidth = 5;
		bc.weightx = 5;
		bl.setConstraints(midPnl, bc);
		bestPnl.add(midPnl);
		midPnl.setLayout(new GridLayout(2, 1));
		teamNameLbl = new JLabel();
		teamNameLbl.setHorizontalAlignment(JLabel.CENTER);
		teamNameLbl.setFont(new Font("微软雅黑", Font.PLAIN, 28));
		midPnl.add(teamNameLbl);
		unionLbl = new JLabel();
		unionLbl.setHorizontalAlignment(JLabel.CENTER);
		unionLbl.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		midPnl.add(unionLbl);
		// -------------
		data = new JLabel();
		data.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		data.setForeground(Style.BACK_GREY);
		bc.gridx = 7;
		bc.gridwidth = 1;
		bc.weightx = 8;
		bl.setConstraints(data, bc);
		bestPnl.add(data);
		// ----表格---------------

		// ------------
		bottomBar.setLayout(new GridLayout(1, 8));
		// ------------
		scoreBtn = new BottomButton("场均得分");
		scoreBtn.setBackground(Style.HOT_BLUEFOCUS);
		scoreBtn.addMouseListener(this);
		bottomBar.add(scoreBtn);
		currentBtn=scoreBtn;
		// -----------
		reboundBtn = new BottomButton("场均篮板");
		reboundBtn.setBackground(Style.HOT_BLUE);
		reboundBtn.addMouseListener(this);
		bottomBar.add(reboundBtn);
		// -----------
		assistBtn = new BottomButton("场均助攻");
		assistBtn.setBackground(Style.HOT_BLUE);
		assistBtn.addMouseListener(this);
		bottomBar.add(assistBtn);
		// -----------
		blockBtn = new BottomButton("场均盖帽");
		blockBtn.setBackground(Style.HOT_BLUE);
		blockBtn.addMouseListener(this);
		bottomBar.add(blockBtn);
		// -----------
		stealBtn = new BottomButton("场均抢断");
		stealBtn.setBackground(Style.HOT_BLUE);
		stealBtn.addMouseListener(this);
		bottomBar.add(stealBtn);
		// -----------
		threeRateBtn = new BottomButton("三分命中率");
		threeRateBtn.setBackground(Style.HOT_BLUE);
		threeRateBtn.addMouseListener(this);
		bottomBar.add(threeRateBtn);
		// -----------
		shootRateBtn = new BottomButton("投篮命中率");
		shootRateBtn.setBackground(Style.HOT_BLUE);
		shootRateBtn.addMouseListener(this);
		bottomBar.add(shootRateBtn);
		// -----------
		freeRateBtn = new BottomButton("罚球命中率");
		freeRateBtn.setBackground(Style.HOT_BLUE);
		freeRateBtn.addMouseListener(this);
		bottomBar.add(freeRateBtn);
		model=new HotTeamModel(head);
		table=new JTable(model);
		jsp.getViewport().add(table);
	}
	
	public void Refresh(String sort){
		//目前只有一个赛季
		vlist=team.getSeasonHotTeam("13-14", sort, 5);
		if(vlist!=null&&vlist.size()!=0){
			model.setHead(head);
		TeamVO topOne=vlist.get(0);
		teamIcon.setIcon(new ImageIcon("image/teamIcon/teamsPng150/"+topOne.getAbLocation()+".png"));
		teamNameLbl.setText(Team.changeTeamNameENToCH(topOne.getAbLocation()));
		data.setText(topOne.getScore()+"");
	//通过Team调用
		if(topOne.getConference().equals("E"))
			unionLbl.setText("东部联盟");
		else
			unionLbl.setText("西部联盟");
		
		model.Refresh(vlist);	
		table.revalidate();
		jsp.getViewport().remove(table);
		table=new JTable(model);
		jsp.getViewport().add(table);
		jsp.repaint();
		}
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		currentBtn.setBackground(Style.HOT_BLUE);
		BottomButton m=(BottomButton)e.getSource();
		if(m==scoreBtn){
			head[5]="场均得分";
			currentBtn=scoreBtn;
			Refresh("score");
			data.setText(vlist.get(0).getScore()+"");
		}else if(m==reboundBtn){
			head[5]="场均篮板";
			currentBtn=reboundBtn;
			Refresh("reboundNum");
			data.setText(vlist.get(0).getReboundNum()+"");
		}else if(m==assistBtn){
			head[5]="场均助攻";
			currentBtn=assistBtn;
			Refresh("assistNum");
			data.setText(vlist.get(0).getAssistNum()+"");
		}else if(m==blockBtn){
			head[5]="场均盖帽";
			currentBtn=blockBtn;
			Refresh("blockNum");
			data.setText(vlist.get(0).getBlockNum()+"");
		}else if(m==stealBtn){
			head[5]="场均抢断";
			currentBtn=stealBtn;
			Refresh("stealNum");
			data.setText(vlist.get(0).getStealNum()+"");
		}else if(m==threeRateBtn){
			head[5]="三分命中率";
			currentBtn=threeRateBtn;
			Refresh("threeHitRate");
			data.setText(vlist.get(0).getThreeHitRate()+"");
		}else if(m==shootRateBtn){
			head[5]="投篮命中率";
			currentBtn=shootRateBtn;
			Refresh("shootHitRate");
			data.setText(vlist.get(0).getShootHitRate()+"");
		}else{
			head[5]="罚球命中率";
			currentBtn=freeRateBtn;
			Refresh("freeThrowHitRate");
			data.setText(vlist.get(0).getFreeThrowHitRate()+"");
		}
		currentBtn.setBackground(Style.HOT_BLUEFOCUS);
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		if (e.getSource().getClass() == BottomButton.class) {
			BottomButton btn = (BottomButton) e.getSource();
			btn.setBackground(Style.HOT_BLUEFOCUS);
		}

	}

	public void mouseExited(MouseEvent e) {
		if (e.getSource().getClass() == BottomButton.class) {
			BottomButton btn = (BottomButton) e.getSource();
			if(currentBtn!=btn)
				btn.setBackground(Style.HOT_BLUE);
		}

	}
	
	class HotTeamModel extends HotTableModel{
		public HotTeamModel(String[] head){
			super(head);
		}
		public void Refresh(ArrayList<TeamVO> vlist){
			content.clear();
			num=2;
			for(int i=1;i<vlist.size();i++){
				TeamVO v=vlist.get(i);
				ArrayList<Object> line=new ArrayList<Object>();
				line.add(num);
				num++;
				ImageIcon icon=new ImageIcon("image/teamIcon/teamsPng35/"+v.getAbLocation()+".png");
				//设置宽高
				
				line.add(icon);
				line.add(v.getTeamName());
				line.add(v.getAbLocation());
				if(v.getConference().equals("E"))
					line.add("东部联盟");
				else
					line.add("西部联盟");
				if(currentBtn==scoreBtn){
					line.add(v.getScore());
				}else if(currentBtn==reboundBtn)
					line.add(v.getReboundNum());
				else if(currentBtn==assistBtn)
					line.add(v.getAssistNum());
				else if(currentBtn==blockBtn)
					line.add(v.getBlockNum());
				else if(currentBtn==stealBtn)
					line.add(v.getStealNum());
				else if(currentBtn==threeRateBtn)
					line.add(v.getThreeHitRate());
				else if(currentBtn==shootRateBtn)
					line.add(v.getShootHitRate());
				else
					line.add(v.getFreeThrowHitRate());
				content.add(line);
			}
			
		}
	}

}
