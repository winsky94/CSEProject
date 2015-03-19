package presentation.mainui;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import presentation.ModeChangeButton;
import presentation.OrderChangeButton;
import presentation.playerui.FilterWindow;

/*
 * author:jin
 * 列表搜索模式球员球队共用框架
 * 
 * */
public class ListModelFPanel extends ContentPanel{
	protected JPanel searchPanel;
	private ListPanel listPanel;
	private JComboBox<String> season;
	private JComboBox<String> allOrAve;
	public ListModelFPanel(int model,ListPanel list) {
		super();
		addTitleBar();
		initSearchPanel( model);
		// TODO Auto-generated constructor stub
		listPanel=list;
		
		this.setOpaque(false);
	    ImageIcon ic=new ImageIcon("img/main/1.jpg");
		JButton backbtn=new JButton(ic);
		list.add(backbtn);
		backbtn.setBounds(100, 200, 100,100 );
		backbtn.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseClicked(MouseEvent e) {
			
				frame.refresh(new MainPanel());
				
			}
		});
		add(searchPanel);
		add(list);
		searchPanel.setBounds(Scale.SEARCHPANE);
		list.setBounds(Scale.LISTPANE);
		
		
	}
	
	//=====选中某一列 参数 进行升降序排序
	private void initSearchPanel(int model) {
		// TODO Auto-generated method stub
		searchPanel=new JPanel();
		OrderChangeButton down=new OrderChangeButton(0,model);
		OrderChangeButton up=new OrderChangeButton(1,model);
		ModeChangeButton card=new ModeChangeButton(model);
		JLabel filter=new JLabel("按条件筛选");
		JLabel refresh=new JLabel("刷新");
		String[] s={"赛季","13-14"};
		 season=new JComboBox<String>(s);
		String[] type={"汇总","场均"};
		allOrAve=new JComboBox<String>(type);
		JTextField search=new JTextField(20);
		JButton searchBtn=new JButton("搜索");
		searchPanel.setLayout(null);
		searchPanel.add(refresh);
		searchPanel.add(allOrAve);
		searchPanel.add(down);searchPanel.add(up);searchPanel.add(card);
		searchPanel.add(filter);searchPanel.add(season);searchPanel.add(search);
		searchPanel.add(searchBtn);
		filter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		//=====位位置设定======
		filter.setBounds(Scale.FILTER);
		season.setBounds(Scale.SEASON);
		search.setBounds(Scale.SEARCH);
		searchBtn.setBounds(Scale.SEARCHBUTTON);
		refresh.setBounds(Scale.REFRESH);
		card.setBounds(Scale.MODEL);
		up.setBounds(Scale.UP);
		down.setBounds(Scale.DOWN);
		allOrAve.setBounds(Scale.TYPE);
		filter.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				FilterWindow f=new FilterWindow(ListModelFPanel.this);
				f.setVisible(true);
				f.setBounds(e.getXOnScreen(), e.getYOnScreen(), 500, 600);
			}
		});
		
		
	}


	public void setListPanel(JPanel listpanel){
	
		
	}
	
	public void filterList(String pos,String unio,String sort){
		String sea=season.getSelectedItem().toString();
		String allorave=allOrAve.getSelectedItem().toString();
		FilterCondition c=new FilterCondition(pos,unio,sort,sea,allorave);
		listPanel.filterRefresh(c);
		
	}

}
