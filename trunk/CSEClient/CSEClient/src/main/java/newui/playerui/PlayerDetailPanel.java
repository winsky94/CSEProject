package newui.playerui;

import newui.FatherPanel;

public class PlayerDetailPanel extends FatherPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	public PlayerDetailPanel(String pname){
		name=pname;
		System.out.println(name);
	}
}
