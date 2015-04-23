package newui.mainui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import newui.UIhelper;

public class AnimeFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	int sWidth=UIhelper.getScreenWidth();
	int sHeight=UIhelper.getScreenHeight();
	JPanel pnl;
	public AnimeFrame(){
		setBounds((sWidth-570)/22,(sHeight-304)/2,570,304);
		setUndecorated(true);
		pnl = new JPanel(){
			private static final long serialVersionUID = 1L;
			// 给panel加上图片
			protected void paintComponent(Graphics g) {
				ImageIcon icon = new ImageIcon("");
				Image img = icon.getImage();
				g.drawImage(img, 0, 0, icon.getIconWidth(),
						icon.getIconHeight(), icon.getImageObserver());
			}
		};
		add(pnl);
		setVisible(true);
		repaint();
	}
}
