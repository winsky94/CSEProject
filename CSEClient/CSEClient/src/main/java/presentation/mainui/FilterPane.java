package presentation.mainui;

import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JWindow;
/*
 * author: jin
 * 筛选panel
 * 考虑复用
 * 
 * */
public class FilterPane extends JWindow {
/*筛选条件  筛选前50位
 * 球员位置(前锋，中锋，后卫)
 * 球员联盟（东部，西部及各自分区）
 * 排序依据（（得分，篮板，助攻，得分/篮板/助攻（加权比为 1:1:1），盖帽，抢断，犯
  规，失误，分钟，效率，投篮，三分，罚球，两双（特指得分、篮板、助攻、抢
  断、盖帽中任何两项 ））
 * 
 * */
	private final int FILTERNUMBER=50;
	private String[] position={"前锋","中锋","后卫"};
	private String[] union={"东部","西部","各自分区？"};
	private String[] sequence={"得分","篮板","助攻","得分/篮板/助攻","盖帽","抢断","犯规"
			,"失误","分钟","效率","投篮","三分","罚球","两双"};
	private String[] double_double={"得分","篮板","助攻","抢断","盖帽"};
	JPanel posPanel=new JPanel();
	JPanel area=new JPanel();
	public FilterPane(){
		this.setVisible(false);
		initView();
		
		
		
	}
	private void initView() {
		// TODO Auto-generated method stub
		JLabel title=new JLabel("球员位置：");
		FlowLayout flow=new FlowLayout();
		flow.setAlignment(FlowLayout.LEFT);
		posPanel.setLayout(flow);
		posPanel.add(title);
		for(String str:position){
			JLabel temp=new JLabel(str);
			posPanel.add(temp);
		}
		
		//======分区选择=====
		title=new JLabel("球员联盟:");
		JRadioButton east=new JRadioButton("东部");
		String[] eastArea={"s"};
		JComboBox<String> eastRegion=new JComboBox<String>(eastArea);
		JRadioButton west=new JRadioButton("西部");
		String[] westArea={};
		JComboBox<String> westRegion=new JComboBox<String>(westArea);
		
		
		
	}
	
	
}
