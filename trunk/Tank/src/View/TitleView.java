package View;

import java.awt.GridLayout;
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
 *         user sees.
 * 
 */
public class TitleView extends MasterViewPanel {

	private static final long serialVersionUID = 1L;
	JPanel title;
	JButton newGame, lan, option, exit;
	JPanel empty1 = new JPanel();

	/**
	 * Class constructor
	 * 
	 * @param m
	 *            this is the masterview object
	 */
	public TitleView(MasterView m) {

		super(m);
		setupPanel();
		setupTop();
		setupTitle();
		setupBot();
		this.setVisible(true);

	}

	/**
	 * This method sets up the format for the panel itself particularly the
	 * layout.
	 */
	public void setupPanel() {

		this.setLayout(new GridLayout(3, 1));
		// this.setBackground(Color.black);

	}

	/**
	 * This method sets up the top banner for tittle view
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

		title = new JPanel(new GridLayout(1, 4));
		newGame = new JButton("SINGLEPLAYER");
		newGame.addActionListener(new newGameListener());
		lan = new JButton("LAN");
		lan.addActionListener(new lanListener());
		option = new JButton("OPTIONS");
		option.addActionListener(new optionListener());
		exit = new JButton("EXIT");
		exit.addActionListener(new exitListener());
		title.add(newGame);
		title.add(lan);
		title.add(option);
		title.add(exit);
		this.add(title);
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
	 *         This allows the user to go the options panel when it is pressed.
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
	 *         is pressed.
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
	 *         the LAN button is pressed.
	 * 
	 */
	private class lanListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			m.changeView(Views.LAN,null);

		}

	}

}
