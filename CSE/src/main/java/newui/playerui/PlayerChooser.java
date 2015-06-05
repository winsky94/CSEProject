package newui.playerui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import newui.Style;
import newui.UIhelper;
import newui.VOLabel;
import vo.PlayerVO;
import bl.player.Player;
import blservice.PlayerBLService;

public class PlayerChooser extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField jtf;
	JButton exitBtn;
	JScrollPane jsp;
	PlayerBLService p;
	VOLabel owner;
	public PlayerChooser(VOLabel o) {
		owner=o;
		p = new Player();
		int screenWidth = UIhelper.getScreenWidth();
		int screenHeight = UIhelper.getScreenHeight();
		setBounds((screenWidth - 550) / 2, (screenHeight - 400) / 2, 550, 400);
		setUndecorated(true);
		getContentPane().setBackground(Style.DEEP_BLUE);
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		// ------------------------------
		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		setLayout(gbl);
		// ------jtf-------------------
		gbc.insets = new Insets(5, 4, 5, 5);
		jtf = new JTextField();
		jtf.setFont(new Font("华文细黑", Font.PLAIN, 18));
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 8;
		gbc.gridheight = 1;
		gbc.weightx = 8;
		gbc.weighty = 0.1;
		gbl.setConstraints(jtf, gbc);
		add(jtf);
		jtf.addKeyListener(new jtfListener());
		// -----exitBtn-----------------
		gbc.insets = new Insets(5, 0, 5, 4);
		exitBtn = new JButton("放弃");
		exitBtn.setBackground(Style.DEEP_BLUE);
		exitBtn.setForeground(Color.white);
		exitBtn.setBorder(BorderFactory.createLineBorder(Color.white));
		exitBtn.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		exitBtn.setFocusPainted(false);
		exitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		gbc.gridx = 8;
		gbc.gridwidth = 1;
		gbc.weightx = 1;
		gbl.setConstraints(exitBtn, gbc);
		add(exitBtn);
		// -----jsp----------------
		gbc.insets = new Insets(0, 4, 3, 4);
		jsp = new JScrollPane();
		jsp.getViewport().setOpaque(false);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 9;
		gbc.gridheight = 9;
		gbc.weightx = 9;
		gbc.weighty = 9;
		gbl.setConstraints(jsp, gbc);
		add(jsp);
		revalidate();
	}

	void refreshJsp(ArrayList<PlayerVO> arr) {
		int size = arr.size();
		JPanel resultPnl = new JPanel();
		resultPnl.setBackground(Color.white);
		resultPnl.setLayout(new GridLayout((size + 1) / 2, 2));
		for (int i = 0; i < size; i++) {
			ResultLabel lbl = new ResultLabel(arr.get(i).getName(), arr.get(i));
			resultPnl.add(lbl);
		}
		resultPnl.setPreferredSize(new Dimension(500,60*(size + 1) / 2));
		jsp.getViewport().add(resultPnl);
	}

	// --------------------------------------------------
	public static void main(String[] args) {
		new PlayerChooser(new VOLabel(null, null));
	}

	class ResultLabel extends VOLabel {
		private static final long serialVersionUID = 1L;

		ResultLabel(String name, PlayerVO p) {
			super(name, p);
			ImageIcon i = new ImageIcon("image/player/portrait/" + name
					+ ".png");
			i.setImage(i.getImage().getScaledInstance(75, 60,
					Image.SCALE_SMOOTH));
			setIcon(i);
			setForeground(Style.DEEP_BLUE);
			setFont(new Font("华文细黑", Font.PLAIN, 14));
			addMouseListener(new ResultLblListener());
		}
	}

	class ResultLblListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			ResultLabel temp = (ResultLabel) e.getSource();
			owner.setVO(temp.getVO());
			PlayerChooser.this.dispose();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			ResultLabel temp = (ResultLabel) e.getSource();
			temp.setForeground(Style.FOCUS_BLUE);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			ResultLabel temp = (ResultLabel) e.getSource();
			temp.setForeground(Style.DEEP_BLUE);
		}

	}

	class jtfListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			ArrayList<PlayerVO> searchResult = p.getPlayerBaseInfo(jtf
					.getText());
			refreshJsp(searchResult);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}
}