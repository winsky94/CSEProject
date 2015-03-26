package newui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class FatherPanel extends JPanel{

	/**
	 * 顶部导航条写在此处
	 */
	private static final long serialVersionUID = 1L;
	protected GridBagLayout gbl;
	protected GridBagConstraints gbc;
	JPanel titleBar;
	public FatherPanel(){
		gbl=new GridBagLayout();
		gbc=new GridBagConstraints();
		gbc.fill=GridBagConstraints.BOTH;
		setLayout(gbl);
		//---TitleBar-------------
		titleBar=new TitleBar();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 10;
		gbc.gridheight = 1;
		gbc.weightx = 10;
		gbc.weighty = 1;
		gbl.setConstraints(titleBar, gbc);
		add(titleBar);
		setBackground(Color.white);
	}
}
