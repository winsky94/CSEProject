package presentation.teamui.detail;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TeamInfoPanel extends ContentPanel{
	private JScrollPane jsp;
	private JPanel test;
	private JPanel content;
	private JPanel leftFilter;
	public TeamInfoPanel(){
		addTitleBar();
		
		test=new JPanel();
		test.setSize(1000, 2000);
		test.setBackground(Color.pink);
		jsp=new JScrollPane(test);
	
		add(jsp);
		jsp.setBounds(Scale.LISTPANE);
		
		
		
		
	}
	
	public static void main(String[] args) {
		MainFrame.getInstance().refresh(new TeamInfoPanel());
	}
	

}
