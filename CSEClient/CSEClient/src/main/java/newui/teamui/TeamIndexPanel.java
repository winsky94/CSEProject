package newui.teamui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import newui.FatherPanel;
import newui.Style;
import newui.tables.MySortableTable;
import newui.tables.TeamTableModel;

public class TeamIndexPanel extends FatherPanel implements MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel funcPnl;
	//
	JScrollPane jsp;
	JTable table;
	TeamTableModel ttm;
	//
	JLabel refreshLbl;
	JComboBox<String> seasonBox,typeBox;
	Font font = new Font("微软雅黑", Font.PLAIN, 13);
	public TeamIndexPanel(){
		super();
		//------funcPnl--------
		funcPnl=new JPanel();
		funcPnl.setBackground(Style.BACK_GREY);
		gbc.gridy=1;
		gbc.gridheight = 1;
		gbc.weighty = 0.1;
		gbl.setConstraints(funcPnl, gbc);
		add(funcPnl);
		//-----season----------
		JLabel seasonBoxLbl=new MyJLabel("赛季：");
		funcPnl.add(seasonBoxLbl);
		String[] seasonBoxText={"我需要监听了啦"};
		seasonBox=new MyComboBox(seasonBoxText);
		funcPnl.add(seasonBox);
		funcPnl.add(new JLabel("       "));
		//----DataType---------
		JLabel typeLbl=new MyJLabel("数据类型：");
		funcPnl.add(typeLbl);
		String[] typeText={"场均","赛季"};
		typeBox=new MyComboBox(typeText);
		funcPnl.add(typeBox);
		funcPnl.add(new JLabel("       "));
		//-----refreshLbl------
		refreshLbl=new MyJLabel("刷新",new ImageIcon("image/refreshWhite.png"));
		refreshLbl.addMouseListener(this);
		funcPnl.add(refreshLbl);
		//-----table-----------
		ttm=new TeamTableModel();
		table=new MySortableTable(ttm);
		jsp=new JScrollPane(table);
		gbc.gridy = 2;
		gbc.gridheight = 10;
		gbc.weighty = 10;
		gbl.setConstraints(jsp, gbc);
		add(jsp);
	}
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseEntered(MouseEvent e) {
		if(e.getSource()==refreshLbl){
			refreshLbl.setForeground(Style.FOCUS_BLUE);
			refreshLbl.setIcon(new ImageIcon("image/refreshFocus.png"));
			refreshLbl.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		
	}
	public void mouseExited(MouseEvent e) {
		if(e.getSource()==refreshLbl){
			refreshLbl.setForeground(Color.white);
			refreshLbl.setIcon(new ImageIcon("image/refreshWhite.png"));
			refreshLbl.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		
	}
	class MyJLabel extends JLabel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public MyJLabel(String text){
			super(text);
			setFont(font);
			setForeground(Color.white);
		}
		public MyJLabel(String text,ImageIcon img){
			super(text,img,JLabel.CENTER);
			setFont(font);
			setForeground(Color.white);
		}
		
	}
	class MyComboBox extends JComboBox<String>{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public MyComboBox(String[] text){
			super(text);
			setFont(font);
		}
	}
}
