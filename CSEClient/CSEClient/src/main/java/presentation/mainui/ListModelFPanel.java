package presentation.mainui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import presentation.ModeChangeButton;
import presentation.OrderChangeButton;

/*
 * author:jin
 * 列表搜索模式球员球队共用框架
 * 
 * */
public class ListModelFPanel extends ContentPanel{
	protected JSplitPane jsppane;
	protected JPanel searchPanel,listPanel;
	public ListModelFPanel(int model,JPanel list) {
		super();
		initSearchPanel( model);
		// TODO Auto-generated constructor stub
		listPanel=list;
		jsppane=new JSplitPane(JSplitPane.VERTICAL_SPLIT,searchPanel,listPanel);
		jsppane.setDividerLocation(0.05);
		add(jsppane);
		jsppane.setBounds(Scale.PANE);
		
		
	}
	
	
	private void initSearchPanel(int model) {
		// TODO Auto-generated method stub
		searchPanel=new JPanel();
		OrderChangeButton down=new OrderChangeButton(0);
		OrderChangeButton up=new OrderChangeButton(1);
		ModeChangeButton card=new ModeChangeButton(model);
		JLabel filter=new JLabel("按条件筛选");
		String[] s={"赛季","2012","2013"};
		JComboBox<String> season=new JComboBox<String>(s);
		JTextField search=new JTextField(20);
		JButton searchBtn=new JButton("搜索");
		searchPanel.setLayout(null);
		add(down);add(up);add(card);
		add(filter);add(season);add(search);add(searchBtn);
		//=====位位置设定======
		
		
		
	}


	public void setListPanel(JPanel listpanel){
		jsppane.setRightComponent(listpanel);
		
	}

}
