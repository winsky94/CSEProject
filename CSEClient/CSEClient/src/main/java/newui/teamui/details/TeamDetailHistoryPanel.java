package newui.teamui.details;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TeamDetailHistoryPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTable table;
	JScrollPane jsp;
	JPanel funcPnl;
	MyBox seasonBox;
	MyBox typeBox;
	Font font=new Font("微软雅黑",Font.PLAIN,15);
	public TeamDetailHistoryPanel(String abbrName){
		GridBagLayout gbl=new GridBagLayout();
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.fill=GridBagConstraints.BOTH;
		setLayout(gbl);
		//------------------------
		funcPnl=new JPanel();
		funcPnl.setOpaque(false);
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.gridwidth=10;
		gbc.gridheight=1;
		gbc.weightx=10;
		gbc.weighty=0.1;
		gbl.setConstraints(funcPnl, gbc);
		add(funcPnl);
		//------------------------
		MyLabel seasonLbl=new MyLabel("赛季：");
		funcPnl.add(seasonLbl);
		//
		String[] seasonText={"我要监听"};
		seasonBox=new MyBox(seasonText);
		funcPnl.add(seasonBox);
		//
		funcPnl.add(new JLabel("              "));
		//
		MyLabel typeLbl=new MyLabel("数据类型：");
		funcPnl.add(typeLbl);
		//
		String[] typeText={"场均","赛季"};
		typeBox=new MyBox(typeText);
		funcPnl.add(typeBox);
		//----------------------
		jsp=new JScrollPane();
		gbc.gridy=1;
		gbc.gridheight=10;
		gbc.weighty=10;
		gbl.setConstraints(jsp, gbc);
		add(jsp);
	}
	class MyBox extends JComboBox<String>{

		private static final long serialVersionUID = 1L;
		public MyBox(String[] arr){
			super(arr);
			setFont(font);
		}
	}
	class MyLabel extends JLabel{

		private static final long serialVersionUID = 1L;
		public MyLabel(String text){
			super(text);
			setFont(font);
		}
	}
}
