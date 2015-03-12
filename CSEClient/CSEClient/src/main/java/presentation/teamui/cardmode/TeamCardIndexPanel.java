package presentation.teamui.cardmode;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class TeamCardIndexPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	TeamCardPanel tcp;
	TeamDetailPanel tdp;
	public TeamCardIndexPanel(){
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		this.setLayout(gbl);
		//---左侧为球队卡片列表panel即tcp----
		tcp=new TeamCardPanel();
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.gridheight=10;
		gbc.gridwidth=3;
		gbc.weightx=3;
		gbc.weighty=10;
		gbl.setConstraints(tcp, gbc);
		this.add(tcp);
		//---右侧为球队详细信息列表即tdp----
		tdp=new TeamDetailPanel();
		gbc.gridx=3;
		gbc.gridwidth=6;
		gbc.weightx=6;
		gbl.setConstraints(tdp, gbc);
		this.add(tdp);
		
	}

}
