package newui.hotui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class HotFatherPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	GridBagLayout gbl;
	GridBagConstraints gbc;
	JPanel bottomBar;
	JPanel bestPnl;
	JScrollPane jsp;
	public HotFatherPanel(){
		gbl=new GridBagLayout();
		gbc=new GridBagConstraints();
		gbc.fill=GridBagConstraints.BOTH;
		setLayout(gbl);
		//---------------------
		bestPnl=new JPanel();
		bestPnl.setBackground(Color.white);
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.gridwidth=10;
		gbc.gridheight=3;
		gbc.weightx=10;
		gbc.weighty=3;
		gbl.setConstraints(bestPnl, gbc);
		add(bestPnl);
		//----------------------
		jsp=new JScrollPane();
		gbc.gridx=0;
		gbc.gridy=3;
		gbc.gridwidth=10;
		gbc.gridheight=3;
		gbc.weightx=10;
		gbc.weighty=3;
		gbl.setConstraints(bestPnl, gbc);
		add(bestPnl);
	}
}
