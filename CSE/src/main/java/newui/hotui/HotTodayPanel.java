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
import javax.swing.table.DefaultTableModel;

import bl.player.Player;
import blservice.PlayerBLService;
import vo.PlayerVO;
import newui.Style;
import newui.tables.HotTableModel;

public class HotTodayPanel extends HotFatherPanel implements MouseListener{
	private static final long serialVersionUID = 1L;
	//---bestPnl------------
	JLabel bestHead,bestName,bestTeamIcon,positionAndTeamName,data;
	//--剩余四人的表格---------
	/**
	 * 需要新的表格及TableModel
	 * jsp已经在HotFatherPanel里建好了，这里只要table和tableModel
	 * 表头：排名（2，3，4，5），头像，球员名，所属球队，位置，比赛数据
	 */
	String[] head={"排名","(头像)","球员名称","所属球队","位置","得分"};
	JTable table;
	PlayerBLService player;	
	//------bottomBar-----
	BottomButton scoreBtn,reboundBtn,assistBtn,blockBtn,stealBtn,currentBtn;
	HotTodayModel model;
	ArrayList<PlayerVO> vlist;
	//----------------------------
	public HotTodayPanel(){
		GridBagLayout bl=new GridBagLayout();
		GridBagConstraints bc=new GridBagConstraints();
		bc.fill=GridBagConstraints.BOTH;
		bestPnl.setLayout(bl);
		player=new Player();
		//========initial======
	
		
		
		//-------bestPnl--------------
		bestHead=new JLabel();
		//有需要就加上bestHead.setPreferredSize(new Dimension(width, height));
		bc.gridx=0;
		bc.gridy=0;
		bc.gridwidth=2;
		bc.gridheight=5;
		bc.weightx=2;
		bc.weighty=5;
		bl.setConstraints(bestHead, bc);
		bestPnl.add(bestHead);
		//------------
		JPanel midPnl=new JPanel();
		midPnl.setOpaque(false);
		bc.gridx=2;
		bc.gridwidth=5;
		bc.weightx=5;
		bl.setConstraints(midPnl, bc);
		bestPnl.add(midPnl);
		midPnl.setLayout(new GridLayout(2,1));
		bestName=new JLabel();
		bestName.setHorizontalAlignment(JLabel.CENTER);
		bestName.setFont(new Font("微软雅黑",Font.PLAIN,28));
		midPnl.add(bestName);
		positionAndTeamName=new JLabel();
		positionAndTeamName.setHorizontalAlignment(JLabel.CENTER);
		positionAndTeamName.setFont(new Font("微软雅黑",Font.PLAIN,20));
		midPnl.add(positionAndTeamName);
		//-------------
		data=new JLabel();
		data.setFont(new Font("微软雅黑",Font.PLAIN,30));
		data.setForeground(Style.BACK_GREY);
		bc.gridx=7;
		bc.gridwidth=1;
		bc.weightx=8;
		bl.setConstraints(data, bc);
		bestPnl.add(data);
		//------------
		bestTeamIcon=new JLabel();
		bc.gridx=8;
		bc.gridwidth=2;
		bc.weightx=2;
		bl.setConstraints(bestTeamIcon, bc);
		bestPnl.add(bestTeamIcon);
		//------------
		bottomBar.setLayout(new GridLayout(1,5));
		//------------
		scoreBtn=new BottomButton("得分");
		scoreBtn.setBackground(Style.HOT_YELLOWFOCUS);
		scoreBtn.addMouseListener(this);
		bottomBar.add(scoreBtn);
		currentBtn=scoreBtn;
		//-----------
		reboundBtn=new BottomButton("篮板");
		reboundBtn.setBackground(Style.HOT_YELLOW);
		reboundBtn.addMouseListener(this);
		bottomBar.add(reboundBtn);
		//-----------
		assistBtn=new BottomButton("助攻");
		assistBtn.setBackground(Style.HOT_YELLOW);
		assistBtn.addMouseListener(this);
		bottomBar.add(assistBtn);
		//-----------
		blockBtn=new BottomButton("盖帽");
		blockBtn.setBackground(Style.HOT_YELLOW);
		blockBtn.addMouseListener(this);
		bottomBar.add(blockBtn);
		//-----------
		stealBtn=new BottomButton("抢断");
		stealBtn.setBackground(Style.HOT_YELLOW);
		stealBtn.addMouseListener(this);
		bottomBar.add(stealBtn);
		//-----------表格===
		model=new HotTodayModel(head);
		table=new JTable(model);
		jsp.add(table);
		
		
	}
	
