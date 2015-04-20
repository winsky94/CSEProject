package newui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import newui.mainui.MainFrame;

public class TopPanel extends JPanel implements MouseListener {

	/**
	 * 
	 * 期望实现：页面跳转功能
	 */
	private static final long serialVersionUID = 1L;
	MyButton miniBtn, maxBtn, exitBtn;
	boolean isMax = false;

	public TopPanel() {
		GridBagLayout g = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(0, 0, 0, 0);
		setLayout(g);
		setBackground(Color.white);
		// --------------------
		JLabel blankLbl = new JLabel();
		c.gridx = 6;
		c.gridwidth = 10;
		c.weightx = 100;
		g.setConstraints(blankLbl, c);
		add(blankLbl);
		// ---------------------
		c.insets = new Insets(0, 5, 0, 5);
		//
		miniBtn = new MyButton(new ImageIcon("image/TopTitle/minimize.png"));
		c.gridx = 16;
		c.gridwidth = 1;
		c.weightx = 0.01;
		miniBtn.addMouseListener(this);
		g.setConstraints(miniBtn, c);
		add(miniBtn);
		// ----------------------
		maxBtn = new MyButton(new ImageIcon("image/TopTitle/max.png"));
		c.gridx = 17;
		maxBtn.addMouseListener(this);
		g.setConstraints(maxBtn, c);
		add(maxBtn);
		// ----------------------
		exitBtn = new MyButton(new ImageIcon("image/TopTitle/exit.png"));
		c.gridx = 18;
		exitBtn.addMouseListener(this);
		g.setConstraints(exitBtn, c);
		add(exitBtn);
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == exitBtn)
			System.exit(0);
		if (e.getSource() == maxBtn) {
			// 监听
			if (!isMax){
				MainFrame.getInstance().setExtendedState(JFrame.MAXIMIZED_BOTH);
				isMax=true;
			}
			else{
				MainFrame.getInstance().setExtendedState(JFrame.NORMAL);
				isMax=false;
			}
		}
		if (e.getSource() == miniBtn) {
			// 监听
			MainFrame.getInstance().setExtendedState(JFrame.HIDE_ON_CLOSE);
		}

	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseEntered(MouseEvent e) {
		if (e.getSource() == exitBtn) {
			exitBtn.setIcon(new ImageIcon("image/TopTitle/exitFocus.png"));
		}
		if (e.getSource() == miniBtn) {
			miniBtn.setIcon(new ImageIcon("image/TopTitle/minimizeFocus.png"));
		}
		if (e.getSource() == maxBtn) {
			maxBtn.setIcon(new ImageIcon("image/TopTitle/maxFocus.png"));
		}
	}

	public void mouseExited(MouseEvent e) {
		if (e.getSource() == exitBtn) {
			exitBtn.setIcon(new ImageIcon("image/TopTitle/exit.png"));
		}
		if (e.getSource() == maxBtn) {
			maxBtn.setIcon(new ImageIcon("image/TopTitle/max.png"));
		}
		if (e.getSource() == miniBtn) {
			miniBtn.setIcon(new ImageIcon("image/TopTitle/minimize.png"));
		}

	}

}

class MyButton extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyButton(ImageIcon img) {
		super(img);
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}

}