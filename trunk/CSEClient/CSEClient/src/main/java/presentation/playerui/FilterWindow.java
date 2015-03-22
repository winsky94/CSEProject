package presentation.playerui;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JWindow;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;

import presentation.mainui.ListModelFPanel;
import presentation.mainui.ListPanel;

public class FilterWindow extends JWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel pnl;
	JButton submitBtn, exitBtn;
	JComboBox<String> locationBox, partitionBox;
	ButtonGroup bg;
	MyRadioButton scoreBtn, reboundBtn, assistBtn, sraBtn, blockShotBtn,
			stealBtn, foulBtn, turnOverBtn, minuteBtn, efficiencyBtn, shootBtn,
			threePointBtn, freeThrowBtn, double_doubleBtn;
	Font font = new Font("微软雅黑", Font.PLAIN, 15);

	public FilterWindow(final ListModelFPanel list) {
		// ----底层panel--------------------
		this.setLayout(new GridLayout(1, 1));
		pnl = new JPanel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				ImageIcon icon = new ImageIcon("img/main/metal.jpg");
				Image img = icon.getImage();
				g.drawImage(img, 0,0, icon.getIconWidth(),
						icon.getIconHeight(), icon.getImageObserver());
			}
		};
		this.add(pnl);
		// --------------------------------
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.BOTH;
		pnl.setLayout(gbl);
		// ---------------------------------球员位置PNL
		JPanel locationPnl = new JPanel();
		locationPnl.setOpaque(false);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 5;
		gbc.weightx = 5;
		gbc.weighty = 0.1;
		gbl.setConstraints(locationPnl, gbc);
		pnl.add(locationPnl);
		FlowLayout fl = new FlowLayout();
		fl.setAlignment(FlowLayout.LEFT);
		locationPnl.setLayout(fl);
		// ---------locationLbl----------
		JLabel locationLbl = new JLabel("球员位置 ");
		locationLbl.setForeground(Color.lightGray);
		locationLbl.setFont(font);
		locationPnl.add(locationLbl);
		// -------locationBox-----------
		String locationTxt[] = { "全部", "前锋", "中锋", "后卫" };
		locationBox = new JComboBox<String>(locationTxt);
		locationBox.setFont(font);
		locationPnl.add(locationBox);
		// --------partitionLbl-----------
		JLabel partitionLbl = new JLabel("               联盟/分区 ");
		partitionLbl.setForeground(Color.lightGray);
		partitionLbl.setFont(font);
		locationPnl.add(partitionLbl);
		// ------partitionBox--------------
		String partitionTxt[] = { "全部", "Eastern", "Atlantic", "Central",
				"Southeast", "Western", "Northwest", "Pacific", "Southwest" };
		partitionBox = new JComboBox<String>(partitionTxt);

		partitionBox.setFont(font);
		partitionBox.setMaximumRowCount(9);
		partitionBox.setRenderer(new MyRenderer());
		locationPnl.add(partitionBox);
		// ----------------------------------排序条件Pnl
		JPanel rankPnl = new JPanel();
		rankPnl.setOpaque(false);
		gbc.gridy = 1;
		gbc.gridheight = 9;
		gbc.weighty = 9;
		gbl.setConstraints(rankPnl, gbc);
		pnl.add(rankPnl);
		rankPnl.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), "排序条件",TitledBorder.DEFAULT_JUSTIFICATION,TitledBorder.DEFAULT_POSITION , font, Color.lightGray));
		// ----排序条件------------
		rankPnl.setLayout(new GridLayout(3, 1));
		JPanel pnl1 = new JPanel();
		JPanel pnl2 = new JPanel();
		JPanel pnl3 = new JPanel();
		pnl1.setOpaque(false);
		pnl2.setOpaque(false);
		pnl3.setOpaque(false);
		rankPnl.add(pnl1);
		rankPnl.add(pnl2);
		rankPnl.add(pnl3);
		bg = new ButtonGroup();
		// ----------------------------------------pnl1
		scoreBtn=new MyRadioButton("得分");
		pnl1.add(scoreBtn);
		bg.add(scoreBtn);
		scoreBtn.setSelected(true);
		reboundBtn=new MyRadioButton("篮板");
		pnl1.add(reboundBtn);
		bg.add(reboundBtn);
		assistBtn=new MyRadioButton("助攻");
		pnl1.add(assistBtn);
		bg.add(assistBtn);
		//-----------------------------------------pnl2
		sraBtn=new MyRadioButton("得分/篮板/助攻（加权比为1:1:1）");
		pnl2.add(sraBtn);
		bg.add(sraBtn);
		//-----------------------------------------pnl3
		blockShotBtn=new MyRadioButton("盖帽");
		pnl3.add(blockShotBtn);
		bg.add(blockShotBtn);
		stealBtn=new MyRadioButton("抢断");
		pnl3.add(stealBtn);
		bg.add(stealBtn);
		foulBtn=new MyRadioButton("犯规");
		pnl3.add(foulBtn);
		bg.add(foulBtn);
		//------------------------------------------pnl4
		turnOverBtn=new MyRadioButton("失误");
		pnl3.add(turnOverBtn);
		bg.add(turnOverBtn);
		minuteBtn=new MyRadioButton("分钟");
		pnl3.add(minuteBtn);
		bg.add(minuteBtn);
		efficiencyBtn=new MyRadioButton("效率");
		pnl3.add(efficiencyBtn);
		bg.add(efficiencyBtn);
		//------------------------------------------pnl5
		shootBtn=new MyRadioButton("投篮");
		pnl2.add(shootBtn);
		bg.add(shootBtn);
		threePointBtn=new MyRadioButton("三分");
		pnl2.add(threePointBtn);
		bg.add(threePointBtn);
		freeThrowBtn=new MyRadioButton("罚球");
		pnl1.add(freeThrowBtn);
		bg.add(freeThrowBtn);
		//-------------------------------------------pnl6
		double_doubleBtn=new MyRadioButton("两双");
		pnl1.add(double_doubleBtn);
		bg.add(double_doubleBtn);
		// ------------------------------------确定取消Pnl
		JPanel surePnl = new JPanel();
		surePnl.setOpaque(false);
		gbc.gridy = 10;
		gbc.gridheight = 1;
		gbc.weighty = 0.1;
		gbl.setConstraints(surePnl, gbc);
		pnl.add(surePnl);

		// ----submitBtn---------
		submitBtn = new MyButton("筛选");
		submitBtn.setForeground(new Color(166, 210, 121));
		surePnl.add(submitBtn);
		submitBtn.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String pos=locationBox.getSelectedItem().toString();
				String union=partitionBox.getSelectedItem().toString();
				String sort=scoreBtn.getText();
				Enumeration<AbstractButton> radioBtns=bg.getElements();   
				while (radioBtns.hasMoreElements()) {   
				    AbstractButton btn = radioBtns.nextElement();   
				    if(btn.isSelected()){   
				        sort=btn.getText();   
				        break;   
				    }   
				}
				list.filterList(pos, union, sort);
				FilterWindow.this.dispose();
				
			}
			
		});
		// ----exitBtn-----------
		exitBtn = new MyButton("取消");
		exitBtn.setForeground(new Color(251, 147, 121));
		surePnl.add(exitBtn);
		// ----------------------
		this.setSize(400, 280);
		this.setVisible(true);
		exitBtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				FilterWindow.this.dispose();
			}
		});
	}


	class MyRenderer extends JLabel implements ListCellRenderer {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			if (value != null) {
				setText(value.toString());
			}
			if ((value == "Eastern") || (value == "Western")) {
				this.setFont(new Font("微软雅黑", Font.BOLD, 15));
			} else
				this.setFont(font);

			return this;
		}

	}

	class MyRadioButton extends JRadioButton {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public MyRadioButton(String text) {
			super(text);
			this.setFont(font);
			this.setForeground(Color.lightGray);
			this.setOpaque(false);
		}

	}

	class MyButton extends JButton {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public MyButton(String text) {
			super(text);
			this.setFont(font);
			this.setFocusPainted(false);
			this.setBackground(Color.white);
			this.setOpaque(false);
		}
	}
}