	public void Refresh(String sort){
		//默认筛选  按得分
		vlist=player.getDayHotPlayer(sort, 5);
		if(vlist!=null&&vlist.size()!=0){
		PlayerVO topOne=vlist.get(0);
		bestHead.setIcon(new ImageIcon("image/player/portrait/"+topOne.getName()+".png"));
		bestName.setText(topOne.getName());
		positionAndTeamName.setText(topOne.getPosition()+"/" +topOne.getOwingTeam());
		//data.setText(topOne.getScore()+"");
		bestTeamIcon.setIcon(new ImageIcon("image/teamIcon/teamsPng150/"+topOne.getOwingTeam()+".png"));
		model.setHead(head);
		model.Refresh(vlist);	
		table.revalidate();
		}
			
	}
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		BottomButton m=(BottomButton)e.getSource();
		currentBtn.setBackground(Style.HOT_YELLOW);
		if(m==scoreBtn){
			head[5]="得分";
			currentBtn=scoreBtn;	
			Refresh("score");
			data.setText(vlist.get(0).getScore()+"");
								
		}else if(m==reboundBtn){
			head[5]="篮板";
			currentBtn=reboundBtn;
			Refresh("reboundNum");
			data.setText(vlist.get(0).getReboundNum()+"");
			
		}else if(m==assistBtn){
			head[5]="助攻";
			currentBtn=assistBtn;
			Refresh("assistNum");
			data.setText(vlist.get(0).getAssistNum()+"");
			
		}else if(m==blockBtn){
			head[5]="盖帽";
			currentBtn=blockBtn;
			Refresh("blockNum");
			data.setText(vlist.get(0).getBlockNum()+"");
			
			
		}else{
			head[5]="抢断";
			currentBtn=stealBtn;
			Refresh("stealNum");
			data.setText(vlist.get(0).getStealNum()+"");
		
			
		}	
		currentBtn.setBackground(Style.HOT_YELLOWFOCUS);
		
	}
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseEntered(MouseEvent e) {
		if(e.getSource().getClass()==BottomButton.class){
			BottomButton btn=(BottomButton) e.getSource();
			btn.setBackground(Style.HOT_YELLOWFOCUS);
		}
		
	}
	public void mouseExited(MouseEvent e) {
		if(e.getSource().getClass()==BottomButton.class){
			BottomButton btn=(BottomButton) e.getSource();
			if(currentBtn!=btn)
				btn.setBackground(Style.HOT_YELLOW);
		}
		
	}
	//"排名","(头像)","球员名称","所属球队","位置","得分"
	class HotTodayModel extends HotTableModel{
		
		public HotTodayModel(String[] head) {
			super(head);
			
			
			// TODO Auto-generated constructor stub
		}
		public void Refresh(ArrayList<PlayerVO> vlist){
			content.clear();
			num=2;
			for(int i=1;i<vlist.size();i++){
				PlayerVO v=vlist.get(i);
				ArrayList<Object> line=new ArrayList<Object>();
				line.add(num);
				num++;
				ImageIcon icon=new ImageIcon("image/player/portrait/"+v.getName()+".png");
				//设置宽高
				
				line.add(icon);
				line.add(v.getName());
				line.add(v.getOwingTeam());
				line.add(v.getPosition());
				if(currentBtn==scoreBtn){
					line.add(v.getScore());
				}else if(currentBtn==reboundBtn)
					line.add(v.getReboundNum());
				else if(currentBtn==assistBtn)
					line.add(v.getAssistNum());
				else if(currentBtn==blockBtn)
					line.add(v.getBlockNum());
				else
					line.add(v.getStealNum());
				content.add(line);
				
			}
			
		}
	}
	
	
	
}
