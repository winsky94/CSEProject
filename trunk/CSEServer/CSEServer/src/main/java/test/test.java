package test;

import java.awt.Graphics;
import java.awt.Image;
import java.rmi.RemoteException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import data.Player;

public class test extends JFrame {
	JPanel pnl;

	public test() {
		pnl = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
//				ImageIcon icon = new ImageIcon("img/main/metal.jpg");
				Player player;
				try {
					player = new Player();
					ImageIcon icon=player.getPlayerImage("Aaron Brooks");
					Image img = icon.getImage();
					g.drawImage(img, 0, 0, icon.getIconWidth(),
							icon.getIconHeight(), icon.getImageObserver());
				} catch (RemoteException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				
			}
		};
		this.add(pnl);
		this.setSize(500, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new test();
	}
}
