package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class OrderChangeButton extends JButton{

	/**btnMode_列表现在的状态
	 * 0_降序descending
	 * 1_升序ascending
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int btnMode;
	public OrderChangeButton(int orderNow){
		super();
		btnMode=orderNow;
		switch(btnMode){
		case 0:this.setText("降序");break;
		case 1:this.setText("升序");break;
		}
		this.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
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
