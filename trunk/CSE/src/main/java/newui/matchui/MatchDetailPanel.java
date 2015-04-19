package newui.matchui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import newui.FatherPanel;
import newui.Style;

public class MatchDetailPanel extends FatherPanel {

	private static final long serialVersionUID = 1L;
	DetailCard card;
	JPanel titlePnl;
	JScrollPane jsp;

	public MatchDetailPanel() {
		// ----card------------
		card = new DetailCard();
		gbc.gridy = 1;
		gbc.gridheight = 4;
		gbc.weighty = 4;
		gbl.setConstraints(card, gbc);
		add(card);
		// ----titlePnl--------
		titlePnl = new JPanel();
		titlePnl.setBackground(Style.DEEP_BLUE);
		gbc.gridy = 5;
		gbc.gridheight = 1;
		gbc.weighty = 1;
		gbl.setConstraints(titlePnl, gbc);
		add(titlePnl);
		// ---jsp---------------
		jsp = new JScrollPane();
		gbc.gridy = 6;
		gbc.gridheight=6;
		gbc.weighty=6;
		gbl.setConstraints(jsp, gbc);
		add(jsp);
	}
}
