package newui.playerui.details;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import newui.MyUIDataFormater;
import newui.Service;
import newui.Style;
import vo.PlayerVO;
import bl.Match;
import blService.PlayerBLService;

public class PKChart extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PlayerBLService player;
	PlayerVO aVO, bVO;
	ADataPanel aScorePnl, aReboundPnl, aAssistPnl, aTpPnl, aFsPnl;
	BDataPanel bScorePnl, bReboundPnl, bAssistPnl, bTpPnl, bFsPnl;
	DataLabel scoreTitle, reboundTitle, assistTitle, tpTitle, fsTitle;
	boolean aBigger;

	public PKChart(PlayerPKPanel father) {
		aBigger = false;
		// ---------------------------------
		player = Service.player;
		aVO = player.getPlayerSeasonInfo(Match.getCurrentSeason(), "Team",
				father.namePnl.aLbl.getText()).get(0);
		bVO = player.getPlayerSeasonInfo(Match.getCurrentSeason(), "Team",
				father.namePnl.bLbl.getText()).get(0);
		// --------------------------------------
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		setLayout(gbl);
		// ------------titleLbl------------------
		JLabel titleLbl = new JLabel("赛季数据");
		titleLbl.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		titleLbl.setForeground(Style.BACK_GREY);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 11;
		gbc.gridheight = 1;
		gbc.weightx = 11;
		gbc.weighty = 0.1;
		gbl.setConstraints(titleLbl, gbc);
		add(titleLbl);
		// ---------大区划分-------------------
		// Layer1-----------------------------
		aScorePnl = new ADataPanel();
		gbc.gridy = 1;
		gbc.gridwidth = 5;
		gbc.weightx = 5;
		gbc.weighty = 1;
		gbl.setConstraints(aScorePnl, gbc);
		add(aScorePnl);
		//
		scoreTitle = new DataLabel("场均得分");
		gbc.gridx = 5;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbl.setConstraints(scoreTitle, gbc);
		add(scoreTitle);
		//
		bScorePnl = new BDataPanel();
		gbc.gridx = 6;
		gbc.gridwidth = 5;
		gbc.weightx = 5;
		gbl.setConstraints(bScorePnl, gbc);
		add(bScorePnl);
		// Layer2-----------------------------
		aReboundPnl = new ADataPanel();
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbl.setConstraints(aReboundPnl, gbc);
		add(aReboundPnl);
		//
		reboundTitle = new DataLabel("场均篮板");
		gbc.gridx = 5;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbl.setConstraints(reboundTitle, gbc);
		add(reboundTitle);
		//
		bReboundPnl = new BDataPanel();
		gbc.gridx = 6;
		gbc.gridwidth = 5;
		gbc.weightx = 5;
		gbl.setConstraints(bReboundPnl, gbc);
		add(bReboundPnl);
		// Layer3-----------------------------
		aAssistPnl = new ADataPanel();
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbl.setConstraints(aAssistPnl, gbc);
		add(aAssistPnl);
		//
		assistTitle = new DataLabel("场均助攻");
		gbc.gridx = 5;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbl.setConstraints(assistTitle, gbc);
		add(assistTitle);
		//
		bAssistPnl = new BDataPanel();
		gbc.gridx = 6;
		gbc.gridwidth = 5;
		gbc.weightx = 5;
		gbl.setConstraints(bAssistPnl, gbc);
		add(bAssistPnl);
		// Layer4-----------------------------
		aTpPnl = new ADataPanel();
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbl.setConstraints(aTpPnl, gbc);
		add(aTpPnl);
		//
		tpTitle = new DataLabel("三分%");
		gbc.gridx = 5;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbl.setConstraints(tpTitle, gbc);
		add(tpTitle);
		//
		bTpPnl = new BDataPanel();
		gbc.gridx = 6;
		gbc.gridwidth = 5;
		gbc.weightx = 5;
		gbl.setConstraints(bTpPnl, gbc);
		add(bTpPnl);
		// Layer5-----------------------------
		aFsPnl = new ADataPanel();
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbl.setConstraints(aFsPnl, gbc);
		add(aFsPnl);
		//
		fsTitle = new DataLabel("罚球%");
		gbc.gridx = 5;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbl.setConstraints(fsTitle, gbc);
		add(fsTitle);
		//
		bFsPnl = new BDataPanel();
		gbc.gridx = 6;
		gbc.gridwidth = 5;
		gbc.weightx = 5;
		gbl.setConstraints(bFsPnl, gbc);
		add(bFsPnl);
		// -------layer1--------------------
		double aScore, bScore;
		if (aVO.getPlayedGames() == 0)
			aScore = 0.0;
		else
			aScore = aVO.getScore() / aVO.getPlayedGames();
		if (bVO.getPlayedGames() == 0)
			bScore = 0.0;
		else
			bScore = bVO.getScore() / bVO.getPlayedGames();
		if (aScore > bScore)
			aBigger = true;
		aScorePnl.add(new DataLabel(MyUIDataFormater.formatTo1(aScore)));
		aScorePnl.add(new MyRect((int) aScore, aBigger));
		bScorePnl.add(new MyRect((int) bScore, !aBigger));
		bScorePnl.add(new DataLabel(MyUIDataFormater.formatTo1(bScore)));
		// -------layer2--------------------
		if (aVO.getPlayedGames() == 0)
			aScore = 0.0;
		else
			aScore = aVO.getReboundNum() / aVO.getPlayedGames();
		if (bVO.getPlayedGames() == 0)
			bScore = 0.0;
		else
			bScore = bVO.getReboundNum() / bVO.getPlayedGames();
		if (aScore > bScore)
			aBigger = true;
		aReboundPnl.add(new DataLabel(MyUIDataFormater.formatTo1(aScore)));
		aReboundPnl.add(new MyRect((int) aScore, aBigger));
		bReboundPnl.add(new MyRect((int) bScore, !aBigger));
		bReboundPnl.add(new DataLabel(MyUIDataFormater.formatTo1(bScore)));
		// -------layer3-------------------
		if (aVO.getPlayedGames() == 0)
			aScore = 0.0;
		else
			aScore = aVO.getAssistNum() / aVO.getPlayedGames();
		if (bVO.getPlayedGames() == 0)
			bScore = 0.0;
		else
			bScore = bVO.getAssistNum() / bVO.getPlayedGames();
		if (aScore > bScore)
			aBigger = true;
		aAssistPnl.add(new DataLabel(MyUIDataFormater.formatTo1(aScore)));
		aAssistPnl.add(new MyRect((int) aScore, aBigger));
		bAssistPnl.add(new MyRect((int) bScore, !aBigger));
		bAssistPnl.add(new DataLabel(MyUIDataFormater.formatTo1(bScore)));
		// -------layer4-------------------
		aScore = aVO.getThreeHitRate() * 100;
		bScore = bVO.getThreeHitRate() * 100;
		if (aScore > bScore)
			aBigger = true;
		aTpPnl.add(new DataLabel(MyUIDataFormater.formatTo1(aScore)));
		aTpPnl.add(new MyRect((int) aScore, aBigger));
		bTpPnl.add(new MyRect((int) bScore, !aBigger));
		bTpPnl.add(new DataLabel(MyUIDataFormater.formatTo1(bScore)));
		// -------layer5-------------------
		aScore = aVO.getFreeThrowHitRate() * 100;
		bScore = bVO.getFreeThrowHitRate() * 100;
		if (aScore > bScore)
			aBigger = true;
		aFsPnl.add(new DataLabel(MyUIDataFormater.formatTo1(aScore)));
		aFsPnl.add(new MyRect((int) aScore, aBigger));
		bFsPnl.add(new MyRect((int) bScore, !aBigger));
		bFsPnl.add(new DataLabel(MyUIDataFormater.formatTo1(bScore)));
		
		revalidate();
	}

	class MyRect extends JPanel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		int width;
		Color fillColor;

		public MyRect(int w, boolean isBlue) {
			width = w*3;
			setPreferredSize(new Dimension(width, 30));
			if (isBlue)
				fillColor = Style.DEEP_BLUE;
			else
				fillColor = Style.BACK_GREY;
		}

		public void paint(Graphics g) {
			g.setColor(fillColor);
			g.fillRect(0, 0, width, 30);
		}
	}

	class DataLabel extends JLabel {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public DataLabel(String txt) {
			super(txt);
			setFont(new Font("微软雅黑", Font.PLAIN, 13));
			setForeground(Style.BACK_GREY);
		}
	}

	class ADataPanel extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ADataPanel() {
			super();
			setBackground(Color.white);
			FlowLayout fl = new FlowLayout(FlowLayout.RIGHT, 5, 0);
			setLayout(fl);
		}
	}

	class BDataPanel extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public BDataPanel() {
			super();
			setBackground(Color.white);
			FlowLayout fl = new FlowLayout(FlowLayout.LEFT, 5, 0);
			setLayout(fl);
		}
	}
}
