package newui.mainui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import newui.FatherPanel;

public class IndexPanel extends FatherPanel implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel titlePnl, infoPnl;
	
	public IndexPanel() {
		removeAll();
		// ----------------------------
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		setLayout(gbl);
		// -----titlePnl-----------
		titlePnl = new TitlePanel();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 10;
		gbc.gridheight = 3;
		gbc.weightx = 10;
		gbc.weighty = 0.1;
		gbl.setConstraints(titlePnl, gbc);
		add(titlePnl);
		// ----infoPnl-------------
		gbc.insets = new Insets(50, 30, 50, 30);
		infoPnl = new JPanel();
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridheight = 7;
		gbc.weightx = 10;
		gbc.weighty = 15;
		gbl.setConstraints(infoPnl, gbc);
		add(infoPnl);
		

	}

	public void mouseClicked(MouseEvent e) {

	
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
