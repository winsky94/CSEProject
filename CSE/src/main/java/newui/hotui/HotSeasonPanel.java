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

import bl.player.Player;
import blservice.PlayerBLService;
import vo.PlayerVO;
import newui.Style;
import newui.tables.HotTableModel;

public class HotSeasonPanel extends HotFatherPanel implements MouseListener {
	private static final long serialVersionUID = 1L;
	// ---bestPnl------------
	JLabel bestHead, bestName, bestTeamIcon, positionAndTeamName, data;
	// --剩余四人的表格---------
	/**
	 * 需要新的表格及TableModel jsp已经在HotFatherPanel里建好了，这里只要table和tableModel
	 * 表头：排名（2，3，4，5），头像，球员名，所属球队，位置，比赛数据
	 */
	// ------bottomBar-----
	BottomButton scoreBtn, reboundBtn, assistBtn, blockBtn, stealBtn,
			threeRateBtn, shootRateBtn, freeRateBtn;
	String[] head={"排名","(头像)","球员名称","所属球队","位置","场均得分"};
	ArrayList<PlayerVO> vlist;
	PlayerBLService player;
	BottomButton currentBtn;
	JTable table;
	
	public HotSeasonPanel() {
		player=new Player();
		GridBagLayout bl = new GridBagLayout();
		GridBagConstraints bc = new GridBagConstraints();
		bc.fill = GridBagConstraints.BOTH;
		bestPnl.setLayout(bl);
		// -------bestPnl--------------
		bestHead = new JLabel(new ImageIcon(
				"image/player/portrait/Kobe Bryant.png"));
		// 有需要就加上bestHead.setPreferredSize(new Dimension(width, height));
		bc.gridx = 0;
		bc.gridy = 0;
		bc.gridwidth = 2;
		bc.gridheight = 5;
		bc.weightx = 2;
		bc.weighty = 5;
		bl.setConstraints(bestHead, bc);
		bestPnl.add(bestHead);
		// ------------
		JPanel midPnl = new JPanel();
		midPnl.setOpaque(false);
		bc.gridx = 2;
		bc.gridwidth = 5;
		bc.weightx = 5;
		bl.setConstraints(midPnl, bc);
		bestPnl.add(midPnl);
		midPnl.setLayout(new GridLayout(2, 1));
		bestName = new JLabel("Kobe 监听");
		bestName.setHorizontalAlignment(JLabel.CENTER);
		bestName.setFont(new Font("微软雅黑", Font.PLAIN, 28));
		midPnl.add(bestName);
		positionAndTeamName = new JLabel("C / 监听队");
		positionAndTeamName.setHorizontalAlignment(JLabel.CENTER);
		positionAndTeamName.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		midPnl.add(positionAndTeamName);
		// -------------
		data = new JLabel("40");
		data.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		data.setForeground(Style.BACK_GREY);
		bc.gridx = 7;
		bc.gridwidth = 1;
		bc.weightx = 8;
		bl.setConstraints(data, bc);
		bestPnl.add(data);
		// ------------
		bestTeamIcon = new JLabel(new ImageIcon(
				"image/teamIcon/teamsPng150/LAL.png"));
		bc.gridx = 8;
		bc.gridwidth = 2;
		bc.weightx = 2;
		bl.setConstraints(bestTeamIcon, bc);
		bestPnl.add(bestTeamIcon);
		//------------
		bottomBar.setLayout(new GridLayout(1, 8));
		//------------
		scoreBtn = new BottomButton("场均得分");
		scoreBtn.setBackground(Style.HOT_RED);
		scoreBtn.addMouseListener(this);
		bottomBar.add(scoreBtn);
		currentBtn=scoreBtn;
		//-----------
		reboundBtn = new BottomButton("场均篮板");
		reboundBtn.setBackground(Style.HOT_RED);
		reboundBtn.addMouseListener(this);
		bottomBar.add(reboundBtn);
		//-----------
		assistBtn = new BottomButton("场均助攻");
		assistBtn.setBackground(Style.HOT_RED);
		assistBtn.addMouseListener(this);
		bottomBar.add(assistBtn);
		//-----------
		blockBtn = new BottomButton("场均盖帽");
		blockBtn.setBackground(Style.HOT_RED);
		blockBtn.addMouseListener(this);
		bottomBar.add(blockBtn);
		//----------
		stealBtn = new BottomButton("场均抢断");
		stealBtn.setBackground(Style.HOT_RED);
		stealBtn.addMouseListener(this);
		bottomBar.add(stealBtn);
		//-----------
		threeRateBtn = new BottomButton("三分命中率");
		threeRateBtn.setBackground(Style.HOT_RED);
		threeRateBtn.addMouseListener(this);
		bottomBar.add(threeRateBtn);
		//-----------
		shootRateBtn = new BottomButton("投篮命中率");
		shootRateBtn.setBackground(Style.HOT_RED);
		shootRateBtn.addMouseListener(this);
		bottomBar.add(shootRateBtn);
		//-----------
		freeRateBtn = new BottomButton("罚球命中率");
		freeRateBtn.setBackground(Style.HOT_RED);
		freeRateBtn.addMouseListener(this);
		bottomBar.add(freeRateBtn);
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		currentBtn.setBackground(Style.HOT_RED);
		BottomButton m=(BottomButton)e.getSource();
		if(m==scoreBtn){
			
		}else if(m==scoreBtn){
			
		}else if(m==scoreBtn){
			
		}else if(m==scoreBtn){
			
		}else if(m==scoreBtn){
			
		}else if(m==scoreBtn){
			
		}else if(m==scoreBtn){
			
		}else{
			
		}
		currentBtn.setBackground(Style.HOT_REDFOCUS);
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
			btn.setBackground(Style.HOT_REDFOCUS);
		}

	}

	public void mouseExited(MouseEvent e) {
		if (e.getSource().getClass() == BottomButton.class) {
			BottomButton btn = (BottomButton) e.getSource();
			if(currentBtn!=btn)
				btn.setBackground(Style.HOT_RED);
		}

	}
	
	class HotSeasonModel extends HotTableModel{
		public HotSeasonModel(String[] head){
			super(head);
		}
		public void Refresh(ArrayList<PlayerVO> vlist){
			content.clear();
			for(int i=1;i<vlist.size();i++){
				PlayerVO v=vlist.get(i);
				
			}
			
		}
	}
}