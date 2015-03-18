package presentation;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JWindow;

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
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		switch(btnMode){
		case 0:this.setText("降序");break;
		case 1:this.setText("升序");break;
		}
		this.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
			//	Point p=e.getPoint();//是否对
			//	SequencePane se=new SequencePane(model);
			//	se.setVisible(true);
				
			//	se.setBounds(e.getXOnScreen(), e.getYOnScreen(), 100, 400);
			
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
