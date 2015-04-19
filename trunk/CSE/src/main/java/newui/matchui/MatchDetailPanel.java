package newui.matchui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import vo.MatchVO;
import newui.FatherPanel;
import newui.Style;

public class MatchDetailPanel extends FatherPanel {

	private static final long serialVersionUID = 1L;
	DetailCard card;
	//------------------
	JPanel titlePnl;
	JLabel switchLbl;
	//------------------
	JScrollPane jsp;

	public MatchDetailPanel(MatchVO v) {
		// ----card------------
		card = new DetailCard(v);
		gbc.gridy = 1;
		gbc.gridheight = 4;
		gbc.weighty =4;
		gbl.setConstraints(card, gbc);
		add(card);
		// ----titlePnl--------
		FlowLayout fl=new FlowLayout();
		fl.setAlignment(FlowLayout.LEFT);
		fl.setHgap(20);
		titlePnl = new JPanel();
		titlePnl.setBackground(Style.DEEP_BLUE);
		titlePnl.setLayout(fl);
		gbc.gridy = 5;
		gbc.gridheight = 1;
		gbc.weighty = 0.5;
		gbl.setConstraints(titlePnl, gbc);
		add(titlePnl);
		//
		JLabel detailTitle=new JLabel("技术统计 - LAL      ");
		detailTitle.setFont(new Font("微软雅黑",Font.PLAIN,20));
		detailTitle.setForeground(Color.white);
		titlePnl.add(detailTitle);
		//
		switchLbl=new JLabel("切换");
		switchLbl.setFont(new Font("微软雅黑",Font.PLAIN,14));
		switchLbl.setForeground(Color.WHITE);
		switchLbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		switchLbl.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseExited(MouseEvent e) {
				switchLbl.setForeground(Color.white);
			}
			
			public void mouseEntered(MouseEvent e) {
				switchLbl.setForeground(Style.FOCUS_BLUE);
			}
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
		titlePnl.add(switchLbl);
		// ---jsp---------------
		jsp = new JScrollPane();
		gbc.gridy = 6;
		gbc.gridheight=6;
		gbc.weighty=14;
		gbl.setConstraints(jsp, gbc);
		add(jsp);
	}
}
