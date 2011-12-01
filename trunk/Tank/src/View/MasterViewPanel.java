package View;

import javax.swing.JPanel;

/**
 * 
 * @author Team Exception
 * 
 * This class is the super class for all the JPanel views that will be used in this application.
 *
 */
public class MasterViewPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	protected MasterView m;

	/**
	 * Class constructor
	 * 
	 * @param m this is the master view (itself)
	 */
	public MasterViewPanel(MasterView m) {
		this.m = m;
	}
}
