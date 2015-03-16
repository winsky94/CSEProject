package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import presentation.mainui.MainFrame;
import presentation.mainui.SequencePane;

public class OrderChangeButton extends JButton{

	/**btnMode_列表现在的状态
	 * 0_降序descending
	 * 1_升序ascending
	 * 
	 */
	
	private int model;//0球队，1球员
	private static final long serialVersionUID = 1L;
	int btnMode;
	public OrderChangeButton(int orderNow,final int model){
		super();
		this.model=model;
		btnMode=orderNow;
		switch(btnMode){
		case 0:this.setText("降序");break;
		case 1:this.setText("升序");break;
		}
		this.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				SequencePane se=new SequencePane(model);
				MainFrame.getInstance().add(se);
				switch(btnMode){
				
				case 0:changeToAscending();break;
				case 1:changeToDescending();break;
				}
				
			}
		});
	}
	public void changeToAscending(){
		
	}
	public void changeToDescending(){
		
	}

}
