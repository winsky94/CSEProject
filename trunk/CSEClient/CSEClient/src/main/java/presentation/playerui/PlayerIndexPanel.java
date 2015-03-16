package presentation.playerui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import businesslogic.Player;
import businesslogicservice.PlayerBLService;
import vo.PlayerVO;

public class PlayerIndexPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel searchPnl,contentPnl;
	ArrayList<ArrayList<String>> cm=new ArrayList<ArrayList<String>> ();
	JTable player;
	public PlayerIndexPanel() {
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		this.setLayout(gbl);
		// ----检索栏--------------
		searchPnl=new JPanel();
		searchPnl.setBackground(Color.BLUE);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 7;
		gbc.gridheight = 1;
		gbc.weightx = 10;
		gbc.weighty = 0.3;
		gbl.setConstraints(searchPnl, gbc);
		this.add(searchPnl);
		//---contentPnl----------
		contentPnl=new JPanel();
		//contentPnl.setBackground(Color.yellow);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 7;
		gbc.gridheight = 6;
		gbc.weightx = 10;
		gbc.weighty = 8.5;
		gbl.setConstraints(contentPnl, gbc);
		this.add(contentPnl);
		
		//=====表格===
		
		PlayerTableModel pt=new PlayerTableModel();
		player=new JTable(pt);
		JScrollPane jsp = new JScrollPane(player);
		contentPnl.setLayout(new BorderLayout());
		contentPnl.add(jsp,BorderLayout.CENTER);
		
		
		
	}
	
	public void RefreshPlayerTable(ArrayList<PlayerVO> vo) {
		cm.clear();
//"编号", "姓名", "球衣号码", "位置", "身高", "体重", "出生日期", "年龄",
		//"球龄", "毕业学校"}
		for (PlayerVO VO : vo) {
			ArrayList<String> lineInfo = new ArrayList<String>();
			lineInfo.add(VO.getId()+"");
			lineInfo.add(VO.getName());
			lineInfo.add(VO.getNumber()+"");
			lineInfo.add(VO.getPosition());
			lineInfo.add(VO.getHeight());
			lineInfo.add(VO.getWeight()+"");
			lineInfo.add(VO.getBirth());
			lineInfo.add(VO.getAge()+"");
			lineInfo.add(VO.getExp()+"");
			lineInfo.add(VO.getSchool());
			
			cm.add(lineInfo);

		}
		player.revalidate();
		PlayerIndexPanel.this.repaint();

	}

	
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		PlayerIndexPanel index=new PlayerIndexPanel();
		frame.add(index);
		frame.setVisible(true);
		frame.setLocation(100,100);
		frame.setSize(1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PlayerBLService p=new Player();
	    ArrayList<PlayerVO> plist=p.getPlayerInfo();
	    if(plist!=null)
	    	index.RefreshPlayerTable(plist);
		
		
	}
	

	class PlayerTableModel extends AbstractTableModel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		// String head[] = { "商品编号","名称", "型号","数量","单价","金额","备注" };
		String head[] = { "编号", "姓名", "球衣号码", "位置", "身高", "体重", "出生日期", "年龄",
				"球龄", "毕业学校"};

		public int getRowCount() {
			return cm.size();
		}

		public int getColumnCount() {
			return head.length;
		}

		public String getValueAt(int row, int col) {
			return cm.get(row).get(col);
		}

		public String getColumnName(int col) {
			return head[col];
		}
	}

}
