package newui.matchui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class DetailCard extends JPanel{
	private static final long serialVersionUID = 1L;
	
	public DetailCard(){
		GridBagLayout gbl=new GridBagLayout();
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.fill=GridBagConstraints.BOTH;
		setLayout(gbl);
		
	}
}
