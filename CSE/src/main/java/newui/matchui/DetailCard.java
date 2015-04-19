package newui.matchui;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


import vo.MatchVO;


import newui.Style;

public class DetailCard extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;

	JPanel homeNamePnl, visitingNamePnl;
	JLabel hchNameLbl, habbrNameLbl, homeScore, homeIcon, visitingIcon,
			vchNameLbl, vabbrNameLbl, visitingScore;
	JPanel detailPnl;
	public DetailCard(MatchVO v){
		GridBagLayout gbl=new GridBagLayout();
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.fill=GridBagConstraints.BOTH;
		gbc.insets = new Insets(0, 40, 0, 40);
		setOpaque(false);

		setLayout(gbl);
		// ----homeNamePnl--------
		homeNamePnl = new JPanel();
		homeNamePnl.setLayout(new GridLayout(2, 1));
		homeNamePnl.setOpaque(false);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.gridheight = 1;
		gbc.weightx = 2;
		gbc.weighty = 1;
		gbl.setConstraints(homeNamePnl, gbc);
		add(homeNamePnl);
		// -----hchNameLbl------
		hchNameLbl = new CHNameLabel("主队的中文名");
		homeNamePnl.add(hchNameLbl);
		// ----habbrNameLbl-----
		habbrNameLbl = new AbbrNameLabel("Abbr");
		homeNamePnl.add(habbrNameLbl);
		// ------homeScore-------
		homeScore = new ScoreLabel("108");
		gbc.gridx = 2;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbl.setConstraints(homeScore, gbc);
		add(homeScore);
		// -----homeIcon---------
		homeIcon = new JLabel(
				new ImageIcon("image/teamIcon/teamsPng90/LAL.png"));
		gbc.gridx = 3;
		gbc.gridwidth = 2;
		gbc.weightx = 2;
		gbl.setConstraints(homeIcon, gbc);
		add(homeIcon);
		// -----vs----------------
		JLabel vs = new JLabel("  VS  ");
		vs.setFont(new Font("微软雅黑", Font.BOLD, 22));
		vs.setForeground(Style.HOT_RED);
		gbc.gridx = 5;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbl.setConstraints(vs, gbc);
		add(vs);
		// -----visitingIcon---------
		visitingIcon = new JLabel(new ImageIcon(
				"image/teamIcon/teamsPng90/NOP.png"));
		gbc.gridx = 6;
		gbc.gridwidth = 2;
		gbc.weightx = 2;
		gbl.setConstraints(visitingIcon, gbc);
		add(visitingIcon);
		// ------visitingScore-------
		visitingScore = new ScoreLabel("101");
		gbc.gridx = 8;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbl.setConstraints(visitingScore, gbc);
		add(visitingScore);
		// ----visitingNamePnl--------
		visitingNamePnl = new JPanel();
		visitingNamePnl.setLayout(new GridLayout(2, 1));
		visitingNamePnl.setOpaque(false);
		gbc.gridx =9;
		gbc.gridwidth = 2;
		gbc.weightx = 2;
		gbl.setConstraints(visitingNamePnl, gbc);
		add(visitingNamePnl);
		// -----vchNameLbl------
		vchNameLbl = new CHNameLabel("客队的中文名");
		visitingNamePnl.add(vchNameLbl);
		// ----habbrNameLbl-----
		vabbrNameLbl = new AbbrNameLabel("abbr");
		visitingNamePnl.add(vabbrNameLbl);
		//----------detailPanel-------
		detailPnl=new JPanel();
		detailPnl.setOpaque(false);
		gbc.gridx =2;
		gbc.gridy = 1;
		gbc.gridwidth = 7;
		gbc.gridheight = 1;
		gbc.weightx = 7;
		gbc.weighty = 1;
		gbl.setConstraints(detailPnl, gbc);
		add(detailPnl);
		//------------
		int col=5;
		detailPnl.setLayout(new GridLayout(3,col));
		detailPnl.add(new JLabel());
		for(int i=1;i<col;i++){
			JLabel temp=new DetailLabel(String.valueOf(i));
			temp.setFont(new Font("微软雅黑",Font.PLAIN,14));
			temp.setForeground(Style.FOCUS_GREY);
			detailPnl.add(temp);
		}
		detailPnl.add(new DetailLabel("LAL"));
		detailPnl.add(new DetailLabel("30"));
		detailPnl.add(new DetailLabel("30"));
		detailPnl.add(new DetailLabel("30"));
		detailPnl.add(new DetailLabel("30"));
		detailPnl.add(new DetailLabel("ABC"));
		detailPnl.add(new DetailLabel("40"));
		detailPnl.add(new DetailLabel("40"));
		detailPnl.add(new DetailLabel("40"));
		detailPnl.add(new DetailLabel("40"));
		
	}
	class DetailLabel extends JLabel{
		private static final long serialVersionUID = 1L;

		public DetailLabel(String txt) {
			super(txt);
			setFont(new Font("微软雅黑", Font.PLAIN,14));
			setForeground(Style.FOCUS_GREY);
		}
	}
	class CHNameLabel extends JLabel {
		private static final long serialVersionUID = 1L;

		public CHNameLabel(String txt) {
			super(txt);
			setFont(new Font("微软雅黑", Font.PLAIN, 22));
			setForeground(Style.FOCUS_GREY);
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			addMouseListener(DetailCard.this);
		}

	}

	class AbbrNameLabel extends JLabel {
		private static final long serialVersionUID = 1L;

		public AbbrNameLabel(String txt) {
			super(txt);
			setFont(new Font("微软雅黑", Font.PLAIN, 16));
			setForeground(Style.FOCUS_GREY);
		}
	}

	class ScoreLabel extends JLabel {
		private static final long serialVersionUID = 1L;

		public ScoreLabel(String txt) {
			super(txt);
			setFont(new Font("微软雅黑", Font.PLAIN, 25));
			setForeground(Style.FOCUS_GREY);
			/**
			 * 谁的分高就是红色(Style.WINNER_RED)
			 */
		}
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
		if (e.getSource().getClass() == CHNameLabel.class) {
			CHNameLabel lbl = (CHNameLabel) e.getSource();
			lbl.setForeground(Style.FOCUS_BLUE);
		}

	}

	public void mouseExited(MouseEvent e) {
		if (e.getSource().getClass() == CHNameLabel.class) {
			CHNameLabel lbl = (CHNameLabel) e.getSource();
			lbl.setForeground(Style.FOCUS_GREY);
		}
	}
}
