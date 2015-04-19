package newui.hotui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import newui.FatherPanel;
import newui.Style;

public class HotIndexPanel extends FatherPanel implements MouseListener{
	/*
	 * 已完成，无需监听
	 */

	private static final long serialVersionUID = 1L;
	JPanel funcPnl,downPnl;
	TopButton todayBtn,seasonBtn,teamBtn,progressBtn;
	//
	Font font=new Font("微软雅黑",Font.PLAIN,15);
	public HotIndexPanel(){
		funcPnl=new JPanel();
		funcPnl.setLayout(new GridLayout(1,4));
		gbc.gridy = 1;
		gbc.gridheight = 1;
		gbc.weighty = 1;
		gbl.setConstraints(funcPnl, gbc);
		add(funcPnl);
		//------四个按钮------------
		todayBtn=new TopButton("今日热点球员");
		todayBtn.setBackground(Style.HOT_YELLOW);
		todayBtn.addMouseListener(this);
		funcPnl.add(todayBtn);
		//
		seasonBtn=new TopButton("赛季热点球员");
		seasonBtn.setBackground(Style.HOT_RED);
		seasonBtn.addMouseListener(this);
		funcPnl.add(seasonBtn);
		//
		teamBtn=new TopButton("赛季热点球队");
		teamBtn.setBackground(Style.HOT_BLUE);
		teamBtn.addMouseListener(this);
		funcPnl.add(teamBtn);
		//
		progressBtn=new TopButton("进步最快球员");
		progressBtn.setBackground(Style.HOT_PURPLE);
		progressBtn.addMouseListener(this);
		funcPnl.add(progressBtn);
		//-----------------------------------
		downPnl=new JPanel();
		gbc.gridy = 2;
		gbc.gridheight = 10;
		gbc.weighty = 15;
		gbl.setConstraints(downPnl, gbc);
		add(downPnl);
		downPnl.removeAll();
		downPnl.setLayout(new GridLayout(1,1));
		downPnl.add(new HotTodayPanel());
		downPnl.repaint();
		downPnl.revalidate();
	}
	class TopButton extends JButton{

		private static final long serialVersionUID = 1L;
		public TopButton(String txt){
			super(txt);
			setFont(font);
			setFocusPainted(false);
			setForeground(Color.white);
			setBorderPainted(false);
			setHorizontalAlignment(JButton.CENTER);
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
	}
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==todayBtn){
			downPnl.removeAll();
			downPnl.setLayout(new GridLayout(1,1));
			downPnl.add(new HotTodayPanel());
			downPnl.repaint();
			downPnl.revalidate();
		}
		if(e.getSource()==seasonBtn){
			downPnl.removeAll();
			downPnl.setLayout(new GridLayout(1,1));
			downPnl.add(new HotSeasonPanel());
			downPnl.repaint();
			downPnl.revalidate();
		}
		if(e.getSource()==teamBtn){
			downPnl.removeAll();
			downPnl.setLayout(new GridLayout(1,1));
			downPnl.add(new HotTeamPanel());
			downPnl.repaint();
			downPnl.revalidate();
		}
		if(e.getSource()==progressBtn){
			downPnl.removeAll();
			downPnl.setLayout(new GridLayout(1,1));
			downPnl.add(new ProgressPanel());
			downPnl.repaint();
			downPnl.revalidate();
		}
	}
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseEntered(MouseEvent e) {
		if(e.getSource()==todayBtn)
			todayBtn.setBackground(Style.HOT_YELLOWFOCUS);
		if(e.getSource()==seasonBtn)
			seasonBtn.setBackground(Style.HOT_REDFOCUS);
		if(e.getSource()==teamBtn)
			teamBtn.setBackground(Style.HOT_BLUEFOCUS);
		if(e.getSource()==progressBtn)
			progressBtn.setBackground(Style.HOT_PURPLEFOCUS);
		
	}
	public void mouseExited(MouseEvent e) {
		if(e.getSource()==todayBtn)
			todayBtn.setBackground(Style.HOT_YELLOW);
		if(e.getSource()==seasonBtn)
			seasonBtn.setBackground(Style.HOT_RED);
		if(e.getSource()==teamBtn)
			teamBtn.setBackground(Style.HOT_BLUE);
		if(e.getSource()==progressBtn)
			progressBtn.setBackground(Style.HOT_PURPLE);
	}
}