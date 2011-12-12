package View;

import javax.swing.JPanel;

/**
 * 
 * @author Team Exception
 * 
 *         This class is the super class for all the JPanel views that will be
 *         used in this application.
 * 
 * @see TitleView, LanView, HostView, ClientView, TankView
 * 
 * @extends JPanel
 * 
 * @serial 1L
 * 
 */
public class MasterViewPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	protected MasterView m;

	/**
	 * This is the class constructor for the MasterViewPanel which essentially
	 * just creates an instance of the MasterViewPanel which is a JPanel.
	 * 
	 * @param m
	 *            this is the master view (itself)
	 * 
	 * @category constructor
	 */
	public MasterViewPanel(MasterView m) {
		this.m = m;
	}
}
