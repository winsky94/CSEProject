package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ModeChangeButton extends JButton {

	/**
	 * btnMode_说明按钮创建者的身份：
	 * 0_当前为球队卡片模式，按钮显示卡片模式图案，按下后listener切换界面到球队列表模式
	 * 1_当前为球队列表模式，按钮显示列表模式图案，按下后listener切换界面到球队卡片模式
	 * 2_当前为球员卡片模式，按钮显示卡片模式图案，按下后listener切换界面到球员列表模式
	 * 3_当前为球员列表模式，按钮显示列表模式图案，按下后listener切换界面到球员卡片模式
	 */
	private static final long serialVersionUID = 1L;
	int btnMode;

	public ModeChangeButton(int modeNow) {
		super();
		btnMode = modeNow;
		switch (btnMode) {
		case 0:
			this.setText("卡");
			;
			break;
		case 1:
			this.setText("表");
			;
			break;
		case 2:
			this.setText("卡");
			;
			break;
		case 3:
			this.setText("表");
			;
			break;
		}
		this.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				switch (btnMode) {
				case 0:
					teamCardModeClicked();
					;
					break;
				case 1:
					teamListModeClicked();
					;
					break;
				case 2:
					playerCardModeClicked();
					;
					break;
				case 3:
					playerListModeClicked();
					;
					break;
				}

			}
		});
	}
	private void teamCardModeClicked(){
		System.out.println("现在是球队卡片模式，按下后变为球队列表模式");
	}
	private void teamListModeClicked(){
		System.out.println("现在是球队列表模式，按下后变为球队卡片模式");
	}
	private void playerCardModeClicked(){
		System.out.println("现在是球员卡片模式，按下后变为球员列表模式");
	}
	private void playerListModeClicked(){
		System.out.println("现在是球员列表模式，按下后变为球员卡片模式");
	}
}
