package presentation.mainui;

import javax.swing.JPanel;
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
	
	public FilterPane(){
		this.setVisible(false);
		
		
		
	}
	
	
}
