package presentation.mainui;

import java.awt.Cursor;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import presentation.playerui.tablemodel.PlayerTableModel;
import presentation.teamui.tablemodel.TeamTableModel;
/*author:jin
 * 选择排序依据
 * 添加到哪里呢？
 * */
public class SequencePane extends JPanel{
	private int model;//0球队，1球员
	private String[] opition;//好像拼错了
	
	public SequencePane(int model){
		this.model=model;
		if(model==0)
			opition=TeamTableModel.getHead();
		else
			opition=PlayerTableModel.getHead();
		this.setLayout(new GridLayout(opition.length,1));
		for(String str:opition){
			JLabel temp=new JLabel(str);
			temp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			add(temp);
		}
	
	}

}
