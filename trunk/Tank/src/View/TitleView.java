package View;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Team Exception
 * 
 *         This class creates the TitleView that is the first panel that the
 *         user sees. This includes JButtons that link to a "New Game", "LAN",
 *         and "Exit". It also includes a banner for the TANKS game. The
 *         preferred size of this window should be around 640 by 640 pixels.
 * 
 * @extends MasterViewPanel (JPanel)
 * 
 * @see LanView, TankView, MasterViewPanel
 * 
 * 
 */


public class TitleView extends MasterViewPanel {

	private static final long serialVersionUID = 1L;
	JPanel title;
	JButton newGame, lan, option, exit;
	JPanel empty1 = new JPanel();
	GridBagConstraints c, d;

	/**
	 * Class constructor for the class TitleView. Has a super call to
	 * MasterView. This calls the setupPanel method that inserts the TANKS game
	 * banner and the "New Game", "LAN", and "EXIT" buttons.
	 * 
	 * @category constructor
	 * 
	 * @param m
	 *            this is the masterview object
	 */
	public TitleView(MasterView m) {

		super(m);// call to super class
		setupPanel();// calls private method to set up panel
		this.setVisible(true);// sets this panel to visible

	}

	/**
	 * This method sets up the format for the panel itself using the Java class
	 * GridBagLayout. The banner is in the top row while the buttons are split
	 * along the bottom row for this layout.
	 * 
	 * @see TitleView, GridBagLayout
	 * 
	 */
	public void setupPanel() {

		this.setLayout(new GridBagLayout());
		c = new GridBagConstraints();// constraints for banner
		d = new GridBagConstraints();// constraints for buttons

		// adding top banner to container
		JLabel banner = new JLabel(new ImageIcon("images/TanksTitleView.png"));
		// c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		this.add(banner, c);

		// adding newGame button
		newGame = new JButton("SINGLEPLAYER");
		newGame.addActionListener(new newGameListener());
		// d.fill = GridBagConstraints.HORIZONTAL;
		d.anchor = GridBagConstraints.CENTER;
		d.ipady = 20;
		d.ipadx = 134;
		d.gridx = 0;
		d.gridy = 1;
		this.add(newGame, d);

		// adding lan button
		lan = new JButton("LAN");
		lan.addActionListener(new lanListener());
		d.gridx = 1;
		d.gridy = 1;
		this.add(lan, d);

		// adding exit button
		exit = new JButton("EXIT");
		exit.addActionListener(new exitListener());
		d.gridx = 2;
		d.gridy = 1;
		this.add(exit, d);

	}

	/**
	 * 
	 * @author Team Exception
	 * 
	 *         This private class allows the listener to go to the TankView when
	 *         clicked (single player). This is done by calling MasterView's
	 *         "changeView" method.
	 * 
	 * @category inner class
	 * 
	 * @see TitleView, MasterView, TankView, changeView
	 * 
	 * @param arg0
	 *            ActionEvent for when the "New Game" button is depressed.
	 * 
	 */
	private class newGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			m.changeView(Views.TANKVIEW, 1);// calling the MasterView's
											// changeView method
		}

	}

	/**
	 * 
	 * @author Team Exception
	 * 
	 *         This allows the user to exit the application when the exit button
	 *         is pressed. This is done by calling MasterView's "changeView"
	 *         method.
	 * 
	 * @category inner class
	 * 
	 * @see TitleView, MasterView, TankView, changeView
	 * 
	 * @param arg0
	 *            ActionEvent for when the "EXIT" button is depressed.
	 * 
	 */
	private class exitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);// calling Java's system exit class

		}

	}

	/**
	 * 
	 * @author Team Exception
	 * 
	 *         This allows the user to open up the LAN view for multiplayer when
	 *         the LAN button is pressed. This is done by calling MasterView's
	 *         "changeView" method.
	 * 
	 * @category inner class
	 * 
	 * @see TitleView, MasterView, TankView, changeView
	 * 
	 * @param arg0
	 *            ActionEvent for when the "LAN" button is depressed.
	 * 
	 */
	private class lanListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			m.changeView(Views.LAN, null);// calling MasterView's changeView
											// method

		}

	}

}
