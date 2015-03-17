package presentation.playerui;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JWindow;
import javax.swing.ListCellRenderer;

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
	MyCheckBox scoreCheckBox, reboundCheckBox, assistCheckBox,
			blockShotCheckBox, stealCheckBox;
	Font font = new Font("微软雅黑", Font.PLAIN, 15);

	public FilterWindow() {
		// ----底层panel--------------------
		this.setLayout(new GridLayout(1, 1));
		pnl = new JPanel();
		this.add(pnl);
		// --------------------------------
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);
		gbc.fill = GridBagConstraints.BOTH;
		pnl.setLayout(gbl);
		// ---------------------------------球员位置PNL
		JPanel locationPnl = new JPanel();
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
		locationLbl.setFont(font);
		locationPnl.add(locationLbl);
		// -------locationBox-----------
		String locationTxt[] = { "全部", "前锋", "中锋", "后卫" };
		locationBox = new JComboBox<String>(locationTxt);
		locationBox.setFont(font);
		locationPnl.add(locationBox);
		// --------partitionLbl-----------
		JLabel partitionLbl = new JLabel("               联盟/分区 ");
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
		gbc.gridy = 1;
		gbc.gridheight = 9;
		gbc.weighty = 9;
		gbl.setConstraints(rankPnl, gbc);
		pnl.add(rankPnl);
		// ----排序条件------------
		rankPnl.setLayout(new GridLayout(6, 1));
		JPanel pnl1 = new JPanel();
		JPanel pnl2 = new JPanel();
		JPanel pnl3 = new JPanel();
		JPanel pnl4 = new JPanel();
		JPanel pnl5 = new JPanel();
		JPanel pnl6 = new JPanel();
		rankPnl.add(pnl1);
		rankPnl.add(pnl2);
		rankPnl.add(pnl3);
		rankPnl.add(pnl4);
		rankPnl.add(pnl5);
		rankPnl.add(pnl6);
		bg = new ButtonGroup();
		// ----------------------------------------pnl1
		scoreBtn=new MyRadioButton("得分");
		pnl1.add(scoreBtn);
		bg.add(scoreBtn);
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
		pnl4.add(turnOverBtn);
		bg.add(turnOverBtn);
		minuteBtn=new MyRadioButton("分钟");
		pnl4.add(minuteBtn);
		bg.add(minuteBtn);
		efficiencyBtn=new MyRadioButton("效率");
		pnl4.add(efficiencyBtn);
		bg.add(efficiencyBtn);
		//------------------------------------------pnl5
		shootBtn=new MyRadioButton("投篮");
		pnl5.add(shootBtn);
		bg.add(shootBtn);
		threePointBtn=new MyRadioButton("三分");
		pnl5.add(threePointBtn);
		bg.add(threePointBtn);
		freeThrowBtn=new MyRadioButton("罚球");
		pnl5.add(freeThrowBtn);
		bg.add(freeThrowBtn);
		//-------------------------------------------pnl6
		double_doubleBtn=new MyRadioButton("两双");
		pnl6.add(double_doubleBtn);
		bg.add(double_doubleBtn);
		scoreCheckBox=new MyCheckBox("得分",true);
		pnl6.add(scoreCheckBox);
		reboundCheckBox=new MyCheckBox("篮板",true);
		pnl6.add(reboundCheckBox);
		assistCheckBox=new MyCheckBox("助攻");
		pnl6.add(assistCheckBox);
		blockShotCheckBox=new MyCheckBox("盖帽");
		pnl6.add(blockShotCheckBox);
		stealCheckBox=new MyCheckBox("抢断");
		pnl6.add(stealCheckBox);
		// ------------------------------------确定取消Pnl
		JPanel surePnl = new JPanel();
		gbc.gridy = 10;
		gbc.gridheight = 1;
		gbc.weighty = 0.1;
		gbl.setConstraints(surePnl, gbc);
		pnl.add(surePnl);

		// ----submitBtn---------
		submitBtn = new MyButton("筛选");
		surePnl.add(submitBtn);
		// ----exitBtn-----------
		exitBtn = new MyButton("取消");
		surePnl.add(exitBtn);
		// ----------------------
		this.setSize(400, 450);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new FilterWindow();
	}

	class MyRenderer extends JLabel implements ListCellRenderer {

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
		}

	}

	class MyButton extends JButton {
		public MyButton(String text) {
			super(text);
			this.setFont(font);
			this.setFocusPainted(false);
		}
	}

	class MyCheckBox extends JCheckBox {
		public MyCheckBox(String text) {
			super(text);
			this.setFont(font);
			
		}
		public MyCheckBox(String text,boolean isSelected) {
			super(text,isSelected);
			this.setFont(font);
			
		}
	}
}
