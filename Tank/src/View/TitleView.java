package View;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
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

		super(m);
		setupPanel();
		// setupTop();
		// setupTitle();
		// setupBot();
		this.setVisible(true);

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
		c = new GridBagConstraints();


		// adding top banner to container
		JLabel banner = new JLabel(new ImageIcon("images/TANKSTITLEVIEW.png"));
		//c.fill = GridBagConstraints.VERTICAL;
		//c.fill = GridBagConstraints.HORIZONTAL;
		//c.weightx = 0.0;
		//c.gridwidth = 3;
		c.gridx = 0;// specifying to be top, left component
		c.gridy = 0;
		//this.add(banner, c);

		d = new GridBagConstraints();

		// adding newGame button
		newGame = new JButton("SINGLEPLAYER");
		newGame.addActionListener(new newGameListener());
		d.anchor = GridBagConstraints.CENTER;
		d.fill = GridBagConstraints.HORIZONTAL;
		d.fill = GridBagConstraints.VERTICAL;
		d.ipady = 5;
		d.weightx = 0;
		d.gridx = 0;
		d.gridy = 1;
		this.add(newGame, d);

		// adding lan button
		lan = new JButton("LAN");
		lan.addActionListener(new lanListener());
		// d.fill = GridBagConstraints.HORIZONTAL;
		// d.fill = GridBagConstraints.VERTICAL;
		d.weightx = 0;
		d.gridx = 1;
		d.gridy = 1;
		this.add(lan, d);

		// adding options button
		option = new JButton("OPTIONS");
		option.addActionListener(new optionListener());
		// d.fill = GridBagConstraints.HORIZONTAL;
		// d.fill = GridBagConstraints.VERTICAL;
		d.weightx = 0;
		d.gridx = 2;
		d.gridy = 1;
		this.add(option, d);

		// adding exit button
		exit = new JButton("EXIT");
		exit.addActionListener(new exitListener());
		// d.fill = GridBagConstraints.HORIZONTAL;
		// d.fill = GridBagConstraints.VERTICAL;
		d.weightx = 0;
		d.gridx = 3;
		d.gridy = 1;
		this.add(exit, d);
		
	}

	/**
	 * This metho d sets up the top banner for title view
	 */
	public void setupTop() {
		JLabel banner = new JLabel(new ImageIcon("images/diamondPlate.png"));
		this.add(banner);

		// this.add(empty1);
	}

	/**
	 * This method sets up the title view page for the tanks game which includes
	 * a new game, lan, exit, and option button.
	 */
	public void setupTitle() {

		// title = new JPanel(new GridLayout(1, 4));
		// newGame = new JButton("SINGLEPLAYER");
		// newGame.addActionListener(new newGameListener());
		// lan = new JButton("LAN");
		// lan.addActionListener(new lanListener());
		// option = new JButton("OPTIONS");
		// option.addActionListener(new optionListener());
		// exit = new JButton("EXIT");
		// exit.addActionListener(new exitListener());
		// title.add(newGame);
		// title.add(lan);
		// title.add(option);
		// title.add(exit);
		// this.add(title);
	}

	/**
	 * This method sets up the bottom banner for the tittle view.
	 */
	public void setupBot() {
		JLabel banner = new JLabel(new ImageIcon("images/diamondPlate.png"));
		this.add(banner);
		// this.add(empty1);
	}

	/**
	 * 
	 * @author Team Exception
	 * 
	 *         This private class allows the listener to go to the tank view
	 *         when clicked (single player)
	 * 
	 */
	private class newGameListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			m.changeView(Views.TANKVIEW,1);
		}

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
	private class optionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			m.changeView(Views.NETWORKTANKVIEW,1);

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
			System.exit(0);

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
			m.changeView(Views.LAN,null);// calling MasterView's changeView
			// method


		}

	}

}
