package presentation.mainui;

import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JWindow;

import presentation.playerui.tablemodel.PlayerTableModel;
import presentation.teamui.tablemodel.TeamTableModel;
/*author:jin
 * 选择排序依据
 * 添加到哪里呢？
 * */
public class SequencePane extends JWindow {
	private int model;//0球队，1球员
	private String[] opition;//好像拼错了
	private JScrollPane jsp;
	public SequencePane(int model){
		JPanel p=new JPanel();
		this.model=model;
		if(model==0)
			opition=TeamTableModel.getHead();
		else
			opition=PlayerTableModel.getHead();
		p.setLayout(new GridLayout(opition.length,1));
		for(String str:opition){
			JLabel temp=new JLabel(str);
			temp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			temp.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					String attr=((JLabel)(e.getSource())).getText();
					SequencePane.this.setVisible(false);
				}
			});
			p.add(temp);
		}
		jsp=new JScrollPane(p);
		add(jsp);
	
	}
	
	

}
