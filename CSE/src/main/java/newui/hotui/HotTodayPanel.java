package newui.hotui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import newui.Style;

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
	//------bottomBar-----
	BottomButton scoreBtn,reboundBtn,assistBtn,blockBtn,stealBtn;
	//----------------------------
	public HotTodayPanel(){
		GridBagLayout bl=new GridBagLayout();
		GridBagConstraints bc=new GridBagConstraints();
		bc.fill=GridBagConstraints.BOTH;
		bestPnl.setLayout(bl);
		//-------bestPnl--------------
		bestHead=new JLabel(new ImageIcon("image/player/portrait/Kobe Bryant.png"));
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
		bestName=new JLabel("Kobe 监听");
		bestName.setHorizontalAlignment(JLabel.CENTER);
		bestName.setFont(new Font("微软雅黑",Font.PLAIN,28));
		midPnl.add(bestName);
		positionAndTeamName=new JLabel("C / 监听队");
		positionAndTeamName.setHorizontalAlignment(JLabel.CENTER);
		positionAndTeamName.setFont(new Font("微软雅黑",Font.PLAIN,20));
		midPnl.add(positionAndTeamName);
		//-------------
		data=new JLabel("40");
		data.setFont(new Font("微软雅黑",Font.PLAIN,30));
		data.setForeground(Style.BACK_GREY);
		bc.gridx=7;
		bc.gridwidth=1;
		bc.weightx=8;
		bl.setConstraints(data, bc);
		bestPnl.add(data);
		//------------
		bestTeamIcon=new JLabel(new ImageIcon("image/teamIcon/teamsPng150/LAL.png"));
		bc.gridx=8;
		bc.gridwidth=2;
		bc.weightx=2;
		bl.setConstraints(bestTeamIcon, bc);
		bestPnl.add(bestTeamIcon);
		//------------
		bottomBar.setLayout(new GridLayout(1,5));
		//------------
		scoreBtn=new BottomButton("得分");
		scoreBtn.setBackground(Style.HOT_YELLOW);
		scoreBtn.addMouseListener(this);
		bottomBar.add(scoreBtn);
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
		//-----------
		
	}
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
			btn.setBackground(Style.HOT_YELLOW);
		}
		
	}
}
