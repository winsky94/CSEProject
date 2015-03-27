package newui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import newui.mainui.IndexPanel;
import newui.mainui.MainFrame;
import newui.matchui.MatchIndexPanel;
import newui.playerui.PlayerIndexPanel;
import newui.teamui.TeamIndexPanel;
import newui.teamui.TeamWindow;

public class TitleBar extends JPanel implements MouseListener{
	/**
	 * 需对文本框和搜索按钮加监听
	 */
	private static final long serialVersionUID = 1L;
	Color color=new Color(6,73,130);//深蓝，未指向时
	Color focusColor=new Color(53,109,160);//选中或指向时的蓝色
	Font font = new Font("微软雅黑", Font.PLAIN, 15);
	MyButton indexBtn,teamBtn,playerBtn,matchBtn;
	JTextField searchFld;
	JLabel searchBtn;
	public TitleBar(){
		setBackground(color);
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill=GridBagConstraints.BOTH;
		setLayout(gbl);
		//---------------------------
		indexBtn=new MyButton("首页");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbl.setConstraints(indexBtn, gbc);
		add(indexBtn);
		//----------------------------
		teamBtn=new MyButton("球队");
		gbc.gridx =1;
		gbl.setConstraints(teamBtn, gbc);
		add(teamBtn);
		//----------------------------
		playerBtn=new MyButton("球员");
		gbc.gridx =2;
		gbl.setConstraints(playerBtn, gbc);
		add(playerBtn);
		//-----------------------------
		matchBtn=new MyButton("比赛");
		gbc.gridx =3;
		gbl.setConstraints(matchBtn, gbc);
		add(matchBtn);
		//-----------------------------
		JLabel blank=new JLabel("        ");
		gbc.gridx=4;
		gbc.gridwidth = 3;
		gbc.weightx = 10;
		gbl.setConstraints(blank, gbc);
		add(blank);
		//-----------------------------
		searchFld=new JTextField(10);
		gbc.insets=new Insets(0, 100, 0, 0);
		gbc.fill=GridBagConstraints.HORIZONTAL;
		gbc.gridx = 7;
		gbc.gridwidth = 3;
		gbc.weightx = 3;
		gbl.setConstraints(searchFld, gbc);
		add(searchFld);
		//-----------------------------
		searchBtn=new JLabel(new ImageIcon("image/search.png"));
		gbc.fill=GridBagConstraints.BOTH;
		gbc.insets=new Insets(0, 0, 0, 0);
		gbc.gridx = 10;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbl.setConstraints(searchBtn, gbc);
		searchBtn.addMouseListener(this);
		add(searchBtn);
	}
	class MyButton extends JButton{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public MyButton(String text){
			super(text);
			setFont(font);
			setForeground(Color.white);
			setBackground(color);
			setFocusPainted(false);
			setBorderPainted(false);
			addMouseListener(TitleBar.this);
		}
	}

	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==indexBtn){
			MainFrame.getInstance().setContentPanel(new IndexPanel());
		}
		if(e.getSource()==teamBtn)
			MainFrame.getInstance().setContentPanel(new TeamIndexPanel());
		if(e.getSource()==playerBtn)
			MainFrame.getInstance().setContentPanel(new PlayerIndexPanel());
		if(e.getSource()==matchBtn)
			MainFrame.getInstance().setContentPanel(new MatchIndexPanel());
		if(e.getSource()==searchBtn){
			//监听
		}
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		if(e.getSource()==indexBtn)
			indexBtn.setBackground(focusColor);
		if(e.getSource()==teamBtn){
			teamBtn.setBackground(focusColor);
			TeamWindow.getInstance(e.getXOnScreen(),e.getYOnScreen()).setVisible(true);
			
		}
		if(e.getSource()==playerBtn)
			playerBtn.setBackground(focusColor);
		if(e.getSource()==matchBtn)
			matchBtn.setBackground(focusColor);
		if(e.getSource()==searchBtn)
			searchBtn.setIcon(new ImageIcon("image/searchFocus.png"));
	}

	public void mouseExited(MouseEvent e) {
		if(e.getSource()==indexBtn)
			indexBtn.setBackground(color);
		if(e.getSource()==teamBtn)
			teamBtn.setBackground(color);
		if(e.getSource()==playerBtn)
			playerBtn.setBackground(color);
		if(e.getSource()==matchBtn)
			matchBtn.setBackground(color);
		if(e.getSource()==searchBtn)
			searchBtn.setIcon(new ImageIcon("image/search.png"));
		
	}

}
