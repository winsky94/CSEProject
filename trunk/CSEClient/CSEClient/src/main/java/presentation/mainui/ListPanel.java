package presentation.mainui;

import javax.swing.JPanel;

/*
 * 列表模式下的表格所在JPanel
 * 考虑panel复用   通过参数值 决定表头样式及表格数据Refresh方法的具体调用
 * 通过new ListPanel添加到ListModelFPanel的下方 完成列表模式
 * 
 * 根据选择的筛选排序条件 将条件列置前
 * */
public class ListPanel extends JPanel{
	
	public ListPanel(){
		
	}

}
