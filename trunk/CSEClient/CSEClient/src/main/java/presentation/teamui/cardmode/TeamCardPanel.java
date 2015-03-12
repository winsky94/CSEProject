package presentation.teamui.cardmode;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import presentation.ModeChangeButton;

public class TeamCardPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//三个基本底层panel---------------------
	JPanel sortPnl,listPnl,searchPnl;
	//sortPnl相关组件----------------------
	JComboBox<String> seasonBox;
	JLabel conditionLbl;
	ModeChangeButton modeBtn;
	
	public TeamCardPanel(){
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		this.setLayout(gbl);
		//----排序栏----------------------------------------------------------------------
		//-------------------------------------------------------------------排序栏基本设置区
		sortPnl=new JPanel();
		sortPnl.setBackground(Color.yellow);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 10;
		gbc.gridheight = 1;
		gbc.weightx = 10;
		gbc.weighty = 0.3;
		gbl.setConstraints(sortPnl, gbc);
		this.add(sortPnl);
		//----------------------------------------------------------------------排序栏组件区
		//----赛季选择ComboBox-------
		
		//----列表区---------
		listPnl=new JPanel();
		listPnl.setBackground(Color.red);
		gbc.gridy = 1;
		gbc.gridheight = 10;
		gbc.weighty = 10;
		gbl.setConstraints(listPnl, gbc);
		this.add(listPnl);
		//----搜索栏---------
		searchPnl=new JPanel();
		searchPnl.setBackground(Color.GREEN);
		gbc.gridy = 11;
		gbc.gridheight = 1;
		gbc.weighty = 0.3;
		gbl.setConstraints(searchPnl, gbc);
		this.add(searchPnl);
	}
}
